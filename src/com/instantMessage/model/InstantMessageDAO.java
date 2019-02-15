package com.instantMessage.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class InstantMessageDAO implements InstantMessageInterface {
    private final static String SELECT = "SELECT * FROM INSTANT_MESSAGE WHERE MEM_ID=? AND START_TIME=?";
    private final static String SELECT_ALL = "SELECT * FROM INSTANT_MESSAGE";
    private final static String UPDATE = "UPDATE INSTANT_MESSAGE SET MSG_RECORD=? WHERE MEM_ID=? AND START_TIME=?";
    private final static String INSERT = "INSERT INTO INSTANT_MESSAGE(MSG_RECORD, MEM_ID, START_TIME) VALUES (?, ?, ?)";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private final static String NAME = "CA106";
    private static DataSource dataSource;
    
//  static {
//  try {
//      Context context = new InitialContext();
//      dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TestDB");
//  } catch (NamingException e) {
//      e.printStackTrace();
//  }
//}

//    public static void main(String[] args) throws ClassNotFoundException, ParseException {
//      Class.forName("oracle.jdbc.driver.OracleDriver");
//      InstantMessageDAO instantMessageDAO = new InstantMessageDAO();
//      InstantMessageVO instantMessageVO = new InstantMessageVO();
//      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//      instantMessageVO.setMemId("M004");
//      instantMessageVO.setStartTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
//      instantMessageVO.setMsgRecord("Hello");
//      instantMessageDAO.insert(instantMessageVO);
//      System.out.println(instantMessageDAO.findByPrimaryKey(instantMessageVO.getMemId(), instantMessageVO.getStartTime()));
//      instantMessageDAO.update(instantMessageVO);
//      System.out.println(instantMessageDAO.findByPrimaryKey(instantMessageVO.getMemId(), instantMessageVO.getStartTime()));
//      instantMessageDAO.delete(instantMessageVO.getMemId(), instantMessageVO.getStartTime());
//      System.out.println(instantMessageDAO.getAll());
//    } // main()
    
    @Override
    public InstantMessageVO findByPrimaryKey(String memId, Date startTime) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InstantMessageVO instantMessageVO = null;
        try {
            int index = 1;
//          connection = dataSource.getConnection();
            connection = DriverManager.getConnection(URL, NAME, NAME);
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setString(index++, memId);
            preparedStatement.setDate(index++, startTime);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               instantMessageVO = getInstantMessageVO(resultSet); 
            } // if
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
        
        return instantMessageVO;
    } // findByPrimaryKey()

    @Override
    public void insert(InstantMessageVO instantMessageVO) {
        excuteUpdate(instantMessageVO, INSERT);
    } // insert()

    @Override
    public void update(InstantMessageVO instantMessageVO) {
        excuteUpdate(instantMessageVO, UPDATE);
    } // update()

    @Override
    public List<InstantMessageVO> getAll() {
        List<InstantMessageVO> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
//          connection = dataSource.getConnection();
            connection = DriverManager.getConnection(URL, NAME, NAME);
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               list.add(getInstantMessageVO(resultSet)); 
            } // while
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
        
        return list;
    } // getAll()
    
    private void excuteUpdate(InstantMessageVO instantMessageVO, String sql) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            int index = 1;
//          connection = dataSource.getConnection();
            connection = DriverManager.getConnection(URL, NAME, NAME);
            preparedStatement = connection.prepareStatement(sql);
            // INSERT statement order: msg_record mem_id start_time        
            preparedStatement.setString(index++, instantMessageVO.getMemId());
            preparedStatement.setDate(index++, instantMessageVO.getStartTime()); 
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
    } // excuteUpdate()

    private InstantMessageVO getInstantMessageVO(ResultSet resultSet) throws SQLException {
        int index = 1;
        InstantMessageVO instantMessageVO = new InstantMessageVO();
        instantMessageVO.setMemId(resultSet.getString(index++));
        instantMessageVO.setStartTime(resultSet.getDate(index++));
        Reader reader = resultSet.getCharacterStream(index++);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        String input;
        try {
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input);
            } // while
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeReader(bufferedReader);
            closeReader(reader);
        } // finally
        
        instantMessageVO.setMsgRecord(stringBuilder.toString());
        return instantMessageVO;
    } // getInstantMessageVO()
    
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
    
    private void closeReader(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } // catch
        } // if
    } // closeReader()
} // class InstantMessageDAO
