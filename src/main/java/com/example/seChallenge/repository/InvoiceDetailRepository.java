package com.example.seChallenge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.seChallenge.domain.InvoiceDetail;

@Repository
public interface InvoiceDetailRepository extends CrudRepository<InvoiceDetail, Long> {
    
}
