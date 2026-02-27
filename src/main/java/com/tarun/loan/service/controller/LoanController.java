package com.tarun.loan.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tarun.loan.service.dto.CreateLoanRequestDto;
import com.tarun.loan.service.service.LoanService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController("api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @Operation(summary = "Create a new loan", description = "Creates a new loan application with the provided details.")
    @PostMapping("/create")
    public ResponseEntity<?> createLoan(@Valid @RequestBody CreateLoanRequestDto createLoanRequestDto) {
        return ResponseEntity.ok(loanService.createLoan(createLoanRequestDto));
    }

    @PatchMapping("/approve")
    public ResponseEntity<?> approveLoan(@PathParam("loan-id") Long loanId) {
        return ResponseEntity.ok(loanService.approveLoan(loanId));
    }
}
