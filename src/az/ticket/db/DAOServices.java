package az.ticket.db;

import az.ticket.beans.User;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Azad M
 */
public class DAOServices {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private CallableStatement callableStatement;
    private ResultSet resultSet;

    private void connect() throws ClassNotFoundException, SQLException {
        String driverName = "com.jdbc.mysql.Driver";
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/ticket_db";
        Class.forName(driverName);
        connection = (Connection) DriverManager.getConnection(url, user, password);
    }

    private void disconnect() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (callableStatement != null) {
            callableStatement.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public void login(User user) {
        int founded = 0;
        try {
            connect();
            String query = "select count(*) from users u where u.username = ? and u.password = ? and u.status = 1";
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                founded = resultSet.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }
    
public void blockUser(User user) {
        int founded = 0;
        try {
            connect();
            String query = "update users set status = 0 where username = ?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                founded = resultSet.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }
 public List<User> getUser(){
        
        List<User> getUser = new ArrayList<>();
        try {
            connect();
            String sqlQuery = "select * from users";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
               User user = new User(resultSet.getLong("id"),resultSet.getString("username"),
               resultSet.getString("password"),resultSet.getString("email"),resultSet.getString("fullname"),
               resultSet.getDate("registered"),resultSet.getDate("last_login"),resultSet.getBoolean("status"),
               resultSet.getInt("attempts"));
               getUser.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.err);
        }finally{
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        return getUser;
    }
}
