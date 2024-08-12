package cn.it.web.bookforum.servlet;


import cn.it.web.bookforum.comment.Comments;
import cn.it.web.bookforum.comment.CommentsServiceJdbc;
import cn.it.web.bookforum.user.UserServiceJdbc;
import cn.it.web.bookforum.user.Users;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Show")
public class ShowServlet extends HttpServlet {
    private CommentsServiceJdbc commentsService = new CommentsServiceJdbc();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer bookId = (Integer) session.getAttribute("bookId");
        List<Comments> comment;
        try {
            Class.forName("org.postgresql.Driver");
            comment = commentsService.searchCommentsByLikes(bookId);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String jsonComments = gson.toJson(comment);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(jsonComments);
        out.flush();


    }
}
