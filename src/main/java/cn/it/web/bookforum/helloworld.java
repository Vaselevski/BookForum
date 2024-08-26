package cn.it.web.bookforum;

import cn.it.web.bookforum.common.MybatisUtil;

import cn.it.web.bookforum.mapper.UserService;
import org.apache.ibatis.session.SqlSession;

import java.io.FileNotFoundException;


public class helloworld {
    public static void main(String[] args) throws FileNotFoundException {
        try(SqlSession sqlSession= MybatisUtil.openSession(true);){
            UserService user=sqlSession.getMapper(UserService.class);
            System.out.println(user.searchUserByUsername("Laoda"));
        }


    }
}
