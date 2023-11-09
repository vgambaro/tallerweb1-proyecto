package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioPregunta {
	Pregunta buscarPregunta(Long id);

	Pregunta getPreguntaPorNivel(Nivel nivel);

	Long guardarPregunta(Pregunta pregunta);

	Pregunta getPreguntaPorId(Long idPregunta);

	List<Pregunta> getPreguntas();

	
}
