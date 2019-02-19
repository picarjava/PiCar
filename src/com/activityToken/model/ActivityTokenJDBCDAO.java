package com.activityToken.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ActivityTokenJDBCDAO implements ActivityTokenDAO_interface{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="CA106";
	String passwd="123456";
	
	private static final String INSERT_STMT=
			"INSERT INTO ACTIVITY_TOKEN(MEM_ID,ACTIVITY_ID,TOKEN_AMOUNT,DEADLINE) VALUES(?,?,?,?)"; 
	private static final String GET_ALL_STMT=
			"SELECT * FROM ACTIVITY_TOKEN ORDER BY ACTIVITY_ID ";
	private static final String GET_ONE_STMT=
			"SELECT * FROM ACTIVITY_TOKEN WHERE MEM_ID=? AND ACTIVITY_ID=?";
	private static final String DELETE=
			"DELETE FROM ACTIVITY_TOKEN WHERE MEM_ID=? AND ACTIVITY_ID= ?";
	private static final String UPDATE=
			"UPDATE ACTIVITY_TOKEN SET TOKEN_AMOUNT=?,DEADLINE=? WHERE MEM_ID=? AND ACTIVITY_ID=?" ;
	@Override
	public void insert(ActivityTokenVO activityTokenVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, activityTokenVO.getMemID());
			pstmt.setString(2, activityTokenVO.getActivityID());
			pstmt.setInt(3, activityTokenVO.getTokenAmount());
			pstmt.setDate(4, activityTokenVO.getDeadline());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("無法載入DB Driver"
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("SQL發生錯誤"
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
	public void update(ActivityTokenVO activityTokenVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, activityTokenVO.getTokenAmount());
			pstmt.setDate(2, activityTokenVO.getDeadline());
			pstmt.setString(3, activityTokenVO.getMemID());
			pstmt.setString(4, activityTokenVO.getActivityID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("無法載入DB Driver "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("SQL發生錯誤 "
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
	public void delete(String mem_id, String activity_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_id);
			pstmt.setString(2, activity_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("無法載入DB Driver "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("SQL發生錯誤"
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
	public ActivityTokenVO findByPrimaryKey(String mem_id, String activity_id) {
		ActivityTokenVO activityTokenVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_id);
			pstmt.setString(2, activity_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// arguments of methods which rs uesed should be same with ActivityTokenVO 
				activityTokenVO = new ActivityTokenVO();
				activityTokenVO.setMemID(rs.getString("mem_id"));
				activityTokenVO.setActivityID(rs.getString("activity_id"));
				activityTokenVO.setTokenAmount(rs.getInt("token_amount"));  
				activityTokenVO.setDeadline(rs.getDate("deadline"));  
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("無法載入DB driver "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("SQL錯誤"
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
		return activityTokenVO;
	}
	

	@Override
	public List<ActivityTokenVO> getAll() {
		List<ActivityTokenVO> list=new ArrayList<ActivityTokenVO>();
		ActivityTokenVO activityTokenVO;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				activityTokenVO = new ActivityTokenVO();
				activityTokenVO.setMemID(rs.getString("mem_id"));
				activityTokenVO.setActivityID(rs.getString("activity_id"));
				activityTokenVO.setTokenAmount(rs.getInt("token_amount"));
				activityTokenVO.setDeadline(rs.getDate("deadline"));
				list.add(activityTokenVO); // Store the row in the list
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
}
