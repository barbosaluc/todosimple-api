package com.barbosaluc.todosimple.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbosaluc.todosimple.models.User;
import com.barbosaluc.todosimple.repositories.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado! id: " + id + ", Tipo: " + User.class.getName()
        ));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(0L);
        obj = this.userRepository.save(obj);
        return obj; 
    }

    @Transactional
    public User update( User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    } 

    public void delete(long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException( 
                "Não é possível excluir usuário com tarefas associadas! ");
        } 
        
    }
}