package cn.it.web.bookforum.comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentsService {
    void addComment(Comments comment) throws SQLException;
    void deleteComment(int id) throws SQLException;
    void likesComment(int id) throws SQLException;
    void dislikesComment(int id) throws SQLException;
    List<Comments> searchCommentsByLikes(int id);
    List<Comments> searchCommentsByTime(int id);
}
