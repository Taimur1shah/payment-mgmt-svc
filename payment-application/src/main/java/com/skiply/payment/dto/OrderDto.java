package com.skiply.payment.dto;

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
public class OrderDto {

  private Integer studentId;
  private String  grade;
  private Integer quantity;
  private String  feeType;
  private Integer cardNumber;
  private String  cardType;

}
