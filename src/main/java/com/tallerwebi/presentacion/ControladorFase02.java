package com.tallerwebi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorFase02 {

    @Autowired
    public ControladorFase02(){
    }

    @RequestMapping("/fase02")
    public ModelAndView irAFase02() {

        return new ModelAndView("fase02");
    }

}

