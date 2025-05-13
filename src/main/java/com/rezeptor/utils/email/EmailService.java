package com.rezeptor.utils.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailSender{
	
	private JavaMailSender javaMailSender;
	private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	@Async
	public void send(String to, String email) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email,true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("inis9187@gmail.com");
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO: handle exception
			LOGGER.error("failed to send email",e);
			throw new IllegalStateException("failed to send email");
		}
		
	}

}
