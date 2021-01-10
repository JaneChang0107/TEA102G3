package com.employee.model;

import java.util.*;
import java.sql.*;

public class EmployeeJDBCDAO implements EmployeeDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G3";
	String passwd = "102G3";

	private static final String INSERT_STMT = 
		"INSERT INTO employee (e_id,e_password,e_identity,e_name,e_gender,e_birth,e_email,e_phone,e_address,e_title,e_status,st_id) VALUES ('E' || LPAD(EMPLOYEE_SEQ.NEXTVAL,5,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT e_id,e_password,e_identity,e_name,e_gender,to_char(e_birth,'yyyy-mm-dd') e_birth,e_email,e_phone,e_address,e_title,e_status,st_id FROM employee order by e_id";
	private static final String GET_ONE_STMT = 
		"SELECT e_id,e_password,e_identity,e_name,e_gender,to_char(e_birth,'yyyy-mm-dd') e_birth,e_email,e_phone,e_address,e_title,e_status,st_id FROM employee where e_id = ?";
	private static final String GET_ONE_STMT_ENAME = 
		"SELECT e_id,e_password,e_identity,e_name,e_gender,to_char(e_birth,'yyyy-mm-dd') e_birth,e_email,e_phone,e_address,e_title,e_status,st_id FROM employee where e_name like ?";
	private static final String DELETE = 
		"DELETE FROM employee where e_id = ?";
	private static final String UPDATE = 
		"UPDATE employee set e_password=?, e_identity=?, e_name=?, e_gender=?, e_birth=?, e_email=?, e_phone=?, e_address=?, e_title=?, e_status=?, st_id=? where e_id = ?";
	private static final String UPDATE_WITHOUT = 
		"UPDATE employee set e_identity=?, e_name=?, e_gender=?, e_birth=?, e_email=?, e_phone=?, e_address=?, e_title=?, st_id=? where e_id = ?";
	private static final String UPDATE_PWD = 
		"UPDATE employee set e_password=? where e_id = ?";
	private static final String UPDATE_STATUS = 
			"UPDATE employee set e_status=? where e_id = ?";

	@Override
	public void insert(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, employeeVO.getE_password());
			pstmt.setString(2, employeeVO.getE_identity());
			pstmt.setString(3, employeeVO.getE_name());
			pstmt.setString(4, employeeVO.getE_gender());
			pstmt.setDate(5, employeeVO.getE_birth());
			pstmt.setString(6, employeeVO.getE_email());
			pstmt.setString(7, employeeVO.getE_phone());
			pstmt.setString(8, employeeVO.getE_address());
			pstmt.setString(9, employeeVO.getE_title());
			pstmt.setInt(10, employeeVO.getE_status());
			pstmt.setString(11, employeeVO.getSt_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	/*****************更新全部*****************/
	@Override
	public void update(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, employeeVO.getE_password());
			pstmt.setString(2, employeeVO.getE_identity());
			pstmt.setString(3, employeeVO.getE_name());
			pstmt.setString(4, employeeVO.getE_gender());
			pstmt.setDate(5, employeeVO.getE_birth());
			pstmt.setString(6, employeeVO.getE_email());
			pstmt.setString(7, employeeVO.getE_phone());
			pstmt.setString(8, employeeVO.getE_address());
			pstmt.setString(9, employeeVO.getE_title());
			pstmt.setInt(10, employeeVO.getE_status());
			pstmt.setString(11, employeeVO.getSt_id());
			pstmt.setString(12, employeeVO.getE_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	/*****************更新除了密碼和狀態*****************/
	@Override
	public void update_without(EmployeeVO employeeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_WITHOUT);
			
			
			
			pstmt.setString(1, employeeVO.getE_identity());
			pstmt.setString(2, employeeVO.getE_name());
			pstmt.setString(3, employeeVO.getE_gender());
			pstmt.setDate(4, employeeVO.getE_birth());
			pstmt.setString(5, employeeVO.getE_email());
			pstmt.setString(6, employeeVO.getE_phone());
			pstmt.setString(7, employeeVO.getE_address());
			pstmt.setString(8, employeeVO.getE_title());
			pstmt.setString(9, employeeVO.getSt_id());
			pstmt.setString(10, employeeVO.getE_id());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	/*****************更新密碼*****************/
	@Override
	public void update_pwd(EmployeeVO employeeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_PWD);
			
			
			
			pstmt.setString(1, employeeVO.getE_password());
			pstmt.setString(2, employeeVO.getE_id());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	/*****************更新狀態*****************/
	@Override
	public void update_status(EmployeeVO employeeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
			
			
			pstmt.setInt(1, employeeVO.getE_status());
			pstmt.setString(2, employeeVO.getE_id());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String e_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, e_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public EmployeeVO findByPrimaryKey(String e_id) {

		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, e_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				employeeVO = new EmployeeVO();
				employeeVO.setE_id(rs.getString("e_id"));
				employeeVO.setE_password(rs.getString("e_password"));
				employeeVO.setE_identity(rs.getString("e_identity"));
				employeeVO.setE_name(rs.getString("e_name"));
				employeeVO.setE_gender(rs.getString("e_gender"));
				employeeVO.setE_birth(rs.getDate("e_birth"));
				employeeVO.setE_email(rs.getString("e_email"));
				employeeVO.setE_phone(rs.getString("e_phone"));
				employeeVO.setE_address(rs.getString("e_address"));
				employeeVO.setE_title(rs.getString("e_title"));
				employeeVO.setE_status(rs.getInt("e_status"));
				employeeVO.setSt_id(rs.getString("st_id"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return employeeVO;
	}
	
//查單個with員工姓名
	@Override
	public List<EmployeeVO> findByPrimaryKey_e_name(String e_name) {
		
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmployeeVO> list = new LinkedList<EmployeeVO>();
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_ENAME);			
			pstmt.setString(1, "%" + e_name + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				employeeVO = new EmployeeVO();
				employeeVO.setE_id(rs.getString("e_id"));
				employeeVO.setE_password(rs.getString("e_password"));
				employeeVO.setE_identity(rs.getString("e_identity"));
				employeeVO.setE_name(rs.getString("e_name"));
				employeeVO.setE_gender(rs.getString("e_gender"));
				employeeVO.setE_birth(rs.getDate("e_birth"));
				employeeVO.setE_email(rs.getString("e_email"));
				employeeVO.setE_phone(rs.getString("e_phone"));
				employeeVO.setE_address(rs.getString("e_address"));
				employeeVO.setE_title(rs.getString("e_title"));
				employeeVO.setE_status(rs.getInt("e_status"));
				employeeVO.setSt_id(rs.getString("st_id"));
				
				list.add(employeeVO);
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;

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
				employeeVO = new EmployeeVO();
				employeeVO.setE_id(rs.getString("E_id"));
				employeeVO.setE_password(rs.getString("E_password"));
				employeeVO.setE_identity(rs.getString("E_identity"));
				employeeVO.setE_name(rs.getString("E_name"));
				employeeVO.setE_gender(rs.getString("E_gender"));
				employeeVO.setE_birth(rs.getDate("E_birth"));
				employeeVO.setE_email(rs.getString("E_email"));
				employeeVO.setE_phone(rs.getString("E_phone"));
				employeeVO.setE_address(rs.getString("E_address"));
				employeeVO.setE_title(rs.getString("E_title"));
				employeeVO.setE_status(rs.getInt("E_status"));
				employeeVO.setSt_id(rs.getString("St_id")); // Store the row in the list				
				list.add(employeeVO); 
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public EmployeeVO getEmployeePwd(String e_id) {

		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, e_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				employeeVO = new EmployeeVO();
				employeeVO.setE_id(rs.getString("e_id"));
				employeeVO.setE_password(rs.getString("e_password"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return employeeVO;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();

//		// 新增
//		EmployeeVO employeeVO1 = new EmployeeVO(); 
//		employeeVO1.setE_password("123456");
//		employeeVO1.setE_identity("A123456789");
//		employeeVO1.setE_name("1");
//		employeeVO1.setE_gender("men");
//		employeeVO1.setE_birth(java.sql.Date.valueOf("2020-12-02"));
//		employeeVO1.setE_email("123");
//		employeeVO1.setE_phone("123");
//		employeeVO1.setE_address("123");
//		employeeVO1.setE_title("123");
//		employeeVO1.setE_status(123);
//		employeeVO1.setSt_id("ST00001");
//		dao.insert(employeeVO1);
//
//		// 修改
//		EmployeeVO employeeVO2 = new EmployeeVO(); 
//		employeeVO2.setE_id("E00001");
//		employeeVO2.setE_password("123456");
//		employeeVO2.setE_identity("A123456789");
//		employeeVO2.setE_name("1");
//		employeeVO2.setE_gender("men");
//		employeeVO2.setE_birth(java.sql.Date.valueOf("2020-12-12"));
//		employeeVO2.setE_email("123");
//		employeeVO2.setE_phone("123");
//		employeeVO2.setE_address("123");
//		employeeVO2.setE_title("123");
//		employeeVO2.setE_status(456);
//		employeeVO2.setSt_id("ST00001");
//		dao.update(employeeVO2);
//		System.out.println("更新成功");
//
//		// 更新部分
//		EmployeeVO employeeVO2 = new EmployeeVO(); 
//		employeeVO2.setE_id("E00001");
//		employeeVO2.setE_identity("A123456789");
//		employeeVO2.setE_name("1");
//		employeeVO2.setE_gender("men");
//		employeeVO2.setE_birth(java.sql.Date.valueOf("2020-12-12"));
//		employeeVO2.setE_email("123");
//		employeeVO2.setE_phone("123");
//		employeeVO2.setE_address("123");
//		employeeVO2.setE_title("123");
//		employeeVO2.setSt_id("ST00001");
//		dao.update_without(employeeVO2);
//		System.out.println("更新成功");
//
//		// 更新密碼
//		EmployeeVO employeeVO2 = new EmployeeVO(); 
//		employeeVO2.setE_id("E00001");
//		employeeVO2.setE_password("123456");
//		dao.update_pwd(employeeVO2);
//		System.out.println("更新成功");
//
//		// 刪除
//		dao.delete("E00002");
//
//		// 查詢
//		EmployeeVO employeeVO3 = dao.findByPrimaryKey("E00001");
//		System.out.print(employeeVO3.getE_id() + ",");
//		System.out.print(employeeVO3.getE_password() + ",");
//		System.out.print(employeeVO3.getE_identity() + ",");
//		System.out.print(employeeVO3.getE_name() + ",");
//		System.out.print(employeeVO3.getE_gender() + ",");
//		System.out.print(employeeVO3.getE_birth() + ",");
//		System.out.print(employeeVO3.getE_email() + ",");
//		System.out.print(employeeVO3.getE_phone() + ",");
//		System.out.print(employeeVO3.getE_address() + ",");
//		System.out.print(employeeVO3.getE_title() + ",");
//		System.out.print(employeeVO3.getE_status() + ",");
//		System.out.println(employeeVO3.getSt_id());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<EmployeeVO> list = dao.getAll();
//		for (EmployeeVO aEmp : list) {
//			System.out.print(aEmp.getE_id() + ",");
//			System.out.print(aEmp.getE_password() + ",");
//			System.out.print(aEmp.getE_identity() + ",");
//			System.out.print(aEmp.getE_name() + ",");
//			System.out.print(aEmp.getE_gender() + ",");
//			System.out.print(aEmp.getE_birth() + ",");
//			System.out.print(aEmp.getE_email() + ",");
//			System.out.print(aEmp.getE_phone() + ",");
//			System.out.print(aEmp.getE_address() + ",");
//			System.out.print(aEmp.getE_title() + ",");
//			System.out.print(aEmp.getE_status() + ",");
//			System.out.println(aEmp.getSt_id());
//			System.out.println();
//		}
//		
//		// 查詢名字
//		List<EmployeeVO> list = dao.findByPrimaryKey_e_name("LI");
//		for (EmployeeVO aEmp : list) {
//			System.out.print(aEmp.getE_id() + ",");
//			System.out.print(aEmp.getE_password() + ",");
//			System.out.print(aEmp.getE_identity() + ",");
//			System.out.print(aEmp.getE_name() + ",");
//			System.out.print(aEmp.getE_gender() + ",");
//			System.out.print(aEmp.getE_birth() + ",");
//			System.out.print(aEmp.getE_email() + ",");
//			System.out.print(aEmp.getE_phone() + ",");
//			System.out.print(aEmp.getE_address() + ",");
//			System.out.print(aEmp.getE_title() + ",");
//			System.out.print(aEmp.getE_status() + ",");
//			System.out.println(aEmp.getSt_id());
//			System.out.println();
//		}
	}
}
