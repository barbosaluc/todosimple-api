  package com.barbosaluc.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbosaluc.todosimple.models.Task;
import com.barbosaluc.todosimple.models.projection.TaskProjection;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {

    List<TaskProjection> findByUser_Id(Long id);
    
}
