package org.aldesa.wms.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.aldesa.wms.model.DetMercRec;
import org.aldesa.wms.model.DetMercRecPK;
import org.aldesa.wms.model.MercadRecibidaPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DetMercRecDaoImpl implements DetMercRecDao{
	@Autowired
	private EntityManager em;

	@Override
	public List<DetMercRec> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetMercRec> criteria = cb.createQuery(DetMercRec.class);
		Root<DetMercRec> user = criteria.from(DetMercRec.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void save(DetMercRec dmr) {
		em.persist(dmr);
	}

	@Override
	public DetMercRec byBulk(String bulk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetMercRec> criteria = cb.createQuery(DetMercRec.class);
		Root<DetMercRec> user = criteria.from(DetMercRec.class);
		// criteria.where(cb.equal(user.get("pk.recepcion_Mercaderia_No"), pk.getRecepcion_Mercaderia_No()));
		criteria.select(user);
		return em.createQuery(criteria).getResultList().get(0);
	}

	@Override
	public DetMercRec getById(DetMercRecPK pk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetMercRec> criteria = cb.createQuery(DetMercRec.class);
		Root<DetMercRec> user = criteria.from(DetMercRec.class);
		criteria.where(cb.equal(user.get("id"), pk));
		criteria.select(user);
		return em.createQuery(criteria).getResultList().get(0);
	}

	@Override
	public List<DetMercRec> getAll(MercadRecibidaPK merc_rec) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetMercRec> criteria = cb.createQuery(DetMercRec.class);
		Root<DetMercRec> user = criteria.from(DetMercRec.class);
		criteria.where(
			cb.and(
				cb.equal(user.get("id").get("recepcion_Mercaderia_No"), merc_rec.getRecepcionMercaderiaNo()),
				cb.equal(user.get("id").get("deposito"), merc_rec.getDepositoNo()),
				cb.equal(user.get("id").get("recepcion_No_"), merc_rec.getEntregaNo())
		));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

}
