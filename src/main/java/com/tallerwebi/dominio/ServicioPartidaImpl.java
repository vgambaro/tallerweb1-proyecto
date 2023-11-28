package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Pregunta;
import com.tallerwebi.dominio.entities.Respuesta;
import com.tallerwebi.dominio.entities.Usuario;
import com.tallerwebi.infraestructura.RepositorioPartida;
import com.tallerwebi.infraestructura.RepositorioPregunta;
import com.tallerwebi.infraestructura.RepositorioRespuesta;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioPartida")
@Transactional
public class ServicioPartidaImpl implements ServicioPartida {

	private RepositorioUsuario repositorioUsuario;
	private RepositorioPartida repositorioPartida;

	public ServicioPartidaImpl(RepositorioUsuario repositorioUsuario, RepositorioPartida repositorioPartida) {
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioPartida = repositorioPartida;
	}

	@Override
	public void crearPartida(String email) {
		Usuario usuario = repositorioUsuario.buscar(email);

		repositorioPartida.crearPartida(usuario);
	}
}
