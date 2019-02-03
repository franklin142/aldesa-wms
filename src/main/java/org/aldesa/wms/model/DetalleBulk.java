package org.aldesa.wms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the detalle_bulk database table.
 * 
 */
@Entity
@Table(name="detalle_bulk")
@NamedQuery(name="DetalleBulk.findAll", query="SELECT d FROM DetalleBulk d")
public class DetalleBulk implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleBulkPK id;

	@Column(name="cantidad")
	private Double cantidad;

	@Column(name="cantidad_original")
	private Double cantidad_original;

	@Column(length=10)
	private String cliente;

	private String estado;

	@Column(name="unidad_medida", length=45)
	private String unidadMedida;
	
	@Column(name="No_Lote")
	private String nLote;
	
	@Column(name="Fec_Venc_Lote")
	private Date fechaVto;

	public DetalleBulk() {
	}

	public DetalleBulkPK getId() {
		return this.id;
	}

	public void setId(DetalleBulkPK id) {
		this.id = id;
	}

	public Double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getCantidad_original() {
		return this.cantidad_original;
	}

	public void setCantidad_original(Double cantidad_original) {
		this.cantidad_original = cantidad_original;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getnLote() {
		return nLote;
	}

	public void setnLote(String nLote) {
		this.nLote = nLote;
	}

	public Date getFechaVto() {
		return fechaVto;
	}

	public void setFechaVto(Date fechaVto) {
		this.fechaVto = fechaVto;
	}

}