package org.aldesa.wms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bulk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Bulk")

public class Bulk implements java.io.Serializable {

	private static final long serialVersionUID = -6884014590836346478L;

	@Id
	@Column(name = "codigo_bulk", unique = true, length = 30)
	private String codigoBulk;

	@Column(name = "id_cliente")
	private String id_cliente;

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	@Column(name = "cliente")
	private String cliente;

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	@Column(name = "Bodega")
	private String bodega;

	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	
	//modificacion de etiqueta Luis
        @Column(name = "pignorado")
	private String pignorado;

	 
    public String getPignorado() {
        return pignorado;
    }

  
    public void setPignorado(String pignorado) {
        this.pignorado = pignorado;
    }
        // fin de modificacion

	public void setPosicionPasillo(String posicionPasillo) {
		this.posicionPasillo = posicionPasillo;
	}

	@Column(name = "codigo_posicion_pasillo")
	private String posicionPasillo;

	@Column(name = "codigo_posicion_patio")
	private String patio;

	@Column(name = "codigo_posicion_estante")
	private String posicionEstante;

	@Column(name = "deposito", length = 25, nullable = true)
	private String deposito;

	@Column(name = "correlativo")
	private Integer correlativo;

	@Column(name = "tarima_flejada", length = 1)
	private String tarimaFlejada;

	@Column(name = "ancho")
	private Double ancho;

	@Column(name = "alto")
	private Double alto;

	@Column(name = "profundidad", precision = 22, scale = 0)
	private Double profundidad;

	@Column(name = "estado", length = 1)
	private String estado;

	@Column(name = "deposito_desc", length = 25, nullable = true)
	private String depositoDesc;

	@Column(name = "peso", precision = 22, scale = 0)
	private Double peso;

	@Column(name = "codigo_posicion_piso")
	private String piso;

	@Column(name = "codigo_posicion_muelle")
	private String muelle;

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getMuelle() {
		return muelle;
	}

	public void setMuelle(String muelle) {
		this.muelle = muelle;
	}

	public String getCodigoBulk() {
		return this.codigoBulk;
	}

	public void setCodigoBulk(String codigoBulk) {
		this.codigoBulk = codigoBulk;
	}

	public String getPosicionPasillo() {
		return this.posicionPasillo;
	}

	public String getPatio() {
		return this.patio;
	}

	public void setPatio(String patio) {
		this.patio = patio;
	}

	public String getPosicionEstante() {
		return this.posicionEstante;
	}

	public void setPosicionEstante(String posicionEstante) {
		this.posicionEstante = posicionEstante;
	}

	public String getDeposito() {
		return this.deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public Integer getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	public String getTarimaFlejada() {
		return this.tarimaFlejada;
	}

	public void setTarimaFlejada(String tarimaFlejada) {
		this.tarimaFlejada = tarimaFlejada;
	}

	public Double getAncho() {
		return this.ancho;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public Double getAlto() {
		return this.alto;
	}

	public void setAlto(Double alto) {
		this.alto = alto;
	}

	public Double getProfundidad() {
		return this.profundidad;
	}

	public void setProfundidad(Double profundidad) {
		this.profundidad = profundidad;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDepositoDesc() {
		return this.depositoDesc;
	}

	public void setDepositoDesc(String depositoDesc) {
		this.depositoDesc = depositoDesc;
	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

}
