package com.sage.integrations;


import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {


	/**
	 * Sample usage:
	 * String from = "your_email@gmail.com";
	 * String password = "your_app_password";
	 * String to = "recipient@example.com";
	 * String cc = "cc@example.com";
	 * String file = "C:\\path\\to\\file.png";
	 * sendEmailWithAttachment(from, password, to, cc, "Subject", "HTML Body", file + "," + file);
	 */

	public static void sendEmailWithAttachment(String from, String password, String to, String cc, String emailTitle, String emailBody, String attachmentFilePath) {
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.enable", "true"); // Use SSL for port 465
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			if (cc != null && !cc.isBlank()) {
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			}

			message.setSubject(emailTitle);

			Multipart multipart = new MimeMultipart();

			// Email body part
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(emailBody, "text/html; charset=utf-8");
			multipart.addBodyPart(htmlPart);

			// Attach files
			if (attachmentFilePath != null && !attachmentFilePath.isBlank()) {
				String[] paths = attachmentFilePath.split(",");
				for (String path : paths) {
					File file = new File(path.trim());
					if (file.exists()) {
						MimeBodyPart attachmentPart = new MimeBodyPart();
						attachmentPart.attachFile(file);
						multipart.addBodyPart(attachmentPart);
					} else {
						System.out.println("Attachment file not found: " + path);
					}
				}
			}

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Email sent successfully!");
		} catch (MessagingException e) {
			System.out.println("Error sending email: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error attaching file: " + e.getMessage());
		}
	}
}