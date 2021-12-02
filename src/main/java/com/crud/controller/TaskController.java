package com.crud.controller;

import java.util.List;

import com.crud.dto.TaskDto;
import com.crud.entity.Task;
import com.crud.enumeration.TaskStatus;
import com.crud.service.TaskService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping(path = "list")
    public List<Task> list() {
        return taskService.findAll();
    }

    @PostMapping(path = "create")
    public Task create(@RequestBody TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        task.setStatus(TaskStatus.PENDING);
        Task newTask = taskService.create(task);
        return newTask;
    }
}
