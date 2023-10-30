package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.presentacion.DatosRegistro;
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


