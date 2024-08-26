package cn.it.web.bookforum.bookscore;

import cn.it.web.bookforum.entityclass.BookScore;

import java.sql.SQLException;

public interface BookScoreService {
    void addBookScore(BookScore bookScore) throws SQLException;
    void deleteBookScore(BookScore bookScore) throws SQLException;
}
