package com.onlinebanking.team3.onlinebanking.service;

import com.onlinebanking.team3.onlinebanking.exception.UserNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Optional<User> findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }

    public User updateUser(Long userId, User updatedUser) {
        // Find the existing user by ID
        Optional<User> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Update the user details
            // You can update fields like name, email, etc.
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setFatherName(updatedUser.getFatherName());
            existingUser.setGender(updatedUser.getGender());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setMiddleName(updatedUser.getMiddleName());
            existingUser.setOccupation(updatedUser.getOccupation());
            existingUser.setSourceOfIncome(updatedUser.getSourceOfIncome());
            existingUser.setLoginPassword(updatedUser.getLoginPassword());
            existingUser.setEmailId(updatedUser.getEmailId());


            // Save the updated user
            return userRepository.save(existingUser);
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }
    public User kycVerifyUser(Long userId) {
        // Find the existing user by ID
        Optional<User> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            existingUser.setKyc(!existingUser.getKyc());


            // Save the updated user
            return userRepository.save(existingUser);
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    //delete 
    public void deleteUser(Long userId) {
        // Find the existing user by ID
        Optional<User> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isPresent()) {
            // User found, delete them
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }
    
}
