package com.example.spring.datajpa.service.util;


	import org.springframework.stereotype.Component;

// Install the Java helper library from twilio.com/docs/java/install

	import com.twilio.Twilio;
	import com.twilio.rest.api.v2010.account.Message;
	import com.twilio.type.PhoneNumber;
@Component
	public class MobileOtp {
	    // Find your Account SID and Auth Token at twilio.com/console
	    // and set the environment variables. See http://twil.io/secure
	    public static final String ACCOUNT_SID = System.getenv("AC5ba980aa754a87bd7653a5d7d5a1a43d");
	    public static final String AUTH_TOKEN = System.getenv("6dac7db2b3a1ea0399dd1f3aed3ee4cd");

	    public void sendOtp(String otp) {
	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	        Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("+919834052716"),
	                new com.twilio.type.PhoneNumber("+917057192939"),
	                "Hi  Your OTP code is"+ otp)
	            .create();

	        System.out.println(message.getSid());
	    }
	
}
