<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html>

<script>
	//Franklin flores... Informe de reingreso/ modificar
    var opts = {
        width:  1,
        height: 10,
        quite: 10,
        format: "CODE39",
        backgroundColor:"#fff",
        lineColor:"#000"
    };

    var usuario = getCookie('user');
    if(usuario==null) document.location.href ='/wms-aldesa/rest/auth/login-web';
    if (usuario===null) usuario=' ';
    if (usuario===null) usuario=' ';
    var _left = 25;
    var _right = 25;
    var _top = 80;
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
                margin: [0,25,25,5],
                stack: [
                    {
                    	margin: [25, 0, 0, 0],
                    	columns: [
                            {width: 150, margin:0, stack:[
                                {image: aldesaLogo, width: 50}
                            ]},
                            {stack: [
                                {text: 'ORDEN DE REINGRESO No ${orden}', alignment: 'center',fontSize: 14},
                                {text: 'CLIENTE: ${ordenobj.getNombre()}',fontSize: 12, margin:[10, 23,0,0]}
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
                    <c:forEach var="d" items="${depositos}" varStatus="loop">{text:'\n\nDEPOSITO: ${ d }\n\n', bold:true, fontSize: 9},
                {table:{
                    headerRows:1,
                    margins:[0,2,0,0],fontSize: 8,
                    widths: [ 110, 160, 40, 40, 40, 40, '*', '*', '*'],
                    body:[
                        ['Codigo','Descripcion', 'Estado Merc. Solicitada',
                            'Cantidad solicitada','Cantidad Preparada','Cantidad Entregada', 'Reingreso',
                            'Ubicacion de Reingreso','Bulk'],<c:forEach var="merc" items="${recepcion_mercaderia}" varStatus="loop"> <c:if test="${merc.getDeposito().equalsIgnoreCase(d)}" >
                        ['${merc.getCodigo()}', '${merc.getDescripcion()}', '${merc.getEstado()}',
                            '${merc.getCantidad()}', '${merc.getCantidadEntregada()+merc.getPendiente()+merc.getCantidadAEntregar()}',
                            '${merc.getCantidadEntregada()}', '${merc.getPendiente()+merc.getCantidadAEntregar()}',
                            '${merc.getUbicacion() }', '${merc.getBulk()}'
                        ],</c:if> </c:forEach>
                        <c:forEach var="st" items="${subtotales}" varStatus="loop"> <c:if test="${d.equalsIgnoreCase(st[1])}" >
                        ['SUBTOTAL', ' ', ' ','${st[2]}', '${(st[4]+st[5]+st[3])}', '${st[4]}', '${st[5]+st[3]}', ' ', ' '],
                        </c:if> </c:forEach>
                    ]
                }}, </c:forEach>
                {text:' ', fontSize:15},
                {
                    table:{
                        headerRows:1,
                        margins:[0,5,0,0],fontSize: 8,
                        widths: [ 110 , 160, 40, 40, 40, 40, '*', '*', '*'],
                        body:[
                                ['TOTALES', ' ', ' ', '${totales[2]}', ' ${(totales[4]+totales[5]+totales[3])}', '${totales[4]}', '${totales[5]+totales[3]}', ' ',
                                    ' ']
                        ]
                    },
                }
            ]}
    };
</script> 
