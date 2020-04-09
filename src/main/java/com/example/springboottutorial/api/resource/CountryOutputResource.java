package com.example.springboottutorial.api.resource;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CountryOutputResource {
    private String code;
    private String name;
    private String continent;
    private String region;
    private BigDecimal surfaceArea;
    private int indepYear;
    private int population;
    private BigDecimal lifeExpectancy;
    private BigDecimal gnp;
    private BigDecimal gnpOld;
    private String localName;
    private String governmentForm;
    private String headOfState;
    private int capital;
    private String code2;

}