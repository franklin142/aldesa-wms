package org.aldesa.wms.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author lennin
 */
@Embeddable
public class MuellePK implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 5945585126692766152L;

	@Basic(optional = false)
    @NotNull
    @Column(name = "BODEGA")
    private String bodega;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Integer codigo;

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public boolean equals(MuellePK pk1){
    	return pk1.getBodega()==this.getBodega() && pk1.getCodigo()==this.getCodigo();
    }
    
    public int hashCode() {
		int hash = 3;
		hash = 7 * hash + this.getBodega().hashCode();
		hash = 7 * hash + this.getCodigo().hashCode();
		return hash;
	}
}
