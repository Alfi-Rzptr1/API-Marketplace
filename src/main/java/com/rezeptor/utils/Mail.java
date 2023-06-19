package com.rezeptor.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Mail {
  
  @Autowired
  private static JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private static String senderAccount;

  public static void send(String to, String subject, String body){
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom(senderAccount);
    mailMessage.setTo(to);
    mailMessage.setSubject(body);
    mailMessage.setText(body);
    javaMailSender.send(mailMessage);
  }

}
