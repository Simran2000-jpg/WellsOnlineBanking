package com.onlinebanking.team3.onlinebanking.controller;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.Transaction;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Autowired
    BeneficiaryService beneficiaryService;

    @PostMapping("/transactions")
    public String demo() {
        return "Welcome User";
    }


    @PostMapping("/transactions/{fromAccountId}/{toBeneficiaryId}")
    public String createTransaction(@PathVariable Long fromAccountId, @PathVariable Long toBeneficiaryId, @RequestBody Transaction transaction) {
        Account account = accountService.getAccountById(fromAccountId);
        Beneficiary beneficiary = beneficiaryService.getBeneficiaryById(toBeneficiaryId);

        transaction.setFromAccount(account);


        transaction.setToBeneficiary(beneficiary);
        return fromAccountId+" "+toBeneficiaryId;

//        return transactionService.createTransaction(transaction);
    }

//    @PostMapping("/transactions/{fromAccountId}/{toBeneficiaryId}/")
//    public Transaction createTransaction(@PathVariable Long fromAccountId, @PathVariable Long toBeneficiaryId, @RequestBody Transaction transaction) {
//        Account account = accountService.getAccountById(fromAccountId);
//        Beneficiary beneficiary = beneficiaryService.getBeneficiaryById(toBeneficiaryId);
//
//        transaction.setFromAccount(account);
//        transaction.setToBeneficiary(beneficiary);
//
//        return transactionService.createTransaction(transaction);
//    }
}
