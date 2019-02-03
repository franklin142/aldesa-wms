package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the MERCAD_RECIBIDA_v database table.
 * 
 */
@Entity
@Table(name="MERCAD_RECIBIDA_v")
@NamedQuery(name="MERCAD_RECIBIDA_v.findAll", query="SELECT m FROM MERCAD_RECIBIDA_v m")
public class MERCAD_RECIBIDA_v implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Bodega", nullable=false, length=4)
	private String bodega;

	@Column(name="Capacidad", nullable=false)
	private short capacidad;

	@Column(name="Certificado", nullable=false)
	private int certificado;

	@Column(name="Cia_de_Transporte", nullable=false)
	private short cia_de_Transporte;

	@Column(name="Clase_de_Vehiculo", nullable=false, length=15)
	private String clase_de_Vehiculo;

	@Column(name="Cliente", nullable=false, length=15)
	private String cliente;

	@Id
	@Column(name="Deposito_No", nullable=false, length=25)
	private String deposito_No;

	@Column(name="Descargado_por", nullable=false)
	private String descargado_por;

	@Column(name="DMTI", nullable=false, length=15)
	private String dmti;

	@Column(name="Entrega_No", nullable=false)
	private int entrega_No;

	@Column(name="Entregado_por", nullable=false, length=40)
	private String entregado_por;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha", nullable=false)
	private Date fecha;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Creado", nullable=false)
	private Date fecha_Creado;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_de_Entrada_Cont", nullable=false)
	private Date fecha_de_Entrada_Cont;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_descarga", nullable=false)
	private Date fecha_descarga;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_fin_descarga", nullable=false)
	private Date fecha_fin_descarga;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Finaliza_transito", nullable=false)
	private Date fecha_Finaliza_transito;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Informe", nullable=false)
	private Date fecha_Informe;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_modificado", nullable=false)
	private Date fecha_modificado;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Visto_Bueno", nullable=false)
	private Date fecha_Visto_Bueno;

	@Column(name="Furgones_por_Deposito", nullable=false)
	private short furgones_por_Deposito;

	@Column(name="GTI", nullable=false, length=15)
	private String gti;

	@Column(name="Hora_autorizacion_corte_marcham", nullable=false)
	private Time hora_autorizacion_corte_marcham;

	@Column(name="Hora_Creado", nullable=false)
	private Time hora_Creado;

	@Column(name="Hora_de_Descarga", nullable=false)
	private Time hora_de_Descarga;

	@Column(name="Hora_entrada_contenedor", nullable=false)
	private Time hora_entrada_contenedor;

	@Column(name="Hora_fin_descarga", nullable=false)
	private Time hora_fin_descarga;

	@Column(name="Hora_Finaliza_transito", nullable=false)
	private Time hora_Finaliza_transito;

	@Column(name="Hora_modificado", nullable=false)
	private Time hora_modificado;

	@Column(name="Hora_visto_bueno", nullable=false)
	private Time hora_visto_bueno;

	@Column(name="Jefe_de_Produccion", nullable=false, length=40)
	private String jefe_de_Produccion;

	@Column(name="Kg_Total", nullable=false)
	private double kg_Total;

	@Column(name="Licencia_No", nullable=false, length=20)
	private String licencia_No;

	@Column(name="M2_Total", nullable=false)
	private double m2_Total;

	@Column(name="M3_Total", nullable=false)
	private double m3_Total;

	@Column(name="Marchamo_No", nullable=false, length=15)
	private String marchamo_No;

	@Column(name="Modificable", nullable=false)
	private byte modificable;

	@Column(name="Motorista", nullable=false, length=40)
	private String motorista;

	@Column(name="Mt2_a_distribuir", nullable=false)
	private double mt2_a_distribuir;

	@Column(name="Mt3_a_distribuir", nullable=false)
	private double mt3_a_distribuir;

	@Column(name="Muelle", nullable=false)
	private short muelle;

	@Column(name="No_de_Items", nullable=false)
	private double no_de_Items;

	@Column(name="No_Informe", nullable=false)
	private int no_Informe;

	@Column(name="No_Vehiculo", nullable=false, length=15)
	private String no_Vehiculo;

	@Column(name="Observaciones", nullable=false, length=300)
	private String observaciones;

	@Column(name="Paletizada", nullable=false, length=2)
	private String paletizada;

	@Column(name="Recepcion_Mercaderia_No", nullable=false)
	private int recepcion_Mercaderia_No;

	@Column(name="Recibido_por", nullable=false, length=50)
	private String recibido_por;

	@Column(name="Revisado_Bodega", nullable=false)
	private byte revisado_Bodega;

	@Column(name="Subrubro", nullable=false)
	private short subrubro;

	@Column(name="Tarimas_flejadas", nullable=false)
	private double tarimas_flejadas;

	@Column(name="Tarimas_totales", nullable=false)
	private double tarimas_totales;

	@Column(name="Total_Bultos", nullable=false)
	private double total_Bultos;

	@Column(name="Total_Mt2_piso", nullable=false)
	private double total_Mt2_piso;

	@Column(name="Usuario_Creador", nullable=false, length=10)
	private String usuario_Creador;

	@Column(name="Usuario_Modificador", nullable=false, length=10)
	private String usuario_Modificador;

	@Column(name="Utiliza_DAN", nullable=false)
	private byte utiliza_DAN;

	@Column(name="Valor_Total", nullable=false)
	private double valor_Total;

	@Column(name="Vehiculo_Placa_No", nullable=false, length=15)
	private String vehiculo_Placa_No;

	@Column(name="Visto_Bueno", nullable=false)
	private byte visto_Bueno;

	public MERCAD_RECIBIDA_v() {
	}

	public String getBodega() {
		return this.bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public short getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(short capacidad) {
		this.capacidad = capacidad;
	}

	public int getCertificado() {
		return this.certificado;
	}

	public void setCertificado(int certificado) {
		this.certificado = certificado;
	}

	public short getCia_de_Transporte() {
		return this.cia_de_Transporte;
	}

	public void setCia_de_Transporte(short cia_de_Transporte) {
		this.cia_de_Transporte = cia_de_Transporte;
	}

	public String getClase_de_Vehiculo() {
		return this.clase_de_Vehiculo;
	}

	public void setClase_de_Vehiculo(String clase_de_Vehiculo) {
		this.clase_de_Vehiculo = clase_de_Vehiculo;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDeposito_No() {
		return this.deposito_No;
	}

	public void setDeposito_No(String deposito_No) {
		this.deposito_No = deposito_No;
	}

	public String getDescargado_por() {
		return this.descargado_por;
	}

	public void setDescargado_por(String descargado_por) {
		this.descargado_por = descargado_por;
	}

	public String getDmti() {
		return this.dmti;
	}

	public void setDmti(String dmti) {
		this.dmti = dmti;
	}

	public int getEntrega_No() {
		return this.entrega_No;
	}

	public void setEntrega_No(int entrega_No) {
		this.entrega_No = entrega_No;
	}

	public String getEntregado_por() {
		return this.entregado_por;
	}

	public void setEntregado_por(String entregado_por) {
		this.entregado_por = entregado_por;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha_Creado() {
		return this.fecha_Creado;
	}

	public void setFecha_Creado(Date fecha_Creado) {
		this.fecha_Creado = fecha_Creado;
	}

	public Date getFecha_de_Entrada_Cont() {
		return this.fecha_de_Entrada_Cont;
	}

	public void setFecha_de_Entrada_Cont(Date fecha_de_Entrada_Cont) {
		this.fecha_de_Entrada_Cont = fecha_de_Entrada_Cont;
	}

	public Date getFecha_descarga() {
		return this.fecha_descarga;
	}

	public void setFecha_descarga(Date fecha_descarga) {
		this.fecha_descarga = fecha_descarga;
	}

	public Date getFecha_fin_descarga() {
		return this.fecha_fin_descarga;
	}

	public void setFecha_fin_descarga(Date fecha_fin_descarga) {
		this.fecha_fin_descarga = fecha_fin_descarga;
	}

	public Date getFecha_Finaliza_transito() {
		return this.fecha_Finaliza_transito;
	}

	public void setFecha_Finaliza_transito(Date fecha_Finaliza_transito) {
		this.fecha_Finaliza_transito = fecha_Finaliza_transito;
	}

	public Date getFecha_Informe() {
		return this.fecha_Informe;
	}

	public void setFecha_Informe(Date fecha_Informe) {
		this.fecha_Informe = fecha_Informe;
	}

	public Date getFecha_modificado() {
		return this.fecha_modificado;
	}

	public void setFecha_modificado(Date fecha_modificado) {
		this.fecha_modificado = fecha_modificado;
	}

	public Date getFecha_Visto_Bueno() {
		return this.fecha_Visto_Bueno;
	}

	public void setFecha_Visto_Bueno(Date fecha_Visto_Bueno) {
		this.fecha_Visto_Bueno = fecha_Visto_Bueno;
	}

	public short getFurgones_por_Deposito() {
		return this.furgones_por_Deposito;
	}

	public void setFurgones_por_Deposito(short furgones_por_Deposito) {
		this.furgones_por_Deposito = furgones_por_Deposito;
	}

	public String getGti() {
		return this.gti;
	}

	public void setGti(String gti) {
		this.gti = gti;
	}

	public Time getHora_autorizacion_corte_marcham() {
		return this.hora_autorizacion_corte_marcham;
	}

	public void setHora_autorizacion_corte_marcham(Time hora_autorizacion_corte_marcham) {
		this.hora_autorizacion_corte_marcham = hora_autorizacion_corte_marcham;
	}

	public Time getHora_Creado() {
		return this.hora_Creado;
	}

	public void setHora_Creado(Time hora_Creado) {
		this.hora_Creado = hora_Creado;
	}

	public Time getHora_de_Descarga() {
		return this.hora_de_Descarga;
	}

	public void setHora_de_Descarga(Time hora_de_Descarga) {
		this.hora_de_Descarga = hora_de_Descarga;
	}

	public Time getHora_entrada_contenedor() {
		return this.hora_entrada_contenedor;
	}

	public void setHora_entrada_contenedor(Time hora_entrada_contenedor) {
		this.hora_entrada_contenedor = hora_entrada_contenedor;
	}

	public Time getHora_fin_descarga() {
		return this.hora_fin_descarga;
	}

	public void setHora_fin_descarga(Time hora_fin_descarga) {
		this.hora_fin_descarga = hora_fin_descarga;
	}

	public Time getHora_Finaliza_transito() {
		return this.hora_Finaliza_transito;
	}

	public void setHora_Finaliza_transito(Time hora_Finaliza_transito) {
		this.hora_Finaliza_transito = hora_Finaliza_transito;
	}

	public Time getHora_modificado() {
		return this.hora_modificado;
	}

	public void setHora_modificado(Time hora_modificado) {
		this.hora_modificado = hora_modificado;
	}

	public Time getHora_visto_bueno() {
		return this.hora_visto_bueno;
	}

	public void setHora_visto_bueno(Time hora_visto_bueno) {
		this.hora_visto_bueno = hora_visto_bueno;
	}

	public String getJefe_de_Produccion() {
		return this.jefe_de_Produccion;
	}

	public void setJefe_de_Produccion(String jefe_de_Produccion) {
		this.jefe_de_Produccion = jefe_de_Produccion;
	}

	public double getKg_Total() {
		return this.kg_Total;
	}

	public void setKg_Total(double kg_Total) {
		this.kg_Total = kg_Total;
	}

	public String getLicencia_No() {
		return this.licencia_No;
	}

	public void setLicencia_No(String licencia_No) {
		this.licencia_No = licencia_No;
	}

	public double getM2_Total() {
		return this.m2_Total;
	}

	public void setM2_Total(double m2_Total) {
		this.m2_Total = m2_Total;
	}

	public double getM3_Total() {
		return this.m3_Total;
	}

	public void setM3_Total(double m3_Total) {
		this.m3_Total = m3_Total;
	}

	public String getMarchamo_No() {
		return this.marchamo_No;
	}

	public void setMarchamo_No(String marchamo_No) {
		this.marchamo_No = marchamo_No;
	}

	public byte getModificable() {
		return this.modificable;
	}

	public void setModificable(byte modificable) {
		this.modificable = modificable;
	}

	public String getMotorista() {
		return this.motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}

	public double getMt2_a_distribuir() {
		return this.mt2_a_distribuir;
	}

	public void setMt2_a_distribuir(double mt2_a_distribuir) {
		this.mt2_a_distribuir = mt2_a_distribuir;
	}

	public double getMt3_a_distribuir() {
		return this.mt3_a_distribuir;
	}

	public void setMt3_a_distribuir(double mt3_a_distribuir) {
		this.mt3_a_distribuir = mt3_a_distribuir;
	}

	public short getMuelle() {
		return this.muelle;
	}

	public void setMuelle(short muelle) {
		this.muelle = muelle;
	}

	public double getNo_de_Items() {
		return this.no_de_Items;
	}

	public void setNo_de_Items(double no_de_Items) {
		this.no_de_Items = no_de_Items;
	}

	public int getNo_Informe() {
		return this.no_Informe;
	}

	public void setNo_Informe(int no_Informe) {
		this.no_Informe = no_Informe;
	}

	public String getNo_Vehiculo() {
		return this.no_Vehiculo;
	}

	public void setNo_Vehiculo(String no_Vehiculo) {
		this.no_Vehiculo = no_Vehiculo;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPaletizada() {
		return this.paletizada;
	}

	public void setPaletizada(String paletizada) {
		this.paletizada = paletizada;
	}

	public int getRecepcion_Mercaderia_No() {
		return this.recepcion_Mercaderia_No;
	}

	public void setRecepcion_Mercaderia_No(int recepcion_Mercaderia_No) {
		this.recepcion_Mercaderia_No = recepcion_Mercaderia_No;
	}

	public String getRecibido_por() {
		return this.recibido_por;
	}

	public void setRecibido_por(String recibido_por) {
		this.recibido_por = recibido_por;
	}

	public byte getRevisado_Bodega() {
		return this.revisado_Bodega;
	}

	public void setRevisado_Bodega(byte revisado_Bodega) {
		this.revisado_Bodega = revisado_Bodega;
	}

	public short getSubrubro() {
		return this.subrubro;
	}

	public void setSubrubro(short subrubro) {
		this.subrubro = subrubro;
	}

	public double getTarimas_flejadas() {
		return this.tarimas_flejadas;
	}

	public void setTarimas_flejadas(double tarimas_flejadas) {
		this.tarimas_flejadas = tarimas_flejadas;
	}

	public double getTarimas_totales() {
		return this.tarimas_totales;
	}

	public void setTarimas_totales(double tarimas_totales) {
		this.tarimas_totales = tarimas_totales;
	}

	public double getTotal_Bultos() {
		return this.total_Bultos;
	}

	public void setTotal_Bultos(double total_Bultos) {
		this.total_Bultos = total_Bultos;
	}

	public double getTotal_Mt2_piso() {
		return this.total_Mt2_piso;
	}

	public void setTotal_Mt2_piso(double total_Mt2_piso) {
		this.total_Mt2_piso = total_Mt2_piso;
	}

	public String getUsuario_Creador() {
		return this.usuario_Creador;
	}

	public void setUsuario_Creador(String usuario_Creador) {
		this.usuario_Creador = usuario_Creador;
	}

	public String getUsuario_Modificador() {
		return this.usuario_Modificador;
	}

	public void setUsuario_Modificador(String usuario_Modificador) {
		this.usuario_Modificador = usuario_Modificador;
	}

	public byte getUtiliza_DAN() {
		return this.utiliza_DAN;
	}

	public void setUtiliza_DAN(byte utiliza_DAN) {
		this.utiliza_DAN = utiliza_DAN;
	}

	public double getValor_Total() {
		return this.valor_Total;
	}

	public void setValor_Total(double valor_Total) {
		this.valor_Total = valor_Total;
	}

	public String getVehiculo_Placa_No() {
		return this.vehiculo_Placa_No;
	}

	public void setVehiculo_Placa_No(String vehiculo_Placa_No) {
		this.vehiculo_Placa_No = vehiculo_Placa_No;
	}

	public byte getVisto_Bueno() {
		return this.visto_Bueno;
	}

	public void setVisto_Bueno(byte visto_Bueno) {
		this.visto_Bueno = visto_Bueno;
	}

}