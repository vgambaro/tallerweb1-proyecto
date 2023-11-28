package com.tallerwebi.dominio;

import java.util.List;

import javax.transaction.Transactional;

import com.tallerwebi.dominio.entities.Pregunta;
import com.tallerwebi.dominio.entities.Respuesta;
import com.tallerwebi.infraestructura.RepositorioPregunta;
import com.tallerwebi.infraestructura.RepositorioRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("servicioInicio")
@Transactional
public class ServicioInicioImpl implements ServicioInicio {

	private RepositorioPregunta repositorioPregunta;
	private RepositorioRespuesta repositorioRespuesta;

	@Autowired
	public ServicioInicioImpl(RepositorioPregunta repositorioPregunta, RepositorioRespuesta repositorioRespuesta) {
		this.repositorioPregunta = repositorioPregunta;
		this.repositorioRespuesta = repositorioRespuesta;
	}

	@Override
	public void instanciarNiveles() {
		// TODO Auto-generated method stub

	}

	@Override
	public void guardarPreguntaConRespuestas(Pregunta pregunta, List<Respuesta> respuestas) {
		Long preguntaIdGuardada = repositorioPregunta.guardarPregunta(pregunta);

		Pregunta preguntaGuardada = repositorioPregunta.buscarPregunta(preguntaIdGuardada);

		for (Respuesta respuesta : respuestas) {
			respuesta.setPregunta(preguntaGuardada); // Asigna la pregunta a cada respuesta
			repositorioRespuesta.guardarRespuesta(respuesta);
		}
	}

	@Override
	public List<Pregunta> obtenerTodasLasPreguntas() {
		
		return repositorioPregunta.getPreguntas();
	}

}
