package com.onlinebanking.team3.onlinebanking.repository;

import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {
//    List<Beneficiary> findByUserId(Long userId);
    @Query("SELECT * FROM beneficiary WHERE beneficiary.uid=:uid")
    List<Beneficiary> findBeneficiaryByUser(
            @Param("uid") Long uid
    );
}
