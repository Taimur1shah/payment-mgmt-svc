package com.skiply.payment.util;

import com.skiply.payment.api.model.ErrorResponse;

public class FeeUtil {

  public ErrorResponse getNotFoundErrorResponse(){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode("404");
    errorResponse.setDetail("Resource Not Found");
    errorResponse.setName("NOT_FOUND");
    errorResponse.setReason("Record does not exist");
    return errorResponse;
  }

}
