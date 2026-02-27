package com.tarun.loan.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarun.loan.service.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    

}
