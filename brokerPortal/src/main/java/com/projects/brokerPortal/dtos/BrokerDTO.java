package com.projects.brokerPortal.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


//Broker DTO for registering new Broker
public class BrokerDTO {
    private Long brokerId;
    @NotBlank(message = "Broker Name cannot be blank")
    private String username;
    @NotBlank(message = "Broker password cannot be blank")
    @Size(min = 3,max = 10,message = "password should be between 3 and 10 characters")
    private String password;
    @Email(message = "Email should be a valid email")
    @NotBlank(message = "email cannot be blank")
    private String email;
}