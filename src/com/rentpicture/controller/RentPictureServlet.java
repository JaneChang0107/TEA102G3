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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("rp_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入出租品照片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/RentPicture/index_rentpicture.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String rp_id = null;
				try {
					rp_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("出租品照片編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/RentPicture/index_rentpicture.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RentPictureService RPSvc = new RentPictureService();
				RentPictureVO rentpictureVO = RPSvc.getOneRentPicture(rp_id);
				if (rentpictureVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/RentPicture/index_rentpicture.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rentpictureVO", rentpictureVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/RentPicture/listOneRentPicture.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/RentPicture/index_rentpicture.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String rp_id = req.getParameter("rp_id");

				/*************************** 2.開始查詢資料 ****************************************/
				RentPictureService rentpictureSvc = new RentPictureService();
				RentPictureVO rentpictureVO = rentpictureSvc.getOneRentPicture(rp_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rentpictureVO", rentpictureVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/RentPicture/update_rentpicture_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/RentPicture/update_rentpicture_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String rp_id = req.getParameter("rp_id");

				// ----------------------------------------------------------------------------------//
				// obtains the upload file part in this multipart request
				Part rp_picture = req.getPart("rp_picture");
				if (rp_picture == null || rp_picture.getSize() == 0) {
					errorMsgs.add("請上傳圖片");
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
					req.setAttribute("rentpictureVO", rentpictureVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/RentPicture/update_rentpicture_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				RentPictureService rentpictureSvc = new RentPictureService();
				rentpictureVO = rentpictureSvc.updateRentPicture(data, r_id, rp_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rentpictureVO", rentpictureVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/RentPicture/listOneRentPicture.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/RentPicture/update_rentpicture_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
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
					errorMsgs.add("出租品編號請勿空白");
				}

				RentPictureVO rentpictureVO = new RentPictureVO();
				rentpictureVO.setR_id(rp_id);
				rentpictureVO.setRp_picture(data);
				rentpictureVO.setR_id(r_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentpictureVO", rentpictureVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/RentPicture/addRentPicture.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RentPictureService rentpictureSvc = new RentPictureService();
				rentpictureVO = rentpictureSvc.addRentPicture(rp_id, data, r_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/Back_end/RentPicture/listAllRentPicture.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				inputStream.close();

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/RentPicture/addRentPicture.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String rp_id = req.getParameter("rp_id");

				/*************************** 2.開始刪除資料 ***************************************/
				RentPictureService rentpictureSvc = new RentPictureService();
				rentpictureSvc.deleteRentPicture(rp_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/Back_end/RentPicture/listAllRentPicture.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/RentPicture/listAllRentPicture.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
