package org.aldesa.wms.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Deposito
 *
 */
@Entity
@Table(name="DEPOSITOS_v")

public class Deposito implements Serializable {

	@Id
	private String Deposito_No;
	@Column
	private Integer Carta_Aceptacion_Carga;
	@Column
	private String Tipo;
	private static final long serialVersionUID = 1L;

	public Deposito() {
		super();
	}   
	public String getDeposito_No() {
		return this.Deposito_No;
	}

	public void setDeposito_No(String Deposito_No) {
		this.Deposito_No = Deposito_No;
	}   
	public Integer getCarta_Aceptacion_Carga() {
		return this.Carta_Aceptacion_Carga;
	}

	public void setCarta_Aceptacion_Carga(Integer Carta_Aceptacion_Carga) {
		this.Carta_Aceptacion_Carga = Carta_Aceptacion_Carga;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
   
}
