package com.rate.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RateDAO implements RateDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO RATE (RATE_ID, RATE_NAME, RATE_PRICE, RATE_BASIC) VALUES"
			+ "(?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE RATE SET  RATE_NAME = ?, RATE_PRICE = ?, RATE_BASIC = ?  "
			+ "WHERE RATE_ID = ?";
	private static final String DELETE = "DELETE FROM RATE WHERE RATE_ID = ? ";
	private static final String GET_ONE_STMT = "SELECT RATE_ID, RATE_NAME, RATE_PRICE, RATE_BASIC FROM RATE WHERE RATE_ID= ?";
	private static final String GET_ALL_STMT = "SELECT RATE_ID, RATE_NAME, RATE_PRICE, RATE_BASIC FROM RATE ORDER BY RATE_ID";

	@Override
	public void insert(RateVO rateVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rateVO.getRateID());
			pstmt.setString(2, rateVO.getRateName());
			pstmt.setDouble(3, rateVO.getRatePrice());
			pstmt.setInt(4, rateVO.getRateBasic());

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
	public void update(RateVO rateVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, rateVO.getRateName());
			pstmt.setDouble(2, rateVO.getRatePrice());
			pstmt.setInt(3, rateVO.getRateBasic());
			pstmt.setInt(4, rateVO.getRateID());

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
	public void delete(Integer rateID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rateID);

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
	public RateVO findByPrimaryKey(Integer rateID) {

		RateVO rateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rateID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				rateVO = new RateVO();
				rateVO.setRateID(rs.getInt("RATE_ID"));
				rateVO.setRateName(rs.getString("RATE_NAME"));
				rateVO.setRatePrice(rs.getDouble("RATE_PRICE"));
				rateVO.setRateBasic(rs.getInt("RATE_BASIC"));
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

		return rateVO;
	}

	@Override
	public List<RateVO> getAll() {

		List<RateVO> list = new ArrayList();
		RateVO rateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rateVO = new RateVO();
				rateVO.setRateID(rs.getInt("RATE_ID"));
				rateVO.setRateName(rs.getString("RATE_NAME"));
				rateVO.setRatePrice(rs.getDouble("RATE_PRICE"));
				rateVO.setRateBasic(rs.getInt("RATE_BASIC"));
				list.add(rateVO);
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
