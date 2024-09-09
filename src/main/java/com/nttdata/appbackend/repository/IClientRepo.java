package com.nttdata.appbackend.repository;

import com.nttdata.appbackend.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepo extends IGenericRepo<Client, Integer> {
}
