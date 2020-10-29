package com.quintet.meditech.service;

import com.quintet.meditech.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void emailSend(Token token) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("ashikmdshakilpranto@gmail.com");
        message.setSubject("Reset Password");
        message.setText("Your reset password link is "+"http://10.0.0.4:4200/reset-password/"+token.getTokenString());
        javaMailSender.send(message);
    }
}
