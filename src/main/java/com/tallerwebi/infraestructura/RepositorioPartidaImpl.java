package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.dominio.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tallerwebi.dominio.entities.Partida;

@Repository("repositorioPartida")
public class RepositorioPartidaImpl implements RepositorioPartida {

	private SessionFactory sessionFactory;

	private RepositorioNivel repositorioNivel;

	@Autowired
	public RepositorioPartidaImpl(SessionFactory sessionFactory, RepositorioNivel repositorioNivel) {
		this.sessionFactory = sessionFactory;
		this.repositorioNivel = repositorioNivel;
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

	@Override
	public Partida crearPartida(Usuario usuario) {
		Partida partidaNueva = new Partida();
		Nivel nivelUno = repositorioNivel.obtenerPrimerNivel();

		partidaNueva.setNivel(nivelUno);
		partidaNueva.setUsuario(usuario);

		sessionFactory.getCurrentSession().save(partidaNueva);

		return partidaNueva;
	}

	@Override
	public Partida buscarPartidaPorUsuario(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		Partida partidaEncontrada = (Partida) session.createCriteria(Partida.class).add(Restrictions.eq("usuario", usuario)).uniqueResult();
		return partidaEncontrada;
	}

	@Override
	public Partida aumentarNivel(Partida partida, Nivel nuevoNivel) {

		partida.setNivel(nuevoNivel);

		sessionFactory.getCurrentSession().save(partida);

		return partida;
	}

	@Override
	public Partida bajarVida(Partida partida) {

		partida.setVidas(partida.getVidas()-1);

		sessionFactory.getCurrentSession().save(partida);

		return partida;
	}

	@Override
	public void reiniciar(Partida partida) {
		partida.setVidas(3);

		Nivel primerNivel = new Nivel();
		primerNivel.setNumero(1);
		partida.setNivel(primerNivel);

		sessionFactory.getCurrentSession().save(partida);
	}


}
