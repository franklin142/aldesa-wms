<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html>
<html lang="en">
<head>
    <title>ORDEN DE PREPARACION NO. ${ orden }</title>
    <script src="/wms-aldesa/static/resources/js/pdfmake.min.js"></script>
    <script src="/wms-aldesa/static/resources/js/vfs_fonts.js"></script>
    <script src="/wms-aldesa/static/resources/js/aldesa-logo.js"></script>
    <script src="/wms-aldesa/static/resources/js/JsBarcode2.js"> </script>
</head>
<body>
<style>
    .report{width:90%; height: 90%; margin: 0 auto; padding-top: 1%;}
    object{width: 100%; height: 600px;}
</style>
<div class="report">
    <object id="object">
    </object>
</div>
<script>

    var getCookie = function (c_name) {
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
    };

    var opts = {
        width:  1,
        height: 10,
        quite: 10,
        format: "CODE39",
        backgroundColor:"#fff",
        lineColor:"#000"
    };

    var usuario = getCookie('user');
    if (usuario===null) usuario=' ';
    if (usuario===null) usuario=' ';
    var _left = 25;
    var _right = 25;
    var _top = 90;
    var _bottom = 20;

    var dd = {
        // a string or { width: number, height: number }
        pageSize: 'LETTER',

        // by default we use portrait, you can change it to landscape if you wish
        pageOrientation: 'landscape',

        // [left, top, right, bottom] or [horizontal, vertical] or just a number for equal margins
        pageMargins: [_left, _top, _right, _bottom],
        fontSize: 9,
        header: function(currentPage, pageCount){
            return{
                fontSize: 9,
                margin: [0,25,25,25],
                stack: [
                    {
                        columns: [
                            {width: 150, margin:0, stack:[
                                {image: aldesaLogo, width: 150},
                                {text: 'CLIENTE: ', alignment: 'right', margin:[0,10,0,0],fontSize: 12}
                            ]},
                            {stack: [
                                {text: 'ORDEN DE PREPARACION No ${orden}', alignment: 'center',fontSize: 14},
                                {text: '${ordenobj.getNombre()}',fontSize: 12, margin:[10, 23,0,0]}
                            ]},
                            {stack: [
                                {text: 'EMISION ${fecha}',fontSize: 11},
                                {text: 'Pag. '+currentPage.toString() + ' / ' + pageCount,fontSize: 11},
                                {
                                    margin:[0,15,0,0],
                                    image: JsBarcodeBase64("","${orden}", opts), alignment: 'left'
                                }],
                                alignment: 'right',
                                width: 200
                            }
                        ]
                    }
                ]
            }
        },
        content: {
            fontSize: 8,
            stack:[
            <c:forEach var="d" items="${depositos}" varStatus="loop">
                {text:'\n\nDEPOSITO: ${ d }\n\n', bold:true, fontSize: 10},
                {table:{
                    headerRows:1,
                    margins:[0,10,0,0],fontSize: 8,
                    body:[
                        ['Codigo','Descripcion','Codigo de Barra',
                            'Cantidad solicitada','Cantidad Preparada','Saldo',
                            'Ubicacion','Bulk', 'Estatus Ubicacion', 'Estado', 'Tipo de ubicacion'],
                        <c:forEach var="merc" items="${recepcion_mercaderia}" varStatus="loop">
                        <c:if test="${merc.getDeposito().equalsIgnoreCase(d)}" >
                        ['${merc.getCodigo()}', '${merc.getDescripcion()}', {image:JsBarcodeBase64("","${merc.getCodigo()}", opts)},
                            '${merc.getCantidad()}', '${merc.getCantidadPreparada()}', '${merc.getSaldos()}', '${merc.getEstante() }',
                            '${merc.getCodigoBulk()}', '${merc.getStatusUbicacion()}', '${merc.getEstadoMercaderia()}', '${merc.getTipoUbicacion()}'
                        ], ['Sub Total', ' ', ' ',
                            '${subtotsol[d+merc.getCodigo()]}', '${subtotpre[d+merc.getCodigo()]}', ' ', ' ',
                            ' ', ' ', ' ', ' '
                        ],
                        </c:if> </c:forEach>
                        ['Total', '', '', '${tdepositos[d]}', '',
                            '', '', '', '', '', '']
                    ]}
                },</c:forEach>
                {
                columns:[
                    'TOTAL SOLICITADA ${tcantidad}',
                    'TOTAL PREPARADA ${tpreparada}'
                ]}
        ]}
    };

    var asdf = pdfMake.createPdf(dd);
    asdf.open2 = function(message){
        try {
            this.getDataUrl(function(result) {
                var objtag = document.getElementById('object');
                objtag.data = result;
                objtag.focus();
            });
        } catch(e) {
            throw e;
        }
    };
    asdf.open2();
</script>

</body>
</html>
