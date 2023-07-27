package com.example.spring.datajpa.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ITransactionService;
import com.example.spring.datajpa.model.Transaction;

@RestController
@RequestMapping("/api/users/registration")
public class TransactionController {
	@Autowired
	ITransactionService service;

	@PostMapping("/showTransaction")
	public ResponseEntity<Transaction> showTransaction(@RequestParam String transactionId) {
		UUID transactionId1= UUID.fromString(transactionId);
		Transaction transaction = service.getTransactionDetails(transactionId1);
		if (transaction!=null) {
			return new ResponseEntity<>(transaction, HttpStatus.OK);
			
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/showTransactionBetween")
	public ResponseEntity<List<Transaction>> showTransactionInBetween(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);
		List<Transaction> transaction = service.findByTransactionDate(startOfDay, endOfDay);
		if (transaction.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		return new ResponseEntity<>(transaction, HttpStatus.OK);

	}
	 @PostMapping("/generate-report")
	    public ResponseEntity<String> generateReport(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
	            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
	        
		 LocalDateTime startOfDay = startDate.atStartOfDay();
	        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);
			boolean test = service.findByTransactionDates(startOfDay, endOfDay);
			if (test!=true) {
				return new ResponseEntity<>("You Entered Wrong OTP !!! ",HttpStatus.NO_CONTENT);
				
			}
			
			return new ResponseEntity<>("Report generated successfully!",  HttpStatus.OK);
	       }
	@GetMapping("/showAllTransaction")
	public ResponseEntity<List<Transaction>> showAllDetails() {
		try {
			List<Transaction> transaction = service.getAllTransactionDetails();

			if (transaction.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(transaction, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
