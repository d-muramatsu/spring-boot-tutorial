package com.example.springboottutorial.domain.repository;

import com.example.springboottutorial.domain.model.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {

    @Select("SELECT id, name FROM User WHERE id = #{id}")
    public User findById(String id);
}