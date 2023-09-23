package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.Transaction;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Autowired
    BeneficiaryService beneficiaryService;

    @PostMapping("/transactions/{fromId}/{toId}")
    public Transaction createTransaction(@PathVariable Long fromId,
                                                         @PathVariable Long toId,
                                                         @RequestBody Transaction transaction) {
//        Account fromAccount = accountService.getAccountById(fromId);
//        Account toAccount = accountService.getAccountById(toId);
//
//        transaction.setToAccount(toAccount);
//        transaction.setFromAccount(fromAccount);
//
//        Transaction t = transactionService.createTransaction(transaction);
//
//        return ResponseEntity.ok(t);
        double amount = transaction.getAmount();

        return transactionService.transferFunds(fromId,toId,amount);



    }

//    @PostMapping("/transfer")
//    public ResponseEntity<Transaction> transferFunds(@RequestBody TransferRequest request) {
//        // Perform validation, authorization, and error handling as needed
//        try {
//            Transaction transaction = transactionService.transferFunds(
//                    request.getFromAccountId(),
//                    request.getToAccountId(),
//                    request.getAmount()
//            );
//            return ResponseEntity.ok(transaction);
//        } catch (TransactionException e) {
//            // Handle transaction-related exceptions
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

//    @GetMapping("/{transactionId}")
//    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transactionId) {
//        Transaction transaction = transactionService.getTransactionById(transactionId);
//        if (transaction != null) {
//            return ResponseEntity.ok(transaction);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable Long userId) {
//        List<Transaction> transactions = transactionService.getUserTransactions(userId);
//        return ResponseEntity.ok(transactions);
//    }


    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
