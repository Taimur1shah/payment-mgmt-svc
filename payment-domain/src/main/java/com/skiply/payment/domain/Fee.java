/*
package com.skiply.payment.domain;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "fee")
public class Fee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "fee_id", nullable = false, updatable = false)
  private Integer feeId;

  @Column(nullable = false)
  private String feeName;

  @Column(nullable = false)
  private String grade;

  @Column(name = "fee_charges", nullable = false)
  private Double feeCharges;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public Fee() {
    this.createdAt = LocalDateTime.now();
  }
}

*/
