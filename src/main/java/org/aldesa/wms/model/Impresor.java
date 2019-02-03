package org.aldesa.wms.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the BODEGAS database table.
 * 
 */
@Entity
@Table(name="impresores")
public class Impresor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mac_impresor")
	private String mac;

	@Column(name="nombre_impresor")
	private String nombre;

	@Column(name="ip_impresor")
	private String ip;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}