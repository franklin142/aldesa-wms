<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Hero Area Section -->

<div class="row">
	<div class="col col-lg-1"></div>
	<div class="col col-lg-10">
		<h2>Detalle de Entrega de Mercadería</h2>
		<h4>Orden: ${orden} </h4>
		<h4>No de Salida: ${salida}</h4>
		<h4>Cliente: ${cliente}</h4>
		<script>
			var updatePendiente = function(or, dif, inp){
				var input = $(inp);
				input.val(or-dif);
				console.log(" " + or + " - " + dif + " = " + (or-dif));
			};
		
			var prc_entrega = function(codigo_prod){
				
				var cod_cliente = '${cod_cliente}', 
					estado_prod = document.getElementById("_"+codigo_prod+"-estado").value, 
					cantidad = document.getElementById("_"+codigo_prod+"-cantidad").value, 
					pendiente = document.getElementById("pend_"+codigo_prod+"").value, 
					deposito = document.getElementById("_"+codigo_prod+"-deposito").value, 
					bulk = document.getElementById("_"+codigo_prod+"-bulk").value, 
					nsalida = document.getElementById("_"+codigo_prod+"-nsalida").value,
					cantidad1 = document.getElementById("_"+codigo_prod+"-cantidad1").value, 
					codprod = document.getElementById("_"+codigo_prod+"-codigo").value, 
					cantidadentregada = document.getElementById("_"+codigo_prod+"-cantidadentregada").value;
				
				if(parseFloat(cantidad)>parseFloat(cantidad1)-parseFloat(cantidadentregada)){ 
					alert('No se puede sacar mas cantidad que la pendiente');
					document.getElementById("_"+codigo_prod+"-cantidad").value = original;
					return;
				}
				updatePendiente(parseFloat(cantidad1) - parseFloat(cantidadentregada), parseFloat(cantidad), '#pend_'+codigo_prod);
				
				var parametros = {
						cod_cliente:cod_cliente,
						codigo_prod:codprod,
						estado_prod:estado_prod,
						cantidad:cantidad,
						pendiente:pendiente,
						deposito:deposito,
						bulk:bulk,
						nSalida:nsalida
				};
				$.ajax({
	                data:  parametros,
	                url:   '/wms-aldesa/web/autorizar_entrega_producto_post/${ orden }/${cliente}/',
	                type:  'post',
	                // beforeSend: function () {},
	                success:  function (response) {
	                	console.log('Actualizacion de producto '+ codigo_prod + '');
	                }
	        });
			};
		</script>
		<form class="form-inline" method="post" action="/wms-aldesa/web/autorizar_entrega/${ orden }/${cliente}/">
			<table class="table table-striped">
				<tr>
<!-- 					<th></th> -->
					<th width="120px">Codigo</th>
					<th>Descripcion</th>
					<th>Unidad de medida</th>
					<th width="120px">Estado mercaderia solicitada</th>
					<th>Cantidad preparadas</th>
					<th>Cantidad entregada en salidas ant.</th>
					<th>Cantidad Entregadas</th>
					<th>Pendiente</th>
					<th width="170px">Bulk</th>
					<th width="170px">Lote</th>
					<th width="170px">Fecha de vencimiento</th>
					
				</tr>
				<c:if test="${ pendiente }">			 
				<c:forEach var="merc" items="${recepcion_mercaderia}" varStatus="loop">
				<tr>
<!-- 					<td> -->
<!-- 						<button type="button" class="btn btn-info" onclick="prc_entrega('${merc.getCodigo()}')"> -->
<!-- 							<span class="glyphicon glyphicon-floppy-disk"></span> -->
<!-- 						</button> -->
<!-- 					</td> -->
					<td><input value="${merc.getCodigo()}" disabled><input id="_${loop.index}-codigo"  type="hidden" name="codigo_prod" value="${merc.getCodigo()}" width="120px"/></td>
					<td>${merc.getDescripcion()}</td>
					<td>${merc.getUnidadMedida()}</td>
					<td><input value="${merc.getEstado()}" disabled><input id="_${loop.index}-estado" type="hidden" name="estado_prod" value="${merc.getEstado()}"  width="120px"/></td>
					<td>${merc.getCantidad()}<input id="_${loop.index}-cantidad1" type="hidden" value="${merc.getCantidad()}" ></td>
					<td>${merc.getCantidadEntregada()}<input id="_${loop.index}-cantidadentregada" type="hidden" value="${merc.getCantidadEntregada()}" ></td>
					<td><input type="number" name="cantidad"
								id="_${loop.index}-cantidad" min="0"
								max="${merc.getCantidad()- merc.getCantidadEntregada()}"
								value="${merc.getCantidadAEntregar()}"
								onblur="prc_entrega('${loop.index}')" /></td>
					<td><input type="number" name="pendiente" value="${merc.getPendiente()}" disabled width="120px" id="pend_${loop.index}" /></td>
					<td>${merc.getBulk()}<input id="_${loop.index}-bulk" type="hidden" name="bulk" value="${merc.getBulk()}"/>
						<input type="hidden" id="_${loop.index}-deposito" name="deposito" value="${merc.getDeposito()}"/>
						<input type="hidden" name="nSalida"  id="_${loop.index}-nsalida" value="${merc.getnSalida()}"/>
					</td>
					<td>${merc.getnLote()}<input id="nolote" type="hidden" name="nolote" value="${merc.getnLote()}"/>
					
					</td>
					<td>${merc.getFechaVto()}
					<input id="fechavto" type="hidden" name="fechavto" value="${merc.getFechaVto()}"/>
						
					</td>
				</tr> 
			</c:forEach>
			</c:if>
			</table>
			<c:if test="${ entrega1 }">
				<div class="row">
					<div class="col col-lg-2 col-lg-offset-1">
						<a href="/wms-aldesa/web/despacho/reporte/${ orden }/${tipo}/${cod_cliente}/" class="btn btn-primary btn-lg">Imprimir Orden Preparada</a>
					</div>
					<!--
						<div class="col col-lg-2 col-lg-offset-1">
							<a href="/wms-aldesa/web/reporte/salida_simple2/${ orden }/${cod_cliente}/" class="btn btn-primary btn-lg">Imprimir Informe de Salida</a>
						</div> -->
					<div class="col col-lg-2 col-lg-offset-1">
						<input type="hidden" name="cod_cliente" value="${cod_cliente}" />
						<input type="submit" class="btn btn-primary btn-lg" value="Imprimir Informe Salida"
							   <c:if test="${ autorizar2 }">disabled</c:if>
						/>
					</div>
					<c:if test="${ pendiente }"><c:if test="${ tipo!='D' }">
						<div class="col col-lg-2 col-lg-offset-1">
						    <a href="/wms-aldesa/web/orden_reingreso/reporte/${ orden }/${tipo}/${cod_cliente}/" class="btn btn-primary btn-lg">Imprimir Orden Reingreso</a>
						</div>
					</c:if></c:if>
				</div>
			</c:if>
		</form>
	</div>
</div>
</div>
