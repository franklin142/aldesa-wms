
package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the MERCADERIA_RECIBIDA_v database table.
 * 
 */
@Entity
@Table(name="MERCADERIA_RECIBIDA_v")
public class MercRecibidaCompleta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Bodega", nullable=false, length=4)
	private String bodega;

	@Column(name="Muelle", nullable=false, length=20)
	private String muelle;

	@Column(name="Capacidad", nullable=false, length=40)
	private String capacidad;

	@Column(nullable=false)
	private int carta;

	@Column(name="Cliente", nullable=false, length=15)
	private String cliente;

	@Column(name="nombre_cliente", nullable=false, length=15)
	private String nombreCliente;
	
	@Id
	@Column(name="Deposito_No", nullable=false, length=25)
	private String deposito_No;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha", nullable=false)
	private Date fecha;

	@Column(name="Motorista", nullable=false, length=50)
	private String motorista;

	@Column(nullable=false, length=15)
	private String placa;

	@Column(nullable=false, length=1)
	private String tipo;

	@Column(nullable=false, length=1)
	private String tipod;

	@Column(name="Utiliza_DAN", nullable=false)
	private String utiliza_DAN;
	
	@Column(name="Inicio_descarga", nullable=false)
	private String inicioDescarga;

	@Column(name="Finalizo_descarga", nullable=false)
	private String finalizoDescarga;
	
	public String getTipod() {
		return tipod;
	}

	public void setTipod(String tipod) {
		this.tipod = tipod;
	}

	public String getInicioDescarga() {
		return inicioDescarga;
	}

	public void setInicioDescarga(String inicioDescarga) {
		this.inicioDescarga = inicioDescarga;
	}

	public String getFinalizoDescarga() {
		return finalizoDescarga;
	}

	public void setFinalizoDescarga(String finalizoDescarga) {
		this.finalizoDescarga = finalizoDescarga;
	}

	public String getBodega() {
		return this.bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public String getMuelle() {
		return this.muelle;
	}

	public void setMuelle(String muelle) {
		this.bodega = muelle;
	}

	public String getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public int getCarta() {
		return this.carta;
	}

	public void setCarta(int carta) {
		this.carta = carta;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDeposito_No() {
		return this.deposito_No;
	}

	public void setDeposito_No(String deposito_No) {
		this.deposito_No = deposito_No;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMotorista() {
		return this.motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUtiliza_DAN() {
		return this.utiliza_DAN;
	}

	public void setUtiliza_DAN(String utiliza_DAN) {
		this.utiliza_DAN = utiliza_DAN;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

}