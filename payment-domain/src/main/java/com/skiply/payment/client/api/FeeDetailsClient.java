package com.skiply.payment.client.api;

import com.skiply.payment.client.dto.FeeDTO;
import com.skiply.payment.client.dto.StudentDTO;

public interface FeeDetailsClient {

  FeeDTO getFeeDetails(String tuitionType,String grade);

}
