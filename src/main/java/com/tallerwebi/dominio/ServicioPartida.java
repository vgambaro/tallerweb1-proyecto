package com.tallerwebi.dominio;
import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Respuesta;
import com.tallerwebi.dominio.entities.Usuario;

public interface ServicioPartida {

	Partida mostrarPartida(String email);

	Partida responderPregunta(Integer respuestaId, String email);

	Partida obtenerPartidaDelUsuario(String email);

	void reiniciarPartida(String email);


}
