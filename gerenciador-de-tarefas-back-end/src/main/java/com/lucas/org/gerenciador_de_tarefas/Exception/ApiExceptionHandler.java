package com.lucas.org.gerenciador_de_tarefas.Exception;

import com.lucas.org.gerenciador_de_tarefas.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNaoEncontradoException.class)
    public ResponseEntity<ErrorDTO> handlerUserNaoEncontradaException(UserNaoEncontradoException ex){
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND, ex.getMessage());

        return ResponseEntity.status(errorDTO.getStatusCode().value()).body(errorDTO);
    }
}
