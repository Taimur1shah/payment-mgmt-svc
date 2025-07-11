package com.skiply.payment.repository;

import com.skiply.payment.domain.TransactionDetails;
import java.util.Optional;

public interface TransactionDetailsRepository {
  TransactionDetails save(TransactionDetails transactionDetails);
  Optional<TransactionDetails> findById(Integer id);
  void deleteById(Integer id);
}
