package com.mmj.beprepared.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class StandardErrorResponse {

    private int code; /*Codigo de Erro*/
    private HttpStatus status;  /**/
    private OffsetDateTime timestamp; /*Dia e hora em que o erro aconteceu*/
    private String title; /*Titulo do erro*/
    private String path; /*Mostra o caminho de onde o erro aconteceu o "url"*/
    private List<ValidationError> fields;

    @Data
    @AllArgsConstructor
    public static class ValidationError{

        private String field; /*Qual e o campo em que o erro esta a acontecer*/
        private String message; /*Mensagem de erro*/
    }





}
