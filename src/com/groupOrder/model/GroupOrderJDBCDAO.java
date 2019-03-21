package com.groupOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


// 要再改 不能用
public class GroupOrderJDBCDAO implements GroupOrderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO GROUP_ORDER (GORDER_ID,DRIVER_ID,MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE) VALUES('GODR'||LPAD(to_char(GODR_ID_SEQ.NEXTVAL),3,'0'),?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT GORDER_ID ,DRIVER_ID, MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE FROM GROUP_ORDER";
		private static final String GET_ONE_STMT = 
			"SELECT GORDER_ID ,DRIVER_ID, MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE FROM GROUP_ORDER where GORDER_ID = ?";
		private static final String DELETE = 
			"DELETE FROM GROUP_ORDER where GORDER_ID = ?";
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
			pstmt.setTimestamp(5,groupOrderVO.getStartTime());
			pstmt.setTimestamp(6,groupOrderVO.getEndTime());
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
	public void delete(String groupOrderno) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, groupOrderno);
			
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
	public GroupOrderVO findByPrimaryKey(String groupOrderno) {
		// TODO Auto-generated method stub
		GroupOrderVO groupOrderVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
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
		}
		}catch (ClassNotFoundException e) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(groupOrderVO);
			}
		}catch (ClassNotFoundException e) {
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


	public void insert2(GroupOrderVO groupOrderVO, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		try {
	
			con = DriverManager.getConnection(url, userid, passwd);
		
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
			pstmt.setString(14,groupOrderVO.getGroupID());
			pstmt.executeUpdate();
			
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

	@Override
	public void insert2(List<GroupOrderVO> list, Connection con) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GroupOrderVO> GET_ONE_groupid__state_men_id(String memid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getOneDriversAve(String driver_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UPDATEmemid__GROUP_ID_MEM_ID(String GROUP_ID, String Memid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String get_memid__memid_groupid(String memid, String groupid) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<String> get_group_id__start_time(String START_TIME, String START_TIME2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMemID_groupID_startTime(String groupID, String START_TIME, String START_TIME2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UPDATE_STATE__GROUP_ID(Integer STATE, String GROUP_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashSet<String> getRatedDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState_GroupID_mem_ID(String GROUP_ID, Integer state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getstateGrouID_Memid_Notnull(String groupID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UPDATE_Total_AmoutGroupIDState(Integer TotalAmout, String GroupID, Integer State) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public List<GroupOrderVO> getByStateAndOrderType(Integer state, Integer orderType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateDriverIDByGroupID(String driverID, String groupID) {
        // TODO Auto-generated method stub
        
    }

	@Override
	public Timestamp getStartTimeGgroupID(String groupID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDelayOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupOrderVO> getALL_GroupID_State(String groupid, Integer state) {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Override
    public List<GroupOrderVO> getByStateAndDriverID(Integer state, String driverID) {
        // TODO Auto-generated method stub
        return null;
    }



}
