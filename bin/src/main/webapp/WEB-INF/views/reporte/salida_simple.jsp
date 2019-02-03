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
    <title>INFORME DE SALIDAS DE MERCADERIA ${ retiro }</title>

    <!-- Bootstrap -->
    <link href="/wms-aldesa/static/resources/css/bootstrap.css"
          rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="application/javascript" language="JavaScript" src="/wms-aldesa/static/resources/js/JsBarcode.all.min.js"> </script>
</head>
<body>

	<style>
        body{font-size: 9pt;}
        .mainrpt{ width: 715px; margin: 0 auto; }
        td{padding: 2px !important;}
        th{padding: 2px !important;}
	</style>
<div class="row mainrpt">
    <div
            class="col col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col col-lg-12">
                <table width="100%">
                    <tr>
                        <td rowspan="3" width="20%">
                            <img alt="aldesa-logo" width="80px"
                                 src="/wms-aldesa/static/resources/img/logo.jpg">
                        </td>
                        <td style="font-size: 12pt" width="55%">ALMACENES DE DESARROLLO, S.A.</td>
                        <td width="5%"></td>
                        <td style="text-align: right;" width="20%">Emisi&oacute;n: ${ fecha }</td>
                    </tr>
                    <tr><td style="font-size: 8pt">Boulevard del Ej&eacute;rcito, Km. 7 1/2 Soyapango, El
                        Salvador C.A</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td style="text-align: right;">${ hora }</td></tr>
                    <tr>
                        <td style="font-size: 10pt">PBX: (503) 2121-1950, FAX: (503) 2121-1951</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td style="text-align: right;"><span id="usuario-lbl"></span></td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col col-lg-12">
                <table width="100%">
                    <tr>
                        <td width="50%"><h5><strong>INFORME DE SALIDAS DE MERCADERIA</strong></h5></td>
                        <td width="30%" style="text-align: right"><h6>
                            <c:if test="${tipo.equalsIgnoreCase('S')}"><strong>ALMACENAJE SIMPLE</strong></c:if>
                            <c:if test="${tipo.equalsIgnoreCase('F')}"><strong>ALMACENAJE FISCAL</strong></c:if>
                        </h6></td>
                        <td width="20%"><img id="barcode" style="display: none;"></td>
                    </tr>
                    <tr>
                        <td width="40%">Fecha: ${ enc.getFechaString() }</td>
                    </tr>
                </table>
            </div>
        </div>
        <script>
            var opts = {
                width:  1,
                height: 10,
                quite: 10,
                format: "CODE39",
                backgroundColor:"#fff",
                lineColor:"#000"
            };
            JsBarcode(document.getElementById("barcode") ,"${ retiro }", opts);
        </script>
        <style>
            .header tr{border: 1px dotted black;}
            .header td{border: 0;}
        </style>
        <div class="row">
            <div class="col col-lg-12 col-sm-12col-md-12">
                <table style="border-collapse: collapse; text-align: justify; text-justify: inter-word;" class="header table">
                    <tr><td colspan="3">Retiro Mercadera No. ${ retiro }</td><td>Salida No. Dep&oacute;: ${ enc.getSalidaNo() }</td></tr>
                    <tr><td colspan="2">Cliente: ${ enc.getCliente() } ${enc.getNombre()}</td><td colspan="2"></td></tr>
                    <tr><td colspan="2">Persona autorizada para retirar la mercaderia. </td><td colspan="2">Placa de veh&iacute;culo${ placa }</td></tr>
                    <tr><td>Motorista: ${ enc.getMotorista() }</td><td>
                        Licencia No. ${ enc.getLicencia_No() }</td><td  colspan="2">
                        No. Marchamo ${ enc.getMarchamo_No() }
                    </td></tr>
                    <tr>
                        <td >Ubicacion de mercaderia  Bodega ${ enc.getBodega() }</td>
                        <td> <strong>Fecha de carga: </strong>${ enc.getFechaDescargaString() }</td>
                        <td>Hora Inicio: ${ enc.getHoraInicioString() }</td>
                        <td>Hora fin: ${ enc.getHoraFinString() }
                        </td>
                    </tr>
                    <tr>
                        <td >
                            Cargado por: ALDESA</td><td>
                            Tipo Carga: ${ enc.getTipo() }</td><td>
                            Tarimas entregadas: ${ ctarifas }</td><td>
                            Tarimas Flejadas: ${ enc.getTarimasFlejadas() }
                        </td>
                </table>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col col-lg-12 col-sm-12 col-md-12">
                <h5>Detalle de la mercaderia entregada:</h5>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Deposito</th>
                        <th>Codigo</th>
                        <th>Descripcion</th>
                        <th>Bultos</th>
                        <th>Unidad Medida</th>
                        <th>Estado Mercaderia</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="m" items="${retiros}" varStatus="loop">
                        <tr>
                            <td>${ m.getDeposito() }</td>
                            <td>${ m.getCodigo() }</td>
                            <td>${ m.getDescripcion() }</td>
                            <td>${ m.getBultos() }</td>
                            <td>${ m.getUnidad_de_Medida() }</td>
                            <td>${ m.getEstado() }</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3">Total</td>
                        <td>${tbultos}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col col-lg-12 col-sm-12 col-md-12"><p>Observaciones ${ enc.getObservaciones() }</p></div>
        </div><br />
        <div class="row">
            <div class="col col-lg-8 col-sm-8 col-md-8 col-lg-offset-2 col-sm-offset-2 col-md-offset-2" style="border: 1px solid #000000;">
                <table>
                    <tr>
                        <td width="25%">LIQUIDACION DE ORDEN:</td>
                        <td width="15%">TOTAL ORDENADO:</td>
                        <td width="10%">${ tordenada }</td>
                        <td width="15%">TOTAL RETIRADO:</td>
                        <td width="10%">${ tbultos }</td>
                        <td width="15%">CANTIDAD RESTANTE</td>
                        <td width="10%">${ tdiferencia }</td>
                    </tr>
                </table>
            </div>
        </div><br /><br /><br />
        <div class="row">
            <table  width="100%" style="text-align: center;">
                <tr>
                    <td width="50%"><h6>F.________________________________</h6></td>
                    <td width="50%"><h6>F.________________________________</h6></td>
                </tr>
                <tr>
                    <td><h6>Entrega ed mercaderia por J.Bodega</h6></td>
                    <td><h6>Recibe Mercaderia</h6></td>
                </tr>
            </table>
        </div>
        <p>Productos recibidos a conformidad en cuantía y estado. ALDESA no acepta ningún reclamo después de haber sido recibida la mercadería por el cliente o su representante a su entera satisfacción en fe de lo que firma.</p>
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
        setTimeout(function(){window.location='/wms-aldesa/web/autorizar_entrega'}, 1000);
    };
    closeOnFinished(window.print);
</script>
</body>
</html>