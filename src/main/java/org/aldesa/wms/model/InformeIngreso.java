package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the Informe_Ingreso_v database table.
 * 
 */
@Entity
@Table(name="Informe_Ingreso_v")
@NamedQuery(name="InformeIngreso.findAll", query="SELECT i FROM InformeIngreso i")
public class InformeIngreso implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="Id")
	private String id;

	@Column(name="area_ocupada")
	private Double areaOcupada;
	
	@Column(name="nombre_reporte")
	private String nombreReporte;	

	@Column(name="Bodega")
	private String bodega;

	@Column(name="carta_porte")
	private String cartaPorte;

	@Column(name="Cia_de_Transporte")
	private String ciaTransporte;

	@Column(name="Cliente")
	private String cliente;

	@Column(name="codigo_contenedor")
	private String codigoContenedor;

	@Column(name="codigo_transporte")
	private String codigoTransporte;

	@Column(name="contenedor_por_deposito")
	private short contenedorPorDeposito;

    
	@Column(name="Deposito_No")
	private String deposito;

	@Column(name="Descargado_Por")
	private String descargado;

	@Column(name="DMTI")
	private String dmti;

	@Column(name="Entrada")
	private int entrada;

	@Column(name="Entregado_por")
	private String entregado;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha")
	private Date fecha;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_descarga")
	private Date fechaDescarga;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Entreda_Veh")
	private Date fechaEntredaVeh;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Finaliza_transito")
	private Date fechaFinalizaTransito;

	@Column(name="Hora_corte_marchamo")
	private Time horaCorteMarchamo;

	@Column(name="Hora_de_Descarga")
	private Time horaDescarga;

	@Column(name="Hora_Entreda_Veh")
	private Time horaEntredaVeh;

	@Column(name="Hora_fin_descarga")
	private Time horaFinDescarga;

	@Column(name="Hora_Finaliza_transito")
	private Time horaFinalizaTransito;

	@Column(name="Jefe_Bodega")
	private String jefeBodega;

	@Column(name="Jefe_de_Produccion")
	private String jefeProduccion;

	@Column(name="Licencia_No")
	private String licenciaNo;

	@Column(name="Marchamo_No")
	private String marchamoNo;

	@Column(name="Motorista")
	private String motorista;

	@Column(name="Muelle")
	private String muelle;

	@Column(name="Nombre")
	private String nombre;

	@Column(name="Numero_de_Informe")
	private String numeroInforme;

	@Column(name="Observaciones")
	private String observaciones;

	@Column(name="Peso_Kgs")
	private Double peso;

    @Column(name="procedencia")
	private String procedencia;

	@Column(name="tarimas_flejadas")
	private Double tarimasFlejadas;

	@Column(name="tarimas_recibidas")
	private Double tarimasRecibidas;

	@Column
	private String tipo;

	@Column(name="tipo_mercaderia")
	private String tipoMercaderia;

	@Column(name="Utiliza_DAN")
	private String dan;

	@Column(name="Vehiculo_placa_No")
	private String placa;

	@Column(name="volumen_ocupado")
	private Double volumenOcupado;

    @Column(name = "tipodeposito")
    private String tipoDeposito;

    @Column(name = "consolidador")
    private String consolidador;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_carta")
    private Date fechaCarta;
    
    @Column(name = "manifiesto_carga")
    private String manifiestoCarga;
    
    @Column(name = "doc_embarque")
    private String docEmbarque;
    
    @Column(name = "aduana_periferica")
    private String aduanaPeriferica;

    @Column(name = "capacidad")
    private int capacidad;
    
    @Column(name = "aduana_de_entrada")
    private String aduanaEntrada;
    
    @Column(name = "vaporynviaje")
    private String vaporynViaje;
        
    @Column(name = "item")
    private int item;
    
    public String getTipoDeposito() {
        return tipoDeposito;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTipoDeposito(String tipoDeposito) {
        this.tipoDeposito = tipoDeposito;
    }
	public Double getAreaOcupada() {
		return areaOcupada;
	}

	public void setAreaOcupada(Double areaOcupada) {
		this.areaOcupada = areaOcupada;
	}

	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public String getCartaPorte() {
		return cartaPorte;
	}

	public void setCartaPorte(String cartaPorte) {
		this.cartaPorte = cartaPorte;
	}

	public String getCiaTransporte() {
		return ciaTransporte;
	}

	public void setCiaTransporte(String ciaTransporte) {
		this.ciaTransporte = ciaTransporte;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCodigoContenedor() {
		return codigoContenedor;
	}

	public void setCodigoContenedor(String codigoContenedor) {
		this.codigoContenedor = codigoContenedor;
	}

	public String getCodigoTransporte() {
		return codigoTransporte;
	}

	public void setCodigoTransporte(String codigoTransporte) {
		this.codigoTransporte = codigoTransporte;
	}

	public short getContenedorPorDeposito() {
		return contenedorPorDeposito;
	}

	public void setContenedorPorDeposito(short contenedorPorDeposito) {
		this.contenedorPorDeposito = contenedorPorDeposito;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getDescargado() {
		return descargado;
	}

	public void setDescargado(String descargado) {
		this.descargado = descargado;
	}

	public String getDmti() {
		return dmti;
	}

	public void setDmti(String dmti) {
		this.dmti = dmti;
	}

	public int getEntrada() {
		return entrada;
	}

	public void setEntrada(int entrada) {
		this.entrada = entrada;
	}

	public String getEntregado() {
		return entregado;
	}

	public void setEntregado(String entregado) {
		this.entregado = entregado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaDescarga() {
		return fechaDescarga;
	}

	public void setFechaDescarga(Date fechaDescarga) {
		this.fechaDescarga = fechaDescarga;
	}

	public Date getFechaEntredaVeh() {
		return fechaEntredaVeh;
	}

	public void setFechaEntredaVeh(Date fechaEntredaVeh) {
		this.fechaEntredaVeh = fechaEntredaVeh;
	}

	public Date getFechaFinalizaTransito() {
		return fechaFinalizaTransito;
	}

	public void setFechaFinalizaTransito(Date fechaFinalizaTransito) {
		this.fechaFinalizaTransito = fechaFinalizaTransito;
	}

	public Time getHoraCorteMarchamo() {
		return horaCorteMarchamo;
	}

	public void setHoraCorteMarchamo(Time horaCorteMarchamo) {
		this.horaCorteMarchamo = horaCorteMarchamo;
	}

	public Time getHoraDescarga() {
		return horaDescarga;
	}

	public void setHoraDescarga(Time horaDescarga) {
		this.horaDescarga = horaDescarga;
	}

	public Time getHoraEntredaVeh() {
		return horaEntredaVeh;
	}

	public void setHoraEntredaVeh(Time horaEntredaVeh) {
		this.horaEntredaVeh = horaEntredaVeh;
	}

	public Time getHoraFinDescarga() {
		return horaFinDescarga;
	}

	public void setHoraFinDescarga(Time horaFinDescarga) {
		this.horaFinDescarga = horaFinDescarga;
	}

	public Time getHoraFinalizaTransito() {
		return horaFinalizaTransito;
	}

	public void setHoraFinalizaTransito(Time horaFinalizaTransito) {
		this.horaFinalizaTransito = horaFinalizaTransito;
	}

	public String getJefeBodega() {
		return jefeBodega;
	}

	public void setJefeBodega(String jefeBodega) {
		this.jefeBodega = jefeBodega;
	}

	public String getJefeProduccion() {
		return jefeProduccion;
	}

	public void setJefeProduccion(String jefeProduccion) {
		this.jefeProduccion = jefeProduccion;
	}

	public String getLicenciaNo() {
		return licenciaNo;
	}

	public void setLicenciaNo(String licenciaNo) {
		this.licenciaNo = licenciaNo;
	}

	public String getMarchamoNo() {
		return marchamoNo;
	}

	public void setMarchamoNo(String marchamoNo) {
		this.marchamoNo = marchamoNo;
	}

	public String getMotorista() {
		return motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}

	public String getMuelle() {
		return muelle;
	}

	public void setMuelle(String muelle) {
		this.muelle = muelle;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroInforme() {
		return numeroInforme;
	}

	public void setNumeroInforme(String numeroInforme) {
		this.numeroInforme = numeroInforme;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public Double getTarimasFlejadas() {
		return tarimasFlejadas;
	}

	public void setTarimasFlejadas(Double tarimasFlejadas) {
		this.tarimasFlejadas = tarimasFlejadas;
	}

	public Double getTarimasRecibidas() {
		return tarimasRecibidas;
	}

	public void setTarimasRecibidas(Double tarimasRecibidas) {
		this.tarimasRecibidas = tarimasRecibidas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoMercaderia() {
		return tipoMercaderia;
	}

	public void setTipoMercaderia(String tipoMercaderia) {
		this.tipoMercaderia = tipoMercaderia;
	}

	public String getDan() {
		return dan;
	}

	public void setDan(String dan) {
		this.dan = dan;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Double getVolumenOcupado() {
		return volumenOcupado;
	}

	public void setVolumenOcupado(Double volumenOcupado) {
		this.volumenOcupado = volumenOcupado;
	}
	
        public String getFechaTransito(){
           SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyy");
		return sdf1.format(fechaFinalizaTransito);
	
            
        }
        
	public String getFechaHoraEntrada(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
		return sdf1.format(this.fechaEntredaVeh) + " " + sdf2.format(this.horaEntredaVeh);
	}

	public String getHoraMarchamo(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		return sdf1.format(this.horaCorteMarchamo);
	}

	public String getFHMarchamo(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		return sdf2.format(this.fecha) + " " +sdf1.format(this.horaCorteMarchamo);
	}
	
	public String getFHTransitoAduana(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
		return sdf1.format(this.fechaFinalizaTransito) + " " + sdf2.format(this.horaFinalizaTransito);
	}
	
	public String getFDescarga(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyy");
		return sdf1.format(this.fechaDescarga);		
	}
	
	public String getHInicio(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		return sdf1.format(this.horaDescarga);
	}

	public String getHFin(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		return sdf1.format(this.horaFinDescarga);
	}
	
	public String getConsolidador() {
		return consolidador;
	}

	public void setConsolidador(String consolidador) {
		this.consolidador = consolidador;
	}

	public String getFCarta(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyy");
		return sdf1.format(this.fechaCarta);		
	}
	
	public void setFCarta(Date fechaCarta) {
		this.fechaCarta = fechaCarta;
	}

	public String getMCarga() {
		return manifiestoCarga;
	}

	public void setMCarga(String manifiestoCarga) {
		this.manifiestoCarga = manifiestoCarga;
	} 

	public String getDocEmbarque() {
		return docEmbarque;
	}

	public void setDocEmbarque(String docEmbarque) {
		this.docEmbarque = docEmbarque;
	}

	public String getAPeriferica() {
		return aduanaPeriferica;
	}

	public void setAPeriferica(String aduanaPeriferica) {
		this.aduanaPeriferica = aduanaPeriferica;
	}
	    
	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public String getAEntrada() {
		return aduanaEntrada;
	}

	public void setAEntrada(String aduanaEntrada) {
		this.aduanaEntrada = aduanaEntrada;
	}

	public String getVaporynViaje() {
		return vaporynViaje;
	} 

	public void setVaporynViaje(String vaporynViaje) {
		this.vaporynViaje = vaporynViaje;
	}
    
	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public String getNombreReporte() {
                return nombreReporte;
        }

	public void setNombreReporte(String nombreReporte) {
         this.nombreReporte = nombreReporte;
        }

	
}

