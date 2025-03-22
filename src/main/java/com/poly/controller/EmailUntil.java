package com.poly.controller;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUntil {

	public static boolean sendEmail(String toEmail, String subject, String body) {
		final String email = "hoivdpd10303@gmail.com";
		final String password = "ijgg bahz druz odmz";

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
}
