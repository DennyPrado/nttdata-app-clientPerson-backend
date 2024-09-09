package com.nttdata.appbackend.repository;


import com.nttdata.appbackend.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer>{

    User findOneByUsername(String username);
}
