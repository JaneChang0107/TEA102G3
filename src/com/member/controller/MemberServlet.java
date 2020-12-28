package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/member/controller/MemberServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//�d�߳�ӷ|��
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//1.�����ШD�ѼơA��J�榡�����~�B�z
				String str =req.getParameter("m_id");
				if(str == null || (str.trim()).length()==0) {
					errorMsgs.add("�п�J�|��ID");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/Front_end/members/select_page.jsp");
				    failureView.forward(req, res);
				    return;
				}
				
				String m_id = null;
				try {
					m_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�|��ID�榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//2.�}�l�d�߸��
				MemberService memSvc =new MemberService();
				MemberVO memberVO = memSvc.findOneMem(m_id);
				if(memberVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/Front_end/members/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//3.�汵�����A�ǳ����
				req.setAttribute("memberVO", memberVO);
				String url ="/Front_end/members/listOneMem.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
			    
				//4.��L�i�઺���~�B�z
			}catch(Exception e) {	
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/Front_end/members/select_page.jsp");
				failureView.forward(req, res);
			}
        }	
		
//���@�Ӷi��ק�
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 1.�����ШD�Ѽ�
				String m_id =new String(req.getParameter("m_id"));
				// 2.�}�l�d�߸��
				MemberService memSvc =new MemberService();
				MemberVO memberVO=memSvc.findOneMem(m_id);
                // 3.�d�ߧ���,�ǳ����
				req.setAttribute("memberVO", memberVO);
				String url ="/Front_end/members/update_mem_input.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/listAllMem.jsp");
				failureView.forward(req, res);				
			}
		}

//�ǳƶi��ӤH�ɮ׭ק�
		if ("Myfileupdate".equals(action)) {
			try {
				// 1.�����ШD�Ѽ�
				HttpSession session =req.getSession();
				String m_id = session.getAttribute("loginId").toString();
				// 2.�}�l�d�߸��
				MemberService memSvc =new MemberService();
				MemberVO memberVO=memSvc.findOneMem(m_id);
                // 3.�d�ߧ���,�ǳ����
				session.setAttribute("memberVO", memberVO);
				String url ="/Front_end/members/MyAccountRevise.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/MyAccount.jsp");
				failureView.forward(req, res);				
			}
		}
		
//�ӤH�ɮ׭ק�
		if ("Myfileupdateconfirm".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 1.�����ШD�ѼơA��J�榡���~�B�z
			try {
			String m_id =new String(req.getParameter("m_id").trim());
			
			String m_email = req.getParameter("m_email");
//			String m_emailReg = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
			if (m_email == null || m_email.trim().length() == 0) {
				errorMsgs.add("�l�c�ФŪť�");
			} 
//			else if (!m_email.trim().matches(m_emailReg)) {
//				errorMsgs.add("�l�c���ŦX�榡!�Э��s��J");
//			}
			
			String m_password = req.getParameter("m_password").trim();
			if (m_password == null || m_password.trim().length() == 0) {
				errorMsgs.add("�K�X�ФŪť�");
			}
			
			String m_name = req.getParameter("m_name");
			String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (m_name == null || m_name.trim().length() == 0) {
				errorMsgs.add("�m�W�ФŪť�");
			} else if (!m_name.trim().matches(m_nameReg)) {
				errorMsgs.add("�m�W�u�ର���^��B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}
			String m_gender = req.getParameter("m_gender");
			
			if (m_gender == null || m_gender.trim().length() == 0) {
				errorMsgs.add("�п�ܩʧO��");
			}
		
			
			String m_phone = req.getParameter("m_phone");
			if (m_phone == null || m_phone.trim().length() == 0) {
				errorMsgs.add("�q�ܽФŪť�");
			}else if(m_phone.length() <=8 || m_phone.length()>10 ) {
				errorMsgs.add("�q�ܸ��X��Ƥ���");
			}

			String m_address = req.getParameter("m_address");
			if (m_address == null || m_address.trim().length() == 0) {
				errorMsgs.add("�a�}�ФŪť�");
			}
			
			java.sql.Date m_birth = null;
			try {
				m_birth = java.sql.Date.valueOf(req.getParameter("m_birth").trim());
			} catch (IllegalArgumentException e) {
				m_birth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("�п�ܥͤ�");
			}
			
			MemberService memSvc =new MemberService();  //�ŧiService����ӥ�
			Part m_headpicpart =req.getPart("m_headpic");   //�W�ǥ��ϥ�Part xxx=req.getPart("�Y�ӰѼ�") ����Part
			InputStream is =m_headpicpart.getInputStream();		//��part�s��InputSream������
			byte[] m_headpic =null;                             //�ŧibyte[]�}�C
			if(is.available()!=0) {                               //�p�G�ޤl�����ӷ����O0
				m_headpic =new byte[is.available()];                
			    is.read(m_headpic);
			    is.close();
			} else 
				m_headpic = memSvc.findOneMem(m_id).getM_headpic();
			
			String m_account = req.getParameter("m_account");			
			String m_accountName =req.getParameter("m_accountName");
		
			String b_code =req.getParameter("b_code");
				
			
			MemberVO memberVO = new MemberVO();
			memberVO.setM_id(m_id);
			memberVO.setM_email(m_email);
			memberVO.setM_password(m_password);
			memberVO.setM_gender(m_gender);
			memberVO.setM_name(m_name);
			memberVO.setM_phone(m_phone);
			memberVO.setM_address(m_address);
			memberVO.setM_birth(m_birth);
			memberVO.setM_headpic(m_headpic);
			memberVO.setM_account(m_account);
			memberVO.setM_accountName(m_accountName);
			memberVO.setB_code(b_code);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/MyAccountRevise.jsp");
				failureView.forward(req, res);
				return;
			}

			// 2.�}�l�ק���
			System.out.println("�}�l�ק�");
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMem( m_email,  m_password,  m_name,  m_gender,  m_phone,
					 m_address, m_birth, m_headpic,m_account,m_accountName,b_code, m_id);//�o��memberVO����H������B�z
            System.out.println("�ק粒��");
            
			// 3.�ק粒���A�ǳ����
            req.setAttribute("memberVO", memberVO);  //��Ʈwupdate�����A�NmemberVO����s�Jreq
			String url= "/Front_end/members/MyAccount.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			// ����L�ҥ~
			}catch(Exception e) {
				System.out.println("�ҥ~�o��");
				errorMsgs.add("��ƭק異��"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/MyAccountRevise.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
//update�欰�ŦX�A�ק�|�����
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 1.�����ШD�ѼơA��J�榡���~�B�z
			try {
			String m_id =new String(req.getParameter("m_id").trim());
			
			String m_email = req.getParameter("m_email");
			String m_emailReg = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
			if (m_email == null || m_email.trim().length() == 0) {
				errorMsgs.add("�l�c�ФŪť�");
			} 
			else if (!m_email.trim().matches(m_emailReg)) {
				errorMsgs.add("�l�c���ŦX�榡!�Э��s��J");
			}
			
			String m_password = req.getParameter("m_password").trim();
			if (m_password == null || m_password.trim().length() == 0) {
				errorMsgs.add("�K�X�ФŪť�");
			}
			String m_name = req.getParameter("m_name");
			String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (m_name == null || m_name.trim().length() == 0) {
				errorMsgs.add("�m�W�ФŪť�");
			} else if (!m_name.trim().matches(m_nameReg)) {
				errorMsgs.add("�m�W�u�ର���^��B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}
			String m_gender = req.getParameter("m_gender");
			
			if (m_gender == null || m_gender.trim().length() == 0) {
				errorMsgs.add("�п�ܩʧO��");
			}
		
			
			String m_phone = req.getParameter("m_phone");
			if (m_phone == null || m_phone.trim().length() == 0) {
				errorMsgs.add("�q�ܽФŪť�");
			}else if(m_phone.length() <=8 || m_phone.length()>10 ) {
				errorMsgs.add("�q�ܸ��X��Ƥ���");
			}

			String m_address = req.getParameter("m_address");
			if (m_address == null || m_address.trim().length() == 0) {
				errorMsgs.add("�a�}�ФŪť�");
			}
			
			java.sql.Date m_birth = null;
			try {
				m_birth = java.sql.Date.valueOf(req.getParameter("m_birth").trim());
			} catch (IllegalArgumentException e) {
				m_birth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("�п�ܥͤ�");
			}
			
			MemberService memSvc =new MemberService();  //�ŧiService����ӥ�
			Part m_headpicpart =req.getPart("m_headpic");   //�W�ǥ��ϥ�Part xxx=req.getPart("�Y�ӰѼ�") ����Part
			InputStream is =m_headpicpart.getInputStream();		//��part�s��InputSream������
			byte[] m_headpic =null;                             //�ŧibyte[]�}�C
			if(is.available()!=0) {                               //�p�G�ޤl�����ӷ����O0
				m_headpic =new byte[is.available()];                
			    is.read(m_headpic);
			    is.close();
			} else 
				m_headpic = memSvc.findOneMem(m_id).getM_headpic();
			
			String m_identity = req.getParameter("m_identity");
			if (m_identity == null || m_identity.trim().length() == 0) {
				errorMsgs.add("�����Ҧr���ФŪť�");
			}
			
			
			Part m_id_picpart = req.getPart("m_id_pic");
            InputStream is2 = m_id_picpart.getInputStream();
            byte[] m_id_pic =null;
            if(is2.available()!=0) {
            	m_id_pic=new byte[is2.available()];
            	is2.read(m_id_pic);
            	is2.close();
            }
            m_id_pic = memSvc.findOneMem(m_id).getM_id_pic();
			
			String m_account = req.getParameter("m_account");
			if (m_account == null || m_account.trim().length() == 0) {
				errorMsgs.add("�Ȧ�b��ФŪť�");
			}
			
			String m_accountName =req.getParameter("m_accountName");
			if (m_accountName == null || m_accountName.trim().length() == 0) {
				errorMsgs.add("��W�ФŪť�");
			}
			
			String b_code =req.getParameter("b_code");
			
            Part m_bank_picpart =req.getPart("m_bank_pic");
            InputStream is3 = m_bank_picpart.getInputStream();
            byte[] m_bank_pic=null;
            if(is3.available()!=0) {
            	m_bank_pic = new byte[is3.available()];
            	is3.read(m_bank_pic);
            	is3.close();
            }else
            	m_bank_pic = memSvc.findOneMem(m_id).getM_bank_pic();
            
			
			String m_storename =req.getParameter("m_storename");
			
			String m_info =req.getParameter("m_info");
			
			Part m_coverpart =req.getPart("m_cover");
			InputStream is4 = m_coverpart.getInputStream();
			byte[] m_cover=null;
			if(is4.available()!=0) {
				m_cover =new byte[is4.available()];
				is4.read(m_cover);
				is4.close();
			}else
			    m_cover = memSvc.findOneMem(m_id).getM_cover();
			
			String m_hi =req.getParameter("m_hi");
			
			String m_offlineHi =req.getParameter("m_offlineHi");
					
			MemberVO memberVO = new MemberVO();
			memberVO.setM_id(m_id);
			memberVO.setM_email(m_email);
			memberVO.setM_password(m_password);
			memberVO.setM_name(m_name);
			memberVO.setM_gender(m_gender);
			memberVO.setM_phone(m_phone);
			memberVO.setM_address(m_address);
			memberVO.setM_birth(m_birth);
			memberVO.setM_headpic(m_headpic);
			memberVO.setM_identity(m_identity);
			memberVO.setM_id_pic(m_id_pic);
			memberVO.setM_account(m_account);
			memberVO.setM_accountName(m_accountName);
			memberVO.setB_code(b_code);
			memberVO.setM_bank_pic(m_bank_pic);
			memberVO.setM_storename(m_storename);
			memberVO.setM_info(m_info);
			memberVO.setM_cover(m_cover);
			memberVO.setM_hi(m_hi);
			memberVO.setM_offlineHi(m_offlineHi);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/update_mem_input.jsp");
				failureView.forward(req, res);
				return;
			}

			// 2.�}�l�ק���
			System.out.println("�}�l�ק�");
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMem( m_email,  m_password,  m_name,  m_gender,  m_phone,
					 m_address, m_birth, m_headpic,  m_identity, m_id_pic,  m_account,  m_accountName,  b_code, m_bank_pic,
					 m_storename,  m_info,  m_cover,  m_hi, m_offlineHi, m_id);//�o��memberVO����H������B�z
            System.out.println("�ק粒��");
            
			// 3.�ק粒���A�ǳ����
            req.setAttribute("memberVO", memberVO);  //��Ʈwupdate�����A�NmemberVO����s�Jreq
			String url= "/Front_end/members/listOneMem.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			// ����L�ҥ~
			}catch(Exception e) {
				System.out.println("�ҥ~�o��");
				errorMsgs.add("��ƭק異��"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
	
// insert�ѼƲŦX�A����s�W�@��|��
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
		    // 1.�����ШD�ѼơA��J�榡���~�B�z
			String m_email = req.getParameter("m_email");
			String m_emailReg = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
			
			MemberService memSvc =new MemberService();
			MemberVO memberVO2=memSvc.getMemberPw(m_email);
			String signedmail = "";
			if(memberVO2 != null) {
				signedmail = memberVO2.getM_email();
			}
			
			if (m_email == null || m_email.trim().length() == 0) {
				errorMsgs.add("�l�c�ФŪť�");
			} 
			if (!m_email.trim().matches(m_emailReg)) {
				errorMsgs.add("�l�c���ŦX�榡!�Э��s��J");
			} 
			if(signedmail != null && m_email.equals(signedmail)) {
				errorMsgs.add("�l�c�w�s�b�A�Э��s���U");
			}			
			String m_password = req.getParameter("m_password").trim();
			if (m_password == null || m_password.trim().length() == 0) {
				errorMsgs.add("�K�X�ФŪť�");
			}
			String m_name = req.getParameter("m_name");
			String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (m_name == null || m_name.trim().length() == 0) {
				errorMsgs.add("�m�W�ФŪť�");
			} else if (!m_name.trim().matches(m_nameReg)) {
				errorMsgs.add("�m�W�u�ର���^��B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}

			String m_gender = req.getParameter("m_gender");
			if (m_gender == null || m_gender.trim().length() == 0) {
				m_gender="";
				errorMsgs.add("�п�ܩʧO��");
			}

			String m_phone = req.getParameter("m_phone");
			if (m_phone == null || m_phone.trim().length() == 0) {
				errorMsgs.add("�q�ܽФŪť�");
			} if(m_phone.length() <=8 || m_phone.length()>10 ) {
				errorMsgs.add("�q�ܸ��X��Ƥ���");
			}

			String m_address = req.getParameter("m_address");
			if (m_address == null || m_address.trim().length() == 0) {
				errorMsgs.add("�a�}�ФŪť�");
			}
			
			java.sql.Date m_birth = null;
			try {
				m_birth = java.sql.Date.valueOf(req.getParameter("m_birth").trim());
			} catch (IllegalArgumentException e) {
				m_birth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("�п�ܥͤ�");
			}
			
			MemberVO memberVO = new MemberVO();
			memberVO.setM_email(m_email);
			memberVO.setM_password(m_password);
			memberVO.setM_name(m_name);
			memberVO.setM_gender(m_gender);
			memberVO.setM_phone(m_phone);
			memberVO.setM_address(m_address);
			memberVO.setM_birth(m_birth);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);			
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/addMem.jsp");
				failureView.forward(req, res);
				return ;
			}

			// 2.�}�l�s�W���
			System.out.println("�}�l�s�W");
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMem(m_email, m_password, m_name,m_gender, m_phone, m_address, m_birth);//�o��memberVO����H������B�z
            System.out.println("�s�W����");
            
			// 3.�s�W�����A�ǳ����
			String url= "/Front_end/members/listAllMem.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			// ����L�ҥ~
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/addMem.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
//insert2�ѼƦ欰�ŦX�A�s�W��a�|��
		if ("insert2".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 1.�����ШD�ѼơA��J�榡���~�B�z
			try {
			String m_email = req.getParameter("m_email");
	        String m_emailReg = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
		    if (m_email == null || m_email.trim().length() == 0) {
					errorMsgs.add("�l�c�ФŪť�");
			} 
			else if (!m_email.trim().matches(m_emailReg)) {
					errorMsgs.add("�l�c���ŦX�榡!�Э��s��J");
			}
			String m_password = req.getParameter("m_password").trim();
			if (m_password == null || m_password.trim().length() == 0) {
				errorMsgs.add("�K�X�ФŪť�");
			}
			String m_name = req.getParameter("m_name");
			String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (m_name == null || m_name.trim().length() == 0) {
				errorMsgs.add("�m�W�ФŪť�");
			} else if (!m_name.trim().matches(m_nameReg)) {
				errorMsgs.add("�m�W�u�ର���^��B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}
			String m_gender = req.getParameter("m_gender");
			if (m_gender == null || m_gender.trim().length() == 0) {
				m_gender="";
				errorMsgs.add("�п�ܩʧO��");
			}
			String m_phone = req.getParameter("m_phone");
			if (m_phone == null || m_phone.trim().length() == 0) {
				errorMsgs.add("�q�ܽФŪť�");
			} if(m_phone.length() <=8 || m_phone.length()>10 ) {
				errorMsgs.add("�q�ܸ��X��Ƥ���");
			}

			String m_address = req.getParameter("m_address");
			if (m_address == null || m_address.trim().length() == 0) {
				errorMsgs.add("�a�}�ФŪť�");
			}
			
			java.sql.Date m_birth = null;
			try {
				m_birth = java.sql.Date.valueOf(req.getParameter("m_birth").trim());
			} catch (IllegalArgumentException e) {
				m_birth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("�п�ܥͤ�");
			}
			
			Part m_headpic = req.getPart("m_headpic");
			InputStream is =m_headpic.getInputStream();
			
			byte[] m_headpicbuffer=null;
			try {
				m_headpicbuffer = new byte[is.available()];
				is.read(m_headpicbuffer);
				is.close();
			} catch (Exception e) {
				errorMsgs.add("�j�Y�K�W�ǥ��ѡA�ЦA�դ@��");
				e.printStackTrace();
			}
			
			String m_identity = req.getParameter("m_identity");
			if (m_identity == null || m_identity.trim().length() == 0) {
				errorMsgs.add("�����Ҧr���ФŪť�");
			}
			
			Part m_id_pic=req.getPart("m_id_pic");
			InputStream is2 =m_id_pic.getInputStream();
			byte[] m_id_picbuffer=null;
			try {
				m_id_picbuffer = new byte[is2.available()];
				is2.read(m_id_picbuffer);
				is2.close();
			} catch (Exception e) {
				errorMsgs.add("�����ҷӤ��W�ǥ��ѡA�ЦA�դ@��");
				e.printStackTrace();
			}
			if(m_id_picbuffer==null || m_id_picbuffer.length==0) {
				errorMsgs.add("�ФW�Ǩ����ҷӤ�");
			}
			
			String m_account = req.getParameter("m_account");
			if (m_account == null || m_account.trim().length() == 0) {
				errorMsgs.add("�Ȧ�b��ФŪť�");
			}
			
			String m_accountName =req.getParameter("m_accountName");
			if (m_accountName == null || m_accountName.trim().length() == 0) {
				errorMsgs.add("��W�ФŪť�");
			}
			
			String b_code =req.getParameter("b_code");
			if(b_code==null || b_code.trim().length()==0) {
				errorMsgs.add("�Ȧ�N���ФŪť�");
			}if(b_code.length()!=3) {
				errorMsgs.add("�Ȧ�N����ƿ��~�A�Э��s��J");
			}
			
			Part m_bank_pic =req.getPart("m_bank_pic");
			InputStream is3 = m_bank_pic.getInputStream();
			byte[] m_bank_picbuffer=null;
			try {
				m_bank_picbuffer =new byte[is3.available()];
				is3.read(m_bank_picbuffer);
				is3.close();
			} catch (Exception e) {
				errorMsgs.add("�s�P�Ӥ��W�ǥ��ѡA�ЦA�դ@��");
				e.printStackTrace();
			}
			
			if(m_bank_picbuffer==null || m_bank_picbuffer.length==0) {
				errorMsgs.add("�ФW�ǻȦ�b��Ӥ�");
			}
			
			String m_storename =req.getParameter("m_storename");
			
			String m_info =req.getParameter("m_info");
			
			
            Part m_cover=req.getPart("m_cover");
            InputStream is4 = m_cover.getInputStream();
            byte[] m_coverbuffer =null;
            try {
            	m_coverbuffer =new byte[is4.available()];
            	is4.read(m_coverbuffer);
            	is4.close();
            }catch(Exception e) {
            	errorMsgs.add("����ʭ��W�ǥ��ѡA�ЦA�դ@��");
            	e.printStackTrace();
            }
            
			String m_hi =req.getParameter("m_hi");
			String m_offlineHi =req.getParameter("m_offlineHi");
			
			MemberVO memberVO = new MemberVO();
			
			memberVO.setM_email(m_email);
			memberVO.setM_password(m_password);
			memberVO.setM_name(m_name);
			memberVO.setM_gender(m_gender);
			memberVO.setM_phone(m_phone);
			memberVO.setM_address(m_address);
			memberVO.setM_birth(m_birth);
			memberVO.setM_headpic(m_headpicbuffer);
			memberVO.setM_identity(m_identity);
			memberVO.setM_id_pic(m_id_picbuffer);
			memberVO.setM_account(m_account);
			memberVO.setM_accountName(m_accountName);
			memberVO.setB_code(b_code);
			memberVO.setM_bank_pic(m_bank_picbuffer);
			
			memberVO.setM_storename(m_storename);
			memberVO.setM_info(m_info);
			memberVO.setM_cover(m_coverbuffer);
			memberVO.setM_hi(m_hi);
			memberVO.setM_offlineHi(m_offlineHi);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/addMemSeller.jsp");
				failureView.forward(req, res);
				return;
			}

			// 2.�}�l�s�W���
			System.out.println("�}�l�s�W");
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMem2( m_email,  m_password,  m_name,  m_gender,  m_phone,
					 m_address, m_birth, m_headpicbuffer,  m_identity, m_id_picbuffer,  m_account,  m_accountName,  b_code, m_bank_picbuffer,
					 m_storename,  m_info,  m_coverbuffer,  m_hi, m_offlineHi);//�o��memberVO����H������B�z
            System.out.println("�s�W����");
            
			// 3.�s�W�����A�ǳ����
			String url= "/Front_end/members/listAllMem.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			// ����L�ҥ~
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/addMemSeller.jsp");
				failureView.forward(req, res);
			}
			
		}
//delete�ѼƦ欰�ŦX�A�R���|��
		if("delete".equals(action)) {
			List<String> errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 1. �����ШD�Ѽ�
				String m_id =new String (req.getParameter("m_id"));
				
				// 2. �}�l�R��
				MemberService memSvc =new MemberService();
				memSvc.deleteMem(m_id);
			
			    // 3. �R�������A�ǳ����
		        String url ="/Front_end/members/listAllMem.jsp";	
		        RequestDispatcher successView =req.getRequestDispatcher(url);
		        successView.forward(req, res);			
			    
		        // ��L���~�B�z
			}catch(Exception e) {
				errorMsgs.add("��ƧR������"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/listAllMem.jsp");
			    failureView.forward(req, res);
			}
		}

	}

}
