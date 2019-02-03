package org.aldesa.wms.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.aldesa.wms.utils.CryptoUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Access;
import static javax.persistence.AccessType.FIELD;
import static javax.persistence.AccessType.PROPERTY;
import javax.persistence.Cacheable;

@Entity
@Table(name = "token_usuario", uniqueConstraints = @UniqueConstraint(columnNames = "token_hash") )
public class TokenAutenticacion implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SECRET_KEY = "hr!n_$w*bt6&1b(s@6b13dp7h^bquc_01jvx5typbz9#ok3)7i^on%am(z2x)n@w^1ss2@3f14k";
	private static final String SECRET_SALT = "_s3cret*k3y_4ld3s42015";

	@JsonIgnore
	@Id
	@Column(name = "token_hash")
	private String tokenHash;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@Column
	private Date creacion;

	@Column
	private Date expira;

	@Transient
	private String publicToken;

	public static TokenAutenticacion createTokenAutenticacion(Usuario u, Date fh, Date ex) throws Exception {
		TokenAutenticacion ta = new TokenAutenticacion();
		ta.usuario = u;
		ta.creacion = fh;
		ta.expira = ex;
		ta.setPublicToken(
				CryptoUtils.HMAC_MD5_encode(
						SECRET_SALT + CryptoUtils.crearPalabraAleatoria(3),
						ta.getUsuario().getUsuario()
				)
		);
		ta.setTokenHash(getTokenHash(ta.getPublicToken()));
		return ta;
	}

	public static String getTokenHash(String token) throws Exception {
		return CryptoUtils.HMAC_MD5_encode(SECRET_KEY, token);
	}

	public String getTokenHash() {
		return tokenHash;
	}

	public void setTokenHash(String tokenHash) {
		this.tokenHash = tokenHash;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public Date getExpira() {
		return expira;
	}

	public void setExpira(Date expira) {
		this.expira = expira;
	}

	public String getPublicToken() {
		return publicToken;
	}

	public void setPublicToken(String publicToken) {
		this.publicToken = publicToken;
	}
}
