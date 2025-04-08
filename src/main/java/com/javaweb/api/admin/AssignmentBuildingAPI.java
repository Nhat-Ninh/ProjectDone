package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentBuildingAPI {
    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<?> updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {

        //xuong repo lam
        if(assignmentBuildingDTO.getBuildingId()==null && assignmentBuildingDTO.getBuildingId().equals("")){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Building ID is null");
            return ResponseEntity.badRequest().body(responseDTO);
        }
        else {
              assignmentService.updateAssignmentBuilding(assignmentBuildingDTO);
              return ResponseEntity.ok().body(assignmentBuildingDTO);
        }


    }
}
