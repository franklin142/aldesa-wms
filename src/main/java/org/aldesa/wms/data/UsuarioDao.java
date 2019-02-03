/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aldesa.wms.data;

import java.math.BigDecimal;
import java.util.List;

import org.aldesa.wms.model.*;

public interface UsuarioDao {
    public Usuario findByUsuario(String usuario);
    public Usuario findByPdaPin(String pdaPin);
    public void register(Usuario usuario);
    public void register(Permiso permiso);
    public void register(Grupo grupo);
    public void update(Grupo grupo);
    public List<Usuario> getAll();
    public List<Usuario> getAll(int grupo);
    public List<Usuario> getAll(String estado);
    public void update(Usuario usuario);
    public TokenAutenticacion login(String usuario, String clave);
    public TokenAutenticacion login(String pinPda) throws Exception;
    public Boolean isValidToken(String token, String usuario) throws Exception;
    public Boolean isValidToken(TokenAutenticacion token) throws Exception;
    public Boolean isValidAdminToken(String token, String usuario) throws Exception;
    public Boolean isValidAdminToken(TokenAutenticacion token) throws Exception;
    public boolean esClaveUsuario(String usuario, String clave);
    public boolean cambiarClave(String usuario, String claveActual, String claveNueva);
    public List<PermisosUsuarios> getPermisosUsuario(String usuario);
    public List<Permiso> getPermisos();
    public void ActualizarPermiso(int idpermiso, String usuario, boolean asignar);
    public List<Grupo> getGrupos();
    public Grupo getGrupo(BigDecimal id);
    public List<Object[]> getUrlsGrupo(BigDecimal id);
    public void updateUrlGrupo(BigDecimal grupo, BigDecimal url, boolean agregar);
	public boolean usuarioTienePermiso(String usuario, BigDecimal url);
	public void cerrarSesion(String token, String usuario) throws Exception;
    public void habilitarUsuario(String usuario, boolean habilitar) throws Exception;
    public void eliminarUsuario(String usuario) throws Exception;
    public void eliminarGrupo(String grupo) throws Exception;

}
