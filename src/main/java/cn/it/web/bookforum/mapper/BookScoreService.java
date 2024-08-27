package cn.it.web.bookforum.mapper;

import cn.it.web.bookforum.entityclass.BookScore;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookScoreService {
    @Insert("INSERT INTO BOOK_SCORE (user_id,book_id,score) VALUES (#{user_id}, #{book_id},#{score})")
    void addBookScore(BookScore bookScore);
    @Delete("DELETE FROM BOOK_SCORE WHERE book_id = #{bookId} AND user_id = #{userId})")
    void deleteBookScore(BookScore bookScore);
}
