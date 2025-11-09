package com.projects.brokerPortal.controllers;


import com.projects.brokerPortal.dtos.QuoteListDTO;
import com.projects.brokerPortal.services.QuoteListService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

//Controller for getting quoteList
@RestController
@RequestMapping("/quoteList")
@CrossOrigin("*")
public class QuoteListController {
    private final QuoteListService quoteListService;

    public QuoteListController(QuoteListService quoteListService) {
        this.quoteListService = quoteListService;
    }


    // fetches list of quotes corresponding to a specific brokerId
    @GetMapping("/{brokerId}")
    public List<QuoteListDTO> getQuoteList(@PathVariable Long brokerId){
        List<QuoteListDTO> quoteListDTOS=quoteListService.getQuoteList(brokerId);

        return quoteListDTOS;
    }

    // fetches premium for a specific quoteId
    @GetMapping("/get/{quoteId}")
    public BigDecimal getPremiumById(@PathVariable Long quoteId){
        return quoteListService.getPremiumById(quoteId);
    }

    // changes the quote status from Approved to Bound and returns true if executed successfully
    @GetMapping("/bound/{quoteId}")
    public Boolean boundById(@PathVariable Long quoteId){
        return quoteListService.boundById(quoteId);
    }

    // changes the quote status from Rejected to Deleted  and returns true if executed successfully
    @DeleteMapping("/{quoteId}")
    public Boolean deleteById(@PathVariable Long quoteId){
        return quoteListService.deleteById(quoteId);
    }


}
