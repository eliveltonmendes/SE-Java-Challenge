package com.example.seChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seChallenge.domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
}
