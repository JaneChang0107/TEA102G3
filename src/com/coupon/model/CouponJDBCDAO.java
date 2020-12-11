package com.coupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponJDBCDAO implements CouponDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";

	private static final String INSERT_STMT = "INSERT INTO coupon (co_id,co_code,co_amount,co_start,co_expire,co_status) VALUES ('CO'|| lpad(COUPON_SEQ.NEXTVAL,5,'0'),?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT co_id,co_code,co_amount,co_start,co_expire,co_status FROM coupon order by co_id";
	private static final String GET_ONE_STMT = "SELECT co_id,co_code,co_amount,co_start,co_expire,co_status FROM coupon where co_id = ?";
//	private static final String DELETE = "DELETE FROM coupon where co_id = ?";
	private static final String UPDATE = "UPDATE coupon set co_id,co_code,co_amount,co_start,co_expire,co_status where co_id = ?";

	@Override
	public void insert(CouponVO couponVO1) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, couponVO1.getCo_code());
			pstmt.setInt(2, couponVO1.getCo_amount());
			pstmt.setTimestamp(3, couponVO1.getCo_start());
			pstmt.setTimestamp(4, couponVO1.getCo_expire());
			pstmt.setString(5, couponVO1.getCo_status());

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
	public void update(CouponVO couponVO3) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponVO3.getCo_code());
			pstmt.setInt(2, couponVO3.getCo_amount());
			pstmt.setTimestamp(3, couponVO3.getCo_start());
			pstmt.setTimestamp(4, couponVO3.getCo_expire());
			pstmt.setString(5, couponVO3.getCo_status());
			
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
	public void delete(String co_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, co_id);

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
	public CouponVO findByPrimaryKey(String co_id) {

		CouponVO couponVO2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, co_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				couponVO2 = new CouponVO();
				couponVO2.setCo_id(rs.getString("co_id"));
				couponVO2.setCo_code(rs.getString("co_code"));
				couponVO2.setCo_amount(rs.getInt("co_amount"));
				couponVO2.setCo_start(rs.getTimestamp("co_start"));
				couponVO2.setCo_expire(rs.getTimestamp("co_expire"));
				couponVO2.setCo_status(rs.getString("co_status"));
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
		return couponVO2;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO2 = null;

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
				couponVO2 = new CouponVO();
				couponVO2.setCo_id(rs.getString("co_id"));
				couponVO2.setCo_code(rs.getString("co_code"));
				couponVO2.setCo_amount(rs.getInt("co_amount"));
				couponVO2.setCo_start(rs.getTimestamp("co_start"));
				couponVO2.setCo_expire(rs.getTimestamp("co_expire"));
				couponVO2.setCo_status(rs.getString("co_status"));
				list.add(couponVO2); // Store the row in the list
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
		CouponJDBCDAO dao = new CouponJDBCDAO();

		// 新增
//		CouponVO couponVO1 = new CouponVO();
//		couponVO1.setCo_code("");
//		couponVO1.setCo_amount("");
//		couponVO1.setCo_start("");
//		couponVO1.setCo_expire("");
//		couponVO1.setCo_status("");
//
//		dao.insert(couponVO1);

		// 查詢
		CouponVO couponVO2 = dao.findByPrimaryKey("CO00001");
		System.out.print(couponVO2.getCo_id() + ",");
		System.out.print(couponVO2.getCo_code() + ",");
		System.out.print(couponVO2.getCo_amount() + ",");
		System.out.print(couponVO2.getCo_start() + ",");
		System.out.print(couponVO2.getCo_expire() + ",");
		System.out.println(couponVO2.getCo_status());
		System.out.println("---------------------");

//		// 查詢
//		List<CouponVO> list = dao.getAll();
//		for (CouponVO aCo : list) {
//			System.out.print(aCo.getCo_id() + ",");
//			System.out.print(aCo.getCo_code() + ",");
//			System.out.print(aCo.getCo_amount() + ",");
//			System.out.print(aCo.getCo_start() + ",");
//			System.out.print(aCo.getCo_expire()+ ",");
//			System.out.print(aCo.getCo_status());
//			System.out.println();
//		}
		// 刪除
//		dao.delete("CO00001");

		// 修改
//		CouponVO couponVO3 = new CouponVO();
//		couponVO3.setCo_id("CO00001");
//		couponVO3.setCo_code("");
//		couponVO3.setCo_amount("");
//		couponVO3.setCo_start("");
//		couponVO3.setCo_expire("");
//		couponVO3.setCo_status("");
//		dao.update(couponVO3);
	}

}
