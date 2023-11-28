package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entities.Partida;

public interface RepositorioPartida {
	Partida buscarPartida(Integer id);

	void guardarPartida(Partida partida);
}
