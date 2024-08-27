package cn.it.web.bookforum.mapper;

import cn.it.web.bookforum.entityclass.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookService {
    @Delete("DELETE FROM books WHERE book_id = #{id}")
    void deleteBookById(@Param("id") int id);
    @Select("SELECT * FROM books WHERE book_name ILIKE '%' || #{search} || '%' OR book_author ILIKE '%' || #{search} || '%' ORDER BY book_score DESC")
    List<Book> searchBooks(@Param("search") String search);
    @Select("SELECT * FROM books ORDER BY book_score DESC LIMIT 20")
    List<Book> mostPopularBooks();
    @Select("SELECT * FROM books WHERE book_id = #{id}")
    Book searchBookById(@Param("id") int id);
    @Select("SELECT * FROM books WHERE type = #{type} ORDER BY book_score DESC LIMIT 10")
    List<Book> mostPopularBooksInType(@Param("type") String type);

}
