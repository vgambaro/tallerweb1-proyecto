package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entities.*;
import com.tallerwebi.infraestructura.RepositorioPregunta;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioPregunta")
@Transactional
public class ServicioPreguntaImpl implements ServicioPregunta {

	private RepositorioPregunta repositorioPregunta;

	public ServicioPreguntaImpl(RepositorioPregunta repositorioPregunta) {
		this.repositorioPregunta = repositorioPregunta;
	}

	@Override
	public Pregunta obtenerPreguntaPorNivel(Nivel nivel) {
		return repositorioPregunta.getPreguntaPorNivel(nivel);
	}
}
