package com.lucas.org.gerenciador_de_tarefas.enums;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    ADMIN("ADMIN"), FUNCIONARIO("FUNCIONARIO");

    private String authority;

    Role(String authority){ this.authority = authority; }

    @Override
    public String getAuthority() {
        return "";
    }
}
