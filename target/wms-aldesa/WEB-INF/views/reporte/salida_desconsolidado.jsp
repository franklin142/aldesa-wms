<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html>

<script>
    var usuario = getCookie('user');
    if(usuario==null) document.location.href ='/wms-aldesa/rest/auth/login-web';
    if (usuario===null) usuario=' ';
    if (usuario===null) usuario=' ';
    var _left = 25;
    var _right = 25;
    var _top = 260;
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
                    columns: [{text:'INFORME DE SALIDAS DE MERCADERIA      No ${ retiro }',fontSize: 14},
                        {text:'DESCONSOLIDACION',alignment: 'right' ,fontSize: 14}]
                },
                {
                    margin: [25, 10, 0, 0],
                    columns: [
                        {text:[{text:'Fecha: ${enc.getFechaString()}'}],width:100},
                        {text:[{text: 'Deposito: ${enc.getDeposito_No()}'}],width:100},
                        {text:[{text: 'Salida: ${ enc.getSalidaNo() }'}],width:80},
                        {text:[{text: 'No. Declaración de Mercancía: ${enc.getDeclaracion()}'}],width:250}
                        ]
                },
                {
                    margin: [25,5,0,0],
                    columns:[
                        {text:[{text:'Consignatario: ',bold:true},'${ enc.getCliente() } ${enc.getNombre()}']},
                        {text:[{text:'BL.Hijo/carta de porte: ',bold:true},'${ enc.getCarta() }']}
                    ]
                },
                {
                    margin: [25,5,0,0],
                    columns:['Consolidador: ${ enc.getConsolidador() }',
                             'Factura o CCF: ${ enc.getFactura() }',]
                },
                {
                    margin: [25,5,0,0],
                    columns:[
                        'Persona autorizada para retirar la mercaderia: ${ enc.getRetirar() }'
                    ]
                },
                {
                    margin: [25,5,0,0],
                    columns:[
                        'Motorista: ${ enc.getMotorista() }',
                        'Licencia No: ${ enc.getLicencia_No() }',
                    ]
                },
                {
                    margin: [25,5,0,0],
                    columns:[
                        'Cia. de Transporte: ${ enc.getNombrecia() }',
                        'Código de transporte: ${ enc.getCodcia() }'
                    ]
                },
                {
                    margin: [25,5,0,0],
                    columns:[
                        'Placa de vehiculo ${ enc.getPlaca() }',
                        'No. Marchamo ${ enc.getMarchamo_No() }'
                    ]
                },
                {
                    margin: [25,5,0,0],
                    columns:[
                        'Ubicacion: ${ enc.getBodega() }',
                        'Muelle: ${ enc.getMuelle() }',
                        'Fecha de carga: ${enc.getFechaDescargaString()}',
                        {width: 95, text:'Hora Inicio: ${enc.getHoraInicioString()}'},
                        {width: 95, text:'Hora fin: ${enc.getHoraFinString()}'}
                    ]
                }
            ]
        },
        content:{
            fontSize: 9,
            stack:[
                'Detalle de la mercaderia entregada',
                {table:{
                    headerRows:1,
                    widths: [ 330, 50, 50, 50 ],
                    body:[
                        [ {text:'Descripcion'},
                          {text:'Cantidad'},{text:'Unidad Medida'},
                          {text:'Estado Mercaderia'}],
                         <c:forEach var="m" items="${retiros}" varStatus="loop">
                        [
                            '${ m.getDescripcion() }', 
                            {text:'${ m.getEntregada()}',alignment: 'right'},
                            '${ m.getUnidad_de_Medida() }', 'SEGUN RECEPCION'
                        ],</c:forEach>
                    ]
                }},                
                {columns:[{text:'Observaciones de Recepcion:',width:130,bold:true}, {text:'${ enc.getObservacionesr() }',alignment: 'left',bold:true}], margin:[0,25,0,0]},
                {columns:[{text:'Otras observaciones:',width:130,bold:true}, {text:'${ enc.getObservaciones1() }',alignment: 'left',bold:true}], margin:[0,30,0,0]},
                {columns:[{text:'Este dia se presentó en ALDESA, el/la Sr/Sra. ${ enc.getRetirar() } a quien identificamos por medio de su correspondiente Licencia de conducir o D.U.I. No. ${ enc.getLicencia_No() } y quien, habiendo presentado la Declaración de Mercancías con impuestos cancelados, la autorización del consolidador, el informe de Recepción de Mercadería y el comprobante de pago cancelado por los servicios prestados por ALDESA expresa estar autorizado por el titular para hacer el retiro de la carga y quien asume las derivaciones correspondientes de lo expresado. ',bold:true}], margin:[0,25,0,0]},
                {columns:[{text:'Productos recibidos a conformidad en cuantía y estado. ALDESA no acepta ningún reclamo después de haber sido recibida la mercadería por el cliente o su representante a su entera satisfacción en fe de lo que firma.',bold:true}], margin:[0,25,0,0]},
                {columns:[
                          {stack:[
                              'F. __________________________________',
                              '${ enc.getEntregado() }',
                              'Entrega mercaderia por J. Bodega'                        
                          ], alignment: 'center'},
                          {text: ' ', width:30},
                          {stack:[
                              'F. __________________________________',
                              '${ enc.getRetirar() }',
                              'Recibe Mercaderia'                        
                          ], alignment: 'center'}
                      ], margin:[25,25,25,25]},
            ]
        }
    };

</script>