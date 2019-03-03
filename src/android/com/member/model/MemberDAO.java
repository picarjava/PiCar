package android.com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberVO;

public class MemberDAO implements MemberDAO_Interface {
    private final static String GET_ONE_BY_EMAIL_PASSWORD_STMT = "SELECT * FROM MEMBER WHERE EMAIL=? AND PASSWORD=?";
    private static DataSource dataSource;
    
    static {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TestDB");
        } catch (NamingException e) {
            e.printStackTrace();
        } // catch
    } // static
    
    @Override
    public MemberVO findByEmailAndPassword(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        MemberVO memberVO = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(GET_ONE_BY_EMAIL_PASSWORD_STMT);
            int index = 1;
            preparedStatement.setString(index++, email);
            preparedStatement.setString(index++, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                memberVO = new MemberVO();
                memberVO.setMemID(resultSet.getString("MEM_ID"));
                memberVO.setName(resultSet.getString("NAME"));
                memberVO.setEmail(resultSet.getString("EMAIL"));
                memberVO.setPassword(resultSet.getString("PASSWORD"));
                memberVO.setPhone(resultSet.getString("PHONE"));
                memberVO.setCreditcard(resultSet.getString("CREDIT_CARD"));
                memberVO.setPet(resultSet.getInt("PET"));
                memberVO.setSmoke(resultSet.getInt("SMOKE"));
                memberVO.setGender(resultSet.getInt("GENDER"));
                memberVO.setToken(resultSet.getInt("TOKEN"));
                memberVO.setActivityToken(resultSet.getInt("ACTIVITY_TOKEN"));
                memberVO.setBirthday(resultSet.getDate("BIRTHDAY"));
                memberVO.setVerified(resultSet.getInt("VERIFIED"));
                memberVO.setBabySeat(resultSet.getInt("BABY_SEAT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
        
        System.out.println(memberVO);
        return memberVO;
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
}
