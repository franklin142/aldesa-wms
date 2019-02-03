package org.aldesa.wms.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="GRUPO")
public class Grupo {
	@Id
	@Column(name="CODIGO_GRUPO")
	private BigDecimal codigoGrupo;

	@Column(name="DESCRIPCION", length=40)
	private String descripcion;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USUARIO_CREADOR")
	private Usuario creador;
	
	@JsonIgnore
	@Column(name="FECHA_CREADO")
	private Date fechaCreacion;
	
	@JsonIgnore
	@Column(name="HORA_CREADO")
	private Time horaCreacion;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USUARIO_MODIFICADOR")
	private Usuario modificador;

	@JsonIgnore
	@Column(name="FECHA_MODIFICADA")
	private Date fechaModificacion;
	
	@JsonIgnore
	@Column(name="HORA_MODIFICADA")
	private Time horaModificacion;
	
	
	public BigDecimal getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(BigDecimal codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public void setFechaCreacion(Date fechaHoraCreacion) {
		this.fechaCreacion = fechaHoraCreacion;
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
	public void setFechaModificacion(Date fechaHoraModificacion) {
		this.fechaModificacion = fechaHoraModificacion;
	}
	public Time getHoraCreacion() {
		return horaCreacion;
	}
	public void setHoraCreacion(Time horaCreacion) {
		this.horaCreacion = horaCreacion;
	}
	public Time getHoraModificacion() {
		return horaModificacion;
	}
	public void setHoraModificacion(Time horaModificacion) {
		this.horaModificacion = horaModificacion;
	}	
}
