package com.driver.model;

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

public class DriverJNDIDAO implements DriverDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO DRIVER (MEM_ID, DRIVER_ID, PLATE_NUM, LICENCE, CRIMINAL, "
			+ "TRAFFIC_RECORD, ID_NUM, PHOTO, VERIFIED, BANNED, DEADLINE, ONLINE_CAR, "
			+ "SCORE, CAR_TYPE, SHARED_CAR, PET, SMOKE, BABY_SEAT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT * FROM DRIVER ORDER BY DRIVER_ID";
	private static final String GET_ONE_STMT = "SELECT MEM_ID, DRIVER_ID, PLATE_NUM, LICENCE, CRIMINAL, TRAFFIC_RECORD, ID_NUM, PHOTO, VERIFIED, BANNED, DEADLINE, ONLINE_CAR, SCORE, CAR_TYPE, SHARED_CAR, PET, SMOKE, BABY_SEAT FROM DRIVER WHERE DRIVER_ID=?";
	private static final String DELETE = "DELETE FROM DRIVER where DRIVER_IDs  = ?";
	private static final String UPDATE = "UPDATE DRIVER SET PLATE_NUM=?, LICENCE=?, CRIMINAL=?, TRAFFIC_RECORD=?, PHOTO=?, VERIFIED=?, BANNED=?, DEADLINE=?, ONLINE_CAR=?, SCORE=?, CAR_TYPE=?, SHARED_CAR=?, PET=?, SMOKE=?, BABY_SEAT=? where DRIVER_ID=?";
	
	
	@Override
	public void insert(DriverVO driverVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(true);
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
			con.commit();
		}catch (SQLException se) {
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

	@Override
	public void update(DriverVO driverVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			/* 根據SQL常數，用vo.getxxx去set pstmt需要的值 */


			pstmt.setString(1, driverVO.getPlateNum());
			pstmt.setBytes(2, driverVO.getLicence());
			pstmt.setBytes(3, driverVO.getCriminal());
			pstmt.setBytes(4, driverVO.getTrafficRecord());
			pstmt.setBytes(5, driverVO.getPhoto());
			pstmt.setInt(6, driverVO.getVerified());
			pstmt.setInt(7, driverVO.getBanned());
			pstmt.setDate(8, driverVO.getDeadline());
			pstmt.setInt(9, driverVO.getOnlineCar());
			pstmt.setInt(10, driverVO.getScore());
			pstmt.setString(11, driverVO.getCarType());
			pstmt.setInt(12, driverVO.getSharedCar());
			pstmt.setInt(13, driverVO.getPet());
			pstmt.setInt(14, driverVO.getSmoke());
			pstmt.setInt(15, driverVO.getBabySeat());
			pstmt.setString(16, driverVO.getDriverID());

			pstmt.executeUpdate();

		}catch (SQLException se) {
			se.printStackTrace();
//			throw new RuntimeException("發生SQL error" + se.getMessage());
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
	public void delete(String driverID) {


		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			/* 根據SQL常數，pstmt需要的值 */
			/* parameterIndex 從1開始 */
			pstmt.setString(1, driverID);
			pstmt.executeUpdate();

		}catch (SQLException se) {
			throw new RuntimeException("發生SQL error" + se.getMessage());
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
	public DriverVO findByPrimaryKey(String driverID) {

		DriverVO driverVO=null;
		Connection con= null;
		PreparedStatement pstmt =null;
		ResultSet rs= null;
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT);
			/*根據SQL常數，pstmt需要的值*/
			/*parameterIndex 從1開始*////第一個?
			pstmt.setString(1, driverID);
			/*進行一筆資料的查詢*/
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				driverVO = new DriverVO();
				driverVO.setMemID(rs.getString("MEM_ID"));
				driverVO.setDriverID(rs.getString("DRIVER_ID"));
				driverVO.setPlateNum(rs.getString("PLATE_NUM"));
				driverVO.setLicence(rs.getBytes("LICENCE"));
				driverVO.setCriminal(rs.getBytes("CRIMINAL"));
				driverVO.setTrafficRecord(rs.getBytes("TRAFFIC_RECORD"));
				driverVO.setIdNum(rs.getBytes("ID_NUM"));
				driverVO.setPhoto(rs.getBytes("PHOTO"));
				driverVO.setVerified(rs.getInt("VERIFIED"));
				driverVO.setBanned(rs.getInt("BANNED"));
				driverVO.setDeadline(rs.getDate("DEADLINE"));
				driverVO.setOnlineCar(rs.getInt("ONLINE_CAR"));
				driverVO.setScore(rs.getInt("SCORE"));
				driverVO.setCarType(rs.getString("CAR_TYPE"));
				driverVO.setSharedCar(rs.getInt("SHARED_CAR"));
				driverVO.setPet(rs.getInt("PET"));
				driverVO.setSmoke(rs.getInt("SMOKE"));
				driverVO.setBabySeat(rs.getInt("BABY_SEAT"));
		
			}
		
		}catch(SQLException se){
			se.printStackTrace();
//			throw new RuntimeException("發生SQL errors"+se.getMessage());
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

		return driverVO;
	}

	@Override
	public List<DriverVO> getAll() {

		List<DriverVO> list = new ArrayList<DriverVO>();
		DriverVO driverVOall = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			/* 進行全部資料的查詢 */
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				driverVOall = new DriverVO();
				driverVOall.setMemID(rs.getString("MEM_ID"));
				driverVOall.setDriverID(rs.getString("DRIVER_ID"));
				driverVOall.setPlateNum(rs.getString("PLATE_NUM"));
				driverVOall.setLicence(rs.getBytes("LICENCE"));
				driverVOall.setCriminal(rs.getBytes("CRIMINAL"));
				driverVOall.setTrafficRecord(rs.getBytes("TRAFFIC_RECORD"));
				driverVOall.setIdNum(rs.getBytes("ID_NUM"));
				driverVOall.setPhoto(rs.getBytes("PHOTO"));
				driverVOall.setVerified(rs.getInt("VERIFIED"));
				driverVOall.setBanned(rs.getInt("BANNED"));
				driverVOall.setDeadline(rs.getDate("DEADLINE"));
				driverVOall.setOnlineCar(rs.getInt("ONLINE_CAR"));
				driverVOall.setScore(rs.getInt("SCORE"));
				driverVOall.setCarType(rs.getString("CAR_TYPE"));
				driverVOall.setSharedCar(rs.getInt("SHARED_CAR"));
				driverVOall.setPet(rs.getInt("PET"));
				driverVOall.setSmoke(rs.getInt("SMOKE"));
				driverVOall.setBabySeat(rs.getInt("BABY_SEAT"));

			
				list.add(driverVOall);

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
