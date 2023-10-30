package com.tallerwebi.dominio;

public interface RepositorioPartida {
	Partida buscarPartida(Integer id);

	void guardarPartida(Partida partida);
}
