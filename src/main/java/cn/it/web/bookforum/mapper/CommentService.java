package cn.it.web.bookforum.mapper;

import cn.it.web.bookforum.entityclass.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommentService {
    @Insert("INSERT INTO comments (user_id,username,parent_comment_id,book_id,comment) VALUES (#{userId},#{username},#{parentCommentId},#{bookId},#{comment})")
    void addComment(Comment comment);
    @Delete("DELETE FROM comments WHERE id = #{commentId}")
    void deleteCommentById(int id);
    @Select("SELECT * FROM comments WHERE book_id = #{bookId} order by comment_likes_count DESC ")
    List<Comment> searchCommentsByLikes(int bookId);
    @Select("SELECT * FROM comments WHERE book_id = #{bookId} order by comment_create_at DESC ")
    List<Comment> searchCommentsByTime(int bookId);
    @Select("SELECT * FROM comments WHERE id = #{commentId}")
    Comment searchCommentById(int id) throws SQLException;
}
