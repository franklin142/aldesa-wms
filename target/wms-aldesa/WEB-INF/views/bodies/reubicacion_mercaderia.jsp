<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div class="row">
        <div class="col col-lg-5 col-lg-offset-2">
        	<h2>Reubicacion de Mercadería</h2>
        </div>    
        <div class="col col-lg-3">
            <form method="get" class="form-inline">
                <div class="input-group">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button" onclick="document.getElementById('depositoTxt').value = ''; ">
                          <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                      </button>
                    </span>
                    <input type="text" class="form-control" id="depositoTxt" name="deposito" placeholder="Deposito">
                    <span class="input-group-btn">
                        <input type="submit" class="btn btn-primary" value="Buscar"/>
                    </span>
                </div>
            </form>
        </div>
    </div>
	<div class="row">
		<div class="col col-lg-2"></div>
		<div class="col col-lg-8">
			<table class="table table-striped">
				<tr>
					<th>Deposito</th>
					<th>Fecha de Entrega</th>
					<th>Cantidad por Bulks</th>
					<th>Reubicar</th>
				</tr>

				<c:forEach var="merc" items="${lista_mercaderia}" varStatus="loop">
					<tr
						onclick="DoNav('/wms-aldesa/web/reubicacion_mercaderia/${ merc.getDeposito() }/')">
						<td>${merc.getDeposito()}</td>
						<td>${merc.getFechaIngreso()}</td>
                        <td>${merc.getCantidad() }</td>
                        <td><a href="/wms-aldesa/web/reubicacion_mercaderia/${ merc.getDeposito() }/${merc.getFechaIngreso()}/" class="btn btn-primary btn-block">Reubicar</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>