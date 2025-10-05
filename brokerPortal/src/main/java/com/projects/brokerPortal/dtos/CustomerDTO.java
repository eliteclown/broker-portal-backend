package com.projects.brokerPortal.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//Customer DTO for all the operations related to customer
public class CustomerDTO {
    private Long customerId;
    private Long brokerId;
    @NotBlank(message = "Customer name First Name cannot be blank")
    @Pattern(regexp = "^[a-zA-z\\s]+$", message = "Invalid name format")
    private String firstName;
    @NotBlank(message = "Customer name Last Name cannot be blank")
    private String lastName;
    @NotBlank(message = "Email Not Valid")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email")
    private String email;
    @NotBlank(message = "Customer address cannot be blank")
    private String address;
    @NotBlank(message = "Customer city cannot be blank")
    private String city;
    @NotBlank(message = "Customer state cannot be blank")
    private String state;
    @NotBlank(message = "Customer pincode cannot be blank")
    private String pincode;
    @NotBlank(message = "Customer DOB cannot be blank")
    private String birthDate;
    @NotBlank(message = "Customer phone no cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone Number")
    private String phoneNo;
}



