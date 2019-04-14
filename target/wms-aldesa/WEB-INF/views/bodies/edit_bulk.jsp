<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Hero Area Section -->
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
<div class="row" style="padding: 10px;">
    <div class="col col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
        <h2 style="text-align: center;">Modificar Bulk</h2>
        <h4 style="text-align: center;">Editar Detalle Mercaderia Recibida</h4>
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
                        <c:if test="${ tipo=='D' }">Desconsolidado</c:if>
                    </strong></h4>
                </div>
            </div>
            <!--  
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
            -->
        </div>
    </div>
</div>
<div class="row" style="padding: 5px;">
    <div class="col col-lg-1"></div>
    <div class="col col-lg-10">
        <div class="container">
            <form method="post" id="guardar-detalles">
                <input type="hidden" name="fdescarga" value="" id="fdescarga"/>
                <div class="row" style="padding:5px;">
                    <div class="col col-lg-2">
                        <h5>Bulk: <strong>${ bulk.getCodigoBulk() }</strong></h5>
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
                                               <c:if test="${ ha_creado }">disabled</c:if> name="profundidad" placeholder="999" required value="${ bulk.getProfundidad() }" class="form-control" <c:if test="${ bulk==null }">disabled</c:if>  />
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
                    var validarDiferencia = function(declarado, valor){
                        var d = parseInt(declarado,10);
                        var v = parseInt(valor,10);
                        if(d-v<0)
                            alert('Valor digitado mayor al declarado');
                    };
                   
                </script>
                <table class="table table-tripped">
                    <tr>
                        <th>Codigo</th>
                        <th>Descripcion</th>
                        <th>Por Bulk</th>
                        <th>Estado de Mercaderia</th>
						<th>No. lote</th>
						<th>Fecha de Vto</th>
                        <th>Posee sobrante?</th>
                    </tr>
                    <c:forEach var="m" items="${detalles}" varStatus="loop">
                        <tr>
                            <td>${ m.getId().getCodigoProducto() }<input type="hidden" value="${ m.getId().getCodigoProducto() }" name="cod"/>
							<input type="hidden" value="${ m.getItem() }" name="item"/></td>
                            <td>${ m.getUnidadMedida() }</td>
                            <td><input type="number" pattern="\d*" class="form-control" step="1" min="1" name="porbulk" value="${ m.getCantidad().intValue() }"/></td>
                            <td>
                                <select name="estado"  class="form-control"
                                        <c:if test="${ finalizado }">disabled</c:if>
                                        <c:if test="${ ha_creado }">disabled</c:if>
                                        <c:if test="${ bulk==null }">disabled</c:if>  >
                                  	<c:forEach items="${estados_mercaderia}" var="estado">
										<option
												<c:if test="${estado.getCodigo()==m.getEstadoMerc()}">
													selected
												</c:if>
												value="${estado.getCodigo()}">
												${estado.getDescripcion()}
										</option>
									</c:forEach>
                                </select>
                            </td>
                            <td>
                            	<input type="hidden" name="nolote" id="nolote" value="${ m.getnLote()}"/>
								<input type="text" class="form-control det_bulk_nolote"
								disabled
								value="${ m.getnLote()}"
								/>
							</td>
							<td>
							    <input type="hidden" name="fechavto" id="fechavto" value="${ m.getFechaVto()}"/>
							
								<input type="text" class="form-control class_fechavto" disabled value="${ m.getFechaVtoString()}"/>
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
                <input type="hidden" name="_method" value="_put_det_bulk"/><input type="hidden" name="codbulk" value="${ bulk.getCodigoBulk() }" /><input type="hidden" name="deposito" value="${ deposito }" /><input type="hidden" name="recepcion" value="${ recepcion }" /><input type="hidden" name="entrega" value="${ entrega }" />
            </form>
            <form method="post" id="nuevobulk" action="/wms-aldesa/web/arribos_detalle">
                <input type="hidden" name="tipo" value="${ tipo }" id="tipo-hidden"/>
                <input type="hidden" name="_method" value="put"/>
                <input type="hidden" name="deposito" value="${ deposito }" id="deposito-hidden"/>
                <input type="hidden" name="recepcion" value="${ recepcion }" />
                <input type="hidden" name="entrega" value="${ entrega }" />
            </form>

            <input type="hidden" name="tipo" value="${ tipo }"/>
            <div class="row">
                <c:if test="${ bulk==null && !ha_creado }">
                    <div class="col col-lg-3 col-lg-offset-1"><button type="button"
                                                                      <c:if test="${ finalizado }">disabled</c:if> onclick="$('#nuevobulk').submit();" class="btn btn-default btn-block">Nuevo Bulk</button></div>
                    <div class="col col-lg-3 col-lg-offset-1"><a href="/wms-aldesa/web/arribos_detalle_ver?cliente=${ cliente.getCliente_No() }&deposito=${ deposito }" class="btn btn-default btn-block">Resumen</a></div>
                    <div class="col col-lg-3 col-lg-offset-1"><a href="/wms-aldesa/web/arribos_detalle_ver?cliente=${ cliente.getCliente_No() }&deposito=${ deposito }" class="btn btn-default btn-block">Salir</a></div>
                </c:if>
                <c:if test="${ ha_creado }">
                    <div class="col col-lg-2 col-lg-offset-1"><a href="/wms-aldesa/web/arribos_detalle?deposito=${ deposito }&recepcion=1&tipo=${ tipo }&entrega=1" class="btn btn-default btn-block">Nuevo Bulk</a></div>
                    <div class="col col-lg-2 col-lg-offset-1"><a target="_blank" href="/wms-aldesa/web/barcode?valor=${ bulk.getCodigoBulk() }&cliente=${ cliente.getCliente_No() }" type="button" class="btn btn-default btn-block">Imprimir
                        Etiqueta</a></div>
                    <div class="col col-lg-2 col-lg-offset-1"><a href="/wms-aldesa/web/arribos_detalle_ver?cliente=${ cliente.getCliente_No() }&deposito=${ deposito }&tipo=${ tipo }" class="btn btn-default btn-block">Resumen</a></div>
                    <div class="col col-lg-2 col-lg-offset-1"><a href="/wms-aldesa/web/arribos_detalle_ver?cliente=${ cliente.getCliente_No() }&deposito=${ deposito }&tipo=${ tipo }" class="btn btn-default btn-block">Salir</a></div>
                </c:if>
                <c:if test="${ bulk!=null && !ha_creado }">
                    <script type="text/javascript">
                        var validarYSubir = function(){
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
                            $("#deposito-hidden").val("${ deposito }".split(',')[0]);
                            $("#tipo-hidden").val("${ tipo }".split(',')[0]);
                            $('#guardar-detalles').submit();
                        };
                    </script>
                    <c:if test="${ tipo=='S' }">
                    <div class="col col-lg-3 "><a target="_blank" href="/wms-aldesa/web/barcode?valor=${ bulk.getCodigoBulk() }&cliente=${ cliente.getCliente_No() }" type="button" class="btn btn-default btn-block">Imprimir
                        Etiqueta</a></div>
                        </c:if>
                    <c:if test="${ tipo=='D' }">
                    <div class="col col-lg-3 "><a target="_blank" href="/wms-aldesa/web/barcode?valor=${ bulk.getCodigoBulk() }&cliente=${cliente.getCliente_No()}-${cliented}" type="button" class="btn btn-default btn-block">Imprimir
                        Etiqueta</a></div>
                        </c:if>

                    <div class="col col-lg-3 "><button type="button"
                                                       <c:if test="${ finalizado }">disabled</c:if>
                                                       class="btn btn-primary btn-block" onclick="validarYSubir();">Guardar Bulk</button></div>
                    <div class="col col-lg-3 "><a href="/wms-aldesa/web/arribos_detalle_ver?cliente=${ cliente.getCliente_No() }&deposito=${ deposito }&tipo=${ tipo }" class="btn btn-default btn-block">Resumen</a></div>
                    <div class="col col-lg-3"><a href="/wms-aldesa/web/arribos" class="btn btn-default btn-block">Salir</a></div>
                </c:if>
            </div>
        </div>
    </div>
</div>
