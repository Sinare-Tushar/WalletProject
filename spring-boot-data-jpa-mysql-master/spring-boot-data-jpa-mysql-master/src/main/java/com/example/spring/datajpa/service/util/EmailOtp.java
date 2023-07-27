package com.example.spring.datajpa.service.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.mail.*;
import javax.mail.internet.*;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.spring.datajpa.model.Transaction;
import com.example.spring.datajpa.repository.impl.OtpNotVerifiedException;

@Component
public class EmailOtp {
	private static final String EMAIL_HOST = "smtp.gmail.com";
	private static final String EMAIL_USERNAME = "tusharsinare0202@gmail.com";
	private static final String EMAIL_PASSWORD = "vqhjeiinbjnzmdfq";
	private static final String EMAIL_FROM = "tusharsinare0202@gmail.com";
	private static final String EMAIL_TO = "tusharsinare1995@gmail.com";
	
	@Autowired
	CreatePdfTable createPdf;

	public String generateOTP(int length) {
		Random random = new Random();
		StringBuilder otp = new StringBuilder();

		for (int i = 0; i < length; i++) {
			otp.append(random.nextInt(10));
		}

		return otp.toString();
	}

	public Boolean sendOTPByEmail(String otp,List<Transaction> transactions,LocalDateTime startDate, LocalDateTime endDate)throws Exception {
		// SMTP server properties
		Properties props = new Properties();
		props.put("mail.smtp.host", EMAIL_HOST);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");

		// Create a session with authentication
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
			}
		});

		try {
			// Create a message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EMAIL_FROM));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO));
			message.setSubject("OTP Verification");
			message.setText("Your OTP is: " + otp);

			// Send the message
			Transport.send(message);

			System.out.println("OTP sent successfully.");

			boolean test = otpVarified(otp);
			if (test != true) {
				throw new OtpNotVerifiedException("OTP Invalid");
				
			} else {
				System.out.println("OTP successfully Verified.");
				System.out.println("Wait Pdf is Sending in your mail..." + EMAIL_TO);
				 createPdf.generatePDFReport(transactions,startDate, endDate);
				 HttpHeaders headers = new HttpHeaders();
			        headers.setContentType(MediaType.APPLICATION_PDF);
			        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("report.pdf").build());
				return true;
			}

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean otpVarified(String otp) {
		System.out.println("Enter a Otp");
		Scanner sc = new Scanner(System.in);
		String otp2 = sc.next();
		int time = 3;
		for (int i = 1; i <= time; i++) {
			if (otp.equals(otp2) && i <= time) {
				return true;

			} else {
				System.out.println("please Enter " + i + " OTP");
				Scanner sc1 = new Scanner(System.in);
				String otp3 = sc.next();
				otp2 = otp3;
			}
		}
		return false;
	}

}
