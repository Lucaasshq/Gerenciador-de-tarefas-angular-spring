package com.lucas.org.gerenciador_de_tarefas.DTO;

import lombok.Data;

@Data
public class SignupRequest {

    private String username;

    private String email;

    private String password;

}
