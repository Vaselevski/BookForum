package cn.it.web.bookforum.user;

import cn.it.web.bookforum.book.Books;
import cn.it.web.bookforum.common.DatabaseConfig;

import java.sql.*;


public class UserServiceJdbc implements UserService {
    public static void main(String[] args) throws SQLException {
        UserServiceJdbc userServiceJdbc = new UserServiceJdbc();
        System.out.println(userServiceJdbc.seachUser("Administrator"));
    }

    @Override
    public Users seachUser(String username) throws SQLException {
        Users user = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            user = new Users();

            if (rs.next()) {
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setLastLoginTime(rs.getTimestamp("last_login_time"));
                user.setCreateAt(rs.getTimestamp("create_at"));
                user.setIsAdmin(rs.getBoolean("is_admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
