package com.barbosaluc.todosimple.models;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = Task.TABLE_NAME)
@Data
public class Task {

    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true )
    private Long id;
    
    @ManyToOne
    @Joincolumn (name = "user_id" , nullable = false , updatable = false)
    private User user;

    @column (name = "description" , lenght = 255 , nullable = false)
    @NotBlank
    @Size(min = 1 , max = 255)
    private String description;
}
