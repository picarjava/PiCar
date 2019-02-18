package com.groupReport.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GroupReportDAO implements GroupReportDAO_interface {

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
		"INSERT INTO GROUP_REPORT(GREPORT_ID, MEM_ID, GROUP_ID, ADMIN_ID, STATE) VALUES (('GR'||LPAD(to_char(GREPORT_ID_SEQ.NEXTVAL),3,'0')), ?,?,?,?)";
	private static final String GET_ALL_STMT =
		"SELECT GREPORT_ID, MEM_ID, GROUP_ID, ADMIN_ID, STATE FROM GROUP_REPORT ORDER BY GREPORT_ID";
	private static final String GET_ONE_STMT =
		"SELECT GREPORT_ID, MEM_ID, GROUP_ID, ADMIN_ID, STATE FROM GROUP_REPORT WHERE GREPORT_ID = ?";
	private static final String DELETE =
		"DELETE FROM GROUP_REPORT WHERE GREPORT_ID = ?";
	private static final String UPDATE =
		"UPDATE GROUP_REPORT SET MEM_ID=?, GROUP_ID =?, ADMIN_ID=?, STATE=? WHERE GREPORT_ID=?";
	
	
	
	@Override
	public void insert(GroupReportVO groupReportVO) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
				
//			pstmt.setString(1, groupReportVO.getGreport_Id()); 此為自增主鍵
			pstmt.setString(1, groupReportVO.getMemID());
			pstmt.setString(2, groupReportVO.getGroupID());		
			pstmt.setString(3, groupReportVO.getAdminID());
			pstmt.setInt(4, groupReportVO.getState());
				
			pstmt.executeUpdate();
			System.out.println("成功增加一筆資料");
		
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
	public void update(GroupReportVO groupReportVO) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
				
			pstmt.setString(1, groupReportVO.getMemID());
			pstmt.setString(2, groupReportVO.getGroupID());
			pstmt.setString(3, groupReportVO.getAdminID());
			pstmt.setInt(4, groupReportVO.getState());
			pstmt.setString(5, groupReportVO.getGreportID());
				
			System.out.println("成功修改資料"+groupReportVO.getGreportID());
				
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
	public void delete(String greportID) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
		
	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(DELETE);

		pstmt.setString(1, greportID);

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
	public GroupReportVO findByPrimaryKey(String greportID) {
		
		GroupReportVO groupReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, greportID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				groupReportVO = new GroupReportVO();
				groupReportVO.setGreportID(rs.getString("greport_Id"));
				groupReportVO.setMemID(rs.getString("mem_Id"));
				groupReportVO.setGroupID(rs.getString("group_Id"));
				groupReportVO.setAdminID(rs.getString("admin_Id"));
				groupReportVO.setState(rs.getInt("state"));
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
		return groupReportVO;
	}

	@Override
	public List<GroupReportVO> getAll() {
		List<GroupReportVO> list = new ArrayList<GroupReportVO>();
		GroupReportVO groupReportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				
				groupReportVO = new GroupReportVO();
				groupReportVO.setGreportID(rs.getString("greport_Id"));
				groupReportVO.setMemID(rs.getString("mem_Id"));
				groupReportVO.setGroupID(rs.getString("group_Id"));
				groupReportVO.setAdminID(rs.getString("admin_Id"));
				groupReportVO.setState(rs.getInt("state"));
				list.add(groupReportVO);  //儲存列資料在list
		
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
	
}
