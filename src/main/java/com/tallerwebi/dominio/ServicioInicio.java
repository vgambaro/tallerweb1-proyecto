package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioInicio {
	
	void instanciarNiveles();
	void guardarPreguntaConRespuestas(Pregunta pregunta,List<Respuesta> respuestas);
	List<Pregunta> obtenerTodasLasPreguntas();
	

}
