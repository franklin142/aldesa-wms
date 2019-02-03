package org.aldesa.wms.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.aldesa.wms.model.Bodega;
import org.aldesa.wms.model.EstadoMercancia;
import org.aldesa.wms.model.Muelle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BodegaDaoImpl implements BodegaDao{
	@Autowired
	private EntityManager em;


	public List<EstadoMercancia> getAllEstadosMercancia(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EstadoMercancia> criteria = cb.createQuery(EstadoMercancia.class);
		Root<EstadoMercancia> user = criteria.from(EstadoMercancia.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Bodega> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Bodega> criteria = cb.createQuery(Bodega.class);
		Root<Bodega> user = criteria.from(Bodega.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	public List<Muelle> getAllMuelle(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Muelle> criteria = cb.createQuery(Muelle.class);
		Root<Muelle> user = criteria.from(Muelle.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void save(Bodega dmr) {
		em.persist(dmr);
	}

	@Override
	public Bodega byBulk(String bulk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Bodega> criteria = cb.createQuery(Bodega.class);
		Root<Bodega> user = criteria.from(Bodega.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList().get(0);
	}

	@Override
	public Bodega getById(String pk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Bodega> criteria = cb.createQuery(Bodega.class);
		Root<Bodega> user = criteria.from(Bodega.class);
		criteria.where(cb.equal(user.get("bodega"), pk));
		criteria.select(user);
		return em.createQuery(criteria).getResultList().get(0);
	}

}
