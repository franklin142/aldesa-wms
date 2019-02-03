package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Orden_retiro_v database table.
 * 
 */
@Entity
@Table(name="Orden_retiro_v")
@NamedQuery(name="Orden_retiro_v.findAll", query="SELECT o FROM OrdenRetiro o")
public class OrdenRetiro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Cliente", nullable=false, length=15)
	private String cliente;

	@Column(name="Deposito_No", nullable=false, length=25)
	private String deposito_No;

	@Id
	@Column(name="Retiro_Mercaderia_No", nullable=false)
	private int retiro_Mercaderia_No;

	@Column(nullable=false, length=1)
	private String tipo;

	public OrdenRetiro() {
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

	public int getRetiro_Mercaderia_No() {
		return this.retiro_Mercaderia_No;
	}

	public void setRetiro_Mercaderia_No(int retiro_Mercaderia_No) {
		this.retiro_Mercaderia_No = retiro_Mercaderia_No;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}