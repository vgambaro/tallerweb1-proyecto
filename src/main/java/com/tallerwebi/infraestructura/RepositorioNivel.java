package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Usuario;

import java.util.List;

public interface RepositorioNivel {

	Nivel buscarProximoNivel(int numeroDeNivel);

	Nivel obtenerPrimerNivel();

	List<Nivel> obtenerTodos();
}
