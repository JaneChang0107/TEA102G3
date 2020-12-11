package com.viewseller.model;

import java.sql.*;
import java.util.*;



public class ViewsellerJDBCDAO implements ViewsellerDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";
	
	private static final String INSERT_STMT = 
			"INSERT INTO VIEWSELLER  (V_ID,O_ID,M_BUYID,M_SELLID, V_GB,V_COMMENT, V_DATE) VALUES ('V' || lpad(VIEWSELLER_SEQ.nextval,5,'0'), ?, ?, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT V_ID,O_ID,M_BUYID,M_SELLID, V_GB,V_COMMENT, V_DATE FROM VIEWSELLER  order by  V_ID";
		private static final String GET_ONE_STMT = 
			"SELECT V_ID,O_ID,M_BUYID,M_SELLID, V_GB,V_COMMENT, V_DATE FROM VIEWSELLER where V_ID = ?";
		private static final String DELETE = 
			"DELETE FROM VIEWSELLER where V_ID = ?";
		private static final String UPDATE = 
			"UPDATE VIEWSELLER set O_id= ? ,M_buyid=? ,M_sellid = ?, V_gb = ?,V_comment = ?, V_date = ? where V_id =?";
	
	@Override
	public void insert(ViewsellerVO viewsellerVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,viewsellerVO.getO_id());
			pstmt.setString(2,viewsellerVO.getM_buyid());
			pstmt.setString(3,viewsellerVO.getM_sellid());
			pstmt.setString(4,viewsellerVO.getV_gb());
			pstmt.setString(5,viewsellerVO.getV_comment());
			pstmt.setTimestamp(6,viewsellerVO.getV_date());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	public void update(ViewsellerVO viewsellerVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, viewsellerVO.getO_id());
			pstmt.setString(2, viewsellerVO.getM_buyid());
			pstmt.setString(3,viewsellerVO.getM_sellid());
			pstmt.setString(4, viewsellerVO.getV_gb());
			pstmt.setString(5,viewsellerVO.getV_comment());
			pstmt.setTimestamp(6,viewsellerVO.getV_date());
			pstmt.setString(7, viewsellerVO.getV_id());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	public void delete(String viewsellerVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, viewsellerVO );
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	public ViewsellerVO findByPrimaryKey(String viewsellerVO) {
		// TODO Auto-generated method stub
		
		ViewsellerVO viewsellerVO1=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,viewsellerVO);
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				viewsellerVO1 = new ViewsellerVO();
				viewsellerVO1.setV_id(rs.getString("v_id"));
				viewsellerVO1.setO_id(rs.getString("o_id"));
				viewsellerVO1.setM_buyid(rs.getString("m_buyid"));
				viewsellerVO1.setM_sellid(rs.getString("m_sellid"));
				viewsellerVO1.setV_gb(rs.getString("v_gb"));
				viewsellerVO1.setV_comment(rs.getString("v_comment"));
				viewsellerVO1.setV_date(rs.getTimestamp("v_date"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		
		return viewsellerVO1;
	}

	@Override
	public List<ViewsellerVO> getAll() {
		// TODO Auto-generated method stub
		List<ViewsellerVO> list = new ArrayList<ViewsellerVO>();
		ViewsellerVO viewsellerVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				viewsellerVO = new ViewsellerVO();
				viewsellerVO.setV_id(rs.getString("v_id"));
				viewsellerVO.setO_id(rs.getString("o_id"));
				viewsellerVO.setM_buyid(rs.getString("m_buyid"));
				viewsellerVO.setM_sellid(rs.getString("m_sellid"));
				viewsellerVO.setV_gb(rs.getString("v_gb"));
				viewsellerVO.setV_comment(rs.getString("v_comment"));
				viewsellerVO.setV_date(rs.getTimestamp("v_date"));
				list.add(viewsellerVO);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		ViewsellerJDBCDAO dao = new ViewsellerJDBCDAO();
		// �s�W
//		ViewsellerVO viewsellerVO1 = new ViewsellerVO();
//		viewsellerVO1.setO_id("O00001");
//		viewsellerVO1.setM_buyid("M00001");
//		viewsellerVO1.setM_sellid("M00002");
//		viewsellerVO1.setV_gb("good");
//		viewsellerVO1.setV_comment("�X�f�t�׫D�`��");
//		viewsellerVO1.setV_date(d);
//		
//		dao.insert(viewsellerVO1);
		
		// �ק�
//		ViewsellerVO viewsellerVO2 = new ViewsellerVO();
//		viewsellerVO2.setV_id("V00005");
//		viewsellerVO2.setO_id("O00001");
//		viewsellerVO2.setM_buyid("M00001");
//		viewsellerVO2.setM_sellid("M00003");
//		viewsellerVO2.setV_gb("good");
//		viewsellerVO2.setV_comment("�X�f�t�׫D�`�C");
//		viewsellerVO2.setV_date(d);
//		dao.update(viewsellerVO2);
		
		//// �R��
//		dao.delete("V00005");
		
		// �d��1
		ViewsellerVO viewsellerVO3 = dao.findByPrimaryKey("V00006");
		System.out.println(viewsellerVO3.getV_id()+",");
		System.out.println(viewsellerVO3.getO_id()+",");
		System.out.println(viewsellerVO3.getM_buyid()+",");
		System.out.println(viewsellerVO3.getM_sellid()+",");
		System.out.println(viewsellerVO3.getV_gb()+",");
		System.out.println(viewsellerVO3.getV_comment()+",");
		System.out.println(viewsellerVO3.getV_date());
		System.out.println("---------------------one");
		
		// �d��all
		List<ViewsellerVO> list =dao.getAll();
		for(ViewsellerVO av :list) {
			System.out.println(av.getV_id()+",");
			System.out.println(av.getO_id()+",");
			System.out.println(av.getM_buyid()+",");
			System.out.println(av.getM_sellid()+",");
			System.out.println(av.getV_gb()+",");
			System.out.println(av.getV_comment()+",");
			System.out.println(av.getV_date());
			System.out.println("---------------------");
		}
		
	}
	
}
