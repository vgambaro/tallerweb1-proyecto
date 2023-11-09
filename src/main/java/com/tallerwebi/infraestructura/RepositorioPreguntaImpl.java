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
	public Pregunta buscarPregunta(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Pregunta) session.createCriteria(Pregunta.class).add(Restrictions.eq("id", id)).uniqueResult();

	}

	@Override
	public Pregunta getPreguntaPorNivel(Nivel nivel) {
		Session session = sessionFactory.getCurrentSession();
		return (Pregunta) session.createCriteria(Pregunta.class).add(Restrictions.eq("nivel", nivel)).uniqueResult();
	}

	@Override
	public Long guardarPregunta(Pregunta pregunta) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pregunta);
		Long lastInsertedId = pregunta.getId();

		return lastInsertedId;
	}

	@Override
	public List<Pregunta> getPreguntas() {
		Session session = sessionFactory.getCurrentSession();
		List<Pregunta> preguntas = session.createQuery("FROM Pregunta", Pregunta.class).list();
		return preguntas;
	}

	@Override
	public Long guardarPreguntaFaseUnoConSuRespuesta(Pregunta pregunta, Respuesta respuesta) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pregunta);
		session.save(respuesta);
		return null;
	}

	@Override
	public Pregunta getPreguntaPorId(Long preguntaId){
		Session session = sessionFactory.getCurrentSession();
		Pregunta pregunta = (Pregunta) session.createCriteria(Pregunta.class).add(Restrictions.eq("id",preguntaId)).uniqueResult();
		return pregunta;
	}
}
