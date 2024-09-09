package com.nttdata.appbackend;

import com.nttdata.appbackend.dto.ClientDTO;
import com.nttdata.appbackend.exception.ModeloNotFoundException;
import com.nttdata.appbackend.model.Client;
import com.nttdata.appbackend.repository.IClientRepo;
import com.nttdata.appbackend.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClientServiceTestsUni {

	@Mock
	private IClientRepo clientRepo;

	@Mock
	private ModelMapper mapper;

	@InjectMocks
	private ClientServiceImpl clientService;

	@Test
	void contextLoads() {
	}

	@Test
	void testListSuccess() throws Exception {
		Client client = new Client();
		when(clientRepo.findAll()).thenReturn(List.of(client));

		List<Client> clients = clientService.list();

		assertNotNull(clients);
		assertFalse(clients.isEmpty());
		verify(clientRepo).findAll();
	}

	@Test
	void testListException() throws Exception {
		when(clientRepo.findAll()).thenThrow(new RuntimeException("Database error"));

		Exception exception = assertThrows(ModeloNotFoundException.class, () -> {
			clientService.list();
		});

		assertTrue(exception.getMessage().contains("Ocurrió un error en el proceso list"));
	}

	@Test
	void testListByIdSuccess() throws Exception {
		ClientDTO clientDTO = new ClientDTO();
		Client client = new Client();
		when(clientRepo.findById(anyInt())).thenReturn(Optional.of(client));
		when(mapper.map(client, ClientDTO.class)).thenReturn(clientDTO);

		ClientDTO result = clientService.listById(1);

		assertNotNull(result);
		verify(clientRepo).findById(anyInt());
	}

	@Test
	void testListByIdException() throws Exception {
		when(clientRepo.findById(anyInt())).thenThrow(new RuntimeException("Database error"));

		Exception exception = assertThrows(ModeloNotFoundException.class, () -> {
			clientService.listById(1);
		});

		assertTrue(exception.getMessage().contains("Ocurrió un error en el proceso listById"));
	}

}
