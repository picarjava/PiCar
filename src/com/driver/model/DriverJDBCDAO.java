package com.driver.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DriverJDBCDAO implements DriverDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO DRIVER (MEM_ID, DRIVER_ID, PLATE_NUM, LICENCE, CRIMINAL, "
			+ "TRAFFIC_RECORD, ID_NUM, PHOTO, VERIFIED, BANNED, DEADLINE, ONLINE_CAR, "
			+ "SCORE, CAR_TYPE, SHARED_CAR, PET, SMOKE, BABY_SEAT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//	private static final String GET_ALL_STMT =
//			"SELECT * FROM DRIVER ORDER BY DRIVER_ID";
//	private static final String GET_ONE_STMT =
//	"SELECT MEM_ID, DRIVER_ID, PLATE_NUM, LICENCE, CRIMINAL, TRAFFIC_RECORD, ID_NUM, PHOTO, VERIFIED, BANNED, DEADLINE, ONLINE_CAR, SCORE, CAR_TYPE, SHARED_CAR, PET, SMOKE, BABY_SEAT FROM DRIVER WHERE DRIVER_ID";
	private static final String DELETE = 
	"DELETE FROM DRIVER where DRIVER_ID = ?";
//	private static final String UPDATE = 
//	"UPDATE DRIVER SET PLATE_NUM=?, LICENCE=?, CRIMINAL=?, TRAFFIC_RECORD=?, PHOTO=?, VERIFIED=?, BANNED=?, DEADLINE=?, ONLINE_CAR=?, SCORE=?, CAR_TYPE=?, SHARED_CAR=?, PET=?, SMOKE=?, BABY_SEAT=? where DRIVER=?";

	@Override
	public void insert(DriverVO driverVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, driverVO.getMemID());
			pstmt.setString(2, driverVO.getDriverID());
			pstmt.setString(3, driverVO.getPlateNum());
			pstmt.setBytes(4, driverVO.getLicence());
			pstmt.setBytes(5, driverVO.getCriminal());
			pstmt.setBytes(6, driverVO.getTrafficRecord());
			pstmt.setBytes(7, driverVO.getIdNum());
			pstmt.setBytes(8, driverVO.getPhoto());
			pstmt.setInt(9, driverVO.getVerified());
			pstmt.setInt(10, driverVO.getBanned());
			pstmt.setDate(11, driverVO.getDeadline());
			pstmt.setInt(12, driverVO.getOnlineCar());
			pstmt.setInt(13, driverVO.getScore());
			pstmt.setString(14, driverVO.getCarType());
			pstmt.setInt(15, driverVO.getSharedCar());
			pstmt.setInt(16, driverVO.getPet());
			pstmt.setInt(17, driverVO.getSmoke());
			pstmt.setInt(18, driverVO.getBabySeat());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
//    public static void main(String[] args) throws ClassNotFoundException {
//  Class.forName("oracle.jdbc.driver.OracleDriver");
//  DriverDAO_interface DriverJDBCDAO = new DriverJDBCDAO();
//  System.out.println(DriverJDBCDAO.insert(null));
//  

//  DriverJDBCDAO driverVO = driverDAO.findByPrimaryKey("2");
//  driverVO.setStartLoc("DR006");
//  System.out.println(singleOrderDAO.findByPrimaryKey("2"));
//  System.out.println(singleOrderDAO.getAll());
//}

	@Override
	public void update(DriverVO driverVO) {

	}

	@Override
	public void delete(String driverID) {

		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE);
			/*根據SQL常數，pstmt需要的值*/
			/*parameterIndex 從1開始*/
			pstmt.setString(1, driverID);
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("無法載入DB Driver"+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("發生SQL error"+se.getMessage());
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	

	}

	@Override
	public DriverVO findByPrimaryKey(String driver_id) {
		return null;
	}

	@Override
	public List<DriverVO> getAll() {
		return null;
	}

}
