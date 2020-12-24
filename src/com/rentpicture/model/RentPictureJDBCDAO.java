package com.rentpicture.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentPictureJDBCDAO implements RentPictureDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";

	private static final String INSERT_STMT = "INSERT INTO rentpicture (rp_id,rp_picture,r_id) VALUES ( 'RP' || lpad(RENTPICTURE_SEQ.NEXTVAL, 5, '0'),?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM rentpicture order by rp_id";
	private static final String GET_ONE_STMT = "SELECT * FROM rentpicture where rp_id = ?";
	private static final String GET_RID_STMT = "SELECT * FROM rentpicture where r_id = ?";
	private static final String DELETE = "DELETE FROM rentpicture where rp_id = ?";
	private static final String UPDATE = "UPDATE rentpicture set rp_picture=?,r_id=? where rp_id = ?";

	@Override
	public void insert(RentPictureVO rentPictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setBytes(1, rentPictureVO.getRp_picture());
			pstmt.setString(2, rentPictureVO.getR_id());
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
	public void update(RentPictureVO rentPictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setBytes(1, rentPictureVO.getRp_picture());
			pstmt.setString(2, rentPictureVO.getR_id());
			pstmt.setString(3, rentPictureVO.getRp_id());

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
	public void delete(String rp_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rp_id);

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
	public RentPictureVO findByPrimaryKey(String rp_id) {
		RentPictureVO rentPictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rentPictureVO = new RentPictureVO();
				rentPictureVO.setRp_id(rs.getString("rp_id"));
				rentPictureVO.setRp_picture(rs.getBytes("rp_picture"));
				rentPictureVO.setR_id(rs.getString("r_id"));

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
		return rentPictureVO;
	}

	@Override
	public List<RentPictureVO> getAll() {
		List<RentPictureVO> list = new ArrayList<RentPictureVO>();
		RentPictureVO rentPictureVO = null;

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
				rentPictureVO = new RentPictureVO();
				rentPictureVO.setRp_id(rs.getString("rp_id"));
				rentPictureVO.setRp_picture(rs.getBytes("rp_picture"));
				rentPictureVO.setR_id(rs.getString("r_id"));

				list.add(rentPictureVO); // Store the row in the list
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
	
	@Override
	public List<RentPictureVO> getRidPic(String r_id) {
		
		List<RentPictureVO> list = new ArrayList<RentPictureVO>();
		RentPictureVO rentPictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_RID_STMT);
			pstmt.setString(1, r_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rentPictureVO = new RentPictureVO();
				rentPictureVO.setRp_id(rs.getString("rp_id"));
				rentPictureVO.setRp_picture(rs.getBytes("rp_picture"));
				rentPictureVO.setR_id(rs.getString("r_id"));

				list.add(rentPictureVO); // Store the row in the list
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

	// 插入圖片
	public static byte[] getPic(String path) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(path);
			buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("找不到檔案");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("傳輸過程發生問題");
			e.printStackTrace();
		}
		return buffer;
	}

	public static void main(String[] args) {
 //insert
		RentPictureJDBCDAO dao = new RentPictureJDBCDAO();
//		RentPictureVO rentPictureVO = new RentPictureVO();
//		byte[] pic=getPic("WebContent/images/ps4test.png");
//		System.out.println("XXX");
//		rentPictureVO.setRp_picture(pic);
//		rentPictureVO.setR_id("R00001");
//		
//		dao.insert(rentPictureVO);
		

//getAll;
//		List<RentPictureVO> list = dao.getAll();
//		for (RentPictureVO rpv : list) {
//			System.out.print(rp	v.getRp_id().toString() + ",");
//			System.out.print(rpv.getRp_picture() + ",");
//			System.out.print(rpv.getR_id() );
//			
//			System.out.println();  
//		}

// update
//		RentPictureVO rpv = new RentPictureVO();
//		rpv.setR_id("R00001");
//		byte[] pic=getPic("WebContent/images/apple.jpg");
//		System.out.println("XXX");
//		rpv.setRp_picture(pic);
//		rpv.setRp_id("RP00011");
//		
//		dao.update(rpv);
//		System.out.println("XXX");
		
//delete
//		dao.delete("RP00003");
//		System.out.println("delete success");
		
		//getAll;
		List<RentPictureVO> list = dao.getRidPic("R00002");
		for (RentPictureVO rpv : list) {
			System.out.print(rpv.getRp_id().toString() + ",");
			System.out.print(rpv.getRp_picture() + ",");
			System.out.print(rpv.getR_id() );
			
			System.out.println();  
		}
//		
	}

	

	
}
