package com.example.service;



import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.spring.datajpa.model.Transaction;

public interface ITransactionService {
	
	Transaction getTransactionDetails(UUID userId);
	List<Transaction> getAllTransactionDetails();
	List<Transaction> findByTransactionDate(LocalDateTime startDate,LocalDateTime endDate);
	Boolean findByTransactionDates(LocalDateTime startDate,LocalDateTime endDate);

}
