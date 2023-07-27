package com.example.spring.datajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ICurrencyService;
import com.example.spring.datajpa.model.Currency;

@RestController
@RequestMapping("/api/users/registration")
public class CurrencyController {

	@Autowired
	ICurrencyService service;
	
	@PostMapping("/createCurrency")
	public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) {
		
		System.out.println("user "+currency);
		try {
			
			Currency _currency = service.createCurrency(currency);
			
			return new ResponseEntity<Currency>(_currency, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
