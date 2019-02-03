package org.aldesa.wms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="ESTADO_MERCANCIA_v")
public class EstadoMercancia {
	@Id
	@Column(name="Codigo")
	private String codigo;

	@Column(name="Descripcion", length=40)
	private String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigoGrupo) {
		this.codigo = codigoGrupo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
