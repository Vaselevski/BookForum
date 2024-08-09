package cn.it.web.bookforum.user;

import java.sql.SQLException;

public interface UserService {
    Users seachUser(String username) throws SQLException;
}
