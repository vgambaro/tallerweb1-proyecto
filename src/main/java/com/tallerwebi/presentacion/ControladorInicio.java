package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioInicio;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.ServicioRespuesta;
import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.entities.Nivel;
import com.tallerwebi.dominio.entities.Usuario;
import com.tallerwebi.presentacion.models.PreguntaRespuestaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorInicio {
    private ServicioInicio servicioInicio;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorInicio(ServicioInicio servicioInicio, ServicioUsuario servicioUsuario) {
        this.servicioInicio = servicioInicio;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(path = "/inicio")
    public ModelAndView irAInicio(HttpServletRequest request) {
        String emailUsuario = request.getSession().getAttribute("EMAIL").toString();

        Usuario usuario = servicioUsuario.buscarUsuarioPorEmail(emailUsuario);

        ModelMap modelo = new ModelMap();

        modelo.addAttribute("nivelActual", usuario.getNivel().getNumero());

        return new ModelAndView("inicio",modelo);
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


