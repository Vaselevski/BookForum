package cn.it.web.bookforum.servlet.get;
import cn.it.web.bookforum.common.CommentUtils;
import cn.it.web.bookforum.common.MybatisUtil;
import cn.it.web.bookforum.entityclass.Comment;
import cn.it.web.bookforum.common.URLParser;
import cn.it.web.bookforum.mapper.CommentService;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//回传的*应该是要获得评论的书籍的ID
@WebServlet("/Book/Comment/*")
public class BookComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        URLParser urlParser = new URLParser();
        Integer bookID = Integer.valueOf(urlParser.extractContentAfterString(req, "/Book/Comment"));
        List<Comment> comments;
        try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
            CommentService commentService = sqlSession.getMapper(CommentService.class);
            comments= commentService.searchCommentsByTime(bookID);
        }
        CommentUtils commentUtils = new CommentUtils();
        List<Comment> sortedComment=commentUtils.processComments(comments);

        Gson gson = new Gson();
        String jsonComment = gson.toJson(sortedComment);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(jsonComment);
        out.flush();
    }
}
