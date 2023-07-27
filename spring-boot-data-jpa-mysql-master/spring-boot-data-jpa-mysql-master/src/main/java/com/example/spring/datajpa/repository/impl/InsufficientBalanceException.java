package com.example.spring.datajpa.repository.impl;

public class InsufficientBalanceException extends Exception {

	public InsufficientBalanceException(Long senderWalletId) throws Exception {
		
		throw new Exception("No Supported transaction");
	}

}
