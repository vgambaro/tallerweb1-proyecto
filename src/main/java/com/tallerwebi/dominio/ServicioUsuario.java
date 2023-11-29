package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entities.Usuario;
import com.tallerwebi.presentacion.models.DatosRegistro;

public interface ServicioUsuario {


	public void registrarUsuario(DatosRegistro datosRegistro);

	public Usuario buscarUsuarioPorEmail(String email);


}
