<%--
  Author: Lennin Hernandez
  Date: 7/18/2016
  Time: 5:25 PM
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- Nav Menu Section -->
<div class="logo-menu">
    <nav class="navbar navbar-default">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header col-md-3">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#navbar">
                    <span class="sr-only">Toggle navigation</span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                        class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"><img
                        src="/wms-aldesa/static/resources/logo.jpg" /></i> ALDESA</a>
            </div>

            <div class="collapse navbar-collapse" id="navbar">
                <ul class="nav navbar-nav pull-right">
                    <li><a href="/wms-aldesa/web/arribos">Recepcion</a></li>
                    <li><a href="/wms-aldesa/web/autorizar_ubicacion">Ubicacion</a></li>
                    <li><a href="/wms-aldesa/web/despacho">Preparacion</a></li>
                    <li><a href="/wms-aldesa/web/autorizar_entrega">Entrega</a></li>
                    <li><a href="/wms-aldesa/web/consulta_ubicacion">Consulta</a></li>
                    <li><div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenuUser" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span> <span id="userspan" />
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuUser">
                            <li><a href="/wms-aldesa/rest/auth/contra">Cambiar Clave</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/wms-aldesa/rest/auth/login-web">Salir</a></li>
                        </ul>
                    </div></li>
                </ul>
            </div>
        </div>
    </nav>
    <script>
    var usuario = getCookie('user');
    document.getElementById("userspan").innerHTML = usuario;
    </script>
</div>
<!-- Nav Menu Section End -->