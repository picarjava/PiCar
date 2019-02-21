package com.singleOrder.model;

import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.sql.DriverManager;

public class SingleOrderDAO implements SingleOrder_interface {
	private final static String SELECT_STMT = "SELECT * FROM SINGLE_ORDER WHERE ORDER_ID=?";
	private final static String SELECT_ALL_STMT = "SELECT * FROM SINGLE_ORDER";
    private final static String UPDATE_STMT = "UPDATE SINGLE_ORDER SET DRIVER_ID=?, STATE=?, START_TIME=?, END_TIME=?, " +
                                                                      "START_LOC=?, END_LOC=?, START_LNG=?, START_LAT=?, " +
                                                                      "END_LNG=?, END_LAT=?, TOTAL_AMOUNT=?, RATE=? " +
                                                                      "WHERE ORDER_ID=?";
    private final static String INSERT_STMT = "INSERT INTO SINGLE_ORDER(ORDER_ID, MEM_ID, STATE, START_TIME, " + 
                                                                  "START_LOC, END_LOC, START_LNG, START_LAT, " +
                                                                  "END_LNG, END_LAT, TOTAL_AMOUNT, ORDER_TYPE, " +
                                                                  "NOTE, LAUNCH_TIME) " + 
                                                                  "VALUES (SEQ_SINGLE_ORDER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            preparedStatement.setDate(index++, singleOrderVO.getStartTime());
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
            preparedStatement.setDate(index++, singleOrderVO.getStartTime());
            preparedStatement.setDate(index++, singleOrderVO.getEndTime());
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
