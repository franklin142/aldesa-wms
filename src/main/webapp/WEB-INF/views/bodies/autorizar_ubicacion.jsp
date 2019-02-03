<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="row">
		<div class="col col-lg-2"></div>
		<div class="col col-lg-8">
			<c:if test="${arribos}"><h2>Arribos de Mercadería</h2></c:if>
            <c:if test="${reubicacion}"><h2>Reubicacion de Mercadería</h2></c:if>
            <c:if test="${consulta}"><h2>Consulta Mercadería Pendiente de Ubicar</h2></c:if>
            <c:if test="${autorizar}"><h2>Autorizar Ubicacion</h2></c:if>
			<c:if test="${ubicacion}"> <h2>Ubicacion de Depósitos</h2>
					<div class="row">
					<form method="get" class="navbar-form navbar-left" role="search">
						<div class="col-lg-3 col-lg-offset-4 col-md-3  col-md-offset-4 ">
							Deposito
							<div class="input-group">
								<input name="deposito" type="text" id="depositoinput"
									   class="form-control" placeholder="Deposito"
								<c:if test="${deposito!=null}"> value="${deposito}" </c:if>
								>
                    <span class="input-group-btn">
                        <input type="submit" class="btn btn-default" value="Filtrar">
                    </span>
							</div><!-- /input-group -->
						</div>
						<!-- /.col-lg-6 -->
					</form>
				</div>
			</c:if>
			<form method="post">
			<table class="table table-striped">
				<tr>
					<th>Cliente</th>
					<th>Deposito</th>
					<th>Bodega</th>
					<th>Cantidad de Bulk</th>
					<c:if test="${ autorizar }"><th>Autorizar</th></c:if>
					<c:if test="${!autorizar && index}">Consultar</c:if>
				</tr>

				<c:forEach var="merc" items="${pendientes}" varStatus="loop">
					<tr>
					    <td>${merc.getCliente()} ${ merc.getNombre()}</td>
						<td>${merc.getDeposito()}</td>
						<td>${merc.getBodega()}</td>
						<td>${merc.getCantidad()}</td>
						<c:if test="${ autorizar }">
						<td>
							<input type="checkbox" class="form-input" onclick="$(this).next().val(this.checked?'${ merc.getDeposito() }':' ')" />
							<input type="hidden" name="depositos" />
						</td>
						</c:if>
						<c:if test="${!autorizar && index}">
							<td><a href="/wms-aldesa/web/autorizar_ubicacion/consultar?deposito=${merc.getDeposito()}" class="btn btn-default btn-block">Consultar</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
				<c:if test="${ autorizar }">
				<div class="row">
					<div class="col col-lg-4 col-lg-offset-4">
						<input type="submit" value="AUTORIZAR UBICACION" class="btn btn-primary btn-lg btn-block" />
					</div>
				</div>
				</c:if>
			</form>
		</div>
	</div>
