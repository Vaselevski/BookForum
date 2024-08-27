package cn.it.web.bookforum.mapper;

import cn.it.web.bookforum.entityclass.CommentLikes;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface CommentLikeService {
    @Insert("INSERT INTO comment_likes (user_id,comment_id) VALUES (#{userId},#{commentId})")
    void addCommentLike(CommentLikes commentLikes) throws SQLException;
    @Delete("DELETE FROM comment_likes WHERE user_id= #{userId} AND comment_id= #{commentId}")
    void deleteCommentLike(CommentLikes commentLikes) throws SQLException;
}
