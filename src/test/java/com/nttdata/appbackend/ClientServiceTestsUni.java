package com.nttdata.appbackend;

import com.nttdata.appbackend.dto.ClientDTO;
import com.nttdata.appbackend.exception.ModeloNotFoundException;
import com.nttdata.appbackend.model.Client;
import com.nttdata.appbackend.repository.IClientRepo;
import com.nttdata.appbackend.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTestsUni {

	@Mock
	private IClientRepo clientRepo;

	@Mock
	private ModelMapper mapper;

	@InjectMocks
	private ClientServiceImpl clientService;

	private ClientDTO clientDTO;
	private Client client;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		clientDTO = new ClientDTO();
		clientDTO.setPersonId(1);
		clientDTO.setCreatedByUser("TestUser");
		clientDTO.setCreatedDate(new Date());

		client = new Client();
		client.setPersonId(1);
	}

	@Test
	void testListSuccess() throws Exception {
		List<Client> clients = new ArrayList<>();
		clients.add(client);

		when(clientRepo.findAll()).thenReturn(clients);

		List<Client> result = clientService.list();
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	void testListThrowsException() {
		when(clientRepo.findAll()).thenThrow(new RuntimeException("Database error"));

		Exception exception = assertThrows(ModeloNotFoundException.class, () -> {
			clientService.list();
		});

		assertTrue(exception.getMessage().contains("Ocurrió un error en el proceso list"));
	}

	@Test
	void testListByIdSuccess() throws Exception {
		when(clientRepo.findById(anyInt())).thenReturn(Optional.of(client));
		when(mapper.map(any(Client.class), any(Class.class))).thenReturn(clientDTO);

		ClientDTO result = clientService.listById(1);
		assertNotNull(result);
		assertEquals(clientDTO.getPersonId(), result.getPersonId());
	}

	@Test
	void testListByIdNotFound() throws Exception {
		when(clientRepo.findById(anyInt())).thenReturn(Optional.empty());

		ClientDTO result = clientService.listById(1);
		assertNull(result);
	}

	@Test
	void testListByIdThrowsException() {
		when(clientRepo.findById(anyInt())).thenThrow(new RuntimeException("Database error"));

		Exception exception = assertThrows(ModeloNotFoundException.class, () -> {
			clientService.listById(1);
		});

		assertTrue(exception.getMessage().contains("Ocurrió un error en el proceso listById"));
	}

}
