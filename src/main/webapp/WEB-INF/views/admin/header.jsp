<%--
  Author: Lennin Hernandez
  Date: 08/08/2016
  Time: 5:25 PM
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <a class="navbar-brand" href="/wms-aldesa/admin/usuarios"><img
                        src="/wms-aldesa/static/resources/logo.jpg" /></i>ADMIN</a>
            </div>
            <div class="collapse navbar-collapse" id="navbar">
                <ul class="nav navbar-nav pull-right">
                    <li><a href="/wms-aldesa/admin/usuarios">Usuarios</a></li>
                    <li><a href="/wms-aldesa/admin/roles">Roles</a></li>
                    <li><div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenuUser" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span> <span id="useradmspan" />
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuUser">
                            <li role="separator" class="divider"></li>
                            <li><a href="/wms-aldesa/admin/login">Salir</a></li>
                        </ul>
                    </div></li>
                </ul>
            </div>
        </div>
    </nav>
    <script>
    var usuario = getCookie('useradm');
    document.getElementById("useradmspan").innerHTML = usuario;
    </script>
</div>
<!-- Nav Menu Section End -->