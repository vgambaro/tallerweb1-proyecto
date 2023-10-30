package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import com.tallerwebi.presentacion.DatosRegistro;

public interface ServicioUsuario {


	public void registrarUsuario(DatosRegistro datosRegistro);

	public Usuario buscarUsuarioPorEmail(String email);

}
