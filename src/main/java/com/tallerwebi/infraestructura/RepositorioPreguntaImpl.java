package com.tallerwebi.infraestructura;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tallerwebi.dominio.Nivel;
import com.tallerwebi.dominio.Pregunta;
import com.tallerwebi.dominio.RepositorioPregunta;
import com.tallerwebi.dominio.Respuesta;
import com.tallerwebi.dominio.Usuario;

@Repository("repositorioPregunta")
public class RepositorioPreguntaImpl implements RepositorioPregunta {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioPreguntaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Pregunta buscarPregunta(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return (Pregunta) session.createCriteria(Pregunta.class).add(Restrictions.eq("id", id)).uniqueResult();

	}

	@Override
	public Pregunta getPreguntaPorNivel(Nivel nivel) {
		Session session = sessionFactory.getCurrentSession();
		return (Pregunta) session.createCriteria(Pregunta.class).add(Restrictions.eq("nivel", nivel)).uniqueResult();
	}

	@Override
	public void guardarPreguntaConSsusRespuestas(Pregunta pregunta, List<Respuesta> respuestas) {
		Session session = sessionFactory.getCurrentSession();

		session.save(pregunta);

		for (Respuesta r : respuestas) {
			session.save(r);
		}
	}

	@Override
	public List<Pregunta> getPreguntas() {
		Session session = sessionFactory.getCurrentSession();
		List<Pregunta> preguntas = session.createQuery("FROM Pregunta", Pregunta.class).list();
		return preguntas;
	}
}
