package com.example.springboottutorial.domain.repository;

import java.util.List;

import com.example.springboottutorial.domain.model.Country;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CountryRepository {

    @Select("SELECT * FROM country LIMIT 10;")
    public List<Country> findAllCountries();
}