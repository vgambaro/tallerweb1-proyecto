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
	public void guardarPreguntaConRespuestas(Pregunta pregunta, List<Respuesta> respuestas) {

	}

	@Override
	public void guardarPreguntaConRespuestas(Pregunta pregunta, ArrayList<Respuesta> respuestas) {
		Long preguntaId = repositorioPregunta.guardarPregunta(pregunta);
		Pregunta preguntaRecibida = repositorioPregunta.getPreguntaPorId(preguntaId);
		for(Respuesta r : respuestas){
			r.setPregunta(preguntaRecibida);
			repositorioRespuesta.guardarRespuesta(r);
		}
		//this.servicioInicio.guardarPreguntaConSsusRespuestas(pregunta, respuestas);
	}

	@Override
	public ArrayList<Pregunta> obtenerTodasLasPreguntas() {
		
		return (ArrayList<Pregunta>) repositorioPregunta.getPreguntas();
		this.repositorioPregunta.guardarPreguntaConSsusRespuestas(pregunta, respuestas);
	}

	@Override
	public List<Pregunta> obtenerTodasLasPreguntas() {

		return repositorioPregunta.getPreguntas();
	}

}
