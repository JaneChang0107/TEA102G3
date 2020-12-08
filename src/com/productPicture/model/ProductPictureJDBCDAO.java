package com.productPicture.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.data.database;

public class ProductPictureJDBCDAO implements ProductPictureDAOInterface{


	
	private final String INSERT = "INSERT INTO ProductPicture (PP_ID,PP_PICTURE,P_ID) VALUES ('PP' || LPAD(ProductPicture_SEQ.NEXTVAL, 5, 0), ?, ?)";
	private final String UPDATE = "UPDATE ProductPicture SET PP_PICTURE=? WHERE PP_ID=?";
	private final String DELETE = "DELETE FROM ProductPicture WHERE PP_ID=?";
	private final String GETONE = "SELECT * FROM ProductPicture WHERE PP_ID=?";
	private final String GETALL = "SELECT * FROM ProductPicture ORDER BY PP_ID";
	
	
	public static void main(String[] args) {
		ProductPictureDAOInterface pt = new ProductPictureJDBCDAO();
//		// 新增
		byte[] b = null;
//		
//		ProductPictureVO ppVOInsert = new ProductPictureVO();
//		
//		try {
//			File f = new File("I:\\Users\\user\\Google 雲端硬碟\\Pictures\\0a85978e19fcb6c8319c52ca9434fa8f.JPG");
//			FileInputStream fis = new FileInputStream(f);
//			b = new byte[fis.available()];
//			fis.read(b);
//			ppVOInsert.setPp_picture(b);
//			ppVOInsert.setP_id("P00013");
//			pt.insert(ppVOInsert);
//			fis.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		
		
		// 更新
//		ProductPictureVO ppVOUpdate = new ProductPictureVO();
//		try {
//			File f = new File("I:\\Users\\user\\Google 雲端硬碟\\Pictures\\301e58afcaec5a391ba659c8c1275382.gif");
//			FileInputStream fis = new FileInputStream(f);
//			b = new byte[fis.available()];
//			fis.read(b);
//			ppVOUpdate.setPp_picture(b);
//			ppVOUpdate.setPp_id("PP00008");
//			pt.update(ppVOUpdate);
//			fis.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		// 刪除
//		pt.delete("PP00003");
		
		
//		// 取一
//		ProductPictureVO ppVOGetOne = pt.findByPrimaryKey("PP00008");
//		String id = ppVOGetOne.getPp_id();
//		String pid = ppVOGetOne.getP_id();
//		File f = null;
//		try {
//			f  = new File("xxx.gif");
//			FileOutputStream fos = new FileOutputStream(f);
//			
//			byte[] img = ppVOGetOne.getPp_picture();
//			fos.write(img);
//			fos.close();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("id: " + id);
//		System.out.println("where is img: " + f.getAbsolutePath());
//		System.out.println("kind: " + pid);
		
//		// 取全部
//		List<ProductPictureVO> list = pt.getAll();
//		for(ProductPictureVO ppVO : list) {
//			String id = ppVO.getPp_id();
////			byte[] img = ppVO.getPp_picture();
//			String pid = ppVO.getP_id();
//			System.out.println("id:" + id);
//			System.out.println("pid:" + pid);
//			System.out.println("===============================");
//		}
		
	}
	
	@Override
	public void insert(ProductPictureVO productPicture) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(INSERT);
			
			ps.setBytes(1, productPicture.getPp_picture());
			ps.setString(2, productPicture.getP_id());
			
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
	public void update(ProductPictureVO productPicture) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(UPDATE);
			
			ps.setBytes(1, productPicture.getPp_picture());	
			ps.setString(2, productPicture.getPp_id());
			
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
	public void delete(String pp_id) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(DELETE);
			
			ps.setString(1, pp_id);
			
			int d = ps.executeUpdate();
			
			System.out.println(d);
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
	public ProductPictureVO findByPrimaryKey(String pp_id) {
		ProductPictureVO productPicture = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(GETONE);
			ps.setString(1, pp_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				productPicture = new ProductPictureVO();
				productPicture.setPp_id(rs.getString("pp_id"));
				productPicture.setPp_picture(rs.getBytes("pp_picture"));
				productPicture.setP_id(rs.getString("p_id"));
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
		
		return productPicture;
	}

	@Override
	public List<ProductPictureVO> getAll() {
		List<ProductPictureVO> listppVO = new LinkedList<ProductPictureVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(database.DRIVER);
			con = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
			ps = con.prepareStatement(GETALL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductPictureVO ppVO = new ProductPictureVO();
				ppVO.setPp_id(rs.getString("pp_id"));
				ppVO.setPp_picture(rs.getBytes("pp_picture"));
				ppVO.setP_id(rs.getString("p_id"));
				listppVO.add(ppVO);
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
		
		
		return listppVO;
	}


}
