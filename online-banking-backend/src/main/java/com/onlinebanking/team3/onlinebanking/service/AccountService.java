package com.onlinebanking.team3.onlinebanking.service;

import com.onlinebanking.team3.onlinebanking.exception.UserNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.AccountRepository;
import com.onlinebanking.team3.onlinebanking.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + accountId));
    }

    public List<Account> getAccountsByUser(Long uid) {
        return accountRepository.findActiveAccountsForUser(uid);
    }

    public List<Account> listAll() {
        return accountRepository.findAll();
    }

    public void updateAccount(Account fromAccount) {
    }

    public List<Account> getActiveAccountsForUser(Long userId) {
        return accountRepository.findActiveAccountsForUser(userId);
    }

    public Account addNewAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID "+userId + " not found"));
        Account account = new Account("NX1845", user.getResidentialAddress(), 1000, true, user);

        // newAccount.setMailingAddress(mailingAddress);
        return accountRepository.save(account);

    }
}