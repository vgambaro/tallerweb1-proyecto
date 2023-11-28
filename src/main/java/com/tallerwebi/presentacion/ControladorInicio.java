package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Pregunta;
import com.tallerwebi.dominio.Respuesta;
import com.tallerwebi.dominio.ServicioInicio;
import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;

import com.tallerwebi.presentacion.models.PreguntaRespuestaForm;
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
    private ServicioInicio servicioInicio;

    @Autowired
    public ControladorInicio(ServicioInicio servicioInicio) {
        this.servicioInicio = servicioInicio;
    }


    @RequestMapping(path = "/cargarPregunta")
    public ModelAndView irACargarPregunta() {
        ModelMap modelo = new ModelMap();
        PreguntaRespuestaForm preguntaRespuestaForm = new PreguntaRespuestaForm();
        modelo.addAttribute("preguntaRespuestaForm", preguntaRespuestaForm);

        return new ModelAndView("cargar-pregunta", modelo);
    }

    @RequestMapping(path = "/guardarPregunta", method = RequestMethod.POST)
    public ModelAndView guardarPregunta(@ModelAttribute("preguntaRespuestaForm") PreguntaRespuestaForm preguntaRespuestaForm) {

        // Guarda la pregunta y las respuestas
        servicioInicio.guardarPreguntaConRespuestas(preguntaRespuestaForm.getPregunta(), preguntaRespuestaForm.getRespuestas());

        return new ModelAndView("redirect:/cargarPregunta");
    }
}


