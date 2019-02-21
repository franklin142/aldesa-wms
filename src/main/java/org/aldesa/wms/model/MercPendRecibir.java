package org.aldesa.wms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the Merc_Pend_Recibir_v database table.
 * 
 */
@Entity
@Table(name="Merc_Pend_Recibir_v")
@NamedQuery(name="MercPendRecibir.findAll", query="SELECT m FROM MercPendRecibir m")
public class MercPendRecibir implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Cliente", nullable=false, length=10)
	private String cliente;

	@Id
	@Column(name="identificador", nullable=false, length=16)
	private String identificador;

	@Column(name="Codigo_Mercaderia", nullable=false, length=16)
	private String codigo_Mercaderia;

	@Column(name="Deposito", nullable=false, length=25)
	private String deposito;

	@Column(name="Descripcion", nullable=false, length=100)
	private String descripcion;

	@Column(nullable=false)
	private double saldo;

	@Column(name="Unidad_Medida", nullable=false, length=10)
	private String unidad_Medida;

    @Column(name = "correlativo")
    private int item;
    
	@Column(name="No_Lote")
	private String nLote;
	
	@Column(name="Fec_Venc_Lote")
	private Date fechaVto;
    
	@Transient
	private String fechaVtoString;

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	public MercPendRecibir() {
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCodigo_Mercaderia() {
		return this.codigo_Mercaderia;
	}

	public void setCodigo_Mercaderia(String codigo_Mercaderia) {
		this.codigo_Mercaderia = codigo_Mercaderia;
	}

	public String getDeposito() {
		return this.deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getUnidad_Medida() {
		return this.unidad_Medida;
	}

	public void setUnidad_Medida(String unidad_Medida) {
		this.unidad_Medida = unidad_Medida;
	}

	public String getnLote() {
		return nLote;
	}

	public void setnLote(String nLote) {
		this.nLote = nLote;
	}

	public Date getFechaVto() {
		return fechaVto;
	}

	public void setFechaVto(Date fechaVto) {
		this.fechaVto = fechaVto;
	}

	public String getFechaVtoString() {
		return fechaVtoString;
	}

	public void setFechaVtoString(String fechaVtoString) {
		this.fechaVtoString = fechaVtoString;
	}

}