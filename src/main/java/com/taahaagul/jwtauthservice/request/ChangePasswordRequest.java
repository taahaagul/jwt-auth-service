package com.taahaagul.jwtauthservice.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @NotBlank
    private String oldPasw;
    @NotBlank
    private String newPasw;
}