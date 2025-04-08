package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentCustomerAPI {
    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<?> updateAssignmentBuilding(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {

        //xuong repo lam
        if(assignmentCustomerDTO.getCustomerId()==null && assignmentCustomerDTO.getCustomerId().equals("")){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Customer ID is null");
            return ResponseEntity.badRequest().body(responseDTO);
        }
        else {
            assignmentService.updateAssignmentCustomer(assignmentCustomerDTO);
            return ResponseEntity.ok().body(assignmentCustomerDTO);
        }


    }
}
