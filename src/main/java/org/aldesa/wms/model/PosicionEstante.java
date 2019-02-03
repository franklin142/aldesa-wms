package org.aldesa.wms.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * PosicionEstante entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="posicion_estantes")
public class PosicionEstante  implements java.io.Serializable {


    // Fields    

     private String codigoPosicionEstante;
     private String codigoBodega;
     private String codigoEstante;
     private Integer correlativoColumna;
     private Integer nivelEstante;
     private Integer codigoTipoestante;
     private String poseeViga;
     private String accesoOtraArea;
     private String areaDePiso;
     private String tipoRotacion;
     private String estado;
     private Integer nicho;


    // Constructors

    /** default constructor */
    public PosicionEstante() {
    }

	/** minimal constructor */
    public PosicionEstante(String codigoPosicionEstante) {
        this.codigoPosicionEstante = codigoPosicionEstante;
    }
    
    /** full constructor */
    public PosicionEstante(String codigoPosicionEstante, String codigoBodega, String codigoEstante, Integer correlativoColumna, Integer nivelEstante, Integer codigoTipoestante, String poseeViga, String accesoOtraArea, String areaDePiso, String tipoRotacion, String estado, Integer nicho) {
        this.codigoPosicionEstante = codigoPosicionEstante;
        this.codigoBodega = codigoBodega;
        this.codigoEstante = codigoEstante;
        this.correlativoColumna = correlativoColumna;
        this.nivelEstante = nivelEstante;
        this.codigoTipoestante = codigoTipoestante;
        this.poseeViga = poseeViga;
        this.accesoOtraArea = accesoOtraArea;
        this.areaDePiso = areaDePiso;
        this.tipoRotacion = tipoRotacion;
        this.estado = estado;
        this.nicho = nicho;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="codigo_posicion_estante", unique=true, nullable=false, length=14)

    public String getCodigoPosicionEstante() {
        return this.codigoPosicionEstante;
    }
    
    public void setCodigoPosicionEstante(String codigoPosicionEstante) {
        this.codigoPosicionEstante = codigoPosicionEstante;
    }
    
    @Column(name="codigo_bodega", length=4)

    public String getCodigoBodega() {
        return this.codigoBodega;
    }
    
    public void setCodigoBodega(String codigoBodega) {
        this.codigoBodega = codigoBodega;
    }
    
    @Column(name="codigo_estante", length=4)

    public String getCodigoEstante() {
        return this.codigoEstante;
    }
    
    public void setCodigoEstante(String codigoEstante) {
        this.codigoEstante = codigoEstante;
    }
    
    @Column(name="correlativo_columna")

    public Integer getCorrelativoColumna() {
        return this.correlativoColumna;
    }
    
    public void setCorrelativoColumna(Integer correlativoColumna) {
        this.correlativoColumna = correlativoColumna;
    }
    
    @Column(name="nivel_estante")

    public Integer getNivelEstante() {
        return this.nivelEstante;
    }
    
    public void setNivelEstante(Integer nivelEstante) {
        this.nivelEstante = nivelEstante;
    }
    
    @Column(name="codigo_tipoestante")

    public Integer getCodigoTipoestante() {
        return this.codigoTipoestante;
    }
    
    public void setCodigoTipoestante(Integer codigoTipoestante) {
        this.codigoTipoestante = codigoTipoestante;
    }
    
    @Column(name="posee_viga", length=1)

    public String getPoseeViga() {
        return this.poseeViga;
    }
    
    public void setPoseeViga(String poseeViga) {
        this.poseeViga = poseeViga;
    }
    
    @Column(name="acceso_otra_area", length=1)

    public String getAccesoOtraArea() {
        return this.accesoOtraArea;
    }
    
    public void setAccesoOtraArea(String accesoOtraArea) {
        this.accesoOtraArea = accesoOtraArea;
    }
    
    @Column(name="area_de_piso", length=1)

    public String getAreaDePiso() {
        return this.areaDePiso;
    }
    
    public void setAreaDePiso(String areaDePiso) {
        this.areaDePiso = areaDePiso;
    }
    
    @Column(name="tipo_rotacion", length=1)

    public String getTipoRotacion() {
        return this.tipoRotacion;
    }
    
    public void setTipoRotacion(String tipoRotacion) {
        this.tipoRotacion = tipoRotacion;
    }
    
    @Column(name="estado", length=1)

    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Column(name="nicho")

    public Integer getNicho() {
        return this.nicho;
    }
    
    public void setNicho(Integer nicho) {
        this.nicho = nicho;
    }
}