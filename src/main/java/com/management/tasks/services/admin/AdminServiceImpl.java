package com.management.tasks.services.admin;

import com.management.tasks.dto.TaskDTO;
import com.management.tasks.dto.UserDto;
import com.management.tasks.entities.Task;
import com.management.tasks.entities.User;
import com.management.tasks.enums.TaskStatus;
import com.management.tasks.enums.UserRole;
import com.management.tasks.repositories.TaskRepository;
import com.management.tasks.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user->user.getUserRole()== UserRole.EMPLOYEE)
                .map(User::getUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Optional<User> optionalUser=userRepository.findById(taskDTO.getEmployeeId());
        if(optionalUser.isPresent()){
            Task task=new Task();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setPriority(taskDTO.getPriority());
            task.setDueDate(taskDTO.getDueDate());
            task.setTaskStatus(TaskStatus.INPROGRESS);
            task.setUser(optionalUser.get());
            return taskRepository.save(task).getTaskDTO();

        }
        return null;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Optional<Task> optionalTask= taskRepository.findById(id);
        return optionalTask.map(Task::getTaskDTO).orElse(null);
    }

    @Override
    public TaskDTO updteTask(Long id, TaskDTO taskDTO) {
       Optional <Task> optionalTask= taskRepository.findById(id);
        if(optionalTask.isPresent()){
            Task existingTask = optionalTask.get();
            existingTask.setTitle(taskDTO.getTitle());
            existingTask.setDescription(taskDTO.getDescription());
            existingTask.setPriority(taskDTO.getPriority());
            existingTask.setDueDate(taskDTO.getDueDate());
            existingTask.setTaskStatus(mapStringToTaskStatus(String.valueOf(taskDTO.getTaskStatus())));
            // 🔽 Add this block to update the employee (user)
            if (taskDTO.getEmployeeId() != null) {
                Optional<User> optionalUser = userRepository.findById(taskDTO.getEmployeeId());
                optionalUser.ifPresent(existingTask::setUser); // set user if found
            }


            return taskRepository.save( existingTask).getTaskDTO();
        }
       return null;
    }

    private TaskStatus mapStringToTaskStatus(String status){
        return switch (status){
            case "PENDING"->TaskStatus.PENDING;
            case "INPROGRESS"->TaskStatus.INPROGRESS;
            case "COMPLETED"->TaskStatus.COMPLETED;
            case "DEFFERED"-> TaskStatus.DEFERRED;
            default -> TaskStatus.CANCELED;
        };
    }




}
