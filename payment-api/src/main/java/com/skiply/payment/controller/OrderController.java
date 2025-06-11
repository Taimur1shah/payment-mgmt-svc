package com.skiply.payment.controller;


import com.skiply.payment.client.dto.FeeDTO;
import com.skiply.payment.client.dto.StudentDTO;
import com.skiply.payment.api.OrderApi;
import com.skiply.payment.api.model.OrderRequest;
import com.skiply.payment.domain.TransactionDetails;
import com.skiply.payment.dto.OrderDto;
import com.skiply.payment.service.PaymentService;
import com.skiply.payment.service.OrderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController implements OrderApi {

  private final PaymentService paymentService;

  private final OrderService orderService;

  public OrderController(PaymentService paymentService,
      OrderService orderService) {
    this.paymentService = paymentService;
    this.orderService = orderService;
  }

  @SneakyThrows
  @Override
  public ResponseEntity<Object> performNewOrder(OrderRequest orderRequest) {
    OrderDto orderDto = new OrderDto();
    BeanUtils.copyProperties(orderRequest, orderDto);

    TransactionDetails transactionDetails = orderService.purchaseOrder(orderDto);

    paymentService.save(transactionDetails);

    return ResponseEntity.ok(transactionDetails);
  }
}


