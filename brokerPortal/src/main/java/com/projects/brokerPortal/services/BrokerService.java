package com.projects.brokerPortal.services;

import com.projects.brokerPortal.dtos.BrokerDTO;
import com.projects.brokerPortal.entities.BrokerEntity;
import com.projects.brokerPortal.exceptions.BrokerNotFoundException;
import com.projects.brokerPortal.exceptions.InvalidBrokerException;
import com.projects.brokerPortal.repositories.BrokerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

// broker service handles all requests from broker controller like register and login
@Service
public class BrokerService {
    private final BrokerRepository brokerRepository;
    private final ModelMapper modelMapper;

    //constructor injection
    public BrokerService(BrokerRepository brokerRepository, ModelMapper modelMapper) {
        this.brokerRepository = brokerRepository;
        this.modelMapper = modelMapper;
    }

    //returns registered broker
    public BrokerDTO createBroker(BrokerDTO brokerDTO) {
        BrokerEntity brokerEntity=modelMapper.map(brokerDTO, BrokerEntity.class);
        BrokerEntity savedEntity=brokerRepository.save(brokerEntity);
        return modelMapper.map(savedEntity,BrokerDTO.class);
    }

    // returns authenticated broker and jwt token or throws error
    public BrokerDTO authenticate(String email, String password) {

        Optional<BrokerEntity> broker = brokerRepository.findByEmail(email);
        if(broker.isPresent()) {
            BrokerEntity b= broker.get();
            if (b.getPassword().equals(password)) {

                return modelMapper.map(b,BrokerDTO.class);

            }
            else {
                throw new InvalidBrokerException("Invalid Credentials");
            }

        }
        else {
            throw new BrokerNotFoundException("Broker with email " + email + " not found");
        }




    }


}
