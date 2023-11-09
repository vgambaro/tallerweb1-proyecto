package com.tallerwebi.dominio;
public class PreguntaRespuestaFormFaseUno {
    public Pregunta pregunta = new Pregunta();
    public Respuesta respuesta = new Respuesta();

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
}
