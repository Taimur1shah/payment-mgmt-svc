package com.skiply.payment.repository;


import com.skiply.payment.domain.TransactionDetails;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDetailsRepoImpl implements TransactionDetailsRepository {

  private final TransactionDetailsSpringDataJpa jpaRepository;

  public TransactionDetailsRepoImpl(TransactionDetailsSpringDataJpa jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public TransactionDetails save(TransactionDetails transactionDetails) {
    return jpaRepository.save(transactionDetails);
  }

  @Override
  public Optional<TransactionDetails> findById(Integer id) {
    return jpaRepository.findById(id);
  }



  @Override
  public void deleteById(Integer id) {
    jpaRepository.deleteById(id);
  }


}