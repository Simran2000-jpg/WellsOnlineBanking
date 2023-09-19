package com.onlinebanking.team3.onlinebanking.service;

import com.onlinebanking.team3.onlinebanking.exception.InsufficientBalanceException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.Transaction;
import com.onlinebanking.team3.onlinebanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

//    @Autowired
//    AccountService accountService;
//
//    @Autowired
//    BeneficiaryService beneficiaryService;

    public  Transaction createTransaction(Transaction transaction) {
//    public Transaction createTransaction(Long fromAccountId, Long toBeneficiaryId, BigDecimal amount) {
        // Retrieve the account and beneficiary
//        Account fromAccount = accountService.getAccountById(fromAccountId);
//        Beneficiary toBeneficiary = beneficiaryService.getBeneficiaryById(toBeneficiaryId);
//
//        // Check if the 'fromAccount' has enough balance for the transaction
//        BigDecimal fromAccountBalance = (fromAccount.getBalance());
//        if (fromAccountBalance.compareTo(amount) < 0) {
//            throw new InsufficientBalanceException("Insufficient balance in the 'fromAccount' for this transaction.");
//        }
//
//        Transaction transaction = new Transaction();
//        transaction.setTransactionDateTime(LocalDateTime.now());
//        transaction.setAmount(amount);
//        transaction.setFromAccount(fromAccount);
//        transaction.setToBeneficiary(toBeneficiary);
//
//        // Update account balances
//        fromAccount.setBalance(fromAccountBalance.subtract(amount));
//        accountService.updateAccount(fromAccount);

        // Save the transaction to the database
        return transactionRepository.save(transaction);
    }


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    }
