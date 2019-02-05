<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="row">
		<div class="col col-lg-2"></div>
		<div class="col col-lg-8">
            <c:if test="${ !entrega1 }"><c:if test="${ !entrega }"><c:if test="${ !autorizar }">
            <h2>Alerta de Ordenes de Preparación</h2></c:if></c:if></c:if>
            <c:if test="${ entrega }"><h2>Alertas de Entregas de Mercaderia</h2></c:if>
            <c:if test="${ entrega1 }"><h2>Detalle de Orden de Preparacion</h2></c:if>
			<c:if test="${ autorizar }"><h2>Detalle de Orden de Preparacion</h2>
			<h5>Orden: ${orden}</h5></c:if>
			<c:if test="${ despacho }">
			<table class="table table-striped">
				<tr>
					<th>Fecha y Hora</th>
					<th>Cod Cliente</th>
                    <th>Nombre Cliente</th>
					<th>Bodega</th>
					<th>No. Orden</th>
					<th>Salida</th> 
					<th>Estado</th>                    
				</tr>

				<c:forEach var="merc" items="${recepcion_mercaderia}" varStatus="loop">
				    
					<tr <c:if test="${entrega==null}"> onclick="DoNav('/wms-aldesa/web/preparacion/${merc.getOrden()}/${merc.getTipo()}/${merc.getNombre()}/N/?autorizado=${merc.getAutorizado()}');" </c:if>
					<c:if test="${entrega}"> onclick="DoNav('/wms-aldesa/web/despacho/${merc.getOrden()}/${merc.getTipo()}/${merc.getNombre()}/S/?autorizado=${merc.getEstado()}&cod_cliente=${merc.getCliente()}&desc_estado=${merc.getDescEstado()}');" </c:if> >
						<td>${merc.getFechaHora()}</td>
                        <td>${merc.getCliente()}</td>
                        <td>${merc.getNombre()}</td>
						<td>${merc.getBodega()}</td>
						<td>${merc.getOrden()}</td>
						<c:if test="${ entrega }"><td>${merc.getSalida()}</td></c:if>
						<c:if test="${ entrega1 }"><td>${merc.getSalida()}</td></c:if>
						<c:if test="${ !entrega1 }"><c:if test="${ !entrega }"><td>1</td></c:if></c:if>
						<c:if test="${ entrega }"><td>${merc.getDescEstado()}</td></c:if>
						<c:if test="${ entrega1 }"><td>${merc.getDescEstado()}</td></c:if>
						<c:if test="${ !entrega1 }"><c:if test="${ !entrega }"><td>Pend. de Preparar</td></c:if></c:if>
                        <c:if test="${ entrega && false }">
                            <td>
                                <form class="form-inline" method="post" action="/wms-aldesa/web/autorizar_entrega/${ merc.getOrden() }/${ merc.getCliente() }">
									<input type="hidden" name="cod_cliente" value="${cod_cliente}" />
                                    <input type="submit" class="btn btn-primary btn-sm" value="Autorizar"
										   <c:if test="${merc.getEstado()==1}">disabled</c:if>
									/>
                                    <input type="button" class="btn btn-primary btn-sm" value="Informe" onclick="window.location='/wms-aldesa/web/reporte/salida_simple/${merc.getOrden()}/${cliente}/'" >
                                </form>
                            </td>
                        </c:if>
					</tr>
				</c:forEach>
			</table>
			
			</c:if>
			<c:if test="${ autorizar }">
			<h5>Cliente: ${cliente}</h5>
			<table class="table table-striped">
				<tr><th>Deposito</th><th>Codigo</th><th>Descripcion</th><th>Cantidad</th></tr>
				
				<c:forEach var="merc" items="${recepcion_mercaderia}" varStatus="loop">
					<tr>
						<td>${merc.getDeposito()}</td>
						<td>${merc.getCodigo()}</td>
						<td>${merc.getDescripcion()}</td>
						<td>${merc.getCantidad()}</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${ !entrega1 }">
			<div class="row">
				<div class="col-lg-6 col col-lg-offset-3">
					<form method="post">
					<input type="hidden" name="orden" value="${orden}" />
					<input type="hidden" name="tipo" value="${tipo}" />
					<input type="hidden" name="cliente" value="${cliente}" />
					<div class="row">
					<div class="col col-lg-3 -col-sm-3 col-lg-offset-3">
						<a href="/wms-aldesa/web/preparacion/reporte/${ orden }/${tipo}/${cliente}/" class="btn btn-primary btn-lg">Imprimir Orden</a></div>
					
					<c:if test="${ autorizar2 }"><div class="col col-lg-3 -col-sm-3 col-lg-offset-3"><input type="submit" class="btn btn-primary btn-lg" value="Autorizar Preparacion" /></div>
					</c:if>
									
					</div>
					</form>
				</div>
				</c:if>
				<c:if test="${ entrega1 }">
					<div class="row">
						<div class="col col-lg-2 col-lg-offset-1">
							<a href="/wms-aldesa/web/preparacion/reporte/${ orden }/${tipo}/${cliente}/" class="btn btn-primary btn-lg">Imprimir Orden Preparada</a>
						</div>
						<div class="col col-lg-2 col-lg-offset-1">
							<a href="/wms-aldesa/web/reporte/salida_simple2/${ orden }/${cod_cliente}/" class="btn btn-primary btn-lg">Imprimir Informe de Salida</a>
						</div>
						<div class="col col-lg-2 col-lg-offset-1">
							<form class="form-inline" method="post" action="/wms-aldesa/web/autorizar_entrega/${ orden }/${cliente}/?cod_cliente=${cod_cliente}">
								<input type="hidden" name="cod_cliente" value="${cod_cliente}" />
								<input type="submit" class="btn btn-primary btn-lg" value="Finalizar Entrega"
									   <c:if test="${ autorizar2 }">disabled</c:if>
								/>
							</form>
						</div>
					</div>
				</c:if>
			</div>
			</c:if>
			
		</div>
	</div>