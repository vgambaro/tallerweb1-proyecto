package com.tallerwebi.infraestructura;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tallerwebi.dominio.entities.Partida;

@Repository("repositorioPartida")
public class RepositorioPartidaImpl implements RepositorioPartida {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioPartidaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Partida buscarPartida(Integer id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Partida) session.createCriteria(Partida.class).add(Restrictions.eq("id", id)).uniqueResult();

	}

	@Override
	public void guardarPartida(Partida partida) {
		sessionFactory.getCurrentSession().save(partida);

	}

}
