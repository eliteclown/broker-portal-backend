package com.projects.brokerPortal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


//QuoteList DTO for handling operations related to quoteList
public class QuoteListDTO {
    private Long quoteListId;

    private Long customerId;
    private String quoteStringId;
    private String customerName;
    private String lastName;
    private String email;
    private String qualification;
    private String specialiaztion;
    private BigDecimal premium;
    private LocalDateTime createdAt;
    private String status;
}

