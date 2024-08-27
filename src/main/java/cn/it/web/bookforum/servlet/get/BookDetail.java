package cn.it.web.bookforum.servlet.get;
import cn.it.web.bookforum.common.MybatisUtil;
import cn.it.web.bookforum.entityclass.Book;
import cn.it.web.bookforum.common.URLParser;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.PrintWriter;

//回传的*应该是要展示的书籍的ID
@WebServlet("/Book/Detail/*")
public class BookDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        URLParser urlParser=new URLParser();
        Integer bookID= Integer.valueOf(urlParser.extractContentAfterString(req,"/Book/Detail"));
        Book book;
        try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
            cn.it.web.bookforum.mapper.BookService bookService = sqlSession.getMapper(cn.it.web.bookforum.mapper.BookService.class);
            book = bookService.searchBookById(bookID);
        }
        Gson gson = new Gson();
        String jsonBook = gson.toJson(book);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(jsonBook);
        out.flush();


    }
}
