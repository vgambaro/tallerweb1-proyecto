package com.tallerwebi.presentacion;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//FASE 01- PREGUNTAS DE APROX
@Controller
public class ControladorFase01 {
    //CONTADOR DE TIEMPO
    private int contador = 0;
    @RequestMapping(path = "/fase1")
    public ModelAndView fase01() {

        return new ModelAndView("fase1");
    }
    @RequestMapping(path = "/contador", method = RequestMethod.GET)
    public ModelAndView mostrarContador() {
        ModelAndView modelAndView = new ModelAndView("fase1");
        modelAndView.addObject("contador", contador);
        return modelAndView;
    }

    // Iniciar el contador cuando se accede a /fase1
    @RequestMapping(path = "/fase1", method = RequestMethod.GET)
    public ModelAndView iniciarContador() {
        contador = 0; // Reiniciar el contador
        return new ModelAndView("redirect:/contador");
    }

    // Incrementar el contador cada segundo (1000 ms)
    @Scheduled(fixedRate = 1000)
    public void incrementarContador() {
        contador++;
    }

    // Detener el contador después de 30 segundos (30000 ms)
    @Scheduled(fixedRate = 30000)
    public void detenerContador() {
        contador = 0; // Reiniciar el contador
    }
}