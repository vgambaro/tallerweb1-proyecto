package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entities.Usuario;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import com.tallerwebi.presentacion.models.DatosRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service ("ServicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	public void registrarUsuario(DatosRegistro datosRegistro) {
		Usuario usuario = new Usuario();
		usuario.setEmail(datosRegistro.getEmail());
		usuario.setPassword(datosRegistro.getPassword());
		usuario.setActivo(true);
		usuario.setRol("usuario");
		usuario.setNivel(1);
		repositorioUsuario.guardar(usuario);
	}

	public Usuario buscarUsuarioPorEmail(String email) {
		return repositorioUsuario.buscar(email);
	}
}


