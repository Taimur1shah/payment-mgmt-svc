package com.skiply.payment.service.impl;

import com.skiply.payment.client.api.FeeDetailsClient;
import com.skiply.payment.client.dto.FeeDTO;
import com.skiply.payment.client.dto.StudentDTO;
import com.skiply.payment.client.api.StudentDetailsClient;
import com.skiply.payment.domain.TransactionDetails;
import com.skiply.payment.dto.PaymentDto;
import com.skiply.payment.repository.TransactionDetailsRepository;
import com.skiply.payment.service.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

  private final StudentDetailsClient studentDetailsClient;
  private final FeeDetailsClient feeDetailsClient;
  private final TransactionDetailsRepository transactionDetailsRepository;

  public PaymentServiceImpl(StudentDetailsClient studentDetailsClient,
      FeeDetailsClient feeDetailsClient,
      TransactionDetailsRepository transactionDetailsRepository) {
    this.studentDetailsClient = studentDetailsClient;
    this.feeDetailsClient = feeDetailsClient;
    this.transactionDetailsRepository = transactionDetailsRepository;
  }


  @Override
  @CircuitBreaker(name = "studentService", fallbackMethod = "fallbackTransaction")
  @Retry(name = "studentService")
  public TransactionDetails performTransaction(PaymentDto paymentDto) {

    StudentDTO studentDTO = studentDetailsClient.getStudentDetails(paymentDto.getStudentId());

    TransactionDetails transactionDetails = new TransactionDetails();
    transactionDetails.setStudentId(paymentDto.getStudentId());
    transactionDetails.setReferenceNumber(ThreadLocalRandom.current().nextInt(0, 100000));
    transactionDetails.setCardType(paymentDto.getCardType());
    transactionDetails.setCardNumber(paymentDto.getCardNumber());
    transactionDetails.setStudentName(studentDTO.getStudentName());
    transactionDetails.setAmount(paymentDto.getAmount());

    TransactionDetails savedTransactionDetails = save(transactionDetails);

    return savedTransactionDetails;
  }

  @Override
  public TransactionDetails save(TransactionDetails transactionDetails) {
    return transactionDetailsRepository.save(transactionDetails);
  }

  @Override
  public Optional<TransactionDetails> getByReceiptId(Integer receiptId) {
    return Optional.empty();
  }

  public TransactionDetails fallbackTransaction(PaymentDto paymentDto, Throwable ex) {
    TransactionDetails failedTransaction = new TransactionDetails();
    failedTransaction.setStudentId(paymentDto.getStudentId());
    failedTransaction.setCardType("UNAVAILABLE");
    failedTransaction.setCardNumber("XXXX");
    failedTransaction.setStudentName("UNKNOWN");
    failedTransaction.setAmount(0.0);
    failedTransaction.setReferenceNumber(-1);
    return failedTransaction;
  }
}

