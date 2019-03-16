package com.groupMem.model;

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



public class GroupMemDAO implements GroupMemDAO_interface {
	
private static DataSource ds =null;
static {
	try {
		Context ctx = new InitialContext();
		ds= (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e) {
			e.printStackTrace();//當例外發生而被捕捉後，你可以呼叫printStackTrace()在主控台（Console）顯示堆疊追蹤：
		}
	}
private static final String INSERT_STMT = "INSERT INTO GROUP_MEM (GROUP_ID,MEM_ID,STATE) VALUES(?,?,?)";
private static final String GET_ALL_STMT = "SELECT GROUP_ID ,MEM_ID, STATE FROM GROUP_MEM";
private static final String GET_ONE_STMT = "SELECT GROUP_ID ,MEM_ID, STATE FROM GROUP_MEM where GROUP_ID = ?";
private static final String DELETE = "DELETE FROM GROUP_MEM where GROUP_ID = ?";
private static final String UPDATE = "UPDATE GROUP_MEM set GROUP_ID=?, MEM_ID=?, STATE=? where GROUP_ID =?";

private static final String UPDATE_STATE = "UPDATE GROUP_MEM set STATE=? where GROUP_ID =? AND MEM_ID=?";
//判斷有沒有這揪團資料
private static final String GET_ONE_MEMID__ALL = "SELECT * FROM GROUP_MEM where GROUP_ID = ? AND MEM_ID=? AND STATE=?";

private static final String GET_ONE_MEMID__GROUP_ID_MEM_ID = "SELECT * FROM GROUP_MEM where GROUP_ID = ? AND MEM_ID=?";

@Override
public void insert( GroupMemVO  groupMemVO) {
	// TODO Auto-generated method stub
	Connection con =null;
	PreparedStatement pstmt = null;
	try {
		con=ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);

		pstmt.setString(1,groupMemVO.getGroupID());
		pstmt.setString(2,groupMemVO.getMemID());
		pstmt.setInt(3,groupMemVO.getState());
		
		pstmt.executeUpdate();
		
		
	}catch(SQLException se) {
		throw new RuntimeException("GroupMemDAO"
				+ se.getMessage());
		
}finally {
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
public void update( GroupMemVO  groupMemVO) {
	// TODO Auto-generated method stub
	Connection con =null;
	PreparedStatement pstmt = null;
	try {
		con=ds.getConnection();
		pstmt = con.prepareStatement(UPDATE);
		pstmt.setString(1,groupMemVO.getGroupID());
		pstmt.setString(2,groupMemVO.getMemID());
		pstmt.setInt(3,groupMemVO.getState());
		pstmt.setString(4,groupMemVO.getGroupID());
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
public void update_State(String groupID ,String memID, int state) {
	// TODO Auto-generated method stub
	Connection con =null;
	PreparedStatement pstmt = null;
	try {
		con=ds.getConnection();
		pstmt = con.prepareStatement(UPDATE_STATE);
		pstmt.setInt(1,state);
		pstmt.setString(2,groupID);
		pstmt.setString(3,memID);
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
public void delete(String groupMemno) {
	// TODO Auto-generated method stub
	Connection con =null;
	PreparedStatement pstmt = null;
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(DELETE);
		pstmt.setString(1, groupMemno);
		
		pstmt.executeUpdate();
	}catch (SQLException se) {
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
public  GroupMemVO findByPrimaryKey(String groupMemno) {
	// TODO Auto-generated method stub
	GroupMemVO groupMemVO =null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMT);
		pstmt.setString(1, groupMemno);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			groupMemVO = new GroupMemVO();	
			groupMemVO.setGroupID(rs.getString("GROUP_ID"));
			groupMemVO.setMemID(rs.getString("MEM_ID"));
			groupMemVO.setState(rs.getInt("STATE"));
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
	return groupMemVO;
}

@Override
public  GroupMemVO findone_memid__ALL(String groupID,String memID,int state) {
	// TODO Auto-generated method stub
	GroupMemVO groupMemVO =null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_MEMID__ALL);
		pstmt.setString(1, groupID);
		pstmt.setString(2, memID);
		pstmt.setInt(3, state);
		rs = pstmt.executeQuery();
		
			groupMemVO = new GroupMemVO();	
			while(rs.next()) {
			groupMemVO.setGroupID(rs.getString("GROUP_ID"));
			groupMemVO.setMemID(rs.getString("MEM_ID"));
			groupMemVO.setState(rs.getInt("STATE"));
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
	return groupMemVO;
}

@Override
public  GroupMemVO findone_memid__GROUP_ID_MEM_ID(String groupID,String memID) {
	// TODO Auto-generated method stub
	GroupMemVO groupMemVO =null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_MEMID__GROUP_ID_MEM_ID);
		pstmt.setString(1, groupID);
		pstmt.setString(2, memID);
		rs = pstmt.executeQuery();
		
			groupMemVO = new GroupMemVO();	
			while(rs.next()) {
			
			groupMemVO.setGroupID(rs.getString("GROUP_ID"));
			groupMemVO.setMemID(rs.getString("MEM_ID"));
			groupMemVO.setState(rs.getInt("STATE"));
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
	return groupMemVO;
}

@Override
public List<GroupMemVO> getAll() {
	List<GroupMemVO> list =new ArrayList<GroupMemVO>();;
	GroupMemVO groupMemVO =null;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			groupMemVO = new GroupMemVO();	
			groupMemVO.setGroupID(rs.getString("GROUP_ID"));
			groupMemVO.setMemID(rs.getString("MEM_ID"));
			groupMemVO.setState(rs.getInt("STATE"));
			list.add(groupMemVO);
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
	// TODO Auto-generated method stub
	return list;
}
}
