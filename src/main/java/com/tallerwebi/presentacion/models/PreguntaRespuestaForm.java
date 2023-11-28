package com.tallerwebi.presentacion.models;

import com.tallerwebi.dominio.entities.Pregunta;
import com.tallerwebi.dominio.entities.Respuesta;

import java.util.ArrayList;
import java.util.List;

public class PreguntaRespuestaForm {
    private Pregunta pregunta = new Pregunta();
    private List<Respuesta> respuestas = new ArrayList<>();

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
