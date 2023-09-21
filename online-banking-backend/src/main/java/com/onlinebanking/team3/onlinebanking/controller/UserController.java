package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.exception.ResourceNotFoundException;
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
import org.springframework.http.MediaType;
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




            User registeredUser = uService.registerUser(user);
            String ph = user.getPhoneNumber();
            System.out.println(ph);

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
