package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.dominio.entities.Pregunta;

import java.util.List;

public interface RepositorioPregunta {
	Pregunta buscarPregunta(Long id);

	Pregunta getPreguntaPorNivel(Nivel nivel);

	Long guardarPregunta(Pregunta pregunta);

	Pregunta getPreguntaPorId(Long idPregunta);

	List<Pregunta> getPreguntas();

}
