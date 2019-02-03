package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the MIEDESC_v database table.
 * 
 */
@Entity
@Table(name="MIEDESC_v")
@NamedQuery(name="MIEDESC_v.findAll", query="SELECT m FROM MIEDESC_v m")
public class MIEDESC_v implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Deposito_No", nullable=false, length=25)
	private String deposito_No;

	
	@Column(name="Agente_Aduanero", nullable=false, length=40)
	private String agente_Aduanero;

	@Column(nullable=false, length=200)
	private String BL_Manifiesto_de_Carga_BL;

	@Column(name="Capacidad", nullable=false)
	private double capacidad;

	@Column(name="Cia_de_Transporte", nullable=false)
	private short cia_de_Transporte;

	@Column(name="Clase_de_Vehiculo", nullable=false, length=15)
	private String clase_de_Vehiculo;

	@Column(name="Codigo_Transporte", nullable=false, length=20)
	private String codigo_Transporte;

	@Column(name="Comision_Cheque", nullable=false, length=20)
	private String comision_Cheque;

	@Column(name="Consolidador", nullable=false)
	private short consolidador;

	@Column(name="Contenedores_por_Deposito", nullable=false)
	private short contenedores_por_Deposito;

	@Column(name="Descargado_por", nullable=false)
	private byte descargado_por;

	@Column(name="DMTI", nullable=false, length=15)
	private String dmti;

	@Column(name="DTI", nullable=false, length=15)
	private String dti;

	@Column(name="Entrega_No", nullable=false)
	private int entrega_No;

	@Column(name="Entregado_por", nullable=false, length=40)
	private String entregado_por;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha", nullable=false)
	private Date fecha;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Creado", nullable=false)
	private Date fecha_Creado;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_de_Descarga_Cont", nullable=false)
	private Date fecha_de_Descarga_Cont;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_de_entrada_contenedor", nullable=false)
	private Date fecha_de_entrada_contenedor;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Emision", nullable=false)
	private Date fecha_Emision;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_fin_descarga", nullable=false)
	private Date fecha_fin_descarga;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_fin_transito", nullable=false)
	private Date fecha_fin_transito;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_modificado", nullable=false)
	private Date fecha_modificado;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Visto_Bueno", nullable=false)
	private Date fecha_Visto_Bueno;

	@Column(nullable=false)
	private Time hasta;

	@Column(name="Hora_autorizacion_corte_marcham", nullable=false)
	private Time hora_autorizacion_corte_marcham;

	@Column(name="Hora_Creado", nullable=false)
	private Time hora_Creado;

	@Column(name="Hora_de_Descarga", nullable=false)
	private Time hora_de_Descarga;

	@Column(name="Hora_descarga_desde", nullable=false)
	private Time hora_descarga_desde;

	@Column(name="hora_fin_transito", nullable=false)
	private Time horaFinTransito;

	@Column(name="Hora_modificado", nullable=false)
	private Time hora_modificado;

	@Column(name="Jefe_Produccion", nullable=false, length=40)
	private String jefe_Produccion;

	@Column(name="`Licencia_No.`", nullable=false, length=20)
	private String licencia_No_;

	@Column(name="`Marchamo_No.`", nullable=false, length=15)
	private String marchamo_No_;

	@Column(name="Modificable", nullable=false)
	private byte modificable;

	@Column(name="Motorista", nullable=false, length=50)
	private String motorista;

	@Column(name="No_de_Items", nullable=false)
	private double no_de_Items;

	@Column(name="No_Vehiculo", nullable=false, length=45)
	private String no_Vehiculo;

	@Column(name="Numero_de_Informe", nullable=false)
	private int numero_de_Informe;

	@Column(name="Observaciones", nullable=false, length=300)
	private String observaciones;

	@Column(name="Paletizada", nullable=false, length=2)
	private String paletizada;

	@Column(name="Peso_Total", nullable=false)
	private double peso_Total;

	@Column(name="Recepcion_Mercaderia_No", nullable=false)
	private int recepcion_Mercaderia_No;

	@Column(name="Recibido_por", nullable=false, length=50)
	private String recibido_por;

	@Column(name="Revisado_Bodega", nullable=false)
	private byte revisado_Bodega;

	@Column(name="Tarimas_Flejadas", nullable=false)
	private double tarimas_Flejadas;

	@Column(name="Tarimas_recibidas", nullable=false)
	private double tarimas_recibidas;

	@Column(name="Total_Mt2", nullable=false)
	private double total_Mt2;

	@Column(name="Total_Mt3", nullable=false)
	private double total_Mt3;

	@Column(name="Total_Unidades", nullable=false)
	private double total_Unidades;

	@Column(name="Total_Valor", nullable=false)
	private double total_Valor;

	@Column(name="Usuario_Creador", nullable=false, length=10)
	private String usuario_Creador;

	@Column(name="Usuario_Modificador", nullable=false, length=10)
	private String usuario_Modificador;

	@Column(name="Utilizo_DAN", nullable=false)
	private byte utilizo_DAN;

	@Column(name="Vehiculo_Placa_No", nullable=false, length=15)
	private String vehiculo_Placa_No;

	@Column(name="Visto_Bueno", nullable=false)
	private byte visto_Bueno;

	public MIEDESC_v() {
	}

	public String getAgente_Aduanero() {
		return this.agente_Aduanero;
	}

	public void setAgente_Aduanero(String agente_Aduanero) {
		this.agente_Aduanero = agente_Aduanero;
	}

	public String getBL_Manifiesto_de_Carga_BL() {
		return this.BL_Manifiesto_de_Carga_BL;
	}

	public void setBL_Manifiesto_de_Carga_BL(String BL_Manifiesto_de_Carga_BL) {
		this.BL_Manifiesto_de_Carga_BL = BL_Manifiesto_de_Carga_BL;
	}

	public double getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public short getCia_de_Transporte() {
		return this.cia_de_Transporte;
	}

	public void setCia_de_Transporte(short cia_de_Transporte) {
		this.cia_de_Transporte = cia_de_Transporte;
	}

	public String getClase_de_Vehiculo() {
		return this.clase_de_Vehiculo;
	}

	public void setClase_de_Vehiculo(String clase_de_Vehiculo) {
		this.clase_de_Vehiculo = clase_de_Vehiculo;
	}

	public String getCodigo_Transporte() {
		return this.codigo_Transporte;
	}

	public void setCodigo_Transporte(String codigo_Transporte) {
		this.codigo_Transporte = codigo_Transporte;
	}

	public String getComision_Cheque() {
		return this.comision_Cheque;
	}

	public void setComision_Cheque(String comision_Cheque) {
		this.comision_Cheque = comision_Cheque;
	}

	public short getConsolidador() {
		return this.consolidador;
	}

	public void setConsolidador(short consolidador) {
		this.consolidador = consolidador;
	}

	public short getContenedores_por_Deposito() {
		return this.contenedores_por_Deposito;
	}

	public void setContenedores_por_Deposito(short contenedores_por_Deposito) {
		this.contenedores_por_Deposito = contenedores_por_Deposito;
	}

	public String getDeposito_No() {
		return this.deposito_No;
	}

	public void setDeposito_No(String deposito_No) {
		this.deposito_No = deposito_No;
	}

	public byte getDescargado_por() {
		return this.descargado_por;
	}

	public void setDescargado_por(byte descargado_por) {
		this.descargado_por = descargado_por;
	}

	public String getDmti() {
		return this.dmti;
	}

	public void setDmti(String dmti) {
		this.dmti = dmti;
	}

	public String getDti() {
		return this.dti;
	}

	public void setDti(String dti) {
		this.dti = dti;
	}

	public int getEntrega_No() {
		return this.entrega_No;
	}

	public void setEntrega_No(int entrega_No) {
		this.entrega_No = entrega_No;
	}

	public String getEntregado_por() {
		return this.entregado_por;
	}

	public void setEntregado_por(String entregado_por) {
		this.entregado_por = entregado_por;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha_Creado() {
		return this.fecha_Creado;
	}

	public void setFecha_Creado(Date fecha_Creado) {
		this.fecha_Creado = fecha_Creado;
	}

	public Date getFecha_de_Descarga_Cont() {
		return this.fecha_de_Descarga_Cont;
	}

	public void setFecha_de_Descarga_Cont(Date fecha_de_Descarga_Cont) {
		this.fecha_de_Descarga_Cont = fecha_de_Descarga_Cont;
	}

	public Date getFecha_de_entrada_contenedor() {
		return this.fecha_de_entrada_contenedor;
	}

	public void setFecha_de_entrada_contenedor(Date fecha_de_entrada_contenedor) {
		this.fecha_de_entrada_contenedor = fecha_de_entrada_contenedor;
	}

	public Date getFecha_Emision() {
		return this.fecha_Emision;
	}

	public void setFecha_Emision(Date fecha_Emision) {
		this.fecha_Emision = fecha_Emision;
	}

	public Date getFecha_fin_descarga() {
		return this.fecha_fin_descarga;
	}

	public void setFecha_fin_descarga(Date fecha_fin_descarga) {
		this.fecha_fin_descarga = fecha_fin_descarga;
	}

	public Date getFecha_fin_transito() {
		return this.fecha_fin_transito;
	}

	public void setFecha_fin_transito(Date fecha_fin_transito) {
		this.fecha_fin_transito = fecha_fin_transito;
	}

	public Date getFecha_modificado() {
		return this.fecha_modificado;
	}

	public void setFecha_modificado(Date fecha_modificado) {
		this.fecha_modificado = fecha_modificado;
	}

	public Date getFecha_Visto_Bueno() {
		return this.fecha_Visto_Bueno;
	}

	public void setFecha_Visto_Bueno(Date fecha_Visto_Bueno) {
		this.fecha_Visto_Bueno = fecha_Visto_Bueno;
	}

	public Time getHasta() {
		return this.hasta;
	}

	public void setHasta(Time hasta) {
		this.hasta = hasta;
	}

	public Time getHora_autorizacion_corte_marcham() {
		return this.hora_autorizacion_corte_marcham;
	}

	public void setHora_autorizacion_corte_marcham(Time hora_autorizacion_corte_marcham) {
		this.hora_autorizacion_corte_marcham = hora_autorizacion_corte_marcham;
	}

	public Time getHora_Creado() {
		return this.hora_Creado;
	}

	public void setHora_Creado(Time hora_Creado) {
		this.hora_Creado = hora_Creado;
	}

	public Time getHora_de_Descarga() {
		return this.hora_de_Descarga;
	}

	public void setHora_de_Descarga(Time hora_de_Descarga) {
		this.hora_de_Descarga = hora_de_Descarga;
	}

	public Time getHora_descarga_desde() {
		return this.hora_descarga_desde;
	}

	public void setHora_descarga_desde(Time hora_descarga_desde) {
		this.hora_descarga_desde = hora_descarga_desde;
	}

	public Time getHoraFinTransito() {
		return this.horaFinTransito;
	}

	public void setHoraFinTransito(Time horaFinTransito) {
		this.horaFinTransito = horaFinTransito;
	}

	public Time getHora_modificado() {
		return this.hora_modificado;
	}

	public void setHora_modificado(Time hora_modificado) {
		this.hora_modificado = hora_modificado;
	}

	public String getJefe_Produccion() {
		return this.jefe_Produccion;
	}

	public void setJefe_Produccion(String jefe_Produccion) {
		this.jefe_Produccion = jefe_Produccion;
	}

	public String getLicencia_No_() {
		return this.licencia_No_;
	}

	public void setLicencia_No_(String licencia_No_) {
		this.licencia_No_ = licencia_No_;
	}

	public String getMarchamo_No_() {
		return this.marchamo_No_;
	}

	public void setMarchamo_No_(String marchamo_No_) {
		this.marchamo_No_ = marchamo_No_;
	}

	public byte getModificable() {
		return this.modificable;
	}

	public void setModificable(byte modificable) {
		this.modificable = modificable;
	}

	public String getMotorista() {
		return this.motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}

	public double getNo_de_Items() {
		return this.no_de_Items;
	}

	public void setNo_de_Items(double no_de_Items) {
		this.no_de_Items = no_de_Items;
	}

	public String getNo_Vehiculo() {
		return this.no_Vehiculo;
	}

	public void setNo_Vehiculo(String no_Vehiculo) {
		this.no_Vehiculo = no_Vehiculo;
	}

	public int getNumero_de_Informe() {
		return this.numero_de_Informe;
	}

	public void setNumero_de_Informe(int numero_de_Informe) {
		this.numero_de_Informe = numero_de_Informe;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPaletizada() {
		return this.paletizada;
	}

	public void setPaletizada(String paletizada) {
		this.paletizada = paletizada;
	}

	public double getPeso_Total() {
		return this.peso_Total;
	}

	public void setPeso_Total(double peso_Total) {
		this.peso_Total = peso_Total;
	}

	public int getRecepcion_Mercaderia_No() {
		return this.recepcion_Mercaderia_No;
	}

	public void setRecepcion_Mercaderia_No(int recepcion_Mercaderia_No) {
		this.recepcion_Mercaderia_No = recepcion_Mercaderia_No;
	}

	public String getRecibido_por() {
		return this.recibido_por;
	}

	public void setRecibido_por(String recibido_por) {
		this.recibido_por = recibido_por;
	}

	public byte getRevisado_Bodega() {
		return this.revisado_Bodega;
	}

	public void setRevisado_Bodega(byte revisado_Bodega) {
		this.revisado_Bodega = revisado_Bodega;
	}

	public double getTarimas_Flejadas() {
		return this.tarimas_Flejadas;
	}

	public void setTarimas_Flejadas(double tarimas_Flejadas) {
		this.tarimas_Flejadas = tarimas_Flejadas;
	}

	public double getTarimas_recibidas() {
		return this.tarimas_recibidas;
	}

	public void setTarimas_recibidas(double tarimas_recibidas) {
		this.tarimas_recibidas = tarimas_recibidas;
	}

	public double getTotal_Mt2() {
		return this.total_Mt2;
	}

	public void setTotal_Mt2(double total_Mt2) {
		this.total_Mt2 = total_Mt2;
	}

	public double getTotal_Mt3() {
		return this.total_Mt3;
	}

	public void setTotal_Mt3(double total_Mt3) {
		this.total_Mt3 = total_Mt3;
	}

	public double getTotal_Unidades() {
		return this.total_Unidades;
	}

	public void setTotal_Unidades(double total_Unidades) {
		this.total_Unidades = total_Unidades;
	}

	public double getTotal_Valor() {
		return this.total_Valor;
	}

	public void setTotal_Valor(double total_Valor) {
		this.total_Valor = total_Valor;
	}

	public String getUsuario_Creador() {
		return this.usuario_Creador;
	}

	public void setUsuario_Creador(String usuario_Creador) {
		this.usuario_Creador = usuario_Creador;
	}

	public String getUsuario_Modificador() {
		return this.usuario_Modificador;
	}

	public void setUsuario_Modificador(String usuario_Modificador) {
		this.usuario_Modificador = usuario_Modificador;
	}

	public byte getUtilizo_DAN() {
		return this.utilizo_DAN;
	}

	public void setUtilizo_DAN(byte utilizo_DAN) {
		this.utilizo_DAN = utilizo_DAN;
	}

	public String getVehiculo_Placa_No() {
		return this.vehiculo_Placa_No;
	}

	public void setVehiculo_Placa_No(String vehiculo_Placa_No) {
		this.vehiculo_Placa_No = vehiculo_Placa_No;
	}

	public byte getVisto_Bueno() {
		return this.visto_Bueno;
	}

	public void setVisto_Bueno(byte visto_Bueno) {
		this.visto_Bueno = visto_Bueno;
	}

}