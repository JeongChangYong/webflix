package webflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSendService {
	@Autowired
	JavaMailSender javaMailSender;
	public void mailsend(String html, String subject, String fromEmail, String toEmail) {
		MimeMessage msg = javaMailSender.createMimeMessage();
	 	try {
			msg.setHeader("content-type", "text/html; charset=UTF-8");
			msg.setContent(html, "text/html; charset=UTF-8");
		 	msg.setSubject(subject);
		 	msg.setFrom(new InternetAddress(fromEmail));
		 	msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
		 	javaMailSender.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
