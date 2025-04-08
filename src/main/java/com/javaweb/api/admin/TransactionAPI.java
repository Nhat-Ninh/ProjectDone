package com.javaweb.api.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.DeleteRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionAPI {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> addOrUpdateTransaction(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
            //xuong service va repo de them
            else {
                transactionService.addOrUpdateTransaction(transactionDTO);
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage("Success");
                return ResponseEntity.ok().body(responseDTO);
            }
        }
        catch (Exception ex) {
            ResponseDTO responseDTOs = new ResponseDTO();
            responseDTOs.setMessage(ex.getMessage());
            return ResponseEntity.badRequest().body(responseDTOs);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id, Long customerId) {
        if(id==null){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Ids is empty");
            responseDTO.setDetail("Hãy điền vào dấu tích để xóa");
            return ResponseEntity.badRequest().body(responseDTO);
        }
        else {
            //xuongservice va xuong repo
            transactionService.deleteById(id,customerId);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Transaction deleted successfully");
            return ResponseEntity.ok().body(responseDTO);
        }

    }
}
