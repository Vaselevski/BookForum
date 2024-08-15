package cn.it.web.bookforum.commentlikes;

import cn.it.web.bookforum.common.DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentLikesJdbc implements CommentLikesService {

    @Override
    public void addCommentLike(CommentLikes commentLikes) throws SQLException {
        String sql = "INSERT INTO comment_likes (userid,bookid) VALUES (?,?)";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql);){
            preparedStatement.setInt(1,commentLikes.getUserid());
            preparedStatement.setInt(2,commentLikes.getCommentid());
            int AffectedRows=preparedStatement.executeUpdate();
            if(AffectedRows==0){
                System.out.println("addCommentLike failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void deleteCommentLike(CommentLikes commentLikes) throws SQLException {
        String sql = "DELETE FROM comment_likes WHERE user_id=? AND comment_id=?";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql);){
            preparedStatement.setInt(1,commentLikes.getUserid());
            preparedStatement.setInt(2,commentLikes.getCommentid());
            int AffectedRows=preparedStatement.executeUpdate();
            if(AffectedRows==0){
                System.out.println("deleteCommentLike failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }
}
