package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CLIENTES_v database table.
 * 
 */
@Entity
@Table(name="CLIENTES_v")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Cliente_No", nullable=false, length=10)
	private String cliente_No;


	@Column(name="NIT", nullable=false, length=20)
	private String nit;

	@Column(name="Nombre", nullable=false, length=50)
	private String nombre;


	public Cliente() {
	}


	public String getCliente_No() {
		return this.cliente_No;
	}

	public void setCliente_No(String cliente_No) {
		this.cliente_No = cliente_No;
	}


	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}