package org.aldesa.wms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="Merc_Recibida_v")
public class MercRecibida {
    @Id
    @Column
    private String correlativo;

    @Column(name="Deposito")
	private String deposito;
	
	@Column(name="Codigo_Mercaderia")
	private String codigoMercaderia;
	
	@Column(name="Declarada")
	private String declarada;
	
	@Column(name="Recibida")
	private String recibida;

	@Column(name="Codigo_Referencia")
	private String codigoReferencia;

	@Column(name="estado")
	private String estado;

	@Column(name="saldo")
	private String saldo;

	@Column(name="averiada")
	private String averiada;

	@Column(name="Unidad_Medida")
	private String unidadMedida;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="Cliente")
	private String cliente;
	
	@Column(name="posee_sobrante")
	private Integer sobrante;
	
	public Integer getSobrante() {
		return sobrante;
	}

	public void setSobrante(Integer sobrante) {
		this.sobrante = sobrante;
	}

	public int getDiferencia(){
		Float a1, a2;
		try {
			a1 =Float.parseFloat(this.declarada);
		} catch (Exception e) {
			a1 = new Float(0.0);
		} 
		try {
			a2 = Float.parseFloat(this.recibida);
		} catch (Exception e) {
			a2  = new Float(0.0);
		}
		return Math.round(a1 - a2);
	}

    public String getCorrelativo() {
        return correlativo;
    }
    
    public void setCorrelativo(String correlativo) {
        this.correlativo= correlativo;
    }
	
    public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public String getCodigoMercaderia() {
		return codigoMercaderia;
	}
	public void setCodigoMercaderia(String codigoMercaderia) {
		this.codigoMercaderia = codigoMercaderia;
	}
	public String getDeclarada() {
		return declarada;
	}
	public void setDeclarada(String declarada) {
		this.declarada = declarada;
	}
	public String getRecibida() {
		return recibida;
	}
	public void setRecibida(String recibida) {
		this.recibida = recibida;
	}
	public String getCodigoReferencia() {
		return codigoReferencia;
	}
	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getAveriada() {
		return averiada;
	}
	public void setAveriada(String averiada) {
		this.averiada = averiada;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	
}
