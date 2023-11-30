package com.tallerwebi.presentacion;
import com.tallerwebi.dominio.ServicioPartida;
import com.tallerwebi.dominio.ServicioPregunta;
import com.tallerwebi.dominio.ServicioRespuesta;
import com.tallerwebi.dominio.entities.Partida;
import com.tallerwebi.dominio.entities.Pregunta;
import com.tallerwebi.dominio.entities.Respuesta;
import javassist.compiler.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPartida {

    private ServicioPartida servicioPartida;
    private ServicioPregunta servicioPregunta;
    private ServicioRespuesta servicioRespuesta;

    @Autowired
    public ControladorPartida(ServicioPartida servicioPartida, ServicioPregunta servicioPregunta, ServicioRespuesta servicioRespuesta) {
        this.servicioPartida = servicioPartida;
        this.servicioPregunta = servicioPregunta;
        this.servicioRespuesta = servicioRespuesta;
    }

    @RequestMapping(path = "/partida", method = RequestMethod.GET)
    public ModelAndView mostrarPartida(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") != null) {
            String emailUsuario = request.getSession().getAttribute("EMAIL").toString();
            Partida partida = servicioPartida.mostrarPartida(emailUsuario);

            Pregunta pregunta = servicioPregunta.obtenerPreguntaPorNivel(partida.getNivel());
            List<Respuesta> respuestas = servicioRespuesta.obtenerRespuestasPorPregunta(pregunta);

            ModelMap model = new ModelMap();

            model.addAttribute("pregunta", pregunta);
            model.addAttribute("respuestas", respuestas);
            model.addAttribute("partida", partida);

            return new ModelAndView("partida", model);
        }
        else{
            return new ModelAndView("redirect:/home");
        }
    }
    @RequestMapping(path = "/enviarRespuesta", method = RequestMethod.GET)
    public ModelAndView enviarRespuesta() {
        return new ModelAndView("redirect:/home");
        }


    @RequestMapping(path = "/enviarRespuesta", method = RequestMethod.POST)
    public ModelAndView enviarRespuesta(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") != null) {
            String emailUsuario = request.getSession().getAttribute("EMAIL").toString();
            String respuestaId = request.getParameter("respuestaId");


        Integer respuestaIdFormatted = Integer.parseInt(respuestaId);

        Partida partida = servicioPartida.responderPregunta(respuestaIdFormatted.intValue(), emailUsuario);

            if(partida.getNivel()== null){
                return new ModelAndView("redirect:/ganaste");
            }

        Pregunta pregunta = servicioPregunta.obtenerPreguntaPorNivel(partida.getNivel());
        List<Respuesta> respuestas = servicioRespuesta.obtenerRespuestasPorPregunta(pregunta);

        ModelMap model = new ModelMap();

        model.addAttribute("partida", partida);
        model.addAttribute("pregunta", pregunta);
        model.addAttribute("respuestas", respuestas);

        if(partida.getVidas() == 0){
            return new ModelAndView("redirect:/perdiste");
        }



        return new ModelAndView("partida", model);
        }else{
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(path = "/perdiste")
    public ModelAndView perdiste(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") != null) {
            String emailUsuario = request.getSession().getAttribute("EMAIL").toString();

            Partida partida = servicioPartida.obtenerPartidaDelUsuario(emailUsuario);

            ModelMap model = new ModelMap();
            model.addAttribute("partida", partida);

            servicioPartida.reiniciarPartida(emailUsuario);

            return new ModelAndView("perdiste", model);
        }else{
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(path = "/ganaste")
    public ModelAndView ganaste(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMAIL") != null) {
            String emailUsuario = request.getSession().getAttribute("EMAIL").toString();
            servicioPartida.reiniciarPartida(emailUsuario);

        return new ModelAndView("ganaste");
        }else{
            return new ModelAndView("redirect:/home");
        }
    }

}