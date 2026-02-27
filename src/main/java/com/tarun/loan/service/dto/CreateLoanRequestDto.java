package com.tarun.loan.service.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateLoanRequestDto {
    
    @NotNull
    @JsonProperty("customer_id")
    private Long customerId;

    @DecimalMin(value = "0.1", message = "Loan amount must be greater than 0")
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("tenure_in_months")
    @Min(value = 1, message = "Tenure must be at least 1 month")
    @Max(value = 360, message = "Tenure cannot exceed 360 months")
    private Integer tenureInMonths;
}
