package com.productType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.data.database;

public class ProductTypeJDBCDAO implements ProductTypeDAOInterface{


	
	private final String INSERT = "INSERT INTO PRODUCTTYPE (PT_ID,PT_PLATFORM,PT_KIND) VALUES ('PT' || LPAD(PRODUCTTYPE_SEQ.NEXTVAL, 5, 0), ?, ?)";
	private final String UPDATE = "UPDATE PRODUCTTYPE SET PT_PLATFORM=?, PT_KIND=? WHERE PT_ID=?";
	private final String DELETE = "DELETE FROM PRODUCTTYPE WHERE PT_ID=?";
	private final String GETONE = "SELECT * FROM PRODUCTTYPE WHERE PT_ID=?";
	private final String GETALL = "SELECT * FROM PRODUCTTYPE ORDER BY PT_ID";
	
	
	public static void main(String[] args) {
		ProductTypeDAOInterface pt = new ProductTypeJDBCDAO();
//		// 新增
//		ProductTypeVO ptvoInsert = new ProductTypeVO();
//		ptvoInsert.setPt_platform("PlaaayStationn");
//		ptvoInsert.setPt_kind("sdddaawh");
//		pt.insert(ptvoInsert);
		
		// 更新
//		ProductTypeVO ptvoUpdate = new ProductTypeVO();
//		ptvoUpdate.setPt_id("PT00012");
//		ptvoUpdate.setPt_platform("PlayStationn");
//		ptvoUpdate.setPt_kind("主機");
//		pt.update(ptvoUpdate);
		
		// 刪除
//		pt.delete("PT00021");
		
		
//		// 取一
//		ProductTypeVO ptvoGetOne = pt.findByPrimaryKey("PT00011");
//		String id = ptvoGetOne.getPt_id();
//		String platform = ptvoGetOne.getPt_platform();
//		String kind = ptvoGetOne.getPt_kind();
//		System.out.println("id:" + id);
//		System.out.println("platform:" + platform);
//		System.out.println("kind:" + kind);
		
		// 取全部
		List<ProductTypeVO> list = pt.getAll();
		for(ProductTypeVO ptVO : list) {
			String id = ptVO.getPt_id();
			String platform = ptVO.getPt_platform();
			String kind = ptVO.getPt_kind();
			System.out.println("id:" + id);
			System.out.println("platform:" + platform);
			System.out.println("kind:" + kind);
			System.out.println("===============================");
		}
		
	}
	
	@Override
	public void insert(ProductTypeVO productType) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, productType.getPt_platform());
			ps.setString(2, productType.getPt_kind());
			
			int line = ps.executeUpdate();
			System.out.println(line);
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
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
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(UPDATE);
			
			ps.setString(1, productType.getPt_platform());
			ps.setString(2, productType.getPt_kind());
			ps.setString(3, productType.getPt_id());
			
			int w = ps.executeUpdate();
			System.out.println(w);
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
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
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(DELETE);
			
			ps.setString(1, p_id);
			
			ps.executeUpdate();
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
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
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(GETONE);
			ps.setString(1, p_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				productType = new ProductTypeVO();
				productType.setPt_id(rs.getString("pt_id"));
				productType.setPt_platform(rs.getString("pt_platform"));
				productType.setPt_kind(rs.getString("pt_kind"));
			}
			
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
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
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(GETALL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductTypeVO ptVO = new ProductTypeVO();
				ptVO.setPt_id(rs.getString("pt_id"));
				ptVO.setPt_platform(rs.getString("pt_platform"));
				ptVO.setPt_kind(rs.getString("pt_kind"));
				listptVO.add(ptVO);
			}
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
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
