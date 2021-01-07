package com.productType.model;

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

public class ProductTypeJDBCDAO implements ProductTypeDAOInterface{

	private static DataSource ds = null;
	
	static {
		Context context;
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/TEA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private final String INSERT = "INSERT INTO PRODUCTTYPE (PT_ID,PT_PLATFORM,PT_KIND) VALUES ('PT' || LPAD(PRODUCTTYPE_SEQ.NEXTVAL, 5, 0), ?, ?)";
	private final String UPDATE = "UPDATE PRODUCTTYPE SET PT_PLATFORM=?, PT_KIND=? WHERE PT_ID=?";
	private final String DELETE = "DELETE FROM PRODUCTTYPE WHERE PT_ID=?";
	private final String GETONE = "SELECT * FROM PRODUCTTYPE WHERE PT_ID=?";
	private final String GETALL = "SELECT * FROM PRODUCTTYPE ORDER BY PT_ID";
	
	@Override
	public void insert(ProductTypeVO productType) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, productType.getPt_platform());
			ps.setString(2, productType.getPt_kind());
			
			int line = ps.executeUpdate();
			System.out.println(line);
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con != null) {
				con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void update(ProductTypeVO productType) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);
			
			ps.setString(1, productType.getPt_platform());
			ps.setString(2, productType.getPt_kind());
			ps.setString(3, productType.getPt_id());
			
			int w = ps.executeUpdate();
			System.out.println(w);
			
		} catch(SQLException e) {
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
			
			
		} catch(SQLException e) {
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
	public ProductTypeVO findByPrimaryKey(String p_id) {
		ProductTypeVO productType = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETONE);
			ps.setString(1, p_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				productType = new ProductTypeVO();
				productType.setPt_id(rs.getString("pt_id"));
				productType.setPt_platform(rs.getString("pt_platform"));
				productType.setPt_kind(rs.getString("pt_kind"));
			}
			
			
			
		} catch(SQLException e) {
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
		
		return productType;
	}

	@Override
	public List<ProductTypeVO> getAll() {
		List<ProductTypeVO> listptVO = new LinkedList<ProductTypeVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductTypeVO ptVO = new ProductTypeVO();
				ptVO.setPt_id(rs.getString("pt_id"));
				ptVO.setPt_platform(rs.getString("pt_platform"));
				ptVO.setPt_kind(rs.getString("pt_kind"));
				listptVO.add(ptVO);
			}
			
			
		} catch(SQLException e) {
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
		
		
		return listptVO;
	}


}
