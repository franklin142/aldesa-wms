package org.aldesa.wms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aldesa.wms.data.DetMercRecDao;
import org.aldesa.wms.model.DetMercRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/detmerc/")
public class DetMercRecController {
	
	@Autowired
	DetMercRecDao detDao;
	
	// PERMISO(5,1)
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public @ResponseBody List<DetMercRec> all(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<DetMercRec> ta = detDao.getAll();
		return ta;
	}

	// PERMISO(4,2)
	@RequestMapping(method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public void save(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DetMercRec dmr)
			throws IOException {
		detDao.save(dmr);
	}

	// PERMISO(4,3)
	@RequestMapping(method = RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE})
	public void update(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DetMercRec dmr)
			throws IOException {
		DetMercRec antiguo = detDao.getById(dmr.getId());
		antiguo.setBodega(dmr.getBodega());
		antiguo.setCantidad(dmr.getCantidad());
		antiguo.setCodigo(dmr.getCodigo());
		antiguo.setEmpaque(dmr.getEmpaque());
		antiguo.setEstado(dmr.getEstado());
		antiguo.setKg(dmr.getKg());
		antiguo.setLinea_Productos(dmr.getLinea_Productos());
		antiguo.setM2(dmr.getM2());
	}

	// PERMISO(6,1)
	@RequestMapping(value = "byId", method = RequestMethod.GET)
	public @ResponseBody DetMercRec byId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("recepcion_Mercaderia_No") String recepcion_Mercaderia_No,
			@RequestParam("deposito") String deposito, 
			@RequestParam("recepcion_No_") String recepcion_No_, 
			@RequestParam("consignatario") String consignatario, 
			@RequestParam("correlativ") String correlativ){

		return null;
		
	}
}
