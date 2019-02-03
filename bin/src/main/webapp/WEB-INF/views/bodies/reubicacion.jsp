<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="row">
		<div class="col col-lg-1"></div>
		<div class="col col-lg-10">
			<h2>Reubicacion de Mercadería</h2>
			<table class="table table-striped">
				<tr>
					<th>Deposito</th>
					<th>Fecha de Ingreso</th>
					<th>No de Bulks</th>
					<th>Reubicar</th>
				</tr>

				<c:forEach var="merc" items="${lista_mercaderia}" varStatus="loop">
					<tr
						onclick="DoNav('/wms-aldesa/web/reubicacion/${ merc.getDeposito() }/')">
						<td>${merc.getDeposito()}</td>
						<td>${merc.getFechaIngreso()}</td>
                        <td>${merc.getCantidad() }</td>
                        <td><a href="/wms-aldesa/web/reubicacion/${ merc.getDeposito() }/" class="btn btn-primary btn-block">Reubicar</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>