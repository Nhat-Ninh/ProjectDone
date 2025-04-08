package com.javaweb.service.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.Status;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerEntity addContact(CustomerDTO customerDTO)throws ServiceException {
        CustomerEntity newCustomer = CustomerEntity.builder()
                .fullName(customerDTO.getFullName())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getCustomerPhone())
                .demand(customerDTO.getDemand())
                .status("CHUA_XU_LY")
                .isActive(1)
                .build();

        customerRepository.save(newCustomer);
        return null;
    }
}
