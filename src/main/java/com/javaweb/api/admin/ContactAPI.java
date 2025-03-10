package com.javaweb.api.admin;

import com.javaweb.exception.ServiceException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.ContactService;
import com.javaweb.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contact")
public class ContactAPI {
    @Autowired
    private ContactService contactService;
    @PostMapping("/send")
    public ResponseEntity<?> sendContact (@Valid @RequestBody CustomerDTO contactDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage).collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if (!contactDTO.getCustomerPhone().matches("^[0-9]{10,11}$")) {
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage("Số điện thoại không hợp lệ! Vui lòng nhập 10-11 chữ số.");
                return ResponseEntity.badRequest().body(responseDTO);
            }

            if (DataUtil.checkData(contactDTO.getFullName()) && DataUtil.checkData(contactDTO.getCustomerPhone())) {
                contactService.addContact(contactDTO);
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage("Thêm liên hệ thành công");
                return ResponseEntity.ok().body(responseDTO);
            }
            return null;
        }
        catch (ServiceException e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
