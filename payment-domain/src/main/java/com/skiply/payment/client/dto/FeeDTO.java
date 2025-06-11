package com.skiply.payment.client.dto;

import jakarta.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeeDTO implements Serializable  {

  private Integer feeId;
  private String feeName;
  private String grade;
  private Double feeCharges;
  private LocalDateTime createdAt;


}
