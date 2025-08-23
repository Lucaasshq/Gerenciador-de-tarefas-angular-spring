package com.lucas.org.gerenciador_de_tarefas.service.auth;

import com.lucas.org.gerenciador_de_tarefas.DTO.SignupRequest;
import com.lucas.org.gerenciador_de_tarefas.DTO.UserDto;
import com.lucas.org.gerenciador_de_tarefas.Entity.Users;
import com.lucas.org.gerenciador_de_tarefas.Repository.UserRepository;
import com.lucas.org.gerenciador_de_tarefas.enums.Roles;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceimpl implements AuthService {

    private final UserRepository userRepository;

    // Cria uma conta como administrador caso ainda não exista
    @PostConstruct
    public void createAnAdminAccount(){
        Optional<Users> optionalUseruser = userRepository.findByRoles(Roles.ADMIN);
        if (optionalUseruser.isEmpty()){
            Users usersAdmin = new Users();
            usersAdmin.setEmail("admin@test.com");
            usersAdmin.setUsername("admin");
            usersAdmin.setRoles(Roles.ADMIN);
            usersAdmin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(usersAdmin);
            System.out.println("Conta de administrador criada");
        } else {
            System.out.println("Já existe uma conta de administrador.");
        }
    }

    // Cria usuario
    @Override
    public UserDto signupUser(SignupRequest signupRequest) {
        Users user = new Users();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRoles(Roles.FUNCIONARIO);
       Users userCreated = userRepository.save(user);
       return userCreated.getUserDto();
    }

    // Verifica se já tem alguem com esse email
    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
