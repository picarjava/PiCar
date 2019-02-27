package com.paymentRecord.model;

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

import com.rate.model.RateVO;

public class PaymentRecordDAO implements PaymentRecordDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO PAYMENT_RECORD (PAYMENT_ID, DRIVER_ID, PAY_AMOUNT, PAY_TIME) VALUES"
			+ "(TO_CHAR(SYSDATE,'YYYYMM')||'-'||LPAD(TO_CHAR(PAY_SEQ.NEXTVAL), 3, '0'), ?, ?, SYSDATE)";
	private static final String UPDATE_STMT = "UPDATE RATE SET  DRIVER_ID = ?, PAY_AMOUNT = ?, PAY_TIME = ?  "
			+ "WHERE RATE_ID = ?";
	private static final String DELETE = "DELETE FROM PAYMENT_RECORD WHERE PAYMENT_ID = ? ";
	private static final String GET_ONE_STMT = "SELECT PAYMENT_ID, DRIVER_ID, PAY_AMOUNT, PAY_TIME FROM PAYMENT_RECORD WHERE PAYMENT_ID= ?";
	private static final String GET_DRIVER_STMT = "SELECT PAYMENT_ID, DRIVER_ID, PAY_AMOUNT, PAY_TIME FROM PAYMENT_RECORD WHERE DRIVER_ID= ?";
	private static final String GET_ALL_STMT = "SELECT PAYMENT_ID, DRIVER_ID, PAY_AMOUNT, PAY_TIME FROM PAYMENT_RECORD ORDER BY PAYMENT_ID";
	private static final String GET_DRIVER_ID_STMT_DISTINCT = "SELECT  DISTINCT DRIVER_ID FROM PAYMENT_RECORD ORDER BY DRIVER_ID";
	
	
	@Override
	public void insert(PaymentRecordVO prVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, prVO.getPaymentID()); 日期自動產生 暫時註解
			pstmt.setString(1, prVO.getDriverID());
			pstmt.setInt(2, prVO.getPayAmount());
//			pstmt.setDate(4, prVO.getPayTime()); 日期自動產生 暫時註解

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
	public void update(PaymentRecordVO prVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, prVO.getDriverID());
			pstmt.setInt(2, prVO.getPayAmount());
			pstmt.setDate(3, prVO.getPayTime());
			pstmt.setString(4, prVO.getPaymentID());

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
	public void delete(String paymentID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, paymentID);

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
	public PaymentRecordVO findByPrimaryKey(String paymentID) {

		PaymentRecordVO prVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, paymentID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				prVO = new PaymentRecordVO();
				prVO.setPaymentID(rs.getString("PAYMENT_ID"));
				prVO.setDriverID(rs.getString("DRIVER_ID"));
				prVO.setPayAmount(rs.getInt("PAY_AMOUNT"));
				prVO.setPayTime(rs.getDate("PAY_TIME"));
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

		return prVO;
	}
	

	@Override
	public List<PaymentRecordVO> findByDriverID(String driverID) {
		
		List<PaymentRecordVO> list = new ArrayList();
		PaymentRecordVO prVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DRIVER_STMT);

			pstmt.setString(1, driverID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				prVO = new PaymentRecordVO();
				prVO.setPaymentID(rs.getString("PAYMENT_ID"));
				prVO.setDriverID(rs.getString("DRIVER_ID"));
				prVO.setPayAmount(rs.getInt("PAY_AMOUNT"));
				prVO.setPayTime(rs.getDate("PAY_TIME"));
				list.add(prVO);
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
	public List<PaymentRecordVO> getAll() {

		List<PaymentRecordVO> list = new ArrayList();
		PaymentRecordVO prVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prVO = new PaymentRecordVO();
				prVO.setPaymentID(rs.getString("PAYMENT_ID"));
				prVO.setDriverID(rs.getString("DRIVER_ID"));
				prVO.setPayAmount(rs.getInt("PAY_AMOUNT"));
				prVO.setPayTime(rs.getDate("PAY_TIME"));
				list.add(prVO);
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
	public List<PaymentRecordVO> getDistinctDriverID() {
		List<PaymentRecordVO> list = new ArrayList();
		PaymentRecordVO prVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DRIVER_ID_STMT_DISTINCT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prVO = new PaymentRecordVO();			
				prVO.setDriverID(rs.getString("DRIVER_ID"));			
				list.add(prVO);
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
