package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Type;


/**
 * The persistent class for the BODEGAS database table.
 * 
 */
@Entity
@Table(name="BODEGAS")
public class 	Bodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Bodega")
	private String bodega;

	@Column(name="Area_Total_de_Piso")
	private double area_Total_de_Piso;

	@Column(name="Area_Total_en_Mt2")
	private double area_Total_en_Mt2;

	@Column(name="Area_Util_de_Piso")
	private double area_Util_de_Piso;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name="Bodega_techada", columnDefinition = "BIT(1) DEFAULT 0")
	private Boolean bodega_techada;

	@Column(name="Capacidad_Total_M3")
	private double capacidad_Total_M3;

	@Column(name="Cta_Valores_CIF")
	private String cta_Valores_CIF;

	@Column(name="`Cta._Aforos`")
	private String cta__Aforos;

	@Column(name="`Cta._Costo_Vtas`")
	private String cta__Costo_Vtas;

	@Column(name="`Cta._Devoluciones`")
	private String cta__Devoluciones;

	@Column(name="`Cta._Inventario`")
	private String cta__Inventario;

	@Column(name="`Cta._Vtas`")
	private String cta__Vtas;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="Facturar")
	private String facturar;

	@Column(name="Nichos")
	private double nichos;

	@Column(name="Tipo_de_Bodega")
	private String tipo_de_Bodega;

	@Column(name="Total_Mt2_en_Estantes")
	private double total_Mt2_en_Estantes;

	@Column(name="Total_Mt2_en_piso")
	private double total_Mt2_en_piso;

	@Column(name="Total_Mt3_en_Estantes")
	private double total_Mt3_en_Estantes;

	@Column(name="Total_Mt3_en_piso")
	private double total_Mt3_en_piso;

	@Column(name="Volumen_Total_en_Mt3")
	private double volumen_Total_en_Mt3;

	public Bodega() {
	}

	public String getBodega() {
		return this.bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public double getArea_Total_de_Piso() {
		return this.area_Total_de_Piso;
	}

	public void setArea_Total_de_Piso(double area_Total_de_Piso) {
		this.area_Total_de_Piso = area_Total_de_Piso;
	}

	public double getArea_Total_en_Mt2() {
		return this.area_Total_en_Mt2;
	}

	public void setArea_Total_en_Mt2(double area_Total_en_Mt2) {
		this.area_Total_en_Mt2 = area_Total_en_Mt2;
	}

	public double getArea_Util_de_Piso() {
		return this.area_Util_de_Piso;
	}

	public void setArea_Util_de_Piso(double area_Util_de_Piso) {
		this.area_Util_de_Piso = area_Util_de_Piso;
	}

	public Boolean getBodega_techada() {
		return this.bodega_techada;
	}

	public void setBodega_techada(Boolean bodega_techada) {
		this.bodega_techada = bodega_techada;
	}

	public double getCapacidad_Total_M3() {
		return this.capacidad_Total_M3;
	}

	public void setCapacidad_Total_M3(double capacidad_Total_M3) {
		this.capacidad_Total_M3 = capacidad_Total_M3;
	}

	public String getCta_Valores_CIF() {
		return this.cta_Valores_CIF;
	}

	public void setCta_Valores_CIF(String cta_Valores_CIF) {
		this.cta_Valores_CIF = cta_Valores_CIF;
	}

	public String getCta__Aforos() {
		return this.cta__Aforos;
	}

	public void setCta__Aforos(String cta__Aforos) {
		this.cta__Aforos = cta__Aforos;
	}

	public String getCta__Costo_Vtas() {
		return this.cta__Costo_Vtas;
	}

	public void setCta__Costo_Vtas(String cta__Costo_Vtas) {
		this.cta__Costo_Vtas = cta__Costo_Vtas;
	}

	public String getCta__Devoluciones() {
		return this.cta__Devoluciones;
	}

	public void setCta__Devoluciones(String cta__Devoluciones) {
		this.cta__Devoluciones = cta__Devoluciones;
	}

	public String getCta__Inventario() {
		return this.cta__Inventario;
	}

	public void setCta__Inventario(String cta__Inventario) {
		this.cta__Inventario = cta__Inventario;
	}

	public String getCta__Vtas() {
		return this.cta__Vtas;
	}

	public void setCta__Vtas(String cta__Vtas) {
		this.cta__Vtas = cta__Vtas;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFacturar() {
		return this.facturar;
	}

	public void setFacturar(String facturar) {
		this.facturar = facturar;
	}

	public double getNichos() {
		return this.nichos;
	}

	public void setNichos(double nichos) {
		this.nichos = nichos;
	}

	public String getTipo_de_Bodega() {
		return this.tipo_de_Bodega;
	}

	public void setTipo_de_Bodega(String tipo_de_Bodega) {
		this.tipo_de_Bodega = tipo_de_Bodega;
	}

	public double getTotal_Mt2_en_Estantes() {
		return this.total_Mt2_en_Estantes;
	}

	public void setTotal_Mt2_en_Estantes(double total_Mt2_en_Estantes) {
		this.total_Mt2_en_Estantes = total_Mt2_en_Estantes;
	}

	public double getTotal_Mt2_en_piso() {
		return this.total_Mt2_en_piso;
	}

	public void setTotal_Mt2_en_piso(double total_Mt2_en_piso) {
		this.total_Mt2_en_piso = total_Mt2_en_piso;
	}

	public double getTotal_Mt3_en_Estantes() {
		return this.total_Mt3_en_Estantes;
	}

	public void setTotal_Mt3_en_Estantes(double total_Mt3_en_Estantes) {
		this.total_Mt3_en_Estantes = total_Mt3_en_Estantes;
	}

	public double getTotal_Mt3_en_piso() {
		return this.total_Mt3_en_piso;
	}

	public void setTotal_Mt3_en_piso(double total_Mt3_en_piso) {
		this.total_Mt3_en_piso = total_Mt3_en_piso;
	}

	public double getVolumen_Total_en_Mt3() {
		return this.volumen_Total_en_Mt3;
	}

	public void setVolumen_Total_en_Mt3(double volumen_Total_en_Mt3) {
		this.volumen_Total_en_Mt3 = volumen_Total_en_Mt3;
	}

}