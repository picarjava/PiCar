package com.groupBand.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class GroupBandDAO implements GroupBandDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO GROUP_BAND (GROUP_ID,CONTENT,LAUNCH_TIME,INTRODUCTION,GROUP_STATUS,CURRENT_NUM,UPPER_LIMIT,LOWER_LIMIT,GROUP_NAME,GROUP_LEADER,START_LOC,END_LOC,PRIVATES,PHOTO,GROUP_TYPE,TOTAL_AMOUT,START_TIME,RATE,NOTE) VALUES('G'||LPAD(to_char(GROUP_BAND_SEQ.NEXTVAL),3,'0'),?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String GET_ALL_STMT = "SELECT deptno , dname, loc FROM GroupBand";
	private static final String GET_ONE_STMT = "SELECT deptno , dname, loc FROM GroupBand where deptno = ?";
	
	private static final String DELETE = 
			"DELETE FROM emp2 where empno = ?";
	private static final String UPDATE = "UPDATE GroupBand set dname=?, loc=? where deptno = ?";

	@Override
	public void insert(GroupBandVO groupBandVO) {
		// TODO Auto-generated method 
		Connection con = null;
		PreparedStatement pstmt =null;
		try {
		con = ds.getConnection();
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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

	
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, groupBandno);
		

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public GroupBandVO findByPrimaryKey(String groupBandno) {
		// TODO Auto-generated method stu
		return null;
	}

	@Override
	public List<GroupBandVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
