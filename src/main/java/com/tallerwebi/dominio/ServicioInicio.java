package com.tallerwebi.dominio;

import java.util.ArrayList;
import java.util.List;

public interface ServicioInicio {
	
	void instanciarNiveles();
	void guardarPreguntaConRespuestas(Pregunta pregunta,List<Respuesta> respuestas);

	void guardarPreguntaConRespuestas(Pregunta pregunta, ArrayList<Respuesta> respuestas);

	ArrayList<Pregunta> obtenerTodasLasPreguntas();

}
