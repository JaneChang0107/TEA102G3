package com.rentOrder.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class RentOrderJDBCDAO implements RentOrderDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";

	private static final String INSERT_STMT = 
		"INSERT INTO rentOrder (RO_ID, RO_DATE, RO_STATUS, ST_ID, RO_OUTDATE, RO_BACKDATE, RO_DEPOSIT, RO_TOTAL, ro_sign, RO_PM, M_ID) VALUES ('RO' || LPAD(RENTORDER_SEQ.NEXTVAL,5,0), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ro_id,ro_date,ro_status,st_id,ro_outdate,ro_backdate,ro_deposit,ro_total,ro_sign,ro_pm,m_id FROM rentOrder order by ro_id";
	private static final String GET_ONE_STMT = 
		"SELECT ro_id,ro_date,ro_status,st_id,ro_outdate,ro_backdate,ro_deposit,ro_total,ro_sign,ro_pm,m_id FROM rentOrder where ro_id = ?";
	private static final String DELETE = 
		"DELETE FROM rentOrder where ro_id = ?";
	private static final String UPDATE = 
		"UPDATE rentOrder set ro_date=?, ro_status=?, st_id=?, ro_outdate=?, ro_backdate=?, ro_deposit=?, ro_total=?, ro_sign=?, ro_pm=?, m_id=? where ro_id = ?";

	@Override
	public void insert(RentOrderVO rentOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setTimestamp(1, rentOrderVO.getRo_date());
			pstmt.setString(2, rentOrderVO.getRo_status());
			pstmt.setString(3, rentOrderVO.getSt_id());
			pstmt.setTimestamp(4, rentOrderVO.getRo_outdate());
			pstmt.setTimestamp(5, rentOrderVO.getRo_backdate());
			pstmt.setInt(6, rentOrderVO.getRo_deposit());
			pstmt.setInt(7, rentOrderVO.getRo_total());
			pstmt.setBytes(8, rentOrderVO.getRo_sign());
			pstmt.setInt(9, rentOrderVO.getRo_pm());
			pstmt.setString(10, rentOrderVO.getM_id());

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
	public void update(RentOrderVO rentOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, rentOrderVO.getRo_date());
			pstmt.setString(2, rentOrderVO.getRo_status());
			pstmt.setString(3, rentOrderVO.getSt_id());
			pstmt.setTimestamp(4, rentOrderVO.getRo_outdate());
			pstmt.setTimestamp(5, rentOrderVO.getRo_backdate());
			pstmt.setInt(6, rentOrderVO.getRo_deposit());
			pstmt.setInt(7, rentOrderVO.getRo_total());
			pstmt.setBytes(8, rentOrderVO.getRo_sign());
			pstmt.setInt(9, rentOrderVO.getRo_pm());
			pstmt.setString(10, rentOrderVO.getM_id());
			pstmt.setString(11, rentOrderVO.getRo_id());

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
	public RentOrderVO findByPrimaryKey(String ro_id) {

		RentOrderVO rentOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ro_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rentOrderVO = new RentOrderVO();
				rentOrderVO.setRo_id(rs.getString("Ro_id"));
				rentOrderVO.setRo_date(rs.getTimestamp("Ro_date"));
				rentOrderVO.setRo_status(rs.getString("Ro_status"));
				rentOrderVO.setSt_id(rs.getString("St_id"));
				rentOrderVO.setRo_outdate(rs.getTimestamp("Ro_outdate"));
				rentOrderVO.setRo_backdate(rs.getTimestamp("Ro_backdate"));
				rentOrderVO.setRo_deposit(rs.getInt("Ro_deposit"));
				rentOrderVO.setRo_total(rs.getInt("Ro_total"));
				rentOrderVO.setRo_sign(rs.getBytes("Ro_sign"));
				rentOrderVO.setRo_pm(rs.getInt("Ro_pm"));
				rentOrderVO.setM_id(rs.getString("M_id"));
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
		return rentOrderVO;
	}

	@Override
	public List<RentOrderVO> getAll() {
		List<RentOrderVO> list = new ArrayList<RentOrderVO>();
		RentOrderVO rentOrderVO = null;

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
				rentOrderVO = new RentOrderVO();
				rentOrderVO.setRo_id(rs.getString("Ro_id"));
				rentOrderVO.setRo_date(rs.getTimestamp("Ro_date"));
				rentOrderVO.setRo_status(rs.getString("Ro_status"));
				rentOrderVO.setSt_id(rs.getString("St_id"));
				rentOrderVO.setRo_outdate(rs.getTimestamp("Ro_outdate"));
				rentOrderVO.setRo_backdate(rs.getTimestamp("Ro_backdate"));
				rentOrderVO.setRo_deposit(rs.getInt("Ro_deposit"));
				rentOrderVO.setRo_total(rs.getInt("Ro_total"));
				rentOrderVO.setRo_sign(rs.getBytes("Ro_sign"));
				rentOrderVO.setRo_pm(rs.getInt("Ro_pm"));
				rentOrderVO.setM_id(rs.getString("M_id")); // Store the row in the list
				list.add(rentOrderVO);
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

		RentOrderJDBCDAO dao = new RentOrderJDBCDAO();
		byte[] pic = getPictureByteArray("WebContent/back_end/employee/images/1.png");
		byte[] pic2 = getPictureByteArray("WebContent/back_end/employee/images/2.jpg");

//		// 新增
//		RentOrderVO RentOrderVO1 = new RentOrderVO(); 
//		RentOrderVO1.setRo_date(java.sql.Timestamp.valueOf("2020-12-02 12:12:12"));
//		RentOrderVO1.setRo_status("1");
//		RentOrderVO1.setRo_status("A123456789");
//		RentOrderVO1.setSt_id("ST00001");
//		RentOrderVO1.setRo_outdate(java.sql.Timestamp.valueOf("2020-12-02 12:12:12"));
//		RentOrderVO1.setRo_backdate(java.sql.Timestamp.valueOf("2020-12-02 12:12:12"));
//		RentOrderVO1.setRo_deposit(1);
//		RentOrderVO1.setRo_total(1);
//		RentOrderVO1.setRo_sign(pic);
//		RentOrderVO1.setRo_pm(1);
//		RentOrderVO1.setM_id("M00001");
//		dao.insert(RentOrderVO1);
//
//		//修改
//		RentOrderVO RentOrderVO2 = new RentOrderVO(); 
//		RentOrderVO2.setRo_id("RO00001");
//		RentOrderVO2.setRo_date(java.sql.Timestamp.valueOf("2020-12-02 12:12:12"));
//		RentOrderVO2.setRo_status("A123456789");
//		RentOrderVO2.setSt_id("ST00001");
//		RentOrderVO2.setRo_outdate(java.sql.Timestamp.valueOf("2020-12-02 12:12:12"));
//		RentOrderVO2.setRo_backdate(java.sql.Timestamp.valueOf("2020-12-02 12:12:12"));
//		RentOrderVO2.setRo_deposit(1);
//		RentOrderVO2.setRo_total(1);
//		RentOrderVO2.setRo_sign(pic2);
//		RentOrderVO2.setRo_pm(1);
//		RentOrderVO2.setM_id("M00002");
//		dao.update(RentOrderVO2);
//
//		// 刪除
//		dao.delete("RO00002");
//
//		// 查詢
//		RentOrderVO RentOrderVO3 = dao.findByPrimaryKey("RO00001");
//		System.out.print(RentOrderVO3.getRo_id() + ",");
//		System.out.print(RentOrderVO3.getRo_date() + ",");
//		System.out.print(RentOrderVO3.getRo_status() + ",");
//		System.out.print(RentOrderVO3.getSt_id() + ",");
//		System.out.print(RentOrderVO3.getRo_outdate() + ",");
//		System.out.print(RentOrderVO3.getRo_backdate() + ",");
//		System.out.print(RentOrderVO3.getRo_deposit() + ",");
//		System.out.print(RentOrderVO3.getRo_total() + ",");
//		System.out.print(RentOrderVO3.getRo_sign() + ",");
//		System.out.print(RentOrderVO3.getRo_pm() + ",");
//		System.out.print(RentOrderVO3.getM_id() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<RentOrderVO> list = dao.getAll();
//		for (RentOrderVO aRO : list) {
//			System.out.print(aRO.getRo_id() + ",");
//			System.out.print(aRO.getRo_date() + ",");
//			System.out.print(aRO.getRo_status() + ",");
//			System.out.print(aRO.getSt_id() + ",");
//			System.out.print(aRO.getRo_outdate() + ",");
//			System.out.print(aRO.getRo_backdate() + ",");
//			System.out.print(aRO.getRo_deposit() + ",");
//			System.out.print(aRO.getRo_total() + ",");
//			System.out.print(aRO.getRo_sign() + ",");
//			System.out.print(aRO.getRo_pm() + ",");
//			System.out.print(aRO.getM_id() + ",");
//			System.out.println();
//		}
	}
	
	public static byte[] getPictureByteArray(String path){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] buffer = null;
		try {
			buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
}
