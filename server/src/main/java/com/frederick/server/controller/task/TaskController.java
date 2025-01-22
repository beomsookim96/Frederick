package com.frederick.server.controller.task;

import com.frederick.server.document.Task;
import com.frederick.server.service.task.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> registerTask(@RequestBody Task task) {
        // task.getUserId() 제거: 서비스 계층에서 현재 사용자의 ID를 설정
        Task createdTask = taskService.registerTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        // 현재 사용자만 자신의 작업을 조회
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable String taskId, @RequestBody Task updatedTask) {
        Task updated = taskService.updateTask(taskId, updatedTask);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        boolean deleted = taskService.deleteTask(taskId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
