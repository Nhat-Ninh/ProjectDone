package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.response.CustomerSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;
@Component
public class CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;


    public CustomerSearchResponse toCustomerResponse (CustomerEntity it){
        CustomerSearchResponse customerResponse = modelMapper.map(it, CustomerSearchResponse.class);

        if (it.getStatus() != null) {
            Status status = Status.valueOf(it.getStatus());
            customerResponse.setStatus(status.getStatusName()); // Set tên trạng thái thay vì Enum name
        }
        return customerResponse;
    }
}
