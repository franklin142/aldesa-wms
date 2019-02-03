package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the DET_MERC_REC database table.
 * 
 */
@Entity
@Table(name="DET_MERC_REC_v")
@NamedQuery(name="DetMercRec.findAll", query="SELECT d FROM DetMercRec d")
public class DetMercRec implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetMercRecPK id;

	@Column(name="Bodega")
	private String bodega;

	@Column(name="Cantidad")
	private double cantidad;

	@Column(name="Codigo")
	private String codigo;

	@Column(name="Empaque")
	private String empaque;

	@Column(name="Estado")
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Doc")
	private Date fecha_Doc;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_modificado")
	private Date fecha_modificado;

	@Column(name="Hora_Creado")
	private Time hora_Creado;

	@Column(name="Hora_modificado")
	private Time hora_modificado;

	@Column(name="Kg")
	private double kg;

	@Column(name="Linea_Productos")
	private String linea_Productos;

	@Column(name="M2")
	private double m2;

	@Column(name="M3")
	private double m3;

	@Column(name="Precio_Unitario")
	private double precio_Unitario;

	@Column(name="Selec_bulk")
	private Byte selec_bulk;

	@Column(name="Ubicacion")
	private String ubicacion;

	@Column(name="Unidad_de_Medida")
	private String unidad_de_Medida;

	@Column(name="Usuario_Creador")
	private String usuario_Creador;

	@Column(name="Usuario_Modificador")
	private String usuario_Modificador;

	@Temporal(TemporalType.DATE)
	@Column(name="Vencimiento")
	private Date vencimiento;

	public DetMercRec() {
	}

	public DetMercRecPK getId() {
		return this.id;
	}

	public void setId(DetMercRecPK id) {
		this.id = id;
	}

	public String getBodega() {
		return this.bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmpaque() {
		return this.empaque;
	}

	public void setEmpaque(String empaque) {
		this.empaque = empaque;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha_Doc() {
		return this.fecha_Doc;
	}

	public void setFecha_Doc(Date fecha_Doc) {
		this.fecha_Doc = fecha_Doc;
	}

	public Date getFecha_modificado() {
		return this.fecha_modificado;
	}

	public void setFecha_modificado(Date fecha_modificado) {
		this.fecha_modificado = fecha_modificado;
	}

	public Time getHora_Creado() {
		return this.hora_Creado;
	}

	public void setHora_Creado(Time hora_Creado) {
		this.hora_Creado = hora_Creado;
	}

	public Time getHora_modificado() {
		return this.hora_modificado;
	}

	public void setHora_modificado(Time hora_modificado) {
		this.hora_modificado = hora_modificado;
	}

	public double getKg() {
		return this.kg;
	}

	public void setKg(double kg) {
		this.kg = kg;
	}

	public String getLinea_Productos() {
		return this.linea_Productos;
	}

	public void setLinea_Productos(String linea_Productos) {
		this.linea_Productos = linea_Productos;
	}

	public double getM2() {
		return this.m2;
	}

	public void setM2(double m2) {
		this.m2 = m2;
	}

	public double getM3() {
		return this.m3;
	}

	public void setM3(double m3) {
		this.m3 = m3;
	}

	public double getPrecio_Unitario() {
		return this.precio_Unitario;
	}

	public void setPrecio_Unitario(double precio_Unitario) {
		this.precio_Unitario = precio_Unitario;
	}

	public Byte getSelec_bulk() {
		return this.selec_bulk;
	}

	public void setSelec_bulk(Byte selec_bulk) {
		this.selec_bulk = selec_bulk;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getUnidad_de_Medida() {
		return this.unidad_de_Medida;
	}

	public void setUnidad_de_Medida(String unidad_de_Medida) {
		this.unidad_de_Medida = unidad_de_Medida;
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

	public Date getVencimiento() {
		return this.vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

}