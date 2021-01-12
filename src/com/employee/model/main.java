package com.employee.model;

import javax.mail.MessagingException;

public class main {
	public static void main(String[] args) throws MessagingException {
		EmployeeService service = new EmployeeService();
		service.sendEmail("123", "s10079000@gmail.com");
	}
}
