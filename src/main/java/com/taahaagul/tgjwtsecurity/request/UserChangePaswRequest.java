package com.taahaagul.tgjwtsecurity.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserChangePaswRequest {

    @NotBlank
    private String oldPasw;
    @NotBlank
    private String newPasw;
}
