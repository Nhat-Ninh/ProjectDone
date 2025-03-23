package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.DeleteRequest;

import java.util.List;

public interface TransactionService {
    TransactionEntity addOrUpdateTransaction(TransactionDTO transactionDTO) throws ServiceException;
    void deleteById(Long id, Long customerId) throws ServiceException;
    List<TransactionDTO> customerDdx (Long customerId) throws ServiceException;
    List<TransactionDTO> customerCskh (Long customerId) throws ServiceException;

}
