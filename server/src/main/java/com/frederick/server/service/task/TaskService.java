package com.frederick.server.service.task;

import com.frederick.server.document.Task;
import com.frederick.server.repository.task.TaskRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task registerTask(Task task) {
        // 현재 사용자 ID를 설정
        String currentUserId = getCurrentUserId();
        task.setUserId(currentUserId);
        return taskRepository.save(task);
    }

    public List<Task> getTasks() {
        // 현재 사용자 ID에 해당하는 작업만 반환
        String currentUserId = getCurrentUserId();
        return taskRepository.findByUserId(currentUserId);
    }

    public Task updateTask(String taskId, Task updatedTask) {
        return taskRepository.findById(taskId)
                .filter(task -> task.getUserId().equals(getCurrentUserId())) // 소유자 확인
                .map(existingTask -> {
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setCompleted(updatedTask.isCompleted());
                    return taskRepository.save(existingTask);
                }).orElse(null);
    }

    public boolean deleteTask(String taskId) {
        return taskRepository.findById(taskId)
                .filter(task -> task.getUserId().equals(getCurrentUserId())) // 소유자 확인
                .map(task -> {
                    taskRepository.delete(task);
                    return true;
                }).orElse(false);
    }

    // 현재 인증된 사용자 ID 반환
    private String getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }
}
