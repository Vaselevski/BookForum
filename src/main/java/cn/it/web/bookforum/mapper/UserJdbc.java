package cn.it.web.bookforum.mapper;

import cn.it.web.bookforum.entityclass.User;

public interface UserJdbc {
    User searchUserById(int userId);
    User searchUserByUsername(String username);
    void createUser(User user);
    boolean verifyUser(String username);
}
