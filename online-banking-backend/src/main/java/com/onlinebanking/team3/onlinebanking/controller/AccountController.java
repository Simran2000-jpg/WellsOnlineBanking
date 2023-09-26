package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.config.AdminAuthentication;
import com.onlinebanking.team3.onlinebanking.exception.UnauthorizedAccessException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    // @PostMapping("/{userId}/accounts")
    // public Account createAccount(@PathVariable Long userId, @RequestBody Account account) {
    //     User user = userService.getUserById(userId);
    //     account.setUser(user);
    //     return accountService.createAccount(account);
    // }

    @PutMapping("/addNewAccount/{userId}")
    public Account addNewAccount(@PathVariable Long userId) {

        return accountService.addNewAccount(userId);
    }

    @GetMapping("/accounts/{accountNo}")
    public Account getParticularAccount(@PathVariable Long accountNo) {
        Account account = accountService.getAccountById(accountNo);
        return account;
    }


    @GetMapping("/user/{uid}/accounts")
    public ResponseEntity<List<Account>> getAccountsForUserId(@PathVariable Long uid){
        try {
            Optional<List<Account>> accounts = accountService.getAccountsForUser(uid);

            if (accounts.isPresent()) {
                return ResponseEntity.ok(accounts.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }



    }

//    @GetMapping("/accounts/{uid}")
//    public List<Account> getUserAccounts(@PathVariable Long uid) {
//        try {
//            return accountService.getUserAccounts(uid);
//        }
//
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        try {
            return accountService.listAll();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/active/{userId}")
    public ResponseEntity<List<Account>> getActiveAccountsForUser(@PathVariable Long userId) {
        List<Account> activeAccounts = accountService.getActiveAccountsForUser(userId);
        return ResponseEntity.ok(activeAccounts);
    }

    @PutMapping("/account/{accountId}/active")
    public ResponseEntity<Account> updateActiveState(@RequestHeader(name = "Authorization") String authentication,
                                                     @PathVariable Long accountId){
        try {
            System.out.println("Inside change active status");
            AdminAuthentication.authenticateAdminCredentials(authentication);
            Account updated = accountService.updateActiveStatus(accountId);

            return ResponseEntity.ok(updated);

        } catch (UnauthorizedAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
