package cn.it.web.bookforum.servlet.get;
import cn.it.web.bookforum.common.MybatisUtil;
import cn.it.web.bookforum.entityclass.Book;
import cn.it.web.bookforum.common.URLParser;
import cn.it.web.bookforum.mapper.BookService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.net.URLDecoder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//*应该是输入搜索栏的内容
@WebServlet("/ReturnSearch/*")
public class ReturnSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        URLParser urlParser = new URLParser();
        String search = urlParser.extractContentAfterString(req, "/ReturnSearch");
         search = URLDecoder.decode(search, "UTF-8");

        List<Book> books;
        try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
            BookService bookService = sqlSession.getMapper(BookService.class);
            books = bookService.searchBooks(search);
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
