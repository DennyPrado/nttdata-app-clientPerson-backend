package com.nttdata.appbackend.service;

import com.nttdata.appbackend.dto.ClientDTO;
import com.nttdata.appbackend.model.Client;

import java.util.List;

public interface IClientService {
    Client register(ClientDTO clientDTO) throws Exception;
    Client modify(ClientDTO clientDTO) throws Exception;
    List<Client> list() throws Exception;
    ClientDTO listById(Integer idPerson) throws Exception;
    void eliminate(Integer idPerson) throws Exception;

}
