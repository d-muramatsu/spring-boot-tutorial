package com.example.springboottutorial.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.springboottutorial.api.resource.CountryOutputResource;
import com.example.springboottutorial.domain.model.Country;
import com.example.springboottutorial.domain.service.CountryService;
import com.github.dozermapper.core.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("countries")
public class CountryController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private CountryService service;

    @GetMapping
    public List<CountryOutputResource> getList() {
        List<CountryOutputResource> outputResource = new ArrayList<>();

        List<Country> countries = service.getAllCountries();

        countries.forEach(country -> outputResource.add(mapper.map(country, CountryOutputResource.class)));

        return outputResource;
    }

}