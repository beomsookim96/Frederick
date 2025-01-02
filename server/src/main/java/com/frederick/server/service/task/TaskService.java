package com.frederick.server.service.task;

import com.frederick.server.document.Task;
import com.frederick.server.repository.task.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task registerTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUserId(String userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task updateTask(String taskId, Task updatedTask) {
        return taskRepository.findById(taskId)
                .map(existingTask -> {
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setCompleted(updatedTask.isCompleted());
                    return taskRepository.save(existingTask);
                }).orElse(null);
    }

    public boolean deleteTask(String taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return true;
        }
        return false;
    }
}
