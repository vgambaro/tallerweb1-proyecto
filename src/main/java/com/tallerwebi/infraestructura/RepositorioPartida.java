package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Usuario;

public interface RepositorioPartida {
	Partida buscarPartida(Integer id);

	void guardarPartida(Partida partida);

	void crearPartida(Usuario usuario);
}
