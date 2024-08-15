package cn.it.web.bookforum.user;

import cn.it.web.bookforum.common.DatabaseConnectionPool;

import java.sql.*;


public class UserServiceJdbc implements UserService {

    @Override
    public User seachUser(String username) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?";
        Connection connection= DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            user = new User();

            if (rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreateAt(rs.getTimestamp("create_at"));
                user.setAdmin(rs.getBoolean("is_admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return user;
    }

    @Override
    public User searchUser(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";
        Connection connection= DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            user = new User();

            if (rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreateAt(rs.getTimestamp("create_at"));
                user.setAdmin(rs.getBoolean("is_admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return user;
    }

    @Override
    public void CreateUser(User user) throws SQLException {
        String sql="INSERT INTO USERS(password,username) VALUES(?,?)";
        Connection connection = DatabaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            int rowsAffected=preparedStatement.executeUpdate();
            if (rowsAffected==0) {
                System.out.println("create user failed");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public boolean varifyUser(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        Connection connection= DatabaseConnectionPool.getInstance().getConnection();
        boolean varify=true;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                varify=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return varify;
    }

}
