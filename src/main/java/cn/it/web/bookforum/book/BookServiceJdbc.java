package cn.it.web.bookforum.book;

import cn.it.web.bookforum.common.DatabaseConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookServiceJdbc implements BookService {
//Should pass in the id of the line of books that you want to delete
    @Override
    public void deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE book_id = ?";
        Connection connection = DatabaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No book found with the specified ID.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }
    @Override
    public List<Book> searchBooks(String search) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE book_name ILIKE ? OR book_author ILIKE ? ORDER BY book_score DESC";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String searchPattern = "%" + search + "%";
            preparedStatement.setString(1, searchPattern);
            preparedStatement.setString(2, searchPattern);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookIntroduction(rs.getString("book_introduction"));
                book.setBookAuthorIntroduction(rs.getString("book_author_introduction"));
                book.setBookPrice(rs.getDouble("book_price"));
                book.setBookPublicationYear(rs.getInt("book_publication_year"));
                book.setBookPublisher(rs.getString("book_publisher"));
                book.setBookTotalScore(rs.getLong("book_total_score"));
                book.setBookRatePeople(rs.getInt("book_rate_people"));
                book.setBookScore(rs.getDouble("book_score"));
                book.setBookType(rs.getString("book_type"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return books;
    }
    @Override
    public List<Book> mostPopularBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY book_score DESC LIMIT 20 ";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookIntroduction(rs.getString("book_introduction"));
                book.setBookAuthorIntroduction(rs.getString("book_author_introduction"));
                book.setBookPrice(rs.getDouble("book_price"));
                book.setBookPublicationYear(rs.getInt("book_publication_year"));
                book.setBookPublisher(rs.getString("book_publisher"));
                book.setBookTotalScore(rs.getLong("book_total_score"));
                book.setBookRatePeople(rs.getInt("book_rate_people"));
                book.setBookScore(rs.getDouble("book_score"));
                book.setBookType(rs.getString("book_type"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return books;
    }
    @Override
    public Book getBook(int id) throws SQLException {
        Book book = new Book();
        String sql = "SELECT*FROM BOOKS WHERE book_id = ?";
        Connection connection = null;
        connection=DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                book.setBookId(rs.getInt("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookIntroduction(rs.getString("book_introduction"));
                book.setBookAuthorIntroduction(rs.getString("book_author_introduction"));
                book.setBookPrice(rs.getDouble("book_price"));
                book.setBookPublicationYear(rs.getInt("book_publication_year"));
                book.setBookPublisher(rs.getString("book_publisher"));
                book.setBookTotalScore(rs.getLong("book_total_score"));
                book.setBookRatePeople(rs.getInt("book_rate_people"));
                book.setBookScore(rs.getDouble("book_score"));
                book.setBookType(rs.getString("book_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return book;

    }
    @Override
    public List<Book> mostPopularBooksInType(String type) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE TYPE = ? ORDER BY book_score DESC LIMIT 10 ";
        Connection connection=DatabaseConnectionPool.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,type);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookIntroduction(rs.getString("book_introduction"));
                book.setBookAuthorIntroduction(rs.getString("book_author_introduction"));
                book.setBookPrice(rs.getDouble("book_price"));
                book.setBookPublicationYear(rs.getInt("book_publication_year"));
                book.setBookPublisher(rs.getString("book_publisher"));
                book.setBookTotalScore(rs.getLong("book_total_score"));
                book.setBookRatePeople(rs.getInt("book_rate_people"));
                book.setBookScore(rs.getDouble("book_score"));
                book.setBookType(rs.getString("book_type"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return books;
    }


}

