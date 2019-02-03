<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<form method="post">
		<input type="hidden" name="modo" value="S" />
	<input type="hidden" name="deposito" value="${ deposito }" />
	<input type="hidden" name="cliente" value="${ cliente }" />
	<c:if test="${mercaderia==null}">
		<input type="hidden" name="tipo" value="S" />
	</c:if><c:if test="${mercaderia!=null}">
		<input type="hidden" name="tipo" value="${ mercaderia.getTipo() }" />
	</c:if>
	<!-- autorizarUbicacion(String forma, String entregado, String recibido, String observacion, String deposito) -->
    <div class="col col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
        <h2 style="text-align: center;">Datos Finales de Recepción</h2>
    </div>
	<style>	.row + .row{ padding: 5px; }</style>
	<div class="row">
	<div class="col-lg-6 col col-lg-offset-3" >
		<div class="row well">
			<div class="col col-lg-4">
				Cliente: <strong>${ cliente }</strong>
			</div>
			<input type="hidden" name="tipo" value="${tipo}"/>
			<div class="col col-lg-4 col-lg-offset-4">
				Deposito: <strong>${ deposito }</strong>
			</div>
		</div>	 
		<div class="row">
			<div class="col col-lg-4 col-lg-offset-4">
				<label for="frecepcion">Forma de Recepción de Mercadería</label>
				<select id="frecepcion" name="forma" class="form-control" >
					<option value="Gr" <c:if test="${inf.getTipo()=='Granel'}">selected</c:if>>GRANEL</option>
					<option value="Pa" <c:if test="${inf.getTipo()=='Paletizada'}">selected</c:if>>PALETIZADO</option>
					<option value="Mi" <c:if test="${inf.getTipo()=='Mixta'}">selected</c:if>>MIXTA</option>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-lg-5">
				<label for="entregado">Entregado por</label>
                <input name="entregado" id="entregado" class="form-control" 
                <c:if  test="${ inf2 }">value="${inf.getEntregado()}"</c:if> />
			</div>
			<div class="col col-lg-5 col-lg-offset-2">
				<label for="recibido">Recibido por</label>
                <select name="recibido" id="recibido" class="form-control">
                    <c:forEach items="${jefes}" var="j" varStatus="loop">
                        <option value="${j.getNombreUsuario()}"
                            <c:if  test="${ !inf2 }">
                        	<c:if test="${j.getUsuario()==usr}">selected</c:if>
                        	</c:if>
                            <c:if  test="${ inf2 }">
                        	<c:if test="${j.getNombreUsuario()==inf.getJefeBodega()}">selected</c:if>
                        	</c:if>
                        	>${j.getNombreUsuario()}</option>
                    </c:forEach>
                </select>
			</div>
		</div>
				<div class="row">
					<div class="col col-lg-6 col-lg-offset-3">
						<label>Observaciones</label>
						<textarea id="observacion" name="observacion" class="form-control" style="width: 100%;" placeholder="Observaciones..."><c:if  test="${ inf2 }">${inf.getObservaciones()}</c:if></textarea>
					</div>
				</div>

		<div class="row">
			<div class="col col-lg-3 col-lg-offset-1">
                <a id="previewBtn" class="btn btn-primary btn-block">Imprimir Informe</a>
				<script>
					var preview = function(){
						var forma = $("#frecepcion").val();
						var entregado = $("#entregado").val();
						var recibido = $("#recibido").val();
						var observaciones = $("#observacion").val().replace(/\n/gi, ". ").trim();
						if (entregado=="") entregado="No se definio persona que entrega";
						if (observaciones=="".trim()) observaciones="-";
						/*
						if (forma=="Gr") forma="Granel";
						if (forma=="Pa") forma="Paletizado";
						if (forma=="Mi") forma="Mixto";
						*/
						var url2 = "/wms-aldesa/web/reporte/recibida_simple/${deposito}/1/" + forma + "/" + recibido + "/" + entregado + "/" + observaciones + "/${cliente}/";
						window.open(url2, '_');
						setTimeout ('location.reload(true)', 5000); ;
					};
					window.onload=function(){
						$('#previewBtn').on('click', preview);
					};
				</script>
			</div> 
			<div class="col-lg-3 col-lg-offset-1">
                <a href="/wms-aldesa/web/reporte/ubicacion_deposito/${deposito}" class="btn btn-default" >Informe Bulks Creados</a>
                </div>
			<div class="col col-lg-3 col-lg-offset-1">
                <input type="submit" class="btn btn-primary btn-block" value="Finalizar Recepcion"
                 <c:if test="${ inf.getEntregado().length()<4 }">disabled</c:if>
                />
			</div>
			</div>
		</div>
	</div>
	</div>
	</form>
	<!-- Hero Area Section -->
	<!-- Hero Area Section End-->
	<!-- Bootstrap JS -->