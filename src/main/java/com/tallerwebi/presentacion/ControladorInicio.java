package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        servicioInicio.guardarPreguntaConRespuestas(preguntaRespuestaForm);

        return new ModelAndView("redirect:/cargarPregunta");
    }
}



