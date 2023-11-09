package com.tallerwebi.infraestructura;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerwebi.dominio.Pregunta;
import com.tallerwebi.dominio.RepositorioPregunta;
import com.tallerwebi.dominio.Respuesta;
import com.tallerwebi.dominio.ServicioInicio;

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
	public void guardarPreguntaConRespuestas(PreguntaRespuestaForm preguntaRespuestaForm) {
		Long preguntaIdGuardada = repositorioPregunta.guardarPregunta(preguntaRespuestaForm.getPregunta());

		Pregunta preguntaGuardada = repositorioPregunta.buscarPregunta(preguntaIdGuardada);

		List<Respuesta> respuestas = preguntaRespuestaForm.getRespuestas();

		for (Respuesta respuesta : respuestas) {
			respuesta.setPregunta(preguntaGuardada); // Asigna la pregunta a cada respuesta
			repositorioRespuesta.guardarRespuesta(respuesta);
		}
	}

	@Override
	public ArrayList<Pregunta> obtenerTodasLasPreguntas() {
		
		return (ArrayList<Pregunta>) repositorioPregunta.getPreguntas();
	}

	@Override
	public void guardarPreguntaFaseUnoConSuRespuesta(PreguntaRespuestaFormFaseUno preguntaRespuestaFormFaseUno) {
		Long preguntaIdGuardada = repositorioPregunta.guardarPregunta(preguntaRespuestaFormFaseUno.getPregunta());
		Pregunta preguntaGuardada = repositorioPregunta.buscarPregunta(preguntaIdGuardada);

		preguntaRespuestaFormFaseUno.getRespuesta().setPregunta(preguntaGuardada);
	}

	@Override
	public Pregunta obtenerPreguntaRandomDeLaFaseUno() {
		return null;
	}

}
