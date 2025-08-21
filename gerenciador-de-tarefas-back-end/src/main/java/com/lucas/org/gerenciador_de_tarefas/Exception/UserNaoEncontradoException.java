package com.lucas.org.gerenciador_de_tarefas.Exception;

public class UserNaoEncontradoException extends RuntimeException {
    public UserNaoEncontradoException(String message) {
        super(message);
    }
}
