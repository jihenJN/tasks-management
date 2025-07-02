package com.management.tasks.services.admin;

import com.management.tasks.dto.TaskDTO;
import com.management.tasks.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getUsers();
    TaskDTO createTask(TaskDTO taskDTO);
    List<TaskDTO> getAllTasks();
    void deleteTask(Long id);
    TaskDTO getTaskById(Long id);
    TaskDTO updteTask(Long id, TaskDTO taskDTO);
}
