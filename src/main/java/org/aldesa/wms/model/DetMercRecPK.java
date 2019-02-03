package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DET_MERC_REC database table.
 * 
 */
@Embeddable
public class DetMercRecPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Recepcion_Mercaderia_No", insertable=false, updatable=false)
	private int recepcion_Mercaderia_No;

	@Column(name="Deposito", insertable=false, updatable=false)
	private String deposito;

	@Column(name="Recepcion_No", insertable=false, updatable=false)
	private int recepcion_No_;

	@Column(name="Consignatario", insertable=false, updatable=false)
	private String consignatario;

	@Column(name="Correlativ")
	private int correlativ;

	public DetMercRecPK() {
	}
	public int getRecepcion_Mercaderia_No() {
		return this.recepcion_Mercaderia_No;
	}
	public void setRecepcion_Mercaderia_No(int recepcion_Mercaderia_No) {
		this.recepcion_Mercaderia_No = recepcion_Mercaderia_No;
	}
	public String getDeposito() {
		return this.deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public int getRecepcion_No() {
		return this.recepcion_No_;
	}
	public void setRecepcion_No_(int recepcion_No_) {
		this.recepcion_No_ = recepcion_No_;
	}
	public String getConsignatario() {
		return this.consignatario;
	}
	public void setConsignatario(String consignatario) {
		this.consignatario = consignatario;
	}
	public int getCorrelativ() {
		return this.correlativ;
	}
	public void setCorrelativ(int correlativ) {
		this.correlativ = correlativ;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetMercRecPK)) {
			return false;
		}
		DetMercRecPK castOther = (DetMercRecPK)other;
		return 
			(this.recepcion_Mercaderia_No == castOther.recepcion_Mercaderia_No)
			&& this.deposito.equals(castOther.deposito)
			&& (this.recepcion_No_ == castOther.recepcion_No_)
			&& this.consignatario.equals(castOther.consignatario)
			&& (this.correlativ == castOther.correlativ);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.recepcion_Mercaderia_No;
		hash = hash * prime + this.deposito.hashCode();
		hash = hash * prime + this.recepcion_No_;
		hash = hash * prime + this.consignatario.hashCode();
		hash = hash * prime + this.correlativ;
		
		return hash;
	}
}