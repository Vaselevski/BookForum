package cn.it.web.bookforum.book;

import cn.it.web.bookforum.common.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookServiceJdbc implements BookService {

//Need to pass in a Books object with values for all the properties except id
    @Override
    public  void addBook(Books book) throws SQLException {



        String sql = "INSERT INTO books (isbn,name,author,introduction,author_nationality,price,publication_year,likes_count,type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getIntroduction());
            preparedStatement.setString(5, book.getAuthorNationality());
            preparedStatement.setDouble(6, book.getPrice());
            preparedStatement.setInt(7, book.getPublicationYear());
            preparedStatement.setInt(8, book.getLikesCount());
            preparedStatement.setString(9, book.getType());

            preparedStatement.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }
    }

//Should pass in the id of the line of books that you want to delete
    @Override
    public void deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setObject(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No book found with the specified ID.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }
    }

//Need to pass in a books object that has all of its properties defined
    @Override
    public void updateBook(Books book) throws SQLException {
        String SQL = "UPDATE comments SET "
                + "isbn = ?, "
                + "name = ?, "
                + "author = ?, "
                + "introduction = ?, "
                + "author_nationality = ?, "
                + "price = ?, "
                + "publication_year = ?, "
                + "likes_count = ?, "
                + "type = ? "
                + "WHERE book_id = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            // 设置各个参数
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getIntroduction());
            preparedStatement.setString(5, book.getAuthorNationality());
            preparedStatement.setDouble(6, book.getPrice());
            preparedStatement.setInt(7, book.getPublicationYear());
            preparedStatement.setInt(8, book.getLikesCount());
            preparedStatement.setString(9, book.getType());
            preparedStatement.setInt(10,book.getId());

            // 执行更新操作
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("No book found with the specified ID.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Books> searchBooks(String search) {
        List<Books> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE isbn LIKE ? OR name LIKE ? OR author LIKE ? ORDER BY likes_count DESC ";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            String searchPattern = search != null ? "%" + search + "%" : "%";

            preparedStatement.setString(1, searchPattern);
            preparedStatement.setString(2, searchPattern);
            preparedStatement.setString(3, searchPattern);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Books book = new Books();
                book.setId(rs.getInt("book_id"));
                book.setIsbn(rs.getString("isbn"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setIntroduction(rs.getString("introduction"));
                book.setAuthorNationality(rs.getString("author_nationality"));
                book.setPrice(rs.getDouble("price"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setLikesCount(rs.getInt("likes_count"));
                book.setType(rs.getString("type"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void addBookLikes(int id) throws SQLException {
        String sql = "UPDATE books SET likes_count = likes_count + 1 WHERE book_id = ?";
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Increase likescount successfully.");
            } else {
                System.out.println("Increase likescount failed.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw e;
        }

    }


}

