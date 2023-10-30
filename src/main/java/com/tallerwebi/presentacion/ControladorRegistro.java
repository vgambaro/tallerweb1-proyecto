package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {
    private ServicioUsuario servicioUsuario;
    @Autowired
    public ControladorRegistro(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public ModelAndView registrar(String email) {
        ModelMap modelo = new ModelMap();
        String nombreVista = "registro";
        if (email.isEmpty()) {
            modelo.put("mensaje", "El email no puede estar vacio");
        } else if (!validarLargoDelEmail(email)) {
            modelo.put("mensaje", "El email debe tener como minimo 11 caracteres");

        } else if (!validarQueElEmailContengaArroba(email)) {
            modelo.put("mensaje", "El email debe contener el caracter: @");

        } else if (validarQueTieneMayuscula(email)) {
            modelo.put("mensaje", "El email no puede contener mayusculas");
        } else {
            modelo.put("mensaje", "El registro fue exitoso");
            nombreVista = "login";
        }
        return new ModelAndView(nombreVista, modelo);
    }

    private static Boolean validarLargoDelEmail(String email) {

        return email.length() >= 11;
    }

    private static Boolean validarQueElEmailContengaArroba(String email) {
        return email.contains("@");
    }

    private static Boolean validarQueTieneMayuscula(String email) {

        boolean contieneMayusculas = false;

        for (int i = 0; i < email.length(); i++) {
            char caracter = email.charAt(i);
            if (Character.isUpperCase(caracter)) {
                contieneMayusculas = true;
                break; // Si se encuentra una mayúscula, se sale del bucle
            }
        }

        return contieneMayusculas;
    }

    @RequestMapping(path = "/registrarse", method = RequestMethod.GET)
    public ModelAndView irARegistrarse() {
        ModelMap modelo = new ModelMap();
        modelo.put("datosRegistro", new DatosRegistro());
        return new ModelAndView("registrarse", modelo);
    }

    @RequestMapping(path = "/registrarse", method = RequestMethod.POST)
    public ModelAndView validarRegistro(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {
        Usuario usuario = servicioUsuario.buscarUsuarioPorEmail(datosRegistro.getEmail());
        ModelMap modelo = new ModelMap();

        if(usuario != null){
            modelo.put("error", "El email ya se encuentra registrado");
            return new ModelAndView("registrarse", modelo);
        }

        if(!datosRegistro.getPassword().equals(datosRegistro.getPasswordRepetida())){
            modelo.put("error", "Las contraseñas no coinciden");
            return new ModelAndView("registrarse", modelo);
        }

        servicioUsuario.registrarUsuario(datosRegistro);
        modelo.put("registroExitoso", "Registro Exitoso");
        return new ModelAndView("home", modelo);
    }

}