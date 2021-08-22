package com.project2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class JavaMailService {

	private JavaMailSender javaMailSender;
	
	
	@Bean
	public void sendEmail()throws Exception{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("thymefirst39@gmail.com");
		mail.setSubject("testing mail");
		mail.setText("we heckin got here bois!!!!!");
		
		javaMailSender.send(mail);
	}
}
