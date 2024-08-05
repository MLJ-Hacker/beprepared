package com.mmj.beprepared.dto.request;

import com.mmj.beprepared.model.enums.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlertRequestDto {

    /*@NotEmpty Excessao:Nao pode estar vazio
    @NotNull /*Excessao: o titulo nao pode estar vazio, verifica se o atributo e nulo ou nao*/

    @NotBlank(message = "Preencha o título") /*E a juncao de ambas a cima, usamos em String*/
    private String title;
    @NotBlank
    private String message;
    @NotNull
    private Severity severity;
    @NotNull(message = "Coloque a provincia")
    private Long provinceId;
    @NotNull(message = "Coloque o distrito")
    private Long cityId;

}
