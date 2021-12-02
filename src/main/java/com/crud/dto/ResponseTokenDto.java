package com.crud.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseTokenDto {
    public final String access_token;    
    public final String refresh_token;
    public final String token_type;
    public final Integer expires_in;
}
