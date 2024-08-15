package cn.it.web.bookforum.bookscore;

import cn.it.web.bookforum.common.DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookScoreJdbc implements BookScoreService{
    @Override
    public void addBookScore(BookScore bookScore) throws SQLException {
        String sql = "INSERT INTO book_score (user_id,book_id,score) VALUES (?,?,?)";
        Connection connection= DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, bookScore.getUserId());
            preparedStatement.setInt(2, bookScore.getBookId());
            preparedStatement.setInt(3, bookScore.getScore());
            int rowsAffected= preparedStatement.executeUpdate();
            if(rowsAffected==0){
                System.out.println("add comment failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void deleteBookScore(BookScore bookScore) throws SQLException {
        String sql = "DELETE FROM book_score WHERE user_id=? AND book_id=?";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, bookScore.getUserId());
            preparedStatement.setInt(2, bookScore.getBookId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected==0){
                System.out.println("delete comment failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        } finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }

    }
}
