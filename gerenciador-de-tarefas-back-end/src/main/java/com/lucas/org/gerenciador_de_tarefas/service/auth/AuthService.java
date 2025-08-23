package com.lucas.org.gerenciador_de_tarefas.service.auth;

import com.lucas.org.gerenciador_de_tarefas.DTO.SignupRequest;
import com.lucas.org.gerenciador_de_tarefas.DTO.UserDto;

public interface AuthService {

   void createAnAdminAccount();

   UserDto signupUser(SignupRequest signupRequest);

   boolean hasUserWithEmail(String email);
}
