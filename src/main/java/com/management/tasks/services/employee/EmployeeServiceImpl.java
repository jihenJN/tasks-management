package com.management.tasks.services.employee;

import com.management.tasks.dto.TaskDTO;
import com.management.tasks.entities.Task;
import com.management.tasks.entities.User;
import com.management.tasks.repositories.TaskRepository;
import com.management.tasks.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final TaskRepository taskRepository;
    private final JwtUtil jwtUtil;
    @Override
    public List<TaskDTO> getTasksByUserId() {
     User user =jwtUtil.getLoggedInUser();
      if(user!=null){
        return  taskRepository.findAllByUserId(user.getId())
                  .stream()
                  .sorted(Comparator.comparing(Task::getDueDate).reversed())
                  .map(Task::getTaskDTO)
                  .collect(Collectors.toList());
      }
     throw new EntityNotFoundException("User not found");
    }
}
