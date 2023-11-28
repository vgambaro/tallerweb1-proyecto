package com.tallerwebi.integracion;

import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import com.tallerwebi.presentacion.ControladorLogin;
import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.entities.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebTestConfig.class, HibernateTestConfig.class })
public class ControladorLoginTest {

	@Autowired
	private ControladorLogin controladorLogin;
	private Usuario usuarioMock;
	private ServicioLogin servicioLogin = mock(ServicioLogin.class);
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		usuarioMock = mock(Usuario.class);
		when(usuarioMock.getEmail()).thenReturn("dami@unlam.com");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		servicioLogin = mock(ServicioLogin.class);
        controladorLogin = new ControladorLogin(servicioLogin);
	}

	@Test
	public void debeRetornarLaPaginaDeLogin() {
		ModelAndView modelAndView = controladorLogin.irALogin();
		assertEquals("login", modelAndView.getViewName());
	}

	@Test
	public void debeRetornarLaPaginaHomeCuandoSeNavegaALaRaiz() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/")).andExpect(status().is3xxRedirection()).andReturn();

		ModelAndView modelAndView = result.getModelAndView();
		assertNotNull(modelAndView);
		assertThat("redirect:/home", equalToIgnoringCase(Objects.requireNonNull(modelAndView.getViewName())));
		assertThat(true, is(modelAndView.getModel().isEmpty()));
	}

	@Test
	public void debeRetornarLaPaginaLoginCuandoSeNavegaALLogin() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/login")).andExpect(status().isOk()).andReturn();

		ModelAndView modelAndView = result.getModelAndView();
		assertNotNull(modelAndView);
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("login"));
		assertThat(modelAndView.getModel().get("datosLogin").toString(),
				containsString("com.tallerwebi.presentacion.DatosLogin"));
	}

	@Test
	public void testElRegistroEsCorrecto() throws UsuarioExistente {
		// GIVEN-USER
		Usuario usuario = new Usuario();
		// WHEN--REGISTRO EXITOSO
		doNothing().when(servicioLogin).registrar(usuario);
		ModelAndView modelAndView = controladorLogin.registrarme(usuario);

		// then--ME REDIRIGE AL LOGIN
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
		assertThat(modelAndView.getModel().size(), is(0));
	}

	@Test
	public void testRegistrarmeUsuarioExistente() throws UsuarioExistente {
		// GIVEN--USER
		Usuario usuario = new Usuario();
		// SIMULA EL REGISTRO EXITOSO
		doThrow(UsuarioExistente.class).when(servicioLogin).registrar(usuario);
		ModelAndView modelAndView = controladorLogin.registrarme(usuario);
//THEN
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("registrarse"));

		assertThat(modelAndView.getModel().isEmpty(), is(false));
	}

	/*
	 * assertThat(modelAndView.getModel().size(), is(1)); VERIFICO QUE EL MODELO
	 * TENG UN SOLO ELEMENTO NO HAY OTROS ELEMNTOS EN EL MODELO
	 */
	@Test
	public void testRegistrarmeErrorAlRegistrar() throws UsuarioExistente {
	    // GIVEN -- CREO UN USUARIO Y CONFIGURO EL SERVICIO PARA LANZAR OTRA EXCEPCIÓN
	    Usuario usuario = new Usuario();

	    //SE LAZÓ OTRO ERROR --RuntimeException
	    doThrow(new RuntimeException("Error al registrar el nuevo usuario")).when(servicioLogin).registrar(usuario);

	    // WHEN -- INTENTO REGISTRARME
	    ModelAndView modelAndView = controladorLogin.registrarme(usuario);
	    assertNotNull(modelAndView);
	    assertNotNull(modelAndView.getModel());

	    // SI EL ERROR NO ES NULO ENTONCES EFECTIVAMENTE SE DISPARÓ
	    assertNotNull(modelAndView.getModel().get("error"));

	    // THEN -- DEBE REDIRIGIR A LA VISTA "registrarse" CON UN ATRIBUTO "error"
	    assertThat(modelAndView.getViewName(), equalToIgnoringCase("registrarse"));
	}
}

