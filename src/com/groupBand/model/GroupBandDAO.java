package com.groupBand.model;

//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.groupOrder.model.GroupOrderDAO;
import com.groupOrder.model.GroupOrderVO;

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

	private static final String INSERT_STMT = "INSERT INTO GROUP_BAND (GROUP_ID,CONTENT,LAUNCH_TIME,INTRODUCTION,GROUP_STATUS,CURRENT_NUM,UPPER_LIMIT,LOWER_LIMIT,GROUP_NAME,GROUP_LEADER,START_LOC,END_LOC,PRIVATES,PHOTO,GROUP_TYPE,TOTAL_AMOUT,START_TIME,RATE,NOTE,GROUP_KIND) VALUES('G'||LPAD(to_char(GROUP_BAND_SEQ.NEXTVAL),3,'0'),?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT GROUP_ID,CONTENT,LAUNCH_TIME,INTRODUCTION,GROUP_STATUS,CURRENT_NUM,UPPER_LIMIT,LOWER_LIMIT,GROUP_NAME,GROUP_LEADER,START_LOC,END_LOC,PRIVATES,PHOTO,GROUP_TYPE,TOTAL_AMOUT,START_TIME,RATE,NOTE,GROUP_KIND FROM GROUP_BAND";
	private static final String GET_ONE_STMT = "SELECT GROUP_ID,CONTENT,LAUNCH_TIME,INTRODUCTION,GROUP_STATUS,CURRENT_NUM,UPPER_LIMIT,LOWER_LIMIT,GROUP_NAME,GROUP_LEADER,START_LOC,END_LOC,PRIVATES,PHOTO,GROUP_TYPE,TOTAL_AMOUT,START_TIME,RATE,NOTE,GROUP_KIND FROM GROUP_BAND where GROUP_ID = ?";
	private static final String DELETE = "DELETE FROM GROUP_BAND where GROUP_ID = ?";
	private static final String UPDATE = "UPDATE GROUP_BAND set GROUP_ID=?,CONTENT=?,LAUNCH_TIME=?,INTRODUCTION=?,GROUP_STATUS=?,CURRENT_NUM=?,UPPER_LIMIT=?,LOWER_LIMIT=?,GROUP_NAME=?,GROUP_LEADER=?,START_LOC=?,END_LOC=?,PRIVATES=?,PHOTO=?,GROUP_TYPE=?,TOTAL_AMOUT=?,START_TIME=?,RATE=?,NOTE=?,GROUP_KIND=? where GROUP_ID= ?";
	private static final String UPDATECURRENT = "UPDATE GROUP_BAND set CURRENT_NUM= ? where GROUP_ID= ?";
	private static final String GET_ALL = "SELECT * FROM GROUP_BAND where GROUP_STATUS = ?";
	private static final String UPDATE_GROUP_STATUS__GROUP_ID = "UPDATE  GROUP_BAND set  GROUP_STATUS= ?  where GROUP_ID = ?";
	
	private static final String UPDATE_GROUP_TYPE_GROUP_NAME_INTRODUCTION_NOTE_PHOTO__GROUP_ID= "UPDATE GROUP_BAND set GROUP_TYPE= ? , GROUP_NAME=? , INTRODUCTION=? , NOTE=?, PHOTO=? where GROUP_ID= ?";

	//聊天室
	private static final String GET_ONE_CONTENT__GROUP_ID = "SELECT CONTENT FROM GROUP_BAND where GROUP_ID= ?";
	private static final String UPDATE_CONTENT__GROUP_ID= "UPDATE GROUP_BAND set CONTENT=? where GROUP_ID= ?";
	private static final String GET_ONE_TOTAL_AMOUT__GROUP_ID = "SELECT TOTAL_AMOUT FROM GROUP_BAND where GROUP_ID= ?";
	private static final String GET_ALL_START_TIME = "select * from GROUP_BAND where GROUP_STATUS=0 and rownum<6 ORDER BY START_TIME";
	@Override
	public void insert(GroupBandVO groupBandVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
//			con.setAutoCommit(true);
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
			pstmt.setInt(18, groupBandVO.getGroupKind());
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
			pstmt.setInt(20, groupBandVO.getGroupKind());
			pstmt.setString(21, groupBandVO.getGroupID());
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
				groupBandVO.setGroupKind(rs.getInt("GROUP_KIND"));
			
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
				groupBandVO.setGroupKind(rs.getInt("GROUP_KIND"));
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

	@Override
	public List<GroupBandVO> getAllStartTime() {
		// TODO Auto-generated method stub
		
		List<GroupBandVO> list = new ArrayList<GroupBandVO>();
		GroupBandVO groupBandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_START_TIME);
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
				groupBandVO.setGroupKind(rs.getInt("GROUP_KIND"));
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
	
	@Override	
	public String getOneContentGroupID(String groupID) {
		// TODO Auto-generated method stub
		String content=null;
		GroupBandVO groupBandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CONTENT__GROUP_ID);
			pstmt.setString(1,groupID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				content =rs.getString("CONTENT");
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

		return content;
	}

	@Override	
	public int getOneTotalAmoutGroupID(String groupID) {
		// TODO Auto-generated method stub
		int totalAmout=0;
		GroupBandVO groupBandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_TOTAL_AMOUT__GROUP_ID);
			pstmt.setString(1,groupID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totalAmout =rs.getInt("TOTAL_AMOUT");
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

		return totalAmout;
	}
	
	@Override
	public List<GroupBandVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		List<GroupBandVO> list = new ArrayList<GroupBandVO>();
		GroupBandVO groupBandVO =null;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con =ds.getConnection();
			String finalSQL = "select GROUP_ID,LAUNCH_TIME,INTRODUCTION,CURRENT_NUM,UPPER_LIMIT,LOWER_LIMIT,GROUP_NAME,GROUP_LEADER,START_LOC, END_LOC, PRIVATES, PHOTO, GROUP_TYPE, TOTAL_AMOUT, START_TIME,GROUP_KIND from GROUP_BAND"
			+jdbcUtil_CompositeQuery_GROUP_BAND.get_WhereCondition(map)
			+ " and GROUP_STATUS=0 order by LAUNCH_TIME";
			
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				groupBandVO = new GroupBandVO();
				groupBandVO.setGroupID(rs.getString("GROUP_ID"));
				groupBandVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
				groupBandVO.setIntroduction(rs.getString("INTRODUCTION"));				
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
				groupBandVO.setGroupKind(rs.getInt("GROUP_KIND"));
				list.add(groupBandVO);
			
			}
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void insertWithEmps(GroupBandVO groupBandVO, List<GroupOrderVO> list) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
//			con.setAutoCommit(true);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);
			String cols[] = {"GROUP_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);	
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
			pstmt.setInt(18, groupBandVO.getGroupKind());
			pstmt.executeUpdate();
			String next_deptno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			System.out.println("自增主鍵值= " + next_deptno +"(剛新增成功的部門編號)");
			if (rs.next()) {//下一筆
				next_deptno = rs.getString(1);
				System.out.println("自增主鍵值= " + next_deptno +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			GroupOrderDAO dao = new GroupOrderDAO();
			System.out.println("list.size()-A="+list.size());
			
			for (GroupOrderVO groupOrderVO : list) {				
				groupOrderVO.setGroupID(next_deptno) ;				
						
			}
			dao.insert2(list,con);		
			System.out.println("++++++++++++++++++++++++++++++++++++++++");
				
			// 2●設定於 pstm.executeUpdate()之後



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
	
	public void UPDATE_GROUP_TYPE_GROUP_NAME_INTRODUCTION_NOTE_PHOTO__GROUP_ID(String groupID,String GROUP_TYPE,String GROUP_NAME,String INTRODUCTION,String NOTE,byte[] PHOTO ) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(UPDATE_GROUP_TYPE_GROUP_NAME_INTRODUCTION_NOTE_PHOTO__GROUP_ID);
			
			pstmt.setString(1,GROUP_TYPE);
			pstmt.setString(2,GROUP_NAME);
			pstmt.setString(3,INTRODUCTION);
			pstmt.setString(4,NOTE);
			pstmt.setBytes(5,PHOTO);
			pstmt.setString(6,groupID);
			
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
	public void UPDATE_CONTENT__GROUP_ID(String groupID,String content ) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(UPDATE_CONTENT__GROUP_ID);
			
			pstmt.setString(1,content);
			pstmt.setString(2,groupID);
			
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
	
	public void UpdateCURRENT(Integer currenTnum,String groupBandno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(UPDATECURRENT);
			pstmt.setInt(1,currenTnum);
			pstmt.setString(2,groupBandno);
			
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
	
	
	public List<GroupBandVO> GETALL(Integer groupStatus) {
		// TODO Auto-generated method stub
		List<GroupBandVO> list = new ArrayList<GroupBandVO>();
		GroupBandVO groupBandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			pstmt.setInt(1, groupStatus);
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
				groupBandVO.setGroupKind(rs.getInt("GROUP_KIND"));
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
	
	public void UPDATE_GROUP_STATUS__GROUP_ID(Integer groupStatus,String groupID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
			pstmt = con.prepareStatement(UPDATE_GROUP_STATUS__GROUP_ID);
			pstmt.setInt(1,groupStatus);
			pstmt.setString(2,groupID);
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
}
