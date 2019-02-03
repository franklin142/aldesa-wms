<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row" style="padding: 10px;" disabled>
	<div class="col col-lg-3"></div>
	<div class="col col-lg-6">
		<h2 style="text-align: center;">Usuarios</h2>
		<c:if test="${msg}!=null">
			<div class="row">
				<div class="col col-lg-6 col-lg-offset-3"
					style="text-align: center; color: red;">
					<strong>${msg}</strong>
				</div>
			</div>
		</c:if>
		<div class="row" style="padding: 5px;">
			<a class="btn btn-info" href="/wms-aldesa/admin/usuarios-add/" role="button">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				Agregar
			</a>
			<table class="table">
				<tr>
					<th>USUARIO</th>
					<th>NOMBRE</th>
					<th>ES ADMIN</th>
					<th>ACCION</th>
				</tr>
				<c:forEach var="usr" items="${usuarios}" varStatus="loop">
					<tr onclick="DoNav('/wms-aldesa/admin/usuarios/${usr.getUsuario()}/');">
						<td>${usr.getUsuario()}</td>
						<td>${usr.getNombreUsuario()}</td>
						<td><input type="checkbox" disabled
								<c:if test="${usr.getEsAdmin().intValue()==1}">checked="checked"</c:if>
							/>
						</td>
						<td>
							<c:if test="${usr.getEstado()=='A'}">
								<a href="/wms-aldesa/admin/usuarios-hab/${usr.getUsuario()}/I" class="btn btn-success">
									Inhabilitar
								</a>
							</c:if>
							<c:if test="${usr.getEstado()=='I'}">
								<a href="/wms-aldesa/admin/usuarios-hab/${usr.getUsuario()}/A" class="btn btn-primary">
									Habilitar
								</a>
							</c:if>
						</td>
						<td>	
						  <a href="/wms-aldesa/admin/usuarios-hab/${usr.getUsuario()}/E" class="btn btn-danger">
							Eliminar
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>