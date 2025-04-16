package com.barbosaluc.todosimple.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbosaluc.todosimple.models.Task;
import com.barbosaluc.todosimple.models.User;
import com.barbosaluc.todosimple.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
            "Tarefa não encontrada! id: " + id + ",Tipo: " + Task.class.getName()
            ));       
    }

    public List<Task> findAllByUserId(long userId){
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(0L);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                "Não é possível excluir tarefa com usuários associados!" );
        }
        
    }

}



