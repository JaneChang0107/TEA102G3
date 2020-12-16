package com.rent.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rentpicture.model.RentPictureVO;

public class RentJDBCDAO implements RentDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";

	private static final String INSERT_STMT = "INSERT INTO rent (r_id,r_type,r_name,pt_id,r_describe,r_situation,r_status,r_price,r_adddate,e_addid,st_id) VALUES ('R' || lpad(RENT_SEQ.NEXTVAL, 5, '0'), ?, ?, ?, ?, ? ,? ,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM rent order by r_id";
	private static final String GET_ONE_STMT = "SELECT * FROM rent where r_id = ?";
	private static final String DELETE = "DELETE FROM rent where r_id = ?";
	private static final String UPDATE = "UPDATE rent set r_type=?, r_name=?,pt_id=?, r_describe=?, r_situation=? ,r_status=? ,r_price=?,r_adddate=?, r_revisedate=?,e_addid=?, e_editorid=? ,st_id=? where r_id = ?";

	@Override
	public void insert(RentVO rentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			// (r_id,r_type,r_name,pt_id,r_describe,r_situation,r_status,r_price,r_adddate,r_revisedate,e_addid,e_editorid,st_id)
			// VALUES
			// (RENT_SEQ, ?, ?, ?, ?, ? ,? ,? ,?,?,?,?,?)";
			pstmt.setString(1, rentVO.getR_type());
			pstmt.setString(2, rentVO.getR_name());
			pstmt.setString(3, rentVO.getPt_id());
			pstmt.setString(4, rentVO.getR_describe());
			pstmt.setString(5, rentVO.getR_situation());
			pstmt.setString(6, rentVO.getR_status());
			pstmt.setInt(7, rentVO.getR_price());
			pstmt.setTimestamp(8, rentVO.getR_adddate());
			pstmt.setString(9, rentVO.getE_addid());
			pstmt.setString(10, rentVO.getSt_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(RentVO rentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			// r_type=?, r_name=?, r_describe=?, r_situation=? ,r_status ,r_price,
			// r_revisedate=?, e_editorid=? where r_id = ?

			pstmt.setString(1, rentVO.getR_type());
			pstmt.setString(2, rentVO.getR_name());
			pstmt.setString(3, rentVO.getPt_id());
			pstmt.setString(4, rentVO.getR_describe());
			pstmt.setString(5, rentVO.getR_situation());
			pstmt.setString(6, rentVO.getR_status());
			pstmt.setInt(7, rentVO.getR_price());
			pstmt.setTimestamp(8, rentVO.getR_adddate());
			pstmt.setTimestamp(9, rentVO.getR_revisedate());
			pstmt.setString(10, rentVO.getE_addid());
			pstmt.setString(11, rentVO.getE_editorid());
			pstmt.setString(12, rentVO.getSt_id());
			pstmt.setString(13, rentVO.getR_id());
		

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String r_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, r_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public RentVO findByPrimaryKey(String r_id) {

		RentVO rentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, r_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rentVO = new RentVO();
				rentVO.setR_id(rs.getString("R_id"));
				rentVO.setR_type(rs.getString("R_type"));
				rentVO.setR_name(rs.getString("R_name"));
				rentVO.setPt_id(rs.getString("Pt_id"));
				rentVO.setR_describe(rs.getString("R_describe"));
				rentVO.setR_situation(rs.getString("R_situation"));
				rentVO.setR_status(rs.getString("R_status"));
				rentVO.setR_price(rs.getInt("R_price"));
				rentVO.setR_adddate(rs.getTimestamp("R_adddate"));
				rentVO.setR_revisedate(rs.getTimestamp("R_revisedate"));
				rentVO.setE_addid(rs.getString("E_addid"));
				rentVO.setE_editorid(rs.getString("E_editorid"));
				rentVO.setSt_id(rs.getString("St_id"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rentVO;

	}

	@Override
	public List<RentVO> getAll() {
		List<RentVO> list = new ArrayList<RentVO>();
		RentVO rentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rentVO = new RentVO();
				rentVO.setR_id(rs.getString("R_id"));
				rentVO.setR_type(rs.getString("R_type"));
				rentVO.setR_name(rs.getString("R_name"));
				rentVO.setPt_id(rs.getString("Pt_id"));
				rentVO.setR_describe(rs.getString("R_describe"));
				rentVO.setR_situation(rs.getString("R_situation"));
				rentVO.setR_status(rs.getString("R_status"));
				rentVO.setR_price(rs.getInt("R_price"));
				rentVO.setR_adddate(rs.getTimestamp("R_adddate"));
				rentVO.setR_revisedate(rs.getTimestamp("R_revisedate"));
				rentVO.setE_addid(rs.getString("E_addid"));
				rentVO.setE_editorid(rs.getString("E_editorid"));
				rentVO.setSt_id(rs.getString("St_id"));

				list.add(rentVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		RentJDBCDAO dao = new RentJDBCDAO();
		RentVO rentVO = new RentVO();
//insert
//		rentVO.setR_type("遊戲主機");
//		rentVO.setR_name("PS4 black");
//		rentVO.setPt_id("PT00001");
//		rentVO.setR_describe("保存良好");
//		rentVO.setR_situation("新品");
//		rentVO.setR_status("未上架");
//		rentVO.setR_price(100);
//		Timestamp add = new Timestamp(System.currentTimeMillis());
//		rentVO.setR_adddate(add);
//		Timestamp revise = new Timestamp(System.currentTimeMillis());
//		rentVO.setR_revisedate(revise);		
//		rentVO.setE_addid("E00001");
//		rentVO.setE_editorid("E00001");
//		rentVO.setSt_id("ST00001");
//		
//		dao.insert(rentVO);
//		System.out.println("新增成功");

//get all
//		dao.getAll();
//		List<RentVO> list = dao.getAll();
//		for (RentVO rpv : list) {
//			System.out.print(rpv.getR_id() + ",");
//			System.out.print(rpv.getR_type() + ",");
//			System.out.print(rpv.getR_name());
//			System.out.print(rpv.getPt_id() + ",");
//			System.out.print(rpv.getR_describe());
//			System.out.print(rpv.getR_situation() + ",");
//			System.out.print(rpv.getR_status());
//			System.out.print(rpv.getR_price() + ",");
//			System.out.print(rpv.getR_adddate());
//			System.out.print(rpv.getR_revisedate() + ",");
//			System.out.print(rpv.getE_addid());
//			System.out.print(rpv.getE_editorid() + ",");
//			System.out.print(rpv.getSt_id());
//
//			System.out.println();

//find one
//		dao.findByPrimaryKey("R00003");
//		RentVO rv = dao.findByPrimaryKey("R00003");
//			System.out.print(rv.getR_id() + ",");
//			System.out.print(rv.getR_type() + ",");
//			System.out.print(rv.getR_name());
//			System.out.print(rv.getPt_id() + ",");
//			System.out.print(rv.getR_describe());
//			System.out.print(rv.getR_situation() + ",");
//			System.out.print(rv.getR_status());
//			System.out.print(rv.getR_price() + ",");
//			System.out.print(rv.getR_adddate());
//			System.out.print(rv.getR_revisedate() + ",");
//			System.out.print(rv.getE_addid());
//			System.out.print(rv.getE_editorid() + ",");
//			System.out.print(rv.getSt_id());
//		
//			System.out.println();

//update

		rentVO.setR_type("遊戲主機");
		rentVO.setR_name("PS4");
		rentVO.setPt_id("PT00001");
		rentVO.setR_describe("全新品未使用");
		rentVO.setR_situation("新品");
		rentVO.setR_status("出租中");
		rentVO.setR_price(4000);
		Timestamp add=dao.findByPrimaryKey("R00009").getR_adddate();
		rentVO.setR_adddate(add);
		Timestamp revise = new Timestamp(System.currentTimeMillis());
		rentVO.setR_revisedate(revise);
		String addid=dao.findByPrimaryKey("R00009").getE_addid();
		rentVO.setE_addid(addid);
		rentVO.setE_editorid("E00001");
		rentVO.setSt_id("ST00002");
		rentVO.setR_id("R00009");
		dao.update(rentVO);
//		
//		//delete
//		dao.delete("R00001");
//		System.out.println("delete success");

	}

}
