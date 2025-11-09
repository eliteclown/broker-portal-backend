package com.projects.brokerPortal.services;

import com.projects.brokerPortal.entities.QuoteEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RatingService {
    public BigDecimal calculatePremium(QuoteEntity quote) {
        Double premium= 0.0;



        Long sumInsured = quote.getCoverageAmount();

        Long previousClaims = quote.getPreviousClaim();


        premium = (0.01)*(sumInsured);
        //risk factors


        if (quote.getSpecialization().equals("Cardio Surgeon")) {
            premium=premium*(1.55);

        } else if (quote.getSpecialization().equals("Neuro Surgeon")) {
            premium=premium*(1.96);



        } else if (quote.getSpecialization().equals("Dermatologist")) {
            premium=premium*(1.75);

        } else if (quote.getSpecialization().equals("General Physician")) {
            premium=premium*(1.15);



        } else if (quote.getSpecialization().equals("Child Paediatrician")) {
            premium=premium*(1.32);

        }

//        //buisnesstype

        if (quote.getBusinessType().equals("Solo Practice")) {
            premium = premium*(1.5);

        } else if (quote.getBusinessType().equals("Group Practice")) {
            premium = premium*(1.2);

        } else if (quote.getBusinessType().equals(("Hospital"))) {
            premium = premium*(1.0);

        }


//        //annualRevenue

        if ((quote.getAnnualRevenue() < 5000000) && (quote.getAnnualRevenue() >= 1000000)) {
            premium = (1.00) * premium;
        } else if ((quote.getAnnualRevenue() < 10000000) && (quote.getAnnualRevenue() >= 5000000) ){
            premium= (1.15) * premium;
        } else if ((quote.getAnnualRevenue()< 15000000 )&& (quote.getAnnualRevenue() >= 10000000)) {
            premium = (1.36) * premium;
        } else if ((quote.getAnnualRevenue() < 20000000 )&& (quote.getAnnualRevenue() >= 15000000)) {
            premium = (1.60) * premium;
        } else if ((quote.getAnnualRevenue() < 100000000 )&&( quote.getAnnualRevenue()>= 20000000)){
            premium = (1.75) * premium;
        } else {
            premium = (1.98) * premium;
        }


        //yoe
        if ((quote.getYearsOfExperience() < 5 )&& (quote.getYearsOfExperience() >= 0)) {
            premium = (1.50) * premium;
        } else if ((quote.getYearsOfExperience() < 10) && (quote.getYearsOfExperience() >= 5) ){
            premium = (1.38) * premium;
        } else if ((quote.getYearsOfExperience() < 15) &&( quote.getYearsOfExperience() >= 10)) {
            premium = (1.21) * premium;
        } else if ((quote.getYearsOfExperience() < 20)&& (quote.getYearsOfExperience() >= 15)){
            premium = (1.05) * premium;
        } else if ((quote.getYearsOfExperience() < 25) && (quote.getYearsOfExperience() >= 20)) {
            premium = (1.00) * premium;
        } else if ((quote.getYearsOfExperience() < 50 )&& (quote.getYearsOfExperience() >= 25)) {
            premium = (0.82) * premium;
        }else{
            premium = (0.71) * premium;
        }

        //deductible

        if((quote.getDeductible()<1000000) && (quote.getDeductible()>=500000)){
            premium=(0.90)*premium;
        } else if ((quote.getDeductible()<2000000)&& (quote.getDeductible()>=1000000)){
            premium=(0.82)*premium;
        } else if ((quote.getDeductible()<3000000)&&(quote.getDeductible()>=2000000)) {
            premium=(0.70)*premium;
        } else if ((quote.getDeductible()<4000000)&&(quote.getDeductible()>=3000000)) {
            premium=(0.60)*premium;
        } else if ((quote.getDeductible()<5000000)&&(quote.getDeductible()>=4000000)) {
            premium=(0.55)*premium;
        }


//        //additionalCoverages

//        premium =premium*(0.2);


        if(previousClaims==0){
            premium=premium*(1.0);
        } else if (previousClaims==1) {
            premium=premium*(1.2);
        } else if (previousClaims==2) {
            premium=premium*(1.4);
        } else if (previousClaims==3) {
            premium=premium*(1.6);
        }


        //coverageLimits

        if(quote.getCoverageLimits().equals("1:1")){
            premium=premium*(1.9);

        } else if (quote.getCoverageLimits().equals("1:2")) {
            premium=premium*(1.6);

        } else if (quote.getCoverageLimits().equals("1:3")) {
            premium=premium*(1.3);
        } else if (quote.getCoverageLimits().equals("1:4")) {
            premium=premium*(1);

        }



        return BigDecimal.valueOf(premium);
    }


}
