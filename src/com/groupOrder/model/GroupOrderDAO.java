package com.groupOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class GroupOrderDAO implements GroupOrderDAO_interface {
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
			"INSERT INTO GROUP_ORDER (GORDER_ID,DRIVER_ID,MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE,GROUP_ID,START_LOC,END_LOC) VALUES('GODR'||LPAD(to_char(GODR_ID_SEQ.NEXTVAL),3,'0'),?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT GORDER_ID ,DRIVER_ID, MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE,GROUP_ID,START_LOC,END_LOC FROM GROUP_ORDER";
		private static final String GET_ONE_STMT = 
			"SELECT GORDER_ID ,DRIVER_ID, MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE,GROUP_ID,START_LOC,END_LOC FROM GROUP_ORDER where GORDER_ID = ?";
		private static final String GET_ALL_MemID = 
				"SELECT GORDER_ID ,DRIVER_ID, MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE,GROUP_ID,START_LOC,END_LOC FROM GROUP_ORDER where MEM_ID = ?";
		private static final String DELETE = 
			"DELETE FROM GROUP_ORDER where GORDER_ID = ?";
		private static final String UPDATE = 
			"UPDATE GROUP_ORDER set DRIVER_ID=?,MEM_ID=?,STATE=?,TOTAL_AMOUT=?,START_TIME=?,END_TIME=?,START_LNG=?,START_LAT=?,END_LNG=?,END_LAT=?,ORDER_TYPE=?,RATE=?,NOTE=? where GORDER_ID = ?";

		private static final String GET_ONE_STMTSTART_TIME = 
				"SELECT GORDER_ID ,DRIVER_ID, MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE,GROUP_ID,START_LOC,END_LOC FROM GROUP_ORDER where GROUP_ID = ? and START_TIME= ?";
		
		private static final String GET_ONE_STMTSTART_TIME_Mem = 
				"SELECT START_TIME FROM GROUP_ORDER where GROUP_ID = ? and MEM_ID= ?";
	
		
		private static final String UPDATEmem = 
				"UPDATE GROUP_ORDER set MEM_ID=? where GORDER_ID = ?";

		private static final String UPDATEmemGROUP_ID__MEM_ID = 
				"UPDATE GROUP_ORDER set MEM_ID=null where GROUP_ID = ? and MEM_ID=?";
		
		private static final String UPDATE_STATE__GROUP_ID = 
				"UPDATE GROUP_ORDER set STATE=? where GROUP_ID = ?";
		
		private static final String UPDATE_MEM_ID__GROUP_ID_MEM_ID = 
				"UPDATE GROUP_ORDER set MEM_ID=null where GROUP_ID = ? and MEM_ID=?";
		
		private static final String GET_ONE_GROUP_ID_START_TIME = 
				"SELECT * FROM GROUP_ORDER where GROUP_ID = ?  and START_TIME= ?";
		private static final String GET_ONE_GROUP_ID__STATE_MEM_ID = 
		"select DISTINCT GROUP_ID from GROUP_ORDER where STATE=0 and MEM_ID=?";
		
		private static final String GET_ONE_RATE__DRIVER_ID = 
				"SELECT AVG(RATE) AS 'AvgRATE' FROM GROUP_ORDER WHERE driver_id =?";
		
		private static final String GET_MEM_ID__MEM_ID_GROUP_ID = 
				"SELECT MEM_ID FROM GROUP_ORDER WHERE MEM_ID=? and GROUP_ID = ?";
		
		private static final String GET_GROUP_ID__START_TIME = 
		"select distinct GROUP_ID  from GROUP_ORDER where START_TIME >= TO_TIMESTAMP (?, 'YYYY-MM-DD HH24:MI:SS') AND  START_TIME <= TO_TIMESTAMP (?, 'YYYY-MM-DD HH24:MI:SS')and STATE=0";
		
		private static final String GET_MEM_ID__GROUP_ID_START_TIME = 
		"select COUNT(MEM_ID)as MEM_ID from GROUP_ORDER where GROUP_ID=? and START_TIME >= TO_TIMESTAMP (?, 'YYYY-MM-DD HH24:MI:SS') AND  START_TIME <= TO_TIMESTAMP (?, 'YYYY-MM-DD HH24:MI:SS')";
		
	@Override
	public void insert(GroupOrderVO groupOrderVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, groupOrderVO.getDriverID());
			pstmt.setString(2, groupOrderVO.getMemID());
			pstmt.setInt(3,groupOrderVO.getState());
			pstmt.setInt(4,groupOrderVO.getTotalAmout());			
			pstmt.setTimestamp(5,groupOrderVO.getStartTime());
			pstmt.setTimestamp(6,groupOrderVO.getEndTime());
			pstmt.setDouble(7,groupOrderVO.getStartLng());
			pstmt.setDouble(8,groupOrderVO.getStartLat());
			pstmt.setDouble(9,groupOrderVO.getEndLng());
			pstmt.setDouble(10,groupOrderVO.getEndLat());
			pstmt.setInt(11,groupOrderVO.getOrderType());
			pstmt.setInt(12,groupOrderVO.getRate());
			pstmt.setString(13,groupOrderVO.getNote());
			pstmt.setString(14,groupOrderVO.getStartLoc());
			pstmt.setString(15,groupOrderVO.getEndLoc());
			pstmt.executeUpdate();
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
			con = ds.getConnection();
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, groupOrderVO.getDriverID());
			pstmt.setString(2, groupOrderVO.getMemID());
			pstmt.setInt(3,groupOrderVO.getState());
			pstmt.setInt(4,groupOrderVO.getTotalAmout());			
			pstmt.setTimestamp(5,groupOrderVO.getStartTime());
			pstmt.setTimestamp(6,groupOrderVO.getEndTime());
			pstmt.setDouble(7,groupOrderVO.getStartLng());
			pstmt.setDouble(8,groupOrderVO.getStartLat());
			pstmt.setDouble(9,groupOrderVO.getEndLng());
			pstmt.setDouble(10,groupOrderVO.getEndLat());
			pstmt.setInt(11,groupOrderVO.getOrderType());
			pstmt.setInt(12,groupOrderVO.getRate());
			pstmt.setString(13,groupOrderVO.getNote());
			pstmt.setString(14, groupOrderVO.getGorderID());
			pstmt.executeUpdate();
			con.commit();
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
	public void delete(String groupOrderno) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, groupOrderno);
			
			pstmt.executeUpdate();
		
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
	public GroupOrderVO findByPrimaryKey(String groupOrderno) {
		// TODO Auto-generated method stub
		GroupOrderVO groupOrderVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMT);
		pstmt.setString(1, groupOrderno);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			
		
			groupOrderVO = new GroupOrderVO();
			groupOrderVO.setGorderID(rs.getString("GORDER_ID"));
			groupOrderVO.setDriverID(rs.getString("DRIVER_ID"));
			groupOrderVO.setMemID(rs.getString("MEM_ID"));
			groupOrderVO.setState(rs.getInt("STATE"));
			groupOrderVO.setTotalAmout(rs.getInt("TOTAL_AMOUT"));
			groupOrderVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
			groupOrderVO.setStartTime(rs.getTimestamp("START_TIME"));
			groupOrderVO.setEndTime(rs.getTimestamp("END_TIME"));
			groupOrderVO.setStartLng(rs.getDouble("START_LNG"));
			groupOrderVO.setStartLat(rs.getDouble("START_LAT"));
			groupOrderVO.setEndLng(rs.getDouble("END_LNG"));
			groupOrderVO.setEndLat(rs.getDouble("END_LAT"));
			groupOrderVO.setOrderType(rs.getInt("ORDER_TYPE"));
			groupOrderVO.setRate(rs.getInt("RATE"));
			groupOrderVO.setNote(rs.getString("NOTE"));
			groupOrderVO.setGroupID(rs.getString("GROUP_ID"));
			groupOrderVO.setStartLoc(rs.getString("START_LOC"));
			groupOrderVO.setEndLoc(rs.getString("END_LOC"));
		}
		}catch (SQLException se) {
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
		return groupOrderVO;
	}
		public GroupOrderVO findByALLMemID(String groupOrderno) {
			// TODO Auto-generated method stub
			GroupOrderVO groupOrderVO =null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MemID);
			pstmt.setString(1, groupOrderno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
			
				groupOrderVO = new GroupOrderVO();
				groupOrderVO.setGorderID(rs.getString("GORDER_ID"));
				groupOrderVO.setDriverID(rs.getString("DRIVER_ID"));
				groupOrderVO.setMemID(rs.getString("MEM_ID"));
				groupOrderVO.setState(rs.getInt("STATE"));
				groupOrderVO.setTotalAmout(rs.getInt("TOTAL_AMOUT"));
				groupOrderVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
				groupOrderVO.setStartTime(rs.getTimestamp("START_TIME"));
				groupOrderVO.setEndTime(rs.getTimestamp("END_TIME"));
				groupOrderVO.setStartLng(rs.getDouble("START_LNG"));
				groupOrderVO.setStartLat(rs.getDouble("START_LAT"));
				groupOrderVO.setEndLng(rs.getDouble("END_LNG"));
				groupOrderVO.setEndLat(rs.getDouble("END_LAT"));
				groupOrderVO.setOrderType(rs.getInt("ORDER_TYPE"));
				groupOrderVO.setRate(rs.getInt("RATE"));
				groupOrderVO.setNote(rs.getString("NOTE"));
				groupOrderVO.setGroupID(rs.getString("GROUP_ID"));
				groupOrderVO.setStartLoc(rs.getString("START_LOC"));
				groupOrderVO.setEndLoc(rs.getString("END_LOC"));
				
			}
		}catch (SQLException se) {
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
		return groupOrderVO;
	}

	@Override
	public List<GroupOrderVO> getAll() {
		// TODO Auto-generated method stub
		List<GroupOrderVO> list =new ArrayList<GroupOrderVO>();
		GroupOrderVO groupOrderVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				
				groupOrderVO = new GroupOrderVO();
				groupOrderVO.setGorderID(rs.getString("GORDER_ID"));
				groupOrderVO.setDriverID(rs.getString("DRIVER_ID"));
				groupOrderVO.setMemID(rs.getString("MEM_ID"));
				groupOrderVO.setState(rs.getInt("STATE"));
				groupOrderVO.setTotalAmout(rs.getInt("TOTAL_AMOUT"));
				groupOrderVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
				groupOrderVO.setStartTime(rs.getTimestamp("START_TIME"));
				groupOrderVO.setEndTime(rs.getTimestamp("END_TIME"));
				groupOrderVO.setStartLng(rs.getDouble("START_LNG"));
				groupOrderVO.setStartLat(rs.getDouble("START_LAT"));
				groupOrderVO.setEndLng(rs.getDouble("END_LNG"));
				groupOrderVO.setEndLat(rs.getDouble("END_LAT"));
				groupOrderVO.setOrderType(rs.getInt("ORDER_TYPE"));
				groupOrderVO.setRate(rs.getInt("RATE"));
				groupOrderVO.setNote(rs.getString("NOTE"));
				groupOrderVO.setGroupID(rs.getString("GROUP_ID"));
				groupOrderVO.setStartLoc(rs.getString("START_LOC"));
				groupOrderVO.setEndLoc(rs.getString("END_LOC"));
				list.add(groupOrderVO);
			}
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
	public void insert2(List<GroupOrderVO> list, Connection con) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		try {
			
			pstmt = con.prepareStatement(INSERT_STMT);
			int conn=0;
			for (GroupOrderVO groupOrderVO : list) {
			System.out.println(conn++);
			pstmt.setString(1, groupOrderVO.getDriverID());
			pstmt.setString(2, groupOrderVO.getMemID());
			pstmt.setInt(3,groupOrderVO.getState());
			pstmt.setInt(4,groupOrderVO.getTotalAmout());			
			pstmt.setTimestamp(5,groupOrderVO.getStartTime());
			pstmt.setTimestamp(6,groupOrderVO.getEndTime());
			pstmt.setDouble(7,groupOrderVO.getStartLng());
			pstmt.setDouble(8,groupOrderVO.getStartLat());
			pstmt.setDouble(9,groupOrderVO.getEndLng());
			pstmt.setDouble(10,groupOrderVO.getEndLat());
			pstmt.setInt(11,groupOrderVO.getOrderType());
			pstmt.setInt(12,groupOrderVO.getRate());
			pstmt.setString(13,groupOrderVO.getNote());
			pstmt.setString(14,groupOrderVO.getGroupID());
			pstmt.setString(15,groupOrderVO.getStartLoc());
			pstmt.setString(16,groupOrderVO.getEndLoc());
			
			pstmt.addBatch();
			}
			System.out.println("=====================================");
			
			pstmt.executeBatch();
			System.out.println("========+++++++++++++=============================");
		}  catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
	}

	public List<GroupOrderVO> findByALLGroupMemTime(String groupid,Timestamp staerTime) {
		// TODO Auto-generated method stub
		List<GroupOrderVO> list =new ArrayList<GroupOrderVO>();
		GroupOrderVO groupOrderVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMTSTART_TIME);
		pstmt.setString(1, groupid);
		pstmt.setTimestamp(2,staerTime);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			
		
			groupOrderVO = new GroupOrderVO();
			groupOrderVO.setGorderID(rs.getString("GORDER_ID"));
			groupOrderVO.setDriverID(rs.getString("DRIVER_ID"));
			groupOrderVO.setMemID(rs.getString("MEM_ID"));
			groupOrderVO.setState(rs.getInt("STATE"));
			groupOrderVO.setTotalAmout(rs.getInt("TOTAL_AMOUT"));
			groupOrderVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
			groupOrderVO.setStartTime(rs.getTimestamp("START_TIME"));
			groupOrderVO.setEndTime(rs.getTimestamp("END_TIME"));
			groupOrderVO.setStartLng(rs.getDouble("START_LNG"));
			groupOrderVO.setStartLat(rs.getDouble("START_LAT"));
			groupOrderVO.setEndLng(rs.getDouble("END_LNG"));
			groupOrderVO.setEndLat(rs.getDouble("END_LAT"));
			groupOrderVO.setOrderType(rs.getInt("ORDER_TYPE"));
			groupOrderVO.setRate(rs.getInt("RATE"));
			groupOrderVO.setNote(rs.getString("NOTE"));
			groupOrderVO.setGroupID(rs.getString("GROUP_ID"));
			groupOrderVO.setStartLoc(rs.getString("START_LOC"));
			groupOrderVO.setEndLoc(rs.getString("END_LOC"));
			list.add(groupOrderVO);
		}
	}catch (SQLException se) {
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
	
	
	
	public void updateMem(String memid,String gorderID) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(UPDATEmem);
			pstmt.setString(1, memid);
			pstmt.setString(2, gorderID);			
			pstmt.executeUpdate();
			con.commit();
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
	
	public void UPDATEmemGROUP_ID__MEM_ID(String GROUP_ID,String MEM_ID) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(UPDATEmemGROUP_ID__MEM_ID);
			pstmt.setString(1, GROUP_ID);
			pstmt.setString(2, MEM_ID);			
			pstmt.executeUpdate();
			con.commit();
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
	
	public void UPDATE_STATE__GROUP_ID(Integer STATE,String GROUP_ID) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(UPDATE_STATE__GROUP_ID);
			pstmt.setInt(1, STATE);
			pstmt.setString(2, GROUP_ID);			
			pstmt.executeUpdate();
			con.commit();
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
	
	public void UPDATEmemid__GROUP_ID_MEM_ID(String GROUP_ID,String Memid) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(UPDATE_MEM_ID__GROUP_ID_MEM_ID);
			pstmt.setString(1, GROUP_ID);
			pstmt.setString(2, Memid);			
			pstmt.executeUpdate();
			con.commit();
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
	
	public GroupOrderVO getOneStntstartTimeMems(String groupid,String memid ) {
		// TODO Auto-generated method stub

		GroupOrderVO groupOrderVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMTSTART_TIME_Mem);
		pstmt.setString(1, groupid);
		pstmt.setString(2,memid);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			
		
			groupOrderVO = new GroupOrderVO();

			groupOrderVO.setStartTime(rs.getTimestamp("START_TIME"));
			
			
		}
	}catch (SQLException se) {
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
	return groupOrderVO;
}
	
	public Integer getOneDriversAve(String driver_id) {
		// TODO Auto-generated method stub

		Integer avgrate=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_RATE__DRIVER_ID);
		pstmt.setString(1, driver_id);
		rs = pstmt.executeQuery();
		
		avgrate=(rs.getInt("AvgRATE"));
			
			
		
	}catch (SQLException se) {
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
	return avgrate;
}
	
	public Integer getMemID_groupID_startTime(String groupID,String START_TIME,String START_TIME2) {
		// TODO Auto-generated method stub

		Integer avgmemID=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_MEM_ID__GROUP_ID_START_TIME);
		pstmt.setString(1, groupID);
		pstmt.setString(2, START_TIME);
		pstmt.setString(3, START_TIME2);
		rs = pstmt.executeQuery();
		   while (rs.next()) {
		avgmemID=(rs.getInt("MEM_ID"));
		   }
			
		
	}catch (SQLException se) {
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
	return avgmemID;
}
	
	public String get_memid__memid_groupid(String memid,String groupid) {
		// TODO Auto-generated method stub

		String memids=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_MEM_ID__MEM_ID_GROUP_ID);
		pstmt.setString(1, memid);
		pstmt.setString(2, groupid);
		rs = pstmt.executeQuery();
		
		memids=(rs.getString("MEM_ID"));
			
			
		
	}catch (SQLException se) {
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
	return memids;
}
	
	public List<String> get_group_id__start_time(String START_TIME,String START_TIME2) {
		// TODO Auto-generated method stub
		List<String> list =new ArrayList<String>();
		String groupid=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_GROUP_ID__START_TIME);
		pstmt.setString(1, START_TIME);
		pstmt.setString(2, START_TIME2);
		rs = pstmt.executeQuery();
		while(rs.next()) {
		groupid=(rs.getString("GROUP_ID"));
		list.add(groupid);
		}
			
		
	}catch (SQLException se) {
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
	
	public List<GroupOrderVO> getOneStntstartTimeMem(String groupid,String memid ) {
		// TODO Auto-generated method stub
		List<GroupOrderVO> list =new ArrayList<GroupOrderVO>();
		GroupOrderVO groupOrderVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMTSTART_TIME_Mem);
		pstmt.setString(1, groupid);
		pstmt.setString(2,memid);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			
		
			groupOrderVO = new GroupOrderVO();

			groupOrderVO.setStartTime(rs.getTimestamp("START_TIME"));
			
			list.add(groupOrderVO);
		}
	}catch (SQLException se) {
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
	
	

	public List<GroupOrderVO> getALL_GroupID_StArtTime(String groupid,Timestamp staerTime) {
		// TODO Auto-generated method stub
		List<GroupOrderVO> list =new ArrayList<GroupOrderVO>();
		GroupOrderVO groupOrderVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_GROUP_ID_START_TIME);
		pstmt.setString(1, groupid);
		pstmt.setTimestamp(2,staerTime);
		rs = pstmt.executeQuery();
		while(rs.next()) {
		    
		
			groupOrderVO = new GroupOrderVO();
			groupOrderVO.setGorderID(rs.getString("GORDER_ID"));
			groupOrderVO.setDriverID(rs.getString("DRIVER_ID"));
			groupOrderVO.setMemID(rs.getString("MEM_ID"));
			groupOrderVO.setState(rs.getInt("STATE"));
			groupOrderVO.setTotalAmout(rs.getInt("TOTAL_AMOUT"));
			groupOrderVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
			groupOrderVO.setStartTime(rs.getTimestamp("START_TIME"));
			groupOrderVO.setEndTime(rs.getTimestamp("END_TIME"));
			groupOrderVO.setStartLng(rs.getDouble("START_LNG"));
			groupOrderVO.setStartLat(rs.getDouble("START_LAT"));
			groupOrderVO.setEndLng(rs.getDouble("END_LNG"));
			groupOrderVO.setEndLat(rs.getDouble("END_LAT"));
			groupOrderVO.setOrderType(rs.getInt("ORDER_TYPE"));
			groupOrderVO.setRate(rs.getInt("RATE"));
			groupOrderVO.setNote(rs.getString("NOTE"));
			groupOrderVO.setGroupID(rs.getString("GROUP_ID"));
			groupOrderVO.setStartLoc(rs.getString("START_LOC"));
			groupOrderVO.setEndLoc(rs.getString("END_LOC"));
			list.add(groupOrderVO);
		}
	}catch (SQLException se) {
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

	public List<GroupOrderVO> GET_ONE_groupid__state_men_id(String memid) {
		// TODO Auto-generated method stub
		List<GroupOrderVO> list =new ArrayList<GroupOrderVO>();
		GroupOrderVO groupOrderVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_GROUP_ID__STATE_MEM_ID);
		pstmt.setString(1, memid);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			
		
			groupOrderVO = new GroupOrderVO();	
			groupOrderVO.setGroupID(rs.getString("GROUP_ID"));
			list.add(groupOrderVO);
		}
	}catch (SQLException se) {
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
