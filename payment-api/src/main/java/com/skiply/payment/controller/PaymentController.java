package com.skiply.payment.controller;

import com.skiply.payment.api.PaymentApi;
import com.skiply.payment.api.model.PaymentRequest;
import com.skiply.payment.api.model.Transaction200Response;
import com.skiply.payment.domain.TransactionDetails;
import com.skiply.payment.dto.PaymentDto;
import com.skiply.payment.response.PaymentResponseBuilder;
import com.skiply.payment.service.PaymentService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PaymentController implements PaymentApi {

  private final PaymentService paymentService;



  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @SneakyThrows
  @Override
  public ResponseEntity<Transaction200Response> performPayment(PaymentRequest paymentRequest) {
    PaymentDto paymentDto = new PaymentDto();
   BeanUtils.copyProperties(paymentRequest, paymentDto);

   TransactionDetails savedTransactionDetails = paymentService.performTransaction(paymentDto);

    return new ResponseEntity<>(
        PaymentResponseBuilder.buildFeeTransactionResponse(savedTransactionDetails),
        HttpStatus.OK);
  }
}


