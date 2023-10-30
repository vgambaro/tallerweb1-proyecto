package com.tallerwebi.presentacion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorFase3 {
    @RequestMapping(path = "/fase03")
    public ModelAndView fase03() {
        return new ModelAndView("fase03");
    }
}
