package com.barbosaluc.todosimple.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = Task.TABLE_NAME)
public class Task {

    public interface CreateTask {}
    public interface upadateTask {}

    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true )
    private Long id;
    
    @ManyToOne
    @JoinColumn (name = "user_id" , nullable = false , updatable = false)
    private User user;

    @Column (name = "description" ,length = 255 , nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1 , max = 255)
    private String description;
    
}

    