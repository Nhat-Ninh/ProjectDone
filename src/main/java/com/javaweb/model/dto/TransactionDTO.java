package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TransactionDTO extends AbstractDTO {
    private String code;
    @NotBlank(message = "Note can not be blank")
    private String note;
    private Long customerId;

}
