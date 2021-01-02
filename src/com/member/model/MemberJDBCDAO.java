package com.member.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";

	private static final String INSERT_STMT = "INSERT INTO member (m_id,m_email,m_password,m_name,m_gender,m_phone,m_address,m_birth,m_status,m_coin) VALUES('M' || LPAD(MEMBER_SEQ.NEXTVAL, 5, 0),?,?,?,?,?,?,?,0,100)";

	private static final String INSERTSELLER_STMT = "INSERT INTO member (m_id,m_email,m_password,m_name,m_gender,m_phone,m_address,m_birth,m_headpic,m_status,m_identity,m_id_pic"
			+ ",m_account,m_accountname,b_code,m_bank_pic,m_storename,m_info,m_cover,m_hi,m_offlinehi,m_coin) VALUES('M' || LPAD(MEMBER_SEQ.NEXTVAL, 5, 0),?,?,?,?,?,?,?,?,0,?,?,?,?,?,?,?,?,?,?,?,100)";

	private static final String GET_ALL_STMT = "SELECT m_id,m_email,m_password,m_name,m_gender,m_phone,m_address,to_char(m_birth,'yyyy-mm-dd') m_birth,m_headpic,m_status,m_identity,m_id_pic"
			+ ",m_account,m_accountname,b_code,m_bank_pic,m_moneytrandate,m_storename,m_info,m_cover,m_hi,m_offlinehi,m_coin FROM member order by m_id";

	private static final String GET_ONE_STMT = "SELECT m_id,m_email,m_password,m_name,m_gender,m_phone,m_address,to_char(m_birth,'yyyy-mm-dd') m_birth,m_headpic,m_status,m_identity,m_id_pic"
			+ ",m_account,m_accountname,b_code,m_bank_pic,m_moneytrandate,m_storename,m_info,m_cover,m_hi,m_offlinehi,m_coin FROM member where m_id = ?";

	private static final String DELETE = "DELETE FROM member where m_id = ? ";

	private static final String UPDATE = "UPDATE member set m_email=?,m_password=?,m_name=?,m_gender=?,m_phone=?,m_address=?,m_birth=?,m_headpic=?,m_identity=?,m_id_pic=?"
			+ ",m_account=?,m_accountname=?,b_code=?,m_bank_pic=?,m_storename=?,m_info=?,m_cover=?,m_hi=?,m_offlinehi=? where m_id=?";
	private static final String UPDATE_PW ="UPDATE member set m_password=? where m_id=?";

	private static final String GET_Mems_ByStatus_STMT = "SELECT * FROM member WHERE m_status =?";

	private static final String GET_Mems_Password_STMT = "SELECT m_id,m_name,m_email,m_password FROM member WHERE m_email=?";
	
	private static final String UPDATE_Status_STMT ="UPDATE member set m_status=1 where m_email=?";

	// 新增一般會員
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, memberVO.getM_email());
			pstmt.setString(2, memberVO.getM_password());
			pstmt.setString(3, memberVO.getM_name());
			pstmt.setString(4, memberVO.getM_gender());
			pstmt.setString(5, memberVO.getM_phone());
			pstmt.setString(6, memberVO.getM_address());
			pstmt.setDate(7, memberVO.getM_birth());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 新增賣家會員
	@Override
	public void insertSeller(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERTSELLER_STMT);
			pstmt.setString(1, memberVO.getM_email());
			pstmt.setString(2, memberVO.getM_password());
			pstmt.setString(3, memberVO.getM_name());
			pstmt.setString(4, memberVO.getM_gender());
			pstmt.setString(5, memberVO.getM_phone());
			pstmt.setString(6, memberVO.getM_address());
			pstmt.setDate(7, memberVO.getM_birth());
			pstmt.setBytes(8, memberVO.getM_headpic());
			pstmt.setString(9, memberVO.getM_identity());
			pstmt.setBytes(10, memberVO.getM_id_pic());
			pstmt.setString(11, memberVO.getM_account());
			pstmt.setString(12, memberVO.getM_accountName());
			pstmt.setString(13, memberVO.getB_code());
			pstmt.setBytes(14, memberVO.getM_bank_pic());
			pstmt.setString(15, memberVO.getM_storename());
			pstmt.setString(16, memberVO.getM_info());
			pstmt.setBytes(17, memberVO.getM_cover());
			pstmt.setString(18, memberVO.getM_hi());
			pstmt.setString(19, memberVO.getM_offlineHi());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, memberVO.getM_email());
			pstmt.setString(2, memberVO.getM_password());
			pstmt.setString(3, memberVO.getM_name());
			pstmt.setString(4, memberVO.getM_gender());
			pstmt.setString(5, memberVO.getM_phone());
			pstmt.setString(6, memberVO.getM_address());
			pstmt.setDate(7, memberVO.getM_birth());
			pstmt.setBytes(8, memberVO.getM_headpic());
			pstmt.setString(9, memberVO.getM_identity());
			pstmt.setBytes(10, memberVO.getM_id_pic());
			pstmt.setString(11, memberVO.getM_account());
			pstmt.setString(12, memberVO.getM_accountName());
			pstmt.setString(13, memberVO.getB_code());
			pstmt.setBytes(14, memberVO.getM_bank_pic());
			pstmt.setString(15, memberVO.getM_storename());
			pstmt.setString(16, memberVO.getM_info());
			pstmt.setBytes(17, memberVO.getM_cover());
			pstmt.setString(18, memberVO.getM_hi());
			pstmt.setString(19, memberVO.getM_offlineHi());

			pstmt.setString(20, memberVO.getM_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void updatepw(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_PW);
			pstmt.setString(1, memberVO.getM_password());
			pstmt.setString(2, memberVO.getM_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String m_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, m_id);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MemberVO findByPK(String m_id) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, m_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();

				memberVO.setM_id(rs.getString("m_id"));
				memberVO.setM_email(rs.getString("m_email"));
				memberVO.setM_password(rs.getString("m_password"));
				memberVO.setM_name(rs.getString("m_name"));
				memberVO.setM_gender(rs.getString("m_gender"));
				memberVO.setM_phone(rs.getString("m_phone"));
				memberVO.setM_address(rs.getString("m_address"));
				memberVO.setM_birth(rs.getDate("m_birth"));
				memberVO.setM_headpic(rs.getBytes("m_headpic"));
				memberVO.setM_status(rs.getInt("m_status"));
				memberVO.setM_identity(rs.getString("m_identity"));
				memberVO.setM_id_pic(rs.getBytes("m_id_pic"));
				memberVO.setM_account(rs.getString("m_account"));
				memberVO.setM_accountName(rs.getString("m_accountName"));
				memberVO.setB_code(rs.getString("b_code"));
				memberVO.setM_bank_pic(rs.getBytes("m_bank_pic"));
				memberVO.setM_moneyTranDate(rs.getTimestamp("m_moneyTranDate"));
				memberVO.setM_storename(rs.getString("m_storename"));
				memberVO.setM_info(rs.getString("m_info"));
				memberVO.setM_cover(rs.getBytes("m_cover"));
				memberVO.setM_hi(rs.getString("m_hi"));
				memberVO.setM_offlineHi(rs.getString("m_offlineHi"));
				memberVO.setM_coin(rs.getInt("m_coin"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setM_id(rs.getString("m_id"));
				memberVO.setM_email(rs.getString("m_email"));
				memberVO.setM_password(rs.getString("m_password"));
				memberVO.setM_name(rs.getString("m_name"));
				memberVO.setM_gender(rs.getString("m_gender"));
				memberVO.setM_phone(rs.getString("m_phone"));
				memberVO.setM_address(rs.getString("m_address"));
				memberVO.setM_birth(rs.getDate("m_birth"));
				memberVO.setM_headpic(rs.getBytes("m_headpic"));
				memberVO.setM_status(rs.getInt("m_status"));
				memberVO.setM_identity(rs.getString("m_identity"));
				memberVO.setM_id_pic(rs.getBytes("m_id_pic"));
				memberVO.setM_account(rs.getString("m_account"));
				memberVO.setM_accountName(rs.getString("m_accountName"));
				memberVO.setB_code(rs.getString("b_code"));
				memberVO.setM_bank_pic(rs.getBytes("m_bank_pic"));
				memberVO.setM_moneyTranDate(rs.getTimestamp("m_moneyTranDate"));
				memberVO.setM_storename(rs.getString("m_storename"));
				memberVO.setM_info(rs.getString("m_info"));
				memberVO.setM_cover(rs.getBytes("m_cover"));
				memberVO.setM_hi(rs.getString("m_hi"));
				memberVO.setM_offlineHi(rs.getString("m_offlineHi"));
				memberVO.setM_coin(rs.getInt("m_coin"));

				list.add(memberVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	@Override
	public Set<MemberVO> getMemberByStatus(Integer m_status) {
		Set<MemberVO> set = new LinkedHashSet<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Mems_ByStatus_STMT);
			pstmt.setInt(1, m_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setM_id(rs.getString("m_id"));
				memberVO.setM_email(rs.getString("m_email"));
				memberVO.setM_password(rs.getString("m_password"));
				memberVO.setM_name(rs.getString("m_name"));
				memberVO.setM_gender(rs.getString("m_gender"));
				memberVO.setM_phone(rs.getString("m_phone"));
				memberVO.setM_address(rs.getString("m_address"));
				memberVO.setM_birth(rs.getDate("m_birth"));
				memberVO.setM_headpic(rs.getBytes("m_headpic"));
				memberVO.setM_status(rs.getInt("m_status"));
				memberVO.setM_identity(rs.getString("m_identity"));
				memberVO.setM_id_pic(rs.getBytes("m_id_pic"));
				memberVO.setM_account(rs.getString("m_account"));
				memberVO.setM_accountName(rs.getString("m_accountName"));
				memberVO.setB_code(rs.getString("b_code"));
				memberVO.setM_bank_pic(rs.getBytes("m_bank_pic"));
				memberVO.setM_moneyTranDate(rs.getTimestamp("m_moneyTranDate"));
				memberVO.setM_storename(rs.getString("m_storename"));
				memberVO.setM_info(rs.getString("m_info"));
				memberVO.setM_cover(rs.getBytes("m_cover"));
				memberVO.setM_hi(rs.getString("m_hi"));
				memberVO.setM_offlineHi(rs.getString("m_offlineHi"));
				memberVO.setM_coin(rs.getInt("m_coin"));
				set.add(memberVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return set;
	}

	@Override
	public MemberVO getMemberPw(String m_email) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Mems_Password_STMT);

			pstmt.setString(1, m_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setM_id(rs.getString("m_id"));
				memberVO.setM_name(rs.getString("m_name"));
				memberVO.setM_email(rs.getString("m_email"));
				memberVO.setM_password(rs.getString("m_password"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return memberVO;
	}
	
	
	@Override
	public void activeMember(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt =con.prepareStatement(UPDATE_Status_STMT);
			pstmt.setString(1, memberVO.getM_email());
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	

	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();

//		//新增
//		MemberVO memberVO1=new MemberVO();		
//		memberVO1.setM_email("a111@yahoo.com.tw");
//		memberVO1.setM_password("123456");
//		memberVO1.setM_name("游阿坤");
//		memberVO1.setM_gender("男");
//		memberVO1.setM_phone("0911111111");
//		memberVO1.setM_address("台北市中正區濟南路一段321號");
//		memberVO1.setM_birth(java.sql.Date.valueOf("1981-11-17"));
//		dao.insert(memberVO1);
//		System.out.println("插入成功");

		// 新增賣家
//		MemberVO memberVO11 = new MemberVO();
//		
//		memberVO11.setM_email("a111@yahoo.com.tw");
//		memberVO11.setM_password("123456");
//		memberVO11.setM_name("游阿坤");
//		memberVO11.setM_gender("男");
//		memberVO11.setM_phone("0911111111");
//		memberVO11.setM_address("台北市中正區濟南路一段321號");
//		memberVO11.setM_birth(java.sql.Date.valueOf("1981-11-17"));
//		
//		byte[] pic = getPic("WebContent/images/apple.jpg");
//		memberVO11.setM_headpic(pic);
//		
//		memberVO11.setM_identity("F123456789");
//		memberVO11.setM_id_pic(null);
//		memberVO11.setM_account("12345678901234");
//		memberVO11.setM_accountName("游阿坤");
//		memberVO11.setB_code("700");
//		memberVO11.setM_bank_pic(null);
//		memberVO11.setM_storename("阿坤市集");
//		memberVO11.setM_info(null);
//		memberVO11.setM_cover(null);
//		memberVO11.setM_hi("Hello我現在在線上");
//		memberVO11.setM_offlineHi("Sorry我現在不在線上");
//		
//		dao.insertSeller(memberVO11);
//		System.out.println("插入成功2");

//		
//		//修改
//		MemberVO memberVO2=new MemberVO();
//		memberVO2.setM_id("M00002");
//		memberVO2.setM_email("yoyoyo@yahoo.com.tw");
//		memberVO2.setM_password("123456");
//		memberVO2.setM_name("游鴻明");
//		memberVO2.setM_gender("男");
//		memberVO2.setM_phone("0911111111");
//		memberVO2.setM_address("台北市中正區濟南路一段321號");
//		memberVO2.setM_birth(java.sql.Date.valueOf("1981-11-17"));
//		byte[] pic=getPic("WebContent/images/apple.jpg");
//		memberVO2.setM_headpic(pic);
//		memberVO2.setM_status(1);
//		memberVO2.setM_identity("F123456789");
//		memberVO2.setM_id_pic(null);
//		memberVO2.setM_account("12345678901234");
//		memberVO2.setM_accountName("游阿坤");
//		memberVO2.setB_code("700");
//		memberVO2.setM_bank_pic(null);
////	memberVO2.setM_moneyTranDate(null);     //如送出修改時由CURRENT_TIMESTAMP送出現在時間
//		memberVO2.setM_storename("阿坤市集");
//		memberVO2.setM_info(null);
//		memberVO2.setM_cover(null);
//		memberVO2.setM_hi("Hello我現在在線上");
//		memberVO2.setM_offlineHi("Sorry我現在不在線上");
//		memberVO2.setM_coin(new Integer(100));
//		dao.update(memberVO2);
//		System.out.println("修改成功");

		// 刪除
//		dao.delete("M00005");
//		System.out.println("刪除成功");

		// 查詢
//		MemberVO memberVO3= dao.findByPK("M00003");
//		System.out.println(memberVO3.getM_id()+",");
//		System.out.println(memberVO3.getM_email()+",");
//		System.out.println(memberVO3.getM_password()+",");
//		System.out.println(memberVO3.getM_name()+",");
//		System.out.println(memberVO3.getM_gender()+",");
//		System.out.println(memberVO3.getM_phone()+",");
//		System.out.println(memberVO3.getM_address()+",");
//		System.out.println(memberVO3.getM_birth()+",");
//		System.out.println(memberVO3.getM_headpic()+",");
//		System.out.println(memberVO3.getM_status()+",");
//		System.out.println(memberVO3.getM_identity()+",");
//		System.out.println(memberVO3.getM_id_pic()+",");
//		System.out.println(memberVO3.getM_account()+",");
//		System.out.println(memberVO3.getM_accountName()+",");
//		System.out.println(memberVO3.getB_code()+",");
//		System.out.println(memberVO3.getM_bank_pic()+",");
//		System.out.println(memberVO3.getM_moneyTranDate()+",");
//		System.out.println(memberVO3.getM_storename()+",");
//		System.out.println(memberVO3.getM_info()+",");
//		System.out.println(memberVO3.getM_cover()+",");
//		System.out.println(memberVO3.getM_hi()+",");
//		System.out.println(memberVO3.getM_offlineHi()+",");
//		System.out.println(memberVO3.getM_coin()+",");
//		System.out.println("======查詢完畢=======");

		// 查全部
		List<MemberVO> list =dao.getAll();
		
		for(MemberVO aMember : list) {
//			System.out.println(aMember.getM_id()+",");
			
//			System.out.println(aMember.getM_email()+",");
//			System.out.println(aMember.getM_password()+",");
//			System.out.println(aMember.getM_name()+",");
//			System.out.println(aMember.getM_gender()+",");
//			System.out.println(aMember.getM_phone()+",");
//			System.out.println(aMember.getM_address()+",");
//			System.out.println(aMember.getM_birth()+",");
//			System.out.println(aMember.getM_headpic()+",");
//			System.out.println(aMember.getM_status()+",");
//			System.out.println(aMember.getM_identity()+",");
//			System.out.println(aMember.getM_id_pic()+",");
//			System.out.println(aMember.getM_account()+",");
//			System.out.println(aMember.getM_accountName()+",");
//			System.out.println(aMember.getB_code()+",");
//			System.out.println(aMember.getM_bank_pic()+",");
//			System.out.println(aMember.getM_moneyTranDate()+",");
//			System.out.println(aMember.getM_storename()+",");
//			System.out.println(aMember.getM_info()+",");
//			System.out.println(aMember.getM_cover()+",");
//			System.out.println(aMember.getM_hi()+",");
//			System.out.println(aMember.getM_offlineHi()+",");
//			System.out.println(aMember.getM_coin()+",");
//			System.out.println("----------------------");
			
		}
//		System.out.println("=====查詢全部完畢======");
//		
//		Set<MemberVO> set = dao.getMemberByStatus(2);
//		for(MemberVO aStatus : set) {
//			System.out.println(aStatus.getM_id()+",");
//			System.out.println(aStatus.getM_email()+",");
//			System.out.println(aStatus.getM_password()+",");
//			System.out.println(aStatus.getM_name()+",");
//			System.out.println(aStatus.getM_gender()+",");
//			System.out.println(aStatus.getM_phone()+",");
//			System.out.println(aStatus.getM_address()+",");
//			System.out.println(aStatus.getM_birth()+",");
//			System.out.println(aStatus.getM_headpic()+",");
//			System.out.println(aStatus.getM_status()+",");
//			System.out.println(aStatus.getM_identity()+",");
//			System.out.println(aStatus.getM_id_pic()+",");
//			System.out.println(aStatus.getM_account()+",");
//			System.out.println(aStatus.getM_accountName()+",");
//			System.out.println(aStatus.getB_code()+",");
//			System.out.println(aStatus.getM_bank_pic()+",");
//			System.out.println(aStatus.getM_moneyTranDate()+",");
//			System.out.println(aStatus.getM_storename()+",");
//			System.out.println(aStatus.getM_info()+",");
//			System.out.println(aStatus.getM_cover()+",");
//			System.out.println(aStatus.getM_hi()+",");
//			System.out.println(aStatus.getM_offlineHi()+",");
//			System.out.println(aStatus.getM_coin()+",");
//			System.out.println("----------------------");
//		}

		// 查密碼
//		MemberVO memberVO4= dao.getMemberPw("a111@yahoo.com.tw");	
//		System.out.println(memberVO4.getM_id());
//		System.out.println(memberVO4.getM_name());
//		System.out.println(memberVO4.getM_email());
//		System.out.println(memberVO4.getM_password());
		
		//改狀態
		MemberVO memberVO5 =new MemberVO();
		memberVO5.setM_email("qqqq@yahoo.com.tw");
		dao.activeMember(memberVO5);
		System.out.println("狀態已改");

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



}
