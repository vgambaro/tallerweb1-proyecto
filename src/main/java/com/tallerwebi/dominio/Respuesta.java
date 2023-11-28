package com.tallerwebi.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//LA OPCION TIENE UN IDE.BOOLEAN DE SI ES CORRECTA 
//Y EL ID DE LA PREGUNTA A LA QUE ESTÁ ASOCIADA

@Entity
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Boolean esCorrecta;
	private String descripcion;

	/*
	 * INSERTABLE y UPDATABLE EN false PARA QUE LA COLUMNA idAsociado no SE
	 * MODIFIQUE DIRECTAMENTE AL PERSISTIR UNA RESPUESTA
	 */

	@ManyToOne
	@JoinColumn(name = "id_pregunta") // Nombre de la columna de la clave foránea en la tabla Respuesta
	private Pregunta pregunta;
	// Resto de los getter y setter

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEsCorrecta() {
		return esCorrecta;
	}

	public void setEsCorrecta(Boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}