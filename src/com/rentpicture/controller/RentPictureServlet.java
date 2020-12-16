package com.rentpicture.controller;

import java.io.ByteArrayOutputStream;
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
import javax.servlet.http.Part;
import com.rentpicture.model.RentPictureService;
import com.rentpicture.model.RentPictureVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/RentPictureServlet")

public class RentPictureServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("rp_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�X���~�Ӥ��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/RentPicture/index_rentpicture.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String rp_id = null;
				try {
					rp_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�X���~�Ӥ��s�������T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/RentPicture/index_rentpicture.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				RentPictureService RPSvc = new RentPictureService();
				RentPictureVO rentpictureVO = RPSvc.getOneRentPicture(rp_id);
				if (rentpictureVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/RentPicture/index_rentpicture.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("rentpictureVO", rentpictureVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/RentPicture/listOneRentPicture.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/RentPicture/index_rentpicture.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String rp_id = req.getParameter("rp_id");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				RentPictureService rentpictureSvc = new RentPictureService();
				RentPictureVO rentpictureVO = rentpictureSvc.getOneRentPicture(rp_id);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("rentpictureVO", rentpictureVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/RentPicture/update_rentpicture_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/RentPicture/update_rentpicture_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String rp_id = req.getParameter("rp_id");

				// ----------------------------------------------------------------------------------//
				// obtains the upload file part in this multipart request
				Part rp_picture = req.getPart("rp_picture");
				if (rp_picture == null || rp_picture.getSize() == 0) {
					errorMsgs.add("�ФW�ǹϤ�");
				}

				// obtains input stream of the upload file
				InputStream inputStream = rp_picture.getInputStream();
				byte[] data = new byte[inputStream.available()];
				inputStream.read(data);

//				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//				int nRead;
//				
//				while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
//					buffer.write(data, 0, nRead);
//				}
//
//				buffer.flush();
//				byte[] byteArray = buffer.toByteArray();
//--------------------------------------------------------------------------------//

				String r_id = req.getParameter("r_id");

				RentPictureVO rentpictureVO = new RentPictureVO();
				rentpictureVO.setRp_id(rp_id);
				rentpictureVO.setRp_picture(data);
				rentpictureVO.setR_id(r_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentpictureVO", rentpictureVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/RentPicture/update_rentpicture_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				RentPictureService rentpictureSvc = new RentPictureService();
				rentpictureVO = rentpictureSvc.updateRentPicture(data, r_id, rp_id);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("rentpictureVO", rentpictureVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/Back_end/RentPicture/listOneRentPicture.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/RentPicture/update_rentpicture_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String rp_id = req.getParameter("rp_id");

//----------------------------------------------------------------------------------//
				// obtains the upload file part in this multipart request
				Part rp_picture = req.getPart("rp_picture");

				// obtains input stream of the upload file
				InputStream inputStream = rp_picture.getInputStream();
				byte[] data = new byte[inputStream.available()];
				inputStream.read(data);

//				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//				int nRead;
//				
//				while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
//					buffer.write(data, 0, nRead);
//				}
//
//				buffer.flush();
//				byte[] byteArray = buffer.toByteArray();
//--------------------------------------------------------------------------------//
				String r_id = req.getParameter("r_id").trim();
				if (r_id == null || r_id.trim().length() == 0) {
					errorMsgs.add("�X���~�s���ФŪť�");
				}

				RentPictureVO rentpictureVO = new RentPictureVO();
				rentpictureVO.setR_id(rp_id);
				rentpictureVO.setRp_picture(data);
				rentpictureVO.setR_id(r_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentpictureVO", rentpictureVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/RentPicture/addRentPicture.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				RentPictureService rentpictureSvc = new RentPictureService();
				rentpictureVO = rentpictureSvc.addRentPicture(rp_id, data, r_id);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/Back_end/RentPicture/listAllRentPicture.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);
				inputStream.close();

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/RentPicture/addRentPicture.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String rp_id = req.getParameter("rp_id");

				/*************************** 2.�}�l�R����� ***************************************/
				RentPictureService rentpictureSvc = new RentPictureService();
				rentpictureSvc.deleteRentPicture(rp_id);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/Back_end/RentPicture/listAllRentPicture.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/RentPicture/listAllRentPicture.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
