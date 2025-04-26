package com.istech.operations;

import com.istech.operations.models.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {
    Page<Operation> findByAccount_IdOrderByCreatedAtDesc(UUID accountId, Pageable pageable);

    @Query("SELECT COUNT(o.id) FROM Operation o WHERE o.account.id = :aid")
    Long findByAccount_IdCount(@Param("aid") UUID accountId);
}
