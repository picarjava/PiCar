
package com.activ_manage.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityJDBCDAO implements ActivityDAO_Impl{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="CA106";
	String passwd="123456";
	
	private static final String INSERT_STMT=
			"INSERT INTO ACTIVITY(ACTIVITY_ID,ACTIVITY_NAME,ACTIVITY_INFO,ACTIVITY_START,ACTIVITY_END,ACTIVITY_CODE,TOKEN_AMOUNT,ACTIVITY_POST) VALUES(?,?,?,?,?,?,?,?)"; 
	private static final String GET_ALL_STMT=
			"SELECT ACTIVITY_ID,ACTIVITY_NAME,ACTIVITY_INFO,TO_CHAR(ACTIVITY_START,'YYYY-MM-DD HH24:MI:SS')ACTIVITY_START,TO_CHAR(ACTIVITY_END,'YYYY-MM-DD HH24:MI:SS')ACTIVITY_END,ACTIVITY_CODE,TOKEN_AMOUNT,ACTIVITY_POST FROM ACTIVITY ORDER BY TO_CHAR(ACTIVITY_START,'YYYY-MM-DD HH24:MI:SS')ACTIVITY_START";
	private static final String GET_ONE_STMT=
			"SELECT ACTIVITY_ID,ACTIVITY_NAME,ACTIVITY_INFO,TO_CHAR(ACTIVITY_START,'YYYY-MM-DD HH24:MI:SS')ACTIVITY_START,TO_CHAR(ACTIVITY_END,'YYYY-MM-DD HH24:MI:SS')ACTIVITY_END,ACTIVITY_CODE,TOKEN_AMOUNT,ACTIVITY_POST FROM ACTIVITY WHERE ACTIVITY_ID=?";
	private static final String DELETE=
			"DELETE FROM ACTIVITY WHERE ACTIVITY_ID=?";
	private static final String UPDATE=
			"UPDATE ACTIVITY SET ACTIVITY_ID=?,ACTIVITY_NAME=?,ACTIVITY_INFO=?,ACTIVITY_START=?,ACTIVITY_END,ACTIVITY_CODE=?,TOKEN_AMOUNT=?,ACTIVITY_POST=?";
	
	/*增、改的code一樣，只差在pstmt預先送出的SQL常數不同*/
	@Override
	public void insert(ActivityVO activityVO) {
		Connection con =null;
		PreparedStatement pstmt= null;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			/*根據SQL常數，用vo.getxxx去set pstmt需要的值*/
			pstmt.setString(1, activityVO.getActivity_Id());
			pstmt.setString(2, activityVO.getActivity_Name());
			pstmt.setString(3, activityVO.getActivity_Info());
			pstmt.setDate(4, activityVO.getActivity_Start());
			pstmt.setDate(5, activityVO.getActivity_End());
			pstmt.setString(6, activityVO.getActivity_Code());
			pstmt.setInt(7, activityVO.getToken_Amount());
			pstmt.setBytes(8, activityVO.getActivity_Post());
			pstmt.executeUpdate();
		/*處理driver errors*/
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入DB Driver"+e.getMessage());
		/*處理SQL errors*/
		}catch(SQLException se){
			throw new RuntimeException ("發生DB error"+se.getMessage());
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
			pstmt.setString(1, activityVO.getActivity_Id());
			pstmt.setString(2, activityVO.getActivity_Name());
			pstmt.setString(3, activityVO.getActivity_Info());
			pstmt.setDate(4, activityVO.getActivity_Start());
			pstmt.setDate(5, activityVO.getActivity_End());
			pstmt.setString(6, activityVO.getActivity_Code());
			pstmt.setInt(7, activityVO.getToken_Amount());
			pstmt.setBytes(8, activityVO.getActivity_Post());
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入DB Driver"+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException ("發生DB error"+se.getMessage());
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
	public void delete(String activity_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE);
			/*根據SQL常數，pstmt需要的值*/
			/*parameterIndex 從1開始*/
			pstmt.setString(1, activity_id);
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入DB Driver"+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("發生DB error"+se.getMessage());
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
	public ActivityVO findByPrimaryKey(String activity_id) {
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
			pstmt.setString(1, activity_id);
			/*進行一筆資料的查詢*/
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				activityVO=new ActivityVO();
				activityVO.setActivity_Id(rs.getString("activity_Id"));
				activityVO.setActivity_Name(rs.getString("activity_Name"));
				activityVO.setActivity_Info(rs.getString("activity_Info"));
				activityVO.setActivity_Start(rs.getDate("activity_Start"));
				activityVO.setActivity_End(rs.getDate("activity_End"));
				activityVO.setActivity_Code(rs.getString("activity_Code"));
				activityVO.setToken_Amount(rs.getInt("token_Amount"));
				activityVO.setActivity_Post(rs.getBytes("activity_Id"));
			}
		
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入BD driver"+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("發生DB errors"+se.getMessage());
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
				activityVO.setActivity_Id(rs.getString("activity_Id"));
				activityVO.setActivity_Name(rs.getString("activity_Name"));
				activityVO.setActivity_Info(rs.getString("activity_Info"));
				activityVO.setActivity_Start(rs.getDate("activity_Start"));
				activityVO.setActivity_End(rs.getDate("activity_End"));
				activityVO.setActivity_Code(rs.getString("activity_Code"));
				activityVO.setToken_Amount(rs.getInt("token_Amount"));
				activityVO.setActivity_Post(rs.getBytes("activity_Id"));
				list.add(activityVO);
			}
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入BD driver"+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("發生DB errors"+se.getMessage());
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
		
		return null;
	}
	
}
		

	
