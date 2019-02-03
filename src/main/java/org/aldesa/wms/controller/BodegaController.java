package org.aldesa.wms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aldesa.wms.data.BodegaDao;
import org.aldesa.wms.model.Bodega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/bodegas/")
public class BodegaController {
	
	@Autowired
	BodegaDao detDao;
	
	// PERMISO (2,1)
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public @ResponseBody List<Bodega> all(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<Bodega> ta = detDao.getAll();
		return ta;
	}

	// PERMISO (1,2)
	@RequestMapping(method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public void save(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Bodega dmr)
			throws IOException {
		detDao.save(dmr);
	}

	// PERMISO (1,3)
	@RequestMapping(method = RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE})
	public void update(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Bodega dmr)
			throws IOException {
		return;
	}

	// PERMISO (3,1)
	@RequestMapping(value = "byId", method = RequestMethod.GET)
	public @ResponseBody Bodega byId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("recepcion_Mercaderia_No") String recepcion_Mercaderia_No,
			@RequestParam("deposito") String deposito, 
			@RequestParam("recepcion_No_") String recepcion_No_, 
			@RequestParam("consignatario") String consignatario, 
			@RequestParam("correlativ") String correlativ){

		return null;
		
	}
}
