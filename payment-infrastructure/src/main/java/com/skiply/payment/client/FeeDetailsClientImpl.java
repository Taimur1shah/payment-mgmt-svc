package com.skiply.payment.client;

import com.skiply.payment.client.api.FeeDetailsClient;
import com.skiply.payment.client.api.StudentDetailsClient;
import com.skiply.payment.client.dto.FeeDTO;
import com.skiply.payment.client.dto.FeeResponse;
import com.skiply.payment.client.dto.StudentDTO;
import com.skiply.payment.client.dto.StudentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FeeDetailsClientImpl implements FeeDetailsClient {

  @Value("${fee.service.url:http://127.0.0.1:8081/fee-service/api/fees/}")
  private String feeServiceUrl;

  private final RestTemplate restTemplate;

  public FeeDetailsClientImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }


  @CircuitBreaker(name = "feeService", fallbackMethod = "fallbackFeeDetails")
  @Retry(name = "feeService")
  @Override
  public FeeDTO getFeeDetails(String tuitionType,String grade) {

    String url = feeServiceUrl + "tuitionType/"+tuitionType+"/grade"+grade;

    ResponseEntity<FeeResponse> response =
        restTemplate.getForEntity(url, FeeResponse.class);

    FeeDTO feeDTO = new FeeDTO();
    BeanUtils.copyProperties(response.getBody(),feeDTO);
    return feeDTO;
  }

  // fallback method
  public FeeDTO fallbackFeeDetails(String tuitionType, String grade, Throwable ex) {
    FeeDTO fallback = new FeeDTO();
    fallback.setGrade(grade);
    fallback.setFeeCharges(0D);
    fallback.setFeeName("Default");
    return fallback;
  }
}
