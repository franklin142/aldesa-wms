package org.aldesa.wms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="PERMISOS_USUARIOS")
public class PermisosUsuarios {
	@Id
	@Column(name="PERMISO_ID")
	private BigDecimal permiso;

	@Column(name="USUARIO_ID", length=40)
	private String usuario;

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
