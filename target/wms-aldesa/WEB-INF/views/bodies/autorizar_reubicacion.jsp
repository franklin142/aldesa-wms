<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col col-lg-2"></div>
	<div class="col col-lg-8">
			<h2>Reubicacion de Mercadería</h2>
			<table class="table table-striped">
				<tr>
					<th>No</th>
					<th>Bulk</th>
					<th>Ubicacion</th>
					<th>Seleccionar Todos</th>
				</tr>

				<c:forEach var="merc" items="${lista_mercaderia}" varStatus="loop">
					<tr>
						<td>${merc.getDeposito()}</td>
						<td>${merc.getBodega()}</td>
						<td>${merc.getCantidad()}</td>
						<td><input type="checkbox" class="form-input" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>