package com.tallerwebi.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
public class Pregunta {

	// ENTIDAD PREGUNTA--
	/*
	 * TIENE QUE TENER UN ID,LA PREGUNTA EN SI(DESC), LA PROV(NIVEL),FASE(NUMERO)
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_pregunta;
	private String descripcion;
	private Nivel nivel;
	private Integer fase;

	public void setId(Long id) {
		this.id_pregunta = id;
	}

	public Long getId() {
		return id_pregunta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Integer getFase() {
		return fase;
	}

	public void setFase(Integer fase) {
		this.fase = fase;
	}

	public Long getId_pregunta() {
		return id_pregunta;
	}

	public void setId_pregunta(Long id_pregunta) {
		this.id_pregunta = id_pregunta;
	}

}
