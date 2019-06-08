package org.aldesa.wms.controller;

import java.awt.geom.Area;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aldesa.wms.data.BulkDao;
import org.aldesa.wms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/rest/ubicacion/")
public class UbicacionController {
	
	@Autowired
	BulkDao detDao;
	
	// PERMISO (7,1)
	/* Obtener bulk segun ubicacion */
	@RequestMapping(value = "getbulkbypos/{posicion}", method = RequestMethod.GET)
	public @ResponseBody UbicacionBulk ubicacionByPos( @PathVariable String posicion,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UbicacionBulk ta = detDao.byPosicion(posicion);
		if(ta == null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return ta;
	}

	// PERMISO (8,1)
	/* Obtener cosa por ubicar */
	@RequestMapping(value = "getbulkv/{bulk}", method = RequestMethod.GET)
	public @ResponseBody BulkV allv(@PathVariable String bulk,
			   HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		BulkV ta = detDao.getById_v(bulk);
		if(ta == null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return ta;
	}

	// PERMISO (9,1)
	/* Obtener cosa por ubicar */
	@RequestMapping(value = "getbulk/{bulk}", method = RequestMethod.GET)
	public @ResponseBody BulkR all( @PathVariable String bulk,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		BulkR ta = detDao.getByIdr_v(bulk);
		if(ta == null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return ta;
	}

	// PERMISO (10,1)
	/* obtener cosa por reubicar */
	@RequestMapping(value = "reubicacion/getbulk/{bulk}", method = RequestMethod.GET)
	public @ResponseBody Bulk reubicacionall( @PathVariable String bulk,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Bulk ta = detDao.getById(bulk);
		if(ta == null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		else if( ta.getEstado().equalsIgnoreCase("R")){
			ta=null;
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
		return ta;
	}

	// PERMISO (11,1)
	/* obtener posicion libre */
	@RequestMapping(value = "getposiciondisponible/{posicion}", method = RequestMethod.GET)
	public @ResponseBody PosicionDisponibleV posicion( @PathVariable String posicion,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PosicionDisponibleV ta = detDao.getPosicionDispById(posicion);
		if(ta == null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return ta;
	}

	// PERMISO (12,1)
	@RequestMapping(value = "areapiso/{bodega}", method = RequestMethod.GET)
	public @ResponseBody List<AreaPisoBodega> getareapiso( @PathVariable String bodega,
													   HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<AreaPisoBodega> apb = detDao.getAreaPiso(bodega);
		return apb;
	}

	// PERMISO (13,1)
	@RequestMapping(value = "orden_reingreso", method = RequestMethod.GET)
	public @ResponseBody List<OrdenReingreso> getOrdenReingreso(
			@RequestParam(required = false, defaultValue = "*-*") String orden,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<OrdenReingreso> apb;
		if(orden.equalsIgnoreCase("*-*"))
			apb = detDao.getOrdenReingreso();
		else
			apb = detDao.getOrdenReingreso(orden);
		return apb;
	}

	// PERMISO (14,1)
	@RequestMapping(value = "detbulkrv", method = RequestMethod.GET)
	public @ResponseBody List<DetalleBulkRV> getDetBulkrv(
			@RequestParam(required = false, defaultValue = "*-*") String orden,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<DetalleBulkRV> apb;
		if(orden.equalsIgnoreCase("*-*"))
			apb = detDao.getDetalles3();
		else
			apb = detDao.getDetalles3(orden	);
		return apb;
	}

	// PERMISO (15,2)
	@RequestMapping(value="create/areapiso/", method=RequestMethod.POST)
	public @ResponseBody AreaPisoBodega createAreapiso(HttpServletRequest request, HttpServletResponse response,
													   @RequestBody AreaPisoBodega detalle){
		detDao.saveAreaPiso(detalle);
		return detalle;
	}


	// PERMISO (16,2)
	@RequestMapping(value="update/areapiso/", method=RequestMethod.POST)
	public @ResponseBody AreaPisoBodega updateAreapiso(HttpServletRequest request, HttpServletResponse response,
													   @RequestBody AreaPisoBodega detalle){
		detDao.updateAreaPiso(detalle);
		return detalle;
	}

	// PERMISO (17,2)
	@RequestMapping(value="dbulk", method=RequestMethod.POST)
	public @ResponseBody DetalleBulk updateDetalleBulk(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DetalleBulk detalle){
		return detDao.updateDetalle(detalle);
	}


	// PERMISO (18,2)
	@RequestMapping(value="reingreso", method=RequestMethod.POST)
	public void reingresaDetalleBulk(
			@RequestParam String bulk,
			@RequestParam String retiro,
			@RequestParam String posicion,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException {
		
		List<String> vResultado=detDao.reingresoDetalleBulk(bulk, retiro, posicion);
		response.getWriter().write("{ejecutado:"+vResultado.get(0)+"}");
	}
	

	// PERMISO (19,2)
	/* actualizar estado de bulk */
	@RequestMapping(value="bulk",method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Bulk save(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Bulk dmr)
			throws IOException {
		Bulk dmr2 =detDao.save(dmr); 
		if(dmr2==null)
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return dmr2;
	}

	// PERMISO (20,2)
	/* actualizar estado de posicion estante */
	@RequestMapping(value="posestante",method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody PosicionEstante savePosEstante(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PosicionEstante dmr)
			throws IOException {
		PosicionEstante dmr2 =detDao.savePosEstante(dmr); 
		if(dmr2==null)
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return dmr2;
	}

	// PERMISO (21,2)
	/* actualizar estado de posicion pasillo */
	@RequestMapping(value="pospasillo",method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public PosisionPasillo savePosPasillo(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PosisionPasillo dmr)
			throws IOException {
		PosisionPasillo dmr2 =detDao.savePosPasillo(dmr); 
		if(dmr2==null)
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return dmr2;
	}

	// PERMISO (22,2)
	/* actualizar estado de posicion patio */
	@RequestMapping(value="pospatio",method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Patio savePosPatio(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Patio dmr)
			throws IOException {
		Patio dmr2 =detDao.saveEstadoPatio(dmr); 
		if(dmr2==null)
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return dmr2;
	}

	// PERMISO (23,1)
	/* obtener order retiro por id */
	@RequestMapping(value = "getordenretiro/{ordenretiro}", method = RequestMethod.GET)
	public @ResponseBody OrdenRetiro getOrdenRetiroById(@PathVariable String ordenretiro,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// actualizar detalle_bulk orden=ordenretiro
		detDao.prc_actualiza_orden(ordenretiro);
		OrdenRetiro ta = detDao.getOrdenRetiroById(Integer.parseInt(ordenretiro));
		if(ta == null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return ta;
	}
	
	/* obtener cliente por id */
	@RequestMapping(value = "getcliente/{cliente}", method = RequestMethod.GET)
	public @ResponseBody Cliente getClienteById(@PathVariable String cliente,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Cliente ta = detDao.getClienteById(cliente);
		if(ta == null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return ta;
	}
	
	
	/* obtener todos los detalles bulk de un bulk*/
		@RequestMapping(value = "getdetallebulk/{bulk}", method = RequestMethod.GET)
		public @ResponseBody List<DetalleBulkV> getAllDetalleBulkByBulkv(
				@PathVariable String bulk,
				HttpServletRequest request, HttpServletResponse response)
				throws IOException {
			List<DetalleBulkV> ta = detDao.getDetalleByBulk2(bulk);
			if(ta == null){
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			return ta;
		}
	
	/* obtener todos los detalles bulk */
	@RequestMapping(value = "getdetallebulk", method = RequestMethod.GET)
	public @ResponseBody List<DetalleBulkV> getAllDetalleBulk(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<DetalleBulkV> ta = detDao.getDetalles2();
		if(ta == null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return ta;
	}

    @RequestMapping(value = "fun_cantidad", method = RequestMethod.POST)
    public void getAllDetalleBulkB(
            @RequestParam String bulk,
            @RequestParam String producto,
            @RequestParam String cliente,
            @RequestParam String cantidad,
            @RequestParam String orden,
            @RequestParam String nlote,
            @RequestParam String fechavto,

            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Integer va = detDao.funCantidad(bulk, producto, cliente, cantidad, orden,nlote,fechavto);
        response.getWriter().write("{cantidad:1}");
    }

	@RequestMapping(value = "bulk-ubicados", method = RequestMethod.GET)
	public @ResponseBody List<CantidadBulkUbicados> geCantidadBulkUbicados(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return detDao.getCantidadBulkUbicados();
	}

	@RequestMapping(value = "bulk-ubicados/{deposito}", method = RequestMethod.GET)
	public @ResponseBody CantidadBulkUbicados geCantidadBulkUbicados(
			@PathVariable String deposito,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return detDao.getCantidadBulkUbicados(deposito);
	}

	@RequestMapping(value = "fun_reagrupacion", method = RequestMethod.POST)
	public void fun_reagrupacion_ws(
			@RequestParam String bulk,
			@RequestParam String producto,
			@RequestParam String cantidad,
			@RequestParam String bulkd,
            HttpServletRequest request, HttpServletResponse response) 
            		throws IOException {
        
		String usr = ControllerUtils.getUsuario(request,"0");
		List<Object[]> vResultado=detDao.fun_reagrupacion(bulk, producto, cantidad, bulkd,usr);
        response.getWriter().write("{ejecutado:"+vResultado.get(0)+"}");
	}
}



