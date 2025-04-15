package com.javaweb.api.admin;


import com.javaweb.kafka.JsonKafkaProducerService;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    CustomerService customerService;
    @Autowired
    JsonKafkaProducerService jsonKafkaProducerService;

    @PostMapping
    public ResponseEntity<?> createOrUpdateBuilding(@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
            //xuong service va repo de them
            else {
                customerService.createOrUpdateCustomer(customerDTO);
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage("Success");
                String staffName = SecurityUtils.getPrincipal().getUsername();
                if(customerDTO.getId()==null){
                    sendMessage("customer-topic","Create customer "+customerDTO.getFullName()+" By "+staffName+" successfully");
                }
                else {
                    sendMessage("customer-topic","Update customer "+customerDTO.getFullName()+" By "+staffName+" successfully");
                }
                return ResponseEntity.ok().body(responseDTO);
            }
        }
        catch (Exception ex) {
            ResponseDTO responseDTOs = new ResponseDTO();
            responseDTOs.setMessage(ex.getMessage());
            return ResponseEntity.badRequest().body(responseDTOs);
        }
    }

    //Xóa
    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteCustomer(@PathVariable List<Long> ids) {
        if(ids.size()==0){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Ids is empty");
            responseDTO.setDetail("Hãy điền vào dấu tích để xóa");
            return ResponseEntity.badRequest().body(responseDTO);
        }
        else {
            //xuongservice va xuong repo
            customerService.deleteById(ids);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Customers deleted successfully");
            String id = ids.stream().map(String::valueOf).collect(Collectors.joining(","));
            String staffName = SecurityUtils.getPrincipal().getUsername();
            sendMessage("customer-topic","Delete customer has id: {"+id+" By "+staffName+ " successfully");
            return ResponseEntity.ok().body(responseDTO);
        }

    }

    //Load danh sach giao
    @GetMapping("/{customerId}/staffs")
    public ResponseEntity<?> loadStaff(@PathVariable Long customerId) {
        //xuong service va repo xu ly
        ResponseDTO staffResponseDTOS = customerService.findStaffByCustomerId(customerId);
        return ResponseEntity.ok(staffResponseDTOS);

    }
    public void sendMessage(String topic, String message) {
        jsonKafkaProducerService.sendMessageForCustomer(topic, message);
    }
}
