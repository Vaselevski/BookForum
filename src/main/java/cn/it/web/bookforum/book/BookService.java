package cn.it.web.bookforum.book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    void deleteBook(int id) throws SQLException;
    List<Book> searchBooks(String search) throws SQLException;
    List<Book> mostPopularBooks() throws SQLException;
    Book getBook(int id) throws SQLException;
    List<Book> mostPopularBooksInType(String type) throws SQLException;
}
