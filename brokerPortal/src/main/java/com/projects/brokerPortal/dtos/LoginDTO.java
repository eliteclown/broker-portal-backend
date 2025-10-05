package com.projects.brokerPortal.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//Login DTO for handling authentication operations
public class LoginDTO {


    @Email(message = "Email should be a valid email")
    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "Broker password cannot be blank")
    @Size(min = 3,max = 10,message = "password should be between 3 and 10 characters")
    private String password;
}

