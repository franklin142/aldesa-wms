<%--
  Author: Lennin Hernandez
  Date: 7/19/2016
  Time: 8:37 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col col-lg-1"></div>
    <div class="col col-lg-10">
        <h2>Alerta de Arribos de Mercadería</h2>
        <table class="table table-striped">
            <tr>
                <th>Motorista</th>
                <th>Placa</th>
                <th>Cliente/Consignatario</th>
                <th>Carta/Notificacion</th>
                <th>Deposito</th>
                <th>Tipo</th>
                <th>Bodega</th>
            </tr>

            <c:forEach var="merc" items="${lista_mercaderia}" varStatus="loop">
                <tr onclick="DoNav('arribos_recepcion?deposito=${ merc.getDeposito_No() }&tipo=${merc.getTipod()}&cliente=${ merc.getCliente() }');">
                    <td>${merc.getMotorista()}</td>
                    <td>${merc.getPlaca()}</td>
                    <td>${merc.getNombreCliente()}</td>
                    <td>${merc.getCarta()}</td>
                    <!-- buscar la carta -->
                    <td>${merc.getDeposito_No()}</td>
                    <td>
                        <c:if test="${ merc.getTipod()=='S' }">Simple</c:if>
                        <c:if test="${ merc.getTipod()=='D' }">Desconsolidado</c:if>
                        <c:if test="${ merc.getTipod()=='F' }">Fiscal</c:if>
                    </td>
                    <td>${merc.getBodega()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
