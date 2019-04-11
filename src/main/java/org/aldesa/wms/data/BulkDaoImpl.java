package org.aldesa.wms.data;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.aldesa.wms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BulkDaoImpl implements BulkDao{
	@Autowired
	private EntityManager em;

	@Override
	public List<Bulk> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Bulk> criteria = cb.createQuery(Bulk.class);
		Root<Bulk> user = criteria.from(Bulk.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

    public List<BulkSugerido> getSugerencia(String deposito){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BulkSugerido> criteria = cb.createQuery(BulkSugerido.class);
        Root<BulkSugerido> user = criteria.from(BulkSugerido.class);
        criteria.where(cb.equal(user.get("deposito"), deposito));
        criteria.select(user);
        return em.createQuery(criteria).getResultList();
    }

	@Override
	public Bulk save(Bulk dmr) {
		Bulk dmr2 = em.find(Bulk.class, dmr.getCodigoBulk());
		if(dmr2==null){
			return null;
		}
		if(dmr.getPosicionEstante()!=null)
			dmr2.setPosicionEstante(dmr.getPosicionEstante());
		if(dmr.getPosicionPasillo()!=null)
			dmr2.setPosicionPasillo(dmr.getPosicionPasillo());
		if(dmr.getPatio()!=null)
			dmr2.setPatio(dmr.getPatio());
		if(dmr.getEstado()!=null)
			dmr2.setEstado(dmr.getEstado());
		if(dmr.getTarimaFlejada()!=null)
			dmr2.setTarimaFlejada(dmr.getTarimaFlejada());
		if(dmr.getPeso()!=null)
			dmr2.setPeso(dmr.getPeso());
		if(dmr.getAncho()!=null)
			dmr2.setAncho(dmr.getAncho());
		if(dmr.getAlto()!=null)
			dmr2.setAlto(dmr.getAlto());
		if(dmr.getProfundidad()!=null)
			dmr2.setProfundidad(dmr.getProfundidad());
		return dmr2;
	}

	@Override
	public Bulk byBulk(String bulk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Bulk> criteria = cb.createQuery(Bulk.class);
		Root<Bulk> user = criteria.from(Bulk.class);
		// criteria.where(cb.equal(user.get("pk.recepcion_Mercaderia_No"), pk.getRecepcion_Mercaderia_No()));
		criteria.select(user);
		return em.createQuery(criteria).getResultList().get(0);
	}

	@Override
	public Bulk getById(String pk) {
		return em.find(Bulk.class, pk);
	}

	@Override
	public BulkV getById_v(String pk) {
		return em.find(BulkV.class, pk);
	}

	@Override
	public BulkR getByIdr_v(String pk) {
		return em.find(BulkR.class, pk);
	}
	
	@Override
	public PosicionDisponibleV getPosicionDispById(String pk){
		return em.find(PosicionDisponibleV.class, pk);
	}

	@Override
	public Patio saveEstadoPatio(Patio a) {
		Patio dmr2 = em.find(Patio.class, a.getCodigoPatio());
		if(dmr2==null)
			return null;
		dmr2.setEstado(a.getEstado());
		return dmr2;
	}

	@Override
	public PosicionEstante savePosEstante(PosicionEstante a) {
		PosicionEstante dmr2 = em.find(PosicionEstante.class, a.getCodigoPosicionEstante());
		if(dmr2==null)
			return null;
		dmr2.setEstado(a.getEstado());
		return dmr2;
	}

	@Override
	public PosisionPasillo savePosPasillo(PosisionPasillo a) {
		PosisionPasillo dmr2 = em.find(PosisionPasillo.class, a.getCodigoPosicionPasillo());
		if(dmr2==null)
			return null;
		dmr2.setEstado(a.getEstado());
		return dmr2;
	}

	@Override
	public OrdenRetiro getOrdenRetiroById(int pk) {
		return em.find(OrdenRetiro.class, pk);
	}

	@Override
	public Cliente getClienteById(String pk) {
		return em.find(Cliente.class, pk);
	}

	@Override
	public List<DetalleBulk> getDetalleByBulk(String bulk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetalleBulk> criteria = cb.createQuery(DetalleBulk.class);
		Root<DetalleBulk> user = criteria.from(DetalleBulk.class);
		criteria.where(cb.equal(user.get("id").get("codigoBulk"), bulk));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<DetalleBulk> getDetalles() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetalleBulk> criteria = cb.createQuery(DetalleBulk.class);
		Root<DetalleBulk> user = criteria.from(DetalleBulk.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public List<DetalleBulkV> getDetalleByBulk2(String bulk) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetalleBulkV> criteria = cb.createQuery(DetalleBulkV.class);
		Root<DetalleBulkV> user = criteria.from(DetalleBulkV.class);
		criteria.where(cb.equal(user.get("id").get("codigoBulk"), bulk));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<DetalleBulkV> getDetalles2() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetalleBulkV> criteria = cb.createQuery(DetalleBulkV.class);
		Root<DetalleBulkV> user = criteria.from(DetalleBulkV.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<DetalleBulkRV> getDetalles3() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetalleBulkRV> criteria = cb.createQuery(DetalleBulkRV.class);
		Root<DetalleBulkRV> user = criteria.from(DetalleBulkRV.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}
	
	public List<DetalleBulkRV> getDetalles3(String orden){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetalleBulkRV> criteria = cb.createQuery(DetalleBulkRV.class);
		Root<DetalleBulkRV> user = criteria.from(DetalleBulkRV.class);
		criteria.where(cb.equal(user.get("id").get("codigoBulk"), orden));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	public List<OrdenReingreso> getOrdenReingreso(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrdenReingreso> criteria = cb.createQuery(OrdenReingreso.class);
		Root<OrdenReingreso> user = criteria.from(OrdenReingreso.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}
	
	public List<OrdenReingreso> getOrdenReingreso(String orden){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrdenReingreso> criteria = cb.createQuery(OrdenReingreso.class);
		Root<OrdenReingreso> user = criteria.from(OrdenReingreso.class);
		criteria.where(cb.equal(user.get("retiroMercaderia"), orden));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}
	
	@Override	
	public DetalleBulk updateDetalle(DetalleBulk db) {
		
		String sqlString = "CALL PRC_Actualiza_DetBulk(:bulk, :prod, :cliente, :cantidad, :nlote);";
		Query q = em.createNativeQuery(sqlString);
		
		q.setParameter("bulk",db.getId().getCodigoBulk())
			.setParameter("prod", db.getId().getCodigoProducto())
			.setParameter("cliente", db.getCliente())
			.setParameter("cantidad", db.getCantidad())
			.setParameter("nlote", db.getnLote())
			.executeUpdate();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetalleBulk> criteria = cb.createQuery(DetalleBulk.class);
		Root<DetalleBulk> user = criteria.from(DetalleBulk.class);
		criteria.where(
			cb.and(	cb.equal(user.get("id").get("codigoBulk"), db.getId().getCodigoBulk()),
					cb.equal(user.get("id").get("codigoProducto"), db.getId().getCodigoProducto()),
					cb.equal(user.get("id").get("estadoMerc"), db.getId().getEstadoMerc())
		));
		criteria.select(user);
		DetalleBulk db2 = em.createQuery(criteria).getSingleResult();
		return db2;
	}

	public List<String> reingresoDetalleBulk(String bulk, String retiro, String posicion) {

		String sqlString = "select fun_reingreso_DetBulk(:bulk, :retiro, :posicion) from dual;";
		Query q = em.createNativeQuery(sqlString);
		       q.setParameter("bulk", bulk)
				.setParameter("retiro", retiro)
				.setParameter("posicion", posicion);
				//.executeUpdate();
		        List<String> result = q.getResultList();
		        return result;
		        //return 1;
	}

	public List<String> validaEntrega(String retiro, String cliente) {

		String sqlString = "select fun_ValidaEntrega(:retiro, :cliente) from dual;";
		Query q = em.createNativeQuery(sqlString);
		       q.setParameter("retiro", retiro)
				.setParameter("cliente", cliente);
				//.executeUpdate();
		        List<String> result = q.getResultList();
		        return result;
		        //return 1;
	}

	public void updateDetalle(String bulk, String producto, double cantidad,String estado,String nlote){

		String sqlString = "Update detalle_bulk Set cantidad=:cantidad, estado_merc=:estado_merc "
					+ "Where codigo_bulk =:bulk "
					+ "And codigo_producto=:prod "
					+ "And No_Lote=:nlote "
					+";";
		 
		Query query = em.createNativeQuery(sqlString);
		query.setParameter("cantidad", cantidad)
			.setParameter("estado_merc", estado)
			.setParameter("nlote", nlote)
			.setParameter("bulk", bulk)
			.setParameter("prod", producto)
			.executeUpdate();
		return;
	}

	@Override
	public DetalleBulk insert(DetalleBulk dmr) {
		em.persist(dmr);
		em.flush();
		return dmr;
	}

	@Override
	public List<Bulk> bulkByDeposito(String deposito) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Bulk> criteria = cb.createQuery(Bulk.class);
		Root<Bulk> user = criteria.from(Bulk.class);
			criteria.where(cb.equal(user.get("deposito"), deposito)
		);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<MercPendPreparar> despachoGetAllPendientes() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MercPendPreparar> criteria = cb.createQuery(MercPendPreparar.class);
		Root<MercPendPreparar> user = criteria.from(MercPendPreparar.class);
        criteria.orderBy(cb.asc(user.get("orden")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<DetalleMercPendPreparar> despachoGetAllDetallePendientes(int orden, String cliente) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetalleMercPendPreparar> criteria = cb.createQuery(DetalleMercPendPreparar.class);
		Root<DetalleMercPendPreparar> user = criteria.from(DetalleMercPendPreparar.class);
		criteria.where(cb.and(
		cb.equal(user.get("orden"), orden),
		cb.equal(user.get("Nombre"), cliente)
		));
		criteria.orderBy(cb.asc(user.get("correlativo")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}
	// luis mario
	@Override
    public List<DetalleMercPendPreparar> despachoGetAllDetallePendientesP(int orden, String cliente) {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<DetalleMercPendPreparar> criteria = cb.createQuery(DetalleMercPendPreparar.class);
                Root<DetalleMercPendPreparar> user = criteria.from(DetalleMercPendPreparar.class);
                criteria.where(cb.and(
                cb.equal(user.get("orden"), orden),
                cb.equal(user.get("consignatario"), cliente)
                ));
                criteria.orderBy(cb.asc(user.get("correlativo")));
                criteria.select(user);
                return em.createQuery(criteria).getResultList();
        }

	@Override
	public int autorizarDespachoOrden(int orden, String tipo) {


		String sqlString="CALL PRC_Actualiza_MR4(:orden , :tipo );";	
		Query query = em.createNativeQuery(sqlString);
		return query.setParameter("orden", orden).setParameter("tipo", tipo).executeUpdate();
	}

	@Override
	public List<MercPendDespachar> despachoGetAllPendientesDespachar() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MercPendDespachar> criteria = cb.createQuery(MercPendDespachar.class);
		Root<MercPendDespachar> user = criteria.from(MercPendDespachar.class);
		criteria.orderBy(cb.asc(user.get("orden")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public int autorizarEntrega() {

		String sqlString = "CALL PRC_Actualiza_MR5 ( :tipo )";
		return em.createNativeQuery(sqlString).setParameter("tipo", "S").executeUpdate();
	}
    
	public int autorizarEntrega(Integer orden, Date fecha, String cliente){
        String sqlString = "CALL PRC_Actualiza_MR5 ( :orden, :fecha, :cliente );";

        return em.createNativeQuery(sqlString)
                    .setParameter("orden", orden)
                    .setParameter("fecha", fecha)
                    .setParameter("cliente", cliente)
                    .executeUpdate();
    }

	@Override
	public void updateMieDesc(String deposito, String dan, Date fecha, String capacidad, String descargado,
			Date descarga, Date horaCorte) {

		String sqlString = "Update MIEDESC_v "
				+ "Set Utilizo_DAN=:dan, Fecha=:fecha, "
				+ "Capacidad=:capacidad, Descargado_por=:descargado, "
				+ "Fecha_de_Descarga_Cont=:descarga, "
				+ "Hora_autorizacion_corte_marcham=:hora "
				+ "Where Deposito_No=:deposito ;";
		Query q = em.createNativeQuery(sqlString);
		
		q.setParameter("dan", dan)
			.setParameter("fecha", fecha)
			.setParameter("capacidad", capacidad)
			.setParameter("descargado", descargado)
			.setParameter("descarga", descarga)
			.setParameter("hora", horaCorte)
			.setParameter("deposito", deposito)
			.executeUpdate();
		
	}

	@Override
	public MercRecibidaCompleta getMercaderiaCompletaByDeposito(String deposito) {
		/*
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MercRecibidaCompleta> criteria = cb.createQuery(MercRecibidaCompleta.class);
		Root<MercRecibidaCompleta> user = criteria.from(MercRecibidaCompleta.class);
		criteria.where(cb.equal(user.get("deposito_No"), deposito));
		criteria.select(user);
		return em.createQuery(criteria).getSingleResult();
		*/
		return em.find(MercRecibidaCompleta.class, deposito);
	}

	@Override
	public void finalizaRecepcion(String deposito, String tipo){
		String sqlString = "CALL PRC_Finaliza_Recepcion (:deposito, :tipo) ;";
		Query q = em.createNativeQuery(sqlString);

		q.setParameter("deposito", deposito)
				.setParameter("tipo", tipo)
				.executeUpdate();
	}
	
	@Override
	public void FinalizaRecepcion(String deposito, String tipo){
		String sqlString = "CALL PRC_Finaliza_Recepcion(:deposito, :tipo) ;";
		Query q = em.createNativeQuery(sqlString);
		
		q.setParameter("deposito",deposito)
			.setParameter("tipo", tipo)
			.executeUpdate();
	}

	@Override
	public void autorizarUbicacion(String forma, String entregado, String recibido, String observacion, String deposito, String tipo, String cliente, String item){
		String sqlString = "CALL PRC_Actualiza_MR2(:deposito, :forma, "
				+ ":entregado, :recibido, :observacion, :tipo, :cliente, :item) ;";
		Query q = em.createNativeQuery(sqlString);
		
		q.setParameter("forma",forma)
			.setParameter("entregado", entregado)
			.setParameter("recibido", recibido)
			.setParameter("observacion", observacion)
			.setParameter("deposito", deposito)
			.setParameter("tipo", tipo)
			.setParameter("cliente", cliente)
			.setParameter("item", item)
			.executeUpdate();
	}
	
	public void prc_actualiza_orden(String orden){
		String sqlString = "CALL PRC_Actualiza_Orden(:orden) ;";
		Query q = em.createNativeQuery(sqlString);
		q.setParameter("orden", orden)
				.executeUpdate();
	}

	// modificacion para fecha y hora de transito
	@Override
	public void ActualizaMR1(String deposito, String dan, String frecepcion, String capacidad, String descargado,
			String fmarchamo, String hmarchamo, String tipo, String bodega, String muelle, String fftransito, String hftransito) throws ParseException {
		String sqlString = "CALL PRC_Actualiza_MR1(:deposito, "
				+ ":dan, :frecepcion, :capacidad, :descargado, "
				+ ":fmarchamo, :hmarchamo, :tipo,:bodega,:muelle,:fftransito,:hftransito) ;";
		Query q = em.createNativeQuery(sqlString);
		int _dan=0;
		int _des=0;
		if (dan.trim().equalsIgnoreCase("1") || dan.trim().equalsIgnoreCase("on") || dan.trim().equalsIgnoreCase("checked"))
			_dan = 1;
		else
			_dan = 0;
		if (descargado.trim().equalsIgnoreCase("1") || descargado.trim().equalsIgnoreCase("on") || descargado.trim().equalsIgnoreCase("checked"))
			_des = 1;
		else
			_des = 0;
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		q.setParameter("deposito",deposito)
			.setParameter("dan",_dan)
			.setParameter("frecepcion", sdf1.parse(frecepcion))
			.setParameter("capacidad", capacidad)
			.setParameter("descargado", _des)
			.setParameter("fmarchamo", sdf1.parse(fmarchamo))
			.setParameter("hmarchamo", hmarchamo)
			.setParameter("tipo", tipo)
			.setParameter("bodega", bodega)
			.setParameter("muelle", muelle)
                        .setParameter("fftransito", sdf1.parse(fftransito))
                        .setParameter("hftransito", (hftransito))
			.executeUpdate();
	}

	@Override
	public void verificaBulk(String deposito) {
		String sqlString = "CALL PRC_Verifica_Bulk(:deposito) ;";
		Query q = em.createNativeQuery(sqlString);
		q.setParameter("deposito",deposito)
			.executeUpdate();
		
	}

	@Override
	public InformeRetiro getInformeById(String noRetiro) {
		return em.find(InformeRetiro.class, Integer.parseInt(noRetiro));
	}

	@Override
	public List<InformeRetiro>  getInformeRetiroList(String noRetiro, String cliente) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<InformeRetiro> criteria = cb.createQuery(InformeRetiro.class);
		Root<InformeRetiro> user = criteria.from(InformeRetiro.class);
		criteria.where(cb.and(
				cb.equal(user.get("retiroMercaderiaNo"), Integer.parseInt(noRetiro)),
				cb.equal(user.get("cliente"), cliente)
		));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}	

	@Override
	public InformeRetiro getInformeById(String noRetiro, String cliente) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<InformeRetiro> criteria = cb.createQuery(InformeRetiro.class);
		Root<InformeRetiro> user = criteria.from(InformeRetiro.class);
		criteria.where(cb.and(
				cb.equal(user.get("retiroMercaderiaNo"), Integer.parseInt(noRetiro)),
				cb.equal(user.get("cliente"), cliente)
		));
		criteria.select(user);
		return em.createQuery(criteria).getSingleResult();
	}
	
	@Override
	public List<DetalleInformeRetiro> getDetalleRetiro(String retiro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetalleInformeRetiro> criteria = cb.createQuery(DetalleInformeRetiro.class);
		Root<DetalleInformeRetiro> user = criteria.from(DetalleInformeRetiro.class);
		criteria.where(cb.equal(user.get("retiro_Mercaderia_No"), Integer.parseInt(retiro)));
		criteria.orderBy(cb.asc(user.get("deposito")), cb.asc(user.get("codigo")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<DetalleInformeRetiro> getDetalleRetiro(String retiro, String cliente) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DetalleInformeRetiro> criteria = cb.createQuery(DetalleInformeRetiro.class);
		Root<DetalleInformeRetiro> user = criteria.from(DetalleInformeRetiro.class);
		criteria.where(cb.and(
				cb.equal(user.get("retiro_Mercaderia_No"), Integer.parseInt(retiro)),
				cb.equal(user.get("cliente"), cliente)
		));
		criteria.orderBy(cb.asc(user.get("deposito")), cb.asc(user.get("codigo")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public UbicacionBulk byPosicion(String posicion) {
		return em.find(UbicacionBulk.class, posicion);
	}

	@Override
	public InformeIngreso getInformeIngresoById(String noDeposito) {
		return em.find(InformeIngreso.class, noDeposito);
	}

	@Override
	public List<InformeIngreso>  getInformeIngreso(String noDeposito) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<InformeIngreso> criteria = cb.createQuery(InformeIngreso.class);
		Root<InformeIngreso> user = criteria.from(InformeIngreso.class);
		criteria.where(	cb.equal(user.get("deposito"), noDeposito));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
}

    @Override
    public List<BulkActivos> getBulkActivos(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BulkActivos> criteria = cb.createQuery(BulkActivos.class);
        Root<BulkActivos> user = criteria.from(BulkActivos.class);
        criteria.select(user);
        return em.createQuery(criteria).getResultList();
    }

    public List<BulkActivos> getBulkActivos(String deposito){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BulkActivos> criteria = cb.createQuery(BulkActivos.class);
        Root<BulkActivos> user = criteria.from(BulkActivos.class);
        criteria.where(cb.equal(user.get("deposito"), deposito));
        criteria.select(user);
        return em.createQuery(criteria).getResultList();
    }

	@Override
	public List<MercRecibida> getMercRecibida() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MercRecibida> criteria = cb.createQuery(MercRecibida.class);
        Root<MercRecibida> user = criteria.from(MercRecibida.class);
        criteria.select(user);
        return em.createQuery(criteria).getResultList();
	}

    @Override
    public List<MercRecibida> getMercRecibida(String deposito) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MercRecibida> criteria = cb.createQuery(MercRecibida.class);
        Root<MercRecibida> user = criteria.from(MercRecibida.class);
        criteria.where(cb.equal(user.get("deposito"), deposito));
        criteria.select(user);
        return em.createQuery(criteria).getResultList();
    }
    @Override
    public List<MercRecibidalV> getMercRecibidalV(String deposito) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MercRecibidalV> criteria = cb.createQuery(MercRecibidalV.class);
        Root<MercRecibidalV> user = criteria.from(MercRecibidalV.class);
        criteria.where(cb.equal(user.get("deposito"), deposito));
        criteria.select(user);
        return em.createQuery(criteria).getResultList();
    }
    public void updateFechaFinDescarga(MercadRecibida mr){
        String sqlString= "Update MERCAD_RECIBIDA "
                + "Set Hora_fin_descarga=:hora "
                + "Where Deposito_No=:deposito ;";
        Query q = em.createNativeQuery(sqlString);
        q.setParameter("hora",mr.getHorafindescarga())
                .setParameter("deposito",
                    mr.getMercadRecibidaPK().getDepositoNo())
                .executeUpdate();
    }

    public void updateFechaFinDescargaD(MercadRecibida mr){
        String sqlString= "Update MIEDESC "
                + "Set hasta=:hora "
                + "Where Deposito_No=:deposito ;";
        Query q = em.createNativeQuery(sqlString);
        q.setParameter("hora",mr.getHorafindescarga())
                .setParameter("deposito",
                    mr.getMercadRecibidaPK().getDepositoNo())
                .executeUpdate();
    }

    @Override
    public List<OrdenEntrega> getOrdenEntrega(Integer orden, String cliente) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrdenEntrega> criteria = cb.createQuery(OrdenEntrega.class);
		Root<OrdenEntrega> user = criteria.from(OrdenEntrega.class);
		criteria.where(cb.and(
		cb.equal(user.get("retiroMercaderia"), orden),
		cb.equal(user.get("nombre"), cliente)
		));
		criteria.orderBy(cb.asc(user.get("deposito")), cb.asc(user.get("id").get("codigo")), cb.asc(user.get("estante")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
    }
	
// creacion de nuevo metodo luis mario
   @Override
    public List<OrdenEntrega> getOrdenEntregaP(Integer orden, String cliente) {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<OrdenEntrega> criteria = cb.createQuery(OrdenEntrega.class);
                Root<OrdenEntrega> user = criteria.from(OrdenEntrega.class);
                criteria.where(cb.and(
                cb.equal(user.get("retiroMercaderia"), orden),
                cb.equal(user.get("consignatario"), cliente)
                ));
                criteria.orderBy(cb.asc(user.get("deposito")), cb.asc(user.get("id").get("codigo")), cb.asc(user.get("estante")));
                criteria.select(user);
                return em.createQuery(criteria).getResultList();
    }

	public List<Object[]> getSubtotalesOrdenEntrega(Integer orden, String cliente){
		String sql = "SELECT \n" +
				"Ordenes_Entrega_v.Retiro_Mercaderia_No,\n" +
				"Ordenes_Entrega_v.Deposito,\n" +
				"Ordenes_Entrega_v.Correlativo,\n" +
				"CAST(Max(Ordenes_Entrega_v.Cantidad) AS SIGNED),\n" +
				"CAST(Sum(Ordenes_Entrega_v.Cantidad_preparada) AS SIGNED),\n" +
				"CAST(Sum(Ordenes_Entrega_v.Saldo) AS SIGNED),\n" +
				"CAST(Sum(Ordenes_Entrega_v.entregada) AS SIGNED)\n" +
				"FROM \n" +
				"Ordenes_Entrega_v\n" +
				"WHERE \n" +
				"Ordenes_Entrega_v.Retiro_Mercaderia_No=?\n" +
				"and Ordenes_Entrega_v.Nombre=?\n" +
				"GROUP BY \n" +
				"Ordenes_Entrega_v.Retiro_Mercaderia_No,\n" +
				"Ordenes_Entrega_v.Deposito,\n" +
				"Ordenes_Entrega_v.Correlativo";
		Query q = em.createNativeQuery(sql);
		q.setParameter(1, orden);
		q.setParameter(2, cliente);
		List<Object[]> detalles = q.getResultList();
		return detalles;
	}
	
	public List<Object[]> getSubtotalesOrdenReingreso(Integer orden, String cliente){
		String sql = "SELECT " 
				+ "	orden, "
				+ "	Deposito, "
				+ "	sum(Cantidad), "
				+ "	sum(cantidad_a_entregar), "
				+ "	sum(cantidad_entregada), "
				+ "	sum(Pendiente) "
				+ "	FROM "
				+ "		Merc_Pend_Preparar_det_v "
				+ "	WHERE "
				+ "		orden =? and Consignatario=? "
				+ "	GROUP BY "
				+ "		orden ";
		Query q = em.createNativeQuery(sql);
		q.setParameter(1, orden);
		q.setParameter(2, cliente);
		List<Object[]> detalles = q.getResultList();
		return detalles;
	}
	
	public List<Object[]> getTotalesOrdenEntrega(Integer orden, String cliente){
		String sql= "SELECT\n" +
				"\tT.Retiro_Mercaderia_No,\n" +
				"\tT.Deposito,\n" +
				"\tsum(T.Cantidad),\n" +
				"\tsum(T.Cantidad_preparada),\n" +
				"\tsum(T.Saldo),\n" +
				"\tsum(T.entregada)\n" +
				"FROM\n" +
				"\t(\n" +
				"\t\tSELECT\n" +
				"\t\t\tOrdenes_Entrega_v.Correlativo,\n" +
				"\t\t\tOrdenes_Entrega_v.Retiro_Mercaderia_No,\n" +
				"\t\t\tOrdenes_Entrega_v.Deposito,\n" +
				"\t\t\tCAST(Max(Ordenes_Entrega_v.Cantidad) AS SIGNED) AS Cantidad,\n" +
				"\t\t\tCAST(Sum(Ordenes_Entrega_v.Cantidad_preparada) AS SIGNED) AS Cantidad_preparada,\n" +
				"\t\t\tCAST(Sum(Ordenes_Entrega_v.Saldo) AS SIGNED) AS Saldo,\n" +
				"\t\t\tCAST(Sum(Ordenes_Entrega_v.entregada) AS SIGNED) AS entregada\n" +
				"\t\tFROM\n" +
				"\t\t\tOrdenes_Entrega_v\n" +
				"\t\tWHERE\n" +
				"\t\t\tOrdenes_Entrega_v.Retiro_Mercaderia_No = ?\n" +
				"\t\t\tand Ordenes_Entrega_v.Nombre= ?\n" +
				"\t\tGROUP BY\n" +
				"\t\t\tOrdenes_Entrega_v.Retiro_Mercaderia_No,\n" +
				"\t\t\tOrdenes_Entrega_v.Deposito,\n" +
				"\t\t\tOrdenes_Entrega_v.Correlativo\n" +
				"\t\tORDER BY\n" +
				"\t\t\tOrdenes_Entrega_v.Retiro_Mercaderia_No ASC,\n" +
				"\t\t\tOrdenes_Entrega_v.Deposito ASC,\n" +
				"\t\t\tOrdenes_Entrega_v.Correlativo ASC\n" +
				"\t) AS T\n" +
				"GROUP BY\n" +
				"\tT.Retiro_Mercaderia_No,\n" +
				"\tT.Deposito";
		Query q = em.createNativeQuery(sql);
		q.setParameter(1, orden);
		q.setParameter(2, cliente);
		List<Object[]> detalles = q.getResultList();
		return detalles;
	}

// agrego Luis Mario
	public List<Object[]> getTotalesOrdenEntregaP(Integer orden, String cliente){
                String sql= "SELECT\n" +
                                "\tT.Retiro_Mercaderia_No,\n" +
                                "\tT.Deposito,\n" +
                                "\tsum(T.Cantidad),\n" +
                                "\tsum(T.Cantidad_preparada),\n" +
                                "\tsum(T.Saldo),\n" +
                                "\tsum(T.entregada)\n" +
                                "FROM\n" +
                                "\t(\n" +
                                "\t\tSELECT\n" +
                                "\t\t\tOrdenes_Entrega_v.Correlativo,\n" +
                                "\t\t\tOrdenes_Entrega_v.Retiro_Mercaderia_No,\n" +
                                "\t\t\tOrdenes_Entrega_v.Deposito,\n" +
                                "\t\t\tCAST(Max(Ordenes_Entrega_v.Cantidad) AS SIGNED) AS Cantidad,\n" +
                                "\t\t\tCAST(Sum(Ordenes_Entrega_v.Cantidad_preparada) AS SIGNED) AS Cantidad_preparada,\n" +
                                "\t\t\tCAST(Sum(Ordenes_Entrega_v.Saldo) AS SIGNED) AS Saldo,\n" +
                                "\t\t\tCAST(Sum(Ordenes_Entrega_v.entregada) AS SIGNED) AS entregada\n" +
                                "\t\tFROM\n" +
                                "\t\t\tOrdenes_Entrega_v\n" +
                                "\t\tWHERE\n" +
                                "\t\t\tOrdenes_Entrega_v.Retiro_Mercaderia_No = ?\n" +
                                "\t\t\tand Ordenes_Entrega_v.Consignatario= ?\n" +
                                "\t\tGROUP BY\n" +
                                "\t\t\tOrdenes_Entrega_v.Retiro_Mercaderia_No,\n" +
                                "\t\t\tOrdenes_Entrega_v.Deposito,\n" +
                                "\t\t\tOrdenes_Entrega_v.Correlativo\n" +
                                "\t\tORDER BY\n" +
                                "\t\t\tOrdenes_Entrega_v.Retiro_Mercaderia_No ASC,\n" +
                                "\t\t\tOrdenes_Entrega_v.Deposito ASC,\n" +
                                "\t\t\tOrdenes_Entrega_v.Correlativo ASC\n" +
                                "\t) AS T\n" +
                                "GROUP BY\n" +
                                "\tT.Retiro_Mercaderia_No,\n" +
                                "\tT.Deposito";
 Query q = em.createNativeQuery(sql);
                q.setParameter(1, orden);
                q.setParameter(2, cliente);
                List<Object[]> detalles = q.getResultList();
                return detalles;
        }

// agrego luis mario
	public List<Object[]> getSubtotalesOrdenEntregaP(Integer orden, String cliente){
                String sql = "SELECT \n" +
                                "Ordenes_Entrega_v.Retiro_Mercaderia_No,\n" +
                                "Ordenes_Entrega_v.Deposito,\n" +
                                "Ordenes_Entrega_v.Correlativo,\n" +
                                "CAST(Max(Ordenes_Entrega_v.Cantidad) AS SIGNED),\n" +
                                "CAST(Sum(Ordenes_Entrega_v.Cantidad_preparada) AS SIGNED),\n" +
                                "CAST(Sum(Ordenes_Entrega_v.Saldo) AS SIGNED),\n" +
                                "CAST(Sum(Ordenes_Entrega_v.entregada) AS SIGNED)\n" +
                                "FROM \n" +
                                "Ordenes_Entrega_v\n" +
                                "WHERE \n" +
                                "Ordenes_Entrega_v.Retiro_Mercaderia_No=?\n" +
                                "and Ordenes_Entrega_v.Consignatario=?\n" +
                                "GROUP BY \n" +
                                "Ordenes_Entrega_v.Retiro_Mercaderia_No,\n" +
                                "Ordenes_Entrega_v.Deposito,\n" +
                                "Ordenes_Entrega_v.Correlativo";
                Query q = em.createNativeQuery(sql);
                q.setParameter(1, orden);
                q.setParameter(2, cliente);
                List<Object[]> detalles = q.getResultList();
                return detalles;
        }

	public List<Object[]> getTotalesOrdenReingreso(Integer orden, String cliente){
		String sql= " SELECT "
				+ " 	orden, "
				+ " 	Deposito, "
				+ " 	sum(Cantidad), "
				+ " 	sum(cantidad_a_entregar), "
				+ " 	sum(cantidad_entregada), "
				+ " 	sum(Pendiente) "
				+ " FROM( "
				+ " 	SELECT "
				+ " 		orden, "
				+ " 		Deposito, "
				+ " 		sum(Cantidad) Cantidad, "
				+ " 		sum(cantidad_a_entregar) cantidad_a_entregar, "
				+ " 		sum(cantidad_entregada) cantidad_entregada, "
				+ " 		sum(Pendiente) Pendiente "
				+ " 	FROM "
				+ " 		Merc_Pend_Preparar_det_v "
				+ "		WHERE "
				+ "			orden =? and Consignatario=?  "
				+ " 	GROUP BY "
				+ " 		orden, "
				+ " 		Deposito "
				+ " ) TMP"
				+ " GROUP BY "
				+ " 	orden, "
				+ " 	Deposito ";
		Query q = em.createNativeQuery(sql);
		q.setParameter(1, orden);
		q.setParameter(2, cliente);
		List<Object[]> detalles = q.getResultList();
		return detalles;
	}
	
    public Integer funCantidad(String bulk, String producto, String cliente, String cantidad, String orden,String nlote,String fechavto){
        String sqlString = "CALL prc_cantidad(:bulk, :producto, :cliente, :cantidad, :orden,:nlote,:fechavto);";

        Query q = em.createNativeQuery(sqlString);
        return  q.setParameter("bulk", bulk)
                 .setParameter("producto", producto)
                 .setParameter("cliente",cliente)
                 .setParameter("cantidad", cantidad)
                 .setParameter("orden", orden)
                 .setParameter("nlote", nlote)
                 .setParameter("fechavto", fechavto).executeUpdate();

    }

	@Override
	public void PRC_Actualiza_MR6(String deposito, String fecha) {

		String sqlString = "CALL PRC_Actualiza_MR6(:deposito , :fecha) ;";
		Query q = em.createNativeQuery(sqlString);
		
		q.setParameter("deposito", deposito)
			.setParameter("fecha", fecha)
			.executeUpdate();
		
	}

	public List<Impresor> getImpresores(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Impresor> criteria = cb.createQuery(Impresor.class);
		Root<Impresor> user = criteria.from(Impresor.class);
		criteria.orderBy(cb.asc(user.get("nombre")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	public Parametros getParametrosWMS(){
		return em.find(Parametros.class, "WMS");
	}

	public List<CantidadBulkUbicados> getCantidadBulkUbicados(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CantidadBulkUbicados> criteria = cb.createQuery(CantidadBulkUbicados.class);
		Root<CantidadBulkUbicados> user = criteria.from(CantidadBulkUbicados.class);
		criteria.orderBy(cb.asc(user.get("deposito")));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	public CantidadBulkUbicados getCantidadBulkUbicados(String deposito){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CantidadBulkUbicados> criteria = cb.createQuery(CantidadBulkUbicados.class);
		Root<CantidadBulkUbicados> user = criteria.from(CantidadBulkUbicados.class);
		criteria.where(cb.equal(user.get("deposito"), deposito));
		criteria.select(user);
		return em.createQuery(criteria).getSingleResult();
	}

	public List<BulkCreados> getBulkCreados(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BulkCreados> criteria = cb.createQuery(BulkCreados.class);
		Root<BulkCreados> user = criteria.from(BulkCreados.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	public List<BulkCreados> getBulkCreados(String deposito){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BulkCreados> criteria = cb.createQuery(BulkCreados.class);
		Root<BulkCreados> user = criteria.from(BulkCreados.class);
		criteria.where(cb.equal(user.get("deposito"), deposito));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	public List<BulkCreadosRes> getBulkCreadosRes(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BulkCreadosRes> criteria = cb.createQuery(BulkCreadosRes.class);
		Root<BulkCreadosRes> user = criteria.from(BulkCreadosRes.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	public List<BulkCreadosRes> getBulkCreadosRes(String deposito){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BulkCreadosRes> criteria = cb.createQuery(BulkCreadosRes.class);
		Root<BulkCreadosRes> user = criteria.from(BulkCreadosRes.class);
		criteria.where(cb.equal(user.get("deposito"), deposito));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	public List<AreaPisoBodega> getAreaPiso(String bodega){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AreaPisoBodega> criteria = cb.createQuery(AreaPisoBodega.class);
		Root<AreaPisoBodega> user = criteria.from(AreaPisoBodega.class);
		criteria.where(cb.equal(user.get("codigoBodega"), bodega));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	public void updateAreaPiso(AreaPisoBodega apb){

		String sqlString = "" +
				"UPDATE Area_piso_bodega set " +
				"		largo=:largo, ancho=:ancho, " +
				"		porcentaje_ocupacion=:ocupacion, " +
				"		estado=:estado" +
				"	WHERE codigo_bodega=:bodega and codigo_posicion_piso=:piso;";

		Query q = em.createNativeQuery(sqlString);
		q.setParameter("largo", apb.getLargo())
				.setParameter("ancho", apb.getAncho())
				.setParameter("ocupacion",apb.getPorcentajeOcupacion())
				.setParameter("estado", apb.getEstado())
				.setParameter("bodega", apb.getCodigoBodega())
				.setParameter("piso", apb.getCodigoPosicionPiso()).executeUpdate();
	}

	public void saveAreaPiso(AreaPisoBodega apb){
		String sql = "INSERT INTO Area_piso_bodega(codigo_bodega, codigo_posicion_piso, largo, ancho, " +
				"porcentaje_ocupacion, estado) VALUES (:bodega, :piso, :largo, :ancho, :ocupacion, :estado);";
		Query q = em.createNativeQuery(sql);
		q.setParameter("largo", apb.getLargo())
				.setParameter("ancho", apb.getAncho())
				.setParameter("ocupacion",apb.getPorcentajeOcupacion())
				.setParameter("estado", apb.getEstado())
				.setParameter("bodega", apb.getCodigoBodega())
				.setParameter("piso", apb.getCodigoPosicionPiso()).executeUpdate();
	}
// revisar Luis mario
	public List<Object[]> getSubtotalesBulkCreados(String deposito){
		String sql = "select " +
				"codigo_producto, CAST(ROUND(max(ancho),2) as char), CAST(ROUND(max(alto),2) as char), CAST(ROUND(max(profundidad),2) as char), " +
				"CAST(ROUND(max(area),2) as char), CAST(ROUND(max(volumen),2) as char), CAST(ROUND(max(peso),2) as char), CAST(ROUND(sum(cantidad),0) as char)" +
				"from bulk_creados_v where deposito=:deposito " +
				"group by codigo_producto";
		Query q = em.createNativeQuery(sql);
		q.setParameter("deposito", deposito);
		List<Object[]> detalles = q.getResultList();
		return detalles;
	}	
	
	public List<Object[]> getTotalesBulkCreados(String deposito){
		String sql = "select deposito, CAST(ROUND(sum(ancho),2) as char), CAST(ROUND(sum(alto),2) as char), " +
		"CAST(ROUND(sum(profundidad),2) as char), CAST(ROUND(sum(ancho*profundidad),2) as char), " +
		"CAST(ROUND(sum(ancho*profundidad*alto),2) as char), CAST(ROUND(sum(peso),2) as char) " +
		"from Bulk_v where deposito=:deposito " + 
		" group by deposito;";
		Query q = em.createNativeQuery(sql);
		q.setParameter("deposito", deposito);
		List<Object[]> totales = q.getResultList();
		return totales;
	}
		
	public void prcInsertaDetBulk(String codigoBulk, String codProducto, String cliente, String cantidad, String estado, String item,String nlote,String fechavto,String fechavtoConf){
	String sqlString = "call PRC_Inserta_DetBulk (:codigoBulk, :codProducto, :cliente, :cantidad, :estado, :item,:nlote,:fechavto,:fechavtoConf) ;";
	Query q = em.createNativeQuery(sqlString);
	
	q.setParameter("codigoBulk", codigoBulk)
		.setParameter("codProducto", codProducto)
		.setParameter("cliente", cliente)
		.setParameter("cantidad", cantidad)
		.setParameter("estado", estado)
		.setParameter("item", item)
		.setParameter("nlote", nlote)
		.setParameter("fechavto", fechavto)
		.setParameter("fechavtoConf", fechavtoConf)
		.executeUpdate();
}
	
	public void actualizarSobrante(String deposito, String cliente, String codigo, String estado, String item,String nlote,String fechavto){

		String sqlString = "call PRC_Actualiza_Posee_Sobrante (:deposito, :cliente, :codigo, :estado, :item) ;";
		Query q = em.createNativeQuery(sqlString);
		
		q.setParameter("deposito", deposito)
			.setParameter("cliente", cliente)
			.setParameter("codigo", codigo)
			.setParameter("estado", estado)
			.setParameter("item", item)
			.executeUpdate();
	}

	public void prc_entrega(String orden, String salida, String producto, String cantidad,
								 String estado, String deposito, String cliente, String nlote){
		String sqlString = "call PRC_Entrega(:orden, :salida, :producto, :cantidad, :estado, :deposito, :cliente,:nlote);";
		Query q = em.createNativeQuery(sqlString);
		q.setParameter("orden", orden)
				.setParameter("salida", salida)
				.setParameter("producto", producto)
				.setParameter("cantidad", cantidad)
				.setParameter("estado", estado)
				.setParameter("deposito", deposito)
				.setParameter("cliente", cliente)
				.setParameter("nlote", nlote)
				.executeUpdate();
	}
	
	public void prc_entrega_merc(String orden, String salida, String cliente, String fecha,
								 String cargado1, String forma, String entregado, String fini,
								 String hini, String ffin, String hfin, String autoret,
								 String observaciones){
		String sqlString = "call PRC_Entrega_Merc(:orden, :salida, :cliente, :fecha, :cargado2, " +
				":forma, :entregado, :fini, :hini, :ffin, :hfin, :autoret, :observaciones);";
		Query q = em.createNativeQuery(sqlString);
		     q.setParameter("orden", orden)
				.setParameter("salida", salida)
				.setParameter("cliente", cliente)
				.setParameter("fecha", fecha)
				.setParameter("cargado2", cargado1)
				.setParameter("forma", forma)
				.setParameter("entregado", entregado)
				.setParameter("fini", fini)
				.setParameter("hini", hini)
				.setParameter("ffin", ffin)
				.setParameter("hfin", hfin)
				.setParameter("autoret", autoret)
				.setParameter("observaciones", observaciones)
				.executeUpdate();  
	}
	
	public List<Object[]> fun_reagrupacion(String bulk, String producto, String cantidad, String bulkd, String usr,String nlote,String fechavto){
		String sqlString = "Select fun_Reagrupacion(:bulk, :producto, :cantidad, :bulkd, :usr, :nlote, :fechavto);";
		Query q = em.createNativeQuery(sqlString);
		       q.setParameter("bulk", bulk)
				.setParameter("producto", producto)
				.setParameter("cantidad", cantidad)
				.setParameter("bulkd", bulkd)
				.setParameter("usr", usr)
				.setParameter("nlote", nlote)
				.setParameter("fechavto", fechavto);
		  List<Object[]> rs = q.getResultList();
		return rs;
	}
	
	
	public String verificacionInforme(String deposito) {
		String sqlString="SELECT verificacionImforme(:deposito)";	
		Query query = em.createNativeQuery(sqlString);
		query.setParameter("deposito", deposito);
		String result = (String) query.getSingleResult();
		return result;
	}
	public String verificacionInformeR(String orden) {
		String sqlString="SELECT verificacionInformeR(:orden)";	
		Query query = em.createNativeQuery(sqlString);
		query.setParameter("orden", orden);
		String result = (String) query.getSingleResult();
		return result;
	}
	public String VerificaFechaLote(String nlote,String fechavto){
			String sqlString="SELECT VerificaFechaLote(:nlote,:fechavto)";	
			Query query = em.createNativeQuery(sqlString);
			query.setParameter("nlote", nlote);
			query.setParameter("fechavto", fechavto);
			String result = (String) query.getSingleResult();
			return result;
		
		
	}	
}

