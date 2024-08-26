package cn.it.web.bookforum.servlet.get;
import cn.it.web.bookforum.entityclass.Comment;
import cn.it.web.bookforum.comment.CommentsServiceJdbc;
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
import java.util.List;
//回传的*应该是要获得评论的书籍的ID
@WebServlet("/Book/Comment/*")
public class BookComment extends HttpServlet {
    private CommentsServiceJdbc commentsService = new CommentsServiceJdbc();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        URLParser urlParser = new URLParser();
        Integer bookID = Integer.valueOf(urlParser.extractContentAfterString(req, "/Book/Comment"));
        List<Comment> comments;
        try {
            comments = commentsService.searchCommentsByTime(bookID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String jsonBook = gson.toJson(comments);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(jsonBook);
        out.flush();
    }
}
