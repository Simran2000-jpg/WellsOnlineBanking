package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.exception.ResourceNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
//import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
//import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService uService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BeneficiaryService beneficiaryService;

    @GetMapping("/welcome")
    public String demo() {
        return "Welcome User";
    }



    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Validated @RequestBody User user) {
        try {
            Address address = user.getAddress();

            address.setUser(user);
            user.setAddress(address);

//
//            List<Account> accounts = user.getAccounts();
//
//            for(Account account:accounts) {
//                account.setUser(user);
//
//            }
//
//            user.setAccounts(accounts);


            User registeredUser = uService.registerUser(user);

            if(registeredUser!=null) {
                return ResponseEntity.ok("Registration Successful");
            }

            else {
                return ResponseEntity.badRequest().body("Registration Failed");
            }

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Error Occurred: "+e.getMessage());

        }
    }


    @PostMapping("/loginUser")
    public Boolean loginUser(@Validated @RequestBody User user) throws ResourceNotFoundException {
        Boolean isLoggedIn = false;
        String phone_number = user.getPhoneNumber();
        String login_password = user.getLoginPassword();

        User u = uService.loginUser(phone_number).orElseThrow(() ->
                new ResourceNotFoundException("No User Enrolled With This Number ::"));

        if(phone_number.equals(u.getPhoneNumber()) && login_password.equals(u.getLoginPassword())) {
            isLoggedIn = true;
        }

        return isLoggedIn;
    }

    @PostMapping("/{userId}/accounts")
    public Account createAccount(@PathVariable Long userId, @RequestBody Account account) {
        User user = uService.getUserById(userId);
        account.setUser(user);
        return accountService.createAccount(account);
    }

    @PostMapping("/{userId}/beneficiaries")
    public Beneficiary createBeneficiary(@PathVariable Long userId, @RequestBody Beneficiary beneficiary) {
        User user = uService.getUserById(userId);
        beneficiary.setUser(user);
        return beneficiaryService.createBeneficiary(beneficiary);
    }

    @GetMapping("/users")

    public List<User> getAllUsers() {
        try {
            return uService.listAll();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

}
