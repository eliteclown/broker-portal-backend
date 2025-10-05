package com.projects.brokerPortal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")

//Customer Entity for handling customer related operations
public class CustomerEntity {
    @Id //sets customerId as primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //auto-increment
    private Long customerId;
    @ManyToOne   //many-to-one relationship between customers table and brokers table
    @JoinColumn(name = "broker_id",referencedColumnName = "brokerId")// foriegn key
    private BrokerEntity broker;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String pincode;
    @Column(nullable = false)
    private String birthDate;
    @Column(nullable = false)
    private String phoneNo;

    @OneToOne(mappedBy = "customer") // one to many mapping
    private QuoteEntity quotes;

}