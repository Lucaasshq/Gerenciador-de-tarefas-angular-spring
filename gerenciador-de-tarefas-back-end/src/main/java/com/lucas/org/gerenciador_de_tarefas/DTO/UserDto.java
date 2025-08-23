package com.lucas.org.gerenciador_de_tarefas.DTO;

import com.lucas.org.gerenciador_de_tarefas.enums.Roles;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Roles roles;
}
