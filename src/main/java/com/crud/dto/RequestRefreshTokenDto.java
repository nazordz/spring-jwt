package com.crud.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RequestRefreshTokenDto {
    @NotEmpty
    private String refresh_token;
}
