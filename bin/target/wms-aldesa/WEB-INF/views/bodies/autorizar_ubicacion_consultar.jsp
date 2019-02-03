<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <style>
        tbody tr:nth-child(even) {
            background-color: #e3f9fc;
        }
        #depositoinput{width:100%;}
        .row{padding:10px;}
    </style>
<div class="row">
    <div class="row">
        <form method="get" class="navbar-form navbar-left" role="search">
            <div class="col-lg-3 col-lg-offset-4 col-md-3  col-md-offset-4 ">
            <h2>Ubicacion de Bulk por Depósito</h2>
                
                <div class="input-group">
                    Depósito
                    <input name="deposito" type="text" id="depositoinput"
                           class="form-control" placeholder="Deposito"
                    <c:if test="${deposito!=null}"> value="${deposito}" </c:if>
                    >
                    <span class="input-group-btn">
                        <input type="submit" class="btn btn-default" value="Filtrar">
                    </span>
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
            <c:if test="${deposito!=null && ubicaciones!=null}">
            <style>.btn1{margin-top: 21px;}</style>
            <div class="col-lg-1 col-md-1 btn1">
                <a href="/wms-aldesa/web/reporte/ubicacion_deposito/${deposito}" class="btn btn-default" >Informe Bulks Creados</a>
            </div><!-- /.col-lg-6 -->
            </c:if>
        </form>
    </div>
    <c:if test="${ubicaciones!=null}">
        <div class="row">
            <div class="col col-lg-6 col-lg-offset-3">
                <table class="table">
                    <tr>
                        <th>Total de bulks:</th>
                        <td>${tbulks}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col col-lg-4 col-lg-offset-4">
                <table class="table">
                    <tr><th>BULK</th><th>UBICACION</th></tr>
                    <c:forEach var="ub" items="${ubicaciones}" varStatus="loop">
                        <tr>
                            <td>${ub.getCodigo()}</td>
                            <td>${ub.getUbicacion()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </c:if>
</div>