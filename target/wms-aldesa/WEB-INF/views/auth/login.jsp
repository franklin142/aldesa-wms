<%--
  Author: Lennin Hernandez
  Date: 7/18/2016
  Time: 5:25 PM
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    .login-div{
        top: 25%;
        margin: 3% auto;
        width: 60%;
        border: #5887ff solid 4px;
    }
    .errormsg{
        color: red;
    }
</style>
<script>
    var delete_cookie = function ( name ) {
        document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    };

    delete_cookie('user');
</script>
<div class="login-div">
    <div class="row">
        <div class="col col-lg-4 col-md-4 col-lg-offset-1 col-md-offset-1">
            <br>
            <img src="/wms-aldesa/static/resources/img/lgvct.png" width="100%" >
        </div>
        <div class="col col-lg-6 col-md-6">
            <h2>ALDESA - WMS</h2>
            <h4>Sistema de Gestión de Bodegas</h4>
            <h6>Ingrese sus datos de acceso:</h6>
            <c:if test="${msg}"><pre class="errormsg"> ${msg} </pre></c:if>
            <form role="form" method="POST">
                <div class="form-group">
                    <input name="user" class="form-control" placeholder="Usuario">
                </div>
                <div class="form-group">
                    <input name="pass" type="password" class="form-control" placeholder="Contraseña">
                </div>
                <input type="submit" class="btn btn-default btn-lg btn-block" value="Conectarse" />
            </form>
        </div>
    </div>
</div>

<!-- Hero Area Section End-->