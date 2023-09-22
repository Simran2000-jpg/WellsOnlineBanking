package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.exception.ResourceNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.AddressService;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.UserService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

            Address residentialAddress = user.getResidentialAddress();
            Address permanentAddress = user.getPermanentAddress();

            Address registeredResidentialAddress = addressService.saveAddress(residentialAddress);
            Address registeredPermanentAddress = addressService.saveAddress(permanentAddress);

            User registeredUser = uService.registerUser(user);

            Address mailingAddress = user.getResidentialAddress();
            Account account = new Account("NX1845", mailingAddress, 0, user);

            Account registeredAccount = accountService.createAccount(account);

            if(registeredUser!=null)
                return ResponseEntity.ok("Registration Successful");
            else
                return ResponseEntity.badRequest().body("Registration Failed");

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Error Occurred: "+e.getMessage());

        }
    }


    @PostMapping(value = "/loginUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean loginUser(@Validated @RequestBody User user) throws ResourceNotFoundException {
        Boolean isLoggedIn = false;
        String phone_number = user.getPhoneNumber();
        String login_password = user.getLoginPassword();

        System.out.println(phone_number);
        System.out.println(login_password);

        User u = uService.loginUser(phone_number).orElseThrow(() ->
                new ResourceNotFoundException("No User Enrolled With This Number ::"));

        if(phone_number.equals(u.getPhoneNumber()) && login_password.equals(u.getLoginPassword())) {
            isLoggedIn = true;
        }

        return isLoggedIn;
    }
    
    @PutMapping("/register")
    public ResponseEntity<String> registerInternetBanking(@RequestParam String emailId, @RequestParam Long accountNumber, @RequestParam String loginPassword, @RequestParam String transactionPassword) {
    	try {
        	Account account = accountService.getAccountById(accountNumber);
        	User user = uService.getUserById(account.getUser().getUid());
        	
        	if(account.getUser().getEmailId().equals(emailId)) {
        		user.setLoginPassword(loginPassword);
        		User updatedUser = uService.registerUser(user);
        		
        		account.setTransactionPassword(transactionPassword);
        		Account updatedAccount = accountService.createAccount(account);
                return ResponseEntity.ok("Registration Successful");
        	}
            else {
                return ResponseEntity.badRequest().body("Account Number and Email Id mismatch");
            }
        	
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Error Occurred: "+e.getMessage());

		}
    }



//    @PostMapping("/{userId}/beneficiaries")
//    public Beneficiary createBeneficiary(@PathVariable Long userId, @RequestBody Beneficiary beneficiary) {
//        User user = uService.getUserById(userId);
//        beneficiary.setUser(user);
//        return beneficiaryService.createBeneficiary(beneficiary);
//    }

    @GetMapping("/users/{uid}")
    public User getUserById(@PathVariable Long uid) {
        User u = uService.getUserById(uid);
        return u;
        //
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        try {
            return uService.listAll();

        } catch (Exception e) {
            System.out.println("Fail");
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

}
