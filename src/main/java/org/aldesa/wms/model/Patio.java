package org.aldesa.wms.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Patio entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="patios")
public class Patio  implements java.io.Serializable {


    // Fields    

     private String codigoPatio;
     private String descripcion;
     private Double largo;
     private Double ancho;
     private Double porcentajeOcupacion;
     private String estado;

    // Constructors

    /** default constructor */
    public Patio() {
    }

	/** minimal constructor */
    public Patio(String codigoPatio) {
        this.codigoPatio = codigoPatio;
    }
    
    /** full constructor */
    public Patio(String codigoPatio, String descripcion, Double largo, Double ancho, Double porcentajeOcupacion, String estado) {
        this.codigoPatio = codigoPatio;
        this.descripcion = descripcion;
        this.largo = largo;
        this.ancho = ancho;
        this.porcentajeOcupacion = porcentajeOcupacion;
        this.estado = estado;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="codigo_patio", unique=true, nullable=false, length=14)

    public String getCodigoPatio() {
        return this.codigoPatio;
    }
    
    public void setCodigoPatio(String codigoPatio) {
        this.codigoPatio = codigoPatio;
    }
    
    @Column(name="descripcion", length=200)

    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Column(name="largo", precision=22, scale=0)

    public Double getLargo() {
        return this.largo;
    }
    
    public void setLargo(Double largo) {
        this.largo = largo;
    }
    
    @Column(name="ancho", precision=22, scale=0)

    public Double getAncho() {
        return this.ancho;
    }
    
    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }
    
    @Column(name="porcentaje_ocupacion", precision=22, scale=0)

    public Double getPorcentajeOcupacion() {
        return this.porcentajeOcupacion;
    }
    
    public void setPorcentajeOcupacion(Double porcentajeOcupacion) {
        this.porcentajeOcupacion = porcentajeOcupacion;
    }
    
    @Column(name="estado", length=1)

    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

}