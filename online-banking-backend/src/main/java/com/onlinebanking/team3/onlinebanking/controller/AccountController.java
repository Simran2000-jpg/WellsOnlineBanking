package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/{userId}/accounts")
    public Account createAccount(@PathVariable Long userId, @RequestBody Account account) {
        User user = userService.getUserById(userId);
        account.setUser(user);
        return accountService.createAccount(account);
    }

    @GetMapping("/accounts/{accountNo}")
    public Account getParticularAccount(@PathVariable Long accountNo) {
        Account account = accountService.getAccountById(accountNo);
        return account;
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


}
