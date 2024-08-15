package cn.it.web.bookforum.comment;

import cn.it.web.bookforum.common.DatabaseConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentsServiceJdbc implements CommentsService {


    //need to pass in a comments object with user_id,book_id, and comment properties
    @Override
    public void addComment(Comment comment) throws SQLException {
        String sql = "INSERT INTO comments (user_id,username,parent_comment_id,book_id,comment) VALUES (?,?,?,?,?)";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, comment.getUserId());
            preparedStatement.setString(2, comment.getUsername());
            preparedStatement.setInt(3, comment.getParentCommentId());
            preparedStatement.setInt(4, comment.getBookId());
            preparedStatement.setString(5, comment.getComment());
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
    public void deleteComment(int id) throws SQLException {
        String sql = "DELETE FROM comments WHERE id = ?";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No book found with the specified ID.");
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
    public List<Comment> searchCommentsByLikes(int id) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE book_id = ? order by comment_likes_count DESC ";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Comment comment=new Comment();
                comment.setCommentId(rs.getInt("id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setUsername(rs.getString("username"));
                comment.setParentCommentId(rs.getInt("parent_comment_id"));
                comment.setBookId(rs.getInt("book_id"));
                comment.setCommentCreateAt(rs.getTimestamp("comment_create_at"));
                comment.setCommentLikesCount(rs.getInt("comment_likes_count"));
                comment.setComment(rs.getString("comment"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }finally {
             if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return comments;
    }

    @Override
    public List<Comment> searchCommentsByTime(int id) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE book_id = ? order by comment_create_at DESC  ";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Comment comment=new Comment();
                comment.setCommentId(rs.getInt("id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setUsername(rs.getString("username"));
                comment.setParentCommentId(rs.getInt("parent_comment_id"));
                comment.setBookId(rs.getInt("book_id"));
                comment.setCommentCreateAt(rs.getTimestamp("comment_create_at"));
                comment.setCommentLikesCount(rs.getInt("comment_likes_count"));
                comment.setComment(rs.getString("comment"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return comments;
    }

    @Override
    public Comment getComment(int id) throws SQLException {
        String sql = "SELECT * FROM comments WHERE id = ?";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();
        Comment comment=new Comment();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                comment.setCommentId(rs.getInt("id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setUsername(rs.getString("username"));
                comment.setParentCommentId(rs.getInt("parent_comment_id"));
                comment.setBookId(rs.getInt("book_id"));
                comment.setCommentCreateAt(rs.getTimestamp("comment_create_at"));
                comment.setCommentLikesCount(rs.getInt("comment_likes_count"));
                comment.setComment(rs.getString("comment"));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        } finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return comment;
    }

}
