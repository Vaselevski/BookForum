package cn.it.web.bookforum.common;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;
//在服务器启动时创建一个用于mybatis查询的sqlSessionFactory，并且在之后调用方法时返回用于查询session
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        InputStream inputStream = MybatisUtil.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    public static SqlSession openSession(boolean autoCommit){
        return sqlSessionFactory.openSession(autoCommit);
    }
}

