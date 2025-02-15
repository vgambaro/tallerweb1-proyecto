package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.entities.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import com.tallerwebi.presentacion.models.DatosLogin;
import com.tallerwebi.presentacion.models.PreguntaRespuestaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorLogin {

    private ServicioLogin servicioLogin;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin, ServicioUsuario servicioUsuario) {
        this.servicioLogin = servicioLogin;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping("/login")
    public ModelAndView irALogin() {

        ModelMap modelo = new ModelMap();
        // el modelo es un mapa
        // el valor es un objeto vacio
        modelo.put("datosLogin", new DatosLogin());
        // esta linea devuelve una vista con los datos. viewname es el nombre de la vistsa q quiero retornar
        // modelo: set de datos que puede ser representado con una clase linkeado con 
        //la vista. Sirve para guardar los datos deseados.
        // Los datos que ingresan por la vista se guardan en el modelo para analizar, validar, etc.
        // El modelo es una instancia de una clase con los datos deseados.
        // Cuando el usuario guarda datos se van guardando en DatosLogin
        return new ModelAndView("login", modelo);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
        if (usuarioBuscado != null) {
            request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
            request.getSession().setAttribute("EMAIL", usuarioBuscado.getEmail());
            return new ModelAndView("redirect:/inicio");
        } else {
            model.put("error", "Usuario o clave incorrecta");
        }
        return new ModelAndView("login", model);
    }

    @RequestMapping(path = "/registrarme", method = RequestMethod.POST)
    public ModelAndView registrarme(@ModelAttribute("usuario") Usuario usuario) {
        ModelMap model = new ModelMap();
        try {
            servicioLogin.registrar(usuario);
        } catch (UsuarioExistente e) {
            model.put("error", "El usuario ya existe");
            return new ModelAndView("registrarse", model);
        } catch (Exception e) {
            model.put("error", "Error al registrar el nuevo usuario");
            return new ModelAndView("registrarse", model);
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(path = "/nuevo-usuario", method = RequestMethod.GET)
    public ModelAndView nuevoUsuario() {
        ModelMap model = new ModelMap();
        model.put("usuario", new Usuario());
        return new ModelAndView("registrarse", model);
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome() {
        return new ModelAndView("home");

    }

    // acá es donde empieza la app: localhost:8080/spring. Esta es la raiz, no se especifica una pag en particular para navegar.
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/home");
    }
    // "/" es la raiz

    @RequestMapping(path = "/cerrarSesion", method = RequestMethod.GET)
    public ModelAndView cerrarSesion(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return new ModelAndView("redirect:/home");
    }

}

