package com.lucas.org.gerenciador_de_tarefas.Repository;

import com.lucas.org.gerenciador_de_tarefas.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
