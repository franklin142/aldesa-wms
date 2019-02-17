package org.aldesa.wms.controller;

import java.io.IOException;
import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aldesa.wms.data.*;
import org.aldesa.wms.model.*;
import org.aldesa.wms.controller.ControllerUtils;
import org.jboss.resteasy.util.HttpResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web/")
public class WebController {

	@Autowired
	DetMercRecDao detDao;

	@Autowired
	MercadRecibidaDao mercDao;

	@Autowired
	BulkDao bDao;

	@Autowired
    UsuarioDao uDao;

	@Autowired
	BodegaDao bodegaDao;

	@RequestMapping(value = "impresoras", method = RequestMethod.GET)
	public @ResponseBody List<Impresor> getImpresoras(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return bDao.getImpresores();
	}

	@RequestMapping(value = "barcode", method = RequestMethod.GET)
 	public ModelAndView getBarcode(@RequestParam String valor,
 			@RequestParam(required = false, defaultValue = "no") String force,
 			@RequestParam(defaultValue="") String cliente, @RequestHeader(value="User-Agent") String userAgent,
 			HttpServletRequest request, HttpServletResponse response) throws IOException{
 		ModelAndView mdl = new ModelAndView("barcode");
 
 		mdl.addObject("valor", valor);
 		mdl.addObject("cliente", cliente);
 		return mdl;
 	}
	
	
	
    @RequestMapping(value = "reubicacion", method = RequestMethod.GET)
    public ModelAndView reubicacion(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 2))
			return new ModelAndView("wms.sin_permiso");
        List<BulkActivos> retiros = bDao.getBulkActivos();
        ModelAndView mdl = new ModelAndView("reubicacion");
        mdl.addObject("lista_mercaderia",retiros);
        return mdl;
    }
    @RequestMapping(value = "reubicacion/{deposito}/", method = RequestMethod.GET)
    public ModelAndView reubicacion_deposito(
            @PathVariable String deposito,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 3))
			return new ModelAndView("wms.sin_permiso");
        List<BulkActivos> retiros = bDao.getBulkActivos(deposito);
        ModelAndView mdl = new ModelAndView("reubicacion");
        mdl.addObject("lista_mercaderia",retiros);
        mdl.addObject("deposito",deposito);
        return mdl;
    }
	@RequestMapping(value = "reporte/recibida_simple2/{deposito}/{vista}/{forma}/{recibido}/{entregado}/{observacion}/{cliente}", method = RequestMethod.GET)
	public ModelAndView getreporte_recepcion_simple(
			@PathVariable String deposito,
			@PathVariable String vista,
			@PathVariable String forma,
			@PathVariable String recibido,
			@PathVariable String entregado,
			@PathVariable String observacion,
			@PathVariable String cliente,
		HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 4))
			return new ModelAndView("wms.sin_permiso");
		bDao.autorizarUbicacion(forma, entregado, recibido, observacion, deposito, "S",cliente,"1");
		InformeIngreso inf = bDao.getInformeIngresoById(deposito);
        List<MercRecibida> ingresos = bDao.getMercRecibida(deposito);
        int tdeclarada=0, trecibida=0, tsaldo=0, taveriada=0;
        for(MercRecibida m:ingresos){
            tdeclarada = tdeclarada + Integer.parseInt(m.getDeclarada());
            trecibida = trecibida + Integer.parseInt(m.getRecibida());
            taveriada = taveriada + Integer.parseInt(m.getAveriada());
            tsaldo = tsaldo + Integer.parseInt(m.getSaldo());
        }
		ModelAndView mdl = new ModelAndView("reporte.recibida_simple");
        mdl.addObject("tipo", inf.getTipoDeposito());
		mdl.addObject("enc", inf);
		mdl.addObject("ingresos",ingresos);
        Calendar c = Calendar.getInstance();
        mdl.addObject("fecha", (new SimpleDateFormat("dd/MM/yyy")).format(new Date()));
        mdl.addObject("hora", (new SimpleDateFormat("HH:mm:ss")).format(c.getTime()));
        mdl.addObject("tdeclarada", tdeclarada);
        mdl.addObject("trecibida", trecibida);
        mdl.addObject("taveriada", taveriada);
        mdl.addObject("tsaldo", tsaldo);
		return mdl;
	}

	@RequestMapping(value = "reporte/recibida_simple/{deposito}/{vista}/{forma}/{recibido}/{entregado}/{observacion}/{cliente}", method = RequestMethod.GET)
	public ModelAndView c(
			@PathVariable String deposito,
			@PathVariable String vista,
			@PathVariable String forma,
			@PathVariable String recibido,
			@PathVariable String entregado,
			@PathVariable String observacion,
			@PathVariable String cliente,
			@RequestParam(required=false, defaultValue="-") String[] obs,
		HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 4))
			return new ModelAndView("wms.sin_permiso");
		List<InformeIngreso> observ = bDao.getInformeIngreso(deposito);
		
		if(observ.size()<1){
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "informe ingreso de deposito " + deposito +
					" inexistente");
			return null;
		}

		InformeIngreso inf = observ.get(0);
		if(inf.getTipoDeposito().equalsIgnoreCase("D"))
			for(Integer i=0; i<obs.length; i++)
				bDao.autorizarUbicacion(forma, entregado, recibido, obs[i],deposito, inf.getTipoDeposito(), cliente, i.toString());
		else
			bDao.autorizarUbicacion(forma, entregado, recibido, observacion,deposito, inf.getTipoDeposito(), cliente, "1");

		List<InformeIngreso> observ1 = bDao.getInformeIngreso(deposito);
		
        List<MercRecibida> ingresos = bDao.getMercRecibida(deposito);
        int tdeclarada = 0, trecibida = 0, tsaldo = 0, taveriada = 0;
        for (MercRecibida m : ingresos) {
            tdeclarada = tdeclarada + Integer.parseInt(m.getDeclarada());
            trecibida = trecibida + Integer.parseInt(m.getRecibida());
            taveriada = taveriada + Integer.parseInt(m.getAveriada());
            tsaldo = tsaldo + Integer.parseInt(m.getSaldo());
        }

        ModelAndView mdl = new ModelAndView("");
        

        String verificacionInforme = bDao.verificacionInforme(deposito);
        
        
        	if (inf.getTipoDeposito().equalsIgnoreCase("D")) {
                mdl = new ModelAndView("reporte.recibida_desconsolidado");
                mdl.addObject("enc", observ1);
            } else {
            	
            	if(verificacionInforme.equals("L")) {     
            	List<MercRecibidalV> ingresosL = bDao.getMercRecibidalV(deposito);
            	tdeclarada = 0; trecibida = 0; tsaldo = 0; taveriada = 0;
                for (MercRecibidalV m : ingresosL) {
                    tdeclarada = tdeclarada+Integer.parseInt(m.getDeclarada());
                    trecibida =  trecibida+Integer.parseInt(m.getRecibida());
                    taveriada = taveriada+Integer.parseInt(m.getAveriada());
                    tsaldo = tsaldo+Integer.parseInt(m.getSaldo());
                }
                mdl = new ModelAndView("reporte.recibida_simplel");
                mdl.addObject("ingresos", ingresosL);
                inf = observ1.get(0);
                mdl.addObject("enc", inf);
                mdl.addObject("cliente", cliente);
                
            	}
            	if(verificacionInforme.equals("A")) {
                    
            		mdl = new ModelAndView("reporte.recibida_simple2");    
            		mdl.addObject("ingresos", ingresos);

                    inf = observ1.get(0);
                    mdl.addObject("enc", inf);
                    mdl.addObject("cliente", cliente);
                }
            }
            mdl.addObject("tipo", inf.getTipoDeposito());
            Calendar c = Calendar.getInstance();
            mdl.addObject("fecha",(new SimpleDateFormat("dd/MM/yyy")).format(new Date()));
            mdl.addObject("hora", (new SimpleDateFormat("HH:mm:ss")).format(c.getTime()));
            mdl.addObject("tdeclarada", tdeclarada);
            mdl.addObject("trecibida", trecibida);
            mdl.addObject("taveriada", taveriada);
            mdl.addObject("tsaldo", tsaldo);
            mdl.addObject("deposito", deposito);
            mdl.addObject("orden", deposito);
            mdl.addObject("df3d", new DecimalFormat("####0.000"));
            mdl.addObject("df2d", new DecimalFormat("####0.00"));

        return mdl;
    }

	@RequestMapping(value = "autorizar_entrega_prc/{orden}/{cliente}/", method = RequestMethod.GET)
	public ModelAndView AutorizarEntrega(
			@PathVariable String orden,
			@PathVariable String cliente,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 37))
			return new ModelAndView("wms.sin_permiso");
		ModelAndView mdl = new ModelAndView("redirect:/web/arribos/");
		Date d = new Date();
		bDao.autorizarEntrega(Integer.parseInt(orden), d, cliente);
		return mdl;
	}
	@RequestMapping(value = "reporte/salida_simple2/{retiro}/{cliente}/", method = RequestMethod.GET)
	public ModelAndView getreporte_salida_simple(
			@PathVariable String retiro,
			@PathVariable String cliente,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 6))
			return new ModelAndView("wms.sin_permiso");
		InformeRetiro inf = bDao.getInformeById(retiro, cliente);
		List<DetalleInformeRetiro> retiros = bDao.getDetalleRetiro(retiro, cliente);
		ModelAndView mdl = new ModelAndView("reporte.salida_simple2");
        mdl.addObject("tipo", inf.getTipoDeposito());
		mdl.addObject("enc", inf);
        int tbultos=0, tordenada=0, tentregada=0;
        for(DetalleInformeRetiro dir:retiros){
            tbultos = tbultos + dir.getBultos();
            tentregada = tentregada + dir.getEntregada();
            tordenada = tordenada + dir.getCantidad();
        }
        Calendar c = Calendar.getInstance();
        mdl.addObject("fecha", (new SimpleDateFormat("dd/MM/yyy")).format(new Date()));
        mdl.addObject("hora", (new SimpleDateFormat("HH:mm:ss")).format(c.getTime()));
		mdl.addObject("retiros",retiros);
		mdl.addObject("retiro",retiro);
        mdl.addObject("tbultos", tbultos);
        mdl.addObject("tordenada", tordenada);
        mdl.addObject("tdiferencia", tentregada);
		return mdl;
	}

	@RequestMapping(value = "reporte/salida_simple/{retiro}/{cliente}/{tipo}/", method = RequestMethod.GET)
	public ModelAndView getreporte_salida_simple2(
			@PathVariable String retiro,
			@PathVariable String cliente,
			@PathVariable String tipo,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 6))
			return new ModelAndView("wms.sin_permiso");
		InformeRetiro inf = bDao.getInformeById(retiro, cliente);
		List<DetalleInformeRetiro> retiros = bDao.getDetalleRetiro(retiro, cliente);
		ModelAndView mdl;
		if (tipo.equalsIgnoreCase("D")){
			mdl = new ModelAndView("reporte.salida_desconsolidado");
		}else {
		    mdl = new ModelAndView("reporte.salida_simple2");
		}
		mdl.addObject("tipo", inf.getTipoDeposito());
		mdl.addObject("enc", inf);
		int tbultos=0, tordenada=0, tentregada=0;
         for(DetalleInformeRetiro dir:retiros){
            tbultos = tbultos + dir.getBultos();
        //    tentregada = tentregada + dir.getEntregada();
        //    tordenada = tordenada + dir.getCantidad();
        }
		  tentregada = inf.getTotEntreg();
          tordenada = inf.getTotSolic();
		Calendar c = Calendar.getInstance();
		mdl.addObject("fecha", (new SimpleDateFormat("dd/MM/yyy")).format(new Date()));
		mdl.addObject("hora", (new SimpleDateFormat("HH:mm:ss")).format(c.getTime()));
		mdl.addObject("retiros",retiros);
		mdl.addObject("retiro",retiro);
		mdl.addObject("tbultos", tbultos);
		mdl.addObject("tordenada", tordenada);
		mdl.addObject("tdiferencia", tentregada);
		return mdl;
	}
	@RequestMapping(value = "arribos_detalle_ver", method = RequestMethod.GET)
	public ModelAndView det_arribos_ver(@RequestParam String deposito, @RequestParam String cliente,
			 @RequestParam(required=false, defaultValue="") String msg,
             @RequestParam(required=false, defaultValue="S") String tipo,
			 HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 7))
			return new ModelAndView("wms.sin_permiso");
		Cliente cliente1 = bDao.getClienteById(cliente);
		List<MercRecibida> pendientes = bDao.getMercRecibida(deposito);
		int tdeclarada=0, trecibida=0, tsaldo=0, taveriada=0;
        for(MercRecibida m:pendientes){
            tdeclarada = tdeclarada + Integer.parseInt(m.getDeclarada());
            trecibida = trecibida + Integer.parseInt(m.getRecibida());
            taveriada = taveriada + Integer.parseInt(m.getAveriada());
            tsaldo = tsaldo + Integer.parseInt(m.getSaldo());
        }
		ModelAndView mv = new ModelAndView("arribos_detalle_ver");
		mv.addObject("pendientes", pendientes);
		mv.addObject("deposito", deposito);
		mv.addObject("cliente", cliente1);
		mv.addObject("ha_creado", false);
        mv.addObject("tdeclarada", tdeclarada);
        mv.addObject("trecibida", trecibida);
        mv.addObject("taveriada", taveriada);
        mv.addObject("tsaldo", tsaldo);
        mv.addObject("tipo", tipo);
        if(msg.equalsIgnoreCase("404"))
        	mv.addObject("msg", true);
		return mv;
	}

    @RequestMapping(value = "edit_bulk/{bulk}", method = RequestMethod.GET)
    public ModelAndView edit_det_arribos(
            @PathVariable String bulk,
            @RequestParam String cliente,
            @RequestParam String deposito,
            @RequestParam String tipo,
            HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 9))
			return new ModelAndView("wms.sin_permiso");
		if(deposito.contains(",")) deposito = deposito.split(",")[0];
		if(tipo.contains(",")) tipo = tipo.split(",")[0];
        Bulk b = bDao.getById(bulk);
        if (b==null){
        	return new ModelAndView("redirect:/web/arribos_detalle_ver?cliente="+cliente+"&deposito="+deposito+"&msg=404&tipo="+tipo);
        }
        List<DetalleBulkV> db = bDao.getDetalleByBulk2(bulk);
        Cliente  c = bDao.getClienteById(cliente);
        ModelAndView mv = new ModelAndView("edit_bulk");
        mv.addObject("bulk", b);
        mv.addObject("detalles", db);
        mv.addObject("tipo", tipo );
		mv.addObject("estados_mercaderia", bodegaDao.getAllEstadosMercancia());
        mv.addObject("cliente", c);
		if (tipo.equalsIgnoreCase("S")){
          mv.addObject("deposito", b.getDeposito());
          mv.addObject("cliented", c);
        }else {
          mv.addObject("deposito", b.getDepositoDesc());
          for (DetalleBulkV db1:db){
          mv.addObject("cliented", db1.getCliente());
          }
		}
        return mv;
    }

	@RequestMapping(value = "edit_bulk/", method = RequestMethod.GET)
	public ModelAndView edit_det_arribos2(
			@RequestParam String cliente,
			@RequestParam String deposito,
			@RequestParam String tipo,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 9))
			return new ModelAndView("wms.sin_permiso");
		if(deposito.contains(",")) deposito = deposito.split(",")[0];
		if(tipo.contains(",")) tipo = tipo.split(",")[0];
		return new ModelAndView("redirect:/web/arribos_detalle_ver?cliente="+cliente+"&deposito="+deposito+"&msg=404&tipo="+tipo);

	}
    @RequestMapping(value = "edit_bulk/{bulk}", method = RequestMethod.POST)
    public ModelAndView post_edit_det_arribos(
            @PathVariable String bulk,
            @RequestParam String alto,
            @RequestParam String ancho,
            @RequestParam String profundidad,
            @RequestParam String peso,
            @RequestParam String cliente,
			@RequestParam String deposito,
			@RequestParam String tipo,
            @RequestParam(required=false, defaultValue="-") String[] cod,
            @RequestParam(required=false, defaultValue="-") String[] porbulk,
            @RequestParam(required=false, defaultValue="-") String[] estado,
            @RequestParam(required=false, defaultValue="-") String[] flejada,
            @RequestParam(required=false, defaultValue="-") String[] sobrante,
            @RequestParam(required=false, defaultValue="") String[] item,
            @RequestParam(required=false, defaultValue="") String[] nolote,
            @RequestParam(required=false, defaultValue="") String[] fechavto,

            HttpServletRequest request, HttpServletResponse response)  throws Exception{
    	
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 9))
			return new ModelAndView("wms.sin_permiso");
		if(deposito.contains(",")) deposito = deposito.split(",")[0];
		if(tipo.contains(",")) tipo = tipo.split(",")[0];
		Bulk b = new Bulk();
		boolean nodet = false;
		if(cod.length==1 && cod[0].equalsIgnoreCase("-")) {
			nodet=true;
			porbulk = new String[0];
		}
		if(porbulk.length==1 && porbulk[0].equalsIgnoreCase("-")){
			nodet=true;
			 porbulk = new String[0];
		}
		if(estado.length==1 && estado[0].equalsIgnoreCase("-")){
			nodet=true;
			 porbulk = new String[0];
		}
		if(porbulk.length>0){
			if(flejada[0].equalsIgnoreCase("on"))
				b.setTarimaFlejada("S");
			else
				b.setTarimaFlejada("N");
			
			if(peso.trim().equalsIgnoreCase("") ) b.setPeso(null);
			else b.setPeso(Double.parseDouble(peso));
			
			if(ancho.trim().equalsIgnoreCase("") ) b.setPeso(null);
			else b.setAncho(Double.parseDouble(ancho));
			
			if(alto.trim().equalsIgnoreCase("")  ) b.setPeso(null);
			else b.setAlto(Double.parseDouble(alto));
			
			if(profundidad.trim().equalsIgnoreCase("") ) b.setProfundidad(null);
			else b.setProfundidad(Double.parseDouble(profundidad));
		}
		b.setCodigoBulk(bulk);
		bDao.save(b);
		if(!nodet) {
			for(int i=0; i< cod.length; i++){
				Date fechavtoConverted = new Date();
				
				bDao.updateDetalle(bulk, cod[i], Double.parseDouble(porbulk[i]), estado[i],nolote[i]);
				// actualizar sobrantes  b.getCliente()
				if(sobrante[i].equalsIgnoreCase("on")) {
					
					if (tipo.equalsIgnoreCase("D")){
					  bDao.actualizarSobrante(deposito,cod[i] , cod[i], estado[i], item[i],nolote[i],fechavtoConverted);
					}else {					  
						bDao.actualizarSobrante(deposito,cliente , cod[i], estado[i], item[i],nolote[i],fechavtoConverted);
					}
				}
			}
    	ModelAndView mv = new ModelAndView("edit_bulk");
        b = bDao.getById(bulk);
        List<DetalleBulk> db = bDao.getDetalleByBulk(bulk);
		}
		return new ModelAndView("redirect:/web/arribos_detalle_ver?cliente="+cliente+"&deposito="+deposito+"&tipo="+tipo);
    }

    @RequestMapping(value = "arribos_detalle", method = RequestMethod.GET)
	public ModelAndView det_arribos(
			@RequestParam(required=false, defaultValue="0") String editar,
			@RequestParam String deposito, 
			@RequestParam String recepcion, 
			@RequestParam String tipo,
			@RequestParam String cliente,
			@RequestParam(required=false, defaultValue="0") String lbl,
			@RequestParam String entrega, 
			@RequestParam(required=false, defaultValue="0") String numreg,
			@RequestParam(required=false, defaultValue=" ") String mensaje,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 11))
			return new ModelAndView("wms.sin_permiso");
		MercadRecibidaPK pk = new MercadRecibidaPK();
		pk.setDepositoNo(deposito);
		MercadRecibida dep1 = mercDao.getById(pk);
		Cliente tcliente = bDao.getClienteById(cliente);
		List<MercPendRecibir> pendientes = mercDao.getMercaderiaPendiente(deposito);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		if(pendientes.size()<=0 && editar.equalsIgnoreCase("0"))
			return new ModelAndView("redirect:/web/arribos_detalle_ver?cliente="+cliente+"&deposito="+deposito+"&tipo="+tipo);
		ModelAndView mv = new ModelAndView("arribos_detalle");
        boolean finalizado = false;
        if(!"00:00:00".equalsIgnoreCase(sdf.format(dep1.getHorafindescarga())))
            finalizado = true;
        mv.addObject("finalizado", finalizado);
		mv.addObject("recepcion", recepcion);
		mv.addObject("pendientes", pendientes);
		mv.addObject("deposito", deposito);
		mv.addObject("entrega", entrega);
		mv.addObject("cliente", tcliente);
		mv.addObject("tipo", tipo);
		mv.addObject("ha_creado", false);
		mv.addObject("estados_mercaderia", bodegaDao.getAllEstadosMercancia());
		mv.addObject("printed", false);
		mv.addObject("fprint", bDao.getParametrosWMS().getEtiquetaObligatoria());
		mv.addObject("mercrec", dep1);
		mv.addObject("lbl", lbl.equalsIgnoreCase("lbl1"));
		if (tipo.equalsIgnoreCase("D")){
		  if (numreg.equalsIgnoreCase("0")){
				mv.addObject("msg", false);		  	
				mv.addObject("mensaje", "");		  	
		  }else {
				mv.addObject("msg", true);		  	
				mv.addObject("mensaje", mensaje);		  	
		  }
		} else {
			mv.addObject("msg", false);		  	
			mv.addObject("mensaje", "");		  	
		}
		return mv;
	}
	
	@RequestMapping(value = "arribos_detalle", method = RequestMethod.POST)
	public ModelAndView det_arribos_nuevo(@RequestParam String deposito, 
			@RequestParam String _method,
			@RequestParam(required=false, defaultValue="") String codbulk,
            @RequestParam(required=false, defaultValue="") String fdescarga,
            @RequestParam(required=false, defaultValue="") String[] codMerc,
			@RequestParam(required=false, defaultValue="") String[] porbulk,
			@RequestParam(required=false, defaultValue="") String[] alto,
			@RequestParam(required=false, defaultValue="") String[] ancho,
			@RequestParam(required=false, defaultValue="") String[] nolote,
			@RequestParam(required=false, defaultValue="") String[] fechavto,
			@RequestParam(required=false, defaultValue="") String[] profund,
			@RequestParam(required=false, defaultValue="") String[] peso,
			@RequestParam(required=false, defaultValue="") String[] flejada,
			@RequestParam(required=false, defaultValue="") String[] sobrante,
			@RequestParam(required=false, defaultValue="") String[] estado,
			@RequestParam(required=false, defaultValue="") String[] medida,
			@RequestParam(required=false, defaultValue="") String[] descripcion,
			@RequestParam(required=false, defaultValue="") String[] saldo,
			@RequestParam(required=false, defaultValue="") String[] item,
			@RequestParam String tipo,
			@RequestParam(required=false, defaultValue="-") String consignatario,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 11))
			return new ModelAndView("wms.sin_permiso");
		MercadRecibidaPK pk = new MercadRecibidaPK();
		pk.setDepositoNo(deposito);
		MercadRecibida dep1 = mercDao.getById(pk);
		Cliente cliente = bDao.getClienteById(dep1.getCliente());
        List<BulkSugerido> bs = bDao.getSugerencia(deposito);
		if(_method.equalsIgnoreCase("put")){
			bDao.verificaBulk(deposito);
			Bulk b = new Bulk();
			b.setCodigoBulk("");
			if (tipo.equalsIgnoreCase("D")) {
				b.setDeposito(null);
				b.setDepositoDesc(deposito);
			}else {
				b.setDeposito(deposito);
				b.setDepositoDesc(null);
			}
			ModelAndView mv = new ModelAndView("arribos_detalle");
            if(bs.size()>0){
                b.setAlto(bs.get(0).getAlto());
                b.setAncho(bs.get(0).getAncho());
                b.setPeso(bs.get(0).getPeso());
                b.setProfundidad(bs.get(0).getProfundidad());
                b.setTarimaFlejada(bs.get(0).getTarimaFlejada());
    			mv.addObject("scantidad", bs.get(0).getCantidad());
    			mv.addObject("smerc", bs.get(0).getCodigoProducto());
    			mv.addObject("smercc", bs.get(0).getCliente());
            }
            else{
    			mv.addObject("scantidad", 0);
    			mv.addObject("smerc", null);
            }
			b = mercDao.saveBulk(b);
			List<MercPendRecibir> pendientes = mercDao.getMercaderiaPendiente(deposito);
			mv.addObject("bulk", b);
			mv.addObject("pendientes", pendientes);
			mv.addObject("cliente", cliente);
			mv.addObject("ha_creado", false);
			mv.addObject("tipo", tipo);
            mv.addObject("deposito", deposito);
			mv.addObject("printed", false);
			mv.addObject("estados_mercaderia", bodegaDao.getAllEstadosMercancia());
			return mv;			
		} else if (_method.equalsIgnoreCase("_put_det_bulk")){
			int i=0;
			Bulk b = new Bulk();
			if(porbulk.length>0){
				if(flejada.length>0 && flejada[0].equalsIgnoreCase("on"))
					b.setTarimaFlejada("S");
				else
					b.setTarimaFlejada("N");
				if(peso.length<=0 ) b.setPeso(null);
				else if(peso[0].trim().length()==0) b.setPeso(null);
				else b.setPeso(Double.parseDouble(peso[0]));
				
				if(ancho.length<=0 ) b.setAncho(null);
				else if(ancho[0].trim().length()==0) b.setAncho(null);
				else b.setAncho(Double.parseDouble(ancho[0]));
				
				if(alto.length<=0 ) b.setPeso(null);
				else if(alto[0].trim().length()==0) b.setAlto(null);
				else b.setAlto(Double.parseDouble(alto[0]));
				
				if(profund.length<=0 ) b.setPeso(null);
				else if(profund[0].trim().length()==0) b.setProfundidad(null);
				else b.setProfundidad(Double.parseDouble(profund[0]));
			}
			b.setCodigoBulk(codbulk);
			bDao.save(b);
            if(fdescarga.equalsIgnoreCase("on")){
                dep1.setHorafindescarga(Calendar.getInstance().getTime());
                if (tipo.equalsIgnoreCase("S")) {
                   bDao.updateFechaFinDescarga(dep1);
                }else {
                   bDao.updateFechaFinDescargaD(dep1);	
                }
            }
			List<MercPendRecibir> pendientes = new ArrayList<MercPendRecibir>();
			MercPendRecibir p;
			String mensaje=" ";
			String numregs="0";
			int numreg =0;
			for(i=0;i<porbulk.length;i++){
				if(porbulk[i].trim().length()==0) continue;
                if(Double.parseDouble(porbulk[i])<=0) continue;

                Date fechavtoConverted = new Date();
				try {
					fechavtoConverted = (Date) (fechavto[i]!=""?((new SimpleDateFormat("yyyy/MM/dd")).parse(fechavto[i])):(new SimpleDateFormat("yyyy/MM/dd")).format(new Date()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (tipo.equalsIgnoreCase("D")){
					if (consignatario.equalsIgnoreCase(codMerc[i])){
						bDao.prcInsertaDetBulk(codbulk,  codMerc[i], cliente.getCliente_No(),porbulk[i],estado[i],item[i],nolote[i],fechavtoConverted);

					}
				}else {					  

					bDao.prcInsertaDetBulk(codbulk,  codMerc[i], cliente.getCliente_No(),porbulk[i],estado[i],item[i],nolote[i],fechavtoConverted);

				}
				if(sobrante[i].equalsIgnoreCase("on")){
					
				   if (tipo.equalsIgnoreCase("D")){
					    bDao.actualizarSobrante(deposito, codMerc[i], codMerc[i], estado[i],item[i],nolote[i],fechavtoConverted);

				   }else {

						bDao.actualizarSobrante(deposito, cliente.getCliente_No(), codMerc[i], estado[i],item[i],nolote[i],fechavtoConverted);

				   }
				}
				p = new MercPendRecibir();
				p.setDeposito(deposito);
				p.setCodigo_Mercaderia(codMerc[i]);
				p.setCliente(cliente.getCliente_No());
				p.setDescripcion(descripcion[i]);
				p.setSaldo(Double.parseDouble(saldo[i])-Double.parseDouble(porbulk[i]));
				p.setUnidad_Medida(medida[i]);
				pendientes.add(p);
			   if (tipo.equalsIgnoreCase("D")){
				   numreg=numreg+1;
				   if (numreg>1) {
				     numregs="2";
				   }
				   mensaje=" Se indico mas de un consignatario para el Bulk "+codbulk+". Se almaceno el consignatario "+consignatario+". Favor Verificar";
			   }
				
			}
			pendientes = mercDao.getMercaderiaPendiente(deposito);

			if(pendientes.size()<=0)
				return new ModelAndView("redirect:/web/arribos_detalle_ver?cliente="+dep1.getCliente()+"&deposito="+deposito+"&tipo="+tipo);
			return det_arribos("0", deposito, "1", tipo,dep1.getCliente(), "1", "lbl1",numregs,mensaje, request, response);
		}
		return null;
	}

// agregar fecha y hora de fin de transito en recepcion

    @RequestMapping(value = "arribos_recepcion", method = RequestMethod.GET)
    public ModelAndView arribos_recepcion(@RequestParam String deposito, @RequestParam String cliente,
            @RequestParam(required = false, defaultValue = "") String tipo,
            @RequestParam(required = false, defaultValue = "0") String editar,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!ControllerUtils.isValidUser(request, uDao)) {
            return new ModelAndView("redirect:/rest/auth/login-web");
        }
        if (!ControllerUtils.isAllowed(request, uDao, 13)) {
            return new ModelAndView("wms.sin_permiso");
        }
        MercadRecibidaPK pk = new MercadRecibidaPK();
        pk.setDepositoNo(deposito);
        MercadRecibida dep = mercDao.getById(pk);
        MercRecibidaCompleta dep2 = bDao.getMercaderiaCompletaByDeposito(deposito);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if (!sdf.format(dep.getHoraautorizacioncortemarcham()).equalsIgnoreCase("00:00:00") && editar.equalsIgnoreCase("0")) {
            return new ModelAndView("redirect:/web/arribos_detalle/?deposito=" + deposito
                    + "&recepcion=" + dep.getMercadRecibidaPK().getRecepcionMercaderiaNo()
                    + "&entrega=" + dep.getMercadRecibidaPK().getEntregaNo()
                    + "&tipo=" + tipo + "&cliente=" + cliente + "&numreg=0&mensaje=");
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        List<Muelle> muelles = bodegaDao.getAllMuelle();
        List<Bodega> bodegas = bodegaDao.getAll();
        ModelAndView mv = new ModelAndView("arribos_recepcion");
        mv.addObject("mercaderia", dep);
        if (!sdf.format(dep.getHoraautorizacioncortemarcham()).equalsIgnoreCase("00:00:00")) {
            mv.addObject("hora", sdf1.format(dep.getHoraautorizacioncortemarcham()));
            mv.addObject("fecha", sdf2.format(dep.getFechadescarga()));
            mv.addObject("fechadt",sdf2.format(dep.getFechaFinalizatransito()));
            mv.addObject("horadt", sdf1.format(dep.getHoraFinalizatransito()));
        } else {
            mv.addObject("hora", sdf1.format(new Date()));
            mv.addObject("fecha", sdf2.format(new Date()));
              mv.addObject("fechadt",sdf2.format(new Date()));
            mv.addObject("horadt", sdf1.format(new Date()));
        }
        mv.addObject("tipo", tipo);
        mv.addObject("fecharecep", sdf2.format(new Date()));
        mv.addObject("obj2", dep2);
        if (editar.equalsIgnoreCase("1")) {
            mv.addObject("editar", true);
        } else {
            mv.addObject("editar", false);
        }
        mv.addObject("muelles", muelles);
        mv.addObject("bodegas", bodegas);
        return mv;
    }

    @RequestMapping(value = "arribos_recepcion", method = RequestMethod.POST)
    public ModelAndView arribos_recepcion_post(
            @RequestParam String deposito,
            @RequestParam String bodega,
            @RequestParam String muelle,
            @RequestParam String recepcion,
            @RequestParam String entrega,
            @RequestParam(required = false, defaultValue = "-") String dan,
            @RequestParam String hactualizacion,
            @RequestParam String factualizacion,
	    @RequestParam (required = false, defaultValue = "1901-01-01") String fftransito,	
	    @RequestParam (required = false, defaultValue = "00:00:00") String hftransito,
            @RequestParam String frecepcion,
            @RequestParam String descargado,
            @RequestParam(required = false, defaultValue = "") String tipo,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!ControllerUtils.isValidUser(request, uDao)) {
            return new ModelAndView("redirect:/rest/auth/login-web");
        }
        if (!ControllerUtils.isAllowed(request, uDao, 13)) {
            return new ModelAndView("wms.sin_permiso");
        }
        MercRecibidaCompleta dep = bDao.getMercaderiaCompletaByDeposito(deposito);
        ModelAndView mv = null;
        if (dep != null) {
            bDao.ActualizaMR1(deposito, dan, frecepcion, "0", descargado, factualizacion, hactualizacion, dep.getTipo(), bodega, muelle,fftransito,hftransito);
            mv = new ModelAndView("redirect:/web/arribos_detalle/?deposito=" + deposito + "&recepcion=" + recepcion + "&entrega=" + entrega + "&tipo=" + dep.getTipo() + "&cliente=" + dep.getCliente() + "&numreg=0&mensaje=");
        }
        return mv;
    }
	
	@RequestMapping(value = "arribos", method = RequestMethod.GET)
	public ModelAndView byId(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 15))
			return new ModelAndView("wms.sin_permiso");
		List<MercRecibidaCompleta> lista_mercaderia = mercDao.getAllFull();
		ModelAndView mdl = new ModelAndView("arribos");
		mdl.addObject("lista_mercaderia", lista_mercaderia);
        mdl.addObject("arribos", true);
		return mdl;
	}
	
	@RequestMapping(value = "recepcion_mercaderia", method = RequestMethod.GET)
	public ModelAndView recepcion_mercaderia(
			@RequestParam String deposito, 
			@RequestParam String cliente,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
        	if(!ControllerUtils.isValidUser(request, uDao))
    			return new ModelAndView("redirect:/rest/auth/login-web");
    		if(!ControllerUtils.isAllowed(request, uDao, 16))
    			return new ModelAndView("wms.sin_permiso");
    		List<MercRecibidaCompleta> lista_mercaderia = mercDao.getAllFull();
            List<Usuario> lista_jefes = uDao.getAll("A");
    		MercRecibidaCompleta dep = bDao.getMercaderiaCompletaByDeposito(deposito);
    		List<InformeIngreso> observ = bDao.getInformeIngreso(deposito);
    		if(observ.size()<1){
    			response.sendError(HttpServletResponse.SC_NOT_FOUND, "informe ingreso de deposito " + deposito +
    					" inexistente");
    			return null;
    		}
    		InformeIngreso inf = observ.get(0);

    	        ModelAndView mdl;

    	        if(inf.getTipoDeposito().equalsIgnoreCase("D")) {
    	        mdl = new ModelAndView("recepcion_mercaderia_desc");

    		mdl.addObject("observ", observ);

            }else {

                mdl = new ModelAndView("recepcion_mercaderia");
            }
    		mdl.addObject("recepcion_mercaderia", lista_mercaderia);
    		mdl.addObject("deposito", deposito);
    		mdl.addObject("cliente", cliente);
    		mdl.addObject("mercaderia", dep);
            mdl.addObject("jefes", lista_jefes);
            mdl.addObject("tipo", inf.getTipoDeposito());
    		if(inf!=null) {
    			mdl.addObject("inf", inf);
    			if(inf.getEntregado().length()>3) {
                   mdl.addObject("inf2", true);
    			}else
    	            mdl.addObject("inf2", false);
            }else
                mdl.addObject("inf2", false);
            mdl.addObject("usr", ControllerUtils.getUsuario(request, "0"));
    		return mdl;

	}
	
	@RequestMapping(value = "recepcion_mercaderia", method = RequestMethod.POST)
	public ModelAndView recepcion_mercaderia_post(
            @RequestParam (required =false, defaultValue = "S") String modo,
			@RequestParam (required =false, defaultValue = "S") String deposito[],
			@RequestParam (required =false, defaultValue = "S") String cliente[],
			@RequestParam (required =false, defaultValue = "S") String forma,
			@RequestParam(required =false, defaultValue = "") String entregado,
			@RequestParam(required =false, defaultValue = "") String recibido,
			@RequestParam(required =false, defaultValue = "") String observacion[],
			@RequestParam(required =false, defaultValue = "S") String tipo,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 1))
			return new ModelAndView("wms.sin_permiso");
        ModelAndView mdl = new ModelAndView("redirect:/web/autorizar_ubicacion");
        bDao.FinalizaRecepcion(deposito[0], tipo);
        
		return mdl;
	}

	@RequestMapping(value = "autorizar_ubicacion", method = RequestMethod.GET)
	public ModelAndView autorizar_ubicacion1(
            @RequestParam(required = false, defaultValue = "--1") String deposito,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(!ControllerUtils.isValidUser(request, uDao))
    		return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 18))
			return new ModelAndView("wms.sin_permiso");
		List<BulkPendAutorizar> lista_mercaderia = mercDao.getAllBulkPendientes();
        List<BulkPendAutorizar> lista = new ArrayList<BulkPendAutorizar>();
		if(deposito.trim().isEmpty()) deposito="--1";
		for(BulkPendAutorizar bpa:lista_mercaderia){
            if(deposito.equalsIgnoreCase("--1")) lista.add(bpa);
            else if(deposito.trim().equalsIgnoreCase(bpa.getDeposito().trim()))
                lista.add(bpa);
        }
		if(deposito.equalsIgnoreCase("--1")) deposito=null;
		ModelAndView mdl = new ModelAndView("autorizar_ubicacion");
		mdl.addObject("deposito", deposito);
		mdl.addObject("pendientes", lista);
		mdl.addObject("autorizar", false);
        mdl.addObject("ubicacion", true);
		mdl.addObject("index", true);
		
		return mdl;
	}

	@RequestMapping(value = "autorizar_ubicacion/consultar", method = RequestMethod.GET)
	public ModelAndView autorizar_ubicacion2(
			@RequestParam(required = false, defaultValue = "--1") String deposito,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 19))
			return new ModelAndView("wms.sin_permiso");
		ModelAndView mdl = new ModelAndView("autorizar_ubicacion_consultar");
		if(deposito.equalsIgnoreCase("--1")){
			mdl.addObject("deposito",null);
			mdl.addObject("tbulks",0);
			mdl.addObject("tbulkstemp",0);
			mdl.addObject("tbulksdef",0);
			mdl.addObject("ubicaciones", new ArrayList<BulkCreadosRes>());
		}else{
			mdl.addObject("deposito", deposito);
			List<BulkCreadosRes> bc = bDao.getBulkCreadosRes(deposito);
			if(bc.size()==0){
				mdl.addObject("tbulks",0);
				mdl.addObject("tbulkstemp",0);
				mdl.addObject("tbulksdef",0);
			} else {
				int tbulks=bc.size(), tbulkstemp=0, tbulksdef=0;
				mdl.addObject("tbulks",tbulks);
				mdl.addObject("tbulkstemp",tbulkstemp);
				mdl.addObject("tbulksdef",tbulksdef);
				mdl.addObject("ubicaciones", bc);
			}
		}
		return mdl;
	}

	@RequestMapping(value = "autorizar_ubicacion/autorizar", method = RequestMethod.GET)
	public ModelAndView autorizar_ubicacion(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 20))
			return new ModelAndView("wms.sin_permiso");
		List<BulkPendAutorizar> lista_mercaderia = mercDao.getAllBulkPendientes();
		ModelAndView mdl = new ModelAndView("autorizar_ubicacion");
		mdl.addObject("pendientes", lista_mercaderia);
		mdl.addObject("autorizar", true);
		return mdl;
	}

	@RequestMapping(value = "autorizar_ubicacion/autorizar", method = RequestMethod.POST)
	public ModelAndView autorizar_ubicacion_post(
			@RequestParam(name="depositos", defaultValue="") String[] depositos,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 20))
			return new ModelAndView("wms.sin_permiso");
		List<Bulk> bs;
		for(String d:depositos){
			bs = bDao.bulkByDeposito(d);
			for(Bulk b: bs){
				b.setEstado("P");
				bDao.save(b);
			}
		}
		List<BulkPendAutorizar> lista_mercaderia = mercDao.getAllBulkPendientes();
		ModelAndView mdl = new ModelAndView("autorizar_ubicacion");
		mdl.addObject("pendientes", lista_mercaderia);
		mdl.addObject("autorizar", true);
		return mdl;
	}
	
	@RequestMapping(value = "consulta_ubicacion", method = RequestMethod.GET)
	public ModelAndView consulta_ubicacion(
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 22))
			return new ModelAndView("wms.sin_permiso");
		List<BulkPendAutorizar> lista_mercaderia = mercDao.getAllBulkPendientes();
		ModelAndView mdl = new ModelAndView("autorizar_ubicacion");
		mdl.addObject("pendientes", lista_mercaderia);
        mdl.addObject("consulta", true);
        mdl.addObject("deposito", "");
		return mdl;
	}

	@RequestMapping(value = "reubicacion_mercaderia/{deposito}/", method = RequestMethod.GET)
	public ModelAndView reubicacion_mercaderia_update(
			@PathVariable String deposito,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(!ControllerUtils.isAllowed(request, uDao, 23))
			return new ModelAndView("wms.sin_permiso");
		return new ModelAndView("redirect:/web/reubicacion_mercaderia/");
	}
	
	@RequestMapping(value = "reubicacion_mercaderia/{deposito}/{fecha}/", method = RequestMethod.GET)
	public ModelAndView reubicacion_mercaderia_update(
			@PathVariable String deposito,

			@PathVariable String fecha,
            HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 24))
			return new ModelAndView("wms.sin_permiso");
		bDao.PRC_Actualiza_MR6(deposito, fecha);
		return new ModelAndView("redirect:/web/reubicacion_mercaderia/");
	}
	
	@RequestMapping(value = "reubicacion_mercaderia", method = RequestMethod.GET)
	public ModelAndView reubicacion_mercaderia(
            @RequestParam(name="deposito", required = false, defaultValue ="") String deposito,
            HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 25))
			return new ModelAndView("wms.sin_permiso");


        List<BulkActivos> lista_mercaderia;
        if(deposito.equalsIgnoreCase(""))
            lista_mercaderia = bDao.getBulkActivos();
        else
		    lista_mercaderia = bDao.getBulkActivos(deposito);
        ModelAndView mdl = new ModelAndView("reubicacion_mercaderia");
        mdl.addObject("lista_mercaderia",lista_mercaderia);
        mdl.addObject("deposito",deposito);
		return mdl;
	}
	
	@RequestMapping(value = "despacho", method = RequestMethod.GET)
	public ModelAndView despacho(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 26))
			return new ModelAndView("wms.sin_permiso");
		List<MercPendPreparar> lista_mercaderia = bDao.despachoGetAllPendientes();
		ModelAndView mdl = new ModelAndView("despacho");
		mdl.addObject("recepcion_mercaderia", lista_mercaderia);
		mdl.addObject("despacho", true);
        mdl.addObject("nolink", false);
		return mdl;
	}
	
	/* para  presentar el detalle de la preparacion */
	@RequestMapping(value = "preparacion/{orden}/{tipo}/{cliente}/{pentrega}/", method = RequestMethod.GET)
	public ModelAndView preparacion_orden(
			@PathVariable int orden,
			@PathVariable String tipo,
			@PathVariable String cliente,
			@PathVariable String pentrega,
			@RequestParam(required = false, defaultValue ="N") String autorizado,
			@RequestParam(required = false, defaultValue ="N") String showbtn2,
			@RequestParam(required = false, defaultValue ="--") String cod_cliente,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 27))
			return new ModelAndView("wms.sin_permiso");
		List<DetalleMercPendPreparar> lista_mercaderia = bDao.despachoGetAllDetallePendientes(orden,cliente);
		ModelAndView mdl = new ModelAndView("despacho");
		mdl.addObject("recepcion_mercaderia", lista_mercaderia);
		mdl.addObject("orden", orden);
		mdl.addObject("tipo", tipo);
		mdl.addObject("cliente", cliente);
		mdl.addObject("cod_cliente", cod_cliente);
		mdl.addObject("pentrega", pentrega);
		mdl.addObject("autorizar", true);
		if(pentrega.equalsIgnoreCase("S"))
			mdl.addObject("entrega1", true);
		else
			mdl.addObject("entrega1", false);
		if(autorizado.equalsIgnoreCase("N"))
			mdl.addObject("autorizar2", true);
		else
			if(autorizado.equalsIgnoreCase("1"))
				mdl.addObject("autorizar2", true);
			else
			   mdl.addObject("autorizar2", false);
		if(showbtn2.equalsIgnoreCase("N"))
			mdl.addObject("showbtn2", false);
		else
			mdl.addObject("showbtn2", true);
        mdl.addObject("nolink", true);
        return mdl;
	}

	/* para  presentar el detalle del despacho */
	@RequestMapping(value = "despacho/{orden}/{tipo}/{cliente}/{pentrega}/", method = RequestMethod.GET)
	public ModelAndView despacho_orden(
			@PathVariable int orden,
			@PathVariable String tipo,
			@PathVariable String cliente,
			@PathVariable String pentrega,
			@RequestParam(required = false, defaultValue ="N") String autorizado,
			@RequestParam(required = false, defaultValue ="N") String showbtn2,
			@RequestParam(required = false, defaultValue ="--") String cod_cliente,
			@RequestParam(required = false, defaultValue ="--") String desc_estado,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 32))
			return new ModelAndView("wms.sin_permiso");
		ModelAndView mdl = new ModelAndView("detalle_entrega_merc");
		if(desc_estado.equalsIgnoreCase("Pendiente de Entrega")||desc_estado.equalsIgnoreCase("Parcialmente Entregada")){
			  List<DetalleMercPendPreparar> lista_mercaderia = bDao.despachoGetAllDetallePendientes(orden, cliente);
			  mdl.addObject("pendiente", true);
			  mdl.addObject("recepcion_mercaderia", lista_mercaderia);	  
			  mdl.addObject("salida", lista_mercaderia.get(0).getnSalida());
		}else		{
			  mdl.addObject("pendiente", false);
		}
		mdl.addObject("orden", orden);
		mdl.addObject("tipo", tipo);
		mdl.addObject("cliente", cliente);
		mdl.addObject("cod_cliente", cod_cliente);
		mdl.addObject("pentrega", pentrega);
		mdl.addObject("autorizar", true);
		if(pentrega.equalsIgnoreCase("S"))
			mdl.addObject("entrega1", true);
		else
			mdl.addObject("entrega1", false);
		if(autorizado.equalsIgnoreCase("N"))
			mdl.addObject("autorizar2", true);
		else
			if(autorizado.equalsIgnoreCase("1"))
				mdl.addObject("autorizar2", true);
			else
			   mdl.addObject("autorizar2", false);
		if(showbtn2.equalsIgnoreCase("N"))
			mdl.addObject("showbtn2", false);
		else
			mdl.addObject("showbtn2", true);
        mdl.addObject("nolink", true);
		mdl.addObject("pantalla2", true);
        return mdl;
	}

    @RequestMapping(value = "despacho2/reporte/{orden}/{tipo}/{cliente}/", method = RequestMethod.GET)
    public ModelAndView despacho_orden_informe(
            @PathVariable int orden,
            @PathVariable String tipo,
            @PathVariable String cliente,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 28))
			return new ModelAndView("wms.sin_permiso");
        List<String> depositos = new ArrayList<String>();
        List<OrdenEntrega> ordenes  =bDao.getOrdenEntrega(orden,cliente);
        HashMap<String, OrdenEntrega> ordenesHash = new HashMap<String, OrdenEntrega>();
        HashMap<String, Integer> tdepositos = new HashMap<String, Integer>();
		HashMap<String, Integer> subtotalessol = new HashMap<String, Integer>();
		HashMap<String, Integer> subtotalesprep = new HashMap<String, Integer>();
        Integer tcantidad = 0;
        String depo, codigo;
		Integer nvalsol, nvalprep;
		String subkey;
        for (OrdenEntrega ord:ordenes){
			subkey = ord.getDeposito()+ord.getCodigo();

			if(subtotalesprep.containsKey(subkey)) nvalprep=0;
			else nvalprep = subtotalesprep.get(subkey);
			nvalprep += ord.getCantidadPreparada();
			if(subtotalessol.containsKey(subkey)) nvalsol=0;
			else nvalsol = subtotalessol.get(subkey);
			nvalsol += ord.getCantidad();

			if(subtotalessol.containsKey(subkey))
				subtotalessol.remove(subkey);
			subtotalessol.put(subkey, nvalsol);
			if(subtotalesprep.containsKey(subkey))
				subtotalesprep.remove(subkey);
			subtotalesprep.put(subkey, nvalprep);

            depo = ord.getDeposito();
			codigo = ord.getCodigo();
            if(!depositos.contains(depo))
                depositos.add(depo);
            if(ordenesHash.containsKey(depo+codigo+ord.getCorrelativo())){
                OrdenEntrega o = ordenesHash.get(depo+codigo+ord.getCorrelativo());
                if(ord.getEstante() == null) continue;
                o.setEstante(o.getEstante() + " <br />" +ord.getEstante());
            }
            else{
                ordenesHash.put(depo+codigo+ord.getCorrelativo(), ord);
                tcantidad += ord.getCantidad();
            }
        }
		for(OrdenEntrega ord:ordenesHash.values()){
			if(! tdepositos.containsKey(ord.getDeposito())){
				tdepositos.put(ord.getDeposito(), ord.getCantidadDeposito().intValue());
			}
		}
        ModelAndView mdl = new ModelAndView("reporte.orden_preparacion");
        mdl.addObject("recepcion_mercaderia", ordenesHash.values());
        mdl.addObject("fecha", (new SimpleDateFormat("dd/MM/yyy HH:mm:ss")).format(new Date()));
		mdl.addObject("orden", orden);
        mdl.addObject("ordenobj", ordenes.get(0));
        mdl.addObject("tipo", tipo);
        mdl.addObject("autorizar", true);
        mdl.addObject("nolink", true);
        mdl.addObject("depositos", depositos);
        mdl.addObject("tcantidad", tcantidad);
        mdl.addObject("tdepositos", tdepositos);
		mdl.addObject("subtotsol", subtotalessol);
		mdl.addObject("subtotpre", subtotalesprep);
        return mdl;
    }
	//REPORTE ORDEN DE PREPARACION
	@RequestMapping(value = "preparacion/reporte/{orden}/{tipo}/{cliente}/", method = RequestMethod.GET)
	public ModelAndView despacho_orden_informe2(
			@PathVariable int orden,
			@PathVariable String tipo,
			@PathVariable String cliente,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 29))
			return new ModelAndView("wms.sin_permiso");
		ModelAndView mdl = new ModelAndView("reporte.orden_preparacion3");
		List<String> depositos = new ArrayList<String>();
		List<OrdenEntrega> ordenes  =bDao.getOrdenEntrega(orden,cliente);
		HashMap<String, OrdenEntrega> ordenesHash = new HashMap<String, OrdenEntrega>();
		List<Object[]> stdepositos = bDao.getSubtotalesOrdenEntrega(orden,cliente);
        List<Object[]> tdepositos = bDao.getTotalesOrdenEntrega(orden,cliente);
		Integer tcantidad = 0, tpreparada=0, tsaldo=0;
		String depo, codigo;
		for (OrdenEntrega ord:ordenes){
			if (ord == null) continue;
			depo = ord.getDeposito();
			codigo = ord.getCodigo();
			if(!depositos.contains(depo))
				depositos.add(depo);
			if(ordenesHash.containsKey(depo+codigo+ord.getCorrelativo())){
				OrdenEntrega o = ordenesHash.get(depo+codigo+ord.getCorrelativo());
				if(ord.getEstante() == null) continue;
				o.setEstante(o.getEstante() + "\\n" +ord.getEstante());
                o.setEstadoMercaderia(o.getEstadoMercaderia() + "\\n" + ord.getEstadoMercaderia());
                o.setTipoUbicacion(o.getTipoUbicacion() + "\\n" + ord.getTipoUbicacion());
                o.setSaldos(o.getSaldos() + "\\n" + ord.getSaldo().toString());
				o.setPreparadas(o.getPreparadas() + "\\n"+ord.getCantidadPreparada().toString());
                o.setCodigoBulk(o.getCodigoBulk() + "\\n"+ord.getCodigoBulk());
				o.setStatusUbicacion(o.getStatusUbicacion() + "\\n"+ord.getStatusUbicacion());
			}
			else{
                ord.setSaldos(""+ord.getSaldo().toString());
                ord.setPreparadas(""+ord.getCantidadPreparada().toString());
				ordenesHash.put(depo+codigo+ord.getCorrelativo(), ord);
			}
		}
		for(Object[] t:tdepositos) {
			tcantidad += Integer.parseInt(""+t[2]);
			tpreparada += Integer.parseInt(""+t[3]);
			tsaldo += Integer.parseInt(""+t[4]);
		}
		mdl.addObject("recepcion_mercaderia", ordenesHash.values());
		mdl.addObject("fecha", (new SimpleDateFormat("dd/MM/yyy HH:mm:ss")).format(new Date()));
		mdl.addObject("orden", orden);
		mdl.addObject("ordenobj", ordenes.get(0));
		mdl.addObject("tipo", tipo);
		mdl.addObject("autorizar", true);
		mdl.addObject("nolink", true);
		mdl.addObject("depositos", depositos);
		mdl.addObject("tcantidad", tcantidad);
		mdl.addObject("tpreparada", tpreparada);
		mdl.addObject("tsaldo", tsaldo);
		mdl.addObject("tdepositos", tdepositos);
		mdl.addObject("stdepositos", stdepositos);
        return mdl;
		
	}

	//REPORTE ORDEN PREPARADA
	@RequestMapping(value = "despacho/reporte/{orden}/{tipo}/{cliente}/", method = RequestMethod.GET)
	public ModelAndView despacho_orden_informe3(
			@PathVariable int orden,
			@PathVariable String tipo,
			@PathVariable String cliente,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
			ModelAndView mdl = new ModelAndView("reporte.orden_preparada");

			if(!ControllerUtils.isValidUser(request, uDao))
				return new ModelAndView("redirect:/rest/auth/login-web");
			if(!ControllerUtils.isAllowed(request, uDao, 30))
				return new ModelAndView("wms.sin_permiso");
			List<String> depositos = new ArrayList<String>();

			List<OrdenEntrega> ordenes  =bDao.getOrdenEntregaP(orden,cliente);

			HashMap<String, OrdenEntrega> ordenesHash = new HashMap<String, OrdenEntrega>();
			List<Object[]> stdepositos = bDao.getSubtotalesOrdenEntregaP(orden,cliente);
	        List<Object[]> tdepositos = bDao.getTotalesOrdenEntregaP(orden,cliente);
	        Integer tcantidad = 0, tpreparada=0, tsaldo=0, tentregada=0;
			String depo, codigo;
			for (OrdenEntrega ord:ordenes){
				if (ord == null) continue;
				depo = ord.getDeposito();
				codigo = ord.getCodigo();
				if(!depositos.contains(depo))
					depositos.add(depo);
				if(ordenesHash.containsKey(depo+codigo+ord.getCorrelativo())){
					OrdenEntrega o = ordenesHash.get(depo+codigo+ord.getCorrelativo());
					if(ord.getEstante() == null) continue;
					o.setEstante(o.getEstante() + "\\n" +ord.getEstante());
	                o.setEstadoMercaderia(o.getEstadoMercaderia() + "\\n" + ord.getEstadoMercaderia());
	                o.setTipoUbicacion(o.getTipoUbicacion() + "\\n" + ord.getTipoUbicacion());
	                o.setSaldos(o.getSaldos() + "\\n" + ord.getSaldo().toString());
					o.setPreparadas(o.getPreparadas() + "\\n"+ord.getCantidadPreparada().toString());
					o.setEntregadas(o.getEntregadas() + "\\n"+ord.getEntregada().toString());
	                o.setCodigoBulk(o.getCodigoBulk() + "\\n"+ord.getCodigoBulk());
					o.setStatusUbicacion(o.getStatusUbicacion() + "\\n"+ord.getStatusUbicacion());
				}
				else{
	                ord.setSaldos(""+ord.getSaldo().toString());
	                ord.setPreparadas(""+ord.getCantidadPreparada().toString());
	                ord.setEntregadas(""+ord.getEntregada().toString());
					ordenesHash.put(depo+codigo+ord.getCorrelativo(), ord);
				}
			}
			for(Object[] t:tdepositos) {
				tcantidad += Integer.parseInt(""+t[2]);
				tpreparada += Integer.parseInt(""+t[3]);
				tsaldo += Integer.parseInt(""+t[4]);
				tentregada += Integer.parseInt(""+t[5]);
			}
			mdl.addObject("recepcion_mercaderia", ordenesHash.values());
			mdl.addObject("fecha", (new SimpleDateFormat("dd/MM/yyy HH:mm:ss")).format(new Date()));
			mdl.addObject("orden", orden);
			mdl.addObject("ordenobj", ordenes.get(0));
			mdl.addObject("tipo", tipo);
			mdl.addObject("autorizar", true);
			mdl.addObject("nolink", true);
			mdl.addObject("depositos", depositos);
			mdl.addObject("tcantidad", tcantidad);
	        mdl.addObject("tpreparada", tpreparada);
	        mdl.addObject("tentregada", tentregada);
	        mdl.addObject("tsaldo", tsaldo);
	        mdl.addObject("tdepositos", tdepositos);
	        mdl.addObject("stdepositos", stdepositos);	        
		return mdl;
	}

	
	// REPORTE ORDEN REINGRESO
	@RequestMapping(value = "orden_reingreso/reporte/{orden}/{tipo}/{cliente}/", method = RequestMethod.GET)
	public ModelAndView orden_reingreso_informe(
			@PathVariable int orden,
			@PathVariable String tipo,
			@PathVariable String cliente,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 31))
			return new ModelAndView("wms.sin_permiso");
		List<String> depositos = new ArrayList<String>();
		List<DetalleMercPendPreparar> ordenes  = bDao.despachoGetAllDetallePendientesP(orden, cliente);
		HashMap<String, DetalleMercPendPreparar> ordenesHash = new HashMap<String, DetalleMercPendPreparar>();
		Integer tcantidad = 0, tpreparada=0, tsaldo=0;
		List<Object[]> totales = bDao.getSubtotalesOrdenReingreso(orden,cliente);
		List<Object[]> subtotales = bDao.getTotalesOrdenReingreso(orden,cliente);
		String depo, codigo;
		for (DetalleMercPendPreparar ord:ordenes) {
			depo = ord.getDeposito();
			if (!depositos.contains(depo))
				depositos.add(depo);
		}
		ModelAndView mdl = new ModelAndView("reporte.orden_reingreso");
		mdl.addObject("recepcion_mercaderia", ordenes);
		mdl.addObject("fecha", (new SimpleDateFormat("dd/MM/yyy HH:mm:ss")).format(new Date()));
		mdl.addObject("orden", orden);
		mdl.addObject("ordenobj", ordenes.get(0));
		mdl.addObject("pendientes", ordenes);
		mdl.addObject("tipo", tipo);
		mdl.addObject("autorizar", true);
		mdl.addObject("depositos", depositos);
		mdl.addObject("subtotales", subtotales);
		mdl.addObject("totales", totales.get(0));
		return mdl;
	}


	@RequestMapping(value = "despacho/{orden}/{tipo}/{cliente}/{pentrega}/", method = RequestMethod.POST)
	public ModelAndView despacho_orden_post(
            @PathVariable int orden,
			@PathVariable String tipo,
			@PathVariable String cliente,
			@PathVariable String pentrega,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 32))
			return new ModelAndView("wms.sin_permiso");
		bDao.autorizarDespachoOrden(orden, tipo);
		ModelAndView mdl = new ModelAndView("redirect:/web/despacho");
		return mdl;
	}

	@RequestMapping(value = "despacho/{orden}/{tipo}", method = RequestMethod.POST)
	public ModelAndView despacho_orden_post2(
			@PathVariable int orden,
			@PathVariable String tipo,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 33))
			return new ModelAndView("wms.sin_permiso");
		bDao.autorizarDespachoOrden(orden, tipo);
		ModelAndView mdl = new ModelAndView("redirect:/web/despacho");
		return mdl;
	}
	// select Fecha, hora, Cliente, Nombre, orden from Merc_Pend_Despachar_v;
	
	@RequestMapping(value = "autorizar_entrega", method = RequestMethod.GET)
	public ModelAndView autorizar_entrega(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 34))
			return new ModelAndView("wms.sin_permiso");
		List<MercPendDespachar> lista_mercaderia = bDao.despachoGetAllPendientesDespachar();
		ModelAndView mdl = new ModelAndView("despacho");
		mdl.addObject("recepcion_mercaderia", lista_mercaderia);
		mdl.addObject("despacho", true);
		mdl.addObject("entrega", true);
		mdl.addObject("nolink", false);
		mdl.addObject("autorizarentrega1", true);
		return mdl;
	}


	@RequestMapping(value = "detalle_complemento/{orden}/{cliente}", method = RequestMethod.GET)
	public ModelAndView detalle_complemento(
		@PathVariable Integer orden,
        @PathVariable String cliente,
		HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 35))
			return new ModelAndView("wms.sin_permiso");
		List<InformeRetiro> trafico = bDao.getInformeRetiroList(orden.toString(), cliente);
        List<Usuario> lista_jefes = uDao.getAll("A");
		ModelAndView mdl = new ModelAndView("retiro_complemento");
		if(trafico.size()<1){
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se ha ingresado informacion en trafico." 
				 +" Favor Verificar "+trafico.size());
			return null;
		}
		InformeRetiro inf = bDao.getInformeById(orden.toString(), cliente);
		List<String> vResult=bDao.validaEntrega(orden.toString(), cliente);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        if(!sdf.format(inf.getHora_fin_descarga()).equalsIgnoreCase("00:00:00")) {
        	mdl.addObject("horac", sdf1.format(inf.getHora_de_carga()));
        	mdl.addObject("fechac", sdf2.format(inf.getFecha_Inicio_descarga()));
        	mdl.addObject("horad", sdf1.format(inf.getHora_fin_descarga()));
        	mdl.addObject("fechad", sdf2.format(inf.getFecha_fin_descarga()));
        }else {
        	mdl.addObject("horac", sdf1.format(new Date()));
        	mdl.addObject("fechac", sdf2.format(new Date()));
        	mdl.addObject("horad", sdf1.format(new Date()));
        	mdl.addObject("fechad", sdf2.format(new Date()));
        }
		
		mdl.addObject("tipo", inf.getTipoDeposito());
		mdl.addObject("retiros",inf);
		mdl.addObject("retiro",orden);
		mdl.addObject("cliente",cliente);
		mdl.addObject("cod_cliente", cliente);
		mdl.addObject("jefes", lista_jefes);
		if (vResult.get(0)!="1"){
			mdl.addObject("incompleto", true);
			mdl.addObject("resultado", vResult.get(0));
		}else {
			mdl.addObject("incompleto", false);
			mdl.addObject("resultado", vResult.get(0));
		}
		mdl.addObject("autorizar2", false);
		if(inf!=null) {
			if(inf.getEntregado().length()>3) {
               mdl.addObject("inf2", true);
			}else
	            mdl.addObject("inf2", false);
        }else
            mdl.addObject("inf2", false);
        mdl.addObject("usr", ControllerUtils.getUsuario(request, "0"));
		return mdl;
	}

	@RequestMapping(value = "detalle_complemento/{orden}/{cliente}/", method = RequestMethod.POST)
	public ModelAndView detalle_complemento_post(
			@PathVariable Integer orden,
			@PathVariable String cliente,
			@RequestParam String cargado,
			@RequestParam String salida,
			@RequestParam String forma,
			@RequestParam String entregado,
			@RequestParam String ficarga,
			@RequestParam String hicarga,
			@RequestParam String ffcarga,
			@RequestParam String hfcarga,
			@RequestParam (required = false, defaultValue ="Sin especificar") String autorizada,
			@RequestParam String observaciones,
			@RequestParam String tipo,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 35))
			return new ModelAndView("wms.sin_permiso");
		bDao.prc_entrega_merc(orden.toString(), salida, cliente, (new Date()).toString(), cargado,forma, entregado,
				ficarga, hicarga, ffcarga, hfcarga, autorizada, observaciones);
		return new ModelAndView("redirect:/web/reporte/salida_simple/"+orden+"/"+cliente+"/"+tipo+"/");
	}

	 // NUEVA FUNCION 2017 
    @RequestMapping(value = "autorizar_entrega_producto_post/{orden}/{cliente}", method = RequestMethod.POST)
    public ModelAndView autorizar_entrega_producto_post(
            @PathVariable Integer orden,
            @PathVariable String cliente,
            @RequestParam String cod_cliente,
            @RequestParam String codigo_prod,
            @RequestParam String estado_prod,
            @RequestParam String cantidad,
            @RequestParam String pendiente,
            @RequestParam String deposito,
            @RequestParam String bulk,
            @RequestParam String nSalida,
            @RequestParam String nlote,
            @RequestParam String fechavto,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!ControllerUtils.isValidUser(request, uDao)) {
            response.sendError(HttpResponseCodes.SC_FORBIDDEN, "Please login");
            return null; // new ModelAndView("redirect:/rest/auth/login-web");
        }
        if (!ControllerUtils.isAllowed(request, uDao, 35)) {
            response.sendError(HttpResponseCodes.SC_FORBIDDEN, "Please login");
            return null; //new ModelAndView("wms.sin_permiso");
        }
        Date fechavtoConverted = new Date();
		try {
			fechavtoConverted = (Date) (fechavto!=null?((new SimpleDateFormat("dd/MM/yyy")).parse(fechavto)):(new SimpleDateFormat("dd/MM/yyy HH:mm:ss")).format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        bDao.prc_entrega(orden.toString(), nSalida, codigo_prod, cantidad, estado_prod, bulk, cod_cliente,nlote,fechavtoConverted);
        return null;
    }

    @RequestMapping(value = "autorizar_entrega/{orden}/{cliente}", method = RequestMethod.POST)
    public ModelAndView autorizar_entrega_post(
            @PathVariable Integer orden,
            @PathVariable String cliente,
            @RequestParam String cod_cliente[],
            @RequestParam(required = false, defaultValue = "--") String[] codigo_prod,
            @RequestParam(required = false, defaultValue = "--") String[] estado_prod,
            @RequestParam(required = false, defaultValue = "--") String[] cantidad,
            @RequestParam(required = false, defaultValue = "--") String[] pendiente,
            @RequestParam(required = false, defaultValue = "--") String[] deposito,
            @RequestParam(required = false, defaultValue = "--") String[] bulk,
            @RequestParam(required = false, defaultValue = "--") String[] nSalida,
            @RequestParam(required = false, defaultValue = "--") String[] nlote,
            @RequestParam(required = false, defaultValue = "--") String[] fechavto,

            HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!ControllerUtils.isValidUser(request, uDao)) {
            return new ModelAndView("redirect:/rest/auth/login-web");
        }
        if (!ControllerUtils.isAllowed(request, uDao, 35)) {
            return new ModelAndView("wms.sin_permiso");
        }
        String clie = cod_cliente[0];
        int count = 0;
        for (int i = 0; i < codigo_prod.length; i++) {
            if (count > 0) {
                break;
            }
            String sali = nSalida[i];
            String prod = codigo_prod[i];
            String cant = cantidad[i];
            String esta = estado_prod[i];
            String cbulk = bulk[i];
            if (cant.length() > 0) {
            	Date fechavtoConverted = new Date();
        		try {
        			fechavtoConverted = (Date) (fechavto!=null?((new SimpleDateFormat("dd/MM/yyy")).parse(fechavto[i])):(new SimpleDateFormat("dd/MM/yyy HH:mm:ss")).format(new Date()));
        		} catch (ParseException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
                bDao.prc_entrega(orden.toString(), sali, prod, cant, esta, "", clie,nlote[i],fechavtoConverted);
                count++;
            }
        }
        return new ModelAndView("redirect:/web/detalle_complemento/" + orden + "/" + cod_cliente[0] + "/");
    }


	@RequestMapping(value = "finalizar_entrega/{orden}/{cliente}", method = RequestMethod.POST)
	public ModelAndView finalizar_entrega_post(
            @PathVariable Integer orden,
            @PathVariable String cliente,
			@RequestParam String cod_cliente[],
            HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 37))
			return new ModelAndView("wms.sin_permiso");
        Date d = new Date();
		bDao.autorizarEntrega(orden, d, cod_cliente[0]);
		 return new ModelAndView("redirect:/web/detalle_complemento/"+orden+"/"+cod_cliente[0]+"/");
	}
	
	@RequestMapping(value = "reporte/ubicacion_deposito/{deposito}", method = RequestMethod.GET)
	public ModelAndView reporte_ubicacion_deposito(
			@PathVariable String deposito,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		if(!ControllerUtils.isValidUser(request, uDao))
			return new ModelAndView("redirect:/rest/auth/login-web");
		if(!ControllerUtils.isAllowed(request, uDao, 39))
			return new ModelAndView("wms.sin_permiso");
		List<BulkCreados> bc = bDao.getBulkCreados(deposito);
		List<String> codigos = new ArrayList<String>();
		Double tcantidad=0.0, tancho=0.0, talto=0.0, tprofundidad=0.0, tarea=0.0, tvolumen=0.0, tpeso=0.0;
		if(bc==null || bc.size()<1){
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No hay Bulks en el deposito");
			return null;
		}
		// obtener subtotales
		List<Object[]> subtotales = bDao.getSubtotalesBulkCreados(deposito);
		
		// obtener totales
		List<Object[]> totales = bDao.getTotalesBulkCreados(deposito);

	
		// crear lista de codigos
		for(BulkCreados b:bc)
			if(!codigos.contains(b.getCodigoProducto()))
				codigos.add(b.getCodigoProducto());
		
		// crear totales cantidad
		for(Object[] sub:subtotales){
			tcantidad += Double.parseDouble(sub[7].toString());
		}

		// crear totales
		for(Object[] tot:totales){
			tancho += Double.parseDouble(tot[1].toString());
			talto += Double.parseDouble(tot[2].toString());
			tprofundidad += Double.parseDouble(tot[3].toString());
			tarea += Double.parseDouble(tot[4].toString());
			tvolumen += Double.parseDouble(tot[5].toString());
			tpeso += Double.parseDouble(tot[6].toString());
		}

		ModelAndView mdl = new ModelAndView("reporte.ubicacion_deposito");
		mdl.addObject("bulks", bc);
		mdl.addObject("subtotales", subtotales);
		mdl.addObject("codigos", codigos);
		mdl.addObject("cliente", bc.get(0).getNombreCliente());
		mdl.addObject("bodega", bc.get(0).getNombreBodega());
		mdl.addObject("tancho", tancho);
		mdl.addObject("talto", talto);
		mdl.addObject("tprofundidad", tprofundidad);
		mdl.addObject("tarea", tarea);
		mdl.addObject("tvolumen", tvolumen);
		mdl.addObject("tpeso", tpeso);
		mdl.addObject("tcantidad", tcantidad.intValue());
		mdl.addObject("fecha", (new SimpleDateFormat("dd/MM/yyy HH:mm:ss")).format(new Date()));
		mdl.addObject("df", new DecimalFormat("######0.00"));
		return mdl;
	}

}
