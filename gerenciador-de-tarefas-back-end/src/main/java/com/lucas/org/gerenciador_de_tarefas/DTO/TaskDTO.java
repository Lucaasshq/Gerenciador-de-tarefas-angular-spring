package com.lucas.org.gerenciador_de_tarefas.DTO;

import com.lucas.org.gerenciador_de_tarefas.enums.TaskStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private Date dueDate;

    private String priority;

    private TaskStatus taskStatus;

    private Long funcionarioId;

    private String funcionarioName;
}
