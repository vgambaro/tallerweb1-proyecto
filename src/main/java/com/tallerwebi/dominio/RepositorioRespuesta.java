package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioRespuesta {

	Respuesta buscarRespuesta(Integer id);

	void guardarRespuesta(Respuesta respuesta);

	void guardarRespuestas(List<Respuesta> respuestas, Pregunta preguntaRecibida);
	
}
