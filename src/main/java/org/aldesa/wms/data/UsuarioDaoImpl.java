package org.aldesa.wms.data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.aldesa.wms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {
	@Autowired
	private EntityManager em;

	public Usuario findByUsuario(String usuario) {
		return em.find(Usuario.class, usuario);
	}

	public void register(Usuario usuario) {
		em.persist(usuario);
		return;
	}

	public void register(Permiso permiso) {
		em.persist(permiso);
		return;
	}

	public void register(Grupo grupo) {
		em.persist(grupo);
		return;
	}

	public void ActualizarPermiso(int idpermiso, String usuario, boolean asignar){
		String sqlString = "CALL prc_ActualizarPermisoUsuario(:permiso, :usuario, :agregar);";

		Query q = em.createNativeQuery(sqlString);
		q.setParameter("permiso", idpermiso)
				.setParameter("usuario", usuario)
				.setParameter("agregar", asignar)
				.executeUpdate();
	}

	public List<Usuario> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> user = criteria.from(Usuario.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

    public List<Usuario> getAll(int grupo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> user = criteria.from(Usuario.class);
        criteria.where(cb.equal(user.get("grupo"),grupo));
        criteria.select(user);
        return em.createQuery(criteria).getResultList();
    }

    public List<Usuario> getAll(String estado) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> user = criteria.from(Usuario.class);
        criteria.where(cb.equal(user.get("estado"),estado));
        criteria.select(user);
        return em.createQuery(criteria).getResultList();
    }
    
    public void update(Usuario usuario) {
		em.merge(usuario);
	}

	public void update(Grupo g) {
		Grupo grp = em.find(Grupo.class, g.getCodigoGrupo());
		grp.setDescripcion(g.getDescripcion());
		em.flush();
	}

	public boolean esClaveUsuario(String usuario, String clave) {
        Usuario u;
        TokenAutenticacion ta = null;
        try {
            u = findByUsuario(usuario);
            if (u != null) if (u.esClave(clave))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean cambiarClave(String usuario, String claveActual, String claveNueva){
        Usuario u;
        TokenAutenticacion ta = null;
        try {
            u = findByUsuario(usuario);
            if (u != null) if (u.esClave(claveActual)){
                u.crearClave(claveNueva);
                em.flush();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public TokenAutenticacion login(String usuario, String clave) {
		Usuario u;
		TokenAutenticacion ta = null;
		try {
			u = findByUsuario(usuario);
			if (u != null) {
				if (u.getEstado().equalsIgnoreCase("A"))
					if (u.esClave(clave)) {
						Date creacion = new Date(Calendar.getInstance().getTime().getTime());
						Date expira = new Date(Calendar.getInstance().getTime().getTime() + 2 * 86400000);
						// token de 2 dias con login
						ta = TokenAutenticacion.createTokenAutenticacion(u, creacion, expira);
						em.persist(ta);
					}
				else
					return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ta;
	}

	@Override
	public TokenAutenticacion login(String p) throws Exception {
		Usuario u;
		TokenAutenticacion ta = null;
		u = findByPdaPin(p);
		if (u != null) {
			Date creacion = new Date(Calendar.getInstance().getTime().getTime());
			Date expira = new Date(Calendar.getInstance().getTime().getTime() + 2 * 3600000); // token
																								// de
																								// 2
																								// horas
																								// con
																								// pin
			ta = TokenAutenticacion.createTokenAutenticacion(u, creacion, expira);
			em.persist(ta);
		}
		return ta;
	}

	@Override
	public Usuario findByPdaPin(String pinPda) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> user = criteria.from(Usuario.class);
		criteria.where(cb.equal(user.get("pinPda"), pinPda));
		criteria.select(user);
		try{
			return em.createQuery(criteria).getSingleResult();
		} catch (NoResultException nre){
				//Ignore this because as per your logic this is ok!
			return null;
		}
	}

	@Override
	public Boolean isValidToken(String token, String usuario) throws Exception{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TokenAutenticacion> criteria = cb.createQuery(TokenAutenticacion.class);
		Root<TokenAutenticacion> user = criteria.from(TokenAutenticacion.class);
		String hash =TokenAutenticacion.getTokenHash(token);
		criteria.where( cb.and(
				cb.equal(user.get("tokenHash"), hash),
				cb.equal(user.get("usuario").get("usuario"), usuario),
				cb.greaterThanOrEqualTo(user.<java.util.Date>get("expira"), new java.util.Date())
		));
		criteria.select(user);
		try{
			TokenAutenticacion ta = em.createQuery(criteria).getSingleResult();
			if (ta==null) return false;
			String sql = "select tiempo_inactividad from parametros where codigo_modulo='WMS'";
			Query q = em.createNativeQuery(sql);
			Integer mins = (Integer) q.getSingleResult();
			Calendar cal = Calendar.getInstance(); // creates calendar
		    cal.setTime(new java.util.Date()); // sets calendar time/date
		    cal.add(Calendar.MINUTE, mins); // adds one hour
		    java.util.Date d = cal.getTime();
		    sql = "Update token_usuario set expira=? where token_hash=? and usuario=?";
			q = em.createNativeQuery(sql);
			q.setParameter(1, d)
				.setParameter(2, hash)
				.setParameter(3, usuario)
				.executeUpdate();
			return true;
		} catch (NoResultException nre){
			return false;
		}
	}
	@Override
	public Boolean isValidToken(TokenAutenticacion token) throws Exception{
		return isValidToken(token.getPublicToken(), token.getUsuario().getUsuario());
	}

	@Override
	public Boolean isValidAdminToken(String token, String usuario) throws Exception{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TokenAutenticacion> criteria = cb.createQuery(TokenAutenticacion.class);
		Root<TokenAutenticacion> user = criteria.from(TokenAutenticacion.class);
		String hash =TokenAutenticacion.getTokenHash(token);
		criteria.where( cb.and(
				cb.equal(user.get("tokenHash"), hash),
				cb.equal(user.get("usuario").get("usuario"), usuario),
				cb.greaterThanOrEqualTo(user.<java.util.Date>get("expira"), new java.util.Date())
		));
		criteria.select(user);
		try{
			TokenAutenticacion ta = em.createQuery(criteria).getSingleResult();
			if (ta==null) return false;
			Usuario u = findByUsuario(usuario);
			String sql = "select tiempo_inactividad from parametros where codigo_modulo='WMS'";
			Query q = em.createNativeQuery(sql);
			Integer mins = (Integer) q.getSingleResult();
			Calendar cal = Calendar.getInstance(); // creates calendar
		    cal.setTime(new java.util.Date()); // sets calendar time/date
		    cal.add(Calendar.MINUTE, mins); // adds one hour
		    java.util.Date d = cal.getTime();
		    sql = "Update token_usuario set expira=? where token_hash=? and usuario=?";
			q = em.createNativeQuery(sql);
			q.setParameter(1, d)
				.setParameter(2, hash)
				.setParameter(3, usuario)
				.executeUpdate();
			return u.getEsAdmin().intValue()==1;
		} catch (NoResultException nre){
			return false;
		}
	}

	@Override
	public Boolean isValidAdminToken(TokenAutenticacion token) throws Exception{
		return isValidAdminToken(token.getPublicToken(), token.getUsuario().getUsuario());
	}

	@Override
	public List<PermisosUsuarios> getPermisosUsuario(String usuario){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PermisosUsuarios> criteria = cb.createQuery(PermisosUsuarios.class);
		Root<PermisosUsuarios> user = criteria.from(PermisosUsuarios.class);
		criteria.where(cb.equal(user.get("usuario"),usuario));
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Permiso> getPermisos(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Permiso> criteria = cb.createQuery(Permiso.class);
		Root<Permiso> user = criteria.from(Permiso.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}
	public List<Grupo> getGrupos(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Grupo> criteria = cb.createQuery(Grupo.class);
		Root<Grupo> user = criteria.from(Grupo.class);
		criteria.select(user);
		return em.createQuery(criteria).getResultList();
	}
	public Grupo getGrupo(BigDecimal id){
		return em.find(Grupo.class, id);
	}

	public List<Object[]> getUrlsGrupo(BigDecimal id){

		String sql= "SELECT DISTINCT U.URL, U.IDURL, UG.CODIGO_GRUPO, U.Descripcion,U.Orden  "+
				" FROM  URL_GRUPO UG JOIN URL U  ON (UG.IDURL=U.IDURL)   "+
				" WHERE UG.CODIGO_GRUPO=? "+
				" UNION  "+
				" SELECT URL, IDURL, NULL, Descripcion,Orden FROM URL "+ 
				" 	WHERE IDURL NOT IN ( "+
				" 		SELECT IDURL FROM URL_GRUPO WHERE CODIGO_GRUPO=? "+
				" 	) order by 5";
		Query q = em.createNativeQuery(sql);
		q.setParameter(1, id);
		q.setParameter(2, id);
		List<Object[]> detalles = q.getResultList();
		return detalles;
	}
	
	public void updateUrlGrupo(BigDecimal grupo, BigDecimal url, boolean agregar){
		String sql;
		Query q;
		if(agregar){
			sql = "INSERT INTO URL_GRUPO(IDURL,CODIGO_GRUPO) " + 
					"SELECT ?,? FROM DUAL WHERE NOT EXISTS " + 
					"(SELECT * FROM URL_GRUPO WHERE IDURL=? AND CODIGO_GRUPO=?)";
			q = em.createNativeQuery(sql);
			q.setParameter(1, url);
			q.setParameter(2, grupo);
			q.setParameter(3, url);
			q.setParameter(4, grupo);	
		} else {
			sql = "DELETE FROM URL_GRUPO WHERE IDURL=? AND CODIGO_GRUPO=?";
			q = em.createNativeQuery(sql);
			q.setParameter(1, url);
			q.setParameter(2, grupo);
		}
		q.executeUpdate();
	}
	
	public boolean usuarioTienePermiso(String usuario, BigDecimal url){
		String sql = "SELECT UG.IDURL, UG.CODIGO_GRUPO " +
						" FROM URL_GRUPO UG INNER JOIN ( " +
						" 	SELECT CODIGO_GRUPO FROM USUARIO U WHERE U.USUARIO=? " +
						" ) GRP_USUARIO ON( " +
						" 	UG.CODIGO_GRUPO = GRP_USUARIO.CODIGO_GRUPO " +
						" ) WHERE IDURL=?";
		Query q = em.createNativeQuery(sql);
		q.setParameter(1, usuario);
		q.setParameter(2, url);
		if (q.getResultList().size()<=0) return false;
		return true;
	}
	
	public void cerrarSesion(String token, String usuario) throws Exception{
		if(token == null) return;
		String hash = TokenAutenticacion.getTokenHash(token);
		String sqlString = "CALL PRC_cerrarSesion(:usuario,:token);";
		Query q = em.createNativeQuery(sqlString);
		q.setParameter("usuario", usuario)
		 .setParameter("token", hash)
				.executeUpdate();
		
	}

	public void habilitarUsuario(String usuario, boolean habilitar) throws Exception{
		String hab = "I";
		if(habilitar) hab="A";
		String sql = "Update USUARIO set ESTADO=? where USUARIO=?";
		Query q = em.createNativeQuery(sql);
		q.setParameter(1, hab).setParameter(2, usuario).executeUpdate();
		if(!habilitar){
			// cerrar sesiones activas
			sql = "Update token_usuario set expira=now() where usuario=?";
			q = em.createNativeQuery(sql);
			q.setParameter(1, usuario)
					.executeUpdate();
		}
	}

	public void eliminarUsuario(String usuario) throws Exception{
		String sqlString = "CALL PRC_EliminarUsuario(:usuario);";
		Query q = em.createNativeQuery(sqlString);
		q.setParameter("usuario", usuario)
				.executeUpdate();
	}

	public void eliminarGrupo(String grupo) throws Exception{
		String sqlString = "CALL PRC_EliminarGrupo(:grupo);";
		Query q = em.createNativeQuery(sqlString);
		q.setParameter("grupo", grupo)
				.executeUpdate();
	}
	
}
