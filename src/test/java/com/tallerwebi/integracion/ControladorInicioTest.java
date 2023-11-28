//package com.tallerwebi.integracion;
//
//import com.tallerwebi.integracion.config.HibernateTestConfig;
//import com.tallerwebi.integracion.config.SpringWebTestConfig;
//import com.tallerwebi.dominio.ServicioInicio;
//import com.tallerwebi.dominio.models.Pregunta;
//import com.tallerwebi.dominio.models.Respuesta;
//import com.tallerwebi.infraestructura.RepositorioPreguntaImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Objects;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//
//import java.util.List;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//import java.util.ArrayList;
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = { SpringWebTestConfig.class, HibernateTestConfig.class })
//public class ControladorInicioTest{
//
//    @Autowired
//    private WebApplicationContext wac;
//    private MockMvc mockMvc;
////RECORAR QUE MOK ES UN OBJ FALSO QUE "REPLICA" LOS METODOS Y SE PARECE A MI SERVICIO INICO
//    @Autowired
//    private ServicioInicio servicioInicio= mock(ServicioInicio.class);
//
//    @BeforeEach
//    public void init() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
//    @Test
//    public void debeRetornarLaPaginaCargarPregunta() throws Exception {
//    	
//    	//GIVEN--PREPARO EL ENTORNO CON EL MOCKITO
////WHEN--EJECUTO SOLICITUD HTTP (PERFORM)
//        MvcResult result = this.mockMvc.perform(get("/cargarPregunta"))
//                .andExpect(status().isOk())
//                .andReturn();
//
// 
//        String viewName = result.getModelAndView().getViewName();
//        ModelMap modelMap = result.getModelAndView().getModelMap();
//
//    //THEN--VERIFICO SI LA VISTA ES CARGAR-PREGUNTAS Y Y SI CONTIENE PREGUNTAS Y RESPUESTAS
//        if (!"cargar-pregunta".equals(viewName)) {
//            throw new AssertionError("La vista no es 'cargar-pregunta'");
//        }
//
//        // Verificar que el modelo contenga los atributos "pregunta" y "respuestas"
//        if (!modelMap.containsAttribute("pregunta") || !modelMap.containsAttribute("respuestas")) {
//            throw new AssertionError("El modelo no contiene los atributos 'pregunta' y 'respuestas'");
//        }
//    }
//
//
//
//    @Test
//    public void debeGuardarPregunta() throws Exception {
//        Pregunta pregunta = new Pregunta();
//        List<Respuesta> respuestas = new ArrayList<>(); // Inicializa la lista vacía
//
//        // MOCK SERV INICIO
//        servicioInicio.guardarPreguntaConRespuestas(pregunta, respuestas);
//        
//        MvcResult result = this.mockMvc.perform(post("/guardarPregunta")
//                .param("pregunta.descripcion", "Descripción de la pregunta")
//                .param("pregunta.nivel", "NIVEL_EJEMPLO")
//                .param("pregunta.fase", "1")
//                .param("respuestas[0].descripcion", "Respuesta 1")
//                .param("respuestas[0].esCorrecta", "true")
//                .param("respuestas[1].descripcion", "Respuesta 2")
//                .param("respuestas[1].esCorrecta", "false")
//                // Configura los parámetros de las otras respuestas
//            ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/cargarPregunta")).andReturn();
//
//        // VEO SI EFECTIVAMENTE LLAMO AL SERVICIO
//        verify(servicioInicio, times(1)).guardarPreguntaConRespuestas(eq(pregunta), eq(respuestas));
//    }
//
//}
