package com.member.controller;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {
	
	public static void openMail(String to) throws MessagingException {
		
		String host = "imap.gmail.com";                //�D����
		String username ="yuxikun102g3@gmail.com";      //�H���
		String password ="kun102g3";                     //�H��̱K�X
		String from = "yuxikun102g3@gmail.com";           //�H���

		Properties properties =new Properties();
		properties.put("mail.imap.ssl.enable","true");
		properties.put("mail.smtp.auth", "true");  
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");	
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		
		properties.setProperty("mail.user", username);
		properties.setProperty("mail.password", password);
		
		Session session = Session.getDefaultInstance(properties);  // Get the default Session object.
		Store store =session.getStore("imap");
		store.connect(host, username, password);
		System.out.println("�s�����\");
		
		try {
			MimeMessage message = new MimeMessage(session);                             //new�@��MimeMessage����
			message.setFrom(new InternetAddress(from));                                //��MimeMessage����set�H���
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));   //��MimeMessage����set�����
			message.setSubject("YuXiKun�|���}�q");                                              //set�D��
			message.setContent(
					"			<FORM METHOD=\"post\" ACTION=\"http://localhost:8081/TEA102G3/member/controller/MemberServlet\" style=\"margin-bottom: 0px;\">\r\n" + 
					"			<input type=\"hidden\" name=\"action\" value=\"activeMember\">\r\n" + 
					"			<input type=\"hidden\" name=\"m_email\" value=\""+to+"\">\r\n" + 
					"            <input type=\"submit\" value=\"Click hear to active your account!\" id=\"revise\" class=\"btn btn-primary\">\r\n" + 
					"            </FORM>","text/html");
			System.out.println(to);
			Transport transport= session.getTransport("smtp");						
			transport.connect(host,username,password);
            transport.sendMessage(message, message.getAllRecipients());
	        transport.close();		
			System.out.println("�o�e���\");
			
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		
	}
}