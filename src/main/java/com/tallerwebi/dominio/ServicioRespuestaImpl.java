package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.dominio.entities.Pregunta;
import com.tallerwebi.dominio.entities.Respuesta;
import com.tallerwebi.infraestructura.RepositorioPregunta;
import com.tallerwebi.infraestructura.RepositorioRespuesta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("servicioRespuesta")
@Transactional
public class ServicioRespuestaImpl implements ServicioRespuesta {

	private RepositorioRespuesta repositorioRespuesta;


	public ServicioRespuestaImpl(RepositorioRespuesta repositorioRespuesta) {
		this.repositorioRespuesta = repositorioRespuesta;
	}

	@Override
	public List<Respuesta> obtenerRespuestasPorPregunta(Pregunta pregunta) {
		return repositorioRespuesta.buscarRespuestasPorPregunta(pregunta);
	}
}
