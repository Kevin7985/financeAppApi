package com.istech.accounts;

import com.istech.accounts.models.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("SELECT a FROM Account a " +
        "WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :q, '%'))"
    )
    Page<Account> searchAccounts(@Param("q") String q, Pageable page);

    @Query("SELECT COUNT(a.id) FROM Account a " +
        "WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :q, '%'))"
    )
    Long searchAccountsCount(@Param("q") String q);
}
