<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	//Franklin Flores... informe preparacion a modificar
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
    var _top = 95;
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
                                {text: 'ORDEN DE PREPARACION No ${orden}', alignment: 'center',fontSize: 14},
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
                    {
                        margin: [0,5,0,5],
                        columns:[
                            {text:[{text:'Motorista: ',bold:true,fontSize: 11}, {text:'${motorista}        ',fontSize: 11},
                            	   {text:'Licencia: ',bold:true,fontSize: 11}, {text:'${licencia}        ',fontSize: 11},
                            	   {text:'Placa: ',bold:true,fontSize: 11}, {text:'${placa}',fontSize: 11}
                            	  ]}
                            ]
                    },
                {table:{
                    headerRows:1,
                    margins:[0,2,0,0],fontSize: 8,
                    widths: [ '*', 160, 40, 40, 40, '*', '*', '*', '*'],
                    body:[
                        ['Codigo','Descripcion', 'Estado Merc. Solicitada',
                            'Cantidad solicitada','Cantidad Preparada','Saldo',
                            'Ubicacion','Bulk',  'Estado'],<c:forEach var="merc" items="${recepcion_mercaderia}" varStatus="loop"> <c:if test="${merc.getDeposito().equalsIgnoreCase(d)}" >
                        ['${merc.getCodigo()}', '${merc.getDescripcion()}', '${merc.getEstadoMercaderiaSolicitada()}',
                            '${merc.getCantidad()}', '${merc.getPreparadas()}', '${merc.getSaldos()}',
                            '${merc.getEstante() }', '${merc.getCodigoBulk()}', '${merc.getEstadoMercaderia()}'
                        ]<c:forEach var="st" items="${stdepositos}" varStatus="loop"> <c:if test="${st[1].toString().equalsIgnoreCase(d) and st[2].toString().equalsIgnoreCase(merc.getCorrelativo())}" >, ['Sub Total', ' ', ' ',
                            '${st[3]}',
                            '${st[4]}',
                            '${st[5]}',
                            ' ', ' ',
                            ' '
                        ],</c:if> </c:forEach></c:if> </c:forEach>
                        <c:forEach var="t" items="${tdepositos}" varStatus="loop"> <c:if test="${t[1].toString().equalsIgnoreCase(d)}" >
                        ['Total', '', ' ',
                            '${t[2]}',
                            '${t[3]}',
                            '${t[4]}',
                            ' ', ' ', ' ']</c:if> </c:forEach>
                    ]
                }}, </c:forEach>
                {
                    columns:[
                        'TOTAL SOLICITADA ${tcantidad} ',
                        'TOTAL PREPARADA ${tpreparada}', ' ', ' ', ' '
                    ]
                }
            ]}
    };
</script>