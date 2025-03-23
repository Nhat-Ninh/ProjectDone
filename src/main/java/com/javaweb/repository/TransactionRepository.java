package com.javaweb.repository;

import com.javaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    public TransactionEntity findTransactionById(Long id);
    public void deleteTransactionByIdAndCustomerEntity_Id(Long id, Long customerId);
    public List<TransactionEntity> findByCustomerEntity_IdAndCode(Long id,String code);
}
