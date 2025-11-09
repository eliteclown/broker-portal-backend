package com.projects.brokerPortal.controllers;

import com.projects.brokerPortal.dtos.QuoteDTO;
import com.projects.brokerPortal.services.QuoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

//Controller for creating , getting and updating quotes
@RestController
@RequestMapping("/quotes")
@CrossOrigin("*")
public class QuoteController {

    private final QuoteService quoteService;



    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;

    }

    //Creates a new quote
    @PostMapping("/{brokerId}/{customerId}")
    public ResponseEntity<QuoteDTO> createNewQuote(@PathVariable Long brokerId, @PathVariable Long customerId, @RequestBody @Valid QuoteDTO inputDTO ){
        QuoteDTO quoteDTO= quoteService.createNewQuote(brokerId,customerId,inputDTO);
        return new ResponseEntity<>(quoteDTO, HttpStatus.CREATED);
    }


    //Get a specific quote by quoteId
    @GetMapping("/{quoteId}")
    public ResponseEntity<QuoteDTO> getQuoteById(@PathVariable Long quoteId){
        Optional<QuoteDTO> quoteDTO=quoteService.getQuoteById(quoteId);
        return quoteDTO
                .map(quoteDTO1 -> ResponseEntity.ok(quoteDTO1))
                .orElseThrow(()-> new RuntimeException());
    }

    //Update a quote corresponding to a specific brokerId and customerId
    @PutMapping("/{brokerId}/{customerId}")
    public QuoteDTO updateQuoteById(@PathVariable Long brokerId, @PathVariable  Long customerId, @RequestBody @Valid  QuoteDTO updateDTO){
        return quoteService.updateQuoteById(brokerId,customerId,updateDTO);
    }




    //Submit a quote for calculating premium
    @PostMapping("/{quoteId}/getPremium")
    public ResponseEntity<BigDecimal> bindQuote(@RequestBody QuoteDTO inputDTO){
        BigDecimal amount= quoteService.bindQuote(inputDTO);
        return ResponseEntity.ok(amount);
    }

}