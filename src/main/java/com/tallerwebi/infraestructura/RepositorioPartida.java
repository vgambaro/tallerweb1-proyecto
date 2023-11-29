package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Usuario;

public interface RepositorioPartida {
	Partida buscarPartida(Integer id);

	void guardarPartida(Partida partida);

	Partida crearPartida(Usuario usuario);

	Partida buscarPartidaPorUsuario(Usuario usuario);

	Partida aumentarNivel(Partida partida);

	Partida bajarVida(Partida partida);
}
