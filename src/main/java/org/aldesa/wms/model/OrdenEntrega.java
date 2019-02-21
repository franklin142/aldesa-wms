package org.aldesa.wms.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Ordenes_Entrega_v")
public class OrdenEntrega {

    @Id
    private OrdenEntregaPK id;
    @Column
    private Integer correlativo;
    @Column(name="Retiro_Mercaderia_No")
    private Integer retiroMercaderia;
    @Column
    private String deposito;
    @Column(name="Consignatario")
    private String consignatario;
    @Column
    private Integer cantidad;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private String estante;
	@Column(name="Cantidad_Deposito")
	private Double cantidadDeposito;
	@Column(name="Cantidad_Total")
	private Double cantidadTotal;
    @Column(name="tipo_ubicacion")
    private String tipoUbicacion;
    @Column(name="Cantidad_preparada")
    private Integer cantidadPreparada;
    @Column(name="entregada")
    private Integer entregada;
    @Column(name="Estado_Mercaderia")
    private String estadoMercaderia;
    @Column(name="Estado_Mercaderia_solicitada")
    private String estadoMercaderiaSolicitada;
    @Column(name="Saldo")
    private Integer saldo;
    @Column(name="status_ubicacion")
    private String statusUbicacion;
    @Transient
    private String saldos=null;
    @Transient
    private String cantidadesPreparadas=null;
    @Transient
    private String cantidadesEntregadas=null;
	@Column(name="No_Lote")
	private String nLote;
	
	@Column(name="Fec_Venc_Lote")
	private Date fechaVto;
    @Transient
	private String fechaVtoString;


    public String getEstadoMercaderiaSolicitada() {
        return estadoMercaderiaSolicitada;
    }

    public void setEstadoMercaderiaSolicitada(String estadoMercaderiaSolicitada) {
        this.estadoMercaderiaSolicitada = estadoMercaderiaSolicitada;
    }

    public String getSaldos(){return saldos;}
    public void setSaldos(String s){ saldos=s;}

    public String getPreparadas(){return cantidadesPreparadas;}
    public void setPreparadas(String s){ cantidadesPreparadas=s;}
    
    public String getEntregadas(){return cantidadesEntregadas;}
    public void setEntregadas(String s){ cantidadesEntregadas=s;}
    
    public String getEstante() {
        return estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    public String getTipoUbicacion() {
        return tipoUbicacion;
    }

    public void setTipoUbicacion(String tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }

    public Integer getCantidadPreparada() {
        return cantidadPreparada;
    }

    public void setCantidadPreparada(Integer cantidadPreparada) {
        this.cantidadPreparada = cantidadPreparada;
    }


    public Integer getEntregada() {
        return entregada;
    }

    public void setEntregada(Integer entregada) {
        this.entregada = entregada;
    }
    
    public String getEstadoMercaderia() {
        return estadoMercaderia;
    }

    public void setEstadoMercaderia(String estadoMercaderia) {
        this.estadoMercaderia = estadoMercaderia;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getStatusUbicacion() {
        return statusUbicacion;
    }

    public void setStatusUbicacion(String statusUbicacion) {
        this.statusUbicacion = statusUbicacion;
    }

    public Double getCantidadDeposito(){return cantidadDeposito;}
	public Double getCantidadTotal(){return cantidadTotal;}
    public void setCantidadDeposito(Double dep){this.cantidadDeposito=dep;}
    public void setCantidadTotal(Double dep){this.cantidadTotal=dep;}
	
    public String getCodigo(){ return this.id.getCodigo();}
    public void setCodigo(String cod){ this.id.setCodigo(cod);}
    public String getCodigoBulk(){ return this.id.getCodigoBulk();}
    public void setCodigoBulk(String cod){ this.id.setCodigoBulk(cod);}
    
    public OrdenEntregaPK getId(){return id;}
    public void setId(OrdenEntregaPK _id){id=_id;}

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

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

    public String getConsignatario() {
        return consignatario;
    }

    public void setConsignatario(String consignatario) {
        this.consignatario = consignatario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	public String getnLote() {
		return nLote;
	}

	public void setnLote(String nLote) {
		this.nLote = nLote;
	}

	public Date getFechaVto() {
		return fechaVto;
	}

	public void setFechaVto(Date fechaVto) {
		this.fechaVto = fechaVto;
	}

	public String getFechaVtoString() {
		return fechaVtoString;
	}

	public void setFechaVtoString(String fechaVtoString) {
		this.fechaVtoString = fechaVtoString;
	}

}
