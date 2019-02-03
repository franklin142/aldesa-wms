package org.aldesa.wms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.text.SimpleDateFormat;

/**
 * Created by lennin on 22/12/2015.
 */
@Entity
@Table(name="Bulk_Activos_v")
public class BulkActivos {
    @Id
    @Column
    private String deposito;

    @Column(name="Fecha_Primer_Ingreso")
    private String fechaIngreso;

    @Column
    private String cantidad;

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha(){
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyy");
        return sdf1.format(this.fechaIngreso);
    }
}
