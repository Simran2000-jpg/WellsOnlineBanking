package com.onlinebanking.team3.onlinebanking.service;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.repository.BeneficiaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

//    public Optional<Beneficiary> getById(Long bid) {
//        return beneficiaryRepository.findById(bid);
//    }
public Beneficiary getBeneficiaryById(Long beneficiaryId) {
    return beneficiaryRepository.findById(beneficiaryId)
            .orElseThrow(() -> new EntityNotFoundException("Beneficiary not found with ID: " + beneficiaryId));
}
    public List<Beneficiary> getBeneficiaryByUser(Long uid) {
        return beneficiaryRepository.findAllByUserId(uid);
    }


}
