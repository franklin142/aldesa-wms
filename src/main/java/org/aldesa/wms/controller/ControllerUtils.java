package org.aldesa.wms.controller;

import java.math.BigDecimal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.aldesa.wms.data.UsuarioDao;
import org.aldesa.wms.model.TokenAutenticacion;

/**
 * Created by lennin on 7/24/2016.
 */
public class ControllerUtils {

	static public String getToken(HttpServletRequest request, String modulo){
		String token;
		if (modulo.equalsIgnoreCase("0")){
		   token =getCookieValue("auth", request);
	       if(token == null)
	    	  token = getCookieValue("auth", request);
		}
		else {
	        token = getHeaderValue("authadm", request);
	        if(token==null)
	    	  token=getCookieValue("authadm", request);
		}
        return token;
    }
	
    static public String getUsuario(HttpServletRequest request, String modulo){
        String usuario;
        if (modulo.equalsIgnoreCase("0")){
           usuario= getHeaderValue("user", request);
           if(usuario==null)
              usuario=getCookieValue("user", request);
        }
        else {
            usuario=getCookieValue("useradm", request);
            if(usuario==null)
               usuario=getCookieValue("useradm", request);
        }
        return usuario;
    }

    static public String getHeaderValue(String key, HttpServletRequest request){
        if(request.getHeader(key) != null)
            return request.getHeader(key);
        return null;
    }

    static public String getCookieValue(String key, HttpServletRequest request){
    	if(request.getCookies()==null) return null;
        for (Cookie c: request.getCookies()){
            if(c.getName().equalsIgnoreCase(key))
                return c.getValue();
        }
        return null;
    }

    static public boolean isValidUser(HttpServletRequest request, UsuarioDao usuarioDao) throws Exception {
        String user = getHeaderValue("user", request);
        if (user==null)
            user = getCookieValue("user", request);
        if (user==null)
            return false;

        String auth = getHeaderValue("auth", request);
        if (auth==null)
            auth = getCookieValue("auth", request);
        if (auth==null)
            return false;

        return usuarioDao.isValidToken(auth, user);
    }

    static public boolean isValidAdmin(HttpServletRequest request, UsuarioDao usuarioDao) throws Exception {
        String user = getHeaderValue("useradm", request);
        if (user==null)
            user = getCookieValue("useradm", request);
        if (user==null)
            return false;

        String auth = getHeaderValue("authadm", request);
        if (auth==null)
            auth = getCookieValue("authadm", request);
        if (auth==null)
            return false;

        return usuarioDao.isValidAdminToken(auth, user);
    }
    
    static public boolean isAllowed(HttpServletRequest request, UsuarioDao usuarioDao, int url)throws Exception {
    	return usuarioDao.usuarioTienePermiso(getUsuario(request,"0"), new BigDecimal(url));
    }
    
    static public void cerrarSesion(HttpServletRequest request, UsuarioDao usuarioDao, String modulo) throws Exception{
    	String token = getToken(request,modulo);
    	String usuario = getUsuario(request,modulo);
    	usuarioDao.cerrarSesion(token, usuario);
    }
}
