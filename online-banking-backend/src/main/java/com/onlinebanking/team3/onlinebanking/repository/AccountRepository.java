package com.onlinebanking.team3.onlinebanking.repository;

import com.onlinebanking.team3.onlinebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
      @Query("SELECT a FROM Account a WHERE a.user.id = :userId AND a.isActive = true")
      List<Account> findActiveAccountsForUser(@Param("userId") Long userId);

      @Query("SELECT a FROM Account a WHERE a.user.id = :userId")
      Optional<List<Account>> findAccountsForUser(@Param("userId") Long userId);
}
