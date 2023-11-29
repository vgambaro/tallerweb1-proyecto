package com.tallerwebi.dominio;
import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.dominio.entities.Pregunta;
import com.tallerwebi.dominio.entities.Respuesta;

import java.util.ArrayList;
import java.util.List;

public interface ServicioRespuesta {

	List<Respuesta> obtenerRespuestasPorPregunta(Pregunta pregunta);


}
