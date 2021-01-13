package com.employee.model;

import java.sql.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;


public class EmployeeService {
	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeJDBCDAO();
	}

	public EmployeeVO addEmployee(String e_password, String e_identity, String e_name, String e_gender, java.sql.Date e_birth, 
			String e_email, String e_phone, String e_address, String e_title, int e_status, String st_id) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setE_password(e_password);
		employeeVO.setE_identity(e_identity);
		employeeVO.setE_name(e_name);
		employeeVO.setE_gender(e_gender);
		employeeVO.setE_birth(e_birth);
		employeeVO.setE_email(e_email);
		employeeVO.setE_phone(e_phone);
		employeeVO.setE_address(e_address);
		employeeVO.setE_title(e_title);
		employeeVO.setE_status(e_status);
		employeeVO.setSt_id(st_id);
		
		dao.insert(employeeVO);

		return employeeVO;
	}

	public EmployeeVO updateEmployee(String e_id, String e_password, String e_identity, String e_name, String e_gender, java.sql.Date e_birth, 
			String e_email, String e_phone, String e_address, String e_title, int e_status, String st_id) {

		EmployeeVO employeeVO = new EmployeeVO();
		
		employeeVO.setE_id(e_id);
		employeeVO.setE_password(e_password);
		employeeVO.setE_identity(e_identity);
		employeeVO.setE_name(e_name);
		employeeVO.setE_gender(e_gender);
		employeeVO.setE_birth(e_birth);
		employeeVO.setE_email(e_email);
		employeeVO.setE_phone(e_phone);
		employeeVO.setE_address(e_address);
		employeeVO.setE_title(e_title);
		employeeVO.setE_status(e_status);
		employeeVO.setSt_id(st_id);
		dao.update(employeeVO);

		return employeeVO;
	}
	
	public EmployeeVO updateEmployee_without(String e_id, String e_identity, String e_name, String e_gender, java.sql.Date e_birth, 
			String e_email, String e_phone, String e_address, String e_title, String st_id) {
		
		EmployeeVO employeeVO = new EmployeeVO();		
		employeeVO.setE_id(e_id);		
		employeeVO.setE_identity(e_identity);
		employeeVO.setE_name(e_name);
		employeeVO.setE_gender(e_gender);
		employeeVO.setE_birth(e_birth);
		employeeVO.setE_email(e_email);
		employeeVO.setE_phone(e_phone);
		employeeVO.setE_address(e_address);
		employeeVO.setE_title(e_title);		
		employeeVO.setSt_id(st_id);
		dao.update_without(employeeVO);
		
		return employeeVO;
	}
	
	public EmployeeVO updateEmployee_pwd(String e_id, String e_password) {
		
		EmployeeVO employeeVO = new EmployeeVO();		
		employeeVO.setE_id(e_id);		
		employeeVO.setE_password(e_password);
		dao.update_pwd(employeeVO);
		
		return employeeVO;
	}
	
	public EmployeeVO updateEmployee_status(String e_id, int e_status) {
		
		EmployeeVO employeeVO = new EmployeeVO();		
		employeeVO.setE_id(e_id);		
		employeeVO.setE_status(e_status);
		dao.update_status(employeeVO);
		
		return employeeVO;
	}

	public void deleteEmployee(String employeeVO) {
		dao.delete(employeeVO);
	}

	public EmployeeVO getOneEmployee(String employeeVO) {
		return dao.findByPrimaryKey(employeeVO);
	}
	public EmployeeVO getOneEmployee_email(String employeeVO) {
		return dao.findByEmail(employeeVO);
	}
	public List<EmployeeVO> getOneEmployee_e_name(String employeeVO) {
		return dao.findByPrimaryKey_e_name(employeeVO);
	}
	public EmployeeVO getOneEmployeePwd(String employeeVO) {
		return dao.getEmployeePwd(employeeVO);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}


	public String random() {			
			String code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			StringBuffer random_code = new StringBuffer();
			for(int i = 0; i < 8; i++) {
				random_code.append(code.charAt((int)(Math.random()*62)));			
			}		
			return random_code.toString();
	}
	
public static void sendEmail(String random_password,String e_mail) throws MessagingException {
		
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
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(e_mail));   //用MimeMessage物件set收件者
			message.setSubject("不是叫你滾嗎?");                                              //set主旨
			//按鈕
//			message.setContent(
//					"			<FORM METHOD=\"post\" ACTION=\"http://localhost:8081/TEA102G3/member/controller/MemberServlet\" style=\"margin-bottom: 0px;\">\r\n" + 
//					"			<input type=\"hidden\" name=\"action\" value=\"activeMember\">\r\n" + 
//					"			<input type=\"hidden\" name=\"m_email\" value=\""+to+"\">\r\n" + 
//					"            <input type=\"submit\" value=\"Click hear to active your account!\" id=\"revise\" class=\"btn btn-primary\">\r\n" + 
//					"            </FORM>","text/html");
			//超連結
			message.setContent("您的新密碼為 : " + random_password,"text/html; charset=UTF-8");
			System.out.println(random_password);
			System.out.println(e_mail);
			Transport transport= session.getTransport("smtp");						
			transport.connect(host,username,password);
            transport.sendMessage(message, message.getAllRecipients());
	        transport.close();		
			System.out.println("發送成功");
			
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		
	}
}
