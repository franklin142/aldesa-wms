package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the Informe_Retiro_v database table.
 *
 */
@Entity
@Table(name="Informe_Retiro_v")
@NamedQuery(name="InformeRetiro.findAll", query="SELECT i FROM InformeRetiro i")
public class InformeRetiro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Bodega")
	private String bodega;

	@Column(name="Cargado_Por")
	private String cargado_Por;

	@Column(name="Cliente")
	private String cliente;

	@Column(name="Deposito_No")
	private String deposito_No;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha")
	private Date fecha;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_fin_descarga")
	private Date fecha_fin_descarga;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Inicio_descarga")
	private Date fecha_Inicio_descarga;

	@Column(name="Hora_de_carga")
	private Time hora_de_carga;

	@Column(name="Hora_fin_descarga")
	private Time hora_fin_descarga;

	@Column(name="Licencia_No")
	private String licencia_No;

	@Column(name="Marchamo_No")
	private String marchamo_No;
	
	@Column(name="declaracion")
	private String declaracion;

	@Column(name="Motorista")
	private String motorista;

	@Column(name="Nombre")
	private String nombre;

	@Column(name="Observaciones")
	private String observaciones;

	@Column(name="Observaciones1")
	private String observaciones1;

	@Column(name="Observacionesr")
	private String observacionesr;

	@Column(name="carta")
	private String carta;
	
	@Column(name="codcia")
	private int codcia;

	@Column(name="nombrecia")
	private String nombrecia;
	
	@Column(name="muelle")
	private String muelle;
	
	@Column(name="consolidador")
	private String consolidador;

	@Column(name="factura")
	private String factura;
	
	// @Column(name="Persona_autorizada_retirar")
	@Transient
	private String personaAutorizadaRetirar;

	@Id
	@Column(name="Retiro_Mercaderia_No")
	private int retiroMercaderiaNo;

	@Column(name="Salida_No")
	private int salidaNo;

	@Column(name="Total_solicitado")
	private int totalSolicitado;

	@Column(name="Total_entregado")
	private int totalEntregado;

	@Column(name="Per_auto_retirar")
	private String autorizadaRetirar;

	public String getRetirar() {
		return autorizadaRetirar;
	}

	public void setRetirar(String retirar) {
		this.autorizadaRetirar = retirar;
	}
	
	@Column(name="Entregado_por")
	private String entregado;

	public String getEntregado() {
		return entregado;
	}

	public void setEntregado(String entrega) {
		this.entregado= entrega;
	}

	@Column(name="Placa_No")
	private String placa;

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Column(name="tarimas_entregadas")
	private double tarimasEntregadas;

	@Column(name="tarimas_flejadas")
	private BigInteger tarimasFlejadas;

	private String tipo;

	@Column(name = "tipodeposito")
	private String tipoDeposito;

	public String getTipoDeposito() {
		return tipoDeposito;
	}

	public void setTipoDeposito(String tipoDeposito) {
		this.tipoDeposito = tipoDeposito;
	}

	public InformeRetiro() {
	}

	public String getBodega() {
		return this.bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public String getCargado_Por() {
		return this.cargado_Por;
	}

	public void setCargado_Por(String cargado_Por) {
		this.cargado_Por = cargado_Por;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDeposito_No() {
		return this.deposito_No;
	}

	public void setDeposito_No(String deposito_No) {
		this.deposito_No = deposito_No;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public String getFechaString() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyy");
		return sdf1.format(this.fecha);
	}

	public String getFechaDescargaString(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyy");
		return sdf1.format(this.fecha_fin_descarga);
	}

	public String getHoraInicioString(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		return sdf1.format(this.hora_de_carga);
	}

	public String getHoraFinString(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		return sdf1.format(this.hora_fin_descarga);
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha_fin_descarga() {
		return this.fecha_fin_descarga;
	}

	public void setFecha_fin_descarga(Date fecha_fin_descarga) {
		this.fecha_fin_descarga = fecha_fin_descarga;
	}

	public Date getFecha_Inicio_descarga() {
		return this.fecha_Inicio_descarga;
	}

	public void setFecha_Inicio_descarga(Date fecha_Inicio_descarga) {
		this.fecha_Inicio_descarga = fecha_Inicio_descarga;
	}

	public Time getHora_de_carga() {
		return this.hora_de_carga;
	}

	public void setHora_de_carga(Time hora_de_carga) {
		this.hora_de_carga = hora_de_carga;
	}

	public Time getHora_fin_descarga() {
		return this.hora_fin_descarga;
	}

	public void setHora_fin_descarga(Time hora_fin_descarga) {
		this.hora_fin_descarga = hora_fin_descarga;
	}

	public String getLicencia_No() {
		return this.licencia_No;
	}

	public void setLicencia_No(String licencia_No) {
		this.licencia_No = licencia_No;
	}

	public String getMarchamo_No() {
		return this.marchamo_No;
	}

	public void setMarchamo_No(String marchamo_No) {
		this.marchamo_No = marchamo_No;
	}

	public String getMotorista() {
		return this.motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservaciones1() {
		return this.observaciones1;
	}

	public void setObservaciones1(String observaciones1) {
		this.observaciones1 = observaciones1;
	}

	public String getObservacionesr() {
		return this.observacionesr;
	}

	public void setObservacionesr(String observacionesr) {
		this.observacionesr = observacionesr;
	}

	public int getCodcia() {
		return this.codcia;
	}
	 
	public void setCodcia(int codcia) {
		this.codcia = codcia;
	}

	public String getNombrecia() {
		return this.nombrecia;
	}

	public void setNombrecia(String nombrecia) {
		this.nombrecia = nombrecia;
	}

	public String getMuelle() {
		return this.muelle;
	}

	public void setMuelle(String muelle) {
		this.muelle = muelle;
	}

	public String getDeclaracion() {
		return this.declaracion;
	}

	public void setDeclaracion(String declaracion) {
		this.declaracion = declaracion;
	}

	public String getCarta() {
		return this.carta;
	}

	public void setCarta(String carta) {
		this.carta = carta;
	}

	public String getFactura() {
		return this.factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getConsolidador() {
		return this.consolidador;
	}

	public void setConsolidador(String consolidador) {
		this.consolidador = consolidador;
	}

	public String getPersonaAutorizadaRetirar() {
		return this.personaAutorizadaRetirar;
	}

	public void setPersonaAutorizadaRetirar(String personaAutorizadaRetirar) {
		this.personaAutorizadaRetirar = personaAutorizadaRetirar;
	}

	public int getRetiroMercaderiaNo() {
		return this.retiroMercaderiaNo;
	}

	public void setRetiroMercaderiaNo(int retiroMercaderiaNo) {
		this.retiroMercaderiaNo = retiroMercaderiaNo;
	}

	public int getSalidaNo() {
		return this.salidaNo;
	}

	public void setSalidaNo(int salidaNo) {
		this.salidaNo = salidaNo;
	}

	public int getTotSolic() {
		return this.totalSolicitado;
	}

	public void setTotSolic(int totalSolicitado) {
		this.totalSolicitado = totalSolicitado;
	}

	public int getTotEntreg() {
		return this.totalEntregado;
	}

	public void setTotEntreg(int totalEntregado) {
		this.totalEntregado = totalEntregado;
	}

	public double getTarimasEntregadas() {
		return this.tarimasEntregadas;
	}

	public void setTarimasEntregadas(double tarimasEntregadas) {
		this.tarimasEntregadas = tarimasEntregadas;
	}

	public BigInteger getTarimasFlejadas() {
		return this.tarimasFlejadas;
	}

	public void setTarimasFlejadas(BigInteger tarimasFlejadas) {
		this.tarimasFlejadas = tarimasFlejadas;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}