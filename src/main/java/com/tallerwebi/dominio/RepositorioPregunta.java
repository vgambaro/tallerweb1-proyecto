package com.tallerwebi.dominio;

public interface RepositorioPregunta {
Pregunta buscarPregunta(Integer id);
Pregunta getPreguntaPorNivel(Nivel nivel);
void guardarPregunta(Pregunta pregunta);

}
