package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import com.tallerwebi.dominio.entities.Usuario;

public interface ServicioLogin {

	Usuario consultarUsuario(String email, String password);

	void registrar(Usuario usuario) throws UsuarioExistente;

	Integer consultarNivelActual(Usuario usuario);

}
