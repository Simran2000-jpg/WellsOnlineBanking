package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @Autowired
    private AccountService accountService;

    @PostMapping("/beneficiaries/{userId}")
    public ResponseEntity<String> createBeneficiary(@PathVariable long userId, @RequestBody Beneficiary beneficiary) {
        try {
        	List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiaryByUser(userId);
        	for (Beneficiary beneficiaryItem : beneficiaries) {
        	    if (beneficiaryItem.getAccountNo().equals(beneficiary.getAccountNo())) {
        	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Beneficiary already exists");
        	    }
        	}

            if(beneficiary.getIfscCode().equals("NX1845")) {
                Account beneficiaryAccount = accountService.getAccountById(Long.parseLong(beneficiary.getAccountNo()));

                if(!(beneficiaryAccount.getTransactionPassword() == null)) {
                    User beneficiaryUser = userService.getUserById(beneficiaryAccount.getUser().getUid());

                    if (beneficiaryUser.getKyc()) {
                        User user = userService.getUserById(userId);
                        beneficiary.setUser(user);
                        Beneficiary b = beneficiaryService.createBeneficiary(beneficiary);

                        return ResponseEntity.status(HttpStatus.CREATED).body("Created Beneficiary Successfully");
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The beneficiary is not a verified user");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The beneficiary not registered for Internet Banking");
                }
            } else {
                User user = userService.getUserById(userId);
                beneficiary.setUser(user);
                Beneficiary b = beneficiaryService.createBeneficiary(beneficiary);

                return ResponseEntity.status(HttpStatus.CREATED).body("Created Beneficiary Successfully");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/beneficiaries")
    public ResponseEntity<List<Beneficiary>> getAllBeneficiaries(){
        try {
            List<Beneficiary> allBeneficiaries = beneficiaryService.listAll();
            return ResponseEntity.ok(allBeneficiaries);
        }

        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/beneficiaries/{uid}")
    public ResponseEntity<List<Beneficiary>> getAllBeneficiaryByUser(@PathVariable Long uid) {
//        return beneficiaryService.getBeneficiaryByUser(uid);
        try {
            List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiaryByUser(uid);
            return ResponseEntity.ok(beneficiaries);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
    
    @DeleteMapping("/beneficiaries/{bid}")
    public ResponseEntity<String> deleteBeneficiaryById(@PathVariable Long bid) {
        try {
            beneficiaryService.deleteBeneficiaryById(bid);
            return ResponseEntity.ok("Deleted Successfully");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
  }

}
