package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ubicacion_bulk_v database table.
 * 
 */
@Entity
@Table(name="ubicacion_bulk_v")
@NamedQuery(name="UbicacionBulk.findAll", query="SELECT u FROM UbicacionBulk u")
public class UbicacionBulk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Bodega")
	private String bodega;

	private String cliente;

	@Column(name="codigo_bulk")
	private String codigoBulk;

	private String deposito;
	  //modificacion de etiqueta Luis
        @Column(name = "pignorado")
	private String pignorado;

	 
    public String getPignorado() {
        return pignorado;
    }

  
    public void setPignorado(String pignorado) {
        this.pignorado = pignorado;
    }
    
    @Column(name = "regimen")
	private String regimen;

	 
    public String getRegimen() {
        return regimen;
    }
	

	@Id
	@Column(name="posicion")
	private String posicionEstante;

	private String tipo;

	@Column(name="tipo_posicion")
	private String tipoPosicion;

	public UbicacionBulk() {
	}

	public String getBodega() {
		return this.bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCodigoBulk() {
		return this.codigoBulk;
	}

	public void setCodigoBulk(String codigoBulk) {
		this.codigoBulk = codigoBulk;
	}

	public String getDeposito() {
		return this.deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getPosicionEstante() {
		return this.posicionEstante;
	}

	public void setPosicionEstante(String posicionEstante) {
		this.posicionEstante = posicionEstante;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoPosicion() {
		return this.tipoPosicion;
	}

	public void setTipoPosicion(String tipoPosicion) {
		this.tipoPosicion = tipoPosicion;
	}

}
