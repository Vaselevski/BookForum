package cn.it.web.bookforum.servlet;

import cn.it.web.bookforum.book.BookServiceJdbc;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/AddBooksLikes")
public class AddBooksLikesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookServiceJdbc bookService = new BookServiceJdbc();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int number = Integer.parseInt(request.getParameter("search"));

            Class.forName("org.postgresql.Driver");
            bookService.addBookLikes(number);

            response.sendRedirect("http://localhost:8080");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
        }
    }
}
