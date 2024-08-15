package cn.it.web.bookforum.servlet.get;
import cn.it.web.bookforum.book.Book;
import cn.it.web.bookforum.book.BookServiceJdbc;
import cn.it.web.bookforum.common.URLParser;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
//*应该是输入搜索栏的内容
@WebServlet("/ReturnSearch/*")
public class ReturnSearch extends HttpServlet {
    private BookServiceJdbc bookServiceJdbc = new BookServiceJdbc();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        URLParser urlParser = new URLParser();
        String search = urlParser.extractContentAfterString(req, "/ReturnSearch");
         search = URLDecoder.decode(search, "UTF-8");
        System.out.println(search);
        List<Book> books;
        try {
            books = bookServiceJdbc.searchBooks(search);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String jsonBook = gson.toJson(books);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(jsonBook);
        out.flush();
    }
}
