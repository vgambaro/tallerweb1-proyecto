package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioPregunta {
	Pregunta buscarPregunta(Integer id);

	Pregunta getPreguntaPorNivel(Nivel nivel);

	Long guardarPregunta(Pregunta pregunta);

	Pregunta getPreguntaPorId(Long idPregunta);

	void guardarPreguntaConSsusRespuestas(Pregunta pregunta, List<Respuesta> respuestas);

	List<Pregunta> getPreguntas();

	
}
