package com.projects.brokerPortal.controllers;

import com.projects.brokerPortal.dtos.CustomerDTO;
import com.projects.brokerPortal.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

// Controller for creating new customers and updating details of old customers
@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //creates new customers and maps it to the brokerId
    @PostMapping("/create/{brokerId}")
    public CustomerDTO assignCustomerToBroker(@RequestBody @Valid CustomerDTO customerDTO, @PathVariable Long brokerId){
        return customerService.assignCustomerToBroker(customerDTO,brokerId);
    }

    //gets customer details by customerId
    @GetMapping("/{customerId}")
    public CustomerDTO getCustomer(@PathVariable Long customerId){
        return customerService.getCustomer(customerId);
    }

    //updates existing customer details based on customerId and brokerId
    @PutMapping("/{brokerId}/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long brokerId,@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO){
        return customerService.updateCustomer(brokerId,id,customerDTO);
    }
}
