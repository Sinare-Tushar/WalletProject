package com.example.spring.datajpa.repository.impl;

public class OtpNotVerifiedException extends Exception {
	public OtpNotVerifiedException(String message) throws Exception{
		throw new Exception(message);
	}
}
