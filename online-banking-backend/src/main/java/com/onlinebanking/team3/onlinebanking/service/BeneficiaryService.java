package com.onlinebanking.team3.onlinebanking.service;

import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {
    @Autowired
    BeneficiaryRepository beneficiaryRepository;

    public Beneficiary createBeneficiary(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }

    public List<Beneficiary> listAll() {
        return beneficiaryRepository.findAll();
    }

    public List<Beneficiary> getBeneficiaryByUser(Long uid) {
        return beneficiaryRepository.findAllByUserId(uid);
    }


}
