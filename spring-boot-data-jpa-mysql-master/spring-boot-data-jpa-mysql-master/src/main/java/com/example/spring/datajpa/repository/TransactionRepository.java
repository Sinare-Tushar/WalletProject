package com.example.spring.datajpa.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.spring.datajpa.model.Transaction;

@Service
public interface TransactionRepository  extends JpaRepository<Transaction, UUID>{

	List<Transaction> findByTransactionDateBetween(LocalDateTime startDate,LocalDateTime endDate);
}
