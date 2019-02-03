

package org.aldesa.wms.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.aldesa.wms.model.Bulk;
import org.aldesa.wms.model.BulkPendAutorizar;
import org.aldesa.wms.model.Deposito;
import org.aldesa.wms.model.MIEDESC_v;
import org.aldesa.wms.model.MercPendRecibir;
import org.aldesa.wms.model.MercRecibidaCompleta;
import org.aldesa.wms.model.MercadRecibida;
import org.aldesa.wms.model.MercadRecibidaPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class MercadRecibidaDaoImpl implements MercadRecibidaDao{
	@Autowired
	private EntityManager em;

	@Override
	public List<MercadRecibida> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MercadRecibida> criteria = cb.createQuery(MercadRecibida.class);
		Root<MercadRecibida> user = criteria.from(MercadRecibida.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void save(MercadRecibida dmr) {
		em.persist(dmr);
	}

	@Override
	public MercadRecibida byBulk(String bulk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MercadRecibida> criteria = cb.createQuery(MercadRecibida.class);
		Root<MercadRecibida> user = criteria.from(MercadRecibida.class);
		// criteria.where(cb.equal(user.get("pk.recepcion_Mercaderia_No"), pk.getRecepcion_Mercaderia_No()));
		criteria.select(user);
		return em.createQuery(criteria).getResultList().get(0);
	}

	@Override
	public MercadRecibida getById(MercadRecibidaPK pk) {
		//return em.find(MercadRecibida.class, pk);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MercadRecibida> criteria = cb.createQuery(MercadRecibida.class);
		Root<MercadRecibida> user = criteria.from(MercadRecibida.class);
		criteria.where(
			cb.equal(user.get("mercadRecibidaPK").get("depositoNo"), pk.getDepositoNo())
		);
		criteria.select(user);
		return em.createQuery(criteria).getSingleResult();
	}

	@Override
	public Deposito getDepositoById(String id_deposito) {
		return em.find(Deposito.class, id_deposito);
	}

	@Override
	public void update(MercadRecibida dmr) {
		MercadRecibidaPK pk = dmr.getMercadRecibidaPK();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MercadRecibida> criteria = cb.createQuery(MercadRecibida.class);
		Root<MercadRecibida> user = criteria.from(MercadRecibida.class);
		criteria.where(
			cb.equal(user.get("mercadRecibidaPK").get("depositoNo"), pk.getDepositoNo())
		);
		criteria.select(user);
		MercadRecibida mr = em.createQuery(criteria).getSingleResult();
		mr.setUtilizaDAN(dmr.getUtilizaDAN());
		mr.setCapacidad(dmr.getCapacidad());
		mr.setHoraautorizacioncortemarcham(dmr.getHoraautorizacioncortemarcham());
		mr.setDescargadopor(dmr.getDescargadopor());
		mr.setFechadescarga(dmr.getFechadescarga());
		mr.setFecha(dmr.getFecha());
	}

	@Override
	public MIEDESC_v saveMie(MIEDESC_v miev) {
		MIEDESC_v mv = em.find(MIEDESC_v.class, miev.getDeposito_No());
		if (mv==null) return null;
		mv.setUtilizo_DAN(miev.getUtilizo_DAN());
		mv.setFecha(miev.getFecha());
		mv.setCapacidad(miev.getCapacidad());
		mv.setFecha_de_Descarga_Cont(miev.getFecha_de_Descarga_Cont());
		mv.setHora_autorizacion_corte_marcham(miev.getHora_autorizacion_corte_marcham());
		return mv;
	}

	@Override
	public Bulk saveBulk(Bulk bulk) {
		em.persist(bulk);
		em.flush();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Bulk> criteria = cb.createQuery(Bulk.class);
		Root<Bulk> user = criteria.from(Bulk.class);
		criteria.where(
			cb.or(
				cb.equal(user.get("deposito"), bulk.getDeposito()),
				cb.equal(user.get("depositoDesc"), bulk.getDepositoDesc())
		));
		criteria.orderBy(cb.desc(user.get("codigoBulk")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList().get(0);
	}


	@Override
	public Bulk saveBulkDesc(Bulk bulk) {
		bulk.setDepositoDesc(bulk.getDeposito());
		em.persist(bulk);
		em.flush();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Bulk> criteria = cb.createQuery(Bulk.class);
		Root<Bulk> user = criteria.from(Bulk.class);
		criteria.where(
			cb.and(
				cb.equal(user.get("depositoDesc"), bulk.getDepositoDesc())
		));
		criteria.orderBy(cb.desc(user.get("codigoBulk")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList().get(0);
	}
	
	@Override
	public List<MercPendRecibir> getMercaderiaPendiente(String deposito) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MercPendRecibir> criteria = cb.createQuery(MercPendRecibir.class);
		Root<MercPendRecibir> user = criteria.from(MercPendRecibir.class);
		criteria.where(cb.equal(user.get("deposito"), deposito));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<MercRecibidaCompleta> getAllFull() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MercRecibidaCompleta> criteria = cb.createQuery(MercRecibidaCompleta.class);
		Root<MercRecibidaCompleta> user = criteria.from(MercRecibidaCompleta.class);
		criteria.orderBy(cb.asc(user.get("deposito_No")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

    @Override
    public List<MercRecibidaCompleta> getAllFull(String deposito) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MercRecibidaCompleta> criteria = cb.createQuery(MercRecibidaCompleta.class);
        Root<MercRecibidaCompleta> user = criteria.from(MercRecibidaCompleta.class);
        criteria.where(cb.like(user.<String>get("deposito_No"), "%" + deposito + "%"));
        criteria.orderBy(cb.asc(user.get("deposito_No")));
        criteria.select(user);
        return em.createQuery(criteria).getResultList();
    }
	@Override
	public List<BulkPendAutorizar> getAllBulkPendientes() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BulkPendAutorizar> criteria = cb.createQuery(BulkPendAutorizar.class);
		Root<BulkPendAutorizar> user = criteria.from(BulkPendAutorizar.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}
	

}
