<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html>

<script>
    var usuario = getCookie('user');
    if(usuario==null) document.location.href ='/wms-aldesa/rest/auth/login-web';
    if (usuario===null) usuario=' ';
    if (usuario===null) usuario=' ';
    var _left = 25;
    var _right = 25;
    var _top = 207;
    var _bottom = 30;

    var dd = {
        // a string or { width: number, height: number }
        pageSize: 'LETTER',

        // by default we use portrait, you can change it to landscape if you wish
        pageOrientation: 'portrait',

        // [left, top, right, bottom] or [horizontal, vertical] or just a number for equal margins
        pageMargins: [_left, _top, _right, _bottom],
        footer: function (currentPage, pageCount) {
            return {
                margin: [25,5,0,20],
                text:currentPage.toString() + ' / ' + pageCount};
        },
        fontSize: 9,
        header: {
            margin: [0,25,25,25],
            fontSize: 9,
            stack: [
                {
                	margin: [25, 0, 0, 0],
                	columns: [
                    {image: aldesaLogo, width: 50, margin:0},
                    {stack: [
                        {text: 'ALMACENES DE DESARROLLO, S.A.',fontSize: 10},
                        {text: 'Boulevard del Ejército, Km. 7 1/2 Soyapango, El Salvador C.A',fontSize: 10},
                        {text: 'PBX: (503) 2121­1950, FAX: (503) 2121­1951',fontSize: 10}
                    ]},
                    {stack: [
                        {text: 'EMISION',fontSize: 9},
                        {text: '${ fecha }',fontSize: 9},
                        {text: usuario, fontSize: 9}],
                        alignment: 'right',
                        width: 85
                    }
                ]
                },
                {
                    margin: [25, 15, 0, 0],
                    columns: [{text:'INFORME DE SALIDAS DE MERCADERIA',fontSize: 14},
                        {text:<c:if test="${tipo.equalsIgnoreCase('S')}">'ALMACENAJE SIMPLE'</c:if><c:if test="${tipo.equalsIgnoreCase('F')}">'ALMACENAJE FISCAL'</c:if>,
                            alignment: 'right' ,fontSize: 14,backgroundColor:"#000",
                            lineColor:"#000"}]
                },
                {
                    margin: [25, 10, 0, 0],
                    columns: [
                        {   text: 'Fecha: ${enc.getFechaString()}',
                            width: 150,backgroundColor:"#fff",
                            lineColor:"#fff"
                        }]
                },
                {
                    margin: [25,5,0,0],
                    columns:[
                        {text:[{text:'Retiro Mercadera No. ${ retiro }',bold:true}]},
                        {text:[{text:'Salida No.: ${ enc.getSalidaNo() }',bold:true}]}
                    ]
                },
                {
                    margin: [25,0,0,0],
                    columns:[
                    	'Cliente: ${ enc.getCliente() } ${enc.getNombre()}',
                    	{width:187,text:'No. Marchamo ${ enc.getMarchamo_No() }'}
                    ]
                    
                },
                {
                    margin: [25,0,0,0],
                    columns:[
                        'Persona autorizada para retirar la mercaderia. ${ enc.getRetirar() }',
                        {width: 187,text:'Placa de vehiculo ${ enc.getPlaca() }'}
                    ]
                },
                {
                    margin: [25,0,0,0],
                    columns:[
                        'Motorista: ${ enc.getMotorista() }',
                        {width:187,text:'Licencia No. ${ enc.getLicencia_No() }'}
                    ]
                },
                {
                    margin: [25,0,0,0],
                    columns:[
                        'Ubicacion de mercaderia  Bodega ${ enc.getBodega() }',
                        'Fecha de carga: ${enc.getFechaDescargaString()}',
                        {width: 95, text:'Hora Inicio: ${enc.getHoraInicioString()}'},
                        {width: 95, text:'Hora fin: ${enc.getHoraFinString()}'}
                    ]
                },
                {
                    margin: [25,0,0,0],
                    columns:[
                        'Cargado por: ${ enc.getCargado_Por() }',
                        'Tipo Carga: ${ enc.getTipo() }',
                        'Tarimas liberadas: ${enc.getTarimasEntregadas()}'                        
                    ]
                }
            ]
        },
        content:{
            fontSize: 9,
            stack:[
                'Detalle de la mercaderia entregada',
                {table:{
                    headerRows:2,
                    widths: [ 40, 40, 150,70,50,50,40,40 ],
                    body:[
                        [ {text:'Deposito',alignment: 'center'},
                          {text:"Código mercaderia",alignment: 'center'},
                          {text:"Descripción mercadería",alignment: 'center'},
                          {text:'Lote', alignment: 'center'},
                          {text:'Fecha de Vencimiento', alignment: 'center'},
                          {text:'Cantidad de Bultos', alignment: 'center'},
                          {text:'Unidad Medida',alignment: 'center'},
                          {text:'Estado Mercaderia',alignment: 'center'}
                        ],
                         <c:forEach var="m" items="${retiros}" varStatus="loop">
                        [
                            {text:'${ m.getDeposito() }',alignment: 'center'},
                            {text:'${ m.getCodigo() }',alignment: 'center'},
                            {text:'${ m.getDescripcion() }',alignment: 'center'},
                            {text:'${m.getnLote()}'=="0"?'':'${m.getnLote()}',alignment: 'center'},
                            {text:'${m.getnLote()}'=="0"?'':moment('${ m.getFechaVto() }').format("DD/MM/YYYY"),alignment: 'center'}, 
                            {text:'${ m.getCantidad()-m.getEntregada()-m.getBultos() }',alignment: 'center'},
                            {text:'${ m.getUnidad_de_Medida() }',alignment: 'center'},
                            {text:'${ m.getEstado() }',alignment: 'center'}
                        ],</c:forEach>
                        [{text:'Total Retiro: ${tbultos+tordenada-tdiferencia}',colSpan:5,alignment: 'center'},
                            '','','','','','','']
                    ]
                }},
                {columns:[
                		   {text:'LIQUIDACIÓN DE ORDEN:',width:125},
                           {text:'Total Ordenado= ',width:75}, '${tordenada}',
                           {text:'Total Retirado =',width:75}, '${tdiferencia}',
                           {text:'Total Restante = ',width:80}, '${tordenada-tdiferencia}'
                      	 ], alignment: 'left', margin:[10,10,0,0]},	 
                {columns:[{text:'OBSERVACIONES:',width:80}, {text:'${ enc.getObservaciones1() }',alignment: 'left'}], margin:[10,20,0,30]},
                {columns:[
                    {stack:[
                        'F. __________________________________',
                        '${ enc.getEntregado() }',
                        'Entrega mercaderia por J. Bodega'                        
                    ], alignment: 'left'},
                    {text: ' ', width:30},
                    {stack:[
                        'F. __________________________________',
                        '${ enc.getRetirar() }',
                        'Recibe Mercaderia'                        
                    ], alignment: 'left'}
                ], margin:[10,25,25,25]},
                {columns:['Productos recibidos a conformidad en cuantía y estado. ALDESA no acepta ningún reclamo después de haber sido recibida la mercadería por el cliente o su representante a su entera satisfacción en fe de lo que firma.'],
                	margin:[10,10,10,10]}
            ]
        }
    };

</script>