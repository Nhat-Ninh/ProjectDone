package com.javaweb.api.admin;

import com.javaweb.kafka.JsonKafkaProducerService;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
  @Autowired
  private BuildingService buildingService;
  @Autowired
  private JsonKafkaProducerService jsonKafkaProducerService;

  @PostMapping
    public ResponseEntity<?> createOrUpdateBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
      try {
        if (bindingResult.hasErrors()) {
          List<String> errors = bindingResult.getFieldErrors().stream()
                  .map(FieldError::getDefaultMessage)
                  .collect(Collectors.toList());
          return ResponseEntity.badRequest().body(errors);
        }
        //xuong service va repo de them
        else {
          buildingService.createOrUpdateBuilding( buildingDTO);
          ResponseDTO responseDTO = new ResponseDTO();
          responseDTO.setMessage("Success");
          String staffName = SecurityUtils.getPrincipal().getUsername();
          if(buildingDTO.getId()==null){
            sendMessage("building-topic","-Created building has name "+buildingDTO.getName()+" By "+staffName+" Success" );
          }
          else {
            sendMessage("building-topic", "-  Update building has name "+buildingDTO.getName()+" By "+staffName+" Success " );
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
  @DeleteMapping ("/{ids}")
  public ResponseEntity<?> deleteBuilding(@PathVariable List<Long> ids) {
    if(ids.size()==0){
      ResponseDTO responseDTO = new ResponseDTO();
      responseDTO.setMessage("Ids is empty");
      responseDTO.setDetail("Hãy điền vào dấu tích để xóa");
      return ResponseEntity.badRequest().body(responseDTO);
    }
    else {
      //xuongservice va xuong repo
      buildingService.deleteById(ids);
      ResponseDTO responseDTO = new ResponseDTO();
      responseDTO.setMessage("Buildings deleted successfully");
      String staffName = SecurityUtils.getPrincipal().getUsername();
      String id = ids.stream().map(String::valueOf).collect(Collectors.joining(","));
      sendMessage("building-topic","-Deleted building has id: ["+id+"] By "+staffName+" Success");
      return ResponseEntity.ok().body(responseDTO);
    }

  }

  //Load danh sach giao
  @GetMapping("/{buildingId}/staffs")
  public ResponseEntity<?> loadStaff(@PathVariable Long buildingId) {
       //xuong service va repo xu ly
         ResponseDTO staffResponseDTOS = buildingService.findStaffByBuildingId(buildingId);
         return ResponseEntity.ok(staffResponseDTOS);

  }
  public void sendMessage(String topic, String message){
    jsonKafkaProducerService.sendMessageForBuilding(topic,message);
  }


}
