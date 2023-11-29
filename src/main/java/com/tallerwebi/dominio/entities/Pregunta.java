package com.tallerwebi.dominio.entities;

import javax.persistence.*;

@Entity
public class Pregunta {

	// ENTIDAD PREGUNTA--
	/*
	 * TIENE QUE TENER UN ID,LA PREGUNTA EN SI(DESC), LA PROV(NIVEL),FASE(NUMERO)
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	@ManyToOne
	private Nivel nivel;
	private Integer fase;
	

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
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

}
