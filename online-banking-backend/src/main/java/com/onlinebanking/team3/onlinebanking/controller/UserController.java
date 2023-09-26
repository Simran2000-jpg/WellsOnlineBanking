package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.config.AdminAuthentication;
import com.onlinebanking.team3.onlinebanking.exception.ResourceNotFoundException;
import com.onlinebanking.team3.onlinebanking.exception.UnauthorizedAccessException;
import com.onlinebanking.team3.onlinebanking.exception.UserNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.AddressService;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
// @CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService uService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/welcome")
    public String demo() {
        return "Welcome User";
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@Validated @RequestBody User user) {
        try {

            Address residentialAddress = user.getResidentialAddress();
            Address permanentAddress = user.getPermanentAddress();

            Address registeredResidentialAddress = addressService.saveAddress(residentialAddress);
            Address registeredPermanentAddress = addressService.saveAddress(permanentAddress);

            User registeredUser = uService.registerUser(user);

            Address mailingAddress = user.getResidentialAddress();
            Account account = new Account("NX1845", mailingAddress, 1000, true, user);

            Account registeredAccount = accountService.createAccount(account);

            if (registeredUser != null)
                return ResponseEntity.ok("Registration Successful");
            else
                return ResponseEntity.badRequest().body("Registration Failed");

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Error Occurred: " + e.getMessage());

        }
    }

    @PostMapping(value = "/loginUser")
    public Boolean loginUser(@RequestParam String phoneNumber, @RequestParam String password)
            throws ResourceNotFoundException {
        Boolean isLoggedIn = false;

        User u = uService.loginUser(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("No User Enrolled With This Number ::"));

        if (phoneNumber.equals(u.getPhoneNumber()) && password.equals(u.getLoginPassword())) {
            isLoggedIn = true;
        }

        return isLoggedIn;
    }

    @PutMapping("/register")
    public ResponseEntity<String> registerInternetBanking(@RequestParam String emailId,
            @RequestParam Long accountNumber, @RequestParam String loginPassword,
            @RequestParam String transactionPassword) {
        try {
            Account account = accountService.getAccountById(accountNumber);
            User user = uService.getUserById(account.getUser().getUid());

            if (account.getUser().getEmailId().equals(emailId)) {
                user.setLoginPassword(loginPassword);
                User updatedUser = uService.registerUser(user);

                account.setTransactionPassword(transactionPassword);
                Account updatedAccount = accountService.createAccount(account);
                return ResponseEntity.ok("Registration Successful");
            } else {
                return ResponseEntity.badRequest().body("Account Number and Email Id mismatch");
            }

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Error Occurred: " + e.getMessage());

        }
    }

    // @PostMapping("/{userId}/beneficiaries")
    // public Beneficiary createBeneficiary(@PathVariable Long userId, @RequestBody
    // Beneficiary beneficiary) {
    // User user = uService.getUserById(userId);
    // beneficiary.setUser(user);
    // return beneficiaryService.createBeneficiary(beneficiary);
    // }

    @GetMapping("/users/{uid}")
    public User getUserById(@PathVariable Long uid) {
        User u = uService.getUserById(uid);
        return u;
        //
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader(name = "Authorization") String authentication) {
        try {
            AdminAuthentication.authenticateAdminCredentials(authentication);
            return ResponseEntity.ok(uService.listAll());

        } catch (UnauthorizedAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    // UPDATE
    @PutMapping("findUser/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User updated = uService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(updated);
    }

    //

}
