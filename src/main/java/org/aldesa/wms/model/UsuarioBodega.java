package org.aldesa.wms.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class UsuarioBodega {
	private Usuario usuario;
	private BigDecimal bodega;
	private Usuario creador;
	private Date fechaCreacion;
	private Time horaCreacion;
	private Usuario modificador;
	private Date fechaModificacion;
	private Time horaModificacion;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public BigDecimal getBodega() {
		return bodega;
	}
	public void setBodega(BigDecimal bodega) {
		this.bodega = bodega;
	}
	public Usuario getCreador() {
		return creador;
	}
	public void setCreador(Usuario creador) {
		this.creador = creador;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Time getHoraCreacion() {
		return horaCreacion;
	}
	public void setHoraCreacion(Time horaCreacion) {
		this.horaCreacion = horaCreacion;
	}
	public Usuario getModificador() {
		return modificador;
	}
	public void setModificador(Usuario modificador) {
		this.modificador = modificador;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Time getHoraModificacion() {
		return horaModificacion;
	}
	public void setHoraModificacion(Time horaModificacion) {
		this.horaModificacion = horaModificacion;
	}
}
