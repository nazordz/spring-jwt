package com.crud.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RoleToUseDto {
    @NotEmpty
    private String email;
    @NotEmpty
    private String roleName;
}
