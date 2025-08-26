package com.lucas.org.gerenciador_de_tarefas.service.admin;


import com.lucas.org.gerenciador_de_tarefas.DTO.UserDto;
import com.lucas.org.gerenciador_de_tarefas.Repository.UserRepository;

import com.lucas.org.gerenciador_de_tarefas.enums.Roles;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


   private final UserRepository userRepository;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .filter(users -> users.getRoles() == Roles.FUNCIONARIO)
                .map(users -> users.getUserDto())
                .toList();
    }
}
