package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.config.AdminAuthentication;
import com.onlinebanking.team3.onlinebanking.exception.ResourceNotFoundException;
import com.onlinebanking.team3.onlinebanking.exception.UnauthorizedAccessException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.AddressService;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
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

            System.out.println(user.toString());

            Address residentialAddress = user.getResidentialAddress();
            Address permanentAddress = user.getPermanentAddress();

            Address registeredResidentialAddress = addressService.saveAddress(residentialAddress);
            Address registeredPermanentAddress = addressService.saveAddress(permanentAddress);

            User registeredUser = uService.registerUser(user);

            Address mailingAddress = user.getResidentialAddress();
            Account account = new Account("Savings Account", "NX1845", mailingAddress, 1000, true, user);

            Account registeredAccount = accountService.createAccount(account);

            if (registeredUser != null)
                return ResponseEntity.ok("Registration Successful");
            else
                return ResponseEntity.badRequest().body("Registration Failed");

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Error Occurred: " + e.getMessage().substring(0, 100));
        }
    }

    @PostMapping(value = "/loginUser")
    public ResponseEntity<User> loginUser(@RequestParam String phoneNumber, @RequestParam String password) {

        try {
            User u = uService.findUserByPhoneNumber(phoneNumber)
                    .orElseThrow(() -> new ResourceNotFoundException("No User Enrolled With This Number ::"));

            Base64.Encoder encoder = Base64.getEncoder();
            String encodedString = encoder.encodeToString( // encrypt password in database field
                    password.getBytes(StandardCharsets.UTF_8));
            if (encodedString.equals(u.getLoginPassword())) {
                return ResponseEntity.ok(u);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PutMapping("/register")
    public ResponseEntity<String> registerInternetBanking(@RequestParam String emailId,
            @RequestParam Long accountNumber, @RequestParam String transactionPassword) {
        try {
            Account account = accountService.getAccountById(accountNumber);
            User user = uService.getUserById(account.getUser().getUid());

            if (account.getUser().getEmailId().equals(emailId)) {
                account.setTransactionPassword(transactionPassword);
                Account updatedAccount = accountService.createAccount(account);
                return ResponseEntity.ok("Registration Successful");
            } else {
                return ResponseEntity.badRequest().body("Account Number and Email Id mismatch");
            }
        	
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Error Occurred: "+e.getMessage());

		}
    }

    @GetMapping("/users/{uid}")
    public User getUserById(@PathVariable Long uid) {
        return uService.getUserById(uid);
    }

    @GetMapping("/users/phone/{ph}")
    public Optional<User> getUserByPhoneNumber(@PathVariable String ph) {
        Optional<User> user =  uService.findUserByPhoneNumber(ph);
        System.out.println("User = " + user.get());
        return user;
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
        try {
            User updated = uService.updateUser(userId, updatedUser);
            return ResponseEntity.ok(updated);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("users/{userId}/verify")
    public ResponseEntity<User> kycVerifyUser(@RequestHeader(name = "Authorization") String authentication,
            @PathVariable Long userId) {
        try {
            System.out.println("Inside KYC");
            AdminAuthentication.authenticateAdminCredentials(authentication);
            User updated = uService.kycVerifyUser(userId);

            return ResponseEntity.ok(updated);

        } catch (UnauthorizedAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    //

}
