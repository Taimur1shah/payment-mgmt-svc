package com.skiply.payment.service;

import com.skiply.payment.domain.TransactionDetails;
import java.util.Optional;

public interface PaymentService {

  void save(TransactionDetails transactionDetails);

  Optional<TransactionDetails> getById(Integer id);

  void deleteFee(Integer id);

}
