package com.onlinebanking.team3.onlinebanking.service;

import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserDetailsService {
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
    
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }

//    public User getUser(String userId) {
//         return userRepository.findOne(userId);
//    }

        @Override
        public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
            // Load the user by their phone number from the database
            Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
            
            if (user == null) {
                throw new UsernameNotFoundException("User not found with phone number: " + phoneNumber);
            }
            
            // You can customize the UserDetails implementation as needed
            // For simplicity, we'll use the built-in User class
//            return new UserDetails("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
            
            return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getPhoneNumber())
                .password(user.get().getLoginPassword()) // You should load the hashed password from the database // Add user roles/authorities here if needed
                .build();
        }
    }

