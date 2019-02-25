
package com.activity.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*DAO功能是用SQL語法，透過VO物件傳遞資料庫資料  進行增刪改查*/
public class ActivityJDBCDAO implements ActivityDAO_interface{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="CA106";
	String passwd="123456";
	
	private static final String INSERT_STMT=
			"INSERT INTO ACTIVITY(ACTIVITY_ID,ACTIVITY_NAME,ACTIVITY_INFO,ACTIVITY_START,ACTIVITY_END,ACTIVITY_CODE,TOKEN_AMOUNT,ACTIVITY_POST) VALUES('AC'||LPAD(to_char(ACTIVITY_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?)"; 
	private static final String GET_ALL_STMT=
			"SELECT * FROM ACTIVITY ORDER BY ACTIVITY_ID";
	private static final String GET_ONE_STMT=
			"SELECT * FROM ACTIVITY WHERE ACTIVITY_ID=?";
	private static final String DELETE=
			"DELETE FROM ACTIVITY WHERE ACTIVITY_ID=?";
	private static final String UPDATE=
			"UPDATE ACTIVITY SET ACTIVITY_NAME=?,ACTIVITY_INFO=?,ACTIVITY_START=?,ACTIVITY_END=?,ACTIVITY_CODE=?,TOKEN_AMOUNT=?,ACTIVITY_POST=? WHERE ACTIVITY_ID=?" ;
	/*新增 透過活動序號找當筆活動資訊的STMT*/
	private static final String GET_ONE_BY_CODE="SELECT * FROM ACTIVITY WHERE ACTIVITY_CODE=?";
	
	/*新增 findActicvityByCode()*/
	@Override
	public ActivityVO findActicvityByCode(String activityCode) {
		ActivityVO activityVO=null;
		Connection con= null;
		PreparedStatement pstmt =null;
		ResultSet rs= null;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ONE_BY_CODE);
			/*根據SQL常數，pstmt需要的值*/
			/*parameterIndex 從1開始*/
			pstmt.setString(1, activityCode);
			/*進行一筆資料的查詢*/
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				activityVO=new ActivityVO();
				activityVO.setActivityID(rs.getString("ACTIVITY_ID"));
				activityVO.setActivityName(rs.getString("ACTIVITY_NAME"));
				activityVO.setActivityInfo(rs.getString("ACTIVITY_INFO"));
				activityVO.setActivityStart(rs.getDate("ACTIVITY_START"));
				activityVO.setActivityEnd(rs.getDate("ACTIVITY_END"));
				activityVO.setActivityCode(rs.getString("ACTIVITY_CODE"));
				activityVO.setTokenAmount(rs.getInt("TOKEN_AMOUNT"));
				activityVO.setActivityPost(rs.getBytes("ACTIVITY_POST"));
			}
		
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入BD driver"+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("發生SQL errors"+se.getMessage());
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return activityVO;
	}
	
	
	
	/*增、改的code一樣，只差在(1)pstmt預先送出的SQL常數不同(2)改的PK放在parameterIndex=8的位置*/
	@Override
	public void insert(ActivityVO activityVO) {
		Connection con =null;
		PreparedStatement pstmt= null;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			/*根據SQL常數，用vo.getxxx去set pstmt需要的值*/
//			pstmt.setString(1, activityVO.getActivityID());
			pstmt.setString(1, activityVO.getActivityName());
			pstmt.setString(2, activityVO.getActivityInfo());
			pstmt.setDate(3, activityVO.getActivityStart());
			pstmt.setDate(4, activityVO.getActivityEnd());
			pstmt.setString(5, activityVO.getActivityCode());
			pstmt.setInt(6, activityVO.getTokenAmount());
			pstmt.setBytes(7, activityVO.getActivityPost());
			pstmt.executeUpdate();
		/*處理driver errors*/
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入DB Driver"+e.getMessage());
		/*處理SQL errors*/
		}catch(SQLException se){
			throw new RuntimeException ("發生SQL error"+se.getMessage());
		/*清除JDBC資源*/
		}finally{
			if(pstmt !=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con !=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void update(ActivityVO activityVO) {
		Connection con =null;
		PreparedStatement pstmt= null;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(UPDATE);
			/*根據SQL常數，用vo.getxxx去set pstmt需要的值*/
			
			pstmt.setString(1, activityVO.getActivityName());
			pstmt.setString(2, activityVO.getActivityInfo());
			pstmt.setDate(3, activityVO.getActivityStart());
			pstmt.setDate(4, activityVO.getActivityEnd());
			pstmt.setString(5, activityVO.getActivityCode());
			pstmt.setInt(6, activityVO.getTokenAmount());
			pstmt.setBytes(7, activityVO.getActivityPost());
			pstmt.setString(8, activityVO.getActivityID());
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入DB Driver"+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException ("發生SQL error"+se.getMessage());
		}finally{
			if(pstmt !=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con !=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
		
	@Override
	public void delete(String activityID) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE);
			/*根據SQL常數，pstmt需要的值*/
			/*parameterIndex 從1開始*/
			pstmt.setString(1, activityID);
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入DB Driver"+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("發生SQL error"+se.getMessage());
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	
	@Override
	public ActivityVO findByPrimaryKey(String activityID) {
		ActivityVO activityVO=null;
		Connection con= null;
		PreparedStatement pstmt =null;
		ResultSet rs= null;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ONE_STMT);
			/*根據SQL常數，pstmt需要的值*/
			/*parameterIndex 從1開始*/
			pstmt.setString(1, activityID);
			/*進行一筆資料的查詢*/
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				activityVO=new ActivityVO();
				activityVO.setActivityID(rs.getString("ACTIVITY_ID"));
				activityVO.setActivityName(rs.getString("ACTIVITY_NAME"));
				activityVO.setActivityInfo(rs.getString("ACTIVITY_INFO"));
				activityVO.setActivityStart(rs.getDate("ACTIVITY_START"));
				activityVO.setActivityEnd(rs.getDate("ACTIVITY_END"));
				activityVO.setActivityCode(rs.getString("ACTIVITY_CODE"));
				activityVO.setTokenAmount(rs.getInt("TOKEN_AMOUNT"));
				activityVO.setActivityPost(rs.getBytes("ACTIVITY_POST"));
			}
		
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入BD driver"+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("發生SQL errors"+se.getMessage());
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return activityVO;
	}
	
	/*查全部跟查一筆 的 code一樣，只差在查全部是把每一筆row存進List*/
	@Override
	public List<ActivityVO> getAll() {
		List<ActivityVO> list= new ArrayList<ActivityVO>();
		ActivityVO activityVO= null;
		
		Connection con= null;
		PreparedStatement pstmt =null;
		ResultSet rs= null;
		
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			/*進行全部資料的查詢*/
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				activityVO=new ActivityVO();
				activityVO.setActivityID(rs.getString("ACTIVITY_ID"));
				activityVO.setActivityName(rs.getString("ACTIVITY_NAME"));
				activityVO.setActivityInfo(rs.getString("ACTIVITY_INFO"));
				
				
//				測試時，getDate()出現 java.lang.IllegalArgumentException
				activityVO.setActivityStart(rs.getDate("ACTIVITY_START"));
				activityVO.setActivityEnd(rs.getDate("ACTIVITY_END"));
				activityVO.setActivityCode(rs.getString("ACTIVITY_CODE"));
				activityVO.setTokenAmount(rs.getInt("TOKEN_AMOUNT"));
				activityVO.setActivityPost(rs.getBytes("ACTIVITY_POST"));
				list.add(activityVO);
			}
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入BD driver"+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("發生SQL errors"+se.getMessage());
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	
}
		

	
