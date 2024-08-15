package cn.it.web.bookforum.servlet.get;
import cn.it.web.bookforum.book.BookService;
import cn.it.web.bookforum.book.BookServiceJdbc;
import cn.it.web.bookforum.book.Book;
import cn.it.web.bookforum.common.URLParser;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
//回传的*应该是要展示的书籍的ID
@WebServlet("/Book/Detail/*")
public class Book_Detail extends HttpServlet {
    private BookService bookService=new BookServiceJdbc();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        URLParser urlParser=new URLParser();
        Integer bookID= Integer.valueOf(urlParser.extractContentAfterString(req,"/Book/Detail"));
        Book book;
        try {
            book = bookService.getBook(bookID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String jsonBook = gson.toJson(book);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(jsonBook);
        out.flush();

        System.out.println("book request successful");
    }
}
