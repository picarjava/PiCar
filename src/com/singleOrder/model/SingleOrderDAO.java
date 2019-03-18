package com.singleOrder.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
//import java.sql.DriverManager;

public class SingleOrderDAO implements SingleOrder_interface {
	private final static String SELECT_STMT = "SELECT * FROM SINGLE_ORDER WHERE ORDER_ID=?";
	private final static String SELECT_BY_STATE_AND_ORDER_TYPE_STMT = "SELECT * FROM SINGLE_ORDER WHERE STATE=? AND ORDER_TYPE=? ORDER BY ORDER_ID";
	private final static String SELECT_ALL_STMT = "SELECT * FROM SINGLE_ORDER";
	private final static String UPDATE_DRIVER_ID_AND_STATE_BY_ORDER_ID = "UPDATE SINGLE_ORDER SET DRIVER_ID=?, STATE=? WHERE ORDER_ID=?";
    private final static String UPDATE_STMT = "UPDATE SINGLE_ORDER SET DRIVER_ID=?, STATE=?, START_TIME=?, END_TIME=?, " +
                                                                      "START_LOC=?, END_LOC=?, START_LNG=?, START_LAT=?, " +
                                                                      "END_LNG=?, END_LAT=?, TOTAL_AMOUNT=?, RATE=? " +
                                                                      "WHERE ORDER_ID=?";
    private final static String INSERT_STMT = "INSERT INTO SINGLE_ORDER(ORDER_ID, MEM_ID, STATE, START_TIME, " + 
                                                                  "START_LOC, END_LOC, START_LNG, START_LAT, " +
                                                                  "END_LNG, END_LAT, TOTAL_AMOUNT, ORDER_TYPE, " +
                                                                  "NOTE, LAUNCH_TIME) " + 
                                                                  "VALUES ('SODR'||LPAD(to_char(SEQ_SINGLE_ORDER.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    //小編新增司機查評價平均
    private final static String DRIVER_RATE_AVE_STMT ="SELECT AVG(RATE) AS Avg_RATE FROM SINGLE_ORDER  WHERE DRIVER_ID =?";
    //小編新增一個刪除語法
    private static final String DELETE=
			"DELETE FROM SINGLE_ORDER WHERE ORDER_ID=?";
    //小編新增查被評價司機
    private static final String GET_RATED_DRIVERS="SELECT DISTINCT DRIVER_ID FROM SINGLE_ORDER WHERE RATE IS NOT NULL AND NOT RATE=0";
    /*小編預約訂單排程器用*/
    //撈單程訂單:三天前 可撈單程
    private static final String LIST_All_UNPAID_SINGLE="SELECT * FROM SINGLE_ORDER WHERE STATE=0 AND DRIVER_ID IS NULL AND ORDER_TYPE=3 AND START_TIME-3 <= CURRENT_TIMESTAMP";
     //撈長期訂單1:三天前撈出長期單的前幾筆)
    private static final String LIST_EARLIER_PART_UNPAID_FROM_LONGTERM_SETS="SELECT * FROM SINGLE_ORDER WHERE STATE=0 AND DRIVER_ID IS NULL AND ORDER_TYPE=4 AND START_TIME-3 <= CURRENT_TIMESTAMP";
    //撈長期訂單2:再透過 撈長期的LAUNCH_TIME ，撈此長期預約的整組
    private static final String LIST_ONT_SET_OF_LONGTERM="SELECT * FROM SINGLE_ORDER WHERE STATE=0 AND DRIVER_ID IS NULL AND ORDER_TYPE=4 AND LAUNCH_TIME=?";
//    以下兩個方法用於訂單管理排成使用
    private final static String TIME_FROM_START =  "SELECT ORDER_ID ,START_TIME FROM SINGLE_ORDER WHERE START_TIME = ?";
    private static final String UPDATE_STATE_TO_DELAY =	"UPDATE SINGLE_ORDER SET STATE ='6' WHERE ";

    private static DataSource dataSource;
//    private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
//    private final static String NAME = "PiCar";
//    private final static String PASSWORD = "123456";

    static {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TestDB");
        } catch (NamingException e) {
            e.printStackTrace();
        } // catch
    } // static
    
//    public static void main(String[] args) throws ClassNotFoundException {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        SingleOrder_interface singleOrderDAO = new SingleOrderDAO();
//        SingleOrderVO singleOrderVO = singleOrderDAO.findByPrimaryKey("2");
//        System.out.println(singleOrderVO);  
//        singleOrderVO.setStartLoc("Taipei 101");
//        singleOrderDAO.update(singleOrderVO);
//        singleOrderVO.setOrderID("4");
//        singleOrderVO.setNote("das");
//        singleOrderDAO.insert(singleOrderVO);
//        System.out.println(singleOrderDAO.getAll());
//    } // main()
    
    /*小編預約訂單排程器用*/
  //撈單程訂單:三天前 可撈單程
    public HashSet<SingleOrderVO> listAllUnpaidReservationOrder(){
    	HashSet<SingleOrderVO> allUnpaidReservationOrderID=new HashSet<SingleOrderVO>();
    	SingleOrderVO singleOrderVO=new  SingleOrderVO();
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
    	try {
    		con=dataSource.getConnection();
			pstmt=con.prepareStatement(LIST_All_UNPAID_SINGLE);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				singleOrderVO.setOrderID(rs.getString("ORDER_ID"));
				singleOrderVO.setDriverID(rs.getString("DRIVER_ID"));
				singleOrderVO.setMemID(rs.getString("MEM_ID"));
				singleOrderVO.setState(rs.getInt("STATE"));
				singleOrderVO.setStartTime(rs.getTimestamp("START_TIME"));
				singleOrderVO.setEndTime(rs.getTimestamp("END_TIME"));
				singleOrderVO.setStartLoc(rs.getString("START_LOC"));
				singleOrderVO.setEndLoc(rs.getString("END_LOC"));
				singleOrderVO.setStartLng(rs.getDouble("START_LNG"));
				singleOrderVO.setStartLat(rs.getDouble("START_LAT"));
				singleOrderVO.setEndLng(rs.getDouble("END_LNG"));
				singleOrderVO.setEndLat(rs.getDouble("END_LAT"));
				singleOrderVO.setTotalAmount(rs.getInt("TOTAL_AMOUNT"));
				singleOrderVO.setOrderType(rs.getInt("ORDER_TYPE"));
				singleOrderVO.setRate(rs.getInt("RATE"));
				singleOrderVO.setNote(rs.getString("NOTE"));
				singleOrderVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
				allUnpaidReservationOrderID.add(singleOrderVO);
			}
    	}catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(con);
        } // finally
    	
    	return allUnpaidReservationOrderID;
    }
  //撈長期訂單1:三天前撈出長期單的前幾筆) LIST_EARLIER_PART_FROM_LONGTERM_SETS
    public HashSet<SingleOrderVO> listEarlierPartUnpaidFromLongtermSets(){
    	HashSet<SingleOrderVO> earlierPartUnpaidFromLongtermSets=new HashSet<SingleOrderVO>();
    	SingleOrderVO singleOrderVO=new  SingleOrderVO();
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
    		con=dataSource.getConnection();
			pstmt=con.prepareStatement(LIST_EARLIER_PART_UNPAID_FROM_LONGTERM_SETS);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				singleOrderVO.setOrderID(rs.getString("ORDER_ID"));
				singleOrderVO.setDriverID(rs.getString("DRIVER_ID"));
				singleOrderVO.setMemID(rs.getString("MEM_ID"));
				singleOrderVO.setState(rs.getInt("STATE"));
				singleOrderVO.setStartTime(rs.getTimestamp("START_TIME"));
				singleOrderVO.setEndTime(rs.getTimestamp("END_TIME"));
				singleOrderVO.setStartLoc(rs.getString("START_LOC"));
				singleOrderVO.setEndLoc(rs.getString("END_LOC"));
				singleOrderVO.setStartLng(rs.getDouble("START_LNG"));
				singleOrderVO.setStartLat(rs.getDouble("START_LAT"));
				singleOrderVO.setEndLng(rs.getDouble("END_LNG"));
				singleOrderVO.setEndLat(rs.getDouble("END_LAT"));
				singleOrderVO.setTotalAmount(rs.getInt("TOTAL_AMOUNT"));
				singleOrderVO.setOrderType(rs.getInt("ORDER_TYPE"));
				singleOrderVO.setRate(rs.getInt("RATE"));
				singleOrderVO.setNote(rs.getString("NOTE"));
				singleOrderVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
				earlierPartUnpaidFromLongtermSets.add(singleOrderVO);
			}
    	}catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(con);
        } // finally
    	return earlierPartUnpaidFromLongtermSets;
    }
  //撈長期訂單2:再透過 撈長期第一筆LAUNCH_TIME ，撈此長期預約的整組
    public HashSet<SingleOrderVO> listOneSetOfLongterm(Timestamp launchtime){
    	HashSet<SingleOrderVO> oneSetOfLongterm=new HashSet<SingleOrderVO>();
    	SingleOrderVO singleOrderVO=new  SingleOrderVO();
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
    		con=dataSource.getConnection();
			pstmt=con.prepareStatement(LIST_ONT_SET_OF_LONGTERM);
			pstmt.setTimestamp(1, launchtime);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				singleOrderVO.setOrderID(rs.getString("ORDER_ID"));
				singleOrderVO.setDriverID(rs.getString("DRIVER_ID"));
				singleOrderVO.setMemID(rs.getString("MEM_ID"));
				singleOrderVO.setState(rs.getInt("STATE"));
				singleOrderVO.setStartTime(rs.getTimestamp("START_TIME"));
				singleOrderVO.setEndTime(rs.getTimestamp("END_TIME"));
				singleOrderVO.setStartLoc(rs.getString("START_LOC"));
				singleOrderVO.setEndLoc(rs.getString("END_LOC"));
				singleOrderVO.setStartLng(rs.getDouble("START_LNG"));
				singleOrderVO.setStartLat(rs.getDouble("START_LAT"));
				singleOrderVO.setEndLng(rs.getDouble("END_LNG"));
				singleOrderVO.setEndLat(rs.getDouble("END_LAT"));
				singleOrderVO.setTotalAmount(rs.getInt("TOTAL_AMOUNT"));
				singleOrderVO.setOrderType(rs.getInt("ORDER_TYPE"));
				singleOrderVO.setRate(rs.getInt("RATE"));
				singleOrderVO.setNote(rs.getString("NOTE"));
				singleOrderVO.setLaunchTime(rs.getTimestamp("LAUNCH_TIME"));
				oneSetOfLongterm.add(singleOrderVO);
			}
    	}catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(con);
        } // finally
    	return oneSetOfLongterm;
    }
    
  //小編新增查被評價司機
    public HashSet<String> getRatedDrivers(){
    	HashSet<String> ratedDrivers=new HashSet<String>();
    	
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
        	con=dataSource.getConnection();
			pstmt=con.prepareStatement(GET_RATED_DRIVERS);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ratedDrivers.add(rs.getString("DRIVER_ID"));
			}
        	
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
            closeConnection(con);
        } // finally
    	return ratedDrivers;
    }
    
   

    
    public void update_state_to_delay() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_STATE_TO_DELAY);
//            int index = 1;
//            preparedStatement.setInt(index++, state);
//            preparedStatement.setString(index++, orderID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
    
    
    public List<String> get_start_time(String START_TIME,String START_TIME2) {
		// TODO Auto-generated method stub
		List<String> list =new ArrayList<String>();
		String starttime=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = dataSource.getConnection();
		pstmt = con.prepareStatement(TIME_FROM_START);
		pstmt.setString(1, START_TIME);
		pstmt.setString(2, START_TIME2);
		
//		where 取現在三天後時間
		rs = pstmt.executeQuery();
		while(rs.next()) {
		starttime=(rs.getString("START_TIME"));
		list.add(starttime);
// 訂單 開始時間ID
//		list.add(orderid).add(memid).add;
//		list.addAll(orderid,)
		}
	}catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
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
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	return list;
}
    
    
    //小編新增司機查評價平均

    public int findRateAveByDriverID(String driverID) {
    	Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
    	int rateAve=0;
    	try {
    		con=dataSource.getConnection();
			pstmt=con.prepareStatement(DRIVER_RATE_AVE_STMT);
			pstmt.setString(1,driverID);
			rs=pstmt.executeQuery();
			while(rs.next()) {
			rateAve=rs.getInt("Avg_RATE");
			}
			
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
    	return rateAve;
    }
    
    private int parseInt(String driverID) {
	// TODO Auto-generated method stub
	return 0;
}

	//小編新增一個刪除方法
    @Override
    public void delete(String orderID) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(DELETE);
			/*根據SQL常數，pstmt需要的值*/
			/*parameterIndex 從1開始*/
			pstmt.setString(1, orderID);
			pstmt.executeUpdate();
			
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
    public SingleOrderVO findByPrimaryKey(String orderID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SingleOrderVO newSingleOrderVO = null;
        try {
//          connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_STMT);
            preparedStatement.setString(1, orderID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               newSingleOrderVO = getSingleOrderVO(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
        
        return newSingleOrderVO;
    } // findByPrimaryKey()
    
    
    //小編新增長期訂單的insert方法
    @Override
    public void insert(LinkedList<SingleOrderVO> singleOrderVOList) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
       
        try {
        	connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_STMT);
			connection.setAutoCommit(false);//多筆訂單新增視為一筆交易
		}  
        catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //for迴圈取出每一筆並新增
        for(SingleOrderVO singleOrderVO:singleOrderVOList){
        	
            try {
            	int index = 1;
                preparedStatement.setString(index++, singleOrderVO.getMemID());
                preparedStatement.setInt(index++, singleOrderVO.getState());
                preparedStatement.setTimestamp(index++, singleOrderVO.getStartTime());
                preparedStatement.setString(index++, singleOrderVO.getStartLoc());
                preparedStatement.setString(index++, singleOrderVO.getEndLoc());
                preparedStatement.setDouble(index++, singleOrderVO.getStartLng());
                preparedStatement.setDouble(index++, singleOrderVO.getStartLat());
                preparedStatement.setDouble(index++, singleOrderVO.getEndLng());
                preparedStatement.setDouble(index++, singleOrderVO.getEndLat());
                preparedStatement.setInt(index++, singleOrderVO.getTotalAmount());
                preparedStatement.setInt(index++, singleOrderVO.getOrderType());
                preparedStatement.setString(index++, singleOrderVO.getNote());
                preparedStatement.setTimestamp(index++, singleOrderVO.getLaunchTime());            
                preparedStatement.addBatch(); //先將每筆置入batch中
         
            }catch (SQLException e) {
            	try {
					connection.rollback(); //每筆置入batch過程中有誤，則rollback
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(), e);
            }
        }//for迴圈
            try {
    			preparedStatement.executeBatch(); //批次新增，效能較佳
    			connection.commit(); //新增過程無誤，則commit
    			connection.setAutoCommit(true); //送進資料庫後調回setAutoCommit(true)
    		} catch (SQLException e) {
    			try {
					connection.rollback(); //批次新增過程中有誤 ，則rollback
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
    			e.printStackTrace();
    		}
            finally {
                closePreparedStatement(preparedStatement);
                closeConnection(connection);
            } // finally 
        
    } // insert()

    

    @Override
    public void insert(SingleOrderVO singleOrderVO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int index = 1;
        try {
//            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_STMT);
            preparedStatement.setString(index++, singleOrderVO.getMemID());
            preparedStatement.setInt(index++, singleOrderVO.getState());
            preparedStatement.setTimestamp(index++, singleOrderVO.getStartTime());
            preparedStatement.setString(index++, singleOrderVO.getStartLoc());
            preparedStatement.setString(index++, singleOrderVO.getEndLoc());
            preparedStatement.setDouble(index++, singleOrderVO.getStartLng());
            preparedStatement.setDouble(index++, singleOrderVO.getStartLat());
            preparedStatement.setDouble(index++, singleOrderVO.getEndLng());
            preparedStatement.setDouble(index++, singleOrderVO.getEndLat());
            preparedStatement.setInt(index++, singleOrderVO.getTotalAmount());
            preparedStatement.setInt(index++, singleOrderVO.getOrderType());
            preparedStatement.setString(index++, singleOrderVO.getNote());
            preparedStatement.setTimestamp(index++, singleOrderVO.getLaunchTime());            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally    
    } // insert()

    @Override
    public void update(SingleOrderVO singleOrderVO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int index = 1;
        try {
//            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_STMT);
            preparedStatement.setString(index++, singleOrderVO.getDriverID());
            preparedStatement.setInt(index++, singleOrderVO.getState());
            preparedStatement.setTimestamp(index++, singleOrderVO.getStartTime());
            preparedStatement.setTimestamp(index++, singleOrderVO.getEndTime());
            preparedStatement.setString(index++, singleOrderVO.getStartLoc());
            preparedStatement.setString(index++, singleOrderVO.getEndLoc());
            preparedStatement.setDouble(index++, singleOrderVO.getStartLng());
            preparedStatement.setDouble(index++, singleOrderVO.getStartLat());
            preparedStatement.setDouble(index++, singleOrderVO.getEndLng());
            preparedStatement.setDouble(index++, singleOrderVO.getEndLat());
            preparedStatement.setInt(index++, singleOrderVO.getTotalAmount());
            preparedStatement.setInt(index++, singleOrderVO.getRate());
            preparedStatement.setString(index++, singleOrderVO.getOrderID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
    } // update()

    @Override
    public List<SingleOrderVO> getAll() {
        List<SingleOrderVO> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
//            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_STMT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getSingleOrderVO(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
        
        return list;
    } // getAll()
    
    
    
    @Override
    public List<SingleOrderVO> findByStateAndOrderType(Integer state, Integer orderType) {
        List<SingleOrderVO> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
//            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_STATE_AND_ORDER_TYPE_STMT);
            int index = 1;
            preparedStatement.setInt(index++, state);
            preparedStatement.setInt(index++, orderType);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getSingleOrderVO(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
        
        return list;
    }
    
    @Override
    public void updateDriverIDAndStateByOrderID(String driverID, Integer state, String orderID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_DRIVER_ID_AND_STATE_BY_ORDER_ID);
            int index = 1;
            preparedStatement.setString(index++, driverID);
            preparedStatement.setInt(index++, state);
            preparedStatement.setString(index++, orderID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
    
    @Override
    public void updateDriverIDAndStateByOrderID(String driverID, Integer state, List<String> orderIDs) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            for (String orderID: orderIDs) {
                int index = 1;
                preparedStatement = connection.prepareStatement(UPDATE_DRIVER_ID_AND_STATE_BY_ORDER_ID);
                preparedStatement.setString(index++, driverID);
                preparedStatement.setInt(index++, state);
                preparedStatement.setString(index++, orderID);
                // add sql command to batch
                preparedStatement.addBatch();
            } // for
            
            // execute batch update
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null)
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    throw new RuntimeException(e.getMessage(), e1);
                }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(), e);
            } // catch
        } // if
    } // closeResultSet()
    
    private SingleOrderVO getSingleOrderVO(ResultSet resultSet) throws SQLException {
        int index = 1;
        SingleOrderVO singleOrderVO = new SingleOrderVO();
        singleOrderVO.setOrderID(resultSet.getString(index++));
        singleOrderVO.setDriverID(resultSet.getString(index++));
        singleOrderVO.setMemID(resultSet.getString(index++));
        singleOrderVO.setState(resultSet.getInt(index++));
        singleOrderVO.setStartTime(resultSet.getTimestamp(index++));
        singleOrderVO.setEndTime(resultSet.getTimestamp(index++));
        singleOrderVO.setStartLoc(resultSet.getString(index++));
        singleOrderVO.setEndLoc(resultSet.getString(index++));
        singleOrderVO.setStartLng(resultSet.getDouble(index++));
        singleOrderVO.setStartLat(resultSet.getDouble(index++));
        singleOrderVO.setEndLng(resultSet.getDouble(index++));
        singleOrderVO.setEndLat(resultSet.getDouble(index++));
        singleOrderVO.setTotalAmount(resultSet.getInt(index++));
        singleOrderVO.setOrderType(resultSet.getInt(index++));
        singleOrderVO.setRate(resultSet.getInt(index++));
        singleOrderVO.setNote(resultSet.getString(index++));
        singleOrderVO.setLaunchTime(resultSet.getTimestamp(index++));
        return singleOrderVO;
    } // getSingleOrderVO()
    
    private void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(), e);
            } // catch
        } // if
    } // closePreparedStatement()
    
    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(), e);
            } //catch
        } // if
    } // closeConnection()
    
		


    
} // class SingleOrderDAO
