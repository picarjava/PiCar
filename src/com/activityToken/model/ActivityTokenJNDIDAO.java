package com.activityToken.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberDAO;


public class ActivityTokenJNDIDAO implements ActivityTokenDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}

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
	/*新增單一會員查詢語法*/
	private static final String GET_ONES_ALL_STMT=
			"SELECT * FROM ACTIVITY_TOKEN WHERE MEM_ID=? ";
	
	@Override
	public void insert(ActivityTokenVO activityTokenVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, activityTokenVO.getMemID());
			pstmt.setString(2, activityTokenVO.getActivityID());
			pstmt.setInt(3, activityTokenVO.getTokenAmount());
			pstmt.setDate(4, activityTokenVO.getDeadline());
			

			pstmt.executeUpdate();

			// Handle any driver errors
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
	//領取代幣 安全交易用
	public void insert(ActivityTokenVO activityTokenVO,Integer sum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			con.setAutoCommit(false);//多筆訂單新增視為一筆交易
			
			pstmt.setString(1, activityTokenVO.getMemID());
			pstmt.setString(2, activityTokenVO.getActivityID());
			pstmt.setInt(3, activityTokenVO.getTokenAmount());
			pstmt.setDate(4, activityTokenVO.getDeadline());
			pstmt.executeUpdate();
			MemberDAO memberDAO=new MemberDAO();
			memberDAO.updateActivityToken(sum, activityTokenVO.getMemID(), con); //同一個連線更新活動代幣
			con.commit();  //新增過程無誤，則commit
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback(); //新增過程中若有誤，則rollback
			}catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("SQL發生錯誤"
					+ se.getMessage());
			// Clean up JDBC resources
		}
		finally {
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
	
	//update for transaction 代幣扣除用 尚未完成 
	public void update(ActivityTokenVO activityTokenVO, Integer sum){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, activityTokenVO.getTokenAmount());
			pstmt.setDate(2, activityTokenVO.getDeadline());
			pstmt.setString(3, activityTokenVO.getMemID());
			pstmt.setString(4, activityTokenVO.getActivityID());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(ActivityTokenVO activityTokenVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, activityTokenVO.getTokenAmount());
			pstmt.setDate(2, activityTokenVO.getDeadline());
			pstmt.setString(3, activityTokenVO.getMemID());
			pstmt.setString(4, activityTokenVO.getActivityID());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_id);
			pstmt.setString(2, activity_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	
	/*新增單一會員查詢所有代幣明細的方法*/
	@Override
	public List<ActivityTokenVO> getOnesALL(String memID) {
		List<ActivityTokenVO> list=new ArrayList<ActivityTokenVO>();
		ActivityTokenVO activityTokenVO=new ActivityTokenVO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONES_ALL_STMT);
			pstmt.setString(1, memID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				activityTokenVO=new ActivityTokenVO();
				activityTokenVO.setMemID(rs.getString("MEM_ID"));
				activityTokenVO.setActivityID(rs.getString("ACTIVITY_ID"));
				activityTokenVO.setTokenAmount(rs.getInt("TOKEN_AMOUNT"));
				activityTokenVO.setDeadline(rs.getDate("DEADLINE"));
				list.add(activityTokenVO);
			}
			
		}catch (SQLException se) {
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
		return list;
	}
		
}
