package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeneficiaryController {
    @Autowired
    BeneficiaryService beneficiaryService;

    @Autowired
    UserService userService;

    @PostMapping("/{uid}/ben")
    public String demo(@PathVariable Long uid, @RequestBody Beneficiary beneficiary) {
        User user = userService.getUserById(uid);
        beneficiary.setUser(user);
       beneficiaryService.createBeneficiary(beneficiary);
        return "Welcome "+uid;
    }



    @PostMapping("/{userId}/beneficiaries")
    public Beneficiary createBeneficiary(@PathVariable Long userId, @RequestBody Beneficiary beneficiary) {
        User user = userService.getUserById(userId);
        beneficiary.setUser(user);
        return beneficiaryService.createBeneficiary(beneficiary);
    }
}
