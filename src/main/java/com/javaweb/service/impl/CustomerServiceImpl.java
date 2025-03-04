package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Override
    public List<CustomerSearchResponse> findAll(CustomerSearchRequest params, Pageable pageable) {
        List<CustomerEntity> customerEntities = customerRepository.findAll(params,pageable);

        List<CustomerSearchResponse> results = new ArrayList<CustomerSearchResponse>();
        for (CustomerEntity it : customerEntities) {
            CustomerSearchResponse customerResponse = customerConverter.toCustomerResponse(it);
            results.add(customerResponse);
        }
        return results;
    }

    @Override
    public int countCustomer(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.countCustomer(customerSearchRequest);
    }

    @Override
    public CustomerDTO findCustomerById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public CustomerEntity createOrUpdateCustomer(CustomerDTO customerDTO) throws ServiceException {
        return null;
    }

    @Override
    public void deleteById(List<Long> ids) {

    }

    @Override
    public ResponseDTO findStaffByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public boolean isStaffOfCustomer(Long staffId, Long customerId) {
        return false;
    }
}
