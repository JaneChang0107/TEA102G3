package com.orderdetail.model;

import java.sql.*;
import java.util.*;


public class OrderdetailJDBCDAO implements OrderdetailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";
	
		private static final String INSERT_STMT = 
			"INSERT INTO orderdetail (od_id,o_id,p_id,od_count) VALUES ('OD' || lpad(ORDERDETAIL_SEQ.nextval,5,'0'), ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT od_id,o_id,p_id,od_count FROM orderdetail order by od_id";
		private static final String GET_ONE_STMT = 
			"SELECT od_id,o_id,p_id,od_count FROM orderdetail where od_id = ?";
		private static final String DELETE = 
			"DELETE FROM orderdetail where od_id = ?";
		private static final String UPDATE = 
			"UPDATE orderdetail set o_id=?, p_id=?,od_count=? where od_id=?";
		private static final String COUNT=
//			"select p_id ,sum(od_count) from orderdetail  group by p_id order by sum(od_count)desc";
		"select orderdetail.p_id ,sum(od_count),productpicture.pp_id from orderdetail inner join productpicture on orderdetail.p_id=productpicture.p_id group by orderdetail.p_id ,productpicture.pp_id order by sum(od_count)desc";
		
		//拿到單筆訂單詳情
		private static final String GET_DETAIL_ByOrder ="SELECT * FROM ORDERDETAIL WHERE O_ID=?";		
		//通知總覽
		
		
		
		@Override
		public List<OrderdetailVO> count() {
			List<OrderdetailVO> list = new ArrayList<OrderdetailVO>();
			OrderdetailVO orderdetailVO4 = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt=con.prepareStatement(COUNT);
				rs = pstmt.executeQuery();

					while (rs.next()) {
						orderdetailVO4=new OrderdetailVO();
						orderdetailVO4.setP_id(rs.getString("p_id"));
						orderdetailVO4.setOd_count(rs.getInt("sum(od_count)"));
						orderdetailVO4.setPp_id(rs.getString("pp_id"));
						list.add(orderdetailVO4);
						
					}
			}catch(Exception e) {
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
		
		
	@Override
	public void insert(OrderdetailVO orderdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1,orderdetailVO.getO_id());
				pstmt.setString(2,orderdetailVO.getP_id());
				pstmt.setInt(3, orderdetailVO.getOd_count());
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
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
	public void update(OrderdetailVO orderdetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,orderdetailVO.getO_id());
			pstmt.setString(2,orderdetailVO.getP_id());
			pstmt.setInt(3, orderdetailVO.getOd_count());
			pstmt.setString(4, orderdetailVO.getOd_id());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
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
	public void delete(String orderdetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, orderdetailVO );
			
			pstmt.executeUpdate();
		} catch (Exception e) {
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
	public OrderdetailVO findByPrimaryKey(String orderdetailVO) {
		OrderdetailVO orderdetailVO3 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ONE_STMT);
		
		pstmt.setString(1,orderdetailVO);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			orderdetailVO3=new OrderdetailVO();
			orderdetailVO3.setOd_id(rs.getString("od_id"));
			orderdetailVO3.setO_id(rs.getString("o_id"));
			orderdetailVO3.setP_id(rs.getString("p_id"));
			orderdetailVO3.setOd_count(rs.getInt("od_count"));
		}
		
		}catch(Exception e) {
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
		return orderdetailVO3;
	}

	@Override
	public List<OrderdetailVO> getAll() {
		List<OrderdetailVO> list = new ArrayList<OrderdetailVO>();
		OrderdetailVO orderdetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderdetailVO=new OrderdetailVO();
				orderdetailVO.setOd_id(rs.getString("od_id"));
				orderdetailVO.setO_id(rs.getString("o_id"));
				orderdetailVO.setP_id(rs.getString("p_id"));
				orderdetailVO.setOd_count(rs.getInt("od_count"));
				list.add(orderdetailVO);
			}
			
		} catch (Exception e) {
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
	
	@Override
	public List<OrderdetailVO> getDetailByOrder(String o_id) {
		List<OrderdetailVO> list = new ArrayList<OrderdetailVO>();
		OrderdetailVO orderdetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_DETAIL_ByOrder);
			pstmt.setString(1,o_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderdetailVO=new OrderdetailVO();
				orderdetailVO.setOd_id(rs.getString("od_id"));
				orderdetailVO.setO_id(rs.getString("o_id"));
				orderdetailVO.setP_id(rs.getString("p_id"));
				orderdetailVO.setOd_count(rs.getInt("od_count"));
				list.add(orderdetailVO);
			}
			
		} catch (Exception e) {
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
		OrderdetailJDBCDAO dao = new OrderdetailJDBCDAO();
		//// 新增
//		OrderdetailVO orderdetailVO1 = new OrderdetailVO();
//		orderdetailVO1.setO_id("O00002");
//		orderdetailVO1.setP_id("P00001");
//		orderdetailVO1.setOd_count(1);
//		dao.insert(orderdetailVO1);
	
		//// 修改
//		OrderdetailVO orderdetailVO2 = new OrderdetailVO();
//		orderdetailVO2.setOd_id("OD00006");
//		orderdetailVO2.setO_id("O00002");
//		orderdetailVO2.setP_id("P00005");
//		orderdetailVO2.setOd_count(1);
//		dao.update(orderdetailVO2);
	
		//// 刪除
//		dao.delete("OD00008");
		
//		// 查詢1
//		OrderdetailVO orderdetailVO3 = dao.findByPrimaryKey("OD00007");
//		System.out.println(orderdetailVO3.getOd_id()+",");
//		System.out.println(orderdetailVO3.getO_id()+",");
//		System.out.println(orderdetailVO3.getP_id()+",");
//		System.out.println(orderdetailVO3.getOd_count());
//		System.out.println("---------------------one");
//		
//		// 查詢all
//		List<OrderdetailVO> list= dao.getAll();
//		for(OrderdetailVO aod :list) {
//			System.out.println(aod.getOd_id()+",");
//			System.out.println(aod.getO_id()+",");
//			System.out.println(aod.getP_id()+",");
//			System.out.println(aod.getOd_count());
//			System.out.println("---------------------");
//		}
//		
		
//		List<OrderdetailVO> list2= dao.count();
//		for(OrderdetailVO aod :list2) {
//			
//			System.out.println(aod.getP_id()+",");
//			System.out.println(aod.getOd_count());
//			System.out.println(aod.getPp_id());
//			System.out.println("---------------------");
//		}
		
		List<OrderdetailVO> list3 =dao.getDetailByOrder("O00021");
		for(OrderdetailVO aod :list3) {
			System.out.println(aod.getOd_id()+",");
			System.out.println(aod.getO_id()+",");
			System.out.println(aod.getP_id()+",");
			System.out.println(aod.getOd_count());
		    System.out.println("---------------------");
	}

	
	}
	
}
