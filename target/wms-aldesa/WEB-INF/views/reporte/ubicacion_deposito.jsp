<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html>

<script>
//Franklin Flores... modificar reporte de bulks creados
    var usuario = getCookie('user');
    if(usuario==null) document.location.href ='/wms-aldesa/rest/auth/login-web';
    if (usuario===null) usuario=' ';
    if (usuario===null) usuario=' ';
    var _left = 25;
    var _right = 25;
    var _top = 140;
    var _bottom = 20;

    var dd = {
        // a string or { width: number, height: number }
        pageSize: 'LETTER',

        // by default we use portrait, you can change it to landscape if you wish
        pageOrientation: 'landscape',

        // [left, top, right, bottom] or [horizontal, vertical] or just a number for equal margins
        pageMargins: [_left, _top, _right, _bottom],
        fontSize: 9,
        header: function (currentPage, pageCount) {
            return {
                margin: [0,25,25,25],
                fontSize: 9,
                stack: [
                    {
                    	margin: [25, 0, 0, 0],
                    	columns: [
                        {image: aldesaLogo, width: 50, margin:0},
                        {stack: [
                            {text: 'ALMACENES DE DESARROLLO, S.A.',fontSize: 14},
                            {text: 'Boulevard del Ejército, Km. 7 1/2 Soyapango, El Salvador C.A',fontSize: 12},
                            {text: 'PBX: (503) 2121­1950, FAX: (503) 2121­1951',fontSize: 12}
                        ]},
                        {stack: [
                            {text: 'EMISION ${ fecha }',fontSize: 9},
                            {text: usuario, fontSize: 9},
                            {text: 'PAGINA ' + currentPage + '/ ' + pageCount, fontSize: 9}],
                            alignment: 'right',
                            width: 95
                        }
                    ]
                    },
                    {
                        margin: [25, 15, 0, 0],
                        text: 'INFORME DE BULK CREADOS'
                    },
                    {
                        margin: [25, 10, 0, 0],
                        columns: [
                            {   text: 'DEPOSITO NO: ${deposito}',
                                width: 150
                            },
                            {   text: 'CLIENTE: ${cliente}'},
                            {   text: 'BODEGA: ${bodega}',
                                alignment: 'left',
                                width: 100
                            }]
                    }
                ]
            }},
        content: [
            {
                fontSize: 9,
                table:{
                    headerRows:1,
                    widths: [ 50,80,55,'*',45, 30,33,26,28,35,35,35,35,20 ],
                    body:[
                        [
                            'BULK', 'UBICACION', 'CODIGO', 'NOMBRE DEL PRODUCTO', 'CANTIDAD', 'UNIDAD', 'ESTADO',
                            'ANCHO', 'ALTO','PROFUN.','AREA','VOL.','PESO',
                            'BULK FLEJ?'],<c:forEach var="c" items="${ codigos }" varStatus="loop"><c:forEach var="b" items="${ bulks }" varStatus="loop"><c:if test="${c.equalsIgnoreCase(b.getCodigoProducto())}">[
                            '${b.getCodigo()}', '${b.getUbicacion()}', '${b.getCodigoProducto()}', '${b.getNombreProducto()}',
                            '${b.getCantidad()}', '${b.getUnidadMedida()}', '${b.getEstadoMerc()}',
                            '${b.getAncho()}', '${b.getAlto()}','${b.getProfundidad()}','${b.getArea()}','${b.getVolumen()}',
                            '${b.getPeso()}', '${b.getTarimaFlejada()}'
                        ],</c:if></c:forEach><c:forEach var="st" items="${ subtotales }" varStatus="loop"><c:if test="${c.equalsIgnoreCase(st[0].toString())}">[
                            ' ', ' ', ' ', {text:'TOTAL CODIGO ${d}',bold:true}, '${st[7]}', ' ', ' ',
                            '${st[1]}','${st[2]}','${st[3]}','${st[4]}','${st[5]}','${st[6]}', ' '
                        ],</c:if></c:forEach></c:forEach>[
                            ' ', ' ', ' ', {text:'TOTAL DEL DEPOSITO', bold:true}, '${tcantidad}', ' ', ' ',
                            '${df.format(tancho)}', '${df.format(talto)}','${df.format(tprofundidad)}','${df.format(tarea)}',
                            '${df.format(tvolumen)}','${df.format(tpeso)}', ' '
                        ]]
                }
            }
        ]
    };

</script>
