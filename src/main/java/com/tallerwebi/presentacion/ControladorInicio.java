package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioInicio;

import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.presentacion.models.PreguntaRespuestaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import java.util.List;

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

        List<Nivel> niveles = servicioInicio.obtenerTodosLosNiveles();

        modelo.addAttribute("preguntaRespuestaForm", preguntaRespuestaForm);
        modelo.addAttribute("niveles", niveles);

        return new ModelAndView("cargar-pregunta", modelo);
    }

    @RequestMapping(path = "/guardarPregunta", method = RequestMethod.POST)
    public ModelAndView guardarPregunta(@ModelAttribute("preguntaRespuestaForm") PreguntaRespuestaForm preguntaRespuestaForm) {

        // Guarda la pregunta y las respuestas
        servicioInicio.guardarPreguntaConRespuestas(preguntaRespuestaForm.getPregunta(), preguntaRespuestaForm.getRespuestas());

        return new ModelAndView("redirect:/cargarPregunta");
    }
}


