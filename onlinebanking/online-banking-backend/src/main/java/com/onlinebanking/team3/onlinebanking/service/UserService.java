package com.onlinebanking.team3.onlinebanking.service;

import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User u) {
        return userRepository.save(u);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

//    public User getUser(String userId) {
//         return userRepository.findOne(userId);
//    }
}
