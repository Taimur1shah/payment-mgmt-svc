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
@Table(name = "purchase_details")
public class PurchaseDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "purchase_id", nullable = false, updatable = false)
  private Integer purchaseId;

  @Column(nullable = false)
  private Integer quantity;

  @Column(nullable = false)
  private String feeName;

  @Column(nullable = false)
  private String grade;

  @Column(name = "custom_amount")
  private Double customAmount;

  @Column(name = "total_amount")
  private Double totalAmount;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public PurchaseDetails() {
    this.createdAt = LocalDateTime.now();
  }
}

