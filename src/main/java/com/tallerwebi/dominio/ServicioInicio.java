package com.tallerwebi.dominio;

import java.util.ArrayList;
import java.util.List;

public interface ServicioInicio {
	
	void instanciarNiveles();

	void guardarPreguntaConRespuestas(PreguntaRespuestaForm pregunta);

	ArrayList<Pregunta> obtenerTodasLasPreguntas();

	void guardarPreguntaFaseUnoConSuRespuesta(PreguntaRespuestaFormFaseUno preguntaRespuestaFormFaseUno);

	Pregunta obtenerPreguntaRandomDeLaFaseUno();
}
