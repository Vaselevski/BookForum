package cn.it.web.bookforum.common;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

@jakarta.servlet.annotation.WebListener
public class WebListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.postgresql.Driver");
            DatabaseConnectionPool.getInstance();
            System.out.println("Database connection pool initialized.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
