package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.CustomerDTO;

public interface ContactService {
    CustomerEntity addContact(CustomerDTO customerDTO) throws ServiceException;
}
