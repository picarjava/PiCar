package com.singleOrder.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingleOrderDAO implements SingleOrder_interface {
	private final static String SELECT = "SELECT * FROM SINGLE_ORDER WHERE ORDER_ID=?";
	private final static String SELECT_ALL = "SELECT * FROM SINGLE_ORDER";
    private final static String UPDATE = "UPDATE SINGLE_ORDER SET DRIVER_ID=?, MEM_ID=?, STATE=?, " + 
                                         "START_TIME=?, END_TIME=?, START_LOC=?, END_LOC=?, START_LNG=?, START_LAT=?, " + 
                                         "END_LNG=?, END_LAT=?, TOTAL_AMOUNT=?, ORDER_TYPE=?, RATE=?, NOTE=?, LUNCH_TIME=? WHERE ORDER_ID=?";
    private final static String INSERT = "INSERT INTO SINGLE_OREDR(SEQ_SINGLE_ORDER.NEXTVAL, DRIVER_ID, MEM_ID, STATE, " + 
                                         "START_TIME, END_TIME, START_LOC, END_LOC, START_LNG, START_LAT, " +
                                         "END_LNG, END_LAT, TOTAL_AMOUNT, ORDER_TYPE, RATE, NOTE, LUNCH_TIME)) " + 
                                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//    private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
//    private final static String NAME = "CA106";
    private static DataSource dataSource;

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
//        SingleOrderInterface singleOrderDAO = new SingleOrderDAO();
//        System.out.println(singleOrderDAO.findByPrimaryKey("2"));
//        SingleOrderVO singleOrderVO = singleOrderDAO.findByPrimaryKey("2");
//        singleOrderVO.setStartLoc("Taipei 101");
//        singleOrderDAO.update(singleOrderVO);
//        System.out.println(singleOrderDAO.findByPrimaryKey("2"));
//        System.out.println(singleOrderDAO.getAll());
//    }
    
    @Override
    public SingleOrderVO findByPrimaryKey(String orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SingleOrderVO newSingleOrderVO = null;
        try {
//          connection = DriverManager.getConnection(URL, NAME, NAME);
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setString(1, orderId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               newSingleOrderVO = getSingleOrderVo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        
        return newSingleOrderVO;
    }

    @Override
    public void insert(SingleOrderVO singleOrderVO) {
        excuteUpdate(singleOrderVO, INSERT);     
    }

    @Override
    public void update(SingleOrderVO singleOrderVO) {
        excuteUpdate(singleOrderVO, UPDATE);
    }

    @Override
    public List<SingleOrderVO> getAll() {
        List<SingleOrderVO> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
//            connection = DriverManager.getConnection(URL, NAME, NAME);
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getSingleOrderVo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private void excuteUpdate(SingleOrderVO singleOrderVO, String sql) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int index = 1;
        try {
//            connection = DriverManager.getConnection(URL, NAME, NAME);
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (sql.equals(INSERT)) {
                
                preparedStatement.setString(index++,singleOrderVO.getOrderID());
            }
            
            preparedStatement.setString(index++,singleOrderVO.getDriverID());
            preparedStatement.setString(index++, singleOrderVO.getMemID());
            preparedStatement.setInt(index++,singleOrderVO.getState());
            preparedStatement.setDate(index++,singleOrderVO.getStartTime());
            preparedStatement.setDate(index++,singleOrderVO.getEndTime());
            preparedStatement.setString(index++,singleOrderVO.getStartLoc());
            preparedStatement.setString(index++,singleOrderVO.getEndLoc());
            preparedStatement.setDouble(index++,singleOrderVO.getStartLng());
            preparedStatement.setDouble(index++,singleOrderVO.getStartLat());
            preparedStatement.setDouble(index++,singleOrderVO.getEndLng());
            preparedStatement.setDouble(index++,singleOrderVO.getEndLat());
            preparedStatement.setInt(index++,singleOrderVO.getTotalAmount());
            preparedStatement.setInt(index++,singleOrderVO.getOrderType());
            preparedStatement.setInt(index++,singleOrderVO.getRate());
            preparedStatement.setString(index++,singleOrderVO.getNote());
            preparedStatement.setTimestamp(index++,singleOrderVO.getLaunchTime());
            if (sql.equals(UPDATE))
                preparedStatement.setString(index++,singleOrderVO.getOrderID());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
        
    }
    
    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private SingleOrderVO getSingleOrderVo(ResultSet resultSet) throws SQLException {
        int index = 1;
        SingleOrderVO singleOrderVO = new SingleOrderVO();
        singleOrderVO.setOrderID(resultSet.getString(index++));
        singleOrderVO.setDriverID(resultSet.getString(index++));
        singleOrderVO.setMemID(resultSet.getString(index++));
        singleOrderVO.setState(resultSet.getInt(index++));
        singleOrderVO.setStartTime(resultSet.getDate(index++));
        singleOrderVO.setEndTime(resultSet.getDate(index++));
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
    }
    
    private void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
