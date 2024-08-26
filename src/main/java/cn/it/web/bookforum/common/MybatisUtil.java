package cn.it.web.bookforum.common;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        InputStream inputStream = MybatisUtil.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    public static SqlSession openSession(boolean autoCommit){
        return sqlSessionFactory.openSession(autoCommit);
    }
    public static void main(String[] args) {
        // 调用 openSession 并开启自动提交
        try (SqlSession session = MybatisUtil.openSession(true)) {
            if (session != null) {
                System.out.println("SqlSession successfully open");
            } else {
                System.out.println("SqlSession did not open");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

