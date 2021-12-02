package com.crud.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class TaskDto {
    
    @NotEmpty(message = "Judul harus diisi")
    private String title;

    private String description;
    private String author;
    private String assigne;
}
