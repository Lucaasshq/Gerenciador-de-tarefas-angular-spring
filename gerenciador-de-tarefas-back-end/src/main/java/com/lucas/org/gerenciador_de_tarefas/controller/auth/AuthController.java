package com.lucas.org.gerenciador_de_tarefas.controller.auth;

import com.lucas.org.gerenciador_de_tarefas.DTO.LoginResponse;
import com.lucas.org.gerenciador_de_tarefas.DTO.LoginRequest;
import com.lucas.org.gerenciador_de_tarefas.DTO.SignupRequest;
import com.lucas.org.gerenciador_de_tarefas.DTO.UserDto;
import com.lucas.org.gerenciador_de_tarefas.Entity.Users;
import com.lucas.org.gerenciador_de_tarefas.JWT.JwtUtils;
import com.lucas.org.gerenciador_de_tarefas.Repository.UserRepository;
import com.lucas.org.gerenciador_de_tarefas.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {


    private final AuthService authService;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        if (authService.hasUserWithEmail(signupRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Já existe um usuario com esse email cadastrado.");
        }
        UserDto createdUserDto = authService.signupUser(signupRequest);
        if (createdUserDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não criado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()));
        }catch (BadCredentialsException ex){
            throw  new BadCredentialsException("email ou senha incorreto");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        Optional<Users> user = userRepository.findFirstByEmail(loginRequest.getEmail());
        final String jwtToken = jwtUtils.gerarToken(userDetails, jwtUtils.EXPIRATION_TOKEN(Date.from(Instant.now())), loginRequest.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        if (user.isPresent()){
            loginResponse.setJwt(jwtToken);
            loginResponse.setUserId(user.get().getId());
            loginResponse.setRole(user.get().getRoles());
        }
        return loginResponse;
    }
}
