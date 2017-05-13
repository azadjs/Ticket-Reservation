package az.ticket.db;

import az.ticket.beans.User;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.*;

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
        String driverName = "com.mysql.jdbc.Driver";
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

    public void unBlockUser(User user) {
        int founded = 0;
        try {
            connect();
            String query = "update users set status = 1 where username = ?";
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

    public User getUser(User user) {

        try {
            connect();
            String sqlQuery = "select * from users u where u.username = ?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getUsername());
            resultSet = preparedStatement.executeQuery();
            user = null;
            while (resultSet.next()) {
                user = new User(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getDate(6), resultSet.getDate(7), resultSet.getInt(8) == 1,
                        resultSet.getInt(9));
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
        return user;
    }

    public void registerUser(User user) {
        try {
            connect();
            String sqlQuery = "INSERT INTO users (usersname,password,email,fullname,"
                    + "registered,status,attempts) VALUES (?,?,?,?,sysdate(),1,0)";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFullname());
            preparedStatement.execute();
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

    public void deleteUser(User user) {
        try {
            connect();
            String sqlQuery = "delete from users where username = ?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.execute();
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

    public void updateUser(User user) {
        try {
            connect();
            String sqlQuery = "update users set username = ?,password = ?,email = ?"
                    + ",fullname = ? where id = ?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFullname());
            preparedStatement.setLong(5, user.getUserId());
            preparedStatement.executeUpdate();
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
}
