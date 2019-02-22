package com.rate.model;

import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.sql.Blob;
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

	private static final String INSERT_STMT = "INSERT INTO RATE (RATE_ID, RATE_NAME, RATE_PRICE, RATE_BASIC, PIC) VALUES"
			+ "(RAT_SEQ.NEXTVAL, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE RATE SET  RATE_NAME = ?, RATE_PRICE = ?, RATE_BASIC = ?  "
			+ "WHERE RATE_ID = ?";
	private static final String DELETE = "DELETE FROM RATE WHERE RATE_ID = ? ";
	private static final String GET_ONE_STMT = "SELECT RATE_ID, RATE_NAME, RATE_PRICE, RATE_BASIC, PIC FROM RATE WHERE RATE_ID= ?";
	private static final String GET_ALL_STMT = "SELECT RATE_ID, RATE_NAME, RATE_PRICE, RATE_BASIC, PIC FROM RATE ORDER BY RATE_ID";
//	private static final String GET_PIC = "SELECT PIC FROM RATE WHERE = ?";
//	private static final String INSER_PIC = "INSERT INTO RATE(PIC) VALUES (?)";
	
	@Override
	public void insert(RateVO rateVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, rateVO.getRateID());
			pstmt.setString(1, rateVO.getRateName());
			pstmt.setDouble(2, rateVO.getRatePrice());
			pstmt.setInt(3, rateVO.getRateBasic());
			Blob blob = con.createBlob();
			byte [] pic = rateVO.getPic();
			blob.setBytes(1, pic);
			pstmt.setBlob(4, blob);

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
				rateVO.setRateID(rs.getInt(1));
				rateVO.setRateName(rs.getString(2));
				rateVO.setRatePrice(rs.getDouble(3));
				rateVO.setRateBasic(rs.getInt(4));
				rateVO.setPic(rs.getBytes(5));
//				InputStream is = rs.getBinaryStream("PIC");
//				rateVO.setPic(rs.getBinaryStream("PIC"));
				
				
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

	@Override
	public RateVO findPic(Integer rateID) {
		
		
		
		
		return null;
	}

	@Override
	public void insertPic(RateVO rateVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
//		try{
//			con=ds.getConnection();
//			pstmt=con.prepareStatement(sql)
//		}
			
		
		
		
		
		
		
		
		
	}

}
