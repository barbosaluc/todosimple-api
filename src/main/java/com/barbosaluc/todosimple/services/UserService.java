package com.barbosaluc.todosimple.services;

import com.barbosaluc.todosimple.models.User;
import com.barbosaluc.todosimple.repositories.TaskRepository;
import com.barbosaluc.todosimple.repositories.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transactional.annotation.Transactional;

@Service
public class UserService {
    // É indicado usar a injeção de depêndencia pelo construtor.

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById (Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RunTimeException (
            "Usuário não encontrado! id: " + id + ", Tipo: " + User.class.getName() 
        ));
   
    }

     @Transactional
     public User create(User obj) {
         obj.setId(id: null);
         obj = this.userRepository.save(obj);
         this.taskRepository.saveAll (obj.getTasks());
         return obj; 
     }

     @Transactional
     public User update (User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj); 
     }

    public  void  delete (Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        }catch(Exception e) {
            throw new RunTimeException("Não é possível excluir pois há entidades relacionadas");
        }

    }

}
