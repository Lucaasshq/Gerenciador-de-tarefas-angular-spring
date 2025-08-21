package com.lucas.org.gerenciador_de_tarefas.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDTO {
    private HttpStatus statusCode;
    private String message;
    private LocalDateTime timeStamp;

    public ErrorDTO(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
