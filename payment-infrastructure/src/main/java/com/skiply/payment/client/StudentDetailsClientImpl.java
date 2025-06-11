package com.skiply.payment.client;

import com.skiply.payment.client.api.StudentDetailsClient;
import com.skiply.payment.client.dto.StudentResponse;
import com.skiply.payment.client.dto.StudentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StudentDetailsClientImpl implements StudentDetailsClient {

  @Value("${student.service.url:http://127.0.0.1:8080/api/students/}")
  private String studentServiceUrl;

  private final RestTemplate restTemplate;

  public StudentDetailsClientImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }


  @Override
  public StudentDTO getStudentDetails(Integer studentId) {

    String url = studentServiceUrl + studentId;

    ResponseEntity<StudentResponse> response =
        restTemplate.getForEntity(url, StudentResponse.class);

    StudentDTO studentDTO = new StudentDTO();
    BeanUtils.copyProperties(response.getBody(),studentDTO);
    return studentDTO;
  }
}
