package com.skiply.payment.repository;

import com.skiply.payment.domain.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsSpringDataJpa extends JpaRepository<TransactionDetails, Integer> {

}
