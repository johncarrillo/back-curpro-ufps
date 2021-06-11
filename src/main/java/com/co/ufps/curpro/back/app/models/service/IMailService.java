package com.co.ufps.curpro.back.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


public interface IMailService {

	public void sendMail(String from, String to, String subject, String body);
}
