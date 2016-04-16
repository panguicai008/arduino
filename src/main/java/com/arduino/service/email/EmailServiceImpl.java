package com.arduino.service.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public void sendCode(String email, String code) throws Exception {
		Session session = this.createSession();
		MimeMessage message = this.createMessage(session, email, code);
		Transport transport = session.getTransport();
		//transport.connect("smtp.126.com", "njwhck", "wanhong710310");
		transport.connect("smtp.163.com", 25, "18852995965", "panic796120");
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	public Session createSession() {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		return session;
	}

	public MimeMessage createMessage(Session session, String email, String text) throws Exception {
		MimeMessage msg = new MimeMessage(session);
//		msg.setFrom(new InternetAddress("njwhck@126.com"));
		msg.setFrom(new InternetAddress("18852995965@163.com"));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		msg.setSubject("验证码", "utf-8");
		msg.setText(text, "utf-8");
		msg.saveChanges();
		return msg;
	}

}
