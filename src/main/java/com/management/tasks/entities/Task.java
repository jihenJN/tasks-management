package com.management.tasks.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.tasks.dto.TaskDTO;
import com.management.tasks.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private Long id;

    private String title;

    private String description;

    private Date dueDate;

    private String priority;

    private TaskStatus taskStatus;

    @ManyToOne (fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="user_id",nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public TaskDTO getTaskDTO(){
        TaskDTO taskDTO=new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        taskDTO.setEmployeeName(user.getName());
        taskDTO.setEmployeeId(user.getId());
        taskDTO.setTaskStatus(taskStatus);
        taskDTO.setDueDate(dueDate);
        taskDTO.setPriority(priority);
        return  taskDTO;
    }
}

