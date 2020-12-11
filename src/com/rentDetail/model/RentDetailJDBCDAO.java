package com.rentDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employee.model.EmployeeJDBCDAO;
import com.employee.model.EmployeeVO;

public class RentDetailJDBCDAO implements RentDetailDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";

	private static final String INSERT_STMT = 
		"INSERT INTO rentDetail (rd_id, ro_id, r_id, rd_count) VALUES ('RD' || LPAD(RENTDETAIL_SEQ.NEXTVAL,5,0), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT rd_id,ro_id,r_id, rd_count FROM rentDetail order by rd_id";
	private static final String GET_ONE_STMT = 
		"SELECT rd_id,ro_id,r_id, rd_count FROM rentDetail where rd_id = ?";
	private static final String DELETE = 
		"DELETE FROM rentDetail where rd_id = ?";
	private static final String UPDATE = 
		"UPDATE rentDetail set ro_id=?, r_id=?, rd_count=? where rd_id=?";

	@Override
	public void insert(RentDetailVO rentDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rentDetailVO.getRo_id());
			pstmt.setString(2, rentDetailVO.getR_id());
			pstmt.setInt(3, rentDetailVO.getRd_count());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(RentDetailVO rentDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rentDetailVO.getRo_id());
			pstmt.setString(2, rentDetailVO.getR_id());
			pstmt.setInt(3, rentDetailVO.getRd_count());
			pstmt.setString(4, rentDetailVO.getRd_id());
			System.out.println("前");
			pstmt.executeUpdate();
			System.out.println("後");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String e_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, e_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public RentDetailVO findByPrimaryKey(String rd_id) {

		RentDetailVO rentDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rd_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rentDetailVO = new RentDetailVO();
				rentDetailVO.setRd_id(rs.getString("Rd_id"));
				rentDetailVO.setRo_id(rs.getString("Ro_id"));
				rentDetailVO.setR_id(rs.getString("R_id"));
				rentDetailVO.setRd_count(rs.getInt("Rd_count"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return rentDetailVO;
	}

	@Override
	public List<RentDetailVO> getAll() {
		List<RentDetailVO> list = new ArrayList<RentDetailVO>();
		RentDetailVO rentDetailVO = null;

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
				rentDetailVO = new RentDetailVO();
				rentDetailVO.setRd_id(rs.getString("Rd_id"));
				rentDetailVO.setRo_id(rs.getString("Ro_id"));
				rentDetailVO.setR_id(rs.getString("R_id"));
				rentDetailVO.setRd_count(rs.getInt("Rd_count"));
				list.add(rentDetailVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

		RentDetailJDBCDAO dao = new RentDetailJDBCDAO();

//		// 新增
//		RentDetailVO rentDetailVO1 = new RentDetailVO(); 
//		rentDetailVO1.setRo_id("RO00001");
//		rentDetailVO1.setR_id("R00001");
//		rentDetailVO1.setRd_count(1);
//		dao.insert(rentDetailVO1);
//
//		// 修改
//		RentDetailVO rentDetailVO2 = new RentDetailVO(); 		
//		rentDetailVO2.setRd_id("RD00001");
//		rentDetailVO2.setRo_id("RO00001");
//		rentDetailVO2.setR_id("R00001");
//		rentDetailVO2.setRd_count(6);
//		
//		dao.update(rentDetailVO2);
//		System.out.println("修改成功");
//		
//
//		// 刪除
//		dao.delete("RD00003");
//
		// 查詢
		RentDetailVO rentDetailVO3 = dao.findByPrimaryKey("RD00001");
		System.out.print(rentDetailVO3.getRd_id() + ",");
		System.out.print(rentDetailVO3.getRo_id() + ",");
		System.out.print(rentDetailVO3.getR_id() + ",");
		System.out.print(rentDetailVO3.getRd_count() + ",");
		System.out.println("---------------------");
//
//		// 查詢
//		List<RentDetailVO> list = dao.getAll();
//		for (RentDetailVO aRD : list) {
//			System.out.print(aRD.getRd_id() + ",");
//			System.out.print(aRD.getRo_id() + ",");
//			System.out.print(aRD.getR_id() + ",");
//			System.out.print(aRD.getRd_count() + ",");
//			System.out.println();
//		}
	}
}
