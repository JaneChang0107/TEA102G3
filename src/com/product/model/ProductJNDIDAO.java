package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductJNDIDAO implements ProductInterface{
	
	private static DataSource ds = null;
	
	static {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/TEA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private final String INSERT = 
			"INSERT INTO PRODUCT (P_ID, P_NAME, P_PRICE, P_DETAIL, PT_ID, P_COUNT, P_ADDDATE, P_STATUS, M_ID) VALUES ('P' || LPAD(PRODUCT_SEQ.NEXTVAL, 5, 0), ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String UPDATE = 
			"UPDATE PRODUCT SET P_NAME = ?, P_PRICE = ?, P_DETAIL = ?, PT_ID = ?, P_COUNT = ?, P_REVISEDATE = ?, P_STATUS = ? WHERE P_ID = ?";
	private final String CHECKED = 
			"UPDATE PRODUCT SET P_STATUS = ? WHERE P_ID = ?";
	private final String SELLOUT = 
			"UPDATE PRODUCT SET P_STATUS = 0, P_COUNT = 0 WHERE P_ID = ?";
	private final String DELETE =
			"DELETE FROM PRODUCT WHERE P_ID = ?";
	private final String SELLERDELETE =
			"UPDATE PRODUCT SET P_STATUS = 50 WHERE P_ID = ?";
	private final String FINDONE =
			"SELECT * FROM PRODUCT WHERE P_ID = ?";
	private final String FINDBYSELLER =
			"SELECT * FROM PRODUCT WHERE M_ID = ? AND P_STATUS <> 50 ORDER BY P_ID DESC";
	private final String FINDBYPNAME =
			"SELECT * FROM PRODUCT WHERE P_NAME like ? AND P_STATUS = 1 ORDER BY P_ID DESC";
	private final String FINDBYPTYPE =
			"SELECT * FROM PRODUCT WHERE PT_ID = ? AND P_STATUS = 1 ORDER BY P_ID DESC";
	private final String FINDBYPNAMETPYE =
			"SELECT * FROM PRODUCT WHERE P_NAME like ? AND PT_ID = ? AND P_STATUS = 1 ORDER BY P_ID DESC";
	private final String FINDBYPSTATUS =
			"SELECT * FROM PRODUCT WHERE P_STATUS= ? ORDER BY P_ID";
	private final String GETALLSELL =
			"SELECT * FROM PRODUCT WHERE P_STATUS = 1 ORDER BY P_ID DESC";
	private final String GETALL =
			"SELECT * FROM PRODUCT ORDER BY P_ID DESC";
	
	@Override
	public String insert(ProductVO product) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			String[] getpid = {"P_ID"};
			ps = con.prepareStatement(INSERT, getpid);
			
			ps.setString(1, product.getP_name());
			ps.setInt(2, product.getP_price());
			ps.setString(3, product.getP_detail());
			ps.setString(4, product.getPt_id());
			ps.setInt(5, product.getP_count());
			ps.setTimestamp(6, product.getP_addDate());
			ps.setInt(7, product.getP_status());
			ps.setString(8, product.getM_id());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			String pid = null;
			while(rs.next()) {
				pid = rs.getString(1);
			}
			return pid;
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(ProductVO product) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);
			
			ps.setString(1, product.getP_name());
			ps.setInt(2, product.getP_price());
			ps.setString(3, product.getP_detail());
			ps.setString(4, product.getPt_id());
			ps.setInt(5, product.getP_count());
			ps.setTimestamp(6, product.getP_reviseDate());
			ps.setInt(7, product.getP_status());
			ps.setString(8, product.getP_id());
			
			ps.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void checked(String p_id, Integer p_status) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(CHECKED);

			ps.setInt(1, p_status);
			ps.setString(2, p_id);
			
			ps.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void sellout(String p_id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SELLOUT);

			ps.setString(1, p_id);
			
			ps.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(String p_id) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);
			
			ps.setString(1, p_id);
			
			ps.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	@Override
	public void sellerDelete(String p_id) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SELLERDELETE);
			
			ps.setString(1, p_id);
			
			ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public ProductVO findOneProduct (String p_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductVO pVO = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FINDONE);
			ps.setString(1, p_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				pVO = new ProductVO();
				pVO.setP_id(rs.getString("p_id"));
				pVO.setP_name(rs.getString("p_name"));
				pVO.setP_price(rs.getInt("p_price"));
				pVO.setP_detail(rs.getString("p_detail"));
				pVO.setPt_id(rs.getString("pt_id"));
				pVO.setP_count(rs.getInt("p_count"));
				pVO.setP_addDate(rs.getTimestamp("p_addDate"));
				pVO.setP_reviseDate(rs.getTimestamp("p_reviseDate"));
				pVO.setP_status(rs.getInt("p_status"));
				pVO.setM_id(rs.getString("m_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return pVO;
	}

	@Override
	public List<ProductVO> findBySeller(String m_id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductVO> list = new LinkedList<ProductVO>();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FINDBYSELLER);
			ps.setString(1, m_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductVO pVO = new ProductVO();
				pVO.setP_id(rs.getString("p_id"));
				pVO.setP_name(rs.getString("p_name"));
				pVO.setP_price(rs.getInt("p_price"));
				pVO.setP_detail(rs.getString("p_detail"));
				pVO.setPt_id(rs.getString("pt_id"));
				pVO.setP_count(rs.getInt("p_count"));
				pVO.setP_addDate(rs.getTimestamp("p_addDate"));
				pVO.setP_reviseDate(rs.getTimestamp("p_reviseDate"));
				pVO.setP_status(rs.getInt("p_status"));
				pVO.setM_id(rs.getString("m_id"));
				
				list.add(pVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public List<ProductVO> findByProductName(String p_name) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductVO> list = new LinkedList<ProductVO>();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FINDBYPNAME);
			ps.setString(1, "%" + p_name + "%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductVO pVO = new ProductVO();
				pVO.setP_id(rs.getString("p_id"));
				pVO.setP_name(rs.getString("p_name"));
				pVO.setP_price(rs.getInt("p_price"));
				pVO.setP_detail(rs.getString("p_detail"));
				pVO.setPt_id(rs.getString("pt_id"));
				pVO.setP_count(rs.getInt("p_count"));
				pVO.setP_addDate(rs.getTimestamp("p_addDate"));
				pVO.setP_reviseDate(rs.getTimestamp("p_reviseDate"));
				pVO.setP_status(rs.getInt("p_status"));
				pVO.setM_id(rs.getString("m_id"));
				
				list.add(pVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<ProductVO> findByProductType(String pt_id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductVO> list = new LinkedList<ProductVO>();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FINDBYPTYPE);
			ps.setString(1, pt_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductVO pVO = new ProductVO();
				pVO.setP_id(rs.getString("p_id"));
				pVO.setP_name(rs.getString("p_name"));
				pVO.setP_price(rs.getInt("p_price"));
				pVO.setP_detail(rs.getString("p_detail"));
				pVO.setPt_id(rs.getString("pt_id"));
				pVO.setP_count(rs.getInt("p_count"));
				pVO.setP_addDate(rs.getTimestamp("p_addDate"));
				pVO.setP_reviseDate(rs.getTimestamp("p_reviseDate"));
				pVO.setP_status(rs.getInt("p_status"));
				pVO.setM_id(rs.getString("m_id"));
				
				list.add(pVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public List<ProductVO> findByProductName(String p_name, String pt_id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductVO> list = new LinkedList<ProductVO>();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FINDBYPNAMETPYE);
			
			ps.setString(1, "%" + p_name + "%");
			ps.setString(2, pt_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductVO pVO = new ProductVO();
				pVO.setP_id(rs.getString("p_id"));
				pVO.setP_name(rs.getString("p_name"));
				pVO.setP_price(rs.getInt("p_price"));
				pVO.setP_detail(rs.getString("p_detail"));
				pVO.setPt_id(rs.getString("pt_id"));
				pVO.setP_count(rs.getInt("p_count"));
				pVO.setP_addDate(rs.getTimestamp("p_addDate"));
				pVO.setP_reviseDate(rs.getTimestamp("p_reviseDate"));
				pVO.setP_status(rs.getInt("p_status"));
				pVO.setM_id(rs.getString("m_id"));
				
				list.add(pVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<ProductVO> findByStatus(Integer p_status) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductVO> list = new LinkedList<ProductVO>();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FINDBYPSTATUS);
			
			ps.setInt(1, p_status);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductVO pVO = new ProductVO();
				pVO.setP_id(rs.getString("p_id"));
				pVO.setP_name(rs.getString("p_name"));
				pVO.setP_price(rs.getInt("p_price"));
				pVO.setP_detail(rs.getString("p_detail"));
				pVO.setPt_id(rs.getString("pt_id"));
				pVO.setP_count(rs.getInt("p_count"));
				pVO.setP_addDate(rs.getTimestamp("p_addDate"));
				pVO.setP_reviseDate(rs.getTimestamp("p_reviseDate"));
				pVO.setP_status(rs.getInt("p_status"));
				pVO.setM_id(rs.getString("m_id"));
				
				list.add(pVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<ProductVO> getAllSell() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductVO> list = new LinkedList<ProductVO>();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALLSELL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductVO pVO = new ProductVO();
				pVO.setP_id(rs.getString("p_id"));
				pVO.setP_name(rs.getString("p_name"));
				pVO.setP_price(rs.getInt("p_price"));
				pVO.setP_detail(rs.getString("p_detail"));
				pVO.setPt_id(rs.getString("pt_id"));
				pVO.setP_count(rs.getInt("p_count"));
				pVO.setP_addDate(rs.getTimestamp("p_addDate"));
				pVO.setP_reviseDate(rs.getTimestamp("p_reviseDate"));
				pVO.setP_status(rs.getInt("p_status"));
				pVO.setM_id(rs.getString("m_id"));
				
				list.add(pVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public List<ProductVO> getAll() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductVO> list = new LinkedList<ProductVO>();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductVO pVO = new ProductVO();
				pVO.setP_id(rs.getString("p_id"));
				pVO.setP_name(rs.getString("p_name"));
				pVO.setP_price(rs.getInt("p_price"));
				pVO.setP_detail(rs.getString("p_detail"));
				pVO.setPt_id(rs.getString("pt_id"));
				pVO.setP_count(rs.getInt("p_count"));
				pVO.setP_addDate(rs.getTimestamp("p_addDate"));
				pVO.setP_reviseDate(rs.getTimestamp("p_reviseDate"));
				pVO.setP_status(rs.getInt("p_status"));
				pVO.setM_id(rs.getString("m_id"));
				
				list.add(pVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	


}
