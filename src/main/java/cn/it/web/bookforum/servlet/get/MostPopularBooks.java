package cn.it.web.bookforum.servlet.get;
import cn.it.web.bookforum.common.MybatisUtil;
import cn.it.web.bookforum.entityclass.Book;
import cn.it.web.bookforum.mapper.BookService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//该servlet用于回传最受欢迎的书籍的内容
@WebServlet("/MostPopularBooks")
public class MostPopularBooks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        List<Book>book;
        try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
            BookService bookService = sqlSession.getMapper(BookService.class);
            book = bookService.mostPopularBooks();
        }
        Gson gson = new Gson();
        String jsonBooks = gson.toJson(book);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(jsonBooks);
        out.flush();


    }
}
