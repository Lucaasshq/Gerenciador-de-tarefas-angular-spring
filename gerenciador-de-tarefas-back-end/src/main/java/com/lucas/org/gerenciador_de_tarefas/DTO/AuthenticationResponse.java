package com.lucas.org.gerenciador_de_tarefas.DTO;

import com.lucas.org.gerenciador_de_tarefas.enums.Roles;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private Long userId;

    private Roles role;
}
