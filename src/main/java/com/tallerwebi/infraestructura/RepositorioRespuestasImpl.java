package com.tallerwebi.infraestructura;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tallerwebi.dominio.Pregunta;
import com.tallerwebi.dominio.RepositorioRespuesta;
import com.tallerwebi.dominio.Respuesta;

@Repository("repositorioRespuesta")
public class RepositorioRespuestasImpl implements RepositorioRespuesta {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioRespuestasImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Respuesta buscarRespuesta(Integer idAsociado) {
		final Session session = sessionFactory.getCurrentSession();
		return (Respuesta) session.createCriteria(Respuesta.class).add(Restrictions.eq("idAsociado", idAsociado))
				.uniqueResult();
	}

	@Override
	public void guardarRespuesta(Respuesta respuesta) {
		sessionFactory.getCurrentSession().save(respuesta);

	}

}
