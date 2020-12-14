package com.bankcode.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankcodeJDBCDAO implements BankcodeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";

	private static final String INSERT_STMT = "INSERT INTO bankcode (b_code,b_name) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT b_code,b_name FROM bankcode order by b_code";
	private static final String GET_ONE_STMT = "SELECT b_code,b_name FROM bankcode where b_code = ?";
//	private static final String DELETE = "DELETE FROM bankcode where b_code = ?";
	private static final String UPDATE = "UPDATE bankcode set b_code=?, b_name=? where b_code = ?";

	@Override
	public void insert(BankcodeVO bankcodeVO1) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,bankcodeVO1.getB_code());
			pstmt.setString(2,bankcodeVO1.getB_name());

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
	public void update(BankcodeVO bankcodeVO3) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, bankcodeVO3.getB_code());
			pstmt.setString(2, bankcodeVO3.getB_name());
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
	public void delete(String b_code) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, b_code);

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
	public BankcodeVO findByPrimaryKey(String b_code) {

		BankcodeVO bankcodeVO2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, b_code);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				bankcodeVO2 = new BankcodeVO();
				bankcodeVO2.setB_code(rs.getString("b_code"));
				bankcodeVO2.setB_name(rs.getString("b_name"));
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
		return bankcodeVO2;
	}

	@Override
	public List<BankcodeVO> getAll() {
		List<BankcodeVO> list = new ArrayList<BankcodeVO>();
		BankcodeVO bankcodeVO2 = null;

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
				bankcodeVO2 = new BankcodeVO();
				bankcodeVO2.setB_code(rs.getString("b_code"));
				bankcodeVO2.setB_name(rs.getString("b_name"));
				list.add(bankcodeVO2); // Store the row in the list
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
		BankcodeJDBCDAO dao = new BankcodeJDBCDAO();

		// 新增
//		BankcodeVO bankcodeVO1 = new BankcodeVO();
//		bankcodeVO1.setB_code("011");
//		bankcodeVO1.setB_name("合作金庫");
//
//		dao.insert(bankcodeVO1);

		// 查詢
		BankcodeVO bankcodeVO2 = dao.findByPrimaryKey("700");
		System.out.print(bankcodeVO2.getB_code() + ",");
		System.out.println(bankcodeVO2.getB_name());
		System.out.println("---------------------");

		// 查詢
//		List<BankcodeVO> list = dao.getAll();
//		for (BankcodeVO aBa : list) {
//			System.out.print(aBa.getB_code() + ",");
//			System.out.print(aBa.getB_name());
//			System.out.println();
//		}
		// 刪除
//		dao.delete("700");
		
		// 修改
//		BankcodeVO bankcodeVO3 = new BankcodeVO();
//		bankcodeVO3.setB_code("700");
//		bankcodeVO3.setB_name("中華郵政");
//		dao.update(bankcodeVO3);
	}
}