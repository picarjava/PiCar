package com.member.model;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MemberDAO implements MemberDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO MEMBER (MEM_ID, NAME, EMAIL, PASSWORD, PHONE, CREDIT_CARD, PET, SMOKE, GENDER, "
			+ "TOKEN, ACTIVITY_TOKEN, BIRTHDAY, VERIFIED, BABY_SEAT) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private static final String GET_ALL_STMT = "SELECT MEM_ID, NAME, EMAIL, PASSWORD, PHONE, CREDIT_CARD, PET, SMOKE, GENDER,"
			+ "TOKEN, ACTIVITY_TOKEN, TO_CHAR(BIRTHDAY, 'YYYY-MM-DD')BIRTHDAY, VERIFIED, BABY_SEAT FROM MEMBER ORDER BY MEM_ID";

	private static final String GET_ONE_STMT = "SELECT MEM_ID, NAME, EMAIL, PASSWORD, PHONE, CREDIT_CARD, PET, SMOKE, GENDER,"
			+ "TOKEN, ACTIVITY_TOKEN, TO_CHAR(BIRTHDAY, 'YYYY-MM-DD')BIRTHDAY, VERIFIED, BABY_SEAT FROM MEMBER WHERE MEM_ID = ?";

	private static final String DELETE = "DELETE FROM MEMBER WHERE MEM_ID = ?";

	private static final String UPDATE_STMT = "UPDATE MEMBER SET  NAME=?, EMAIL=?, PASSWORD=?, PHONE=?, CREDIT_CARD=?, PET=?, SMOKE=?, GENDER=?, "
			+ "TOKEN=?, ACTIVITY_TOKEN=?, BIRTHDAY=?, VERIFIED=?, BABY_SEAT=? WHERE MEM_ID=?";

	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberVO.getMemID());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getEmail());
			pstmt.setString(4, memberVO.getPassword());
			pstmt.setString(5, memberVO.getPhone());
			pstmt.setString(6, memberVO.getCreditcard());
			pstmt.setInt(7, memberVO.getPet());
			pstmt.setInt(8, memberVO.getSmoke());
			pstmt.setInt(9, memberVO.getGender());
			pstmt.setInt(10, memberVO.getToken());
			pstmt.setInt(11, memberVO.getActivityToken());
			pstmt.setDate(12, memberVO.getBirthday());
			pstmt.setInt(13, memberVO.getVerified());
			pstmt.setInt(14, memberVO.getBabySeat());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("資料庫連線錯誤:" + se.getMessage());

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

//	用來對應pstmt的方法
//	UPDATE_STMT =
//			"UPDATE MEMBER SET  NAME=?, EMAIL=?, PASSWORD=?, PHONE=?, CREDIT_CARD=?, PET=?, SMOKE=?, GENDER=?, "
//			+ "TOKEN=?, ACTIVITY_TOKEN=?, BIRTHDAY=?, VERIFIED=?, BABY_SEAT=? WHERE MEM_ID=?";

	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, memberVO.getName());
			pstmt.setString(2, memberVO.getEmail());
			pstmt.setString(3, memberVO.getPassword());
			pstmt.setString(4, memberVO.getPhone());
			pstmt.setString(5, memberVO.getCreditcard());
			pstmt.setInt(6, memberVO.getPet());
			pstmt.setInt(7, memberVO.getSmoke());
			pstmt.setInt(8, memberVO.getGender());
			pstmt.setInt(9, memberVO.getToken());
			pstmt.setInt(10, memberVO.getActivityToken());
			pstmt.setDate(11, memberVO.getBirthday());
			pstmt.setInt(12, memberVO.getVerified());
			pstmt.setInt(13, memberVO.getBabySeat());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("資料庫連線錯誤:" + se.getMessage());

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

//	用來對應pstmt的方法
//	private static final String DELETE =  "DELET FROM MEMBER WHERE MEM_ID = ?";

	@Override
	public void delete(String memID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memID);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("資料庫連線錯誤:" + se.getMessage());

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

//	"SELECT MEM_ID, NAME, EMAIL, PASSWORD, PHONE, CREDIT_CARD, PET, SMOKE, GENDER"
//			+ "TOKEN, ACTIVITY_TOKEN, TO_CHAR(BIRTHDAY, 'YYYY')BIRTHDAY, VERIFIED, BABY_SEAT FROM MEMBER WHERE MEM_ID = ?";
	@Override
	public MemberVO findByPrimaryKey(String memID) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();

				memberVO.setMemID(rs.getString("MEM_ID"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setPassword(rs.getString("PASSWORD"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setCreditcard(rs.getString("CREDIT_CARD"));
				memberVO.setPet(rs.getInt("PET"));
				memberVO.setSmoke(rs.getInt("SMOKE"));
				memberVO.setGender(rs.getInt("GENDER"));
				memberVO.setToken(rs.getInt("TOKEN"));
				memberVO.setActivityToken(rs.getInt("ACTIVITY_TOKEN"));
				memberVO.setBirthday(rs.getDate("BIRTHDAY"));
				memberVO.setVerified(rs.getInt("VERIFIED"));
				memberVO.setBabySeat(rs.getInt("BABY_SEAT"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("資料庫連線錯誤:" + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {

		List<MemberVO> list = new ArrayList();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemID(rs.getString("MEM_ID"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setPassword(rs.getString("PASSWORD"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setCreditcard(rs.getString("CREDIT_CARD"));
				memberVO.setPet(rs.getInt("PET"));
				memberVO.setSmoke(rs.getInt("SMOKE"));
				memberVO.setGender(rs.getInt("GENDER"));
				memberVO.setToken(rs.getInt("TOKEN"));
				memberVO.setActivityToken(rs.getInt("ACTIVITY_TOKEN"));
				memberVO.setBirthday(rs.getDate("BIRTHDAY"));
				memberVO.setVerified(rs.getInt("VERIFIED"));
				memberVO.setBabySeat(rs.getInt("BABY_SEAT"));
				list.add(memberVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("連線或SQL錯誤:" + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		return list;
	}

}
