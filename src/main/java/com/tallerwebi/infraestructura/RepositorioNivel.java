package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Usuario;

public interface RepositorioNivel {

	Nivel buscarProximoNivel(int numeroDeNivel);

	Nivel obtenerPrimerNivel();
}
