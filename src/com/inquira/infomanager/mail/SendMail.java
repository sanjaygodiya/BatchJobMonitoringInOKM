package com.inquira.infomanager.mail;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.inquira.infomanager.config.ReadConfig;

public class SendMail {

	private static final Logger log = Logger.getLogger(SendMail.class);

	public static void sendMail(String mailbody) throws IOException {
		String to = ReadConfig.getPropValues("mail.to");
		String from = ReadConfig.getPropValues("mail.from");
		String host = ReadConfig.getPropValues("mail.host");

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("OKB batch job run report");
			message.setContent(mailbody, "text/html");

			Transport.send(message);
			log.debug(mailbody);
			log.info("mail sent successfully....");
		} catch (MessagingException mex) {
			log.debug(mailbody);
			log.info("mail sending failed :" + mex.getMessage());
		}
	}

}
