package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(user);

        return beneficiaryService.createBeneficiary(beneficiary);
    }
}
