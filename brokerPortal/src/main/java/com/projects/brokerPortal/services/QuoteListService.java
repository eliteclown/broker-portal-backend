package com.projects.brokerPortal.services;

import com.projects.brokerPortal.dtos.QuoteListDTO;
import com.projects.brokerPortal.entities.BrokerEntity;
import com.projects.brokerPortal.entities.QuoteEntity;
import com.projects.brokerPortal.entities.QuoteListEntity;
import com.projects.brokerPortal.exceptions.BrokerNotFoundException;
import com.projects.brokerPortal.exceptions.QuoteNotFoundException;
import com.projects.brokerPortal.repositories.BrokerRepository;
import com.projects.brokerPortal.repositories.CustomerRepository;
import com.projects.brokerPortal.repositories.QuoteListRepository;
import com.projects.brokerPortal.repositories.QuoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteListService {
    private final QuoteListRepository quoteListRepository;
    private final BrokerRepository brokerRepository;
    private final CustomerRepository customerRepository;
    private final QuoteRepository quoteRepository;
    private final ModelMapper modelMapper;

    public QuoteListService(QuoteListRepository quoteListRepository, BrokerRepository brokerRepository, CustomerRepository customerRepository, QuoteRepository quoteRepository, ModelMapper modelMapper) {
        this.quoteListRepository = quoteListRepository;
        this.brokerRepository = brokerRepository;
        this.customerRepository = customerRepository;
        this.quoteRepository = quoteRepository;
        this.modelMapper = modelMapper;
    }

    // returns list of quoteList corresponding to given brokerId
    public List<QuoteListDTO> getQuoteList(Long id){
        BrokerEntity brokerEntity=brokerRepository.findById(id).orElseThrow(()-> new BrokerNotFoundException("Broker Not Found with Id "+id));
        List<QuoteListEntity> quoteListEntities=quoteListRepository.findAllByBroker(brokerEntity);

        return quoteListEntities.stream()
                .filter(quote-> !quote.getStatus().equalsIgnoreCase("Deleted"))
                .map(quoteListEntity -> modelMapper.map(quoteListEntity, QuoteListDTO.class))
                .collect(Collectors.toList());
    }

    //return premium by quoteId
    public BigDecimal getPremiumById(Long quoteId) {
        QuoteListEntity quoteListEntity=quoteListRepository.findById(quoteId).orElseThrow(()-> new QuoteNotFoundException("Quote Not Found with Id"+quoteId));
        return quoteListEntity.getPremium();
    }


    //returns true if the status is successfully changed from Rejected to Deleted
    public Boolean deleteById( Long quoteId) {
        QuoteListEntity quoteListEntity=quoteListRepository.findById(quoteId).orElseThrow(()-> new QuoteNotFoundException("Quote Not Found with Id "+quoteId));
        QuoteEntity quoteEntity=quoteRepository.findById(quoteId).orElseThrow(()-> new QuoteNotFoundException("Quote Not Found with Id "+quoteId));
        quoteListEntity.setStatus("Deleted");
        quoteEntity.setStatus("Deleted");
        quoteRepository.save(quoteEntity);
        quoteListRepository.save(quoteListEntity);
        return  true;
    }

    //returns true if the status is successfully changed from Approved to Bound
    public Boolean boundById(Long quoteId) {
        QuoteListEntity quoteListEntity=quoteListRepository.findById(quoteId).orElseThrow(()-> new QuoteNotFoundException("Quote Not Found with Id"+quoteId));
        QuoteEntity quoteEntity=quoteRepository.findById(quoteId).orElseThrow(()-> new QuoteNotFoundException("Quote Not Found with Id"+quoteId));
        quoteListEntity.setStatus("Bound");
        quoteEntity.setStatus("Bound");
        quoteRepository.save(quoteEntity);
        quoteListRepository.save(quoteListEntity);
        return  true;
    }
}
