package com.lucas.org.gerenciador_de_tarefas.Repository;

import com.lucas.org.gerenciador_de_tarefas.Entity.Users;
import com.lucas.org.gerenciador_de_tarefas.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
   Optional<Users> findByEmail(String email);


    Optional<Users> findByRoles(Roles roles);

    Optional<Users> findFirstByEmail(String email);
}
