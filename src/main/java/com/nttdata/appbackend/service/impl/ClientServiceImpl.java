package com.nttdata.appbackend.service.impl;

import com.nttdata.appbackend.dto.ClientDTO;
import com.nttdata.appbackend.exception.ModeloNotFoundException;
import com.nttdata.appbackend.model.Client;
import com.nttdata.appbackend.repository.IClientRepo;
import com.nttdata.appbackend.repository.IGenericRepo;
import com.nttdata.appbackend.service.IClientService;
import com.nttdata.appbackend.util.ResponseType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    //@Autowired
    private final IClientRepo clientRepo;

    private final ModelMapper mapper;

    private String userGeneric = "Dprado";

    @Override
    public Client register(ClientDTO clientDTO) throws Exception {
        try{
            clientDTO.setCreatedByUser(userGeneric);
            clientDTO.setCreatedDate(new Date());

            Client clt = mapper.map(clientDTO, Client.class);

            return clientRepo.save(clt);
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso register: {} " + e.getMessage());
        }

    }

    @Override
    public Client modify(ClientDTO clientDTO) throws Exception {
        try{
            Client resp = new Client();
            ClientDTO obj = this.listById(clientDTO.getPersonId());

            if(obj == null) {
                throw new ModeloNotFoundException("El ID del cliente no existe");
            }else{
                clientDTO.setCreatedByUser(obj.getCreatedByUser());
                clientDTO.setCreatedDate(obj.getCreatedDate());
                clientDTO.setLastModifiedByUser(userGeneric);
                clientDTO.setLastModifiedDate(new Date());

                Client clt    = mapper.map(clientDTO, Client.class);
                resp          = clientRepo.save(clt);
                clientDTO.setPersonId(clt.getPersonId());
            }
            return resp;
        }catch (Exception e){
            throw new ModeloNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Client> list() throws Exception {
        try{
            return clientRepo.findAll();
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso list: {} " + e.getMessage());
        }
    }

    @Override
    public ClientDTO listById(Integer idPerson) throws Exception {
        ClientDTO clientDTO = null;
        try{
            Optional<Client> client = clientRepo.findById(idPerson);
            if (client.isPresent()) {
                clientDTO = mapper.map(client.get(), ClientDTO.class);
            }
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso listById: {} " + e.getMessage());
        }
        return clientDTO;
    }

    @Override
    public void eliminate(Integer idPerson) throws Exception {
        try{
            ClientDTO obj = this.listById(idPerson);
            if(obj == null) {
                throw new ModeloNotFoundException("El ID a eliminar no existe");
            }else{
                clientRepo.deleteById(idPerson);
            }
        }catch (Exception e){
            throw new ModeloNotFoundException(e.getMessage());
        }
    }
}
