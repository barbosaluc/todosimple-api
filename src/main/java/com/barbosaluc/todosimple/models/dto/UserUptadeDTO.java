package com.barbosaluc.todosimple.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUptadeDTO {
    
    private Long id;

    @NotBlank
    @Size(min = 8, max = 60)
    private String password;
}
