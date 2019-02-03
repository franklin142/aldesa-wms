<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col col-lg-2"></div>
    <div class="col col-lg-8">
        <c:if test="${ tipo=='S' }"><h2>Datos Generales de Recepcion de Mercaderia Simple</h2></c:if>
        <c:if test="${ tipo=='F' }"><h2>Datos Generales de Recepcion de Mercaderia Fiscal</h2></c:if>
        <c:if test="${ tipo=='D' }"><h2>Datos Generales de Recepcion de Mercaderia Desconsolidada</h2></c:if>
            <form class="form-horizontal" action="arribos_recepcion/"
                  method="post">
                <div class="row">
                    <div class="col col-md-4 col-md-offset-2">
                        <div class="form-group">
                            <label for="deposito" class="col-sm-3 control-label">Deposito</label>
                            <div class="col-sm-9">
                                <input class="form-control" id="deposito" disabled
                                       placeholder="Deposito"
                                       value="${ mercaderia.getMercadRecibidaPK().getDepositoNo() }">
                        </div>
                    </div>
                </div>
                <div class="col col-md-4">
                    <div class="form-group">
                        <label for="fecha-recepcion" class="col-sm-3 control-label">Fecha
                            Recepcion</label>
                        <div class="col-sm-9">
                            <input class="form-control datepicker" id="fecha-recepcion" disabled
                                   placeholder="Fecha - Recepcion" 
                                   value="${ fecharecep }" data-date-format="dd/mm/yyyy"
                                   data-provide="datepicker-inline" type="date">
                        </div>

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="bodega" class="col-sm-4 control-label">Bodega/Patio</label>
                <div class="col-sm-4">
                    <select id="bodegaSelect" onchange="cambiarMuelleSelect()">
                    </select>
                    <input class="form-control" id="bodega" name="bodega" type="hidden"
                           placeholder="Bodega" value="${ mercaderia.getBodega() }">
                </div>
            </div>
            <div class="form-group">
                <label for="muelle" class="col-sm-4 control-label">Muelle</label>
                <div class="col-sm-4">
                    <select id="muelleSelect" onchange="cambioMuelle()">
                    </select>
                    <input class="form-control" id="muelle" name="muelle" type="hidden"
                           placeholder="Muelle" value="${ obj2.getMuelle() }">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <div class="checkbox">
                        <label for="dan"> <input type="checkbox" name="dan" id="dan"
                                                 <c:if test="${mercaderia.getUtilizaDAN()}">checked</c:if>>
                                                 Revision por la DAN en ALDESA
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="capacidadv" class="col-sm-4 control-label">
                        Tipo de Vehículo de Transporte
                    </label>
                    <div class="col-sm-4">
                        <input class="form-control datepicker" name="capacidadv"
                               value="${ obj2.getCapacidad() }" id="capacidadv"
                        placeholder="capacidadv" disabled>
                </div>
            </div>
            <div class="form-group">
                <label for="fecha-actualizacion" class="col-sm-4 control-label">Fecha
                    de corte de Marchamo</label>
                <div class="col-sm-4">
                    <input class="form-control datepicker" name="factualizacion"
                           value="${ fecha }" id="fecha-actualizacion" onblur="fn_validateDate()"
                           placeholder="Fecha actualizacion" data-date-format="dd/mm/yyyy"
                           type="date">
                </div>
            </div>
            <div class="form-group">
                <label for="hora-actualizacion" class="col-sm-4 control-label">Hora
                    de corte de Marchamo</label>
                <div class="col-sm-4">
                    <input id="timepicker" class="form-control"
                           data-provide="timepicker" data-template="modal"
                           data-minute-step="1" data-modal-backdrop="true" type="time"
                           name="hactualizacion" value="${ hora }" id="hora-actualizacion"
                           required placeholder="Hora actualizacion formato 24 horas"
                           pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}" />
                </div>
                <%-- modificacion luis mejora wms--%>

  </div>
	  <c:if test="${ tipo!='S' }">
                <div class="form-group">
                    <label for="ffcarga" class="col-sm-4 control-label">Fecha Fin de tránsito</label>
                    <div class="col-sm-4">
                        <input class="form-control" id="fecha-actualizacion" name="fftransito" onblur="fn_validateDate()"
                               placeholder="Fin de transito" type="date" value="${ fechadt }">
                    </div>
                </div>
                <div class="form-group">
                    <label for="hfcarga" class="col-sm-4 control-label">Hora Fin de tránsito</label>
                    <div class="col-sm-4">
                        <input id="timepicker" class="form-control"
                               data-provide="timepicker" data-template="modal"
                               data-minute-step="1" data-modal-backdrop="true" type="time"
                               name="hftransito" value="${ horadt }" id="hftransito"
                               required placeholder="Hora Fin de transito formato 24 horas"
                               pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}" />
                    </div>

                </div> 
 </c:if>
                <%-- fin de mejora--%>

                <script>

                    var bodegas = [<c:forEach var="b" items="${bodegas}" varStatus="loop">
                    {bodega:'${b.getBodega()}', descripcion:'${b.getDescripcion()}'},
                    </c:forEach>
                    ];
                            var muelles = [<c:forEach var="m" items="${muelles}" varStatus="loop">
                            {bodega:'${m.getId().getBodega()}', codigo:'${m.getId().getCodigo()}', nombre:'${m.getNombre()}'},
                    </c:forEach>
                            ];
                            var cambiarMuelleSelect = function () {
                                var e = document.getElementById('bodegaSelect');
                                var e2 = document.getElementById('muelleSelect');
                                e2.options.length = 0;
                                var value = e.options[e.selectedIndex].value;
                                var muelle = null;
                                var vmuelle = null;
                                var vbodega = null;
                                var opt = null;
                                vbodega = $("#bodegaSelect").val();
                                $("#bodega").val(vbodega);
                                for (var i = 0; i < muelles.length; i++) {
                                    muelle = muelles[i];
                                    if (muelle.bodega != value)
                                        continue;
                                    opt = document.createElement('option');
                                    opt.value = muelle.codigo;
                                    opt.innerHTML = muelle.nombre;
                                    e2.appendChild(opt);
                                }
                                vmuelle = $("#muelleSelect").val();
                                $("#muelle").val(vmuelle);
                            };
                    var cambioMuelle = function () {
                        var vmuelle = null;
                        vmuelle = $("#muelleSelect").val();
                        $("#muelle").val(vmuelle);
                    };
                    var fn_DateCompare = function (DateA, DateB) {
                        var a = new Date(DateA);
                        var b = new Date(DateB);

                        var msDateA = Date.UTC(a.getFullYear(), a.getMonth() + 1, a.getDate());
                        var msDateB = Date.UTC(b.getFullYear(), b.getMonth() + 1, b.getDate());

                        if (parseFloat(msDateA) < parseFloat(msDateB))
                            return -1;  // less than
                        else if (parseFloat(msDateA) == parseFloat(msDateB))
                            return 0;  // equal
                        else if (parseFloat(msDateA) > parseFloat(msDateB))
                            return 1;  // greater than
                        else
                            return null;  // error
                    };

                    var fn_validateDate = function () {
                        var a = new Date($("#fecha-actualizacion").val());
                        var hoy = new Date();
                        if (parseFloat(a.getDate()) > parseFloat(hoy.getDate())) {
                            alert("No se puede poner una fecha valor mayor al dia de hoy");
                            $("#fecha-actualizacion").val(hoy());
                            return;
                        } else {
                            if (parseFloat(a.getMonth() + 1) > parseFloat(hoy.getMonth() + 1)) {
                                alert("No se puede poner una fecha valor mayor al mes actual");
                                $("#fecha-actualizacion").val(hoy());
                                return;
                            }
                        }
                        if (parseFloat(a.getMonth() + 1) < parseFloat(hoy.getMonth())) {
                            alert("No se puede poner una fecha valor anterior al mes actual");
                            $("#fecha-actualizacion").val(hoy());
                            return;
                        }

                    };

                    var e = document.getElementById('bodegaSelect');
                    var e2 = document.getElementById('muelleSelect');
                    e.options.length = 0;
                    e2.options.length = 0;
                    var bod = null;
                    var opt = null;
                    var muelle = null;
                    for (var i = 0; i < bodegas.length; i++) {
                        var bod = bodegas[i];
                        opt = document.createElement('option');
                        opt.value = bod.bodega;
                        opt.innerHTML = '' + bod.bodega + ' ' + bod.descripcion;
                        if (bod.bodega == '${mercaderia.getBodega()}')
                            opt.selected = true;
                        e.appendChild(opt);
                    }

                    for (var i = 0; i < muelles.length; i++) {
                        muelle = muelles[i];
                        if (muelle.bodega != '${mercaderia.getBodega()}')
                            continue;
                        opt = document.createElement('option');
                        opt.value = muelle.codigo;
                        opt.innerHTML = muelle.nombre;
                        if (muelle.nombre == '${obj2.getMuelle()}')
                            opt.selected = true;
                        e2.appendChild(opt);
                    }

                </script>
          


            <div class="form-group">
                <label for="descargado-por" class="col-sm-4 control-label">Descargado
                    por</label>
                <div class="col-sm-4">
                    <select class="form-control" id="descargado-por" name="descargado">
                        <option value="1"
                                <c:if test="${mercaderia.getDescargadopor()}">selected</c:if>>ALDESA</option>
                                <option value="0"
                                <c:if test="${!mercaderia.getDescargadopor()}">selected</c:if>>CLIENTE</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-offset-1 col-lg-4 col">
                        <button type="submit" class="btn btn-success btn-lg btn-block">Ok</button>
                    </div>
                    <div class="col-lg-offset-2 col-lg-4 col">	
                        <button type="reset" class="btn btn-default btn-lg btn-block">Cancelar</button>
                    </div>
                </div>
                <input type="hidden"
                       value="${ mercaderia.getMercadRecibidaPK().getDepositoNo() }"
                name="deposito" /> <input type="hidden"
                value="${ mercaderia.getMercadRecibidaPK().getRecepcionMercaderiaNo() }"
                name="recepcion" /> <input type="hidden"
                value="${ mercaderia.getMercadRecibidaPK().getEntregaNo() }"
                name="entrega" />
            <input type="hidden"
                   value="0"
                   name="capacidad" />
            <input type="hidden"
                   value="${ fecharecep }"
                   name="frecepcion" data-date-format="dd/mm/yyyy" type="date"/>

        </form>
    </div>
</div>
