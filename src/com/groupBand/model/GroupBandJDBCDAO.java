package com.groupBand.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GroupBandJDBCDAO implements GroupBandDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO GROUP_BAND (GROUP_ID,CONTENT,LAUNCH_TIME,INTRODUCTION,GROUP_STATUS,CURRENT_NUM,UPPER_LIMIT,LOWER_LIMIT,GROUP_NAME,GROUP_LEADER,START_LOC,END_LOC,PRIVATES,PHOTO,GROUP_TYPE,TOTAL_AMOUT,START_TIME,RATE,NOTE) VALUES('G'||LPAD(to_char(GROUP_BAND_SEQ.NEXTVAL),3,'0'),?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT GORDER_ID ,DRIVER_ID, MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE FROM GROUP_ORDER";
		private static final String GET_ONE_STMT = 
			"SELECT GORDER_ID ,DRIVER_ID, MEM_ID,STATE,TOTAL_AMOUT,LAUNCH_TIME,START_TIME,END_TIME,START_LNG,START_LAT,END_LNG,END_LAT,ORDER_TYPE,RATE,NOTE FROM GROUP_ORDER where GORDER_ID = ?";
		private static final String DELETE = 
			"DELETE FROM GROUP_ORDER where GORDER_ID = ?";
		private static final String UPDATE = 
			"UPDATE GROUP_BAND set GROUP_ID=?,CONTENT=?,LAUNCH_TIME=?,INTRODUCTION=?,GROUP_STATUS=?,CURRENT_NUM=?,UPPER_LIMIT=?,LOWER_LIMIT=?,GROUP_NAME=?,GROUP_LEADER=?,START_LOC=?,END_LOC=?,PRIVATES=?,PHOTO=?,GROUP_TYPE=?,TOTAL_AMOUT=?,START_TIME=?,RATE=?,NOTE=? where GROUP_ID= ?";

	@Override
	public void insert(GroupBandVO groupBandVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, groupBandVO.getContent());		
			pstmt.setString(2, groupBandVO.getIntroduction());
			pstmt.setInt(3, groupBandVO.getGroupStatus());
			pstmt.setInt(4, groupBandVO.getCurrenTnum());
			pstmt.setInt(5, groupBandVO.getUpperLimit());
			pstmt.setInt(6, groupBandVO.getLowerLimit());
			pstmt.setString(7, groupBandVO.getGroupName());
			pstmt.setInt(8, groupBandVO.getGroupLeader());
			pstmt.setString(9, groupBandVO.getStartLoc());
			pstmt.setString(10, groupBandVO.getEndLoc());
			pstmt.setInt(11, groupBandVO.getPrivates());
			pstmt.setBytes(12, groupBandVO.getPhoto());
			pstmt.setString(13, groupBandVO.getGroupType());
			pstmt.setInt(14, groupBandVO.getTotalAmout());
			pstmt.setDate(15, groupBandVO.getStartTime());
			pstmt.setInt(16, groupBandVO.getRate());
			pstmt.setString(17, groupBandVO.getNote());
			
			pstmt.executeUpdate();
			con.commit();
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
	public void update(GroupBandVO groupBandVO) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, groupBandVO.getGroupID());	
			pstmt.setString(2, groupBandVO.getContent());
			pstmt.setTimestamp(3, groupBandVO.getLaunchTime());
			pstmt.setString(4, groupBandVO.getIntroduction());
			pstmt.setInt(5, groupBandVO.getGroupStatus());
			pstmt.setInt(6, groupBandVO.getCurrenTnum());
			pstmt.setInt(7, groupBandVO.getUpperLimit());
			pstmt.setInt(8, groupBandVO.getLowerLimit());
			pstmt.setString(9, groupBandVO.getGroupName());
			pstmt.setInt(10, groupBandVO.getGroupLeader());
			pstmt.setString(11, groupBandVO.getStartLoc());
			pstmt.setString(12, groupBandVO.getEndLoc());
			pstmt.setInt(13, groupBandVO.getPrivates());
			pstmt.setBytes(14, groupBandVO.getPhoto());
			pstmt.setString(15, groupBandVO.getGroupType());
			pstmt.setInt(16, groupBandVO.getTotalAmout());
			pstmt.setDate(17, groupBandVO.getStartTime());
			pstmt.setInt(18, groupBandVO.getRate());
			pstmt.setString(19, groupBandVO.getNote());
			pstmt.setString(20, groupBandVO.getGroupID());
			pstmt.executeUpdate();
			con.commit();
			
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
	public void delete(String groupBandno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GroupBandVO findByPrimaryKey(String groupBandno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupBandVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
