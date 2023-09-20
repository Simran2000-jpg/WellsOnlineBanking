package com.onlinebanking.team3.onlinebanking.repository;

import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {
//    List<Beneficiary> findByUserId(Long userId);
    @Query("SELECT b FROM Beneficiary b WHERE b.user.id=:uid")
    List<Beneficiary> findAllByUserId(
             Long uid
    );
}
