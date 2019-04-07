<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Visor de Reportes</title>
</head>
<body>
<canvas id="the-canvas" style="border:1px  solid black"></canvas>

<script src="/wms-aldesa/static/resources/js/pdf.js"></script>
<script id="script">

  var BASE64_MARKER = ';base64,';
  var url = "${url}";
  var base64Index = dataURI.indexOf(BASE64_MARKER) + BASE64_MARKER.length;
  var url = dataURI.substring(base64Index);
  url = atob(url);

  PDFJS.workerSrc = '/wms-aldesa/static/resources/js/pdf.worker.js';

  //
  // Asynchronous download PDF
  //
  PDFJS.getDocument({data: url}).then(function getPdfHelloWorld(pdf) {
    //
    // Fetch the first page
    //
    pdf.getPage(1).then(function getPageHelloWorld(page) {
      var scale = 1.5;
      var viewport = page.getViewport(scale);

      //
      // Prepare canvas using PDF page dimensions
      //
      var canvas = document.getElementById('the-canvas');
      var context = canvas.getContext('2d');
      canvas.height = viewport.height;
      canvas.width = viewport.width;

      //
      // Render PDF page into canvas context
      //
      var renderContext = {
        canvasContext: context,
        viewport: viewport
      };
      page.render(renderContext);
    });
  });
</script>
</body>
</html>
