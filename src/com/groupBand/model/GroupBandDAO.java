package com.groupBand.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GroupBandDAO implements GroupBandDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GROUP_BAND (GROUP_ID,CONTENT,LAUNCH_TIME,INTRODUCTION,GROUP_STATUS,CURRENT_NUM,UPPER_LIMIT,LOWER_LIMIT,GROUP_NAME,GROUP_LEADER,START_LOC,END_LOC,PRIVATES,PHOTO,GROUP_TYPE,TOTAL_AMOUT,START_TIME,RATE,NOTE) VALUES('G'||LPAD(to_char(GROUP_BAND_SEQ.NEXTVAL),3,'0'),?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT GROUP_ID,CONTENT,LAUNCH_TIME,INTRODUCTION,GROUP_STATUS,CURRENT_NUM,UPPER_LIMIT,LOWER_LIMIT,GROUP_NAME,GROUP_LEADER,START_LOC,END_LOC,PRIVATES,PHOTO,GROUP_TYPE,TOTAL_AMOUT,START_TIME,RATE,NOTE FROM GROUP_BAND";
	private static final String GET_ONE_STMT = "SELECT GROUP_ID,CONTENT,LAUNCH_TIME,INTRODUCTION,GROUP_STATUS,CURRENT_NUM,UPPER_LIMIT,LOWER_LIMIT,GROUP_NAME,GROUP_LEADER,START_LOC,END_LOC,PRIVATES,PHOTO,GROUP_TYPE,TOTAL_AMOUT,START_TIME,RATE,NOTE FROM GROUP_BAND where GROUP_ID = ?";
	private static final String DELETE = "DELETE FROM GROUP_BAND where GROUP_ID = ?";
	private static final String UPDATE = "UPDATE GROUP_BAND set GROUP_ID=?,CONTENT=?,LAUNCH_TIME=?,INTRODUCTION=?,GROUP_STATUS=?,CURRENT_NUM=?,UPPER_LIMIT=?,LOWER_LIMIT=?,GROUP_NAME=?,GROUP_LEADER=?,START_LOC=?,END_LOC=?,PRIVATES=?,PHOTO=?,GROUP_TYPE=?,TOTAL_AMOUT=?,START_TIME=?,RATE=?,NOTE=? where GROUP_ID= ?";

	@Override
	public void insert(GroupBandVO groupBandVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, groupBandVO.getContent());
			pstmt.setString(2, groupBandVO.getIntroduction());
			pstmt.setInt(3, groupBandVO.getGroupStatus());
			pstmt.setInt(4, groupBandVO.getCurrenTnum());
			pstmt.setInt(5, groupBandVO.getUpperLimit());
			pstmt.setInt(6, groupBandVO.getLowerLimit());
			pstmt.setString(7, groupBandVO.getGroupName());
			pstmt.setString(8, groupBandVO.getGroupLeader());
			pstmt.setString(9, groupBandVO.getStartLoc());
			pstmt.setString(10, groupBandVO.getEndLoc());
			pstmt.setInt(11, groupBandVO.getPrivates());
			pstmt.setBytes(12, groupBandVO.getPhoto());
			pstmt.setString(13, groupBandVO.getGroupType());
			pstmt.setInt(14, groupBandVO.getTotalAmout());
			pstmt.setTimestamp(15, groupBandVO.getStartTime());
			pstmt.setInt(16, groupBandVO.getRate());
			pstmt.setString(17, groupBandVO.getNote());

			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
//			 Clean up JDBC resources
//			se.printStackTrace();
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
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
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
			pstmt.setString(10, groupBandVO.getGroupLeader());
			pstmt.setString(11, groupBandVO.getStartLoc());
			pstmt.setString(12, groupBandVO.getEndLoc());
			pstmt.setInt(13, groupBandVO.getPrivates());
			pstmt.setBytes(14, groupBandVO.getPhoto());
			pstmt.setString(15, groupBandVO.getGroupType());
			pstmt.setInt(16, groupBandVO.getTotalAmout());
			pstmt.setTimestamp(17, groupBandVO.getStartTime());
			pstmt.setInt(18, groupBandVO.getRate());
			pstmt.setString(19, groupBandVO.getNote());
			pstmt.setString(20, groupBandVO.getGroupID());
			pstmt.executeUpdate();
			con.commit();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, groupBandno);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public GroupBandVO findByPrimaryKey(String groupBandno) {
		// TODO Auto-generated method stub
		GroupBandVO groupBandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, groupBandno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBandVO = new GroupBandVO();
				groupBandVO.setGroupID(rs.getString("GROUP_ID"));
				groupBandVO.setContent(rs.getString("CONTENT"));
				groupBandVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
				groupBandVO.setIntroduction(rs.getString("INTRODUCTION"));
				groupBandVO.setGroupStatus(rs.getInt("GROUP_STATUS"));
				groupBandVO.setCurrenTnum(rs.getInt("CURRENT_NUM"));
				groupBandVO.setUpperLimit(rs.getInt("UPPER_LIMIT"));
				groupBandVO.setLowerLimit(rs.getInt("LOWER_LIMIT"));
				groupBandVO.setGroupName(rs.getString("GROUP_NAME"));
				groupBandVO.setGroupLeader(rs.getString("GROUP_LEADER"));
				groupBandVO.setStartLoc(rs.getString("START_LOC"));
				groupBandVO.setEndLoc(rs.getString("END_LOC"));
				groupBandVO.setPrivates(rs.getInt("PRIVATES"));
				groupBandVO.setPhoto(rs.getBytes("PHOTO"));
				groupBandVO.setGroupType(rs.getString("GROUP_TYPE"));
				groupBandVO.setTotalAmout(rs.getInt("TOTAL_AMOUT"));
				groupBandVO.setStartTime(rs.getTimestamp("START_TIME"));
				groupBandVO.setRate(rs.getInt("RATE"));
				groupBandVO.setNote(rs.getString("NOTE"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return groupBandVO;
	}

	@Override
	public List<GroupBandVO> getAll() {
		// TODO Auto-generated method stub
		List<GroupBandVO> list = new ArrayList<GroupBandVO>();
		GroupBandVO groupBandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				groupBandVO = new GroupBandVO();
				groupBandVO.setGroupID(rs.getString("GROUP_ID"));
				groupBandVO.setContent(rs.getString("CONTENT"));
				groupBandVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
				groupBandVO.setIntroduction(rs.getString("INTRODUCTION"));
				groupBandVO.setGroupStatus(rs.getInt("GROUP_STATUS"));
				groupBandVO.setCurrenTnum(rs.getInt("CURRENT_NUM"));
				groupBandVO.setUpperLimit(rs.getInt("UPPER_LIMIT"));
				groupBandVO.setLowerLimit(rs.getInt("LOWER_LIMIT"));
				groupBandVO.setGroupName(rs.getString("GROUP_NAME"));
				groupBandVO.setGroupLeader(rs.getString("GROUP_LEADER"));
				groupBandVO.setStartLoc(rs.getString("START_LOC"));
				groupBandVO.setEndLoc(rs.getString("END_LOC"));
				groupBandVO.setPrivates(rs.getInt("PRIVATES"));
				groupBandVO.setPhoto(rs.getBytes("PHOTO"));
				groupBandVO.setGroupType(rs.getString("GROUP_TYPE"));
				groupBandVO.setTotalAmout(rs.getInt("TOTAL_AMOUT"));
				groupBandVO.setStartTime(rs.getTimestamp("START_TIME"));
				groupBandVO.setRate(rs.getInt("RATE"));
				groupBandVO.setNote(rs.getString("NOTE"));
				list.add(groupBandVO);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
