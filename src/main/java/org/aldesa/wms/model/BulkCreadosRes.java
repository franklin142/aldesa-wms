package org.aldesa.wms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;

/**
 * Created by lennin on 22/12/2015.
 */
@Entity
@Table(name="bulk_creados_resumen_v")
public class BulkCreadosRes {
    @Id
    @Column(name="codigo_bulk")
    private String codigo;

    @Column
    private String deposito;

    @Column(name="ubicacion")
    private String ubicacion;

    @Column
    private String bodega;
    
    @Column
    private String cliente;

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
