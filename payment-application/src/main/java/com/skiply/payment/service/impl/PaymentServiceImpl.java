package com.skiply.payment.service.impl;

import com.skiply.payment.domain.Fee;
import com.skiply.payment.domain.TransactionDetails;
import com.skiply.payment.repository.TransactionDetailsRepository;
import com.skiply.payment.service.PaymentService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

  private final TransactionDetailsRepository repository;

  public PaymentServiceImpl(TransactionDetailsRepository repository) {
    this.repository = repository;
  }


  @Override
  public void save(TransactionDetails transactionDetails) {
     repository.save(transactionDetails);
  }

  @Override
  public Optional<TransactionDetails>  getById(Integer id) {
    Optional<TransactionDetails> optionalTransactionDetails = repository.findById(id);
    return optionalTransactionDetails;
  }



  @Override
  public void deleteFee(Integer feeId) {
    repository.deleteById(feeId);
  }
}
