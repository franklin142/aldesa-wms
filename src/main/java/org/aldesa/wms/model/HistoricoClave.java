package org.aldesa.wms.model;

import java.sql.Date;

public class HistoricoClave {
	private Usuario usuario;
	private String claveAcceso;
	private Date fechaCaduca;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getClaveAcceso() {
		return claveAcceso;
	}
	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}
	public Date getFechaCaduca() {
		return fechaCaduca;
	}
	public void setFechaCaduca(Date fechaCaduca) {
		this.fechaCaduca = fechaCaduca;
	}	
}
