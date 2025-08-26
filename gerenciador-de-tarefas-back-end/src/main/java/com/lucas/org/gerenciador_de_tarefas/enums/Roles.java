package com.lucas.org.gerenciador_de_tarefas.enums;

import org.springframework.security.core.GrantedAuthority;


public enum Roles implements GrantedAuthority {
    ADMIN("ADMIN"), FUNCIONARIO("FUNCIONARIO");

    private final String authority;

    Roles(String authority){ this.authority = authority; }

    @Override
    public String getAuthority() {
        return authority;
    }
}
