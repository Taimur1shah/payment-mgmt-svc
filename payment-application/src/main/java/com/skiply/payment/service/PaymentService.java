package com.skiply.payment.service;

import com.skiply.payment.domain.TransactionDetails;
import com.skiply.payment.dto.PaymentDto;
import java.util.Optional;

public interface PaymentService {
  TransactionDetails performTransaction(PaymentDto paymentDto);
  TransactionDetails save(TransactionDetails transactionDetails);
  Optional<TransactionDetails> getByReceiptId(Integer receiptId);
}
