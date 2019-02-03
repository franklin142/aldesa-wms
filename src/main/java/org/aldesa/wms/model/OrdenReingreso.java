package org.aldesa.wms.model;

import javax.persistence.*;

@Entity
@Table(name="Orden_reingreso_v")
public class OrdenReingreso {

    @Id
    @Column(name="Retiro_Mercaderia_No")
    private Integer retiroMercaderia;

    @Column(name="Deposito_No")
    private String deposito;

    @Column(name="Cliente")
    private String cliente;

    @Column(name="tipo")
    private String tipo;

    public Integer getRetiroMercaderia() {
        return retiroMercaderia;
    }

    public void setRetiroMercaderia(Integer retiroMercaderia) {
        this.retiroMercaderia = retiroMercaderia;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
