package org.aldesa.wms.model;

import java.sql.Date;
import java.sql.Time;

public class GrupoOpcion {
	private Grupo grupo;
	private OpcionMenu menu;
	private Usuario creador;
	private Date fechaCreacion;
	private Time horaCreacion;
	private Usuario modifico;
	private Date fechaModificacion;
	private Time horaModificacion;
	private String inserta;
	private String modifica;
	private String elimina;
	private String consulta;
	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public OpcionMenu getMenu() {
		return menu;
	}
	public void setMenu(OpcionMenu menu) {
		this.menu = menu;
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
	public Usuario getModifico() {
		return modifico;
	}
	public void setModifico(Usuario modifico) {
		this.modifico = modifico;
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
	public String getInserta() {
		return inserta;
	}
	public void setInserta(String inserta) {
		this.inserta = inserta;
	}
	public String getModifica() {
		return modifica;
	}
	public void setModifica(String modifica) {
		this.modifica = modifica;
	}
	public String getElimina() {
		return elimina;
	}
	public void setElimina(String elimina) {
		this.elimina = elimina;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
}
