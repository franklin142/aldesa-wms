<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

    var opts = {
        width: 1,
        height: 10,
        quite: 10,
        format: "CODE39",
        backgroundColor: "#fff",
        lineColor: "#000"
    };
    var usuario = getCookie('user');
    if (usuario == null)
        document.location.href = '/wms-aldesa/rest/auth/login-web';
    if (usuario === null)
        usuario = ' ';
    if (usuario === null)
        usuario = ' ';
    var _left = 25;
    var _right = 25;
    var _top = 290;
    var _bottom = 20;
    var dd = {
        // a string or { width: number, height: number }
        pageSize: 'LETTER',
        // by default we use portrait, you can change it to landscape if you wish
        pageOrientation: 'portrait',
        // [left, top, right, bottom] or [horizontal, vertical] or just a number for equal margins
        pageMargins: [_left, _top, _right, _bottom],
        footer: function (currentPage, pageCount) {
            return {
                margin: [25, 5, 0, 0],
                text: currentPage.toString() + ' / ' + pageCount};
        },
        fontSize: 9,
        header: {
            margin: [0, 25, 25, 25],
            fontSize: 9,
            stack: [
                {
                    margin: [25, 0, 0, 0],
                    columns: [
                        {image: aldesaLogo, width: 50, margin: 0},
                        {stack: [
                                {text: 'ALMACENES DE DESARROLLO, S.A.', fontSize: 10},
                                {text: 'Boulevard del Ejército, Km. 7 1/2 Soyapango, El Salvador C.A', fontSize: 10},
                                {text: 'PBX: (503) 2121­1950, FAX: (503) 2121­1951', fontSize: 10}
                            ]},
                        {stack: [
                                {text: 'EMISION', fontSize: 9},
                                {text: '${ fecha }  ${ hora }', fontSize: 9},
                                                                {text: usuario, fontSize: 9}],
                                                            alignment: 'right',
                                                            width: 95
                                                        }
                                                    ]
                                                },
                                                {
                                                    margin: [25, 15, 0, 0],
                                                    columns: [{text: 'INFORME DE MERCADERIA RECIBIDA   ${enc.getNumeroInforme()}', fontSize: 14, width: 350}, {text: '${enc.getNombreReporte()}', alignment: 'right'}]
                                                },
                                                {
                                                    margin: [25, 10, 0, 0],
                                                    columns: [
                                                        {text: 'Fecha: ${ fecha }'},
                                                        {text: [{text: '(Carta de Aceptacion de carga o Notificacion)  ${enc.getNumeroInforme()}', fontSize: 8}, {text: ' \nBL o carta de Porte:', bold: true}, ' ${enc.getCartaPorte()}']},
                                                    ]
                                                },
                                                {
                                                    margin: [25, 5, 0, 0],
                                                    columns: [
                                                        {text: [{text: 'Cliente: ', bold: true}, '${ enc.getCliente()} ', '${ enc.getNombre()}']},
                                                        {text: [{text: 'Deposito: ', bold: true}, '${ enc.getDeposito() }']}
                                                    ]
                                                },
                                                {
                                                    margin: [25, 2, 0, 0],
                                                    columns: [
                                                        {text: [{text: 'Tipo de mercaderia: ', bold: true}, '${ enc.getTipoMercaderia() }']},
                                                        {text: [{text: 'Procedencia: ', bold: true}, '${enc.getProcedencia()}']}
                                                    ]
                                                },
                                                {
                                                    margin: [25, 2, 0, 0],
                                                    columns: [
                                                        {text: [{text: 'Fecha y hora de entrada del vehiculo de transporte: ', bold: true}]},
                                                        '${ enc.getFechaHoraEntrada() }'
                                                    ]
                                                },
                                                {
                                                    margin: [25, 2, 0, 0],
                                                    columns: [
                                                        {text: [{text: 'Motorista: ', bold: true}, '${ enc.getMotorista() }']},
                                                        {text: [{text: 'Licencia No. ', bold: true}, '${ enc.getLicenciaNo() }']}
                                                    ]
                                                },
                                                {
                                                    margin: [25, 2, 0, 0],
                                                    columns: [
                                                        {text: [{text: 'Cia. de Transporte: ', bold: true}, '${ enc.getCiaTransporte() }']},
                                                        {text: [{text: 'Codigo de Transporte: ', bold: true}, '${ enc.getCodigoTransporte() }']}
                                                    ]
                                                },
                                                {
                                                    margin: [25, 2, 0, 0],
                                                    columns: [
                                                        {text: [{text: 'Placa de vehiculo: ', bold: true}, '${ enc.getPlaca() }']},
                                                        {text: [{text: 'No. Marchamo: ', bold: true}, '${ enc.getMarchamoNo() }']}
                                                    ]
                                                },
                                                {
                                                    margin: [25, 2, 0, 0],
                                                    columns: [
                                                        {text: [{text: 'DMTI: ', bold: true}, '${enc.getDmti()}'], width: 130},
                                                        {text: [{text: 'Codigo Contenedor: ', bold: true}, '${enc.getCodigoContenedor()}']},
                                                        {text: [{text: 'Contenedores por deposito: ', bold: true}, '${enc.getContenedorPorDeposito()}']}
                                                    ]
                                                },
                                                {
                                                    margin: [25, 2, 0, 0],
                                                    columns: [
                                                        {text: [{text: 'Fecha y hora autorizacion de corte de marchamo: ', bold: true}, '${enc.getFHMarchamo()}']},
                                                        //    {text:[{text:'Fecha y hora finalizacion por Aduana: ',bold:true}, '${enc.getFHTransitoAduana()}']}
                                                    ]
                                                },
                                                {
                                                    margin: [25, 2, 0, 0],
                                                    columns: [
                                                        {text: [{text: 'Fecha y hora de descarga inicio: ', bold: true}, '${enc.getFDescarga()} ${enc.getHInicio()}'], width: 220},
                                                                                {text: [{text: 'fin: ', bold: true}, '${enc.getFDescarga()} ${enc.getHFin()}'], width: 100},
                                                                                                        {text: [{text: 'Ubicacion: ', bold: true}, '${enc.getBodega()}'], width: 140},
                                                                                                        {text: [{text: 'Muelle: ', bold: true}, '${enc.getMuelle()}']}
                                                                                                    ]
                                                                                                },
                                                                                                {
                                                                                                    margin: [25, 2, 0, 0],
                                                                                                    columns: [
                                                                                                        {text: [{text: 'Descargado por: ', bold: true}, '${ enc.getDescargado() }']},
                                                                                                        {text: [{text: 'Mercaderia Paletizada? ', bold: true}, '${ enc.getTipo() }']},
                                                                                                        {text: [{text: 'Utiliza DAN? ', bold: true}, '${enc.getDan()}']}

                                                                                                    ]
                                                                                                },
                                                                                                {
                                                                                                    margin: [25, 2, 0, 0],
                                                                                                    columns: [
                                                                                                        {text: [{text: 'Bulks Armados: ', bold: true}, '${enc.getTarimasRecibidas().intValue()}'], width: 95},
                                                                                                        {text: [{text: 'Bulks Flejados: ', bold: true}, '${enc.getTarimasFlejadas().intValue()}'], width: 95},
                                                                                                        {text: [{text: 'Area ocupada: ', bold: true}, '${ df3d.format(enc.getAreaOcupada()) } m²']},
                                                                                                        {text: [{text: 'Volumen ocupado: ', bold: true}, '${df3d.format(enc.getVolumenOcupado())} m³']},
                                                                                                        {text: [{text: 'Peso kilogramos: ', bold: true}, '${df2d.format(enc.getPeso())}']}
                                                                                                    ]
                                                                                                },
                                                                                                {
                                                                                                    margin: [25, 0, 2, 0],
                                                                                                    columns: [
                                                                                                        {text: [{text: ('${enc.getNombreReporte()}'=='ALMACENAMIENTO SIMPLE')?"\n":'Fecha fin tránsito: ', bold: true},('${enc.getNombreReporte()}'=='ALMACENAMIENTO SIMPLE')?"\n":'${ enc.getFechaTransito() }']},
                                                                                                        {text: [{text: ('${enc.getNombreReporte()}'=='ALMACENAMIENTO SIMPLE')?"\n":'Hora fin tránsito ', bold: true},('${enc.getNombreReporte()}'=='ALMACENAMIENTO SIMPLE')?"\n": '${ enc.getHoraFinalizaTransito() }']}
																										

                                                                                                    ]
                                                                                                }
                                                                                            ]
                                                                                        },
                                                                                        content: [
                                                                                            {
                                                                                                fontSize: 7,
                                                                                                table: {
                                                                                                    headerRows: 2,
                                                                                                    widths: [50, 120,55,40, 40, 40, 40, 35, 45],
                                                                                                    body: [
                                                                                                        	[	
                                                                                                        		{rowSpan: 2, text: 'Código mercadería',alignment: 'center',bold: true},
                                                                                                            	{rowSpan: 2, text: 'Descripción mercadería',alignment: 'center',bold: true},
                                                                                                            	{rowSpan: 2, text: 'Lote',alignment: 'center',bold: true},
                                                                                                            	{rowSpan: 2, text: 'Fecha de vencimiento',alignment: 'center',bold: true},
                                                                                                            	{text: 'Cantidad de Bultos', colSpan: 3, alignment: 'center',bold: true},
                                                                                                            	'',
                                                                                                            	'',
                                                                                                            	{rowSpan: 2, text: 'Unidad Medida',alignment: 'center',bold: true},
                                                                                                            	{rowSpan: 2, text: 'Estado mercadería',alignment: 'center',bold: true}
                                                                                                            ],
                                                                                                        	[
                                                                                                        		'',
                                                                                                        		'',
                                                                                                        		'',
                                                                                                        		'',
                                                                                                        		{text: 'Declarada',alignment: 'center',bold: true},
                                                                                                        		{text: 'Recibida', alignment: 'center',bold: true},
                                                                                                            	{text: 'Diferencia',alignment: 'center',bold: true},
                                                                                                            	'',
                                                                                                            	''
                                                                                                        	],
                                                                                                 		  <c:forEach var="m" items="${ ingresos }" varStatus="loop">
                                                                                                 		    [
                                                                                                 		    	{text: '${ m.getCodigoMercaderia() }',alignment: 'center',fontSize: 7},
                                                                                                                {text: '${ m.getDescripcion() }',alignment: 'center',fontSize: 7},
                                                                                                                {text: (('${ m.getnLote()}'=='0')?'':'${ m.getnLote() }'),alignment: 'center',fontSize: 7},
        																										{text: (('${ m.getnLote()}'=='0')?'':moment('${ m.getFechaVto()}').format("DD/MM/YYYY")),alignment: 'center',fontSize: 7},
        																										{text: '${ m.getDeclarada() }', alignment: 'center',fontSize: 7},
                                                                                                        		{text: '${ m.getRecibida() }', alignment: 'center',fontSize: 7},
                                                                                                        		{text: '${ m.getSaldo() }', alignment: 'center',fontSize: 7},
                                                                                                        		{text: '${ m.getUnidadMedida() }',alignment: 'center',fontSize: 7},
                                                                                                        		{text: '${ m.getEstado() }',alignment: 'center',fontSize: 7},
                                                                                                        	 ],
                                                                                                           </c:forEach>
                                                                                                    ]
                                                                                                }
                                                                                            },
                                                                                            {fontSize: 7, bold: true, margin: [25, 2, 0, 0], stack: [{columns: [{text: ' ', width: 225}, {text: 'TOTALES ', width: 50,alignment: 'center',fontSize: 8},
                                                                                                {text: '${ tdeclarada }', width: 51, alignment: 'center', fontSize: 7}, {text: '${ trecibida }', width: 49, alignment: 'center', fontSize: 7},
                                                                                                {text: '${ tsaldo }', width: 48, alignment: 'center', fontSize: 7},
                                                                                                {text: ' ', width: '*'}], alignment: 'right'}]},
                                                                                            {fontSize: 9, margin: [0, 10, 0, 0], text: [{text: '', bold: true}, '']},
                                                                                            {fontSize: 9,
                                                                                            	table: {
                                                                                                    headerRows: 1,
                                                                                                    widths: [65,463],
                                                                                                    body: [
                                                                                                    	[	
                                                                                                    		{text: 'Observaciones:',alignment: 'left',bold: true},
                                                                                                        	{text: '${enc.getObservaciones()}',alignment: 'left',fontSize: 7}
                                                                                                        	
                                                                                                        ]
                                                                                                    ]
                                                                                            	}
                                                                                            },
																							{fontSize: 9, margin: [0, 20, 0, 0], stack: [
                                                                                                    {columns: ['CLIENTE', {text: ' ', width: 50}, 'Recibe Mercaderia']},
                                                                                                    {columns: ['Entrega de Mercaderia', {text: ' ', width: 50}, 'Jefe de Bodega']},
                                                                                                    {columns: ['${enc.getEntregado()} _____________________', {text: ' ', width: 50}, '${enc.getJefeBodega()} _____________________']},
                                                                                                    {columns: [' ', {text: 'SELLO', width: 50}, ' ']},
                                                                                                    {columns: ['Visto Bueno', {text: ' ', width: 50}, 'Fecha: _____________________']},
                                                                                                    {columns: ['Jefe de Produccion', {text: ' ', width: 50}, 'Hora: _____________________']},
                                                                                                    {columns: ['${ enc.getJefeProduccion() }_____________________', {text: ' ', width: 50}, 'Funcionario de Aduana _____________________']}
                                                                                                ]}
                                                                                        ]
                                                                                    };

</script>