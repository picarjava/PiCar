package com.driverReport.model;

import java.sql.*;
import java.util.*;


public class DriverReportJDBCDAO implements DriverReportDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PICAR";
	String passwd = "PICAR";
	
	private static final String INSERT_STMT =
		"INSERT INTO DRIVER_REPORT(DREPORT_ID, MEM_ID, ADMIN_ID, ORDER_ID,CONTENT, TIME, STATE) VALUES (('DR'||LPAD(to_char(DREPORT_ID_SEQ.NEXTVAL),3,'0')), ?,?,?,?,?,?)";
	private static final String GET_ALL_STMT =
		"SELECT DREPORT_ID, MEM_ID, ADMIN_ID, ORDER_ID,CONTENT, TIME, STATE FROM DRIVER_REPORT ORDER BY DREPORT_ID";
	private static final String GET_ONE_STMT =
		"SELECT DREPORT_ID, MEM_ID, ADMIN_ID, ORDER_ID,CONTENT, TIME, STATE FROM DRIVER_REPORT WHERE DREPORT_ID = ?";
	private static final String DELETE =
		"DELETE FROM DRIVER_REPORT WHERE DREPORT_ID = ?";
	private static final String UPDATE =
		"UPDATE DRIVER_REPORT SET MEM_ID=?, ADMIN_ID=?, ORDER_ID=?, CONTENT=?, TIME=?, STATE=? WHERE DREPORT_ID=?";

	@Override
	public void insert(DriverReportVO driverReportVO) {
		
	Connection con = null;
	PreparedStatement pstmt = null;
	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
//			pstmt.setString(1, driverReportVO.getDreport_Id()); 此為自增主鍵
			pstmt.setString(1, driverReportVO.getMemID());
			pstmt.setString(2, driverReportVO.getAdminID());
			pstmt.setString(3, driverReportVO.getOrderID());
			pstmt.setString(4, driverReportVO.getContent());
			pstmt.setDate(5, driverReportVO.getTime());
			pstmt.setInt(6, driverReportVO.getState());
			
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
	public void update(DriverReportVO driverReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, driverReportVO.getMemID());
				pstmt.setString(2, driverReportVO.getAdminID());
				pstmt.setString(3, driverReportVO.getOrderID());
				pstmt.setString(4, driverReportVO.getContent());
				pstmt.setDate(5, driverReportVO.getTime());
				pstmt.setInt(6, driverReportVO.getState());
				pstmt.setString(7, driverReportVO.getDreportID());
				
				System.out.println("成功修改資料"+driverReportVO.getDreportID());
				
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
	public void delete(String dreportID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, dreportID);

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
	public DriverReportVO findByPrimaryKey(String dreportID) {
		
		DriverReportVO driverReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, dreportID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				driverReportVO = new DriverReportVO();
				driverReportVO.setDreportID(rs.getString("dreport_Id"));
				driverReportVO.setMemID(rs.getString("mem_Id"));
				driverReportVO.setAdminID(rs.getString("admin_Id"));
				driverReportVO.setOrderID(rs.getString("order_Id"));
				driverReportVO.setContent(rs.getString("content"));
				driverReportVO.setTime(rs.getDate("time"));
				driverReportVO.setState(rs.getInt("state"));
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
		return driverReportVO;
	}

	@Override
	public List<DriverReportVO> getAll() {
		List<DriverReportVO> list = new ArrayList<DriverReportVO>();
		DriverReportVO driverReportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				
				driverReportVO = new DriverReportVO();
				driverReportVO.setDreportID(rs.getString("dreport_Id"));
				driverReportVO.setMemID(rs.getString("mem_Id"));
				driverReportVO.setAdminID(rs.getString("admin_Id"));
				driverReportVO.setOrderID(rs.getString("order_Id"));
				driverReportVO.setContent(rs.getString("content"));
				driverReportVO.setTime(rs.getDate("time"));
				driverReportVO.setState(rs.getInt("state"));	
				list.add(driverReportVO);  //儲存列資料在list
		
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

		DriverReportJDBCDAO dao = new DriverReportJDBCDAO();

		// 新增
		DriverReportVO driverReportVO1 = new DriverReportVO();
//		driverReportVO1.setDreport_Id("DR004"); //此為自增主鍵不用set
		driverReportVO1.setMemID("M001");
		driverReportVO1.setAdminID("A001");
		driverReportVO1.setOrderID("1");
		driverReportVO1.setContent("不準時害我遲到");
		driverReportVO1.setTime(java.sql.Date.valueOf("2019-02-14"));
		driverReportVO1.setState(1);
		dao.insert(driverReportVO1);

//		// 修改
		DriverReportVO driverReportVO2 = new DriverReportVO();
		driverReportVO2.setDreportID("DR003");
		driverReportVO2.setMemID("M001");
		driverReportVO2.setAdminID("A001");
		driverReportVO2.setOrderID("3");
		driverReportVO2.setContent("司機態度太差了吧傻眼");
		driverReportVO2.setTime(java.sql.Date.valueOf("2019-02-01"));
		driverReportVO2.setState(1);
		dao.update(driverReportVO2);

//		// 刪除
		dao.delete("DR003");
		System.out.println("成功刪除一筆資料");
//
		// 查詢
		DriverReportVO driverReportVO3 = dao.findByPrimaryKey("DR001");
		System.out.print(driverReportVO3.getDreportID() + ",");
		System.out.print(driverReportVO3.getMemID() + ",");
		System.out.print(driverReportVO3.getAdminID() + ",");
		System.out.print(driverReportVO3.getOrderID() + ",");
		System.out.print(driverReportVO3.getContent() + ",");
		System.out.print(driverReportVO3.getTime() + ",");
		System.out.println(driverReportVO3.getState());
		System.out.println("---------------------");

//		// 查詢
		List<DriverReportVO> list = dao.getAll();
		for (DriverReportVO aDriverReport : list) {
			System.out.print(aDriverReport.getDreportID() + ",");
			System.out.print(aDriverReport.getMemID() + ",");
			System.out.print(aDriverReport.getAdminID() + ",");
			System.out.print(aDriverReport.getOrderID() + ",");
			System.out.print(aDriverReport.getContent() + ",");
			System.out.print(aDriverReport.getTime() + ",");
			System.out.print(aDriverReport.getState());
			System.out.println();
		}
	}
	
	
}
