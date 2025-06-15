package com.skiply.payment.service.test;

import com.skiply.payment.client.api.FeeDetailsClient;
import com.skiply.payment.client.api.StudentDetailsClient;
import com.skiply.payment.client.dto.StudentDTO;
import com.skiply.payment.domain.TransactionDetails;
import com.skiply.payment.dto.PaymentDto;
import com.skiply.payment.repository.TransactionDetailsRepository;
import com.skiply.payment.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

  @Mock
  private StudentDetailsClient studentDetailsClient;

  @Mock
  private FeeDetailsClient feeDetailsClient;

  @Mock
  private TransactionDetailsRepository transactionDetailsRepository;

  @InjectMocks
  private PaymentServiceImpl paymentService;

  private PaymentDto paymentDto;
  private StudentDTO studentDTO;
  private TransactionDetails transactionDetails;

  @BeforeEach
  void setUp() {
    paymentDto = new PaymentDto();
    paymentDto.setStudentId(123);
    paymentDto.setCardType("VISA");
    paymentDto .setCardNumber("4111111111111111");
    paymentDto .setAmount(1000.0);


    studentDTO = new StudentDTO();
    studentDTO.setStudentId(123);
    studentDTO.setStudentName("Ali Khan");

    transactionDetails = new TransactionDetails();
    transactionDetails.setStudentId(123);
    transactionDetails.setReferenceNumber(12345);
    transactionDetails.setCardType("VISA");
    transactionDetails.setCardNumber("4111111111111111");
    transactionDetails.setStudentName("John Doe");
    transactionDetails.setAmount(1000.0);
  }

  @Test
  void performTransaction_shouldReturnTransactionDetails() {

    when(studentDetailsClient.getStudentDetails(123)).thenReturn(studentDTO);
    when(transactionDetailsRepository.save(any(TransactionDetails.class))).thenReturn(transactionDetails);
    TransactionDetails result = paymentService.performTransaction(paymentDto);

    assertNotNull(result);
    assertEquals(123, result.getStudentId());
    assertEquals("Ali Khan", result.getStudentName());
    assertEquals("VISA", result.getCardType());
    assertEquals(1000.0, result.getAmount());
    assertNotNull(result.getReferenceNumber());
    verify(studentDetailsClient).getStudentDetails(123);
    verify(transactionDetailsRepository).save(any(TransactionDetails.class));
  }

  @Test
  void performTransaction_shouldGenerateReferenceNumber() {
    when(studentDetailsClient.getStudentDetails(123)).thenReturn(studentDTO);
    when(transactionDetailsRepository.save(any(TransactionDetails.class))).thenAnswer(invocation -> {
      TransactionDetails td = invocation.getArgument(0);
      assertTrue(td.getReferenceNumber() >= 0 && td.getReferenceNumber() < 100000);
      return td;
    });

    TransactionDetails result = paymentService.performTransaction(paymentDto);
    assertNotNull(result.getReferenceNumber());
  }

  @Test
  void performTransaction_shouldMaskCardNumber() {
    when(studentDetailsClient.getStudentDetails(123)).thenReturn(studentDTO);
    when(transactionDetailsRepository.save(any(TransactionDetails.class))).thenAnswer(invocation ->
        invocation.getArgument(0)
    );

    TransactionDetails result = paymentService.performTransaction(paymentDto);
    assertNotNull(result.getCardNumber());
    assertNotEquals("4111111111111111", result.getCardNumber());
  }

  @Test
  void save_shouldCallRepositorySave() {
    when(transactionDetailsRepository.save(transactionDetails)).thenReturn(transactionDetails);
    TransactionDetails result = paymentService.save(transactionDetails);
    assertNotNull(result);
    verify(transactionDetailsRepository).save(transactionDetails);
  }

  @Test
  void getByReceiptId_shouldReturnEmptyWhenNotFound() {
    when(transactionDetailsRepository.findById(999)).thenReturn(Optional.empty());
    Optional<TransactionDetails> result = paymentService.getByReceiptId(999);
    assertTrue(result.isEmpty());
  }

  @Test
  void getByReceiptId_shouldReturnTransactionWhenFound() {
    when(transactionDetailsRepository.findById(123)).thenReturn(Optional.of(transactionDetails));

    Optional<TransactionDetails> result = paymentService.getByReceiptId(123);

    assertTrue(result.isPresent());
    assertEquals(123, result.get().getStudentId());
  }
}