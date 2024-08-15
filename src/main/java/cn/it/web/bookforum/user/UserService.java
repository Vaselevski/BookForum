package cn.it.web.bookforum.user;

import java.sql.SQLException;

public interface UserService {
    User seachUser(String username) throws SQLException;
    User searchUser(int id) throws SQLException;
    void  CreateUser(User user) throws SQLException;
    boolean varifyUser(String username) throws SQLException;
}
