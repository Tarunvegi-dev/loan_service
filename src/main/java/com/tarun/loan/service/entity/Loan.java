package com.tarun.loan.service.entity;

import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//lombok annotations to generate getters, setters, constructors
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//JPA annotations to map this class to a database table
@Entity
@Table(name = "loans", 
    indexes = {
        @Index(name = "idx_customer_id", columnList = "customer_id")
    }
)
public class Loan {

    @Id
    @SequenceGenerator(name = "loan_id_seq", sequenceName = "loan_id_seq", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_id_seq")
    @Column(nullable = false, name = "loan_id", updatable = false)
    private Long loanId;

    @Column(nullable = false, name = "customer_id", updatable = false)
    private Long customerId;

    @Column(nullable = false, precision = 15, scale = 2)
    @Check(constraints = "amount > 0")
    private BigDecimal amount;

    @Column(nullable = false, name = "tenure_in_months")
    private Integer tenureInMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "loan_status")
    private LoanStatus status;

    @Column(nullable = false, name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "approved_at")
    private Instant approvedAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = Instant.now();
        if (this.status == null) {
            this.status = LoanStatus.CREATED;
        }
    }

}
