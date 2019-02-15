package com.groupBand.model;

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
	private static final String INSERT_STMT = "INSERT INTO GroupBand (deptno,dname,loc) VALUES (dept2_seq.NEXTVAL, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT deptno , dname, loc FROM GroupBand";
	private static final String GET_ONE_STMT = "SELECT deptno , dname, loc FROM GroupBand where deptno = ?";
	private static final String GET_Emps_ByDeptno_STMT = "SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM GroupBand where deptno = ? order by empno";
	
	private static final String DELETE_EMPs = "DELETE FROM GroupBand where deptno = ?";
	private static final String DELETE_DEPT = "DELETE FROM GroupBand where deptno = ?";	
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

		pstmt.setString(1, groupBandVO.getGroupID());
		pstmt.setString(2, groupBandVO.getContent());
		pstmt.setDate(3, groupBandVO.getLaunchTime());
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
	public void delete(Integer groupBandno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, groupBandno);
		

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
	public GroupBandVO findByPrimaryKey(Integer groupBandno) {
		// TODO Auto-generated method stu
		return null;
	}

	@Override
	public List<GroupBandVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
