package com.tarun.loan.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tarun.loan.service.entity.LoanStatus;

import lombok.Data;

@Data
public class CreateLoanResponseDto {
    
    @JsonProperty("loan_id")
    private Long loanId;

    @JsonProperty("status")
    private LoanStatus status;
}
