package com.onlinebanking.team3.onlinebanking.repository;

import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {
//    List<Beneficiary> findByUserId(Long userId);
}