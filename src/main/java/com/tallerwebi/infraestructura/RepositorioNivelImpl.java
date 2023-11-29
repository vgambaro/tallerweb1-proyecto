package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioNivel")
public class RepositorioNivelImpl implements RepositorioNivel {
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioNivelImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Nivel buscarProximoNivel(int numeroDeNivel) {
		final Session session = sessionFactory.getCurrentSession();
		return (Nivel) session.createCriteria(Nivel.class)
				.add(Restrictions.eq("numero", numeroDeNivel+1))
				.uniqueResult();
	}

	@Override
	public Nivel obtenerPrimerNivel() {
		final Session session = sessionFactory.getCurrentSession();
		return (Nivel) session.createCriteria(Nivel.class)
				.add(Restrictions.eq("numero", 1))
				.uniqueResult();
	}
}
