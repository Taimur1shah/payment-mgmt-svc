package com.skiply.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

  private Integer receiptId;
  private Integer studentId;
  private String grade;
  private String feeName;
  private Double amount;
  private String cardNumber;
  private String cardType;
  private LocalDateTime createdAt;

}
