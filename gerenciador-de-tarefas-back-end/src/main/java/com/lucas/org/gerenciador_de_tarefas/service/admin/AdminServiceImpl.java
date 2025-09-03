package com.lucas.org.gerenciador_de_tarefas.service.admin;


import com.lucas.org.gerenciador_de_tarefas.DTO.TaskDTO;
import com.lucas.org.gerenciador_de_tarefas.DTO.UserDto;
import com.lucas.org.gerenciador_de_tarefas.Entity.Task;
import com.lucas.org.gerenciador_de_tarefas.Entity.Users;
import com.lucas.org.gerenciador_de_tarefas.Repository.TaskRepository;
import com.lucas.org.gerenciador_de_tarefas.Repository.UserRepository;

import com.lucas.org.gerenciador_de_tarefas.enums.Roles;

import com.lucas.org.gerenciador_de_tarefas.enums.TaskStatus;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


   private final UserRepository userRepository;

   private final TaskRepository taskRepository;


    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .filter(users -> users.getRoles() == Roles.FUNCIONARIO)
                .map(users -> users.getUserDto())
                .toList();
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Optional<Users> optionalUsers = userRepository.findById(taskDTO.getFuncionarioId());
        if (optionalUsers.isPresent()){
            Task task = new Task();

            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setPriority(taskDTO.getPriority());
            task.setDueDate(taskDTO.getDueDate());
            task.setTaskStatus(TaskStatus.INPROGRESS);
            task.setUser(optionalUsers.get());
           return taskRepository.save(task).getTaskDTO();
        }
        return null;
    }

}
