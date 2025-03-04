package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<CustomerSearchResponse> findAll (CustomerSearchRequest request , Pageable pageable);

    CustomerDTO findCustomerById(Long id) throws ServiceException;

    CustomerEntity createOrUpdateCustomer(CustomerDTO customerDTO) throws ServiceException;

    void deleteById(List<Long> ids);

    ResponseDTO findStaffByCustomerId(Long customerId);

    int countCustomer (CustomerSearchRequest customerSearchRequest);

    boolean isStaffOfCustomer (Long staffId, Long customerId);
}
