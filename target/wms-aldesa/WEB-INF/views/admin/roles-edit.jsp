<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row" style="padding: 10px;" disabled>
	<div class="col col-lg-3"></div>
	<div class="col col-lg-6">
		<h2 style="text-align: center;">Grupos</h2>
		<c:if test="${msg}!=null">
			<div class="row">
				<div class="col col-lg-6 col-lg-offset-3"
					style="text-align: center; color: red;">
					<strong>${msg}</strong>
				</div>
			</div>
		</c:if>
		<h3>ROL : ${rol.getDescripcion()}</h3>
		<form method="post">
		<div class="row" style="padding: 5px;">
			<table class="table">
				<tr>
					<th>URL</th>
					<th>Tiene Acceso</th>
				</tr>
				<c:forEach var="url" items="${urls}" varStatus="loop">
					<tr>
						<td> ${url[3].toString()} </td>
						<td>
							<input type="checkbox" name="url" value="${url[1]}"
								   <c:if test="${url[2]!=null}"> checked</c:if> />
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="submit" value="Guardar" class="btn btn-primary btn-block">
		</div>
		</form>
	</div>
</div>