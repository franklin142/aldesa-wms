package org.aldesa.wms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aldesa.wms.data.UsuarioDao;
import org.aldesa.wms.model.TokenAutenticacion;
import org.aldesa.wms.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rest/auth/")
public class LoginRestController {

	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public @ResponseBody List<Usuario> all(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<Usuario> ta = usuarioDao.getAll();
		return ta;
	}
	

	@RequestMapping(value = "login-web", method = RequestMethod.POST)
	public ModelAndView webLoginUser(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="user") String usuario,
			@RequestParam(value="pass") String contrasena)
			throws IOException {
		ModelAndView model=null;
		TokenAutenticacion ta = usuarioDao.login(usuario, contrasena);
		if (ta == null) {
			model = new ModelAndView("wms.login");
			model.addObject("msg", "Credenciales Inválidas.");
		} else {
			model = new ModelAndView("wms.redirect");
			model.addObject("isadmin", false);
			model.addObject("user", ta.getUsuario().getUsuario());
			model.addObject("auth", ta.getPublicToken());
			model.addObject("redirecturl", "/wms-aldesa/web/arribos");
			response.addCookie(new Cookie("auth", ta.getPublicToken()));
			response.addCookie(new Cookie("user", usuario));
			response.addHeader("auth", ta.getPublicToken());
			response.addHeader("user", usuario);
		}
		return model;
	}

	@RequestMapping(value = "login-web", method = RequestMethod.GET)
	public ModelAndView webLoginUserGET(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ControllerUtils.cerrarSesion(request, usuarioDao,"0");
		ModelAndView model = new ModelAndView("wms.login");
		model.addObject("msg", "Ingrese sus credenciales");
		return model;
	}

    @RequestMapping(value = "contra", method = RequestMethod.GET)
    public ModelAndView webContraGET(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		if(!ControllerUtils.isValidUser(request, usuarioDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
        ModelAndView model = new ModelAndView("wms.changepass");
        model.addObject("msg", null);
		model.addObject("msg1", null);
		model.addObject("usuario", ControllerUtils.getUsuario(request,"0"));
        return model;
    }

    @RequestMapping(value = "contra", method = RequestMethod.POST)
    public ModelAndView webContraPOST(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value="user") String usuario,
                                     @RequestParam(value="pass") String contrasena,
                                      @RequestParam(value="pass1") String contrasena1,
                                      @RequestParam(value="pass2") String contrasena2)
			throws Exception {
		if(!ControllerUtils.isValidUser(request, usuarioDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
        ModelAndView model=null;
		model = new ModelAndView("wms.changepass");
        String usr = ControllerUtils.getUsuario(request,"0");
        if(!usr.equals(usuario)){
            model.addObject("msg", "Error al cambiar la clave, verificar el valor de la clave actual");
        } else if(!contrasena1.equals(contrasena2)){
			model.addObject("msg1", null);
            model.addObject("msg", "Las claves nuevas deben ser iguales");
			model.addObject("msg1", null);
        } else {
        	boolean exito = usuarioDao.cambiarClave(usr, contrasena, contrasena2);
			if(exito) {
				model.addObject("msg1", "Se actualizo la clave");
				model.addObject("msg", null);
			}
			else{
				model.addObject("msg", "Error al cambiar la clave, verificar el valor de la clave actual");
				model.addObject("msg1", null);
			}
		}
		model.addObject("usuario", ControllerUtils.getUsuario(request,"0"));
        return model;
    }

	@RequestMapping(value = "login_pda", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody TokenAutenticacion loginUserPda(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody Usuario u, @RequestParam(value="format", defaultValue="") String format)
			throws Exception {
        TokenAutenticacion ta = usuarioDao.login(u.getPinPda());
        if(ta == null){
            u.setPinPdaHash(u.getPinPda());
            ta = usuarioDao.login(u.getPinPda());
            if (ta == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid credentials");
            } else {
                if (format.equalsIgnoreCase("!json")){
                    return ta;
                } else{
                    response.getWriter().write("Authorization: " + ta.getPublicToken());
        			response.addCookie(new Cookie("auth", ta.getPublicToken()));
        			response.addCookie(new Cookie("user", u.getUsuario()));
        			response.addHeader("auth", ta.getPublicToken());
        			response.addHeader("user", u.getUsuario());
                }
            }
        } else{
            if (format.equalsIgnoreCase("!json")){
                return ta;
            } else{
                response.getWriter().write("Authorization: " + ta.getPublicToken());
    			response.addCookie(new Cookie("auth", ta.getPublicToken()));
    			response.addCookie(new Cookie("user", u.getUsuario()));
    			response.addHeader("auth", ta.getPublicToken());
    			response.addHeader("user", u.getUsuario());
            }
        }
        return null;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String usuario = request.getParameter("usuario"); 
		String clave = request.getParameter("clave");
		String pinPda = request.getParameter("pinpda");
		Usuario u = new Usuario();
		u.setUsuario(usuario);
		u.crearClave(clave);
		if (pinPda == null) {
			u.setPinPda(null);
		} else {
			u.setPinPdaHash(pinPda);
		}
		usuarioDao.register(u);
	}

	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void registerUser(@RequestBody Usuario u, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		u.crearClave(u.getClave());
		if (u.getPinPda() == null) {
			u.setPinPda(null);
		} else {
			u.setPinPdaHash(u.getPinPda());
		}
		usuarioDao.register(u);
	}
	
	
}
