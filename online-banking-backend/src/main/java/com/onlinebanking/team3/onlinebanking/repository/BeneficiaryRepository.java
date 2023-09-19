package com.onlinebanking.team3.onlinebanking.repository;

import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {
    //
}
