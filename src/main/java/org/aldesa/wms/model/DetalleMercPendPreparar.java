package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Merc_Pend_Preparar_det_v database table.
 * 
 */
@Entity
@Table(name="Merc_Pend_Preparar_det_v")
@NamedQuery(name="DetalleMercPendPreparar.findAll", query="SELECT d FROM DetalleMercPendPreparar d")
public class DetalleMercPendPreparar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Correlativo", nullable=false)
	private String correlativo;

	@Column(name="Nombre", nullable=false, length=130)
	private String Nombre;

	@Column(name="orden", nullable=false)
	private String orden;

	@Column(name="Deposito", nullable=false, length=25)
	private String deposito;

	@Column(name="Codigo", nullable=false, length=16)
	private String codigo;

	@Column(name="Descripcion", nullable=false, length=100)
	private String descripcion;

	@Column(name="Cantidad", nullable=false)
	private Integer cantidad;

	@Column(nullable=false, length=1)
	private String tipo;

	@Column(name="Consignatario", nullable=false)
	private String consignatario;

	@Column(name="unidad_medida", nullable=false)
	private String unidadMedida;

	@Column(name="Estado", nullable=false)
	private String estado;

	@Column(name="cantidad_entregada", nullable=false)
	private Integer cantidadEntregada;

	@Column(name="cantidad_a_entregar", nullable=false)
	private Integer cantidadAEntregar;

	@Column(name="Pendiente", nullable=false)
	private Integer pendiente;

	@Column(name="Bulk", nullable=false)
	private String bulk;

	@Column(name="No_salida", nullable=false)
	private String nSalida;

	@Column(name="Ubicacion", nullable=false)
	private String ubicacion;
	
	public DetalleMercPendPreparar() {
	}

  
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getConsignatario() {
        return consignatario;
    }

    public void setConsignatario(String consignatario) {
        this.consignatario = consignatario;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(Integer cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public Integer getCantidadAEntregar() {
        return cantidadAEntregar;
    }

    public void setCantidadAEntregar(Integer cantidadAEntregar) {
        this.cantidadAEntregar = cantidadAEntregar;
    }

    public Integer getPendiente() {
        return pendiente;
    }

    public void setPendiente(Integer pendiente) {
        this.pendiente = pendiente;
    }

    public String getBulk() {
        return bulk;
    }

    public void setBulk(String bulk) {
        this.bulk = bulk;
    }

    public String getnSalida() {
        return nSalida;
    }

    public void setnSalida(String nSalida) {
        this.nSalida = nSalida;
    }

    public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	public String getDeposito() {
		return this.deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOrden() {
		return this.orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}