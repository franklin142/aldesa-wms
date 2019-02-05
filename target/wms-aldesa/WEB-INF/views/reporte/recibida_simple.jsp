<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>INFORME DE MERCADERIA RECIBIDA NO. ${ enc.getDeposito() } }</title>

<!-- Bootstrap -->
<link href="/wms-aldesa/static/resources/css/bootstrap.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <script type="application/javascript" src="/wms-aldesa/static/resources/js/JsBarcode.all.min.js"> </script>
    <![endif]-->
</head>
<body>
	<style>
    body{font-size: 8pt;}
	.mainrpt{ width: 715px; margin: 0 auto; display: table;  }
    td{padding: 2px !important;}
    th{padding: 2px !important;}
    @media print {
        @page {
            margin: 0;
            @bottom-left {
                content: counter(page) "/" counter(pages);
            }
        }
        body { margin-top: 1.6cm; margin-bottom: 1.6cm; }
        #content {
            display: table;
        }

        #pageFooter {
            display: table-footer-group;
        }

        #pageFooter:after {
            counter-increment: page;
            content: counter(page);
        }
        #header{display: table-header-group;}
    }
	</style>
	<div class="row mainrpt">
        <div id="pageFooter">Page </div>
		<div class="col col-lg-12 col-md-12 col-sm-12">
            <div id="header">
                <div class="row">
                    <div class="col col-lg-12">
                        <table>
                            <tr>
                                <td rowspan="3" width="30%">
                                    <img alt="aldesa-logo" width="100%"
                                        src="/wms-aldesa/static/resources/img/logo.jpg">
                                </td>
                                <td style="font-size: 9pt" width="55%">ALMACENES DE DESARROLLO, S.A.</td>
                                <td style="text-align: right;" width="15%">Emisi&oacute;n: ${ fecha }</td>
                            </tr>
                            <tr><td style="font-size: 8pt">Boulevard del Ej&eacute;rcito, Km. 7 1/2 Soyapango, El
                                Salvador C.A</td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td style="text-align: right;">${ hora }</td></tr>
                            <tr>
                                <td style="font-size: 9pt">PBX: (503) 2121-1950, FAX: (503) 2121-1951</td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td style="text-align: right;"><span id="usuario-lbl"></span></td> <!-- poner usuario -->
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <table width="100%" class="col col-lg-12">
                        <tr>
                            <td width="40%" style="font-size: 15px; font-weight: bold;">INFORME DE MERCADERIA RECIBIDA</td>
                            <td width="30%"><!-- ${ enc.getNumeroInforme() } --></td>
                            <td width="30%">
                                <c:if test="${tipo.equalsIgnoreCase('S')}"><strong>ALMACENAMIENTO SIMPLE</strong></c:if>
                                <c:if test="${tipo.equalsIgnoreCase('F')}"><strong>DEPOSITO DE ADUANA (RECINTO FISCAL)</strong></c:if>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Fecha:</strong> ${ fecha }</td>
                            <td colspan="2">
                                <strong>(Carta de Aceptacion de carga o Notificacion)</strong> ${enc.getCartaPorte()}<br/>
                                <strong>BL o carta de Porte:</strong>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="row">
                    <div class="col col-lg-12 col-sm-12">
                        <table style="border-collapse: collapse;" class="header table">
                            <tr>
                                <td><strong>Cliente: </strong>${ enc.getNombre()}</td>
                                <td><strong>Dep&oacute;sito: </strong>${ enc.getDeposito() }</td>
                            </tr>
                            <tr>
                                <td><strong>Tipo de Mercader&iacute;a: </strong>${ enc.getTipoMercaderia() }</td>
                                <td><strong>Procedencia: </strong> ${enc.getProcedencia()}</td>
                            </tr>
                            <tr>
                                <td><strong>Fecha y hora de entrada del
                                    veh&iacute;culo de transporte</strong></td>
                                <td>${ enc.getFechaHoraEntrada() }</td>
                            </tr>
                            <tr>
                                <td><strong>Motorista: </strong>${ enc.getMotorista() }</td>
                                <td><strong>Licencia No. </strong>${ enc.getLicenciaNo() }</td>
                            </tr>
                            <tr>
                                <td><strong>Cia. de Transporte </strong>${ enc.getCiaTransporte() }</td>
                                <td><strong>C&oacute;digo de Transporte </strong>${ enc.getCodigoTransporte() }</td>
                            </tr>
                            <tr>
                                <td><strong>Placa de veh&iacute;culo </strong>${ enc.getPlaca() }</td>
                                <td><strong>No. Marchamo </strong>${ enc.getMarchamoNo() }</td>
                            </tr>
                            <tr>
                                <td style="padding-right: 10px;"><span style="float: left;"><strong>DMTI: </strong>${ enc.getDmti()  }</span>
                                	<span style="float: right; padding-right: 30px;"><strong>C&oacute;digo
                                    Contenedor: </strong>${ enc.getCodigoContenedor() } </span>
                                    <span class="clear"></span></td><td style="padding-left: 250px; text-align: left;"><strong>Contenedores
                                    por dep&oacute;sito: </strong>${ enc.getContenedorPorDeposito() }</td>
                            </tr>
                            <tr>
                                <td colspan="2"><span style="float: left;"><strong>Hora autorizacion de corte
                                    de marchamo: </strong>${ enc.getHoraMarchamo() }</span> <span style="float: right;"><strong>Fecha y hora
                                    finalizacion tr&aacute;nsito por Aduana: </strong>${ enc.getFHTransitoAduana() }</span></td>
                            </tr>
                            <tr>
                                <td><strong>Fecha de descarga: </strong>${ enc.getFDescarga() }
                                    <strong>Hora Inicio:</strong> ${ enc.getHInicio() } <strong>Hora
                                        fin:</strong> ${ enc.getHFin() } </td><td style="padding-right: 10px;">
                                        <span style="float: left;"><strong>Ubicacion</strong> ${ enc.getBodega() }</span> 
                                        <span style="float: right;"><strong>Muelle:</strong> ${ enc.getMuelle() }</span>
                                        <span class="clear"></span></td>
                            </tr>
                            <tr>
                                <td><strong>Descargado por: </strong>${ enc.getDescargado() }
                                    <strong>Mercaderia Paletizada?</strong> ${ forma } </td><td><strong>Utiliza
                                        DAN?</strong> ${ enc.getDan() }</td>
                            </tr>
                            <tr>
                                <td><strong>Bulks Armados: </strong>${ enc.getTarimasRecibidas() }<span style="width: 5px;">&nbsp;</span>
                                    <strong>Bulks flejados:</strong> ${ enc.getTarimasFlejadas() } <span style="width: 5px;">&nbsp;</span><strong>
                                        &Aacute;rea ocupada: </strong> ${ enc.getAreaOcupada() } m&sup2;</td><td>
                                    <strong>Volumen ocupado </strong> ${ enc.getVolumenOcupado() } m&sup3;
                                    <strong>Peso kilogramos: </strong> ${ enc.getPeso() }</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

            <div>
                <div class="row">
                    <div class="col col-lg-12 col-sm-12">
                        <table class="table" style="font-size: 9pt;">
                            <thead>
                            <thead>
                            <tr>
                                <th rowspan=2 width="15%">Codigo Mercaderia</th>
                                <th rowspan=2 width="10%">Codigo Referencia</th>
                                <th rowspan=2 width="25%">Descripcion Mercaderia</th>
                                <th colspan=3 width="40%" style="text-align:center">Cantidad de Bultos</th>
                                <th rowspan=2 width="10%">Unidad Medida</th>
                            </tr>
                            <tr>
                                <th>Declarada</th>
                                <th>Rec Buena</th>
                                <th>Rec Averiada</th>
                                <th>Diferencia</th></tr>
                            </thead>
                            </thead>
                            <tbody>
                            <c:forEach var="m" items="${ ingresos }" varStatus="loop">
                                <tr>
                                    <td>${ m.getCodigoMercaderia() }</td>
                                    <td>${ m.getCodigoReferencia() }</td>
                                    <td>${ m.getDescripcion() }</td>
                                    <td>${ m.getDeclarada() }</td>
                                    <td>${ m.getRecibida() }</td>
                                    <td>${ m.getAveriada() }</td>
                                    <td>${ m.getSaldo() }</td>
                                    <td>${ m.getUnidadMedida() }</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3"></td>
                                <td>${ tdeclarada }</td>
                                <td>${ trecibida }</td>
                                <td>${ taveriada }</td>
                                <td>${ tsaldo }</td>
                                <td></td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-lg-12 col-sm-12"><p>Observaciones ${ observacion }</p></div>
                </div>
                <div class="row">
                    <div class="col col-lg-12 col-sm-12">
                        <table width="100%">
                            <tr><td width="40%">CLIENTE</td><td width="20%"></td><td width="40%">Recibe mercaderia</td></tr>
                            <tr><td>Entrega de mercader&iacute;a </td><td></td><td>Jefe de Bodega</td></tr>
                            <tr><td>${ entregado }_____________________</td><td style="text-align: center;"></td><td>${ recibido } _____________________</td></tr>
                            <tr><td></td><td style="text-align: center;">Sello</td><td></td></tr>
                            <tr><td>Visto Bueno</td><td></td><td>Fecha:______________</td></tr>
                            <tr><td>Jefe de Produccion</td><td></td><td>Hora:__________________</td></tr>
                            <tr><td>${ enc.getJefeProduccion() }<c:if test="${ vista==1 }">	Reporte Preliminar</c:if><c:if test="${ vista==1 }">	_____________________</c:if></td><td></td><td>Funcionario de Aduana _________________________</td></tr>
                        </table>

                    </div>
                </div>
            </div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/wms-aldesa/static/resources/js/jquery-min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/wms-aldesa/static/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
        function getCookie(c_name) {
            var c_value = " " + document.cookie;
            var c_start = c_value.indexOf(" " + c_name + "=");
            if (c_start == -1) {
                c_value = null;
            }
            else {
                c_start = c_value.indexOf("=", c_start) + 1;
                var c_end = c_value.indexOf(";", c_start);
                if (c_end == -1) {
                    c_end = c_value.length;
                }
                c_value = unescape(c_value.substring(c_start,c_end));
            }
            return c_value;
        }
		document.getElementById("usuario-lbl").textContent=getCookie('user');
        var closeOnFinished = function(fn){
            fn();
            if (vista=0)
                setTimeout(function(){window.location='/wms-aldesa/web/arribos'}, 6000);
            else
                setTimeout(function(){window.location='/wms-aldesa/web/recepcion_mercaderia?cliente=${ cliente.getCliente_No() }&deposito=${ deposito }'}, 6000);
        };
        closeOnFinished(window.print);

	</script>
</body>
</html>