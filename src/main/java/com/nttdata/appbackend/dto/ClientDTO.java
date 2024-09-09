package com.nttdata.appbackend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO extends PersonDTO {

    @NotEmpty(message = "Estimado usuario, el id del cliente no puede estar vacío.")
    private String clientId;

    @NotEmpty(message = "Estimado usuario, la contraseña no puede estar vacia.")
    @Size(max = 15, message = "Estimado usuario, la longitud máxima de la contraseña es: 15 caracteres")
    private String password;

    private Boolean state;

}
