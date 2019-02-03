<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row" style="padding: 10px;"disabled>
    <div class="col col-lg-2"></div>
    <div class="col col-lg-8">
        <h2 style="text-align: center;">Resumen de Mercaderia Recibida</h2>
        <div class="well">
        	<c:if test="${msg}">
        		<div class="row">
        			<div class="col col-lg-6 col-lg-offset-3" style="text-align: center; color: red;">
        				<strong>Bulk inexistente</strong>
        			</div>
        		</div>
        	</c:if>
            <div class="row" style="padding:5px;">
                <div class="col col-lg-6">
                    Cliente: <strong>${ cliente.getNombre() }</strong>
                </div>
                <div class="col col-lg-3 col-lg-offset-3">
                    Depósito: <strong>${ deposito }</strong>
                </div>
            </div>
            <div class="row" style="padding:5px; align:center;">
                <div class="col col-lg-12">
                    <h5>Detalle de Mercaderia Recibida</h5>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row" style="padding: 10px;">
    <div class="col col-lg-1"></div>
    <div class="col col-lg-10">
        <div class="container">
            <table class="table table-tripped" disabled>
                <tr>
                    <th>Codigo</th>
                    <th>Descripcion</th>
                    <th>Declarada</th>
                    <th>Recibidos Buenos</th>
                    <th>Recibidos Averiados</th>
                    <th>Total Recibidos</th>
                    <th>Diferencia</th>
                    <th>Unidad de Medida</th>
                </tr>


                <c:forEach var="m" items="${pendientes}" varStatus="loop">
                    <tr>
                        <td>${ m.getCodigoMercaderia() }</td>
                        <td>${ m.getDescripcion() }</td>
                        <td>${ m.getDeclarada() }</td>
                        <td>${ m.getRecibida() }</td>
                        <td>${ m.getAveriada() }</td>
                        <td>${ m.getRecibida()+m.getAveriada()}</td>
                        <td>${ m.getSaldo() }</td>
                        <td>${ m.getUnidadMedida() }</td>
                        <td></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2">Totales:</td>
                    <td>${tdeclarada}</td>
                    <td>${trecibida}</td>
                    <td>${taveriada}</td>
                    <td>${trecibida+taveriada}</td>
                    <td>${tsaldo}</td>
                    <td></td><td></td>
                </tr>
            </table>
            <div class="row">
            	<div class="col col-lg-4 col-sm-6">
		            <div id="modal2" style="display: none;">
		                <h4>Editar Bulk</h4>
		                <div class="input-group">
		                    <input type="text" class="form-control" placeholder="Bulk"  id="editBulkTxt">
		                  <span class="input-group-btn">
		                    <button class="btn btn-default" type="button" id="editBulkBtn">Editar</button>
		                  </span>
		                </div><!-- /input-group -->
		                <script>
		                    document.getElementById("editBulkBtn").onclick = function(){
		                        var bulk =   document.getElementById("editBulkTxt").value;
		                        window.location = "/wms-aldesa/web/edit_bulk/"+bulk+"?cliente=${ cliente.getCliente_No() }&deposito=${ deposito }&tipo=${ tipo }";
		                    };
		                </script>
		            </div>
            	</div>
            </div>
            <div class="row" >
                <div class="col col-lg-2">
                    <a class="btn btn-primary btn-lg btn-block"
                       href="/wms-aldesa/web/arribos_recepcion?editar=1&cliente=${ cliente.getCliente_No() }&deposito=${ deposito }&tipo=${tipo}">
                        Ir a datos arribo</a>
                </div>
                <div class="col col-lg-2 col-lg-offset-1">
                    <a class="btn btn-primary btn-lg btn-block" onclick="$('#modal2').toggle();">
                        Modificar bulk</a>
                </div>
                <div class="col col-lg-2 col-lg-offset-1">
                    <a class="btn btn-primary btn-lg btn-block"
                       href="/wms-aldesa/web/recepcion_mercaderia?cliente=${ cliente.getCliente_No() }&deposito=${ deposito }" >
                        Imprimir Informe</a>
                </div>
                 <div class="col-lg-2 col-lg-offset-1">
                <a href="/wms-aldesa/web/reporte/ubicacion_deposito/${deposito}" class="btn btn-primary btn-lg btn-block" >Bulks Creados</a>
                </div>
            </div>
        </div>
    </div>
</div>