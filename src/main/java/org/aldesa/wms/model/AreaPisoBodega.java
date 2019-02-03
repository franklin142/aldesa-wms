package org.aldesa.wms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * PosicionEstante entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Area_piso_bodega")
public class AreaPisoBodega implements java.io.Serializable {


    // Fields
    @Column(name="codigo_bodega") private String codigoBodega;
    @Id @Column(name="codigo_posicion_piso") private String codigoPosicionPiso;
    @Column(name="estado") private String estado;
    @Column(name="largo") private Double largo;
    @Column(name="ancho") private Double ancho;
    @Column(name="porcentaje_ocupacion") private Double porcentajeOcupacion;

    public String getCodigoBodega() {
        return codigoBodega;
    }

    public void setCodigoBodega(String codigoBodega) {
        this.codigoBodega = codigoBodega;
    }

    public String getCodigoPosicionPiso() {
        return codigoPosicionPiso;
    }

    public void setCodigoPosicionPiso(String codigoPosicionPiso) {
        this.codigoPosicionPiso = codigoPosicionPiso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getPorcentajeOcupacion() {
        return porcentajeOcupacion;
    }

    public void setPorcentajeOcupacion(Double porcentajeOcupacion) {
        this.porcentajeOcupacion = porcentajeOcupacion;
    }
}