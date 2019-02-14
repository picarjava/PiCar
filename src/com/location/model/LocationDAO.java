package com.location.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LocationDAO implements LocationDAOInterface{
    private final static String SELECT = "SELECT * FROM LOCATION WHERE MEM_ID=? AND LOCATION=?";
    private final static String SELECT_ALL = "SELECT * FROM LOCATION";
    private final static String UPDATE = "UPDATE LOCATION SET LOCATION=? WHERE MEM_ID=? AND LOCATION=?";
    private final static String INSERT = "INSERT INTO LOCATION(MEM_ID, LOCATION) VALUES (?, ?)";
    private final static String DELETE = "DELETE FROM LOCATION WHERE MEM_ID=? AND LOCATION=?";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private final static String NAME = "CA106";
    private static DataSource dataSource;
    
//    static {
//        try {
//            Context context = new InitialContext();
//            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TestDB");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }
    
//    public static void main(String[] args) throws ClassNotFoundException {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        LocationDAO locationDAO = new LocationDAO();
//        LocationVO locationVO = new LocationVO("M004", "台北火車站");
//        System.out.println(locationDAO.findByPrimaryKey("M004", "台北火車站"));
//        locationDAO.update(locationVO, "Taipei 101");
//        System.out.println(locationDAO.findByPrimaryKey("M004", "Taipei 101"));
//        System.out.println(locationDAO.getAll());
//    } // main()
    
    @Override
    public LocationVO findByPrimaryKey(String primaryKey1, String primaryKey2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LocationVO locationVO = null;
        try {
//          connection = dataSource.getConnection();
          int index = 1;
          connection = DriverManager.getConnection(URL, NAME, NAME);
          preparedStatement = connection.prepareStatement(SELECT);
          preparedStatement.setString(index++, primaryKey1);
          preparedStatement.setString(index++, primaryKey2);
          resultSet = preparedStatement.executeQuery();
          if (resultSet.next()) {
              locationVO = getLocationVO(resultSet);
          }
        } catch (SQLException e) {
            
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
        
        return locationVO;
    } // findByPrimaryKey()
    
    @Override
    public void insert(LocationVO locationVO) {
        excuteUpdate(locationVO, INSERT, null);
    } // insert()
    
    @Override
    public void update(LocationVO locationVO, String newLocation) {
        excuteUpdate(locationVO, UPDATE, newLocation);
    } // update()
    
    @Override
    public void delete(String memId, String location) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            int index = 1;
//          connection = dataSource.getConnection();
            connection = DriverManager.getConnection(URL, NAME, NAME);
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(index++, memId);
            preparedStatement.setString(index++, location);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
    } // delete()
    
    @Override
    public List<LocationVO> getAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LocationVO> list = new ArrayList<>();
        try {
//            connection = dataSource.getConnection();
            connection = DriverManager.getConnection(URL, NAME, NAME);
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getLocationVO(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
        
        return list;
    } // getAll()
    
    private void excuteUpdate(LocationVO locationVO, String sql, String newLocation) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int index = 1;
        try {
//            connection = dataSource.getConnection();
            connection = DriverManager.getConnection(URL, NAME, NAME);
            preparedStatement = connection.prepareStatement(sql);    
            if (sql.equals(UPDATE))
                preparedStatement.setString(index++, newLocation);
            
            preparedStatement.setString(index++, locationVO.getMemId());
            preparedStatement.setString(index++, locationVO.getLocation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
    } // excuteUpdate()
    
    private LocationVO getLocationVO(ResultSet resultSet) throws SQLException {
        LocationVO locationVO = new LocationVO();
        locationVO.setMemId(resultSet.getString(1));
        locationVO.setLocation(resultSet.getString(2));
        return locationVO; 
    } // getLocationVO()
    
    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } // catch
        } // if
    } // closeResultSet()
    
    private void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } // catch
        } // if
    } // closePreparedStatement()
    
    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } // catch
        } // if
    } // closeConnection()
} // class InstantMessageDAO
