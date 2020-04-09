package com.example.springboottutorial.domain.service;

import java.util.List;

import com.example.springboottutorial.domain.model.Country;
import com.example.springboottutorial.domain.repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repository;

    @Override
    public List<Country> getAllCountries() {
        return repository.findAllCountries();
    }

}