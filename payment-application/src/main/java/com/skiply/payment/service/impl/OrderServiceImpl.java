package com.skiply.payment.service.impl;

import com.skiply.payment.client.api.FeeDetailsClient;
import com.skiply.payment.client.dto.FeeDTO;
import com.skiply.payment.client.dto.StudentDTO;
import com.skiply.payment.client.api.StudentDetailsClient;
import com.skiply.payment.domain.TransactionDetails;
import com.skiply.payment.dto.OrderDto;
import com.skiply.payment.service.OrderService;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final StudentDetailsClient studentDetailsClient;
  private final FeeDetailsClient feeDetailsClient;

  public OrderServiceImpl(StudentDetailsClient studentDetailsClient,
      FeeDetailsClient feeDetailsClient) {
    this.studentDetailsClient = studentDetailsClient;
    this.feeDetailsClient = feeDetailsClient;
  }


  @Override
  public TransactionDetails purchaseOrder(OrderDto orderDto) {
    StudentDTO studentDTO = studentDetailsClient.getStudentDetails(orderDto.getStudentId());
    FeeDTO feeDTO = feeDetailsClient.getFeeDetails(orderDto.getGrade());

    TransactionDetails transactionDetails = new TransactionDetails();
    transactionDetails.setStudentId(orderDto.getStudentId());
    transactionDetails.setStudentName(studentDTO.getStudentName());
    transactionDetails.setReferenceNumber(ThreadLocalRandom.current().nextInt(0, 100000));
    transactionDetails.setCardType(orderDto.getCardType());
    transactionDetails.setCardNumber(orderDto.getCardNumber());

    // To Do : Send Email To User

    return transactionDetails;
  }
}
