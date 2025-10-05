package com.projects.brokerPortal.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//Quote DTO  for handling operations related to quotes
public class QuoteDTO {
    private Long quoteId;
    private Long brokerId;
    private Long customerId;
    @NotBlank(message = "qualification cannot be blank")
    private String qualification;
    @NotBlank(message = "specialization cannot be blank")
    private String specialization;
    @NotBlank(message = "buisness type cannot be blank")
    private String businessType;
    @NotNull(message = "Annual Revenue cannot be empty")
    @Positive(message = "Annual Revenue should be positive")
    private Long annualRevenue;
    @NotNull(message = "yoe cannot be empty")
    @Positive(message = "yoe should be positive")
    private Long yearsOfExperience;
    @NotNull(message = "coverage amount cannot be empty")
    @Positive(message = "coverage amount should be positive")
    private Long coverageAmount;
    @NotNull(message = "deductible amount cannot be empty")
    @Positive(message = "deductible amount should be positive")
    private Long deductible;
    @NotBlank(message = "additional coverages cannot be blank")
    private String additionalCoverages;

    @NotNull(message = "previous claim cannot be empty")
//    @Positive(message = "previous claim should be positive")
    private Long previousClaim;
    @NotBlank(message = "coverage limits cannot be blank")
    private String coverageLimits;
    @NotBlank(message = "Pan Card number cannot be blank")
    private String panCardNumber;
    @NotBlank(message = "Policy Start Date cannot be blank")
    private String policyStartDate;
    @NotBlank(message = "Policy End Date cannot be blank")
    private String policyEndDate;
    private String retroactiveDate;
    private LocalDateTime quoteDate;
    private String status;  //{'Rejected','submitted','bound','deleted'}

}
