package cn.it.web.bookforum.book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    void addBook(Books book) throws SQLException;
    void deleteBook(int id) throws SQLException;
    void updateBook(Books book) throws SQLException;
    List<Books> searchBooks(String search) throws SQLException;
    void addBookLikes(int id) throws SQLException;

}
