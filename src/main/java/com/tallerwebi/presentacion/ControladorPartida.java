package com.tallerwebi.presentacion;
import com.tallerwebi.dominio.ServicioPartida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorPartida {

    private ServicioPartida servicioPartida;

    @Autowired
    public ControladorPartida(ServicioPartida servicioPartida) {
        this.servicioPartida = servicioPartida;
    }

    @RequestMapping(path = "/partida", method = RequestMethod.GET)
    public ModelAndView mostrarPartida(HttpServletRequest request) {
        String emailUsuario = request.getSession().getAttribute("EMAIL").toString();

        servicioPartida.crearPartida(emailUsuario);

        ModelMap model = new ModelMap();

        model.addAttribute("pregunta", 1);

        return new ModelAndView("partida", model);
    }




}