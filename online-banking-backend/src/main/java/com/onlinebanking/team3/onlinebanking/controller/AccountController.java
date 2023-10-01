package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.config.AdminAuthentication;
import com.onlinebanking.team3.onlinebanking.exception.UnauthorizedAccessException;
import com.onlinebanking.team3.onlinebanking.exception.UserNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.Transaction;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.AddressService;
import com.onlinebanking.team3.onlinebanking.service.TransactionService;
import com.onlinebanking.team3.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    private AddressService addressService;

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

    @PostMapping("/createAnotherNewAccount/{userId}")
    public ResponseEntity<String> createAnotherNewAccount(@RequestBody Address address, @RequestParam String accountType, @RequestParam String transactionPassword, @PathVariable Long userId) {
        try{
            Address newAddress = new Address(address.getAddress(), address.getCity(), address.getState(), address.getPincode());
            Address registeredAddress = addressService.saveAddress(address);

            User user = userService.getUserById(userId);
            Account newAccount = new Account(accountType, "NX1845", registeredAddress, 1000, true, user);
            newAccount.setTransactionPassword(transactionPassword);
            Account registeredAccount = accountService.createAccount(newAccount);

            if (registeredAccount != null)
                return ResponseEntity.ok("Account creation Successful");
            else
                return ResponseEntity.badRequest().body("Account creation Failed");
        }catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Error Occurred: " + e.getMessage().substring(0, 100));
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

    @PutMapping("/account/{accountId}/withdraw")
    public ResponseEntity<?> withdrawMoneyFromAccount(@RequestHeader(name = "Authorization") String authentication,
                                                      @PathVariable Long accountId,
                                                      @RequestParam double amount,
                                                      @RequestParam String transactionPassword){
        System.out.println("inside WithdrawMoneyFromAcoount: AccountController");
        try{
            AdminAuthentication.authenticateAdminCredentials(authentication);
            if(!transactionPassword.equals("adminTransaction")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Transaction Password not correct");
            }
            Account account = accountService.getAccountById(accountId);
            if((account.getBalance() - amount) < 0){
                return ResponseEntity.badRequest().body("Not enough money to withdraw");
            }
            transactionService.withdrawMoney(account, amount);
            return ResponseEntity.ok("Money Withdraw Successful");
        } catch (UnauthorizedAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The user is not authorized to withdraw money");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @PutMapping("/account/{accountId}/deposit")
    public ResponseEntity<?> depositMoneyToAccount(@RequestHeader(name = "Authorization") String authentication,
                                                      @PathVariable Long accountId,
                                                      @RequestParam String transactionPassword,
                                                      @RequestParam Double amount){
        try{
            AdminAuthentication.authenticateAdminCredentials(authentication);
            if(!transactionPassword.equals("adminTransaction")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Transaction Password not correct");
            }
            Account account = accountService.getAccountById(accountId);
            transactionService.depositMoney(account, amount);
            return ResponseEntity.ok("Money Deposit Successful");
        } catch (UnauthorizedAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The user is not authorized to deposit money");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }
}
