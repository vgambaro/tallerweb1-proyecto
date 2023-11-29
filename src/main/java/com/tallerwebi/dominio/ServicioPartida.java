package com.tallerwebi.dominio;
import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Respuesta;

public interface ServicioPartida {

	Partida mostrarPartida(String email);

	Partida responderPregunta(Integer respuestaId, String email);


}
