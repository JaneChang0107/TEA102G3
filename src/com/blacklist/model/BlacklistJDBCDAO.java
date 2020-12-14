package com.blacklist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlacklistJDBCDAO implements BlacklistDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";

	private static final String INSERT_STMT = "INSERT INTO blacklist (bl_id,m_id,m_blackid) VALUES ('BL'|| lpad(BLACKLIST_SEQ.NEXTVAL,5,'0'),?,?)";
	private static final String GET_ALL_STMT = "SELECT bl_id,m_id,m_blackid FROM blacklist order by bl_id";
	private static final String GET_ONE_STMT = "SELECT bl_id,m_id,m_blackid FROM blacklist where bl_id = ?";
//	private static final String DELETE = "DELETE FROM blacklist where bl_id = ?";
	private static final String UPDATE = "UPDATE blacklist set bl_id=?, m_id=?, m_blackid where bl_id = ?";

	@Override
	public void insert(BlacklistVO blacklistVO1) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, blacklistVO1.getM_id());
			pstmt.setString(2, blacklistVO1.getM_blackId());

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
	public void update(BlacklistVO blacklistVO3) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, blacklistVO3.getM_id());
			pstmt.setString(2, blacklistVO3.getM_blackId());
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
	public void delete(String bl_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bl_id);

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
	public BlacklistVO findByPrimaryKey(String bl_id) {

		BlacklistVO blacklistVO2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bl_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				blacklistVO2 = new BlacklistVO();
				blacklistVO2.setBl_id(rs.getString("bl_id"));
				blacklistVO2.setM_id(rs.getString("m_id"));
				blacklistVO2.setM_blackId(rs.getString("m_blackid"));
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
		return blacklistVO2;
	}

	@Override
	public List<BlacklistVO> getAll() {
		List<BlacklistVO> list = new ArrayList<BlacklistVO>();
		BlacklistVO blacklistVO2 = null;

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
				blacklistVO2 = new BlacklistVO();
				blacklistVO2.setBl_id(rs.getString("bl_id"));
				blacklistVO2.setM_id(rs.getString("m_id"));
				blacklistVO2.setM_blackId(rs.getString("m_blackid"));
				list.add(blacklistVO2); // Store the row in the list
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
		BlacklistJDBCDAO dao = new BlacklistJDBCDAO();

		// 新增
//		BlacklistVO blacklistVO1 = new BlacklistVO();
//		blacklistVO1.setM_id("M00003");
//		blacklistVO1.setM_blackId("M00004");
//		dao.insert(blacklistVO1);

		// 查詢
		BlacklistVO blacklistVO2 = dao.findByPrimaryKey("BL00001");
		System.out.print(blacklistVO2.getBl_id() + ",");
		System.out.print(blacklistVO2.getM_id() + ",");
		System.out.println(blacklistVO2.getM_blackId());
		System.out.println("---------------------");

//		// 查詢
//		List<BlacklistVO> list = dao.getAll();
//		for (BlacklistVO aBl : list) {
//			System.out.print(aBl.getBl_id() + ",");
//			System.out.print(aBl.getM_id()+ ",");
//			System.out.print(aBl.getM_blackId());
//			System.out.println();
//		}
		// 刪除
//		dao.delete("BL00001");

		// 修改
//		BlacklistVO blacklistVO3 = new BlacklistVO();
//		blacklistVO3.setBl_id("BL00001");
//		blacklistVO3.setM_id("M00001");
//		blacklistVO3.setM_blackId("M00002");
//		dao.update(blacklistVO3);
	}
}
