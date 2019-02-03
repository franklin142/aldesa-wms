package org.aldesa.wms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the detalle_bulk database table.
 * 
 */
@Embeddable
public class DetalleBulkPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_bulk")
	private String codigoBulk;

	@Column(name="codigo_producto", length=16)
	private String codigoProducto;

	@Column(name="estado_merc", length=1)
	private String estadoMerc;
			
	public DetalleBulkPK() {
	}
	public String getCodigoBulk() {
		return this.codigoBulk;
	}
	public void setCodigoBulk(String codigoBulk) {
		this.codigoBulk = codigoBulk;
	}
	public String getCodigoProducto() {
		return this.codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getEstadoMerc() {
		return this.estadoMerc;
	}
	public void setEstadoMerc(String estadoMerc) {
		this.estadoMerc = estadoMerc;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetalleBulkPK)) {
			return false;
		}
		DetalleBulkPK castOther = (DetalleBulkPK)other;
		return 
			this.codigoBulk.equals(castOther.codigoBulk)
			&& this.codigoProducto.equals(castOther.codigoProducto)
			&& this.estadoMerc.equals(castOther.estadoMerc);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoBulk.hashCode();
		hash = hash * prime + this.codigoProducto.hashCode();
		hash = hash * prime + this.estadoMerc.hashCode();
		
		return hash;
	}
}