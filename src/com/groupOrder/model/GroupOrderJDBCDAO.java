package com.groupOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GroupOrderJDBCDAO implements GroupOrderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO GROUP_ORDER (GORDER_ID,DRIVER_ID,MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE) VALUES('GODR'||LPAD(to_char(GODR_ID_SEQ.NEXTVAL),3,'0'),?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM emp2 order by empno";
		private static final String GET_ONE_STMT = 
			"SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM emp2 where empno = ?";
		private static final String DELETE = 
			"DELETE FROM emp2 where empno = ?";
		private static final String UPDATE = 
			"UPDATE GROUP_ORDER set DRIVER_ID=?,MEM_ID=?,STATE=?,TOTAL_AMOUT=?,START_TIME=?,END_TIME=?,START_LNG=?,START_LAT=?,END_LNG=?,END_LAT=?,ORDER_TYPE=?,RATE=?,NOTE=? where GORDER_ID = ?";

	@Override
	public void insert(GroupOrderVO groupOrderVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, groupOrderVO.getDriverID());
			pstmt.setString(2, groupOrderVO.getMemID());
			pstmt.setInt(3,groupOrderVO.getState());
			pstmt.setInt(4,groupOrderVO.getTotalAmout());			
			pstmt.setDate(5,groupOrderVO.getStartTime());
			pstmt.setDate(6,groupOrderVO.getEndTime());
			pstmt.setDouble(7,groupOrderVO.getStartLng());
			pstmt.setDouble(8,groupOrderVO.getStartLat());
			pstmt.setDouble(9,groupOrderVO.getEndLng());
			pstmt.setDouble(10,groupOrderVO.getEndLat());
			pstmt.setInt(11,groupOrderVO.getOrderType());
			pstmt.setInt(12,groupOrderVO.getRate());
			pstmt.setString(13,groupOrderVO.getNote());
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
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
	public void update(GroupOrderVO groupOrderVO) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, groupOrderVO.getDriverID());
			pstmt.setString(2, groupOrderVO.getMemID());
			pstmt.setInt(3,groupOrderVO.getState());
			pstmt.setInt(4,groupOrderVO.getTotalAmout());			
			pstmt.setDate(5,groupOrderVO.getStartTime());
			pstmt.setDate(6,groupOrderVO.getEndTime());
			pstmt.setDouble(7,groupOrderVO.getStartLng());
			pstmt.setDouble(8,groupOrderVO.getStartLat());
			pstmt.setDouble(9,groupOrderVO.getEndLng());
			pstmt.setDouble(10,groupOrderVO.getEndLat());
			pstmt.setInt(11,groupOrderVO.getOrderType());
			pstmt.setInt(12,groupOrderVO.getRate());
			pstmt.setString(13,groupOrderVO.getNote());
			pstmt.setString(14, groupOrderVO.getGorderID());
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
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
	public void delete(Integer groupOrderno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GroupOrderVO findByPrimaryKey(Integer groupOrderno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupOrderVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
