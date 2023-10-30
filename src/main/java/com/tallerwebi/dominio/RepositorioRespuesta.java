package com.tallerwebi.dominio;

public interface RepositorioRespuesta {

	Respuesta buscarRespuesta(Integer id);

	void guardarRespuesta(Respuesta respuesta);
}
