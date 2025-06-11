package com.skiply.payment.client.api;

import com.skiply.payment.client.dto.StudentDTO;

public interface StudentDetailsClient {

  StudentDTO getStudentDetails(Integer studentId);

}
