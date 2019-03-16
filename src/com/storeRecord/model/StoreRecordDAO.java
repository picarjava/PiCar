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

	private static final String INSERT_STMT = "INSERT INTO STORE_RECORD (STORE_ID, MEM_ID, SAVE_DATE, AMOUNT) VALUES"
			+ "(TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(STO_SEQ.NEXTVAL), 3, '0'), ?, SYSDATE, ?)";
	private static final String UPDATE_STMT = "UPDATE RATE SET  STORE_ID = ?, MEM_ID = ?, SAVE_DATE = ?  "
			+ "WHERE STORE_ID = ?";
	private static final String DELETE = "DELETE FROM STORE_RECORD WHERE STORE_ID = ? ";
	private static final String GET_ONE_STMT = "SELECT STORE_ID, MEM_ID, SAVE_DATE, AMOUNT FROM STORE_RECORD WHERE STORE_ID= ?";
	private static final String GET_MEM_STMT = "SELECT STORE_ID, MEM_ID, SAVE_DATE, AMOUNT FROM STORE_RECORD WHERE MEM_ID= ?";
	private static final String GET_ALL_STMT = "SELECT STORE_ID, MEM_ID, SAVE_DATE, AMOUNT FROM STORE_RECORD ORDER BY STORE_ID";
	private static final String GET_MEM_ID_STMT_DISTINCT = "SELECT  DISTINCT MEM_ID FROM STORE_RECORD ORDER BY MEM_ID";

	private static final String GET_AMOUT_MEM = "SELECT SUM (AMOUNT) FROM STORE_RECORD WHERE MEM_ID=?";

	@Override
	public void insert(StoreRecordVO srVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, srVO.getStoreID());
			pstmt.setString(1, srVO.getMemID());
//			pstmt.setTimestamp(3, srVO.getSaveDate());
			pstmt.setInt(2, srVO.getAmount());

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
			pstmt.setTimestamp(3, srVO.getSaveDate());
			pstmt.setInt(4, srVO.getAmount());
			pstmt.setString(1, srVO.getStoreID());

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
	public void delete(String storeID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, storeID);

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
	public StoreRecordVO findByPrimaryKey(String storeID) {

		StoreRecordVO srVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, storeID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				srVO = new StoreRecordVO();
				srVO.setStoreID(rs.getString("STORE_ID"));
				srVO.setMemID(rs.getString("MEM_ID"));
				srVO.setSaveDate(rs.getTimestamp("SAVE_DATE"));
				System.out.println(rs.getTimestamp("SAVE_DATE"));
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
	public List<StoreRecordVO> findByMemID(String memID) {
		List<StoreRecordVO> list = new ArrayList();
		StoreRecordVO srVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_STMT);
			pstmt.setString(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				srVO = new StoreRecordVO();
				srVO.setStoreID(rs.getString("STORE_ID"));
				srVO.setMemID(rs.getString("MEM_ID"));
				srVO.setSaveDate(rs.getTimestamp("SAVE_DATE"));
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
				srVO.setStoreID(rs.getString("STORE_ID"));
				srVO.setMemID(rs.getString("MEM_ID"));
				srVO.setSaveDate(rs.getTimestamp("SAVE_DATE"));
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

	@Override
	public List<StoreRecordVO> getDistinctMemID() {
		List<StoreRecordVO> list = new ArrayList();
		StoreRecordVO srVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_ID_STMT_DISTINCT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				srVO = new StoreRecordVO();
				srVO.setMemID(rs.getString("MEM_ID"));
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

//	public Integer getSumAmount(String memID) {
//
//		Integer sumAmount = new Integer(0);
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_AMOUT_MEM);
//			pstmt.setString(1, memID);
//			rs = pstmt.executeQuery();
//
//			rs.next();
//			sumAmount = rs.getInt(1);
//
//		} catch (SQLException se) {
//			throw new RuntimeException("SQL錯誤: " + se.getMessage());
//
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//		}
//
//		return sumAmount;
//
//	}

}
