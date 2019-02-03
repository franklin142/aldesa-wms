package org.aldesa.wms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity implementation class for Entity: Deposito
 *
 */
@Entity
@Table(name="parametros")

public class Parametros implements Serializable {

	@Id
	private String codigo_modulo;

	@Column
	private Integer dias_vigencia_clave;

	@Column
	private Integer no_clave_sin_repetir;

	@Column
	private String etiqueta_obligatoria;

    public String getCodigoModulo() {
        return codigo_modulo;
    }

    public void setCodigoModulo(String codigo_modulo) {
        this.codigo_modulo = codigo_modulo;
    }

    public Integer getDiasVigenciaClave() {
        return dias_vigencia_clave;
    }

    public void setDiasVigenciaClave(Integer dias_vigencia_clave) {
        this.dias_vigencia_clave = dias_vigencia_clave;
    }

    public Integer getNoClaveSinRepetir() {
        return no_clave_sin_repetir;
    }

    public void setNoClaveSinRepetir(Integer no_clave_sin_repetir) {
        this.no_clave_sin_repetir = no_clave_sin_repetir;
    }

    public String getEtiquetaObligatoria() {
        return etiqueta_obligatoria;
    }

    public void setEtiquetaObligatoria(String etiqueta_obligatoria) {
        this.etiqueta_obligatoria = etiqueta_obligatoria;
    }
}
