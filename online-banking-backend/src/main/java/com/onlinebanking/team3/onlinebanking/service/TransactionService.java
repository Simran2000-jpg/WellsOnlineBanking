package com.onlinebanking.team3.onlinebanking.service;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Transaction;
import com.onlinebanking.team3.onlinebanking.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    BeneficiaryService beneficiaryService;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction transferFunds(Long fromAccountId, Long toAccountId, double amount) {
        // Retrieve sender and recipient accounts
        Account fromAccount = accountService.getAccountById(fromAccountId);
        Account toAccount = accountService.getAccountById(toAccountId);

        // Check if the sender has enough balance
        double fromAccountBalance = fromAccount.getBalance();
        if (fromAccountBalance-amount < 0) {
            throw new TransactionException("Insufficient balance in the sender's account.");
        }

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionDateTime(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);

        // Update account balances
        fromAccount.setBalance(fromAccountBalance-amount);
        toAccount.setBalance(toAccount.getBalance()+amount);

        // Save the transaction and update account balances
        transactionRepository.save(transaction);
        accountService.updateAccount(fromAccount);
        accountService.updateAccount(toAccount);

        return transaction;
    }
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

//    public Transaction getTransactionById(Long transactionId) {
//        return transactionRepository.findById(transactionId).orElse(null);
//    }
//
//    public List<Transaction> getUserTransactions(Long userId) {
//        return transactionRepository.findByUserId(userId);
//    }

}
