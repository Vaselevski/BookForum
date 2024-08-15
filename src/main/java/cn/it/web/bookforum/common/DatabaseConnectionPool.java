package cn.it.web.bookforum.common;
import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

public class DatabaseConnectionPool {
    public static DatabaseConnectionPool instance;
    private final Queue<Connection> connectionPool = new LinkedList<>();
    private final int maxPoolSize=50;
    private final long maxWaitTime=3000;
    private long waitInterval = 100;
    private final String url = "jdbc:postgresql://localhost:5432/bookforum";
    private final String user = "postgres";
    private final String password = "951640";


    private DatabaseConnectionPool() {
        initializePool();
    }

    public static synchronized DatabaseConnectionPool getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionPool();
        }
        return instance;
    }

    private void initializePool() {
        for (int i = 0; i < maxPoolSize; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionPool.offer(connection);
            } catch (SQLException e) {
                throw new RuntimeException("Error initializing connection pool", e);
            }
        }
    }

    public synchronized Connection getConnection() throws SQLException {
        long startTime = System.currentTimeMillis();
        while (connectionPool.isEmpty()) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            long waitTime = maxWaitTime - elapsedTime;
            if (waitTime <= 0) {
                throw new SQLException("Timeout waiting for connection");
            }
            try {
                wait(Math.min(waitInterval, waitTime));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new SQLException("Interrupted while waiting for connection", e);
            }
        }
        return connectionPool.poll();
    }

    public synchronized void releaseConnection(Connection connection) {
        connectionPool.offer(connection);
        notify();
    }
}
