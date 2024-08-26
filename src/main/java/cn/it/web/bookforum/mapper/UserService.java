package cn.it.web.bookforum.mapper;

import cn.it.web.bookforum.entityclass.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserService {
    @Select("SELECT * FROM USERS WHERE user_id = #{userid}")
    User searchUserById(int userId);
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User searchUserByUsername(String username);
    @Insert("INSERT INTO USERS (password, username) VALUES (#{password}, #{username})")
    void createUser(User user);
    @Select("SELECT NOT COUNT(*) > 0 FROM users WHERE username = #{username}")
    boolean verifyUser(String username);
}
