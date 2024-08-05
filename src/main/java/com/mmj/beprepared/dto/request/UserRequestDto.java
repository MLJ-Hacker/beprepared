package com.mmj.beprepared.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank
    @Size(min = 3, max = 50) /*Da um tamanho minimo e um tamanho maximo aceitavel*/
    private String name;
    @NotBlank
    @Email /*Verifica se e um email(Procura pelo "@")*/
    private String email;
    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

}
