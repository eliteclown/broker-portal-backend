package com.projects.brokerPortal.services;

import com.projects.brokerPortal.dtos.CustomerDTO;
import com.projects.brokerPortal.entities.BrokerEntity;
import com.projects.brokerPortal.entities.CustomerEntity;
import com.projects.brokerPortal.exceptions.BrokerNotFoundException;
import com.projects.brokerPortal.exceptions.CustomerNotFoundException;
import com.projects.brokerPortal.repositories.BrokerRepository;
import com.projects.brokerPortal.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

//customer service handles all requests from customer controller
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final BrokerRepository brokerRepository;

    //constructor injection
    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, BrokerRepository brokerRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.brokerRepository = brokerRepository;
    }




    // creates a new customer and assigns it to a brokerId
    public CustomerDTO assignCustomerToBroker(CustomerDTO customerDTO, Long brokerId) {
        BrokerEntity brokerEntity=brokerRepository.findById(brokerId).orElseThrow(()->  new BrokerNotFoundException("Broker Not Found with Id"+brokerId));
        CustomerEntity customerEntity= modelMapper.map(customerDTO,CustomerEntity.class);

        customerEntity.setBroker(brokerEntity);

        CustomerEntity savedEntity=customerRepository.save(customerEntity);//customer entity is saved in the database
        return modelMapper.map(savedEntity,CustomerDTO.class);



    }
    // returns a customer by customerId
    public CustomerDTO getCustomer(Long customerId) {
        CustomerEntity customerEntity= customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found with Id"+customerId));
        return modelMapper.map(customerEntity,CustomerDTO.class);
    }

    // updates an existing customer by customerId
    public CustomerDTO updateCustomer(Long brokerId,Long id, CustomerDTO customerDTO) {
        CustomerEntity customerEntity=modelMapper.map(customerDTO,CustomerEntity.class);
        BrokerEntity brokerEntity = brokerRepository.findById(brokerId).orElseThrow(()-> new BrokerNotFoundException("Broker Not Found with Id"+brokerId));
        customerEntity.setCustomerId(id);
        customerEntity.setBroker(brokerEntity);

        CustomerEntity savedEntity=customerRepository.save(customerEntity);
        return modelMapper.map(savedEntity,CustomerDTO.class);
    }
}
