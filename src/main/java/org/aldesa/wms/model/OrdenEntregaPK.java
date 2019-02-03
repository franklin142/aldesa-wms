package org.aldesa.wms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrdenEntregaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

    @Column
    private String codigo;

    @Column(name="codigo_bulk")
    private String codigoBulk;
    
    public String getCodigo(){ return this.codigo;}
    public void setCodigo(String cod){ this.codigo = cod;}
    public String getCodigoBulk(){ return this.codigoBulk;}
    public void setCodigoBulk(String cod){ this.codigoBulk = cod;}
}