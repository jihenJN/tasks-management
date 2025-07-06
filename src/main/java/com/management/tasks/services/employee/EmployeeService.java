package com.management.tasks.services.employee;

import com.management.tasks.dto.TaskDTO;

import java.util.List;

public interface EmployeeService {
    List<TaskDTO> getTasksByUserId();
}
