package com.projects.brokerPortal.controllers;

import com.projects.brokerPortal.dtos.BrokerDTO;
import com.projects.brokerPortal.dtos.LoginDTO;
import com.projects.brokerPortal.security.JwtUtil;
import com.projects.brokerPortal.services.BrokerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//Controller for creating and verifying(login) brokers
@RestController
@RequestMapping("/brokers")
@CrossOrigin("*")
public class BrokerController {

    //Bean Injection
    @Autowired
    private JwtUtil jwtUtil;
    private  final BrokerService brokerService;

    public BrokerController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    //creates new Broker
    @PostMapping("/create")
    public BrokerDTO createBroker(@RequestBody @Valid BrokerDTO brokerDTO){

        return brokerService.createBroker(brokerDTO);
    }


    //authenticate a given Broker and generate a JWT token
    @PostMapping("/login")
    public ResponseEntity<?> brokerLogin(@RequestBody @Valid LoginDTO loginDTO) {
        BrokerDTO broker = brokerService.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
        String token = jwtUtil.generateToken(broker.getEmail());


        return ResponseEntity.ok(Map.of(
                "token", token,
                "broker", broker
        ));

    }


}
