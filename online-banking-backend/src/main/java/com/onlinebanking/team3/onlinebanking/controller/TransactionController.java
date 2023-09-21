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

    @PostMapping("/transactions/{aid}/{bid}")
    public ResponseEntity<Transaction> createTransaction(@PathVariable Long aid,
                                                         @PathVariable Long bid,
                                                         @RequestBody Transaction transaction) {
        Account account = accountService.getAccountById(aid);
        Beneficiary beneficiary = beneficiaryService.getBeneficiaryById(bid);

        transaction.setAccount(account);
        transaction.setBeneficiary(beneficiary);

        Transaction t = transactionService.createTransaction(transaction);

        return ResponseEntity.ok(t);


    }
}
