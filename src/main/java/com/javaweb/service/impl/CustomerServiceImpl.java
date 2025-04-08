package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;
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
        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(id);
        if (customerEntity == null) {
            throw new ServiceException("Customer Not Found");
        }
        CustomerDTO customerDTO = modelMapper.map(customerRepository.findCustomerEntityById(id), CustomerDTO.class);

        return customerDTO;
    }

    @Override
    public CustomerEntity createOrUpdateCustomer(CustomerDTO customerDTO) throws ServiceException {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        customerEntity.setIsActive(1);
        if(customerDTO.getId()!= null){
            CustomerEntity customerEntity1 = customerRepository.findCustomerEntityById(customerDTO.getId());
            customerEntity.setStaffs(customerEntity1.getStaffs());
        }
        customerRepository.save(customerEntity);
        return null;
    }

    @Override
    public void deleteById(List<Long> ids) {
        List<CustomerEntity> customerEntities = customerRepository.findAllById(ids);
        for (CustomerEntity customerEntity : customerEntities) {
            customerEntity.setIsActive(0);
        }
        customerRepository.saveAll(customerEntities);
    }

    @Override
    public ResponseDTO findStaffByCustomerId(Long customerId) {
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(customerId);
        List<UserEntity> staffss = customerEntity.getStaffs();
        List<StaffResponseDTO> staffResponseDTOs = new ArrayList<>(); // danh sach nhan vien tra ra
        for(UserEntity staff : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(staff.getId());
            staffResponseDTO.setFullName(staff.getFullName());
            if(staffss.contains(staff)){
                staffResponseDTO.setChecked("checked");
            }
            else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOs.add(staffResponseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOs);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public boolean isStaffOfCustomer(Long staffId, Long customerId) {
        UserEntity userEntity = userRepository.findById(staffId).get();
        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(customerId);
        if(customerEntity.getStaffs().contains(userEntity)){
            return true;
        }
        else {
            return false;
        }
    }
}
