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
@Table(name="bulk_creados_v")
public class BulkCreados {
    @Id
    @Column(name="id")
    private String idcodigo;
    
    @Column(name="codigo_bulk")
    private String codigo;

    @Column
    private String deposito;

    @Column(name="ubicacion")
    private String ubicacion;

    @Column(name="status_ubic")
    private String tipoUbicacion;

    @Column
    private String ancho;
    @Column
    private String alto;
    @Column
    private String area;
    @Column
    private String peso;
    @Column
    private String profundidad;
    @Column
    private String cliente;
    @Column
    private String nombre_cliente;
    @Column
    private String volumen;
    @Column
    private String tarima_flejada;
    @Column
    private String codigo_producto;
    @Column
    private String nombre_producto;
    @Column
    private String cantidad;
    @Column
    private String unidad_medida;
    @Column
    private String estado_merc;
    @Column
    private String bodega;
    @Column
    private String nombre_bodega;

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public String getNombreBodega() {
        return nombre_bodega;
    }

    public void setNombre_bodega(String nombre_bodega) {
        this.nombre_bodega = nombre_bodega;
    }

    public String getArea() {
        return area;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public String getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(String profundidad) {
        this.profundidad = profundidad;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNombreCliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getTarimaFlejada() {
        return tarima_flejada;
    }

    public void setTarima_flejada(String tarima_flejada) {
        this.tarima_flejada = tarima_flejada;
    }

    public String getCodigoProducto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getNombreProducto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public String getEstadoMerc() {
        return estado_merc;
    }

    public void setEstado_merc(String estado_merc) {
        this.estado_merc = estado_merc;
    }


    public String getId() {
        return idcodigo;
    }

    public void setId(String idcodigo) {
        this.codigo = idcodigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getTipoUbicacion() {
        return tipoUbicacion;
    }

    public void setTipoUbicacion(String tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }
}
