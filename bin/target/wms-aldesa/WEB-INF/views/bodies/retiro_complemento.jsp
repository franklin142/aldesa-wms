<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col col-lg-2"></div>
	<div class="col col-lg-8">
		<h2>Entrega mercaderia </h2>
		<c:if test="${ tipo=='S' }"><h4>Complemento de Datos Generales de Entrega de Mercaderia Simple</h4></c:if>
		<c:if test="${ tipo=='F' }"><h4>Complemento de Datos Generales de Entrega de Mercaderia Fiscal</h4></c:if>
		<c:if test="${ tipo=='D' }"><h4>Complemento de Datos Generales de Entrega de Mercaderia Desconsolidada</h4></c:if>
		<h4>Orden: ${retiro} </h4>
		<h4>No de Salida: ${retiros.getSalidaNo()}</h4>
		<h4>Cliente: ${retiros.getNombre()}</h4>
		<form class="form-horizontal" method="post">
			<div class="row">
				<div class="form-group">
					<label for="cargado" class="col-sm-3 control-label">Cargado por</label>
					<div class="col-sm-9">
						<select class="form-control" id="cargado" name="cargado" onchange="updateBtns()">
							<option value="CLIENTE"
									<c:if test="${retiros.getCargado_Por()=='CLIENTE'}">selected</c:if>>CLIENTE</option>
							<option value="ALDESA"
									<c:if test="${retiros.getCargado_Por()=='ALDESA'}">selected</c:if>>ALDESA</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="formaentrega" class="col-sm-3 control-label">Forma de entrega de carga</label>
					<div class="col-sm-9">
						<select id="formaentrega" name="forma" class="form-control" onchange="updateBtns()">
							<option value="GRANEL"
									<c:if test="${retiros.getTipo()=='Granel'}">selected</c:if>>GRANEL</option>
							<option value="PALETIZADO"
									<c:if test="${retiros.getTipo()=='Paletizada'}">selected</c:if>>PALETIZADO</option>
							<option value="MIXTA"
									<c:if test="${retiros.getTipo()=='Mixta'}">selected</c:if>>MIXTA</option>
						</select>
					</div>
				</div>


				<div class="form-group">
					<label for="entregado" class="col-sm-4 control-label">Entregado por</label>
					<div class="col-sm-4">
                <select name="entregado" id="entregado" class="form-control">
                    <c:forEach items="${jefes}" var="j" varStatus="loop">
                         <option value="${j.getNombreUsuario()}"
                            <c:if  test="${ !inf2 }">
                        	<c:if test="${j.getUsuario()==usr}">selected</c:if>
                        	</c:if>
                            <c:if  test="${ inf2 }">
                        	<c:if test="${j.getNombreUsuario()==retiros.getEntregado()}">selected</c:if>
                        	</c:if>
                        	>${j.getNombreUsuario()}</option>
                    </c:forEach>
                </select>
					</div>
				</div>

				<div class="form-group">
					<label for="ficarga" class="col-sm-4 control-label">Fecha Inicio de carga</label>
					<div class="col-sm-4">
						<input class="form-control" id="ficarga" name="ficarga" 
							   placeholder="Inicio de carga" type="date" value="${ fechac }">
					</div>
				</div>

			    <div class="form-group">
					<label for="hicarga" class="col-sm-4 control-label">Hora Inicio de carga</label>
					<div class="col-sm-4">
						<input id="timepicker" class="form-control"
							data-provide="timepicker" data-template="modal"
							data-minute-step="1" data-modal-backdrop="true" type="time"
							name="hicarga" value="${ horac }" id="hicarga"
							required placeholder="Hora Inicio de carga formato 24 horas"
							pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}" />
					</div>
					
				</div>

				<div class="form-group">
					<label for="ffcarga" class="col-sm-4 control-label">Fecha Fin de carga</label>
					<div class="col-sm-4">
						<input class="form-control" id="ffcarga" name="ffcarga" 
							   placeholder="Fin de carga" type="date" value="${ fechad }">
					</div>
				</div>
                <div class="form-group">
					<label for="hfcarga" class="col-sm-4 control-label">Hora Fin de carga</label>
					<div class="col-sm-4">
						<input id="timepicker" class="form-control"
							data-provide="timepicker" data-template="modal"
							data-minute-step="1" data-modal-backdrop="true" type="time"
							name="hfcarga" value="${ horad }" id="hfcarga"
							required placeholder="Hora Fin de carga formato 24 horas"
							pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}" />
					</div>
					
				</div>
			

				<div class="form-group">
					<label for="autorizada" class="col-sm-4 control-label">Persona Autorizada</label>
					<div class="col-sm-4">
						<input class="form-control" id="autorizada" name="autorizada" 
							   placeholder="Persona autorizada" value="${ retiros.getRetirar() }">
					</div>
				</div>

				<div class="form-group">
					<label for="observaciones" class="col-sm-4 control-label">Observaciones</label>
					<div class="col-sm-4">
						<textarea class="form-control" id="observaciones" name="observaciones"
								  placeholder="Observaciones">${ retiros.getObservaciones() }</textarea>
					</div>
				</div>
				<script>
					document.getElementById('ficarga').value = new Date().toDateInputValue();
					document.getElementById('ffcarga').value = new Date().toDateInputValue();

					var cambiarMuelleSelect = function(){
						var e = document.getElementById('bodegaSelect');
						var e2 = document.getElementById('muelleSelect');
						e2.options.length = 0;
						var value =  e.options[e.selectedIndex].value;
						var muelle = null;
						var opt = null;
						for(var i=0; i<muelles.length; i++){
							muelle = muelles[i];
							if(muelle.bodega!=value) continue;
							opt = document.createElement('option');
							opt.value = muelle.codigo;
							opt.innerHTML = muelle.nombre;
							e2.appendChild(opt);
						}
					};
					var fn_DateCompare = function (DateA, DateB) {
						var a = new Date(DateA);
						var b = new Date(DateB);

						var msDateA = Date.UTC(a.getFullYear(), a.getMonth()+1, a.getDate());
						var msDateB = Date.UTC(b.getFullYear(), b.getMonth()+1, b.getDate());

						if (parseFloat(msDateA) < parseFloat(msDateB))
							return -1;  // less than
						else if (parseFloat(msDateA) == parseFloat(msDateB))
							return 0;  // equal
						else if (parseFloat(msDateA) > parseFloat(msDateB))
							return 1;  // greater than
						else
							return null;  // error
					};

					var fn_validateDate = function(){
						var a = new Date($("#fecha-actualizacion").val());
						var a = new Date(a.getFullYear(), a.getDate(), a.getMonth()+1);
						var hoy = new Date();
						console.log("aqui");
						console.log(parseFloat(a.getDate()));
						console.log(parseFloat(hoy.getDate()));
						if(parseFloat(a.getDate())>parseFloat(hoy.getDate())){
							alert("No se puede poner una fecha valor mayor al dia de hoy");
							$("#fecha-actualizacion").val(hoy);
							console.log("aqui a");
							return;
						}
						if(parseFloat(a.getMonth()+1)<parseFloat(hoy.getMonth())){
							alert("No se puede poner una fecha valor anterior al mes actual");
							$("#fecha-actualizacion").val(hoy);
							console.log("aqui b ");
							return;
						}
						console.log("aqui c");
					};
				</script>
				<div class="row">
					<div class="col-lg-offset-1 col-lg-4 col">
						<input type="hidden" value="${tipo}" name="tipo">
						<input type="hidden" value="${retiros.getSalidaNo()}" name="salida">
						<button id="salidaBtn" onclick="updateBtns()" type="submit" class="btn btn-success btn-lg btn-block">Imprimir Informe Salida</button>
					</div>
					<div class="col-lg-offset-2 col-lg-4 col">
						<c:if test="${resultado==0 }">	<h4>Existe informacion incompleta en datos del almacen, favor verificar.</h4> </c:if>
						<c:if test="${resultado==2 }">	<h4>Existe informacion incompleta en datos de retiro, favor verificar.</h4> </c:if>
					    <c:if test="${resultado==1}">
						<a id="finalizarBtn" <c:if test="${ retiros.getRetirar().length()<4 }">disabled</c:if> href="/wms-aldesa/web/autorizar_entrega_prc/${retiro}/${cliente}/" 
						class="btn btn-default btn-lg btn-block" >Finalizar Entrega	</a></c:if>
					
					</div>

				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
var updateBtns = function(){
	var en = true;
	if(document.getElementById('formaentrega').value==null) en = false;
	if(document.getElementById('cargado').value==null) en = false;
	if(document.getElementById('entregado').value==null) en = false;
	if(document.getElementById('ficarga').value==null) en = false;
	if(document.getElementById('hicarga').value==null) en = false;
	if(document.getElementById('ffcarga').value==null) en = false;
	if(document.getElementById('hfcarga').value==null) en = false;
	if(document.getElementById('autorizada').value==null) en = false;
	if(document.getElementById('observaciones').value==null) en = false;
	
	document.getElementById('finalizarBtn').disabled = !en;
};
</script>