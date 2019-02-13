<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	var  printed = ${printed || !fprint};
</script>
<script>
	$(document).ready(function(){
    	$( ".class_fechavto" ).datepicker({
	    	appendText: "",
	    	showAnim: "fadeIn",
	    	button:false,
	    	buttonImage:false,
	   		dateFormat: 'dd/MM/yyyy'
		});
	
	});
</script>
<!-- Hero Area Section -->
<div class="row" style="padding: 10px;">
	<div class="col col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
		<h2 style="text-align: center;">Creación de Bulk</h2>
		<h4 style="text-align: center;">Detalle Mercaderia Recibida</h4>
        	<c:if test="${msg}">
        		<div class="row">
        			<div class="col col-lg-6 col-lg-offset-3" style="text-align: center; color: red;">
        				<strong>${mensaje}</strong>
        			</div>
        		</div>
        	</c:if>

		<div class="well">
			<div class="row" style="padding:5px;">
				<div class="col col-lg-7">
					<h4>Cliente: <strong>${ cliente.getNombre() }</strong></h4>
				</div>
				<div class="col col-lg-3">
					<h4>Depósito: <strong>${ deposito }</strong></h4>
				</div>
				<div class="col col-lg-2">
					<h4>Tipo: <strong>
						<c:if test="${ tipo=='S' }">Simple</c:if>
						 <c:if test="${ tipo=='F' }">Fiscal</c:if>
						<c:if test="${ tipo=='D' }">Desconsolidado</c:if>
					</strong></h4>
				</div>
			</div>
			<div class="row" style="padding:5px;">
				<div class="col col-lg-4 col-lg-offset-2">
					<label for="inicio-descarga">Inicio de descarga</label>
					<input type="checkbox" class="form-control" id="inicio-descarga" checked
						   disabled />
				</div>
				<div class="col col-lg-4">
					<label for="fin-descarga" > Fin de descarga</label>
					<input type="checkbox" id="fin-descarga" class="form-control"
						   <c:if test="${ bulk==null }">disabled</c:if>
						   <c:if test="${ finalizado }">disabled checked</c:if>
						   onclick="$('#fdescarga').val(this.checked?'on':'off')"/>

				</div>
			</div>
		</div>
	</div>
</div>
<div class="row" style="padding: 5px;">
	<div class="col col-lg-1"></div>
	<div class="col col-lg-10">
		<div class="container">
			<form method="post" id="guardar-detalles">
				<input type="hidden" name="fdescarga" value="" id="fdescarga"/>
				<input type="hidden" name="consignatario" value="${smercc}" id="consignatario"/>
				<div class="row" style="padding:5px;">
					<div class="col col-lg-2">
						<h5>Bulk: <strong>${ bulk.getCodigoBulk() }</strong></h5>
                                                <c:if test="${ tipo=='S' }"> <h5 >Régimen: <strong>${ bulk.getPignorado() }</strong></h5></c:if>
                                                 <c:if test="${ tipo=='F' }"> <h5 >Régimen: <strong>${ bulk.getPignorado() }</strong></h5></c:if>
                                               
					</div>
                    <div class="col col-sm-2">
						Ancho (m)<input id="ancho" type="number" min="0" name="ancho" placeholder="999" required
										<c:if test="${ finalizado }">disabled</c:if>
										<c:if test="${ ha_creado }">disabled</c:if> value="${ bulk.getAncho() }" class="form-control" <c:if test="${ bulk==null }">disabled</c:if> />
					</div>
					<div class="col col-sm-2">
						Alto (m)<input id="alto" type="number" min="0" name="alto" placeholder="999"
									   <c:if test="${ finalizado }">disabled</c:if>
									   <c:if test="${ ha_creado }">disabled</c:if> required value="${ bulk.getAlto() }" class="form-control" <c:if test="${ bulk==null }">disabled</c:if>  />
					</div>
					<div class="col col-sm-2">
						Profundidad (m) <input id="profundidad" type="number" min="0"
											   <c:if test="${ finalizado }">disabled</c:if>
											   <c:if test="${ ha_creado }">disabled</c:if> name="profund" placeholder="999" required value="${ bulk.getProfundidad() }" class="form-control" <c:if test="${ bulk==null }">disabled</c:if>  />
					</div>
					<div class="col col-sm-2">
						Peso (Kg)<input id="peso" type="number" min="0" placeholder="999" class="form-control"
										<c:if test="${ finalizado }">disabled</c:if>
										<c:if test="${ ha_creado }">disabled</c:if> required name="peso" value="${ bulk.getPeso() }" <c:if test="${ bulk==null }">disabled</c:if>  />
					</div>
					<div class="col col-sm-2">
						Bulk Flejado
						<input type="checkbox" class="form-control"
							   <c:if test="${ finalizado }">disabled</c:if>
							   <c:if test="${ ha_creado }">disabled</c:if>
							   <c:if test="${ bulk.getTarimaFlejada() == 'S' }" >checked</c:if>
							   onclick="$(this).next().val(this.checked?'on':'off')" <c:if test="${ bulk==null }">disabled</c:if>  />
						<input type="hidden"
							   <c:if test="${ bulk.getTarimaFlejada() == 'N' }" >value="off"</c:if>
							   <c:if test="${ bulk.getTarimaFlejada() == 'S' }" >value="on"</c:if>
							   name="flejada" class="form-control"/>
					</div>
				</div>
				<script type="text/javascript">
				    var codm ='';
				    var vcuenta = 0;
					var validarDiferencia = function(declarado, valor){
						var d = parseInt(declarado,10);
						var v = parseInt(valor,10);
						if(d-v<0)
							alert('Valor digitado no puede ser mayor al declarado');
						if(v==0){
							alert('Valor digitado debe ser mayor a cero');
						}
					};
					var validarDiferencia2 = function(declarado, valor, input, codigomerc){
						var d = parseInt(declarado,10);
						var v = parseInt(valor,10);
						if (v>0) {
						    codm = codigomerc;
						}
						if(v==0){
							var ans = confirm("¿ESTA SEGURO QUE EXISTE UN FALTANTE?");
							if (!ans){
								input.focus();
								input.select();
							}
						}
						if(v>d){
							var ans = confirm("¿ESTA SEGURO QUE HAY SOBRANTE?");
							if (!ans){
								input.focus();
								input.select();
							}
						}
						if(v<0){
							alert('Valor digitado debe ser mayor o igual a cero');
						}
					};
				</script>
				<table class="table table-tripped">
					<tr>
						<th>Codigo</th>
						<th>Descripcion</th>
						<th>Saldo</th>
						<th>Por Bulk</th>
						<th>Unidad de Medida</th>
						<th>Estado de Mercaderia</th>
						<th>No. lote</th>
						<th>Fecha de Vto</th>
						<th>Posee sobrante?</th>
					</tr>


					<c:forEach var="m" items="${pendientes}" varStatus="loop">
						<tr>
							
							<td>${ m.getCodigo_Mercaderia() }<input id="codMerc" type="hidden" value="${ m.getCodigo_Mercaderia() }" name="codMerc"/>
							
							<input type="hidden" value="${ m.getItem() }" name="item"/></td>
							
							<td>${ m.getDescripcion() }<input type="hidden" name="descripcion" value="${ m.getDescripcion() }" /></td>
							
							<td><c:out value="${ m.getSaldo().intValue() }"/><input type="hidden" name="saldo" value="${ m.getSaldo() }" /></td>
							
							<td><input type="number" min="1" name="porbulk"
								<c:if test="${ finalizado }">disabled</c:if>
									<c:if test="${ smerc.equalsIgnoreCase(m.getIdentificador())  }">
										<c:if test="${ scantidad.intValue()<= m.getSaldo().intValue() }"> value="${ scantidad.intValue() }" </c:if>
										<c:if test="${ scantidad.intValue()> m.getSaldo().intValue() }"> value="${ m.getSaldo().intValue() }" </c:if>
									</c:if>
									<c:if test="${ !smerc.equalsIgnoreCase(m.getIdentificador()) }">value="0"</c:if>
									   onblur="validarDiferencia2(${ m.getSaldo().intValue() }, this.value, this,'${m.getCodigo_Mercaderia()}');"
									   placeholder="999" required class="form-control"
									   <c:if test="${ ha_creado }">disabled</c:if>
									   <c:if test="${ bulk==null }">disabled</c:if>
									   step="1" pattern="\d*" /> </td>
							<td>${ m.getUnidad_Medida() }<input type="hidden" value="${ m.getUnidad_Medida() }" name="medida" <c:if test="${ bulk==null }">disabled</c:if>  /></td>
							<td>
								<select name="estado"  class="form-control"
										<c:if test="${ finalizado }">disabled</c:if>
										<c:if test="${ ha_creado }">disabled</c:if>
										<c:if test="${ bulk==null }">disabled</c:if>>
									<c:forEach items="${estados_mercaderia}" var="estado">
										<option
												<c:if test="${estado.getCodigo()=='B'}">
													selected
												</c:if>
												value="${estado.getCodigo()}">
												${estado.getDescripcion()}
										</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input type="text" min="1" name="nolote" id="nolote" class="form-control"
								<c:if test="${ finalizado }">disabled</c:if>
								<c:if test="${ bulk==null }">disabled</c:if>
								class="det_bulk_nlote"
								/>
							</td>
							<td>
							
								<input type="text" name="fechavto" id="fechavto" class="form-control class_fechavto" 
								<c:if test="${ finalizado }">disabled</c:if>
								<c:if test="${ bulk==null }">disabled</c:if>
								class="det_bulk_fechavto"
								/>
							</td>
							
							<td>
								<input type="checkbox" class="form-control"
									   <c:if test="${ finalizado }">disabled</c:if>
									   <c:if test="${ ha_creado }">disabled</c:if>
									   onclick="$(this).next().val(this.checked?'on':'off')" <c:if test="${ bulk==null }">disabled</c:if>  />
								<input type="hidden" value="off"
                                       name="sobrante" class="form-control"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				<input type="hidden" name="tipo" value="${ tipo }"/>
				<input type="hidden" name="_method" value="_put_det_bulk"/>
				<input type="hidden" name="codbulk" value="${ bulk.getCodigoBulk() }" />
				<input type="hidden" name="deposito" value="${ deposito }" />
				<input type="hidden" name="recepcion" value="${ recepcion }" />
				<input type="hidden" name="entrega" value="${ entrega }" />
			</form>
			<form method="post" id="nuevobulk" action="/wms-aldesa/web/arribos_detalle">
				<input type="hidden" name="tipo" value="${ tipo }"/>
				<input type="hidden" name="_method" value="put"/>
				<input type="hidden" name="deposito" value="${ deposito }" />
				<input type="hidden" name="recepcion" value="${ recepcion }" />
				<input type="hidden" name="entrega" value="${ entrega }" />
			</form>

			<input type="hidden" name="tipo" value="${ tipo }"/>
			<div class="row">
				<c:if test="${ bulk==null && !ha_creado }">
					<script>
						var nuevoBulk=function(){
							$('#nuevobulk').submit();
						};
					</script>
					<div class="col col-lg-3 col-lg-offset-1">
					<button type="button"
						 <c:if test="${ finalizado }">disabled</c:if> onclick="nuevoBulk()" class="btn btn-default btn-block">Nuevo Bulk</button>
				 	</div>
					<div class="col col-lg-3 col-lg-offset-1"><a href="/wms-aldesa/web/arribos_detalle_ver?cliente=${ cliente.getCliente_No() }&deposito=${ deposito }&tipo=${ tipo }" class="btn btn-default btn-block">Resumen</a></div>
					<div class="col col-lg-3 col-lg-offset-1"><a href="/wms-aldesa/web/arribos" class="btn btn-default btn-block">Salir</a></div>
				</c:if>
				<c:if test="${ ha_creado }">
					<div class="col col-lg-2 col-lg-offset-1"><a href="/wms-aldesa/web/arribos_detalle?deposito=${ deposito }&recepcion=1&tipo=${ tipo }&entrega=1" class="btn btn-default btn-block">Nuevo Bulk2</a></div>
					<c:if test="${ tipo=='S' }">
					<div class="col col-lg-2 col-lg-offset-1"><a target="_blank" href="barcode?valor=${ bulk.getCodigoBulk() }&cliente=${ bulk.getPignorado() }" type="button" class="btn btn-default btn-block">Imprimir
						Etiqueta</a></div> </c:if>
					<div class="col col-lg-2 col-lg-offset-1"><a href="/wms-aldesa/web/arribos_detalle_ver?cliente=${ cliente.getCliente_No() }&deposito=${ deposito }&tipo=${ tipo }" class="btn btn-default btn-block">Resumen</a></div>
					<div class="col col-lg-2 col-lg-offset-1"><a href="/wms-aldesa/web/arribos" class="btn btn-default btn-block">Salir</a></div>
				</c:if>
				<c:if test="${ bulk!=null && !ha_creado }">
					<script type="text/javascript">
						var validarYSubir = function(ptipo){
							var vtipo = ptipo;
							if( $("#ancho").val().trim().length<=0 ){
								alert('Debe establecer un valor para el ancho');
								return;
							}
							if( $("#alto").val().trim().length<=0){
								alert('Debe establecer un valor para el alto');
								return;
							}
							if( $("#profundidad").val().trim().length<=0){
								alert('Debe establecer un valor para profundidad');
								return;
							}
							if( $("#peso").val().trim().length<=0) {
								alert('Debe establecer un valor para el peso');
								return;
							}
							if( parseFloat($("#ancho").val().trim())<=0 ){
								alert('Debe establecer un valor para el ancho mayor que cero');
								return;
							}
							if( parseFloat($("#alto").val().trim())<=0){
								alert('Debe establecer un valor para el alto mayor que cero');
								return;
							}
							if( parseFloat($("#profundidad").val().trim())<=0){
								alert('Debe establecer un valor para profundidad mayor que cero');
								return;
							}
							if( parseFloat($("#peso").val().trim())<=0){
								alert('Debe establecer un valor para el peso mayor que cero');
								return;
							}
							if( parseFloat($("#peso").val().trim())<=0){
								alert('Debe establecer un valor para el peso mayor que cero');
								return;
							}
							//Franklin Flores valida Campos vacios para No_Lote y Fec_Venc_Lote
							var validarNLote=true;
                    		var tabla = document.getElementsByTagName("table")[0];
                    		console.log(tabla);
                    		for(i=0;i<tabla.childNodes[1].children.length;i++){
                    			if(tabla.childNodes[1].children[i].cells[0].nodeName=="TD"){
                					var valInputNLote = tabla.childNodes[1].children[i].cells[6].children[0].value;	
                					if(valInputNLote.length!=0&&!tabla.childNodes[1].children[i].cells[6].children[0].disabled){
                    					var valInputFechavto = tabla.childNodes[1].children[i].cells[7].children[0].value;	
                    					if(valInputFechavto.length==0){
                							
    										alert('Ha digitado un numero de lote para el detalle con codigo \"'+
    												tabla.childNodes[1].children[i].cells[0].childNodes[0].data+
    												'\". \nPero el valor de fecha de vencimiento esta vacío');
    										return;
                						}
										if((new Date(valInputFechavto))<=new Date()){
                							
    										alert('La fecha de vencimiento de lote no puede ser menor o igual a la fecha de hoy.');
    										return;
                						}
                					}
                    			}

                    		}
							//Fin de la validacion Franklin Flores.
							
							if( vtipo=="D") {
								if (codm.length<=0){
									codm= $("#consignatario").val();
								}
								
								$("#consignatario").val(codm);
								window.open('/wms-aldesa/web/barcode?valor=${bulk.getCodigoBulk()}&cliente=${bulk.getId_cliente()}+-'+codm, '_blank');
						   }
							$('#guardar-detalles').submit();
							
						};

						var onPrint = function(){
							printed = true;
						};

					</script>
					
					<c:if test="${ tipo=='S' || tipo=='F' }">
					<div class="col col-lg-3 col-lg-offset-1"><a onclick="onPrint();" target="_blank" href="barcode?valor=${ bulk.getCodigoBulk() }&cliente=${ bulk.getPignorado() }" type="button" class="btn btn-default btn-block">Imprimir
						Etiqueta</a></div> 
					<div class="col col-lg-3 col-lg-offset-1"><button type="button"
																	  <c:if test="${ finalizado }">disabled</c:if>
																	  class="btn btn-primary btn-block" onclick="validarYSubir('S');">Guardar Bulk</button></div>
                    </c:if>																	  
					<c:if test="${ tipo=='D' }">
					<div class="col col-lg-3 col-lg-offset-1"><button type="button"
						<c:if test="${ finalizado }">disabled</c:if>
						 class="btn btn-primary btn-block" onclick="validarYSubir('${tipo}');">Guardar Bulk e Imprimir Etiqueta</button></div>
                    </c:if>																	  
					<div class="col col-lg-3 col-lg-offset-1"><a href="/wms-aldesa/web/arribos" class="btn btn-default btn-block">Salir</a></div>
				</c:if>
			</div>
		</div>
	</div>
</div>

