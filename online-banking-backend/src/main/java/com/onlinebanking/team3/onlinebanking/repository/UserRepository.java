package com.onlinebanking.team3.onlinebanking.repository;

import com.onlinebanking.team3.onlinebanking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByPhoneNumber(String phoneNumber);
//    User findById(Long id);
    public Optional<User> findById(Long id);
//    List<User> findAll();
}
