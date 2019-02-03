package org.aldesa.wms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity implementation class for Entity: Deposito
 *
 */
@Entity
@Table(name="cantidad_bulk_ubicados_v")

public class CantidadBulkUbicados implements Serializable {
	@Id
	@Column(name="Deposito")
	private String deposito;
	@Column(name="cantidad_ubicada")
	private Integer cantidad;
	@Column(name="cantidad_total")
	private String total;
	@Column(name="estatus")
	private String estatus;

	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
