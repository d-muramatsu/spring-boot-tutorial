package com.example.springboottutorial.domain.service;

import com.example.springboottutorial.domain.model.User;
import com.example.springboottutorial.domain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String id) {
        User user = new User();
        user.setId("hoge@hoge.jp");
        user.setName("ほげ太郎");
        return user;
        // return userRepository.findById(id);
    }

}