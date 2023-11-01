package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Pregunta;
import com.tallerwebi.dominio.Respuesta;
import com.tallerwebi.dominio.ServicioInicio;
import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class ControladorInicio {
	private ServicioLogin servicioLogin;
	private ServicioInicio servicioInicio;

	@Autowired
	public ControladorInicio(ServicioLogin servicioLogin) {
		this.servicioLogin = servicioLogin;
	}

	@RequestMapping(path = "/inicio")
	public ModelAndView inicioLogueado() {

		return new ModelAndView("inicio");
	}
//
//	@RequestMapping(path = "/cargarPregunta")
//	public ModelAndView irACargarPregunta() {
//		ModelMap model = new ModelMap();
//
//		Pregunta pregunta = new Pregunta();
//		List<Respuesta> respuestas = new ArrayList<>();
//		for (int i = 0; i < 4; i++) {
//			Respuesta respuesta = new Respuesta();
//			respuesta.setPregunta(pregunta);
//			respuestas.add(respuesta);
//		}
//
//		pregunta.setRespuestas(respuestas); // Establece las respuestas en la pregunta
//
//		model.addAttribute("pregunta", pregunta);
//
//		return new ModelAndView("cargar-pregunta", model);
//	}



	@RequestMapping(path = "/cargarPregunta")
	public ModelAndView irACargarPregunta() {
	    ModelMap modelo = new ModelMap();
	    modelo.addAttribute("pregunta", new Pregunta());
	    modelo.addAttribute("respuestas", new ArrayList<Respuesta>());

	    return new ModelAndView("cargar-pregunta", modelo);
	}


	@RequestMapping(path = "/guardarPregunta", method = RequestMethod.POST)
	public ModelAndView guardarPregunta(@ModelAttribute("pregunta") Pregunta pregunta,
			@ModelAttribute("respuestas") List<Respuesta> respuestas) {
		ModelMap model = new ModelMap();

		// Asigna la pregunta a cada respuesta
		for (Respuesta respuesta : respuestas) {
			respuesta.setPregunta(pregunta);
		}

		// Guarda la pregunta y las respuestas
		servicioInicio.guardarPreguntaConRespuestas(pregunta, respuestas);

		return new ModelAndView("redirect:/cargar-pregunta");
	}

	@RequestMapping(path = "/verPreguntas")
	public ModelAndView verPreguntas() {
		ModelMap model = new ModelMap();

		List<Pregunta> preguntas = servicioInicio.obtenerTodasLasPreguntas();

		model.addAttribute("preguntas", preguntas);

		return new ModelAndView("ver-preguntas", model);

	}
}