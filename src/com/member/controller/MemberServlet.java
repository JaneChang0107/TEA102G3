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

//查詢單個會員
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//1.接收請求參數，輸入格式的錯誤處理
				String str =req.getParameter("m_id");
				if(str == null || (str.trim()).length()==0) {
					errorMsgs.add("請輸入會員ID");
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
					errorMsgs.add("會員ID格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//2.開始查詢資料
				MemberService memSvc =new MemberService();
				MemberVO memberVO = memSvc.findOneMem(m_id);
				if(memberVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView =req.getRequestDispatcher("/Front_end/members/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//3.交接完成，準備轉交
				req.setAttribute("memberVO", memberVO);
				String url ="/Front_end/members/listOneMem.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
			    
				//4.其他可能的錯誤處理
			}catch(Exception e) {	
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/Front_end/members/select_page.jsp");
				failureView.forward(req, res);
			}
        }	
		
//取一個進行修改
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 1.接收請求參數
				String m_id =new String(req.getParameter("m_id"));
				// 2.開始查詢資料
				MemberService memSvc =new MemberService();
				MemberVO memberVO=memSvc.findOneMem(m_id);
                // 3.查詢完成,準備轉交
				req.setAttribute("memberVO", memberVO);
				String url ="/Front_end/members/update_mem_input.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/listAllMem.jsp");
				failureView.forward(req, res);				
			}
		}

//準備進行個人檔案修改
		if ("Myfileupdate".equals(action)) {
			try {
				// 1.接收請求參數
				HttpSession session =req.getSession();
				String m_id = session.getAttribute("loginId").toString();
				// 2.開始查詢資料
				MemberService memSvc =new MemberService();
				MemberVO memberVO=memSvc.findOneMem(m_id);
                // 3.查詢完成,準備轉交
				session.setAttribute("memberVO", memberVO);
				String url ="/Front_end/members/MyAccountRevise.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/MyAccount.jsp");
				failureView.forward(req, res);				
			}
		}
		
//個人檔案修改
		if ("Myfileupdateconfirm".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 1.接收請求參數，輸入格式錯誤處理
			try {
			String m_id =new String(req.getParameter("m_id").trim());
			
			String m_email = req.getParameter("m_email");
//			String m_emailReg = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
			if (m_email == null || m_email.trim().length() == 0) {
				errorMsgs.add("郵箱請勿空白");
			} 
//			else if (!m_email.trim().matches(m_emailReg)) {
//				errorMsgs.add("郵箱不符合格式!請重新輸入");
//			}
			
			String m_password = req.getParameter("m_password").trim();
			if (m_password == null || m_password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			
			String m_name = req.getParameter("m_name");
			String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (m_name == null || m_name.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			} else if (!m_name.trim().matches(m_nameReg)) {
				errorMsgs.add("姓名只能為中英文、數字和_ , 且長度必需在2到10之間");
			}
			String m_gender = req.getParameter("m_gender");
			
			if (m_gender == null || m_gender.trim().length() == 0) {
				errorMsgs.add("請選擇性別欄");
			}
		
			
			String m_phone = req.getParameter("m_phone");
			if (m_phone == null || m_phone.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			}else if(m_phone.length() <=8 || m_phone.length()>10 ) {
				errorMsgs.add("電話號碼位數不符");
			}

			String m_address = req.getParameter("m_address");
			if (m_address == null || m_address.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}
			
			java.sql.Date m_birth = null;
			try {
				m_birth = java.sql.Date.valueOf(req.getParameter("m_birth").trim());
			} catch (IllegalArgumentException e) {
				m_birth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請選擇生日");
			}
			
			MemberService memSvc =new MemberService();  //宣告Service物件來用
			Part m_headpicpart =req.getPart("m_headpic");   //上傳必使用Part xxx=req.getPart("某個參數") 拿到Part
			InputStream is =m_headpicpart.getInputStream();		//用part連接InputSream高階管
			byte[] m_headpic =null;                             //宣告byte[]陣列
			if(is.available()!=0) {                               //如果管子接的來源不是0
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

			// 2.開始修改資料
			System.out.println("開始修改");
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMem( m_email,  m_password,  m_name,  m_gender,  m_phone,
					 m_address, m_birth, m_headpic,m_account,m_accountName,b_code, m_id);//得到memberVO物件以做後續處理
            System.out.println("修改完成");
            
			// 3.修改完成，準備轉交
            req.setAttribute("memberVO", memberVO);  //資料庫update完成，將memberVO物件存入req
			String url= "/Front_end/members/MyAccount.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			// 抓到其他例外
			}catch(Exception e) {
				System.out.println("例外發生");
				errorMsgs.add("資料修改失敗"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/MyAccountRevise.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
//update行為符合，修改會員資料
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 1.接收請求參數，輸入格式錯誤處理
			try {
			String m_id =new String(req.getParameter("m_id").trim());
			
			String m_email = req.getParameter("m_email");
			String m_emailReg = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
			if (m_email == null || m_email.trim().length() == 0) {
				errorMsgs.add("郵箱請勿空白");
			} 
			else if (!m_email.trim().matches(m_emailReg)) {
				errorMsgs.add("郵箱不符合格式!請重新輸入");
			}
			
			String m_password = req.getParameter("m_password").trim();
			if (m_password == null || m_password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			String m_name = req.getParameter("m_name");
			String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (m_name == null || m_name.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			} else if (!m_name.trim().matches(m_nameReg)) {
				errorMsgs.add("姓名只能為中英文、數字和_ , 且長度必需在2到10之間");
			}
			String m_gender = req.getParameter("m_gender");
			
			if (m_gender == null || m_gender.trim().length() == 0) {
				errorMsgs.add("請選擇性別欄");
			}
		
			
			String m_phone = req.getParameter("m_phone");
			if (m_phone == null || m_phone.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			}else if(m_phone.length() <=8 || m_phone.length()>10 ) {
				errorMsgs.add("電話號碼位數不符");
			}

			String m_address = req.getParameter("m_address");
			if (m_address == null || m_address.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}
			
			java.sql.Date m_birth = null;
			try {
				m_birth = java.sql.Date.valueOf(req.getParameter("m_birth").trim());
			} catch (IllegalArgumentException e) {
				m_birth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請選擇生日");
			}
			
			MemberService memSvc =new MemberService();  //宣告Service物件來用
			Part m_headpicpart =req.getPart("m_headpic");   //上傳必使用Part xxx=req.getPart("某個參數") 拿到Part
			InputStream is =m_headpicpart.getInputStream();		//用part連接InputSream高階管
			byte[] m_headpic =null;                             //宣告byte[]陣列
			if(is.available()!=0) {                               //如果管子接的來源不是0
				m_headpic =new byte[is.available()];                
			    is.read(m_headpic);
			    is.close();
			} else 
				m_headpic = memSvc.findOneMem(m_id).getM_headpic();
			
			String m_identity = req.getParameter("m_identity");
			if (m_identity == null || m_identity.trim().length() == 0) {
				errorMsgs.add("身分證字號請勿空白");
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
				errorMsgs.add("銀行帳戶請勿空白");
			}
			
			String m_accountName =req.getParameter("m_accountName");
			if (m_accountName == null || m_accountName.trim().length() == 0) {
				errorMsgs.add("戶名請勿空白");
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

			// 2.開始修改資料
			System.out.println("開始修改");
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMem( m_email,  m_password,  m_name,  m_gender,  m_phone,
					 m_address, m_birth, m_headpic,  m_identity, m_id_pic,  m_account,  m_accountName,  b_code, m_bank_pic,
					 m_storename,  m_info,  m_cover,  m_hi, m_offlineHi, m_id);//得到memberVO物件以做後續處理
            System.out.println("修改完成");
            
			// 3.修改完成，準備轉交
            req.setAttribute("memberVO", memberVO);  //資料庫update完成，將memberVO物件存入req
			String url= "/Front_end/members/listOneMem.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			// 抓到其他例外
			}catch(Exception e) {
				System.out.println("例外發生");
				errorMsgs.add("資料修改失敗"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
	
// insert參數符合，執行新增一般會員
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
		    // 1.接收請求參數，輸入格式錯誤處理
			String m_email = req.getParameter("m_email");
			String m_emailReg = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
			
			MemberService memSvc =new MemberService();
			MemberVO memberVO2=memSvc.getMemberPw(m_email);
			String signedmail = "";
			if(memberVO2 != null) {
				signedmail = memberVO2.getM_email();
			}
			
			if (m_email == null || m_email.trim().length() == 0) {
				errorMsgs.add("郵箱請勿空白");
			} 
			if (!m_email.trim().matches(m_emailReg)) {
				errorMsgs.add("郵箱不符合格式!請重新輸入");
			} 
			if(signedmail != null && m_email.equals(signedmail)) {
				errorMsgs.add("郵箱已存在，請重新註冊");
			}			
			String m_password = req.getParameter("m_password").trim();
			if (m_password == null || m_password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			String m_name = req.getParameter("m_name");
			String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (m_name == null || m_name.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			} else if (!m_name.trim().matches(m_nameReg)) {
				errorMsgs.add("姓名只能為中英文、數字和_ , 且長度必需在2到10之間");
			}

			String m_gender = req.getParameter("m_gender");
			if (m_gender == null || m_gender.trim().length() == 0) {
				m_gender="";
				errorMsgs.add("請選擇性別欄");
			}

			String m_phone = req.getParameter("m_phone");
			if (m_phone == null || m_phone.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			} if(m_phone.length() <=8 || m_phone.length()>10 ) {
				errorMsgs.add("電話號碼位數不符");
			}

			String m_address = req.getParameter("m_address");
			if (m_address == null || m_address.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}
			
			java.sql.Date m_birth = null;
			try {
				m_birth = java.sql.Date.valueOf(req.getParameter("m_birth").trim());
			} catch (IllegalArgumentException e) {
				m_birth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請選擇生日");
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

			// 2.開始新增資料
			System.out.println("開始新增");
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMem(m_email, m_password, m_name,m_gender, m_phone, m_address, m_birth);//得到memberVO物件以做後續處理
            System.out.println("新增完成");
            
			// 3.新增完成，準備轉交
			String url= "/Front_end/members/listAllMem.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			// 抓到其他例外
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/addMem.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
//insert2參數行為符合，新增賣家會員
		if ("insert2".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 1.接收請求參數，輸入格式錯誤處理
			try {
			String m_email = req.getParameter("m_email");
	        String m_emailReg = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
		    if (m_email == null || m_email.trim().length() == 0) {
					errorMsgs.add("郵箱請勿空白");
			} 
			else if (!m_email.trim().matches(m_emailReg)) {
					errorMsgs.add("郵箱不符合格式!請重新輸入");
			}
			String m_password = req.getParameter("m_password").trim();
			if (m_password == null || m_password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			String m_name = req.getParameter("m_name");
			String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (m_name == null || m_name.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			} else if (!m_name.trim().matches(m_nameReg)) {
				errorMsgs.add("姓名只能為中英文、數字和_ , 且長度必需在2到10之間");
			}
			String m_gender = req.getParameter("m_gender");
			if (m_gender == null || m_gender.trim().length() == 0) {
				m_gender="";
				errorMsgs.add("請選擇性別欄");
			}
			String m_phone = req.getParameter("m_phone");
			if (m_phone == null || m_phone.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			} if(m_phone.length() <=8 || m_phone.length()>10 ) {
				errorMsgs.add("電話號碼位數不符");
			}

			String m_address = req.getParameter("m_address");
			if (m_address == null || m_address.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}
			
			java.sql.Date m_birth = null;
			try {
				m_birth = java.sql.Date.valueOf(req.getParameter("m_birth").trim());
			} catch (IllegalArgumentException e) {
				m_birth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請選擇生日");
			}
			
			Part m_headpic = req.getPart("m_headpic");
			InputStream is =m_headpic.getInputStream();
			
			byte[] m_headpicbuffer=null;
			try {
				m_headpicbuffer = new byte[is.available()];
				is.read(m_headpicbuffer);
				is.close();
			} catch (Exception e) {
				errorMsgs.add("大頭貼上傳失敗，請再試一次");
				e.printStackTrace();
			}
			
			String m_identity = req.getParameter("m_identity");
			if (m_identity == null || m_identity.trim().length() == 0) {
				errorMsgs.add("身分證字號請勿空白");
			}
			
			Part m_id_pic=req.getPart("m_id_pic");
			InputStream is2 =m_id_pic.getInputStream();
			byte[] m_id_picbuffer=null;
			try {
				m_id_picbuffer = new byte[is2.available()];
				is2.read(m_id_picbuffer);
				is2.close();
			} catch (Exception e) {
				errorMsgs.add("身分證照片上傳失敗，請再試一次");
				e.printStackTrace();
			}
			if(m_id_picbuffer==null || m_id_picbuffer.length==0) {
				errorMsgs.add("請上傳身分證照片");
			}
			
			String m_account = req.getParameter("m_account");
			if (m_account == null || m_account.trim().length() == 0) {
				errorMsgs.add("銀行帳戶請勿空白");
			}
			
			String m_accountName =req.getParameter("m_accountName");
			if (m_accountName == null || m_accountName.trim().length() == 0) {
				errorMsgs.add("戶名請勿空白");
			}
			
			String b_code =req.getParameter("b_code");
			if(b_code==null || b_code.trim().length()==0) {
				errorMsgs.add("銀行代號請勿空白");
			}if(b_code.length()!=3) {
				errorMsgs.add("銀行代號位數錯誤，請重新輸入");
			}
			
			Part m_bank_pic =req.getPart("m_bank_pic");
			InputStream is3 = m_bank_pic.getInputStream();
			byte[] m_bank_picbuffer=null;
			try {
				m_bank_picbuffer =new byte[is3.available()];
				is3.read(m_bank_picbuffer);
				is3.close();
			} catch (Exception e) {
				errorMsgs.add("存摺照片上傳失敗，請再試一次");
				e.printStackTrace();
			}
			
			if(m_bank_picbuffer==null || m_bank_picbuffer.length==0) {
				errorMsgs.add("請上傳銀行帳戶照片");
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
            	errorMsgs.add("賣場封面上傳失敗，請再試一次");
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

			// 2.開始新增資料
			System.out.println("開始新增");
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMem2( m_email,  m_password,  m_name,  m_gender,  m_phone,
					 m_address, m_birth, m_headpicbuffer,  m_identity, m_id_picbuffer,  m_account,  m_accountName,  b_code, m_bank_picbuffer,
					 m_storename,  m_info,  m_coverbuffer,  m_hi, m_offlineHi);//得到memberVO物件以做後續處理
            System.out.println("新增完成");
            
			// 3.新增完成，準備轉交
			String url= "/Front_end/members/listAllMem.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			// 抓到其他例外
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/addMemSeller.jsp");
				failureView.forward(req, res);
			}
			
		}
//delete參數行為符合，刪除會員
		if("delete".equals(action)) {
			List<String> errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 1. 接收請求參數
				String m_id =new String (req.getParameter("m_id"));
				
				// 2. 開始刪除
				MemberService memSvc =new MemberService();
				memSvc.deleteMem(m_id);
			
			    // 3. 刪除完成，準備轉交
		        String url ="/Front_end/members/listAllMem.jsp";	
		        RequestDispatcher successView =req.getRequestDispatcher(url);
		        successView.forward(req, res);			
			    
		        // 其他錯誤處理
			}catch(Exception e) {
				errorMsgs.add("資料刪除失敗"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Front_end/members/listAllMem.jsp");
			    failureView.forward(req, res);
			}
		}

	}

}
