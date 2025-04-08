package com.javaweb.service.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.DeleteRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.TransactionService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public TransactionEntity addOrUpdateTransaction(TransactionDTO transactionDTO) throws ServiceException {
        CustomerEntity customerEntity = customerRepository.findCustomerEntityById(transactionDTO.getCustomerId());

        TransactionEntity transactionEntity;
        String createdBy = null;
        Date createdDate = null;

        if (transactionDTO.getId() != null) {
            // Nếu có ID, lấy dữ liệu cũ từ database
            transactionEntity = transactionRepository.findById(transactionDTO.getId())
                    .orElseThrow(() -> new ServiceException("Transaction not found"));

            // Lưu lại createdBy và createdDate trước khi mapping
            createdBy = transactionEntity.getCreatedBy();
            createdDate = transactionEntity.getCreatedDate();
        } else {
            // Nếu không có ID, tạo mới
            transactionEntity = new TransactionEntity();
            createdBy = SecurityUtils.getPrincipal().getFullName();
            createdDate = new Date();
        }

        // Cấu hình ModelMapper để không ghi đè giá trị null
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(transactionDTO, transactionEntity);

        // Gán lại các giá trị ban đầu để tránh mất dữ liệu
        transactionEntity.setCreatedBy(createdBy);
        transactionEntity.setCreatedDate(createdDate);

        // Gán CustomerEntity
        transactionEntity.setCustomerEntity(customerEntity);

        // Cập nhật người sửa và ngày sửa
        transactionEntity.setModifiedBy(SecurityUtils.getPrincipal().getFullName());
        transactionEntity.setModifiedDate(new Date());

        //Update staffid
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<UserEntity> staffOfCustomer = customerEntity.getStaffs();
        for(UserEntity staff : staffs){
            if(staffOfCustomer.contains(staff)){
                transactionEntity.setStaffId(staff.getId());
            }
        }

        // Lưu entity vào database
        transactionRepository.save(transactionEntity);

        return null;
    }

    @Override
    public void deleteById(Long id, Long customerId) throws ServiceException {
        transactionRepository.deleteTransactionByIdAndCustomerEntity_Id(id,customerId);
    }

    @Override
    public List<TransactionDTO> customerDdx(Long customerId) throws ServiceException {
        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomerEntity_IdAndCode(customerId,"DDX");
        List<TransactionDTO> result = new ArrayList<TransactionDTO>();
        for(TransactionEntity transactionEntity : transactionEntities){
            TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
            result.add(transactionDTO);
        }

        return result;
    }

    @Override
    public List<TransactionDTO> customerCskh(Long customerId) throws ServiceException {
        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomerEntity_IdAndCode(customerId,"CSKH");

        List<TransactionDTO> result = new ArrayList<TransactionDTO>();
        for(TransactionEntity transactionEntity : transactionEntities){
            TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
            result.add(transactionDTO);
        }

        return result;
    }
}
