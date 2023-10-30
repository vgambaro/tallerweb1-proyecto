package com.tallerwebi.presentacion;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class ControladorHome {
    // acá es donde empieza la app: localhost:8080/spring. Esta es la raiz, no se especifica una pag en particular para navegar.
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/home");
    }
    // "/" es la raiz
}
