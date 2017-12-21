package com.senzit.cyberclaz.server.subservice;


import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.senzit.cyberclaz.server.model.CyberInit;



	public class EmailSms {
	 
	 public static void sendMail(String to, String subject, String body) throws IOException, AddressException, MessagingException
	 {        
		
	  String mailPort    = CyberInit.cyberProperty.getProperty("outgoingMailPort");
	  String mailServer    = CyberInit.cyberProperty.getProperty("outgoingMailServer");
	  final String mailUserName  = CyberInit.cyberProperty.getProperty("outgoingMailUserName");
	  final String mailPassword  = CyberInit.cyberProperty.getProperty("outgoingMailPassword");
	 
	  Properties newProp = new Properties();

	  newProp.put("mail.smtp.host", mailServer);  
	  newProp.put("mail.smtp.socketFactory.port", mailPort);  
	  newProp.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
	  newProp.put("mail.smtp.auth", "true");  
	  newProp.put("mail.smtp.port", "25"); 
	  
	  Session session = Session.getDefaultInstance(newProp, new javax.mail.Authenticator() 
	  {  
	   protected PasswordAuthentication getPasswordAuthentication()
	   {  
	     return new PasswordAuthentication(mailUserName, mailPassword);  
	   }  

	  });
	  
	  Message message = new MimeMessage(session); 

	  message.setFrom(new InternetAddress(mailUserName));  
	  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));  
	 
	  message.setSubject(subject);  
	  message.setText(body);  

	  Transport.send(message);
	  
	 }

	}


