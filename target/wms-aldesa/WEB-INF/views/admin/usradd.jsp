<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row" style="padding: 10px;" disabled>
	<div class="col col-lg-3"></div>
	<div class="col col-lg-6">
		<c:if test="${idusuario!=null}">
			<h2 style="text-align: center;">Editar usuario</h2>
		</c:if>
		<c:if test="${idusuario==null}">
			<h2 style="text-align: center;">Agregar usuario</h2>
		</c:if>
		<c:if test="${msg!=null}">
			<div class="row">
				<div class="col col-lg-6 col-lg-offset-3"
					style="text-align: center; color: red;">
					<strong>${msg}</strong>
				</div>
			</div>
		</c:if>
		<c:if test="${msg1!=null}">
			<div class="row">
				<div class="col col-lg-6 col-lg-offset-3"
					 style="text-align: center; color: mediumseagreen;">
					<strong>${msg1}</strong>
				</div>
			</div>
		</c:if>
		<form method="post">
			<h4>Datos del usuario</h4>
			<div class="row" style="padding: 5px;">
				 <div class="form-group">
					 <label for="usuario">Usuario</label>
					 <input class="form-control" id="usuario" placeholder="Usuario" name="user" pattern=".{8,20}"
							 <c:if test="${idusuario!=null}"> value="${idusuario.getUsuario()}" readonly="readonly" </c:if>
							 <c:if test="${idusuario==null}"> value="usuario" required </c:if>
					 />
				 </div>
				 <div class="form-group">
					 <label for="clave">Clave</label>
					 <c:if test="${idusuario!=null}">
					 <label>
						 <input type="checkbox" name="cambiarClave" onclick="document.getElementById('clave').disabled=(!this.checked)" > Cambiar Clave?
					 </label>
					 </c:if>
					 <input class="form-control" id="clave"
							placeholder="Clave" name="clave" type="password"
							 <c:if test="${idusuario!=null}">disabled</c:if>
							 <c:if test="${idusuario==null}"> value="" required</c:if>
					 />
				 </div>
				 <div class="form-group">
					 <label for="clave">PIN PDA</label>
					 <label>
						<input type="checkbox" name="tiene_pin_pda" onclick="document.getElementById('pinpda').disabled=(!this.checked)" >
					 	<c:if test="${idusuario==null}">Establecer PIN</c:if>
					 	<c:if test="${idusuario!=null}">Cambiar PIN?</c:if>
					 </label>
					 <input class="form-control" id="pinpda" placeholder="PIN" name="pinpda" disabled/>
				 </div>
				 <div class="form-group">
					 <label for="nombre">Nombre</label>
					 <input class="form-control" id="nombre" placeholder="Nombre" name="nombre" maxlength="40" pattern=".{8,40}"  required
							<c:if test="${idusuario!=null}"> value="${idusuario.getNombreUsuario()}" </c:if>
							<c:if test="${idusuario=null}"> value="" </c:if>
					 />
				 </div>
				<div class="form-group">
					<label for="grupo">Grupo </label>
					<select class="form-control" id="grupo" placeholder="Grupo" name="grupo">
						<c:forEach var="m" items="${grupos}" varStatus="loop">
							<option value="${m.getCodigoGrupo()}"
									<c:if test="${m.getCodigoGrupo()==vGrupo}">selected</c:if>
							> ${m.getDescripcion()} </option>
						</c:forEach>
					</select>
				</div>
				 <div class="checkbox">
					 <label>
						 <input type="checkbox" name="esAdmin"
									 <c:if test="${vesAdmon!=0}">checked value="on"</c:if>
						 > Es Administrador?
					 </label>
				 </div>
			</div>
			<input type="submit" class="btn btn-default" value="Guardar"/>
		</form>
	</div>
</div>