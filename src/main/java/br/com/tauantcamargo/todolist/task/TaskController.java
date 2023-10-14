package br.com.tauantcamargo.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  @Autowired
  private ITaskRepository taskRepository;

  @GetMapping("/")
  public ResponseEntity<List<TaskModel>> getAll() {
    var allTasks = this.taskRepository.findAll();
    return ResponseEntity.ok().body(allTasks);
  }
  
  @PostMapping("/create")
  public ResponseEntity<TaskModel> create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    UUID userId = (UUID) request.getAttribute("userId");
    taskModel.setUserId(userId);
    var taskCreated = this.taskRepository.save(taskModel);
    return ResponseEntity.status(201).body(taskCreated);
  }
}
