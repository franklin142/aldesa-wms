package org.aldesa.wms.data;

import java.awt.geom.Area;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.aldesa.wms.model.*;

public interface BulkDao {
	public Bulk getById(String pk);
	public BulkV getById_v(String pk);
	public BulkR getByIdr_v(String pk);
	public void prc_actualiza_orden(String orden);
	public List<Bulk> getAll();
	public List<DetalleBulk> getDetalles();
	public List<DetalleBulk> getDetalleByBulk(String bulk);
	public List<DetalleBulkV> getDetalles2();
	public List<DetalleBulkV> getDetalleByBulk2(String bulk);
	public Bulk save(Bulk dmr);
    public List<BulkSugerido> getSugerencia(String deposito);
	public DetalleBulk insert(DetalleBulk dmr);
	public Bulk byBulk(String bulk);
	public UbicacionBulk byPosicion(String posicion);
	public List<Bulk> bulkByDeposito(String deposito);
	public PosicionDisponibleV getPosicionDispById(String bulk);
	public Patio saveEstadoPatio(Patio a);
	public List<AreaPisoBodega> getAreaPiso(String bodega);
	public void updateAreaPiso(AreaPisoBodega apb);
	public void saveAreaPiso(AreaPisoBodega apb);
	public PosicionEstante savePosEstante(PosicionEstante a);
	public PosisionPasillo savePosPasillo(PosisionPasillo a);
	public OrdenRetiro getOrdenRetiroById(int pk);
	public Cliente getClienteById(String pk);
	public DetalleBulk updateDetalle(DetalleBulk db);
	public void updateDetalle(String bulk, String producto, double cantidad, String estado);
	public List<String> reingresoDetalleBulk(String bulk, String retiro, String posicion);
	public List<String> validaEntrega(String retiro, String cliente);
	public List<MercPendPreparar> despachoGetAllPendientes();
	public List<MercPendDespachar> despachoGetAllPendientesDespachar();
	public List<DetalleMercPendPreparar> despachoGetAllDetallePendientes(int orden, String cliente);
	public List<DetalleMercPendPreparar> despachoGetAllDetallePendientesP(int orden, String cliente);
	public int autorizarDespachoOrden(int orden, String tipo);
	public int autorizarEntrega();
    public int autorizarEntrega(Integer orden, Date fecha, String cliente);
	public void updateMieDesc(String deposito, String dan, Date date, String capacidad, String descargado, Date date2,
			Date date3);
	public void FinalizaRecepcion(String deposito, String tipo);
	public void autorizarUbicacion(String forma, String entregado, String recibido, String observacion, String deposito, String tipo, String cliente, String item);
	public MercRecibidaCompleta getMercaderiaCompletaByDeposito(String deposito);
	public void ActualizaMR1(String deposito, String dan, String frecepcion, String capacidad, String descargado, String fmarchamo, String hmarchamo, String tipo, String bodega, String muelle, String fftransito, String hftransito) throws ParseException;
	public void verificaBulk(String deposito);
	public InformeIngreso getInformeIngresoById(String noDeposito);
	public List<InformeIngreso> getInformeIngreso(String noDeposito);
	public InformeRetiro getInformeById(String noRetiro);
	public List<InformeRetiro> getInformeRetiroList(String noRetiro, String cliente);
	public InformeRetiro getInformeById(String noRetiro, String cliente);
	public List<DetalleInformeRetiro> getDetalleRetiro(String retiro);
	public List<DetalleInformeRetiro> getDetalleRetiro(String retiro, String cliente);
    public List<BulkActivos> getBulkActivos();
    public List<BulkActivos> getBulkActivos(String deposito);
    public List<MercRecibida> getMercRecibida();
    public List<MercRecibida> getMercRecibida(String deposito);
    public void updateFechaFinDescarga(MercadRecibida mr);
    public void updateFechaFinDescargaD(MercadRecibida mr);
    public List<OrdenEntrega> getOrdenEntrega(Integer orden, String cliente);
	public List<OrdenEntrega> getOrdenEntregaP(Integer orden, String cliente);
	public List<Object[]> getSubtotalesOrdenEntrega(Integer orden, String cliente);
	public List<Object[]> getSubtotalesOrdenEntregaP(Integer orden, String cliente);
	public List<Object[]> getTotalesOrdenEntrega(Integer orden, String cliente);
	public List<Object[]> getTotalesOrdenEntregaP(Integer orden, String cliente);
	public List<Impresor> getImpresores();
    public Integer funCantidad(String bulk, String producto, String cliente, String cantidad, String orden,String nlote,Date fechavto);
    public void PRC_Actualiza_MR6(String deposito, String fecha);
	public Parametros getParametrosWMS();
	public List<CantidadBulkUbicados> getCantidadBulkUbicados();
	public CantidadBulkUbicados getCantidadBulkUbicados(String deposito);
	public List<BulkCreados> getBulkCreados();
	public List<BulkCreados> getBulkCreados(String deposito);
	public List<BulkCreadosRes> getBulkCreadosRes();
	public List<BulkCreadosRes> getBulkCreadosRes(String deposito);
	public List<Object[]> getSubtotalesBulkCreados(String deposito);
	public List<Object[]> getTotalesBulkCreados(String deposito);
	public void finalizaRecepcion(String deposito, String tipo);
	public void prcInsertaDetBulk(String codigoBulk, String codProducto, String cliente, String cantidad, String estado, String item,String nlote,Date fechavto);
	public void actualizarSobrante(String deposito, String cliente, String codigo, String estado, String item,String nlote,Date fechavto);
	public void prc_entrega(String orden, String salida, String producto, String cantidad,
								 String estado, String deposito, String cliente,String nlote,Date fechavto);
	public void prc_entrega_merc(String orden, String salida, String cliente, String fecha,
								 String cargado1, String forma, String entregado, String fini,
								 String hini, String ffin, String hfin, String autoret,
								 String observaciones);
	public List<DetalleBulkRV> getDetalles3();
	public List<DetalleBulkRV> getDetalles3(String orden);
	public List<OrdenReingreso> getOrdenReingreso();
	public List<OrdenReingreso> getOrdenReingreso(String orden);
	public List<Object[]> getSubtotalesOrdenReingreso(Integer orden, String cliente);
	public List<Object[]> getTotalesOrdenReingreso(Integer orden, String cliente);
	public List<Object[]> fun_reagrupacion(String bulk, String producto, String cantidad, String bulkd, String usr,String nlote,Date fechavto);
	public String verificacionInforme(String deposito);
}
