package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;

import com.tallerwebi.dominio.ServicioUsuario;

import com.tallerwebi.dominio.entities.Usuario;

import com.tallerwebi.dominio.excepcion.UsuarioExistente;

import com.tallerwebi.presentacion.models.DatosLogin;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

import static org.mockito.Mockito.*;

public class ControladorLoginTest {

	private ControladorLogin controladorLogin;

	private Usuario usuarioMock;

	private DatosLogin datosLoginMock;

	private HttpServletRequest requestMock;

	private HttpSession sessionMock;

	private ServicioLogin servicioLoginMock;

	private ServicioUsuario servicioUsuarioMock;

	@BeforeEach

	public void init(){

		datosLoginMock = new DatosLogin("dami@unlam.com", "123");

		usuarioMock = mock(Usuario.class);
		when(usuarioMock.getEmail()).thenReturn("dami@unlam.com");

		requestMock = mock(HttpServletRequest.class);

		sessionMock = mock(HttpSession.class);

		servicioLoginMock = mock(ServicioLogin.class);

		servicioUsuarioMock = mock(ServicioUsuario.class);

		controladorLogin = new ControladorLogin(servicioLoginMock, servicioUsuarioMock);

	}
	@Test

	public void loginConUsuarioYPasswordInorrectosDeberiaLlevarALoginNuevamente(){

// preparacion
		when(servicioLoginMock.consultarUsuario(anyString(), anyString())).thenReturn(null);

// ejecucion

		ModelAndView modelAndView = controladorLogin.validarLogin(datosLoginMock, requestMock);

// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("login"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Usuario o clave incorrecta"));
		verify(sessionMock, times(0)).setAttribute("ROL", "ADMIN");

	}
	@Test

	public void loginConUsuarioYPasswordCorrectosDeberiaLLevarAHome(){

// preparacion

		Usuario usuarioEncontradoMock = mock(Usuario.class);
		when(usuarioEncontradoMock.getRol()).thenReturn("ADMIN");
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(servicioLoginMock.consultarUsuario(anyString(), anyString())).thenReturn(usuarioEncontradoMock);

// ejecucion

		ModelAndView modelAndView = controladorLogin.validarLogin(datosLoginMock, requestMock);

// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/inicio"));
		verify(sessionMock, times(1)).setAttribute("ROL", usuarioEncontradoMock.getRol());

	}
	@Test

	public void loginConUsuarioYPasswordIncorrectosDeberiaLLevarALoginConError(){

// preparacion
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(servicioLoginMock.consultarUsuario(anyString(), anyString())).thenReturn(null);

// ejecucion

		ModelAndView modelAndView = controladorLogin.validarLogin(datosLoginMock, requestMock);

// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("login"));

	}
	@Test

	public void registrameSiUsuarioNoExisteDeberiaCrearUsuarioYVolverAlLogin() throws UsuarioExistente {

// ejecucion

		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
		verify(servicioLoginMock, times(1)).registrar(usuarioMock);

	}
	@Test

	public void registrarmeSiUsuarioExisteDeberiaVolverAFormularioYMostrarError() throws UsuarioExistente {

// preparacion
		doThrow(UsuarioExistente.class).when(servicioLoginMock).registrar(usuarioMock);

// ejecucion

		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("registrarse"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("El usuario ya existe"));

	}
	@Test

	public void errorEnRegistrarmeDeberiaVolverAFormularioYMostrarError() throws UsuarioExistente {

// preparacion
		doThrow(RuntimeException.class).when(servicioLoginMock).registrar(usuarioMock);

// ejecucion

		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("registrarse"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Error al registrar el nuevo usuario"));

	}

}
