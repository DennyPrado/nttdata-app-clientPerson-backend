package com.nttdata.appbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "client")
public class Client extends Person {

    @Column(nullable = false, unique=true)
    private String clientId;

    @Column(nullable = false, length = 15)
    private String password;

    @Column(nullable = false, length = 1)
    private Boolean state;

}
