package com.example.spring.datajpa.repository.impl;


import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.service.ITransactionService;
import com.example.spring.datajpa.model.Transaction;
import com.example.spring.datajpa.repository.TransactionRepository;
import com.example.spring.datajpa.service.util.CreatePdf;
import com.example.spring.datajpa.service.util.CreatePdfTable;
import com.example.spring.datajpa.service.util.EmailOtp;
import com.example.spring.datajpa.service.util.EmailUtil;
import com.example.spring.datajpa.service.util.MobileOtp;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class TransactionServiceImpl implements ITransactionService {
	@Autowired

	TransactionRepository repository;
	/*
	 * @Autowired private JavaMailSender sender;
	 */
	@Autowired
	EmailOtp emailOtp;
	
	@Autowired
	MobileOtp mobile;
	@Override
	public Transaction getTransactionDetails(UUID transactionId) {
			Transaction transaction = repository.findById(transactionId).orElse(null);
			if(transaction!=null) {
				return transaction;
			}
			else {
				throw new EntityNotFoundException("Data Not Found");
			}
		
		
	}

	@Override
	public List<Transaction> getAllTransactionDetails() {
		List<Transaction> trans = repository.findAll();
		if (trans.isEmpty()) {
			throw new EntityNotFoundException("Data Not Found");
		}
		return trans;
	}

	@Override
	public List<Transaction> findByTransactionDate(LocalDateTime startDate, LocalDateTime endDate) {
	
		List<Transaction> transaction = repository.findByTransactionDateBetween(startDate, endDate);
		if (transaction.isEmpty()) {
			throw new EntityNotFoundException("Data Not Found");
		}
		return transaction;
	}
	
	@Override
	public Boolean findByTransactionDates(LocalDateTime startDate, LocalDateTime endDate) {
		boolean test=false;
		List<Transaction> transaction = repository.findByTransactionDateBetween(startDate, endDate);
		
		
		if (transaction.isEmpty()) {
			throw new EntityNotFoundException("Data Not Found");
		}
		 String otp = emailOtp.generateOTP(6); // Generate a 6-digit OTP

	       try {
	    	//  mobile.sendOtp(otp); 
		 test = emailOtp.sendOTPByEmail(otp,transaction,startDate, endDate);
		 return test;
	       }catch(Exception e) {
	    	   e.printStackTrace();
	       }
		
	        // Store the report location
	       // storeReportLocation(reportLocation);
		return test;
	}
	
	 
}
