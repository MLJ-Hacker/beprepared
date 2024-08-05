package com.mmj.beprepared.exception.handler;


import com.mmj.beprepared.exception.BadRequestException;
import com.mmj.beprepared.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class APiExceptionHandler  extends ResponseEntityExceptionHandler {

    private  final MessageSource messageSource; /*esta dependecia pega as mensagens*/


    @Override /*Esse metodo lhe da com validacoes*/
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST; /*Pega os Status que  nos queremos, o erro do cliente*/
        List<StandardErrorResponse.ValidationError> validationErrors = new ArrayList<>(); /* */

        for (ObjectError objectError: ex.getBindingResult().getAllErrors()) { /*Vai mapiar(pegar) todos o erros que vamos ter, atravex do paramentro "ex"*/
            String name = ((FieldError) objectError).getField(); /*tras o campo ou atributo em que o erro aconteceu*/
            String massage = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());/*Vai pegar a mensagem de erro*/
            validationErrors.add(new StandardErrorResponse.ValidationError(name, massage)); /*ira adicionar um nome no metodo "validactionError"*/
        }

        StandardErrorResponse response = new StandardErrorResponse();
        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimestamp(OffsetDateTime.now());
        response.setTitle("ERRO DE VALIDACÃO! Um ou mais campos estão invalidos") ;
        response.setPath(request.getContextPath());
        response.setFields(validationErrors);
        return super.handleExceptionInternal(ex, response, headers,httpStatus, request);
    }

    @ExceptionHandler(BadRequestException.class)/*Mostra a class que lhe da com o tratamento de excessoes*/
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setCode(status.value());
        response.setStatus(status);
        response.setTimestamp(OffsetDateTime.now());
        response.setTitle(ex.getMessage());
        response.setPath(request.getContextPath());

        return ResponseEntity.badRequest()
                .body(response);

    }


    @ExceptionHandler(EntityNotFoundException.class)/*Uma excessao que vai usar caso algo nao seja encontrado*/
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setCode(status.value());
        response.setStatus(status);
        response.setTimestamp(OffsetDateTime.now());
        response.setTitle(ex.getMessage());
        response.setPath(request.getContextPath());

        return new ResponseEntity<>(response, status);

    }



}
