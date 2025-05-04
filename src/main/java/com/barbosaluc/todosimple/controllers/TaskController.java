package com.barbosaluc.todosimple.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.barbosaluc.todosimple.models.Task;
import com.barbosaluc.todosimple.models.Task.CreateTask;
import com.barbosaluc.todosimple.services.TaskService;
import com.barbosaluc.todosimple.services.UserService;

@RestController
@RequestMapping(value = "/task")
@Validated
public class TaskController {
    
    @Autowired
    public TaskService taskService;

    @Autowired
    public UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable long id){
        Task obj = this.taskService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable long userId){
        this.userService.findById(userId);
        List<Task> objs = this.taskService.findAllByUserId(userId);
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated(CreateTask.class)
    public ResponseEntity<Void> findById(@Valid @RequestBody Task obj) {
        this.taskService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(Task.upadateTask.class)
    public ResponseEntity<Void> update (@Valid @RequestBody Task obj,@PathVariable long id) {
        obj.setId(id);
        this.taskService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
