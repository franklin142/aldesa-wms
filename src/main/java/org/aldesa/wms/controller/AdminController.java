package org.aldesa.wms.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aldesa.wms.data.UsuarioDao;
import org.aldesa.wms.model.Grupo;
import org.aldesa.wms.model.Permiso;
import org.aldesa.wms.model.TokenAutenticacion;
import org.aldesa.wms.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    UsuarioDao uDao;
    

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView webLoginUser(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="user") String usuario,
			@RequestParam(value="pass") String contrasena)
			throws IOException {
		ModelAndView model=null;
		TokenAutenticacion ta = uDao.login(usuario, contrasena);
		if (ta == null) {
			model = new ModelAndView("admin.login");
			model.addObject("msg", "Credenciales Inválidas.");
		} else {
			model = new ModelAndView("wms.redirect");
			model.addObject("isadmin", true);
			model.addObject("useradm", ta.getUsuario().getUsuario());
			model.addObject("authadm", ta.getPublicToken());
			model.addObject("redirecturl", "/wms-aldesa/admin/usuarios/");
			response.addCookie(new Cookie("authadm", ta.getPublicToken()));
			response.addCookie(new Cookie("useradm", usuario));
			response.addHeader("authadm", ta.getPublicToken());
			response.addHeader("useradm", usuario);
		}
		return model;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView webLoginUserGET(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ControllerUtils.cerrarSesion(request, uDao,"1");
		ModelAndView model = new ModelAndView("admin.login");
		model.addObject("msg", "Ingrese sus credenciales");
		return model;
	}
	
    @RequestMapping(value = "usuarios", method = RequestMethod.GET)
    public ModelAndView usuarios(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidAdmin(request, uDao))
			return new ModelAndView("redirect:/admin/login");
		ModelAndView mdl = new ModelAndView("admin.main");
        mdl.addObject("usuarios",uDao.getAll());
        return mdl;
    }
    
    @RequestMapping(value = "usuarios-add", method = RequestMethod.GET)
    public ModelAndView usuariosAdd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidAdmin(request, uDao))
			return new ModelAndView("redirect:/admin/login");
		ModelAndView mdl = new ModelAndView("admin.usradd");
        mdl.addObject("idusuario",null);
        mdl.addObject("grupos", uDao.getGrupos());
        return mdl;
    }

    @RequestMapping(value = "usuarios-add", method = RequestMethod.POST)
    public ModelAndView usuariosAddPost(
            @RequestParam String user,
            @RequestParam String clave,
            @RequestParam(required=false, defaultValue="off") String tiene_pin_pda,
            @RequestParam(required=false, defaultValue="0") String pinpda,
            @RequestParam String nombre,
            @RequestParam String grupo,
            @RequestParam(required=false, defaultValue="off") String esAdmin,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(!ControllerUtils.isValidAdmin(request, uDao))
            return new ModelAndView("redirect:/admin/login");
        ModelAndView mdl = new ModelAndView("admin.usradd");
        mdl.addObject("grupos", uDao.getGrupos());
        Usuario u = uDao.findByUsuario(user);
        if(u!=null){
            mdl.addObject("msg", "El usuario ya existe");
        }else{
            if(clave.trim().length()==0){
                mdl.addObject("msg", "La clave no debe estar vacía");
                return mdl;
            } else {
                u = new Usuario();
                u.setCreador(uDao.findByUsuario(ControllerUtils.getUsuario(request,"1")));
                if(esAdmin.equalsIgnoreCase("off")) u.setEsAdmin(0);
                else u.setEsAdmin(1);
                if(tiene_pin_pda.equalsIgnoreCase("off")) u.setPinPda(null);
                else{
                    if(pinpda.trim().length()==0){
                        mdl.addObject("msg", "El pin PDA no debe estar vacio");
                        return mdl;
                    }
                    u.setPinPdaHash(pinpda);
                }
                u.setNombreUsuario(nombre);
                u.setUsuario(user);
                u.crearClave(clave);
                u.setGrupo(Integer.parseInt(grupo));
                uDao.register(u);
                mdl.addObject("msg1", "El usuario fue creado con exito");
            }
        }
        u = uDao.findByUsuario(user);
        mdl.addObject("idusuario",u);
        mdl.addObject("vesAdmon",u.getEsAdmin());
        mdl.addObject("vGrupo", u.getGrupo());
        return mdl;
    }

    @RequestMapping(value = "usuarios/{user}/", method = RequestMethod.GET)
    public ModelAndView usuariosEdit(
            @PathVariable String user,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(!ControllerUtils.isValidAdmin(request, uDao))
            return new ModelAndView("redirect:/admin/login");
        Usuario u = uDao.findByUsuario(user);
        if(u==null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "usuario no existe");
            return null;
        }
        ModelAndView mdl = new ModelAndView("admin.usradd");
        mdl.addObject("idusuario",u);
        mdl.addObject("grupos", uDao.getGrupos());
        mdl.addObject("vesAdmon",u.getEsAdmin());
        mdl.addObject("vGrupo", u.getGrupo());
        return mdl;
    }
    @RequestMapping(value = "usuarios/{username}/", method = RequestMethod.POST)
    public ModelAndView usuariosEditPost(
            @PathVariable String username,
            @RequestParam String user,
            @RequestParam(required=false, defaultValue="off") String cambiarClave,
            @RequestParam(required=false, defaultValue="0") String clave,
            @RequestParam(required=false, defaultValue="off") String tiene_pin_pda,
            @RequestParam(required=false, defaultValue="0") String pinpda,
            @RequestParam String nombre,
            @RequestParam String grupo,
            @RequestParam(required=false, defaultValue="off") String esAdmin,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(!ControllerUtils.isValidAdmin(request, uDao))
            return new ModelAndView("redirect:/admin/login");
        ModelAndView mdl = new ModelAndView("admin.usradd");
        Usuario u = uDao.findByUsuario(user);
        if(u==null){
            mdl.addObject("msg", "El usuario no existe");
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "El usuario no existe");
        }else{
            if(esAdmin.equalsIgnoreCase("off")) u.setEsAdmin(0);
            else u.setEsAdmin(1);
            if(tiene_pin_pda.equalsIgnoreCase("off")) u.setPinPda(null);
            else u.setPinPdaHash(pinpda);
            u.setNombreUsuario(nombre);
            u.setUsuario(user);
            if(cambiarClave.equalsIgnoreCase("on"))
                u.crearClave(clave);
            u.setGrupo(Integer.parseInt(grupo));
            uDao.update(u);
            mdl.addObject("msg1", "El usuario fue actualizado con exito");
        }
        u = uDao.findByUsuario(user);
        mdl.addObject("idusuario",u);
        mdl.addObject("grupos", uDao.getGrupos());
        mdl.addObject("vesAdmon",u.getEsAdmin());
        mdl.addObject("vGrupo", u.getGrupo());
        return mdl;
    }

    @RequestMapping(value = "roles", method = RequestMethod.GET)
    public ModelAndView roles(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidAdmin(request, uDao))
			return new ModelAndView("redirect:/admin/login");
		ModelAndView mdl = new ModelAndView("admin.roles");
        mdl.addObject("grupos", uDao.getGrupos());
        return mdl;
    }
    
    @RequestMapping(value = "roles", method = RequestMethod.POST)
    public ModelAndView rolesPost(
            @RequestParam (required=false, defaultValue="0") String codigo,
            @RequestParam (required=false, defaultValue="GRUPO NO DEFINIDO") String descripcion,
            @RequestParam String action,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidAdmin(request, uDao))
			return new ModelAndView("redirect:/admin/login");
		Grupo g;
		g = new Grupo();
		g.setCodigoGrupo(new BigDecimal(codigo));
		g.setDescripcion(descripcion);
		if(action.equalsIgnoreCase("add"))
			uDao.register(g);
		else if(action.equalsIgnoreCase("edit"))
			uDao.update(g);
		ModelAndView mdl = new ModelAndView("admin.roles");
        mdl.addObject("grupos", uDao.getGrupos());
        return mdl;
    }

    @RequestMapping(value = "roles/{id}", method = RequestMethod.GET)
    public ModelAndView rolesEdit(
            @PathVariable String id,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidAdmin(request, uDao))
			return new ModelAndView("redirect:/admin/login");
		ModelAndView mdl = new ModelAndView("admin.rolesedit");
        mdl.addObject("rol", uDao.getGrupo(new BigDecimal(id)));
        mdl.addObject("urls", uDao.getUrlsGrupo(new BigDecimal(id)));
        return mdl;
    }

    @RequestMapping(value = "roles/{id}", method = RequestMethod.POST)
    public ModelAndView rolesEditPost(
            @PathVariable String id,
            @RequestParam(required=false,defaultValue="**") String[] url,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(!ControllerUtils.isValidAdmin(request, uDao))
            return new ModelAndView("redirect:/admin/login");
        Grupo g = uDao.getGrupo(new BigDecimal(id));
        List<Object[]> perms = uDao.getUrlsGrupo(new BigDecimal(id));
        for(Object[] p:perms){
            boolean esta=false;
            for(String u: url){
                if(u.equalsIgnoreCase("**"))
                    break;
                if(u.equalsIgnoreCase(p[1].toString())){
                    esta=true;
                    break;
                }
            }
            uDao.updateUrlGrupo(g.getCodigoGrupo(),new BigDecimal(p[1].toString()), esta);
        }
        ModelAndView mdl = new ModelAndView("admin.rolesedit");
        mdl.addObject("rol", uDao.getGrupo(new BigDecimal(id)));
        mdl.addObject("urls", uDao.getUrlsGrupo(new BigDecimal(id)));
        mdl.addObject("msg1", "Se actualizo el rol");
        return mdl;
    }

    @RequestMapping(value = "usuarios-hab/{usuario}/{habilitar}", method = RequestMethod.GET)
    public ModelAndView usuarios_hab(
            @PathVariable String usuario,
            @PathVariable String habilitar,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(!ControllerUtils.isValidAdmin(request, uDao))
            return new ModelAndView("redirect:/admin/login");
        if (habilitar.equalsIgnoreCase("E")) {
            uDao.eliminarUsuario(usuario);
        }
        else {
           uDao.habilitarUsuario(usuario, habilitar.equalsIgnoreCase("A"));
        }
        ModelAndView mdl = new ModelAndView("redirect:/admin/usuarios");
        return mdl;
    }

    @RequestMapping(value = "roles-eli/{grupo}", method = RequestMethod.GET)
    public ModelAndView roles_eli(
            @PathVariable String grupo,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(!ControllerUtils.isValidAdmin(request, uDao))
            return new ModelAndView("redirect:/admin/login");
            uDao.eliminarGrupo(grupo);
        ModelAndView mdl = new ModelAndView("redirect:/admin/roles");
        return mdl;
    }


}
