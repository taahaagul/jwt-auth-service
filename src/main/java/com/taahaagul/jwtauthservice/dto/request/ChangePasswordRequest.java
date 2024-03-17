package com.taahaagul.jwtauthservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @NotBlank
    private String oldPasw;
    @NotBlank
    private String newPasw;
}