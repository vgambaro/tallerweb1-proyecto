package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Partida {

	// TIENE UN ID DE LA PARTIDA,EL USUARIO,NUMERO DEL NIVEL
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer vidas;
	private Nivel nivel;

	// RELACION CON EL JUGADOR--USER
	@OneToOne
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVidas(Integer vidas) {
		this.vidas = vidas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getVidas() {
		return vidas;
	}

	public void aumentarVidas() {
		this.vidas = vidas++;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

}
