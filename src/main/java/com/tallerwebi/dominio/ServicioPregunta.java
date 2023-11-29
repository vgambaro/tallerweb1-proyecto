package com.tallerwebi.dominio;
import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Pregunta;
import com.tallerwebi.dominio.entities.Respuesta;

public interface ServicioPregunta {

	Pregunta obtenerPreguntaPorNivel(Nivel nivel);


}
