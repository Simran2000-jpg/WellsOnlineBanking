package com.onlinebanking.team3.onlinebanking.service;

import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User u) {
        return userRepository.save(u);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public Optional<User> loginUser(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

//    public User getUser(String userId) {
//         return userRepository.findOne(userId);
//    }
}