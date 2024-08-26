package cn.it.web.bookforum.commentlikes;

import cn.it.web.bookforum.entityclass.CommentLikes;

import java.sql.SQLException;

public interface CommentLikesService {
    public void addCommentLike(CommentLikes commentLikes) throws SQLException;
    public void deleteCommentLike(CommentLikes commentLikes) throws SQLException;
}
