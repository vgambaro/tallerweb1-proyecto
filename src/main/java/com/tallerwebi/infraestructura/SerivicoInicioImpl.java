package com.tallerwebi.infraestructura;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerwebi.dominio.Pregunta;
import com.tallerwebi.dominio.RepositorioPregunta;
import com.tallerwebi.dominio.Respuesta;
import com.tallerwebi.dominio.ServicioInicio;

@Service("servicioInicio")
@Transactional
public class SerivicoInicioImpl implements ServicioInicio {

	private RepositorioPregunta servicioInicio;

	@Autowired
	public SerivicoInicioImpl(RepositorioPregunta servicioInicio) {
		this.servicioInicio = servicioInicio;
	}

	@Override
	public void instanciarNiveles() {
		// TODO Auto-generated method stub

	}

	@Override
	public void guardarPreguntaConRespuestas(Pregunta pregunta, List<Respuesta> respuestas) {
		this.servicioInicio.guardarPreguntaConSsusRespuestas(pregunta, respuestas);
	}

	@Override
	public List<Pregunta> obtenerTodasLasPreguntas() {
		
		return servicioInicio.getPreguntas();
	}

}
