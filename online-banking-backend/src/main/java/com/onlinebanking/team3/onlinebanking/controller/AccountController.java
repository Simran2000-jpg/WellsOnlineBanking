package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.config.AdminAuthentication;
import com.onlinebanking.team3.onlinebanking.exception.UnauthorizedAccessException;
import com.onlinebanking.team3.onlinebanking.exception.UserNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Transaction;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.TransactionService;
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

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/addNewAccount/{userId}")
    public ResponseEntity<Account> addNewAccount(@PathVariable Long userId) {

        try{
            Account newAccount = accountService.addNewAccount(userId);
            return ResponseEntity.status((HttpStatus.CREATED)).body(newAccount);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/accounts/{accountNo}")
    public ResponseEntity<Account> getParticularAccount(@PathVariable Long accountNo) {
        try {
            Account account = accountService.getAccountById(accountNo);
            return ResponseEntity.ok(account);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/accounts/user/{uid}")
    public ResponseEntity<List<Account>> getUserAccounts(@PathVariable Long uid) {
        try {
            List<Account> accounts = accountService.getAccountsByUser(uid);
            return ResponseEntity.ok(accounts);
        }
        catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/transactions/accounts/user/{uid}")
    public ResponseEntity<List<Transaction>> getUsersFirstAccountTransactions(@PathVariable Long uid) {
        try {
            List<Account> accounts = accountService.getAccountsByUser(uid);
            List<Transaction> transactions =  transactionService.getTransactionsByAccount(accounts.get(0).getAccountNo());
            return ResponseEntity.ok(transactions);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
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
