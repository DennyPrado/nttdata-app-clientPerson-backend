package com.nttdata.appbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.appbackend.dto.BaseResponseVo;
import com.nttdata.appbackend.dto.ClientDTO;
import com.nttdata.appbackend.model.Client;
import com.nttdata.appbackend.service.IClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private IClientService clientService;

	String token = "";

	@BeforeEach
	void setUp() throws Exception {
		// Setup inicial si es necesario
		token = obtainAuthToken();
	}

	private String obtainAuthToken() throws Exception {
		// Simular la obtención de un token JWT
		// Reemplaza con la implementación específica de tu aplicación
		String username = "admin@gmail.com";
		String password = "123";

		String response = mockMvc.perform(post("/login")  // Endpoint para obtener el token
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.access_token").exists())
				.andReturn().getResponse().getContentAsString();

		// Extraer el token del cuerpo de la respuesta
		return objectMapper.readTree(response).path("access_token").asText();
	}

	@Test
	public void testFindAllClients() throws Exception {
		mockMvc.perform(get("/api/clientes")
						.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(header().string("restyp", "SUCCESS"))
				.andExpect(jsonPath("$.message").value("Se realizó la consulta exitosamente."));
	}

	@Test
	public void testFindClientById() throws Exception {
		mockMvc.perform(get("/api/clientes/1")  // Asegúrate de usar un ID válido
						.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(header().string("restyp", "SUCCESS"))
				.andExpect(jsonPath("$.message").value("Se realizó la consulta exitosamente."));
	}
}
