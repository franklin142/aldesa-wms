package org.aldesa.wms.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * PosisionPasillo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="posicion_pasillos")
public class PosisionPasillo  implements java.io.Serializable {


    // Fields    

     private String codigoPosicionPasillo;
     private String bodega;
     private Double ancho;
     private Double largo;
     private Integer correlativo;
     private String estado;
     private String tipoRotacion;
     private Double porcOcupacion;


    // Constructors

    /** default constructor */
    public PosisionPasillo() {
    }

	/** minimal constructor */
    public PosisionPasillo(String codigoPosicionPasillo) {
        this.codigoPosicionPasillo = codigoPosicionPasillo;
    }
    
    /** full constructor */
    public PosisionPasillo(String codigoPosicionPasillo, String Bodega, Double ancho, Double largo, Integer correlativo, String estado, String tipoRotacion, Double porcOcupacion) {
        this.codigoPosicionPasillo = codigoPosicionPasillo;
        this.bodega = bodega;
        this.ancho = ancho;
        this.largo = largo;
        this.correlativo = correlativo;
        this.estado = estado;
        this.tipoRotacion = tipoRotacion;
        this.porcOcupacion = porcOcupacion;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="codigo_posicion_pasillo", unique=true, nullable=false, length=14)

    public String getCodigoPosicionPasillo() {
        return this.codigoPosicionPasillo;
    }
    
    public void setCodigoPosicionPasillo(String codigoPosicionPasillo) {
        this.codigoPosicionPasillo = codigoPosicionPasillo;
    }
	@Column(name="bodega",length=4)

    public String getBodega() {
        return this.bodega;
    }
    
    public void setBodega(String bodega) {
        this.bodega = bodega;
    }
    
    
    @Column(name="ancho", precision=22, scale=0)

    public Double getAncho() {
        return this.ancho;
    }
    
    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }
    
    @Column(name="largo", precision=22, scale=0)

    public Double getLargo() {
        return this.largo;
    }
    
    public void setLargo(Double largo) {
        this.largo = largo;
    }
    
    @Column(name="correlativo")

    public Integer getCorrelativo() {
        return this.correlativo;
    }
    
    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }
    
    @Column(name="estado", length=1)

    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Column(name="tipo_rotacion", length=1)

    public String getTipoRotacion() {
        return this.tipoRotacion;
    }
    
    public void setTipoRotacion(String tipoRotacion) {
        this.tipoRotacion = tipoRotacion;
    }
    
    @Column(name="porc_ocupacion", precision=22, scale=0)

    public Double getPorcOcupacion() {
        return this.porcOcupacion;
    }
    
    public void setPorcOcupacion(Double porcOcupacion) {
        this.porcOcupacion = porcOcupacion;
    }

}