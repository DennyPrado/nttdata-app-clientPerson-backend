package com.nttdata.appbackend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Integer personId;

    @NotEmpty(message = "Estimado usuario, el nombre no puede estar vacío")
    @Size(max = 150, message = "Estimado usuario, la longitud máxima del nombre es: 150 caracteres")
    private String name;

    @NotEmpty(message = "Estimado usuario, el genero no puede estar vacío")
    @Size(max = 10, message = "Estimado usuario, la longitud máxima del genero es: 10 caracteres")
    private String gender;

    @NotNull(message = "Estimado usuario, debe ingresar la edad.")
    private Integer age;

    @NotEmpty(message = "Estimado usuario, debe ingresar la identificación.")
    @Size(max = 10, message = "Estimado usuario, la longitud máxima de la identificación es: 10 digitos")
    private String dni;

    @Size(max = 150, message = "Estimado usuario, la longitud máxima de la dirección es: 150 caracteres")
    private String address;

    @Size(max = 10, message = "Estimado usuario, la longitud máxima del teléfono es: 10 digitos")
    private String phone;

    private String createdByUser;

    private Date createdDate;

    private String lastModifiedByUser;

    private Date lastModifiedDate;
}
