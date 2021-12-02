package com.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import com.crud.entity.Task;
import com.crud.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "TaskService")
@Transactional
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll () {
        return taskRepository.findAll();
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

}
