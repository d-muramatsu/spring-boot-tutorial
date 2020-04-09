package com.example.springboottutorial.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Country {
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