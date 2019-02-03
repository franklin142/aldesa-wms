<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <script type="application/javascript" src="/wms-aldesa/static/resources/js/JsBarcode.all.min.js"> </script>
	<style type="text/css">
		@media print {
			@page { size: 4in 2in; margin: 0.15in; }
			body { margin:auto; width: 3.70in; height: 1.70in;}
			.button{display: none;}
		}
		h1{margin:0;}
		#parent{width: 100%; height: 100%;margin:auto;}
		img{width:80%;margin:auto;}
		body{font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-weight: bold;
			margin:auto; width: 3.70in; height: 1.70in;}
		.button{text-align: center; margin: 10px;}
		.btn{
			border-top: 1px solid #2d4b5e;
			background: #a6a9ab;
			background: -webkit-gradient(linear, left top, left bottom, from(#e0e0e0), to(#a6a9ab));
			background: -webkit-linear-gradient(top, #e0e0e0, #a6a9ab);
			background: -moz-linear-gradient(top, #e0e0e0, #a6a9ab);
			background: -ms-linear-gradient(top, #e0e0e0, #a6a9ab);
			background: -o-linear-gradient(top, #e0e0e0, #a6a9ab);
			padding: 8px 16px;
			-webkit-border-radius: 4px;
			-moz-border-radius: 4px;
			border-radius: 4px;
			-webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;
			-moz-box-shadow: rgba(0,0,0,1) 0 1px 0;
			box-shadow: rgba(0,0,0,1) 0 1px 0;
			text-shadow: rgba(0,0,0,.4) 0 1px 0;
			color: #000000;
			font-size: 18px;
			font-family: Georgia, serif;
			text-decoration: none;
			vertical-align: middle;
		}
		.btn:hover {
			border-top-color: #28597a;
			background: #28597a;
			color: #ccc;
		}
		.btn:active {
			border-top-color: #b8d0e0;
			background: #b8d0e0;
		}
	</style>
</head>
<body>
	<div class="button">
		<a class="btn" href="intent://aldesa/#Intent;scheme=barcodeprint;package=com.aldesa.barcodeprinter;S.valor=${valor};S.cliente=${cliente};end">Imprimir</a>
	</div>
	<div id="parent" style="text-align: center; font-size: 0.95em;">
	<div id="child">
	<h1>${ cliente }</h1>
    <img id="barcode">
	<h1>${ valor }</h1>
	</div>
	</div>
	<script type="text/javascript">
		var opts = {
			width:  4,
			height: 100,
			quite: 10,
			format: "CODE39",
			backgroundColor:"#fff",
			lineColor:"#000"
		};
    JsBarcode(document.getElementById("barcode") ,"${ valor }", opts);
    var closeOnFinished = function(fn){
        fn();
        setTimeout(window.close, 500);
    };
    // closeOnFinished(window.print);
	</script>
</body>
</html>