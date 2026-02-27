package com.tarun.loan.service.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tarun.loan.service.dto.CreateLoanRequestDto;
import com.tarun.loan.service.dto.CreateLoanResponseDto;
import com.tarun.loan.service.entity.Loan;
import com.tarun.loan.service.entity.LoanStatus;
import com.tarun.loan.service.repository.LoanRepository;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    
    public CreateLoanResponseDto createLoan(CreateLoanRequestDto createLoanRequestDto) {
        // Business logic to create a loan
        //customer validatiion
        Loan loan = new Loan();
        loan.setCustomerId(createLoanRequestDto.getCustomerId());
        loan.setAmount(createLoanRequestDto.getAmount());
        loan.setTenureInMonths(createLoanRequestDto.getTenureInMonths());
        loan.setStatus(LoanStatus.CREATED);
        loan.setCreatedAt(Instant.now());
        loan = loanRepository.save(loan);
        
        CreateLoanResponseDto response = new CreateLoanResponseDto();
        response.setLoanId(loan.getLoanId());
        response.setStatus(loan.getStatus());
        return response;
    }


    public CreateLoanResponseDto approveLoan(Long loanId) {
        // Business logic to approve a loan
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(LoanStatus.APPROVED);
        loanRepository.save(loan);
        CreateLoanResponseDto response = new CreateLoanResponseDto();
        response.setLoanId(loan.getLoanId());
        response.setStatus(loan.getStatus());
        return response;
    }
}