package com.storeRecord.model;

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

public class StoreRecordDAO implements StoreRecordDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO RATE (RECORD_ID, MEM_ID, SAVE_DATE, AMOUNT) VALUES"
			+ "(?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE RATE SET  RECORD_ID = ?, MEM_ID = ?, SAVE_DATE = ?  "
			+ "WHERE RECORD_ID = ?";
	private static final String DELETE = "DELETE FROM RATE WHERE RECORD_ID = ? ";
	private static final String GET_ONE_STMT = "SELECT RECORD_ID, MEM_ID, SAVE_DATE, AMOUNT FROM STORE_RECORD WHERE RECORD_ID= ?";
	private static final String GET_ALL_STMT = "SELECT RECORD_ID, MEM_ID, SAVE_DATE, AMOUNT FROM STORE_RECORD ORDER BY RECORD_ID";

	@Override
	public void insert(StoreRecordVO srVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, srVO.getRecordID());
			pstmt.setString(2, srVO.getMemID());
			pstmt.setDate(3, srVO.getSaveDate());
			pstmt.setInt(4, srVO.getAmount());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("SQL錯誤: " + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(StoreRecordVO srVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(2, srVO.getMemID());
			pstmt.setDate(3, srVO.getSaveDate());
			pstmt.setInt(4, srVO.getAmount());
			pstmt.setString(1, srVO.getRecordID());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("SQL錯誤: " + se.getMessage());

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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String recordID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, recordID);

		} catch (SQLException se) {
			throw new RuntimeException("SQL錯誤: " + se.getMessage());

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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public StoreRecordVO findByPrimaryKey(String recordID) {

		StoreRecordVO srVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, recordID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				srVO = new StoreRecordVO();
				srVO.setRecordID(rs.getString("RECORD_ID"));
				srVO.setMemID(rs.getString("MEM_ID"));
				srVO.setSaveDate(rs.getDate("SAVE_DATE"));
				srVO.setAmount(rs.getInt("AMOUNT"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("SQL錯誤: " + se.getMessage());

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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return srVO;
	}

	@Override
	public List<StoreRecordVO> getAll() {

		List<StoreRecordVO> list = new ArrayList();
		StoreRecordVO srVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				srVO = new StoreRecordVO();
				srVO.setRecordID(rs.getString("RECORD_ID"));
				srVO.setMemID(rs.getString("MEM_ID"));
				srVO.setSaveDate(rs.getDate("SAVE_DATE"));
				srVO.setAmount(rs.getInt("AMOUNT"));
				list.add(srVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("SQL錯誤: " + se.getMessage());

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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

}
