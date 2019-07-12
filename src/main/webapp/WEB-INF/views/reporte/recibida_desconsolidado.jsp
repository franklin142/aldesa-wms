<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

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
    var _top = 90;
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
                margin: [25,5,0,0],
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
                        {text: '${ fecha }  ${ hora }',fontSize: 9},
                        {text: usuario, fontSize: 9}],
                        alignment: 'right',
                        width: 95
                    }
                ]
                },
            ]
        },
        content: [
          	<c:forEach var="enc" items="${ enc }" varStatus="loop">
            {
            fontSize:9,
            stack: [
             {
                 margin: [25, 15, 0, 0],
                 columns: [{text:'INFORME DE MERCADERIA RECIBIDA POR CONSIGNATARIO No ${ enc.getDeposito() }/${ enc.getItem() }',
                	 fontSize: 14,width: 350}, {text: 'DESCONSOLIDACION', alignment: 'right'}]
             },
             {
                 margin: [25, 10, 0, 0],
                 columns: [
                         {text:[{text: 'Fecha: ',bold:true},'${ fecha }']},
                         {text:[{text:'Deposito: ',bold:true},'${ enc.getDeposito() }']}, 
                         {text:[{text:'Recepcion: ',bold:true},'1']}
                     ]
             },
             {
                 margin: [25,5,0,0],
                 columns:[
                     {text:[{text:'Consignatario: ',bold:true}, '${ enc.getNombre()}']},
                     {text:[{text:' BL. Hijo:',bold:true},'${enc.getNumeroInforme()}']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Consolidador: ',bold:true}, '${ enc.getConsolidador()}']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Carta de Aceptacion: ',bold:true},' ${enc.getCartaPorte()}'], width:140},
                     {text:[{text:'Fecha carta: ',bold:true}, '${ enc.getFCarta() }'], width:130},
                     {text:[{text:'Documento de embarque master: ',bold:true}, '${enc.getDocEmbarque()}']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Manifiesto de Carga: ',bold:true}, ' ${enc.getMCarga()} ']},
                     {text:[{text:'Procedencia: ',bold:true}, '${enc.getProcedencia()}']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Contenedor: ',bold:true}, '${enc.getCodigoContenedor()}',
                            {text:'Capacidad: ',bold:true}, '  ${enc.getCapacidad()} ']},
                     {text:[{text:'Consignatarios: ',bold:true}, '${enc.getContenedorPorDeposito()}']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Aduana Periferica: ',bold:true}, ' ${enc.getAPeriferica()}   ']},
                     {text:[{text:'Aduana de Entrada: ',bold:true}, ' ${enc.getAEntrada()}']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Cia. de Transporte: ',bold:true}, '${ enc.getCiaTransporte() }']},
                     {text:[{text:'Codigo de Transporte: ',bold:true}, '${ enc.getCodigoTransporte() }']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Motorista: ',bold:true}, '${ enc.getMotorista() }']},
                     {text:[{text:'Licencia No. ',bold:true}, '${ enc.getLicenciaNo() }']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Placa de vehiculo: ',bold:true}, '${ enc.getPlaca() }']},
                     {text:[{text:'DMTI: ',bold:true}, '${enc.getDmti()}']},
                     {text:[{text:'Vapor y No. viajes: ',bold:true}, '${enc.getVaporynViaje()}']},
                     {text:[{text:'No. Marchamo: ',bold:true}, '${ enc.getMarchamoNo() }']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Fecha y hora de entrada del vehiculo de transporte: ',bold:true}]},
                     '${ enc.getFechaHoraEntrada() }'
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Fecha y hora de finalizacion transito por Aduana: ',bold:true}]},
                     '${enc.getFHTransitoAduana()}'
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Fecha y hora de descarga inicio: ',bold:true}, 
                            '${enc.getFDescarga()} ${enc.getHInicio()}']},
                     {text:[{text:'fin: ',bold:true}, '${enc.getFDescarga()} ${enc.getHFin()}']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Fecha y hora autorizacion de corte de marchamo: ',bold:true}, 
                            '${enc.getFHMarchamo()}']},
                     {text:[{text:'Ubicacion: ',bold:true}, '${enc.getBodega()}'], width:140},
                     {text:[{text:'Muelle: ',bold:true}, '${enc.getMuelle()}']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Descargado por: ',bold:true}, '${ enc.getDescargado() }']},
                     {text:[{text:'Mercaderia Paletizada? ',bold:true}, '${ enc.getTipo() }']},
                     {text:[{text:'Utiliza DAN? ',bold:true}, '${enc.getDan()}']}
                 ]
             },
             {
                 margin: [25,2,0,0],
                 columns:[
                     {text:[{text:'Bulks Armados: ',bold:true}, '${enc.getTarimasRecibidas().intValue()}'], width:95},
                     {text:[{text:'Bulks Flejados: ',bold:true}, '${enc.getTarimasFlejadas().intValue()}'], width:95},
                     {text:[{text:'Area ocupada: ',bold:true}, '${ enc.getAreaOcupada() } m²']},
                     {text:[{text:'Volumen ocupado: ',bold:true}, '${ enc.getVolumenOcupado()} m³']},
                     {text:[{text:'Peso kilogramos: ',bold:true}, '${ enc.getPeso() }']}
                 ]
             }
             ]
        	},
           {fontSize: 9,
           table:{
               headerRows:2,
               widths: [ 145, 45,45,45,60, 60, 60 ],
               body:[
                   [{rowSpan:2, text:'Descripcion Mercaderia'},
                    {text:'CANTIDAD',colSpan: 3, alignment: 'center'},'','',
                     {rowSpan:2, text:'Unidad Medida'},
                     {rowSpan:2, text:'Estado'},
                     {rowSpan:2, text:'Ubicacion'}],
                     ['',{text:'Declarada'},{text:'Recibidos',alignment: 'center'},
                      {text:'Diferencia'},'','',''],
                  <c:forEach var="m" items="${ ingresos }" varStatus="loop">
                  <c:if  test="${m.getCorrelativo().equalsIgnoreCase(enc.getNumeroInforme())}">[
                       '${ m.getDescripcion() }',{text:'${ m.getDeclarada() }',alignment: 'right'},
                       {text:'${ m.getRecibida() +m.getAveriada()    }',alignment: 'right'},
                       {text:'${ m.getSaldo() }',alignment: 'right'},
                       '${ m.getUnidadMedida() }','Segun Obs.',' '
                   ],</c:if>
                  </c:forEach>
                 ]
               }
       },
       {fontSize: 9, margin:[0,10,0,0], text:[{text:'Observaciones: ', bold:true}, '${enc.getObservaciones()}']},
       {fontSize: 9, margin:[0,20,0,0], stack:[
           {columns:[' ', {text:' ',width:50}, 'Recibe Mercaderia']},
           {columns:['Entrega de Mercaderia', {text:' ',width:50}, 'Jefe de Bodega']},
           {columns:['${enc.getEntregado()} _____________________', {text:' ',width:50}, '${enc.getJefeBodega()} _____________________']},
           {columns:[' ', {text:'SELLO',width:50}, ' ']},
           {columns:['Visto Bueno', {text:' ',width:50}, 'Fecha: _____________________']},
           {columns:['Jefe de Produccion', {text:' ',width:50}, 'Hora: _____________________']},
           {columns:['${ enc.getJefeProduccion() }_____________________', {text:' ',width:50}, 'Funcionario de Aduana _____________________']}
       ]},
       <c:if  test="${enc.getContenedorPorDeposito()>enc.getItem()}">[
       {fontSize: 9, margin:[0,20,0,0], 
    	   text:[{text:'INFORME ORIGINAL NECESARIO PARA TRAMITE ADUANAL, NO HABRA REPOSICION ',
    		   alignment: 'center'}],pageBreak: 'after'},
       ],</c:if>
        <c:if  test="${enc.getContenedorPorDeposito()==enc.getItem()}">[
       {fontSize: 9, margin:[0,20,0,0], 
    	   text:[{text:'INFORME ORIGINAL NECESARIO PARA TRAMITE ADUANAL, NO HABRA REPOSICION ',
    		   alignment: 'center'}]}
    	]</c:if>	   
    		   
       </c:forEach>
          ]
        
    };

</script>
