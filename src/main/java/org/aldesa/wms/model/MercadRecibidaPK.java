package org.aldesa.wms.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lennin
 */
@Embeddable
public class MercadRecibidaPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5945585126692766152L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "Recepcion_Mercaderia_No")
    private int recepcionMercaderiaNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Deposito_No")
    private String depositoNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Entrega_No")
    private int entregaNo;

    public MercadRecibidaPK() {
    }

    public MercadRecibidaPK(int recepcionMercaderiaNo, String depositoNo, int entregaNo) {
        this.recepcionMercaderiaNo = recepcionMercaderiaNo;
        this.depositoNo = depositoNo;
        this.entregaNo = entregaNo;
    }

    public int getRecepcionMercaderiaNo() {
        return recepcionMercaderiaNo;
    }

    public void setRecepcionMercaderiaNo(int recepcionMercaderiaNo) {
        this.recepcionMercaderiaNo = recepcionMercaderiaNo;
    }

    public String getDepositoNo() {
        return depositoNo;
    }

    public void setDepositoNo(String depositoNo) {
        this.depositoNo = depositoNo;
    }

    public int getEntregaNo() {
        return entregaNo;
    }

    public void setEntregaNo(int entregaNo) {
        this.entregaNo = entregaNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recepcionMercaderiaNo;
        hash += (depositoNo != null ? depositoNo.hashCode() : 0);
        hash += (int) entregaNo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MercadRecibidaPK)) {
            return false;
        }
        MercadRecibidaPK other = (MercadRecibidaPK) object;
        if (this.recepcionMercaderiaNo != other.recepcionMercaderiaNo) {
            return false;
        }
        if ((this.depositoNo == null && other.depositoNo != null) || (this.depositoNo != null && !this.depositoNo.equals(other.depositoNo))) {
            return false;
        }
        if (this.entregaNo != other.entregaNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.aldesa.wms.rest.models.MercadRecibidaPK[ recepcionMercaderiaNo=" + recepcionMercaderiaNo + ", depositoNo=" + depositoNo + ", entregaNo=" + entregaNo + " ]";
    }
    
}
