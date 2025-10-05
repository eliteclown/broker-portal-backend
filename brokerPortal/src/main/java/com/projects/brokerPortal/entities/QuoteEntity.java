package com.projects.brokerPortal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quotes")

//Quote Entity for handling quote related operations
public class QuoteEntity {
    @Id // sets quoteId as primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // auto-increment
    private Long quoteId;


    @ManyToOne  //many-to-one mapping between quotes and brokers table
    @JoinColumn(name = "broker_id",referencedColumnName = "brokerId")
    private BrokerEntity brokerQuote;

    @OneToOne //one-to-one mapping between quotes and customers table
    @JoinColumn(name = "customer_id",referencedColumnName = "customerId")
    private CustomerEntity customer;
    @Column(nullable = false)
    private String qualification;
    @Column(nullable = false)
    private String specialization;
    @Column(nullable = false)
    private String businessType;
    @Column(nullable = false)
    private Long annualRevenue;
    @Column(nullable = false)
    private Long yearsOfExperience;
    @Column(nullable = false)
    private Long coverageAmount;
    @Column(nullable = false)
    private Long deductible;
    @Column(nullable = false)
    private String additionalCoverages;
    @Column(nullable = false)
    private Long previousClaim;
    @Column(nullable = false)
    private String coverageLimits;
    @Column(nullable = false)
    private String panCardNumber;
    @Column(nullable = false)
    private String policyStartDate;
    @Column(nullable = false)
    private String policyEndDate;
    @Column(nullable = false)
    private String retroactiveDate;

    @CreationTimestamp
    private LocalDateTime quoteDate; //sets quoteDate automatically

    private String status;  //{'Rejected','submitted','bound','deleted'}


    @OneToOne(mappedBy = "quotes") //one to one mapping between quotes and quoteList table
    private QuoteListEntity quoteLists;


}