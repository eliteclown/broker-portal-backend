package com.projects.brokerPortal.advices;

import com.projects.brokerPortal.exceptions.BrokerNotFoundException;
import com.projects.brokerPortal.exceptions.CustomerNotFoundException;
import com.projects.brokerPortal.exceptions.InvalidBrokerException;
import com.projects.brokerPortal.exceptions.QuoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Handling Exception globally
@RestControllerAdvice
public class GlobalExceptionHandler {

    //handling BrokerNotFoundException
    @ExceptionHandler(BrokerNotFoundException.class)
    public ResponseEntity<?> handleBrokerError(BrokerNotFoundException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }

    // handling CustomerNotFoundException
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerError(CustomerNotFoundException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }


    //handling InvalidBrokerException
    @ExceptionHandler(InvalidBrokerException.class)
    public ResponseEntity<?> handleBrokerAuthenticateError(InvalidBrokerException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }

    //handling QuoteNotFoundException
    @ExceptionHandler(QuoteNotFoundException.class)
    public ResponseEntity<?> handleQuoteError(QuoteNotFoundException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }


}
