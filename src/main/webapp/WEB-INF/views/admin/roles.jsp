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
		<div class="row" style="padding: 5px;">
			<table class="table">
				<tr>
					<th>CODIGO</th>
					<th>DESCRIPCION</th>
					<th>GUARDAR</th>
				</tr>
				<tr>
					<td>
						<input form="ga" name="codigo" placeholder="Codigo" type="number" class="form-control"/>
					</td>
					<td>
						<input form="ga" name="descripcion" placeholder="Descripcion" class="form-control"/>
					</td>
					<td>
						<form id="ga" method="post">
							<input type="hidden" value="add" name="action" class="form-control">
							<input type="submit" value="Agregar" class="form-control">
						</form>
					</td>
				</tr>
				<c:forEach var="g" items="${grupos}" varStatus="loop">
					<tr>
						<td>
							<input value="${g.getCodigoGrupo()}" 
								form="ge-${g.getCodigoGrupo()}" class="form-control"
								name="codigo" readonly="readonly"/>
						</td>
						<td>
							<input value="${g.getDescripcion()}" class="form-control"
								form="ge-${g.getCodigoGrupo()}" 
								name="descripcion" required/>
						</td>
						<td>
							<form id="ge-${g.getCodigoGrupo()}" method="post">
								<input type="hidden" value="edit" name="action">
								<input type="submit" value="Guardar" class="btn btn-success">
							</form>
						</td>
						<td>
							<a href="/wms-aldesa/admin/roles/${g.getCodigoGrupo()}"
								class="btn btn-default">
									Editar
							</a>
						</td>
						<td>
							<a href="/wms-aldesa/admin/roles-eli/${g.getCodigoGrupo()}"
								class="btn btn-danger">
									Eliminar
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>