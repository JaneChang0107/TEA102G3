package com.member.controller;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.naming.factory.OpenEjbFactory;

import javax.activation.*;

public class SendEmail {
	
	public static void openMail(String status,String to) throws MessagingException {
		
		String host = "imap.gmail.com";                //主機端
		String username ="yuxikun102g3@gmail.com";      //寄件者
		String password ="kun102g3";                     //寄件者密碼
		String from = "yuxikun102g3@gmail.com";           //寄件者

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
		System.out.println("連結成功");
		
		try {
			MimeMessage message = new MimeMessage(session);                             //new一個MimeMessage物件
			message.setFrom(new InternetAddress(from));                                //用MimeMessage物件set寄件者
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));   //用MimeMessage物件set收件者
			message.setSubject("YuXiKun會員開通");                                              //set主旨
			//按鈕
//			message.setContent(
//					"			<FORM METHOD=\"post\" ACTION=\"http://localhost:8081/TEA102G3/member/controller/MemberServlet\" style=\"margin-bottom: 0px;\">\r\n" + 
//					"			<input type=\"hidden\" name=\"action\" value=\"activeMember\">\r\n" + 
//					"			<input type=\"hidden\" name=\"m_email\" value=\""+to+"\">\r\n" + 
//					"            <input type=\"submit\" value=\"Click hear to active your account!\" id=\"revise\" class=\"btn btn-primary\">\r\n" + 
//					"            </FORM>","text/html");
			//超連結
			message.setContent("<h2>Welcome to YuXiKun</h2><br><a href=\"http://localhost:8081/TEA102G3/member/controller/MemberServlet?action=activeMember&m_status="+status+"&m_email="+to+"\">點選此處以開通</a>","text/html; charset=UTF-8");
			System.out.println(status);
			System.out.println(to);
			Transport transport= session.getTransport("smtp");						
			transport.connect(host,username,password);
            transport.sendMessage(message, message.getAllRecipients());
	        transport.close();		
			System.out.println("發送成功");
			
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		
	}
					
//	public static void main(String[] args) throws MessagingException {
//		SendEmail.openMail("2","aceg19682@gmail.com");
//	}
//	System.out.println(<h2>Welcome to YuXiKun</h2><br><a href="http://localhost:8081/TEA102G3/member/controller/MemberServlet?action=activeMember&m_status="+status+"&m_email="+to+"">click to join</a>
//);
	
}