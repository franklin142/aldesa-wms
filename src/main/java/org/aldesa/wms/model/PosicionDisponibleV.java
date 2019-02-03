package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the posiciones_disponibles_v database table.
 * 
 */
@Entity
@Table(name="posiciones_disponibles_v")
@NamedQuery(name="PosicionesDisponiblesV.findAll", query="SELECT p FROM PosicionDisponibleV p")
public class PosicionDisponibleV implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_posicion", nullable=false, length=14)
	private String codigoPosicion;

	@Column(nullable=false, length=1)
	private String tipo;

	public PosicionDisponibleV() {
	}

	public String getCodigoPosicion() {
		return this.codigoPosicion;
	}

	public void setCodigoPosicion(String codigoPosicion) {
		this.codigoPosicion = codigoPosicion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}