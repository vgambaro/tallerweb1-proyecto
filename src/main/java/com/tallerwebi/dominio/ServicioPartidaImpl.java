package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Respuesta;
import com.tallerwebi.dominio.entities.Usuario;
import com.tallerwebi.infraestructura.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioPartida")
@Transactional
public class ServicioPartidaImpl implements ServicioPartida {

	private RepositorioUsuario repositorioUsuario;
	private RepositorioPartida repositorioPartida;
	private RepositorioRespuesta repositorioRespuesta;
	private RepositorioPregunta repositorioPregunta;
	private RepositorioNivel repositorioNivel;

	public ServicioPartidaImpl(RepositorioUsuario repositorioUsuario, RepositorioPartida repositorioPartida, RepositorioRespuesta repositorioRespuesta, RepositorioPregunta repositorioPregunta, RepositorioNivel repositorioNivel) {
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioPartida = repositorioPartida;
		this.repositorioRespuesta = repositorioRespuesta;
		this.repositorioPregunta = repositorioPregunta;
		this.repositorioNivel = repositorioNivel;
	}

	@Override
	public Partida mostrarPartida(String email) {
		Usuario usuario = repositorioUsuario.buscar(email);

		Partida partidaDelUsuario = repositorioPartida.buscarPartidaPorUsuario(usuario);

		if(partidaDelUsuario == null){
			return repositorioPartida.crearPartida(usuario);
		}

		return partidaDelUsuario;
	}

	@Override
	public Partida responderPregunta(Integer respuestaId, String email) {
		Respuesta respuestaContestada = repositorioRespuesta.buscarRespuesta(respuestaId);
		Usuario usuario = repositorioUsuario.buscar(email);
		Partida partida = repositorioPartida.buscarPartidaPorUsuario(usuario);

		if(respuestaContestada.getEsCorrecta()){
			Nivel nuevoNivel = repositorioNivel.buscarProximoNivel(partida.getNivel().getNumero());
			repositorioPartida.aumentarNivel(partida, nuevoNivel);
			repositorioUsuario.aumentarNivel(usuario, nuevoNivel);
		}else{
			repositorioPartida.bajarVida(partida);
		}

		return partida;
	}

}
