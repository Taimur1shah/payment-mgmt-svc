package com.skiply.payment.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction_details")
public class TransactionDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_id", nullable = false, updatable = false)
  private Integer transactionId;

  @Column(nullable = false)
  private String studentName;

  @Column(nullable = false)
  private Integer studentId;

  @Column(nullable = false)
  private Integer referenceNumber;

  @Column(nullable = false)
  private Integer cardNumber;

  @Column(nullable = false)
  private String cardType;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public TransactionDetails() {
    this.createdAt = LocalDateTime.now();
  }
}

