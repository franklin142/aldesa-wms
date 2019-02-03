package org.aldesa.wms.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="PERMISO")
public class Permiso {
	@Id
	@Column(name="ID")
	private BigDecimal permiso;

	@Transient
	private String usuario=null;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="DESCRIPCION")
	private String descripcion;

	public BigDecimal getPermiso() {
		return permiso;
	}

	public void setPermiso(BigDecimal permiso) {
		this.permiso = permiso;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
