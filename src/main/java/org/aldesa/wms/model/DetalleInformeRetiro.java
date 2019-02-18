package org.aldesa.wms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the Informe_Retiro_Det_v database table.
 * 
 */
@Entity
@Table(name="Informe_Retiro_Det_v")
@NamedQuery(name="DetalleInformeRetiro.findAll", query="SELECT d FROM DetalleInformeRetiro d")
public class DetalleInformeRetiro implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name="Correlativo")
    private int correlativo;

	@Column(name="Bultos")
	private Integer bultos;

	@Column(name="Cantidad")
	private Integer cantidad;

	@Column(name="Codigo")
	private String codigo;

	@Column(name="Cliente")
	private String cliente;
	
	@Column(name="Deposito")
	private String deposito;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="Estado")
	private String estado;

	@Column(name="Retiro_Mercaderia_No")
	private int retiro_Mercaderia_No;

	@Column(name="Unidad_de_Medida")
	private String unidad_de_Medida;

	@Column(name="Entregada")
	private int entregada;
	
	@Column(name="No_Lote")
	private String nLote;
	
	@Column(name="Fec_Venc_Lote")
	private Date fechaVto;

	public DetalleInformeRetiro() {
	}

    public int getCorrelativo() {
        return this.correlativo;
    }
    public void setCorrelativo(int correlativo){
        this.correlativo = correlativo;
    }

	public Integer getBultos() {
		return this.bultos;
	}

	public void setBultos(Integer bultos) {
		this.bultos = bultos;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
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

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getRetiro_Mercaderia_No() {
		return this.retiro_Mercaderia_No;
	}

	public void setRetiro_Mercaderia_No(int retiro_Mercaderia_No) {
		this.retiro_Mercaderia_No = retiro_Mercaderia_No;
	}

	public String getUnidad_de_Medida() {
		return this.unidad_de_Medida;
	}

	public void setUnidad_de_Medida(String unidad_de_Medida) {
		this.unidad_de_Medida = unidad_de_Medida;
	}
	
	public int getEntregada() {
		return this.entregada;
	}

	public void setEntregada(int entregada) {
		this.entregada = entregada;
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

}