package com.lucas.org.gerenciador_de_tarefas.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;

    private String password;
}
