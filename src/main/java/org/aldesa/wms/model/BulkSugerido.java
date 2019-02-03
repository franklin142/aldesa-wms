package org.aldesa.wms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lennin on 28/12/2015.
 */
@Entity
@Table(name="Bulk_sugerido_v")
public class BulkSugerido {
    @Column
	private Double ancho;
    
    @Column
	private Double alto;
    
    @Column
	private Double profundidad;
    
    @Column
	private Double peso;
    
    @Column(name="tarima_flejada")
    private String tarimaFlejada;
    
    @Column
	private Double cantidad;
    
    private String deposito;

    @Id
    @Column(name="codigo_producto")
    private String codigoProducto;

    @Column
    private String cliente;

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Double profundidad) {
        this.profundidad = profundidad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getTarimaFlejada() {
        return tarimaFlejada;
    }

    public void setTarimaFlejada(String tarimaFlejada) {
        this.tarimaFlejada = tarimaFlejada;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
