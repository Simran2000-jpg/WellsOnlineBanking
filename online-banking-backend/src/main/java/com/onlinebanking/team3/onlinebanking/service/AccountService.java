package com.onlinebanking.team3.onlinebanking.service;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

//    public Optional<Account> getAccountByAccountNo(Long accountNo) {
//        return accountRepository.findById(accountNo);
//    }


//    public List<Account> getUserAccounts(Long uid) {
//        return accountRepository.findByUserId(uid);
//    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + accountId));
    }


    public List<Account> listAll() {
        return accountRepository.findAll();
    }


}
