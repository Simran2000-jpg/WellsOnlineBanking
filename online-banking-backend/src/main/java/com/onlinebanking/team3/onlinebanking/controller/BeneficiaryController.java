package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BeneficiaryController {
    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private UserService userService;

    @PostMapping("/beneficiaries/{userId}")
    public Beneficiary createBeneficiary(@PathVariable long userId, @RequestBody Beneficiary beneficiary) {
        User user = userService.getUserById(userId);
        beneficiary.setUser(user);
        System.out.println(user.getAadharNumber());

        Beneficiary b = beneficiaryService.createBeneficiary(beneficiary);
        System.out.println(b.getIfscCode());

        return b;
    }

    @GetMapping("/beneficiaries")
    public List<Beneficiary> getAllBeneficiaries(){
        try {
            return beneficiaryService.listAll();
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/beneficiaries/{uid}")
    public ResponseEntity<List<Beneficiary>> getAllBeneficiaryByUser(@PathVariable Long uid) {
//        return beneficiaryService.getBeneficiaryByUser(uid);
        List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiaryByUser(uid);
        return ResponseEntity.ok(beneficiaries);
    }

}
