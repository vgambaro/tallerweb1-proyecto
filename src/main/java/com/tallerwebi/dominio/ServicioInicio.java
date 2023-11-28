package com.tallerwebi.dominio;
import com.tallerwebi.dominio.entities.Pregunta;
import com.tallerwebi.dominio.entities.Respuesta;

import java.util.List;

public interface ServicioInicio {

	void instanciarNiveles();

	void guardarPreguntaConRespuestas(Pregunta pregunta, List<Respuesta> respuestas);

	List<Pregunta> obtenerTodasLasPreguntas();

}
