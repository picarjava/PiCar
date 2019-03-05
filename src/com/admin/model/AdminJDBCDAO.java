package com.admin.model;

import java.sql.*;
import java.util.*;

public class AdminJDBCDAO implements AdminDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PICAR";
	String passwd = "PICAR";
	
	private static final String INSERT_STMT =
		"INSERT INTO ADMIN (ADMIN_ID, ADMIN_NAME, EMAIL, PASSWORD, IS_EMP) VALUES (('A'||LPAD(to_char(ADMIN_SEQ.NEXTVAL),3,'0')), ?,?,?,?)";
	private static final String GET_ALL_STMT =
		"SELECT ADMIN_ID, ADMIN_NAME, EMAIL, PASSWORD, IS_EMP FROM ADMIN ORDER BY ADMIN_ID";
	private static final String GET_ONE_STMT =
		"SELECT ADMIN_ID, ADMIN_NAME, EMAIL, PASSWORD, IS_EMP FROM ADMIN WHERE ADMIN_ID = ?";
	private static final String DELETE =
		"DELETE FROM ADMIN WHERE ADMIN_ID = ?";
	private static final String UPDATE =
		"UPDATE ADMIN SET ADMIN_NAME=?, EMAIL=?, PASSWORD =?, IS_EMP=? WHERE ADMIN_ID=?";
	
	private static final String LOGIN =
		"SELECT * FROM ADMIN WHERE ADMIN_ID=? AND PASSWORD=?";
	
	@Override
	public void insert(AdminVO adminVO) {

	Connection con = null;
	PreparedStatement pstmt = null;
	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, adminVO.getAdminName());
			pstmt.setString(2, adminVO.getEmail());
			pstmt.setString(3, adminVO.getPassword());
			pstmt.setInt(4, adminVO.getIsEmp());
			
			pstmt.executeUpdate();
			System.out.println("成功增加一筆資料");
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
			//清除JDBC資源
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				} 
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(AdminVO adminVO) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, adminVO.getAdminName());
			pstmt.setString(2, adminVO.getEmail());
			pstmt.setString(3, adminVO.getPassword());
			pstmt.setInt(4, adminVO.getIsEmp());
			pstmt.setString(5, adminVO.getAdminID());
			
			System.out.println("成功修改資料"+adminVO.getAdminID());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			//控制任何SQL的錯誤
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
			//清除JDBC資源
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				} 
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		
	}

	@Override
	public void delete(String adminID) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, adminID);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
			//清除JDBC資源
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				} 
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		
	} 

	@Override
	public AdminVO findByPrimaryKey(String adminID) {
		
		AdminVO adminVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, adminID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				adminVO = new AdminVO();
				adminVO.setAdminID(rs.getString("admin_Id"));
				adminVO.setAdminName(rs.getString("admin_Name"));
				adminVO.setEmail(rs.getString("email"));
				adminVO.setPassword(rs.getString("password"));
				adminVO.setIsEmp(rs.getInt("is_Emp"));
				
			}	
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
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
		return adminVO;
	}

	@Override
	public List<AdminVO> getAll() {
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminID(rs.getString("admin_Id"));
				adminVO.setAdminName(rs.getString("admin_Name"));
				adminVO.setEmail(rs.getString("email"));
				adminVO.setPassword(rs.getString("password"));
				adminVO.setIsEmp(rs.getInt("is_Emp"));
				list.add(adminVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());		
			// Handle any driver errors
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
	public AdminVO login(String adminID, String password) {
		
		AdminVO adminVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LOGIN);
			
			pstmt.setString(1, adminID);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				adminVO = new AdminVO();
				adminVO.setAdminID(rs.getString("admin_Id"));
				adminVO.setPassword(rs.getString("password"));
				adminVO.setAdminName(rs.getString("admin_Name"));
				adminVO.setEmail(rs.getString("email"));
				adminVO.setIsEmp(rs.getInt("is_Emp"));
				
			}	
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());	

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
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
		return adminVO;
	}
	
	
	public static void main(String[] args) {
		
		AdminJDBCDAO dao = new AdminJDBCDAO();
		
		// 新增
		AdminVO adminVO1 = new AdminVO();
		adminVO1.setAdminName("古阿明");
		adminVO1.setEmail("wwww123@gmail.com");
		adminVO1.setPassword("aaa2222");	
		adminVO1.setIsEmp(1);
		dao.insert(adminVO1);
		
		//修改
		AdminVO adminVO2 = new AdminVO();
		adminVO2.setAdminID("A002");
		adminVO2.setAdminName("Kevin");
		adminVO2.setEmail("qqq123@gmail.com");
		adminVO2.setPassword("9996666");
		adminVO2.setIsEmp(0);
		dao.update(adminVO2);
		
		//刪除    無法刪除!
//		dao.delete("A001");
//		System.out.println("成功刪除一筆資料");
		
		//單一查詢
		AdminVO adminVO3 = dao.findByPrimaryKey("A001");
		System.out.print(adminVO3.getAdminID() + ",");
		System.out.print(adminVO3.getAdminName() + ",");
		System.out.print(adminVO3.getEmail() + ",");
		System.out.print(adminVO3.getPassword() + ",");	
		System.out.println(adminVO3.getIsEmp());
		System.out.println("---------------------");	
		
		//查詢
		List<AdminVO> list = dao.getAll();		
		for (AdminVO aAdmin: list) {
			System.out.print(aAdmin.getAdminID() + ",");
			System.out.print(aAdmin.getAdminName() + ",");
			System.out.print(aAdmin.getEmail() + ",");
			System.out.print(aAdmin.getPassword() + ",");
			System.out.println(aAdmin.getIsEmp());
			System.out.println();
		}
		
		AdminVO adminVO4 = dao.login("A005","aaa2222");
		System.out.print(adminVO4.getAdminID() + ",");
		System.out.print(adminVO4.getPassword());
		
	}

}
