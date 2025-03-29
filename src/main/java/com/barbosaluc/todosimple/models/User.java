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
@Table(name = "user.TABLE_NAME")
@Data

public class User {
    
    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "username", unique = true)
    @NotBlank
    @Size(min = 2 , max = 100)
    private String userName;

    @Column(name = "password")
    @NotBlank
    @Size(min = 8 , max = 60)
    private String password;

    @OneToMany(mappebBy = "user")
    private List<Task> tasks = new ArrayList<Task>();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime + result + ({this.id == null} ? 0 :this.id.hashCode{});
        return result;
    }
}
