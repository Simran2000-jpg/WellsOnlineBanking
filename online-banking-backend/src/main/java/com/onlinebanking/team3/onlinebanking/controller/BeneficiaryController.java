package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.User;
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

    @PostMapping("/beneficiaries/{userId}")
    public ResponseEntity<String> createBeneficiary(@PathVariable long userId, @RequestBody Beneficiary beneficiary) {
        try {
        	List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiaryByUser(userId);
        	for (Beneficiary beneficiaryItem : beneficiaries) {
        	    if (beneficiaryItem.getAccountNo().equals(beneficiary.getAccountNo())) {
        	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Beneficiary already exists");
        	    }
        	}
            User user = userService.getUserById(userId);

            beneficiary.setUser(user);
            System.out.println(user.getAadharNumber());

            Beneficiary b = beneficiaryService.createBeneficiary(beneficiary);
            System.out.println(b.getIfscCode());

            return ResponseEntity.status(HttpStatus.CREATED).body("Created Beneficiary Successfully");
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
