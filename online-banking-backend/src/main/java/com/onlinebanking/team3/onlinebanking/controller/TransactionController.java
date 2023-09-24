package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.exception.TransactionNotFoundException;
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
                                                         @RequestParam double amount,
                                                         @RequestParam String remarks,
                                                         @RequestParam String transactionPassword) {
//        Account fromAccount = accountService.getAccountById(fromId);
//        Account toAccount = accountService.getAccountById(toId);
//
//        transaction.setToAccount(toAccount);
//        transaction.setFromAccount(fromAccount);
//
//        Transaction t = transactionService.createTransaction(transaction);
//
//        return ResponseEntity.ok(t);
//        double amount = transaction.getAmount();

        return transactionService.transferFunds(fromId,toId,amount,remarks,transactionPassword);



    }



    @GetMapping("transactions/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//
    @GetMapping("/transactions/accounts/{accountNo}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable Long accountNo) {
        List<Transaction> transactions = transactionService.getTransactionsByAccount(accountNo);
        return ResponseEntity.ok(transactions);
    }


    @GetMapping("/transactions/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    //update
    @PutMapping("/{transactionId}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable Long transactionId,
            @RequestBody Transaction updatedTransaction
    ) {
        Transaction updated = transactionService.updateTransaction(transactionId, updatedTransaction);
        return ResponseEntity.ok(updated);
    }

    //delete

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        try {
            transactionService.deleteTransaction(transactionId);
            return ResponseEntity.noContent().build();
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
