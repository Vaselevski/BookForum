package cn.it.web.bookforum.comment;

import cn.it.web.bookforum.common.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentsServiceJdbc implements CommentsService {


    //need to pass in a comments object with user_id,book_id, and comment properties
    @Override
    public void addComment(Comments comment) throws SQLException {
        String sql = "INSERT INTO comments (user_id,book_id,comment) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, comment.getUserId());
            preparedStatement.setInt(2, comment.getBookId());
            preparedStatement.setString(3, comment.getComment());

            preparedStatement.executeUpdate();
            System.out.println("Comment added successfully.");
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteComment(int id) throws SQLException {
        String sql = "DELETE FROM comments WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setObject(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No book found with the specified ID.");
            }else{
                System.out.println("Book deleted successfully.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void likesComment(int id) throws SQLException {
            String sql = "UPDATE comments SET likes_count = likes_count + 1 WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Increase likescount successfully.");
            } else {
                System.out.println("Increase likescount failed.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }

    }

    @Override
    public void dislikesComment(int id) throws SQLException {
        String sql = "UPDATE comments SET dislikes_count = dislikes_count + 1 WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1,id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Increase dislikescount successfully.");
            } else {
                System.out.println("Increase dielikescouont failed.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }

    }

    @Override
    public List<Comments> searchCommentsByLikes(int id) {
        List<Comments> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE book_id = ? order by(likes_count - dislikes_count) DESC ";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Comments comment=new Comments();
                comment.setId(rs.getInt("id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setBookId(rs.getInt("book_id"));
                comment.setCreateAt(rs.getTimestamp("create_at"));
                comment.setDislikesCount(rs.getInt("dislikes_count"));
                comment.setLikesCount(rs.getInt("likes_count"));
                comment.setComment(rs.getString("comment"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public List<Comments> searchCommentsByTime(int id) {
        List<Comments> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE book_id = ? order by(create_at) DESC ";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Comments comment=new Comments();
                comment.setId(rs.getInt("id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setBookId(rs.getInt("book_id"));
                comment.setCreateAt(rs.getTimestamp("create_at"));
                comment.setDislikesCount(rs.getInt("dislikes_count"));
                comment.setLikesCount(rs.getInt("likes_count"));
                comment.setComment(rs.getString("comment"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
}
