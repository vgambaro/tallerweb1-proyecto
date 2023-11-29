package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Respuesta;
import com.tallerwebi.dominio.entities.Usuario;
import com.tallerwebi.infraestructura.RepositorioPartida;
import com.tallerwebi.infraestructura.RepositorioPregunta;
import com.tallerwebi.infraestructura.RepositorioRespuesta;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioPartida")
@Transactional
public class ServicioPartidaImpl implements ServicioPartida {

	private RepositorioUsuario repositorioUsuario;
	private RepositorioPartida repositorioPartida;
	private RepositorioRespuesta repositorioRespuesta;
	private RepositorioPregunta repositorioPregunta;

	public ServicioPartidaImpl(RepositorioUsuario repositorioUsuario, RepositorioPartida repositorioPartida, RepositorioRespuesta repositorioRespuesta, RepositorioPregunta repositorioPregunta) {
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioPartida = repositorioPartida;
		this.repositorioRespuesta = repositorioRespuesta;
		this.repositorioPregunta = repositorioPregunta;
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
			repositorioPartida.aumentarNivel(partida);
		}else{
			repositorioPartida.bajarVida(partida);
		}

		return partida;
	}

}
