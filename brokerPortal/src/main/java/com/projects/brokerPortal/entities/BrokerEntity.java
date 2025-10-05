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
@Table(name = "brokers")

//Broker entity for handling registeration and authentication of brokers
public class BrokerEntity {
    @Id  //sets brokerId as primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //auto increment
    private Long brokerId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
}
