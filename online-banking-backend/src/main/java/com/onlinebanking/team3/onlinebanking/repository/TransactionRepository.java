package com.onlinebanking.team3.onlinebanking.repository;

import com.onlinebanking.team3.onlinebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("Select t From Transaction t Where t.fromAccount.id = :accountNo OR t.toAccount.id = :accountNo")
    List<Transaction> findTransactionsByAccountNo(Long accountNo);
//    List<Transaction> findByUserId(Long userId);
}
