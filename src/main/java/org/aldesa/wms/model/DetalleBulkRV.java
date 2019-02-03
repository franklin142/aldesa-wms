package org.aldesa.wms.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the detalle_bulk database table.
 * 
 */
@Entity
@Table(name="detalle_bulkr_v")
public class DetalleBulkRV implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleBulkPK id;

	@Column(name="cantidad")
	private double cantidad;

	@Column(name="cantidad_original")
	private double cantidad_original;

	@Column(name="orden", nullable=false)
	private int orden;
	
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

	public DetalleBulkRV() {
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

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
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