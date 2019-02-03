/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aldesa.wms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lennin
 */
@Entity
@Table(name = "MERCAD_RECIBIDA_v")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MercadRecibida.findAll", query = "SELECT m FROM MercadRecibida m"),
    @NamedQuery(name = "MercadRecibida.findByRecepcionMercaderiaNo", query = "SELECT m FROM MercadRecibida m WHERE m.mercadRecibidaPK.recepcionMercaderiaNo = :recepcionMercaderiaNo"),
    @NamedQuery(name = "MercadRecibida.findByDepositoNo", query = "SELECT m FROM MercadRecibida m WHERE m.mercadRecibidaPK.depositoNo = :depositoNo"),
    @NamedQuery(name = "MercadRecibida.findByEntregaNo", query = "SELECT m FROM MercadRecibida m WHERE m.mercadRecibidaPK.entregaNo = :entregaNo"),
    @NamedQuery(name = "MercadRecibida.findByFecha", query = "SELECT m FROM MercadRecibida m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "MercadRecibida.findByMotorista", query = "SELECT m FROM MercadRecibida m WHERE m.motorista = :motorista"),
    @NamedQuery(name = "MercadRecibida.findByVehiculoPlacaNo", query = "SELECT m FROM MercadRecibida m WHERE m.vehiculoPlacaNo = :vehiculoPlacaNo"),
    @NamedQuery(name = "MercadRecibida.findByJefedeProduccion", query = "SELECT m FROM MercadRecibida m WHERE m.jefedeProduccion = :jefedeProduccion"),
    @NamedQuery(name = "MercadRecibida.findByHoravistobueno", query = "SELECT m FROM MercadRecibida m WHERE m.horavistobueno = :horavistobueno")})
public class MercadRecibida implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MercadRecibidaPK mercadRecibidaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Motorista")
    private String motorista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Vehiculo_Placa_No")
    private String vehiculoPlacaNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Clase_de_Vehiculo")
    private String clasedeVehiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "No_Vehiculo")
    private String noVehiculo;
    
    /* LENNIN AGREGA: */

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Bodega")
    private String bodega;
    
    public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Marchamo_No")
    private String marchamoNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "DMTI")
    private String dmti;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "GTI")
    private String gti;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_de_Descarga")
    @Temporal(TemporalType.TIME)
    private Date horadeDescarga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Total_Bultos")
    private double totalBultos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kg_Total")
    private double kgTotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Recibido_por")
    private String recibidopor;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Cliente")
    private String cliente;
    
    public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@Basic(optional = false)
    @NotNull
    @Column(name = "No_de_Items")
    private double nodeItems;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Usuario_Creador")
    private String usuarioCreador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Creado")
    @Temporal(TemporalType.DATE)
    private Date fechaCreado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Creado")
    @Temporal(TemporalType.TIME)
    private Date horaCreado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Usuario_Modificador")
    private String usuarioModificador;
    /*
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_modificado")
    @Temporal(TemporalType.DATE)
    private Date fechamodificado;
    */
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_modificado")
    @Temporal(TemporalType.TIME)
    private Date horamodificado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Furgones_por_Deposito")
    private short furgonesporDeposito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Modificable")
    private boolean modificable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor_Total")
    private double valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "M3_Total")
    private double m3Total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "M2_Total")
    private double m2Total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Capacidad")
    private short capacidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Visto_Bueno")
    private boolean vistoBueno;
    /* @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Visto_Bueno")
    @Temporal(TemporalType.DATE)
    private Date fechaVistoBueno;
    */
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mt3_a_distribuir")
    private double mt3adistribuir;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mt2_a_distribuir")
    private double mt2adistribuir;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_de_Entrada_Cont")
    @Temporal(TemporalType.DATE)
    private Date fechadeEntradaCont;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Paletizada")
    private String paletizada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Subrubro")
    private short subrubro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Certificado")
    private int certificado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Revisado_Bodega")
    private boolean revisadoBodega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Descargado_por")
    private boolean descargadopor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Total_Mt2_piso")
    private double totalMt2piso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "No_Informe")
    private int noInforme;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Informe")
    @Temporal(TemporalType.DATE)
    private Date fechaInforme;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_entrada_contenedor")
    @Temporal(TemporalType.TIME)
    private Date horaentradacontenedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_descarga")
    @Temporal(TemporalType.DATE)
    private Date fechadescarga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_fin_descarga")
    @Temporal(TemporalType.TIME)
    private Date horafindescarga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tarimas_totales")
    private double tarimastotales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tarimas_flejadas")
    private double tarimasflejadas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Finaliza_transito")
    @Temporal(TemporalType.DATE)
    private Date fechaFinalizatransito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Finaliza_transito")
    @Temporal(TemporalType.TIME)
    private Date horaFinalizatransito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Utiliza_DAN")
    private boolean utilizaDAN;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_autorizacion_corte_marcham")
    @Temporal(TemporalType.TIME)
    private Date horaautorizacioncortemarcham;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Entregado_por")
    private String entregadopor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Jefe_de_Produccion")
    private String jefedeProduccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_visto_bueno")
    @Temporal(TemporalType.TIME)
    private Date horavistobueno;

    public MercadRecibida() {
    }

    public MercadRecibida(MercadRecibidaPK mercadRecibidaPK) {
        this.mercadRecibidaPK = mercadRecibidaPK;
    }

    public MercadRecibida(int recepcionMercaderiaNo, String depositoNo, int entregaNo) {
        this.mercadRecibidaPK = new MercadRecibidaPK(recepcionMercaderiaNo, depositoNo, entregaNo);
    }

    public MercadRecibidaPK getMercadRecibidaPK() {
        return mercadRecibidaPK;
    }

    public void setMercadRecibidaPK(MercadRecibidaPK mercadRecibidaPK) {
        this.mercadRecibidaPK = mercadRecibidaPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getVehiculoPlacaNo() {
        return vehiculoPlacaNo;
    }

    public void setVehiculoPlacaNo(String vehiculoPlacaNo) {
        this.vehiculoPlacaNo = vehiculoPlacaNo;
    }

    public String getClasedeVehiculo() {
        return clasedeVehiculo;
    }

    public void setClasedeVehiculo(String clasedeVehiculo) {
        this.clasedeVehiculo = clasedeVehiculo;
    }

    public String getNoVehiculo() {
        return noVehiculo;
    }

    public void setNoVehiculo(String noVehiculo) {
        this.noVehiculo = noVehiculo;
    }

    public String getMarchamoNo() {
        return marchamoNo;
    }

    public void setMarchamoNo(String marchamoNo) {
        this.marchamoNo = marchamoNo;
    }

    public String getDmti() {
        return dmti;
    }

    public void setDmti(String dmti) {
        this.dmti = dmti;
    }

    public String getGti() {
        return gti;
    }

    public void setGti(String gti) {
        this.gti = gti;
    }

    public Date getHoradeDescarga() {
        return horadeDescarga;
    }

    public void setHoradeDescarga(Date horadeDescarga) {
        this.horadeDescarga = horadeDescarga;
    }

    public double getTotalBultos() {
        return totalBultos;
    }

    public void setTotalBultos(double totalBultos) {
        this.totalBultos = totalBultos;
    }

    public double getKgTotal() {
        return kgTotal;
    }

    public void setKgTotal(double kgTotal) {
        this.kgTotal = kgTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRecibidopor() {
        return recibidopor;
    }

    public void setRecibidopor(String recibidopor) {
        this.recibidopor = recibidopor;
    }

    public double getNodeItems() {
        return nodeItems;
    }

    public void setNodeItems(double nodeItems) {
        this.nodeItems = nodeItems;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Date getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(Date fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public Date getHoraCreado() {
        return horaCreado;
    }

    public void setHoraCreado(Date horaCreado) {
        this.horaCreado = horaCreado;
    }

    public String getUsuarioModificador() {
        return usuarioModificador;
    }

    public void setUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
    }
    /*
    public Date getFechamodificado() {
        return fechamodificado;
    }

    public void setFechamodificado(Date fechamodificado) {
        this.fechamodificado = fechamodificado;
    }
    */
    public Date getHoramodificado() {
        return horamodificado;
    }

    public void setHoramodificado(Date horamodificado) {
        this.horamodificado = horamodificado;
    }

    public short getFurgonesporDeposito() {
        return furgonesporDeposito;
    }

    public void setFurgonesporDeposito(short furgonesporDeposito) {
        this.furgonesporDeposito = furgonesporDeposito;
    }

    public boolean getModificable() {
        return modificable;
    }

    public void setModificable(boolean modificable) {
        this.modificable = modificable;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getM3Total() {
        return m3Total;
    }

    public void setM3Total(double m3Total) {
        this.m3Total = m3Total;
    }

    public double getM2Total() {
        return m2Total;
    }

    public void setM2Total(double m2Total) {
        this.m2Total = m2Total;
    }

    public short getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(short capacidad) {
        this.capacidad = capacidad;
    }

    public boolean getVistoBueno() {
        return vistoBueno;
    }

    public void setVistoBueno(boolean vistoBueno) {
        this.vistoBueno = vistoBueno;
    }
    /*
    public Date getFechaVistoBueno() {
        return fechaVistoBueno;
    }

    public void setFechaVistoBueno(Date fechaVistoBueno) {
        this.fechaVistoBueno = fechaVistoBueno;
    }
*/
    public double getMt3adistribuir() {
        return mt3adistribuir;
    }

    public void setMt3adistribuir(double mt3adistribuir) {
        this.mt3adistribuir = mt3adistribuir;
    }

    public double getMt2adistribuir() {
        return mt2adistribuir;
    }

    public void setMt2adistribuir(double mt2adistribuir) {
        this.mt2adistribuir = mt2adistribuir;
    }

    public Date getFechadeEntradaCont() {
        return fechadeEntradaCont;
    }

    public void setFechadeEntradaCont(Date fechadeEntradaCont) {
        this.fechadeEntradaCont = fechadeEntradaCont;
    }

    public String getPaletizada() {
        return paletizada;
    }

    public void setPaletizada(String paletizada) {
        this.paletizada = paletizada;
    }

    public short getSubrubro() {
        return subrubro;
    }

    public void setSubrubro(short subrubro) {
        this.subrubro = subrubro;
    }

    public int getCertificado() {
        return certificado;
    }

    public void setCertificado(int certificado) {
        this.certificado = certificado;
    }

    public boolean getRevisadoBodega() {
        return revisadoBodega;
    }

    public void setRevisadoBodega(boolean revisadoBodega) {
        this.revisadoBodega = revisadoBodega;
    }

    public boolean getDescargadopor() {
        return descargadopor;
    }

    public void setDescargadopor(boolean descargadopor) {
        this.descargadopor = descargadopor;
    }

    public double getTotalMt2piso() {
        return totalMt2piso;
    }

    public void setTotalMt2piso(double totalMt2piso) {
        this.totalMt2piso = totalMt2piso;
    }

    public int getNoInforme() {
        return noInforme;
    }

    public void setNoInforme(int noInforme) {
        this.noInforme = noInforme;
    }

    public Date getFechaInforme() {
        return fechaInforme;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme;
    }

    public Date getHoraentradacontenedor() {
        return horaentradacontenedor;
    }

    public void setHoraentradacontenedor(Date horaentradacontenedor) {
        this.horaentradacontenedor = horaentradacontenedor;
    }

    public Date getFechadescarga() {
        return fechadescarga;
    }

    public void setFechadescarga(Date fechadescarga) {
        this.fechadescarga = fechadescarga;
    }

    public Date getHorafindescarga() {
        return horafindescarga;
    }

    public void setHorafindescarga(Date horafindescarga) {
        this.horafindescarga = horafindescarga;
    }

    public double getTarimastotales() {
        return tarimastotales;
    }

    public void setTarimastotales(double tarimastotales) {
        this.tarimastotales = tarimastotales;
    }

    public double getTarimasflejadas() {
        return tarimasflejadas;
    }

    public void setTarimasflejadas(double tarimasflejadas) {
        this.tarimasflejadas = tarimasflejadas;
    }

    public Date getFechaFinalizatransito() {
        return fechaFinalizatransito;
    }

    public void setFechaFinalizatransito(Date fechaFinalizatransito) {
        this.fechaFinalizatransito = fechaFinalizatransito;
    }

    public Date getHoraFinalizatransito() {
        return horaFinalizatransito;
    }

    public void setHoraFinalizatransito(Date horaFinalizatransito) {
        this.horaFinalizatransito = horaFinalizatransito;
    }

    public boolean getUtilizaDAN() {
        return utilizaDAN;
    }

    public void setUtilizaDAN(boolean utilizaDAN) {
        this.utilizaDAN = utilizaDAN;
    }

    public Date getHoraautorizacioncortemarcham() {
        return horaautorizacioncortemarcham;
    }

    public void setHoraautorizacioncortemarcham(Date horaautorizacioncortemarcham) {
        this.horaautorizacioncortemarcham = horaautorizacioncortemarcham;
    }

    public String getEntregadopor() {
        return entregadopor;
    }

    public void setEntregadopor(String entregadopor) {
        this.entregadopor = entregadopor;
    }

    public String getJefedeProduccion() {
        return jefedeProduccion;
    }

    public void setJefedeProduccion(String jefedeProduccion) {
        this.jefedeProduccion = jefedeProduccion;
    }

    public Date getHoravistobueno() {
        return horavistobueno;
    }

    public void setHoravistobueno(Date horavistobueno) {
        this.horavistobueno = horavistobueno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mercadRecibidaPK != null ? mercadRecibidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MercadRecibida)) {
            return false;
        }
        MercadRecibida other = (MercadRecibida) object;
        if ((this.mercadRecibidaPK == null && other.mercadRecibidaPK != null) || (this.mercadRecibidaPK != null && !this.mercadRecibidaPK.equals(other.mercadRecibidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.aldesa.wms.rest.models.MercadRecibida[ mercadRecibidaPK=" + mercadRecibidaPK + " ]";
    }
    
}

