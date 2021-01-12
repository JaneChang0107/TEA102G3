package com.orderlist.model;

import java.sql.*;

import java.util.*;

public class OrderlistJDBCDAO implements OrderlistDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";

	private static final String INSERT_STMT = "INSERT INTO ORDERLIST   (o_id, o_date, o_status, o_shipdate, o_deceiptdate, o_finishdate, o_transport, o_address, o_total, o_pm, m_id) VALUES ('O' || lpad(ORDERLIST_SEQ.nextval,5,'0'), ?, ?, ?, ?, ?,? ,?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT o_id, o_date, o_status, o_shipdate, o_deceiptdate, o_finishdate, o_transport, o_address, o_total, o_pm, m_id FROM ORDERLIST   order by  o_id";
	private static final String GET_ONE_STMT = "SELECT  o_id, o_date, o_status, o_shipdate, o_deceiptdate, o_finishdate, o_transport, o_address, o_total, o_pm, m_id FROM ORDERLIST  where o_id = ?";
	private static final String DELETE = "DELETE FROM ORDERLIST  where o_id = ?";
	private static final String UPDATE = "UPDATE ORDERLIST  set  o_date = ? , o_status = ? , o_shipdate = ? , o_deceiptdate = ? , o_finishdate = ? , o_transport = ? , o_address = ? , o_total = ? , o_pm = ? , m_id = ?  where o_id =?";
	private static final String status = "select o_id, o_date, o_status, o_shipdate, o_deceiptdate, o_finishdate, o_transport, o_address, o_total, o_pm, m_id from orderlist where m_id = ?";
	//查詢訂單用
	private static final String GET_ORDER_ByMember ="SELECT * FROM ORDERLIST WHERE M_ID=?";
	private static final String GET_ORDER_ByMember_Status ="SELECT * FROM ORDERLIST WHERE M_ID=? AND O_STATUS=?";
	//改變訂單狀態(出貨用)
	private static final String UPDATE_Status_STMT ="UPDATE ORDERLIST SET o_shipdate=CURRENT_TIMESTAMP, o_status=? WHERE o_id=?";
	//改變訂單狀態(到貨用)
	private static final String UPDATE_Arrive_STMT ="UPDATE ORDERLIST SET o_deceiptdate=CURRENT_TIMESTAMP, o_status='已到貨' WHERE o_id=?";
	//改變訂單狀態(訂單完成用)
	private static final String UPDATE_Finish_STMT ="UPDATE ORDERLIST SET o_finishdate=CURRENT_TIMESTAMP, o_status='訂單完成' WHERE o_id=?";

	
	@Override
	public List<OrderlistVO> status() {
		
		List<OrderlistVO> list = new ArrayList<OrderlistVO>();
		OrderlistVO orderlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderlistVO = new OrderlistVO();
				orderlistVO.setO_id(rs.getString("o_id"));
				orderlistVO.setO_date(rs.getTimestamp("o_date"));
				orderlistVO.setO_status(rs.getString("o_status"));
				orderlistVO.setO_shipdate(rs.getTimestamp("o_shipdate"));
				orderlistVO.setO_deceiptdate(rs.getTimestamp("o_deceiptdate"));
				orderlistVO.setO_finishdate(rs.getTimestamp("o_finishdate"));
				orderlistVO.setO_transport(rs.getString("o_transport"));
				orderlistVO.setO_address(rs.getString("o_address"));
				orderlistVO.setO_total(rs.getInt("o_total"));
				orderlistVO.setO_pm(rs.getInt("o_pm"));
				orderlistVO.setM_id(rs.getString("m_id"));
				list.add(orderlistVO);
			}
			
		} catch (Exception e) {
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
		return list;
	}
	
	//查詢訂單用--------------------------------------
	@Override
	public List<OrderlistVO> findByMember(String m_id) {
		
		List<OrderlistVO> list = new ArrayList<OrderlistVO>();
		OrderlistVO orderlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ORDER_ByMember);
			pstmt.setString(1, m_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderlistVO = new OrderlistVO();
				orderlistVO.setO_id(rs.getString("o_id"));
				orderlistVO.setO_date(rs.getTimestamp("o_date"));
				orderlistVO.setO_status(rs.getString("o_status"));
				orderlistVO.setO_shipdate(rs.getTimestamp("o_shipdate"));
				orderlistVO.setO_deceiptdate(rs.getTimestamp("o_deceiptdate"));
				orderlistVO.setO_finishdate(rs.getTimestamp("o_finishdate"));
				orderlistVO.setO_transport(rs.getString("o_transport"));
				orderlistVO.setO_address(rs.getString("o_address"));
				orderlistVO.setO_total(rs.getInt("o_total"));
				orderlistVO.setO_pm(rs.getInt("o_pm"));
				orderlistVO.setM_id(rs.getString("m_id"));
				list.add(orderlistVO);
			}
			
		} catch (Exception e) {
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
		return list;
	}
	
	

	@Override
	public String insert(OrderlistVO orderlistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String oid = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String[] getoid = {"O_ID"};
			
			pstmt = con.prepareStatement(INSERT_STMT, getoid);

			pstmt.setTimestamp(1, orderlistVO.getO_date());
			pstmt.setString(2, orderlistVO.getO_status());
			pstmt.setTimestamp(3, orderlistVO.getO_shipdate());
			pstmt.setTimestamp(4, orderlistVO.getO_deceiptdate());
			pstmt.setTimestamp(5, orderlistVO.getO_finishdate());
			pstmt.setString(6, orderlistVO.getO_transport());
			pstmt.setString(7, orderlistVO.getO_address());
			pstmt.setInt(8, orderlistVO.getO_total());
			pstmt.setInt(9, orderlistVO.getO_pm());
			pstmt.setString(10, orderlistVO.getM_id());

			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();

			while(rs.next()) {
				oid = rs.getString(1);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
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
		return oid;	

	}
	
//修改訂單狀態
	@Override
	public void updateStatus(OrderlistVO orderlistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_Status_STMT);

			pstmt.setString(1, orderlistVO.getO_status());
			pstmt.setString(2, orderlistVO.getO_id());
			
			pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
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
	public void updateStatusArrive(String o_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_Arrive_STMT);

			pstmt.setString(1, o_id);
			
			pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
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
	public void updateStatusFinish(String o_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_Finish_STMT);

			pstmt.setString(1, o_id);
			
			pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
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
	public void update(OrderlistVO orderlistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, orderlistVO.getO_date());
			pstmt.setString(2, orderlistVO.getO_status());
			pstmt.setTimestamp(3, orderlistVO.getO_shipdate());
			pstmt.setTimestamp(4, orderlistVO.getO_deceiptdate());
			pstmt.setTimestamp(5, orderlistVO.getO_finishdate());
			pstmt.setString(6, orderlistVO.getO_transport());
			pstmt.setString(7, orderlistVO.getO_address());
			pstmt.setInt(8, orderlistVO.getO_total());
			pstmt.setInt(9, orderlistVO.getO_pm());
			pstmt.setString(10, orderlistVO.getM_id());
			pstmt.setString(11, orderlistVO.getO_id());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
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
	public void delete(String orderlistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, orderlistVO);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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
	public OrderlistVO findByPrimaryKey(String orderlistVO) {
		OrderlistVO orderlistVO1 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, orderlistVO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderlistVO1 = new OrderlistVO();
				orderlistVO1.setO_id(rs.getString("o_id"));
				orderlistVO1.setO_date(rs.getTimestamp("o_date"));
				orderlistVO1.setO_status(rs.getString("o_status"));
				orderlistVO1.setO_shipdate(rs.getTimestamp("o_shipdate"));
				orderlistVO1.setO_deceiptdate(rs.getTimestamp("o_deceiptdate"));
				orderlistVO1.setO_finishdate(rs.getTimestamp("o_finishdate"));
				orderlistVO1.setO_transport(rs.getString("o_transport"));
				orderlistVO1.setO_address(rs.getString("o_address"));
				orderlistVO1.setO_total(rs.getInt("o_total"));
				orderlistVO1.setO_pm(rs.getInt("o_pm"));
				orderlistVO1.setM_id(rs.getString("m_id"));
			}

		} catch (Exception e) {
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

		return orderlistVO1;
	}

	@Override
	public List<OrderlistVO> getAll() {
		List<OrderlistVO> list = new ArrayList<OrderlistVO>();
		OrderlistVO orderlistVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderlistVO = new OrderlistVO();
				orderlistVO.setO_id(rs.getString("o_id"));
				orderlistVO.setO_date(rs.getTimestamp("o_date"));
				orderlistVO.setO_status(rs.getString("o_status"));
				orderlistVO.setO_shipdate(rs.getTimestamp("o_shipdate"));
				orderlistVO.setO_deceiptdate(rs.getTimestamp("o_deceiptdate"));
				orderlistVO.setO_finishdate(rs.getTimestamp("o_finishdate"));
				orderlistVO.setO_transport(rs.getString("o_transport"));
				orderlistVO.setO_address(rs.getString("o_address"));
				orderlistVO.setO_total(rs.getInt("o_total"));
				orderlistVO.setO_pm(rs.getInt("o_pm"));
				orderlistVO.setM_id(rs.getString("m_id"));
				list.add(orderlistVO);
			}

		} catch (Exception e) {
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

		return list;
	}

	public static void main(String[] args) {
		Timestamp d = new Timestamp(System.currentTimeMillis());
		OrderlistJDBCDAO dao = new OrderlistJDBCDAO();

//		// 新增
//		OrderlistVO orderlistVO1= new OrderlistVO();
//		orderlistVO1.setO_id("O00001");
//		orderlistVO1.setO_date(d);
//		orderlistVO1.setO_status("已送達");
//		orderlistVO1.setO_shipdate(d);
//		orderlistVO1.setO_deceiptdate(d);
//		orderlistVO1.setO_finishdate(d);
//		orderlistVO1.setO_transport("便利商店");
//		orderlistVO1.setO_address("台北市xxx路");
//		orderlistVO1.setO_total(500);
//		orderlistVO1.setO_pm(5);
//		orderlistVO1.setM_id("M00001");
//		
//		dao.insert(orderlistVO1);

		// 修改
//		OrderlistVO orderlistVO2= new OrderlistVO();
//		orderlistVO2.setO_id("O00004");
//		orderlistVO2.setO_date(d);
//		orderlistVO2.setO_status("未送達");
//		orderlistVO2.setO_shipdate(d);
//		orderlistVO2.setO_deceiptdate(d);
//		orderlistVO2.setO_finishdate(d);
//		orderlistVO2.setO_transport("便利商店");
//		orderlistVO2.setO_address("台北市");
//		orderlistVO2.setO_total(500);
//		orderlistVO2.setO_pm(5);
//		orderlistVO2.setM_id("M00001");
//		
//		dao.update(orderlistVO2);
		
		OrderlistVO orderlistVO22 = new OrderlistVO();
		orderlistVO22.setO_id("O00001");
		orderlistVO22.setO_status("已出貨");
		
		dao.updateStatus(orderlistVO22);
		

//		// 刪除
//		dao.delete("O00003");

//		// 查詢1
//		OrderlistVO orderlistVO3 = dao.findByPrimaryKey("O00004");
//		System.out.println(orderlistVO3.getO_id() + ",");
//		System.out.println(orderlistVO3.getO_date() + ",");
//		System.out.println(orderlistVO3.getO_status() + ",");
//		System.out.println(orderlistVO3.getO_shipdate() + ",");
//		System.out.println(orderlistVO3.getO_deceiptdate() + ",");
//		System.out.println(orderlistVO3.getO_finishdate() + ",");
//		System.out.println(orderlistVO3.getO_transport() + ",");
//		System.out.println(orderlistVO3.getO_address() + ",");
//		System.out.println(orderlistVO3.getO_total() + ",");
//		System.out.println(orderlistVO3.getO_pm() + ",");
//		System.out.println(orderlistVO3.getM_id());
//		System.out.println("---------------------one");

//		// 查詢all
//		List<OrderlistVO> list = dao.getAll();
//		for (OrderlistVO ao : list) {
//			System.out.println(ao.getO_id() + ",");
//			System.out.println(ao.getO_date() + ",");
//			System.out.println(ao.getO_status() + ",");
//			System.out.println(ao.getO_shipdate() + ",");
//			System.out.println(ao.getO_deceiptdate() + ",");
//			System.out.println(ao.getO_finishdate() + ",");
//			System.out.println(ao.getO_transport() + ",");
//			System.out.println(ao.getO_address() + ",");
//			System.out.println(ao.getO_total() + ",");
//			System.out.println(ao.getO_pm() + ",");
//			System.out.println(ao.getM_id());
//			System.out.println("---------------------");
//		}

//		List<OrderlistVO> list = dao.status();
//		for (OrderlistVO ao : list) {
//			System.out.println(ao.getO_id() + ",");
//			System.out.println(ao.getO_date() + ",");
//			System.out.println(ao.getO_status() + ",");
//			System.out.println(ao.getO_shipdate() + ",");
//			System.out.println(ao.getO_deceiptdate() + ",");
//			System.out.println(ao.getO_finishdate() + ",");
//			System.out.println(ao.getO_transport() + ",");
//			System.out.println(ao.getO_address() + ",");
//			System.out.println(ao.getO_total() + ",");
//			System.out.println(ao.getO_pm() + ",");
//			System.out.println(ao.getM_id());
//			System.out.println("---------------------");
//		}
		
//		List<OrderlistVO> list =dao.findByMember("M00001");
//		for (OrderlistVO ao : list) {
//		System.out.println(ao.getO_id() + ",");
//		System.out.println(ao.getO_date() + ",");
//		System.out.println(ao.getO_status() + ",");
//		System.out.println(ao.getO_shipdate() + ",");
//		System.out.println(ao.getO_deceiptdate() + ",");
//		System.out.println(ao.getO_finishdate() + ",");
//		System.out.println(ao.getO_transport() + ",");
//		System.out.println(ao.getO_address() + ",");
//		System.out.println(ao.getO_total() + ",");
//		System.out.println(ao.getO_pm() + ",");
//		System.out.println(ao.getM_id());
//		System.out.println("---------------------");
//	}
		
//		
		
	}



	
	
}
