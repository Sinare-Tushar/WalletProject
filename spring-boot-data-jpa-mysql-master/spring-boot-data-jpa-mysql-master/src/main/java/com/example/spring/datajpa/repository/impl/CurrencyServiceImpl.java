package com.example.spring.datajpa.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service.ICurrencyService;
import com.example.spring.datajpa.model.Currency;
import com.example.spring.datajpa.repository.CurrencyRepository;

@Service
public class CurrencyServiceImpl implements ICurrencyService {

	@Autowired
	CurrencyRepository currencyRepository;
	
	@Override
	public Currency createCurrency(Currency currency) {
		
		return currencyRepository.save(currency);
	}

}
