package org.aldesa.wms.model;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the BODEGAS database table.
 * 
 */
@Entity
@Table(name="MUELLES")
public class Muelle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected MuellePK id;

	@Column(name="NOMBRE")
	private String nombre;

    public MuellePK getId() {
        return id;
    }

    public void setId(MuellePK id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}