package com.projects.brokerPortal.exceptions;

public class BrokerNotFoundException extends RuntimeException{
    public BrokerNotFoundException(String message){
        super(message);
    }
}
