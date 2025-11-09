package com.projects.brokerPortal.services;


import com.projects.brokerPortal.dtos.QuoteDTO;
import com.projects.brokerPortal.entities.BrokerEntity;
import com.projects.brokerPortal.entities.CustomerEntity;
import com.projects.brokerPortal.entities.QuoteEntity;
import com.projects.brokerPortal.entities.QuoteListEntity;
import com.projects.brokerPortal.exceptions.BrokerNotFoundException;
import com.projects.brokerPortal.exceptions.CustomerNotFoundException;
import com.projects.brokerPortal.exceptions.QuoteNotFoundException;
import com.projects.brokerPortal.repositories.BrokerRepository;
import com.projects.brokerPortal.repositories.CustomerRepository;
import com.projects.brokerPortal.repositories.QuoteListRepository;
import com.projects.brokerPortal.repositories.QuoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Quote Service for handling all the requests from Quote Controller
@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final QuoteListRepository quoteListRepository;
    private final BrokerRepository brokerRepository;
    private final CustomerRepository customerRepository;
    private final RatingService ratingService;
    private final ModelMapper modelMapper;

    private final int pageSize=5;


    //constructor injection
    public QuoteService(QuoteRepository quoteRepository, QuoteListRepository quoteListRepository, BrokerRepository brokerRepository, CustomerRepository customerRepository, RatingService ratingService, ModelMapper modelMapper) {
        this.quoteRepository = quoteRepository;
        this.quoteListRepository = quoteListRepository;
        this.brokerRepository = brokerRepository;
        this.customerRepository = customerRepository;
        this.ratingService = ratingService;

        this.modelMapper = modelMapper;

    }

    //checks whether given quoteId exists or not
    public void isExistsByQuoteId(Long quoteId){
        boolean exists = quoteRepository.existsById(quoteId);
        if(!exists) throw new QuoteNotFoundException("Quote not found with id: "+quoteId);
    }

    //creates new quote and save the quote to the database
    public QuoteDTO createNewQuote(Long brokerId, Long customerId, QuoteDTO inputDTO) {
        BrokerEntity brokerEntity= brokerRepository.findById(brokerId).orElseThrow(()-> new BrokerNotFoundException("Broker Not Found with Id "+brokerId));
        CustomerEntity customerEntity=customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found with Id "+customerId));
        QuoteEntity quoteEntity=modelMapper.map(inputDTO, QuoteEntity.class);
        quoteEntity.setBrokerQuote(brokerEntity);
        quoteEntity.setCustomer(customerEntity);
        quoteEntity.setStatus("Submitted");
        QuoteEntity savedEntity= quoteRepository.save(quoteEntity);

        BigDecimal premiumNew= bindQuote(inputDTO);
        QuoteListEntity quoteListEntity=QuoteListEntity
                .builder()
                .quoteStringId("PL-2025-"+quoteEntity.getQuoteId())
                .quotes(quoteEntity)
                .broker(brokerEntity)
                .customerId(quoteEntity.getCustomer().getCustomerId())
                .customerName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .email(customerEntity.getEmail())
                .qualification(quoteEntity.getQualification())
                .specialiaztion(quoteEntity.getSpecialization())
                .premium(premiumNew)
                .createdAt(LocalDateTime.now())
                .status(quoteEntity.getStatus())
                .build();
        quoteListRepository.save(quoteListEntity);
        return modelMapper.map(savedEntity,QuoteDTO.class);
    }

    //pagination
    public List<QuoteDTO> getQuotes(int pageNumber, String sortBy) {
        Pageable pageable =  PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        List<QuoteEntity> quoteEntities=quoteRepository.findAll(pageable).getContent();
        return  quoteEntities.stream()
                .map(quoteEntity -> modelMapper.map(quoteEntity,QuoteDTO.class))
                .collect(Collectors.toList());

    }


    //returns quote by quoteId
    public Optional<QuoteDTO> getQuoteById(Long quoteId) {
        return quoteRepository.findById(quoteId).
                map(quoteEntity -> modelMapper.map(quoteEntity,QuoteDTO.class));

    }


    //updates quote corresponding to specific brokerId and customerId
    public QuoteDTO updateQuoteById( Long brokerId,Long id,QuoteDTO quoteDTO) {

        QuoteEntity quoteEntity=modelMapper.map(quoteDTO,QuoteEntity.class);

        CustomerEntity customerEntity=customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found with Id "+id));
        BrokerEntity brokerEntity =brokerRepository.findById(brokerId).orElseThrow(()-> new BrokerNotFoundException("Broker Not Found with Id "+brokerId));
        quoteEntity.setQuoteId(id);

        quoteEntity.setCustomer(customerEntity);
        quoteEntity.setQuoteDate(LocalDateTime.now());
        quoteEntity.setBrokerQuote(brokerEntity);
        quoteEntity.setStatus("Submitted");
        QuoteEntity savedEntity=quoteRepository.save(quoteEntity);
        BigDecimal premiumUpdated=ratingService.calculatePremium(savedEntity);
        QuoteListEntity quoteListEntity=quoteListRepository.findById(id).orElseThrow(()-> new QuoteNotFoundException("Quote Not Found with "+id));
        quoteListEntity.setQuotes(savedEntity);
        quoteListEntity.setQuoteStringId("PL-2025-"+savedEntity.getQuoteId());
        quoteListEntity.setBroker(brokerEntity);
        quoteListEntity.setCustomerId(savedEntity.getCustomer().getCustomerId());
        quoteListEntity.setCustomerName(savedEntity.getCustomer().getFirstName());
        quoteListEntity.setLastName(savedEntity.getCustomer().getLastName());
        quoteListEntity.setEmail(savedEntity.getCustomer().getEmail());
        quoteListEntity.setQualification(savedEntity.getQualification());
        quoteListEntity.setSpecialiaztion(savedEntity.getSpecialization());
        quoteListEntity.setPremium(BigDecimal.valueOf(100000));
        quoteListEntity.setCreatedAt(LocalDateTime.now());
        quoteListEntity.setStatus(savedEntity.getStatus());
        quoteListEntity.setPremium(premiumUpdated);



        quoteListRepository.save(quoteListEntity);
        return modelMapper.map(savedEntity,QuoteDTO.class);
    }


    //implements soft delete functionality by changing the status from Rejected to Deleted
    public Boolean deleteQuoteBYId(Long quoteId) {
        isExistsByQuoteId(quoteId);
        quoteRepository.deleteById(quoteId);

        return true;
    }



    //return premium for a specifc quote
    public BigDecimal bindQuote(QuoteDTO inputDTO) {
        QuoteEntity quoteEntity=modelMapper.map(inputDTO,QuoteEntity.class);
        return ratingService.calculatePremium(quoteEntity);
    }
}
