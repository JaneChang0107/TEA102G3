package com.productPicture.model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.data.database;

public class ProductPictureJNDIDAO implements ProductPictureDAOInterface{

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

	
	private final String INSERT = "INSERT INTO ProductPicture (PP_ID,PP_PICTURE,P_ID) VALUES ('PP' || LPAD(ProductPicture_SEQ.NEXTVAL, 5, 0), ?, ?)";
	private final String UPDATE = "UPDATE ProductPicture SET PP_PICTURE=? WHERE PP_ID=?";
	private final String DELETE = "DELETE FROM ProductPicture WHERE PP_ID=?";
	private final String DELETEPRODUCT = "DELETE FROM ProductPicture WHERE P_ID=?";
	private final String GETONE = "SELECT * FROM ProductPicture WHERE PP_ID=?";
	private final String GETPP = "SELECT * FROM ProductPicture WHERE P_ID=? ORDER BY PP_ID";
	private final String GETALL = "SELECT * FROM ProductPicture ORDER BY PP_ID";
	
	@Override
	public void insert(ProductPictureVO productPicture) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			
			ps.setBytes(1, productPicture.getPp_picture());
			ps.setString(2, productPicture.getP_id());
			
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
	public void update(ProductPictureVO productPicture) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);
			
			ps.setBytes(1, productPicture.getPp_picture());	
			ps.setString(2, productPicture.getPp_id());
			
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
	public void delete(String pp_id) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);
			
			ps.setString(1, pp_id);
			
			int d = ps.executeUpdate();
			
			System.out.println(d);
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
	public void deleteByProduct(String p_id) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETEPRODUCT);

			ps.setString(1, p_id);

			int d = ps.executeUpdate();

			System.out.println(d);
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
	public ProductPictureVO findByPrimaryKey(String pp_id) {
		ProductPictureVO productPicture = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETONE);
			ps.setString(1, pp_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				productPicture = new ProductPictureVO();
				productPicture.setPp_id(rs.getString("pp_id"));
				productPicture.setPp_picture(rs.getBytes("pp_picture"));
				productPicture.setP_id(rs.getString("p_id"));
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
		
		return productPicture;
	}

	@Override
	public List<ProductPictureVO> findByProduct (String p_id) {
		ProductPictureVO productPicture = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductPictureVO> list = new LinkedList<ProductPictureVO>();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETPP);
			ps.setString(1, p_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				productPicture = new ProductPictureVO();
				productPicture.setPp_id(rs.getString("pp_id"));
				productPicture.setPp_picture(rs.getBytes("pp_picture"));
				productPicture.setP_id(rs.getString("p_id"));
				list.add(productPicture);
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
		
		return list;
	}
	
	@Override
	public List<ProductPictureVO> getAll() {
		List<ProductPictureVO> listppVO = new LinkedList<ProductPictureVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductPictureVO ppVO = new ProductPictureVO();
				ppVO.setPp_id(rs.getString("pp_id"));
				ppVO.setPp_picture(rs.getBytes("pp_picture"));
				ppVO.setP_id(rs.getString("p_id"));
				listppVO.add(ppVO);
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
		return listppVO;
	}


}
