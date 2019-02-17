package com.driverReport.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DriverReportJNDIDAO implements DriverReportDAO_interface{
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, driverReportVO.getMemID());
			pstmt.setString(2, driverReportVO.getAdminID());
			pstmt.setString(3, driverReportVO.getOrderID());
			pstmt.setString(4, driverReportVO.getContent());
			pstmt.setDate(5, driverReportVO.getTime());
			pstmt.setInt(6, driverReportVO.getState());
			
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
	public void update(DriverReportVO driverReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
			try {
				con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, dreportID);

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
	public DriverReportVO findByPrimaryKey(String dreportID) {
		
		DriverReportVO driverReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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

			con = ds.getConnection();
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
