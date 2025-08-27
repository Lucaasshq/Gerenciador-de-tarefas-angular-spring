package com.lucas.org.gerenciador_de_tarefas.service;

import com.lucas.org.gerenciador_de_tarefas.Exception.UserNaoEncontradoException;
import com.lucas.org.gerenciador_de_tarefas.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserNaoEncontradoException("Funcionario de email "+email+" não encontrado."));
    }
}
