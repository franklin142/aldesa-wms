<%--
  Author: Lennin Hernandez
  Date: 7/18/2016
  Time: 5:25 PM
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    .sucessmsg{
        color: mediumseagreen;
    }
</style>
<div class="login-div">
    <div class="row">
        <div class="col col-lg-4 col-md-4 col-lg-offset-1 col-md-offset-1">
            <br>
            <img src="/wms-aldesa/static/resources/img/lgvct.png" width="100%" >
        </div>
        <div class="col col-lg-6 col-md-6">
            <h2>ALDESA - WMS</h2>
            <h4>Sistema de Gestión de Bodegas</h4>
            <h6>Cambio de Contraseña:</h6>
            <c:if test="${msg!=null}"><pre class="errormsg"> ${msg} </pre></c:if>
            <c:if test="${msg1!=null}"><pre class="sucessmsg"> ${msg1} </pre></c:if>
            <form role="form" method="POST">
                <div class="form-group">
                    <input class="form-control" placeholder="Usuario" disabled value="${usuario}">
                    <input type="hidden" name="user" class="form-control" placeholder="Usuario" value="${usuario}">
                </div>
                <div class="form-group">
                    <input name="pass" type="password" class="form-control" placeholder="Contraseña Actual">
                </div>
                <div class="form-group">
                    <input name="pass1" type="password" class="form-control" placeholder="Contraseña Nueva">
                </div>
                <div class="form-group">
                    <input name="pass2" type="password" class="form-control" placeholder="Repetir Contraseña Nueva">
                </div>
                <input type="submit" class="btn btn-default btn-lg btn-block" value="Cambiar Contraseña" />
            </form>
        </div>
    </div>
</div>

<!-- Hero Area Section End-->