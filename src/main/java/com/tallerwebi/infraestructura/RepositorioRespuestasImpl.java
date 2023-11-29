package com.tallerwebi.infraestructura;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tallerwebi.dominio.entities.Pregunta;
import com.tallerwebi.dominio.entities.Respuesta;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("repositorioRespuesta")
public class RepositorioRespuestasImpl implements RepositorioRespuesta {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioRespuestasImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Respuesta buscarRespuesta(Integer id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Respuesta) session.createCriteria(Respuesta.class).add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	@Override
	public void guardarRespuesta(Respuesta respuesta) {
		sessionFactory.getCurrentSession().save(respuesta);

	}

	@Override
	public void guardarRespuestas(List<Respuesta> respuestas, Pregunta preguntaRecibida){
		for (Respuesta r : respuestas){
			sessionFactory.getCurrentSession().save(r);
		}
	}

	@Override
	public List<Respuesta> buscarRespuestasPorPregunta(Pregunta pregunta) {
		final Session session = sessionFactory.getCurrentSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Respuesta> criteriaQuery = builder.createQuery(Respuesta.class);
		Root<Respuesta> root = criteriaQuery.from(Respuesta.class);

		criteriaQuery.select(root).where(builder.equal(root.get("pregunta"), pregunta));

		return session.createQuery(criteriaQuery).getResultList();
	}


}
