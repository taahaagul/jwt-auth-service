package com.taahaagul.jwtauthservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgetPasswordRequest {

    @NotBlank(message = "token is required")
    private String token;
    @NotBlank(message = "newPasw is required")
    private String newPasw;
}
