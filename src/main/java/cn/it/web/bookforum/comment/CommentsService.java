package cn.it.web.bookforum.comment;

import cn.it.web.bookforum.entityclass.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentsService {
    void addComment(Comment comment) throws SQLException;
    void deleteComment(int id) throws SQLException;
    List<Comment> searchCommentsByLikes(int id) throws SQLException;
    List<Comment> searchCommentsByTime(int id) throws SQLException;
    Comment getComment(int id) throws SQLException;
}
