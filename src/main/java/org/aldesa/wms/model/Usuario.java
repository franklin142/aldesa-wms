package org.aldesa.wms.model;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.aldesa.wms.utils.CryptoUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USUARIO")
public class Usuario  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 317943552936833250L;
    
    private static final String SECRET_SALT = "20154l3s4_s3cret*salt_4ld3s42015";
    

	@Id
	@Column(name = "USUARIO")
	private String usuario;


	@Column(name = "NOMBRE_USUARIO", length = 20)
	private String nombreUsuario;

    @Column(name="CODIGO_GRUPO")
    private Integer grupo;
    
	@JsonIgnore
	@Column(name = "FECHA_ULTIMA_ACTUALIZACION")
	private Date fechaUltimaActualizacion;

	@Column(name = "FECHA_CADUCA_CLAVE")
	private Date fechaExpiracionClave;

	@Column(name = "ESTADO", length = 1)
	private String estado;

	@Column(name = "CLAVE_ACCESO", length = 150)
	private String clave;
	
	@Column(name = "PIN_PDA", length = 150, unique=true)
	private String pinPda;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USUARIO_CREADOR")
	private Usuario creador;

	@JsonIgnore
	@Column(name = "FECHA_CREADO")
	private Date fechaCreacion;

	@JsonIgnore
	@Column(name = "HORA_CREADO")
	private Time horaCreacion;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USUARIO_MODIFICADOR")
	private Usuario modificado;

	@JsonIgnore
	@Column(name = "FECHA_MODIFICADO")
	private Date fechaModificacion;

	@JsonIgnore
	@Column(name = "HORA_MODIFICADO")
	private Time horaModificacion;

	@JsonIgnore
	@Column(name = "ES_ADMIN")
	private Integer esAdmin;

    public Integer getEsAdmin(){
    	return esAdmin;
    	}
    public void setEsAdmin(Integer esAdmin){
    	this.esAdmin=esAdmin;
    	}
    
    public Integer getGrupo(){
    	return grupo;
    	}
    public void setGrupo(Integer grupo){
    	this.grupo=grupo;
    	}

	public String getPinPda() {
		return pinPda;
	}

	public void setPinPda(String pinPda) {
		this.pinPda = pinPda;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public Date getFechaExpiracionClave() {
		return fechaExpiracionClave;
	}

	public void setFechaExpiracionClave(Date fechaExpiracionClave) {
		this.fechaExpiracionClave = fechaExpiracionClave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getClave() {
		return clave;
	}

	private void setClave(String clave) {
		this.clave = clave;
	}
	
	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	

	public Time getHoraCreacion() {
		return horaCreacion;
	}

	public void setHoraCreacion(Time horaCreacion) {
		this.horaCreacion = horaCreacion;
	}

	public Usuario getModificado() {
		return modificado;
	}

	public void setModificado(Usuario modificado) {
		this.modificado = modificado;
	}
	
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}


	public Time getHoraModificacion() {
		return horaModificacion;
	}

	public void setHoraModificacion(Time horaModificacion) {
		this.horaModificacion = horaModificacion;
	}

	public void crearClave(String clave) throws NoSuchAlgorithmException {
		this.setClave(CryptoUtils.crearContrasena(clave));
	}
	
	public boolean esClave(String clave) throws NoSuchAlgorithmException{
		return clave.equalsIgnoreCase(this.clave) || CryptoUtils.esClave(clave, this.clave);
	}
	
	public void setPinPdaHash(String pinPda) throws Exception{
		this.setPinPda(createPinPdaHash(pinPda));		
	}
	
	public static String createPinPdaHash(String pinPda) throws Exception{
		return CryptoUtils.HMAC_MD5_encode(SECRET_SALT, pinPda);
	}
}
