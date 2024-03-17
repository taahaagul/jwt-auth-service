package com.taahaagul.jwtauthservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePasswordRequest {

    @NotBlank
    private String oldPasw;
    @NotBlank
    private String newPasw;
}