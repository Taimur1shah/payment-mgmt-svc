package com.skiply.payment.response;

import com.skiply.payment.api.model.Transaction200Response;
import com.skiply.payment.domain.TransactionDetails;
import java.util.concurrent.ThreadLocalRandom;

public class PaymentResponseBuilder {

  public static Transaction200Response buildFeeTransactionResponse(TransactionDetails transactionDetails){
    Transaction200Response transaction200Response = new Transaction200Response();
    transaction200Response.setReferenceNumber(transactionDetails.getReferenceNumber());
    transaction200Response.setStatus("SUCCESS");
    return transaction200Response;
  }
}
