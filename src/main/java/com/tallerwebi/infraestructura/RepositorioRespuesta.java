package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entities.Pregunta;
import com.tallerwebi.dominio.entities.Respuesta;

import java.util.List;

public interface RepositorioRespuesta {

	Respuesta buscarRespuesta(Integer id);

	void guardarRespuesta(Respuesta respuesta);
	void guardarRespuestas(List<Respuesta> respuesta, Pregunta preguntaRecibida);

}
