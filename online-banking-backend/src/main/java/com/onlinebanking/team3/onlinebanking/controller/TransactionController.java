package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.Transaction;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Transaction> createTransaction(@PathVariable Long fromId,
                                                         @PathVariable Long toId,
                                                         @RequestBody Transaction transaction) {
        Account fromAccount = accountService.getAccountById(fromId);
        Account toAccount = accountService.getAccountById(toId);

        transaction.setToAccount(toAccount);
        transaction.setFromAccount(fromAccount);

        Transaction t = transactionService.createTransaction(transaction);

        return ResponseEntity.ok(t);


    }
}
