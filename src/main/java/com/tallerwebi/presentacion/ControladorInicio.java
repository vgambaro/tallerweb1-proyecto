package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Pregunta;
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

	@RequestMapping(path = "/cargarPregunta")
	public ModelAndView irACargarPregunta() {
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("pregunta", new Pregunta());

		return new ModelAndView("cargar-pregunta", modelo);

	}

	@RequestMapping(path = "/guardarPregunta", method = RequestMethod.POST)
	public ModelAndView guardarPregunta(@ModelAttribute("pregunta") Pregunta pregunta) {
		ModelMap model = new ModelMap();
		servicioInicio.guardarPregunta(pregunta);

		return new ModelAndView("redirect:/cargar-pregunta");
	}

}