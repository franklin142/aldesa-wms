package org.aldesa.wms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the detalle_bulk database table.
 * 
 */
@Entity
@Table(name="detalle_bulk_v")
public class DetalleBulkV implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleBulkPK id;

	@Column(name="cantidad")
	private double cantidad;

	@Column(name="cantidad_original")
	private double cantidad_original;

	@Column(length=10)
	private String cliente;

	@Column(name="estado_merc", insertable=false, updatable=false )
	private String estadoMerc;

	private String estado;

	@Column(name="unidad_medida")
	private String unidadMedida;
	
	@Column(name="No_Lote")
	private String nLote;
	
	@Column(name="Fec_Venc_Lote")
	private Date fechaVto;

	@Column(name="item")
	private String item;
	
	public DetalleBulkV() {
	}

	public DetalleBulkPK getId() {
		return this.id;
	}

	public void setId(DetalleBulkPK id) {
		this.id = id;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getCantidad_original() {
		return this.cantidad_original;
	}

	public void setCantidad_original(double cantidad_original) {
		this.cantidad_original = cantidad_original;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
		
	public String getEstadoMerc() {
		return this.estadoMerc;
	}

	public void setEstadoMerc(String estadoMerc) {
		this.estado = estadoMerc;
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

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
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