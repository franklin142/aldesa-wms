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
    <title>ORDEN DE PREPARACION NO. ${ orden }</title>

    <!-- Bootstrap -->
    <link href="/wms-aldesa/static/resources/css/bootstrap.css"
          rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="application/javascript" src="/wms-aldesa/static/resources/js/JsBarcode.all.min.js"> </script>
    <style>
        body{font-size: 8pt;}
        .mainrpt{ width: 715px; margin: 0 auto; }
        td{padding: 2px !important;}
        th{padding: 2px !important;}
        @media print {
            @page { margin: 0; }
            body { margin-top: 1.6cm; margin-bottom: 1.6cm; }
        }
    </style>
</head>
<body>
<div class="row mainrpt">
<div class="col col-lg-12 col-md-12 col-sm-12">
    <div class="row">
    <div class="col col-lg-12">
                
        <table width="100%">
            <tr>
            <td width="20%"> </td>
            <td width="60%"> </td>
                <th style="text-align: right;" width="20%">Emisi&oacute;n: ${ fecha }</th>
            </tr>        
            <tr>
                <td width="20%">
                    <img alt="aldesa-logo" width="80px"
                         src="/wms-aldesa/static/resources/img/logo.jpg">
                </td>
                <th style="font-size: 12pt; text-align: center" width="60%">ORDEN DE PREPARACION</th>
                <th style="text-align: right;" width="20%">No. ${ orden } </th>
            </tr>

        </table>
    </div>
    </div>
    <div class="row">
    <div class="col col-lg-12">
        <table width="100%">
            <tr>
                <td width="20%">
                    <strong>CLIENTE:</strong>
                </td>
                <td style="font-size: 12pt; text-align: center" width="60%"></td>
                <td style="text-align: right;" width="20%"><img id="cliente-barcode" /></td>
            </tr>
        </table>
    </div>
    </div>
    <c:forEach var="d" items="${depositos}" varStatus="loop">
        <div class="row">
        <div class="col col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2">
            <strong>DEPOSITO:</strong> ${ d }
        </div>
        </div>
        <div class="row">
        <div class="col col-lg-12">
            <table width="100%" class="table">
                <tr><th>Codigo</th><th>Descripcion</th><th>Codigo de Barra</th><th>Cantidad solicitada</th>
                    <th>Pasillo</th><th>Area de piso</th><th>Estante</th><th>Alero</th><th>Patio</th></tr>
                <c:forEach var="merc" items="${recepcion_mercaderia}" varStatus="loop">
                <c:if test="${merc.getDeposito().equalsIgnoreCase(d)}" >
                    <tr>
                        <td>${merc.getCodigo()}</td>
                        <td>${merc.getDescripcion()}</td>
                        <td><img id="barcode-${d}-${merc.getCorrelativo()}" /></td>
                        <td>${merc.getCantidad()}</td>
                        <td>${merc.getPasillo() }</td>
                        <td>${merc.getPiso() }</td>
                        <td>${merc.getEstante() }</td>
                        <td>${merc.getAlero() }</td>
                    </tr>
                    <tr>
                        <td>Sub total</td>
                        <td></td>
                        <td></td>
                        <td>${subtotsol.get(d+merc.getCodigo())}</td>
                        <td>${subtotpre.get(d+merc.getCodigo())}</td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </c:if>
                </c:forEach>
                <tr><td colspan="3">Total</td>
                <td>
                	<c:out value="${tdepositos[d]}"/>
				</td>
				<td colspan="4"></td>
				</tr>
            </table>
        </div>
        </div>
    </c:forEach>
    <table class="table" width="100%">
        <tr>
            <td style="width: 30%; text-align: right;"><strong>Total General</strong></td>
            <td style="width: 20%; text-align: center;">${tcantidad}</td>
            <td style="width: 50%;"></td>
        </tr>
    </table>
</div><!-- .main col -->
</div><!-- .row .mainrpt -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/wms-aldesa/static/resources/js/jquery-min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/wms-aldesa/static/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">

    var opts = {
        width:  1,
        height: 10,
        quite: 10,
        format: "CODE39",
        backgroundColor:"#fff",
        lineColor:"#000"
    };
    JsBarcode(document.getElementById("cliente-barcode") ,"${ orden }", opts);
    <c:forEach var="merc" items="${recepcion_mercaderia}" varStatus="loop">
    JsBarcode(document.getElementById("barcode-${merc.getDeposito()}-${merc.getCorrelativo()}") ,"${ merc.getCodigo() }", opts);
    </c:forEach>
    var closeOnFinished = function(fn){
        fn();
        setTimeout(function(){window.location='/wms-aldesa/web/despacho'}, 3500);
    };
    closeOnFinished(window.print);
</script>
</body>
</html>
