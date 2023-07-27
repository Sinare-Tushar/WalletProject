package com.example.spring.datajpa.service.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class EmailUtil {
@Autowired
	private JavaMailSender sender;
public boolean send(String to, String subject, String text, FileSystemResource file,String text1) {
	boolean flag=false;
	try {
		MimeMessage message=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message, 
				 file!=null?true:false);
		helper.setTo(to);
		helper.setFrom("tusharsinare0202@gmail.com");
		helper.setSubject(subject);
		helper.setText(text +"\n"+text1);
		if(file!=null)
		helper.addAttachment(file.getFilename(),file);
		sender.send(message);
		flag=true;
		} catch (Exception e) {
		flag=false;
		e.printStackTrace();
		}
		return flag;
		}
		}

