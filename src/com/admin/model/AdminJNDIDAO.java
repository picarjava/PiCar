package com.admin.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdminJNDIDAO implements AdminDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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
		
		private static final String UPDATEPSW =
				"UPDATE ADMIN SET PASSWORD =? WHERE ADMIN_ID=?";
		
		@Override
		public void insert(AdminVO adminVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, adminVO.getAdminName());
				pstmt.setString(2, adminVO.getEmail());
				pstmt.setString(3, adminVO.getPassword());
				pstmt.setInt(4, adminVO.getIsEmp());
				
				pstmt.executeUpdate();
				System.out.println("成功增加一筆資料");
				
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
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, adminVO.getAdminName());
				pstmt.setString(2, adminVO.getEmail());
				pstmt.setString(3, adminVO.getPassword());
				pstmt.setInt(4, adminVO.getIsEmp());
				pstmt.setString(5, adminVO.getAdminID());
				
				System.out.println("成功修改資料"+adminVO.getAdminID());
				
				pstmt.executeUpdate();

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
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, adminID);
				
				pstmt.executeUpdate();
				
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
				con = ds.getConnection();
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

				con = ds.getConnection();
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
				con = ds.getConnection();
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
		public void updatePSW(AdminVO adminVO) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATEPSW);
				
				pstmt.setString(1, adminVO.getPassword());
				pstmt.setString(2, adminVO.getAdminID());
				
				System.out.println("成功修改資料"+adminVO.getPassword());
				
				pstmt.executeUpdate();

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
		
	}
