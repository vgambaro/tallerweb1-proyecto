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
        Pregunta pregunta = new Pregunta();
        List<Respuesta> respuestas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Respuesta respuesta = new Respuesta();
            respuestas.add(respuesta);
        }
        modelo.addAttribute("pregunta", pregunta);
        modelo.addAttribute("respuestas", respuestas);

        return new ModelAndView("cargar-pregunta", modelo);
    }

    @RequestMapping(path = "/guardarPregunta", method = RequestMethod.POST)
    public ModelAndView guardarPregunta(@ModelAttribute("pregunta") Pregunta pregunta,
                                        @ModelAttribute("respuestas") ArrayList<Respuesta> respuestas) {

        // Guarda la pregunta y las respuestas
        servicioInicio.guardarPreguntaConRespuestas(pregunta, respuestas);

        return new ModelAndView("redirect:/cargarPregunta");
    }
}



