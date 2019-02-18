package com.broadcast.model;

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

public class BroadcastJNDIDAO implements BroadcastDAO_interface {

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
			"INSERT INTO BROADCAST(MSG_ID,MEM_ID,MESSAGE,CONFIRMED) VALUES(?,?,?,?)";
	private static final String GET_ONE_STMT = 
			"SELECT MSG_ID,MEM_ID,MESSAGE,CONFIRMED FROM BROADCAST WHERE MSG_ID=?";
	private static final String GET_ALL_STMT = 
			"SELECT MSG_ID,MEM_ID,MESSAGE,CONFIRMED FROM BROADCAST ORDER BY MSG_ID";
	private static final String DELETE = 
			"DELETE FROM BROADCAST WHERE MSG_ID=?";
	private static final String UPDATE = 
			"UPDATE BROADCAST SET MEM_ID=? ,MESSAGE=? ,CONFIRMED=?  WHERE MSG_ID=?";

	@Override
	public void insert(BroadcastVO broadcastVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, broadcastVO.getMsgID());
			pstmt.setString(2, broadcastVO.getMemID());
			pstmt.setString(3, broadcastVO.getMessage());
			pstmt.setInt(4, broadcastVO.getConfirmed());
			pstmt.executeUpdate();
//			deal driver errors
		} catch (SQLException se) {
			throw new RuntimeException("SQL error" + se.getMessage());
//		close jdbc resource
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
	public void update(BroadcastVO broadcastVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, broadcastVO.getMemID());
			pstmt.setString(2, broadcastVO.getMessage());
			pstmt.setInt(3, broadcastVO.getConfirmed());
			// PK MsgID
			pstmt.setString(4, broadcastVO.getMsgID());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("SQL errors" + se.getMessage());
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
	public void delete(String msgID) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			/* 根據SQL常數，pstmt需要的值 */

			pstmt.setString(1, msgID);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("SQL error" + se.getMessage());
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
	public BroadcastVO findByPrimaryKey(String msgID) {

		BroadcastVO broadcastVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			/* 根據SQL常數，pstmt需要的值 */
			/* parameterIndex 從1開始 */
			pstmt.setString(1, msgID);
			/* 進行一筆資料的查詢 */
			rs = pstmt.executeQuery();

			while (rs.next()) {

				broadcastVO = new BroadcastVO();
				broadcastVO.setMsgID(rs.getString("MSG_ID"));
				broadcastVO.setMemID(rs.getString("MEM_ID"));
				broadcastVO.setMessage(rs.getString("MESSAGE"));
				broadcastVO.setConfirmed(rs.getInt("CONFIRMED"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("發生SQL errors" + se.getMessage());
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
		return broadcastVO;
	}

	@Override
	public List<BroadcastVO> getAll() {


		List<BroadcastVO> list = new ArrayList<BroadcastVO>();
		BroadcastVO broadcastVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			/* 進行全部資料的查詢 */
			rs = pstmt.executeQuery();
			while (rs.next()) {

				broadcastVO = new BroadcastVO();
				broadcastVO.setMsgID(rs.getString("MSG_ID"));
				broadcastVO.setMemID(rs.getString("MEM_ID"));
				broadcastVO.setMessage(rs.getString("MESSAGE"));
				broadcastVO.setConfirmed(rs.getInt("CONFIRMED"));

				list.add(broadcastVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("發生SQL errors" + se.getMessage());
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
		return list;
	}

}
