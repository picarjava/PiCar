package com.groupReport.model;

import java.sql.*;
import java.util.*;

public class GroupReportJDBCDAO implements GroupReportDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PICAR";
	String passwd = "PICAR";
	
	private static final String INSERT_STMT =
		"INSERT INTO GROUP_REPORT(GREPORT_ID, MEM_ID, GROUP_ID, ADMIN_ID, CONTENT, TIME, STATE) VALUES (('GR'||LPAD(to_char(GREPORT_ID_SEQ.NEXTVAL),3,'0')), ?,?,?,?,?,?)";
	private static final String GET_ALL_STMT =
		"SELECT GREPORT_ID, MEM_ID, GROUP_ID, ADMIN_ID, CONTENT, TIME, STATE FROM GROUP_REPORT ORDER BY GREPORT_ID";
	private static final String GET_ONE_STMT =
		"SELECT GREPORT_ID, MEM_ID, GROUP_ID, ADMIN_ID, CONTENT, TIME, STATE FROM GROUP_REPORT WHERE GREPORT_ID = ?";
	private static final String DELETE =
		"DELETE FROM GROUP_REPORT WHERE GREPORT_ID = ?";
	private static final String UPDATE =
		"UPDATE GROUP_REPORT SET MEM_ID=?, GROUP_ID =?, ADMIN_ID=?, CONTENT=?, TIME=?,STATE=? WHERE GREPORT_ID=?";
	
	
	
	@Override
	public void insert(GroupReportVO groupReportVO) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
				
//			pstmt.setString(1, groupReportVO.getGreportID()); 此為自增主鍵
			pstmt.setString(1, groupReportVO.getMemID());
			pstmt.setString(2, groupReportVO.getGroupID());		
			pstmt.setString(3, groupReportVO.getAdminID());
			pstmt.setString(4, groupReportVO.getContent());
			pstmt.setDate(5, groupReportVO.getTime());
			pstmt.setInt(6, groupReportVO.getState());
				
			pstmt.executeUpdate();
			System.out.println("成功增加一筆資料");
		
			//控制任何SQL的錯誤
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
	public void update(GroupReportVO groupReportVO) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
				
			pstmt.setString(1, groupReportVO.getMemID());
			pstmt.setString(2, groupReportVO.getGroupID());
			pstmt.setString(3, groupReportVO.getAdminID());
			pstmt.setString(4, groupReportVO.getContent());
			pstmt.setDate(5, groupReportVO.getTime());
			pstmt.setInt(6, groupReportVO.getState());
			pstmt.setString(7, groupReportVO.getGreportID());
				
			System.out.println("成功修改資料"+groupReportVO.getGreportID());
				
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
	public void delete(String greportID) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
		
	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(DELETE);

		pstmt.setString(1, greportID);

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
	public GroupReportVO findByPrimaryKey(String greportID) {
		
		GroupReportVO groupReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, greportID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				groupReportVO = new GroupReportVO();
				groupReportVO.setGreportID(rs.getString("greport_Id"));
				groupReportVO.setMemID(rs.getString("mem_Id"));
				groupReportVO.setGroupID(rs.getString("group_Id"));
				groupReportVO.setAdminID(rs.getString("admin_Id"));
				groupReportVO.setContent(rs.getString("content"));
				groupReportVO.setTime(rs.getDate("time"));
				groupReportVO.setState(rs.getInt("state"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				
				groupReportVO = new GroupReportVO();
				groupReportVO.setGreportID(rs.getString("greport_Id"));
				groupReportVO.setMemID(rs.getString("mem_Id"));
				groupReportVO.setGroupID(rs.getString("group_Id"));
				groupReportVO.setAdminID(rs.getString("admin_Id"));
				groupReportVO.setContent(rs.getString("content"));
				groupReportVO.setTime(rs.getDate("time"));
				groupReportVO.setState(rs.getInt("state"));
				list.add(groupReportVO);  //儲存列資料在list
		
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
	
	public static void main(String[] args) {

		GroupReportJDBCDAO dao = new GroupReportJDBCDAO();

		// 新增
		GroupReportVO groupReportVO1 = new GroupReportVO();
	  //groupReportVO1.setGreportID("GR004"); //此為自增主鍵不用set
		groupReportVO1.setMemID("M001");
		groupReportVO1.setGroupID("G001");
		groupReportVO1.setAdminID("A001");
		groupReportVO1.setContent("這個揪團團長講髒話很沒禮貌");
		groupReportVO1.setTime(java.sql.Date.valueOf("2019-02-22"));
		groupReportVO1.setState(1);
		dao.insert(groupReportVO1);

		// 修改
//		GroupReportVO groupReportVO2 = new GroupReportVO();
//		groupReportVO2.setGreportID("GR001");
//		groupReportVO2.setMemID("M002");
//		groupReportVO2.setGroupID("G001");
//		groupReportVO2.setAdminID("A001");
//		groupReportVO2.setContent("這個揪團團長講髒話很沒禮貌");
//		groupReportVO2.setTime(java.sql.Date.valueOf("2019-02-22"));
//		groupReportVO2.setState(1);
//		dao.update(groupReportVO2);

		// 刪除
//		dao.delete("GR002");
//		System.out.println("成功刪除一筆資料");
//
		// 查詢 
		GroupReportVO groupReportVO3 = dao.findByPrimaryKey("GR001");
		System.out.print(groupReportVO3.getGreportID() + ",");
		System.out.print(groupReportVO3.getMemID() + ",");
		System.out.print(groupReportVO3.getGroupID() + ",");	
		System.out.print(groupReportVO3.getAdminID() + ",");
		System.out.print(groupReportVO3.getContent() + ",");
		System.out.print(groupReportVO3.getTime() + ",");
		System.out.println(groupReportVO3.getState());
		System.out.println("---------------------");

//		// 查詢
		List<GroupReportVO> list = dao.getAll();
		for (GroupReportVO aGroupReport : list) {
			System.out.print(aGroupReport.getGreportID() + ",");
			System.out.print(aGroupReport.getMemID() + ",");
			System.out.print(aGroupReport.getGroupID() + ",");
			System.out.print(aGroupReport.getAdminID() + ",");
			System.out.print(aGroupReport.getContent() + ",");
			System.out.print(aGroupReport.getTime() + ",");
			System.out.print(aGroupReport.getState());
			System.out.println();
		}
		
	}
	
}
