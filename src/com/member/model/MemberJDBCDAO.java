package com.member.model;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

//測試完成
public class MemberJDBCDAO implements MemberDAO_interface {

	public static final String driver = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String userid = "PICAR";
	public static final String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO MEMBER (MEM_ID, NAME, EMAIL, PASSWORD, PHONE, CREDIT_CARD, PET, SMOKE, GENDER, "
			+ "TOKEN, ACTIVITY_TOKEN, BIRTHDAY, VERIFIED, BABY_SEAT) VALUES('M'||LPAD(MEM_SEQ.NEXTVAL, 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private static final String GET_ALL_STMT = "SELECT MEM_ID, NAME, EMAIL, PASSWORD, PHONE, CREDIT_CARD, PET, SMOKE, GENDER,"
			+ "TOKEN, ACTIVITY_TOKEN, TO_CHAR(BIRTHDAY, 'YYYY-MM-DD')BIRTHDAY, VERIFIED, BABY_SEAT FROM MEMBER ORDER BY MEM_ID";

	private static final String GET_ONE_STMT = "SELECT MEM_ID, NAME, EMAIL, PASSWORD, PHONE, CREDIT_CARD, PET, SMOKE, GENDER,"
			+ "TOKEN, ACTIVITY_TOKEN, TO_CHAR(BIRTHDAY, 'YYYY-MM-DD')BIRTHDAY, VERIFIED, BABY_SEAT FROM MEMBER WHERE MEM_ID = ?";

	private static final String DELETE = "DELETE FROM MEMBER WHERE MEM_ID = ?";

	private static final String UPDATE_STMT = "UPDATE MEMBER SET  NAME=?, EMAIL=?, PASSWORD=?, PHONE=?, CREDIT_CARD=?, PET=?, SMOKE=?, GENDER=?, "
			+ "TOKEN=?, ACTIVITY_TOKEN=?, BIRTHDAY=?, VERIFIED=?, BABY_SEAT=? WHERE MEM_ID=?";

	private static final String GET_AMOUT_MEM = "SELECT SUM (AMOUNT) FROM STORE_RECORD WHERE MEM_ID=?";

	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, memberVO.getMemID());
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
		} catch (ClassNotFoundException se) {
			throw new RuntimeException("資料庫連線錯誤:" + se.getMessage());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			pstmt.setString(14, memberVO.getMemID());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException se) {
			throw new RuntimeException("資料庫連線錯誤:" + se.getMessage());

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memID);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException se) {
			throw new RuntimeException("資料庫連線錯誤:" + se.getMessage());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException se) {
			throw new RuntimeException("資料庫連線錯誤:" + se.getMessage());

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException se) {
			throw new RuntimeException("連線或SQL錯誤:" + se.getMessage());
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

	public Integer getSumAmount(String memID) {

		Integer sumAmount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(GET_AMOUT_MEM);
			pstmt.setString(1, memID);
			rs = pstmt.executeQuery();
			rs.next();
			sumAmount = rs.getInt(1);
			

		} catch (ClassNotFoundException se) {
			throw new RuntimeException("資料庫連線錯誤:" + se.getMessage());

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

		return sumAmount;
	}

	public static void main(String[] args) {

		MemberJDBCDAO memberDAOJBDC = new MemberJDBCDAO();
		System.out.println(memberDAOJBDC.getSumAmount("M001"));
//		MemberVO mbDAO = memberDAOJBDC.findByPrimaryKey("M003");
//		System.out.println(mbDAO.getMemID());
//		System.out.println(mbDAO.getName());
//		System.out.println(mbDAO.getEmail());
//		System.out.println(mbDAO.getPassword());
//		System.out.println(mbDAO.getPhone());
//		System.out.println(mbDAO.getCreditcard());
//		System.out.println(mbDAO.getPet());
//		System.out.println(mbDAO.getSmoke());
//		System.out.println(mbDAO.getGender());
//		System.out.println(mbDAO.getToken());
//		System.out.println(mbDAO.getActivityToken());
//		System.out.println(mbDAO.getBirthday());
//		System.out.println(mbDAO.getVerified());
//		System.out.println(mbDAO.getBabySeat());

//		MemberVO mbDAO1 = new MemberVO();
//
////		mbDAO1.setMemID("M111");
//		mbDAO1.setName("SS");
//		mbDAO1.setEmail("ZXX");
//		mbDAO1.setPassword("XX");
//		mbDAO1.setPhone("XXXX");
//		mbDAO1.setCreditcard("XXXX");
//		mbDAO1.setPet(0);
//		mbDAO1.setSmoke(0);
//		mbDAO1.setGender(1);
//		mbDAO1.setToken(33);
//		mbDAO1.setActivityToken(444);
//		mbDAO1.setBirthday(java.sql.Date.valueOf("1988-07-07"));
//		mbDAO1.setVerified(1);
//		mbDAO1.setBabySeat(1);//
//		memberDAOJBDC.insert(mbDAO1);
//		System.out.println(" insert ok~");

//		memberDAOJBDC.delete("M111");
//		System.out.println("delete ok~");

//		List<MemberVO> list = memberDAOJBDC.getAll();
//		for(MemberVO mbDAO2 : list) {
//			System.out.println(mbDAO2);
//			System.out.println(mbDAO2.getMemID());
//			System.out.println(mbDAO2.getName());
//			System.out.println(mbDAO2.getEmail());
//			System.out.println(mbDAO2.getPassword());
//			System.out.println(mbDAO2.getPhone());
//			System.out.println(mbDAO2.getCreditcard());
//			System.out.println(mbDAO2.getPet());
//			System.out.println(mbDAO2.getSmoke());
//			System.out.println(mbDAO2.getGender());
//			System.out.println(mbDAO2.getToken());
//			System.out.println(mbDAO2.getActivityToken());
//			System.out.println(mbDAO2.getBirthday());
//			System.out.println(mbDAO2.getVerified());
//			System.out.println(mbDAO2.getBabySeat());
//			System.out.println("==============================");

//		MemberVO mbDAO1 = new MemberVO();
//		
//		mbDAO1.setMemID("M001");
//		mbDAO1.setName("SS");
//		mbDAO1.setEmail("ZXX");
//		mbDAO1.setPassword("XX");
//		mbDAO1.setPhone("XXXX");
//		mbDAO1.setCreditcard("XXXX");
//		mbDAO1.setPet(0);
//		mbDAO1.setSmoke(0);
//		mbDAO1.setGender(1);
//		mbDAO1.setToken(33);
//		mbDAO1.setActivityToken(666);
//		mbDAO1.setBirthday(java.sql.Date.valueOf("1988-07-07"));
//		mbDAO1.setVerified(1);
//		mbDAO1.setBabySeat(1);//	
//		memberDAOJBDC.update(mbDAO1);
//		System.out.println(" update ok~");
//			

	}

	@Override
	public MemberVO findByLoginPass(String memID, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateToken(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVerified(String memID) {
		// TODO Auto-generated method stub
		
	}


}
