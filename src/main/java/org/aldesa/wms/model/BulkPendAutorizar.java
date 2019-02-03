package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the Bulk_pend_Autorizar_v database table.
 * 
 */
@Entity
@Table(name="Bulk_pend_Autorizar_v")
@NamedQuery(name="BulkPendAutorizar.findAll", query="SELECT b FROM BulkPendAutorizar b")
public class BulkPendAutorizar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Cantidad", nullable=false)
	private BigInteger cantidad;

	@Column(length=10)
	private String cliente;
	
	@Id
	@Column(length=30)
	private String deposito;

	@Column(name="Nombre", nullable=false, length=50)
	private String nombre;

	@Column(name="Bodega", nullable=false, length=50)
	private String bodega;
	 
	
	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public BigInteger getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigInteger cantidad) {
		this.cantidad = cantidad;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDeposito() {
		return this.deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}