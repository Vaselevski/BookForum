package cn.it.web.bookforum;

import cn.it.web.bookforum.common.MybatisUtil;

import cn.it.web.bookforum.mapper.UserJdbc;
import org.apache.ibatis.session.SqlSession;

import java.io.FileNotFoundException;


public class helloworld {
    public static void main(String[] args) throws FileNotFoundException {
        try(SqlSession sqlSession= MybatisUtil.openSession(true);){
            UserJdbc user=sqlSession.getMapper(UserJdbc.class);
            System.out.println(user.searchUserByUsername("Laoda"));
        }


    }
}
