package com.skiply.payment.service;


import com.skiply.payment.client.dto.StudentDTO;
import com.skiply.payment.domain.TransactionDetails;
import com.skiply.payment.dto.OrderDto;

public interface OrderService {

  TransactionDetails purchaseOrder(OrderDto orderDto);

}
