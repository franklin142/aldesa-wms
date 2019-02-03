package org.aldesa.wms.model;

public class UsuarioAcceso {
	private Usuario usuario;
	private int accesosDenegados;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getAccesosDenegados() {
		return accesosDenegados;
	}
	public void setAccesosDenegados(int accesosDenegados) {
		this.accesosDenegados = accesosDenegados;
	}
}
