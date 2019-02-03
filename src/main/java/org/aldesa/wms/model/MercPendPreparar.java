package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the Merc_Pend_Preparar_v database table.
 * 
 */
@Entity
@Table(name="Merc_Pend_Preparar_v")
@NamedQuery(name="MercPendPreparar.findAll", query="SELECT m FROM MercPendPreparar m")
public class MercPendPreparar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Cliente", nullable=false, length=15)
	private String cliente;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha", nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private Time hora;

	@Column(name="Nombre", nullable=false, length=50)
	private String nombre;

	@Id
	@Column(nullable=false)
	private int orden;

	@Column(nullable=false, length=1)
	private String tipo;

	@Column(nullable=false, length=1)
	private String autorizado;

	@Column(name="bodega")
	private String bodega;

	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public MercPendPreparar() {
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getAutorizado() {
		return this.autorizado;
	}

	public void setAutorizado(String autorizado) {
		this.tipo = autorizado;
	}

	public String getFechaHora(){
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		return sdf2.format(this.fecha)+ " " +sdf1.format(this.hora);
	}

}