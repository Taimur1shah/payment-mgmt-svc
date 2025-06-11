package com.skiply.payment.client.dto;

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
public class StudentResponse implements Serializable {

  private Integer studentId;
  private String studentName;
  private String grade;
  private String mobileNumber;
  private String schoolName;
  private LocalDateTime createdAt;

}
