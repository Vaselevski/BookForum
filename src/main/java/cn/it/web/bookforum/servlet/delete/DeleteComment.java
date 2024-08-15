package cn.it.web.bookforum.servlet.delete;

import cn.it.web.bookforum.comment.Comment;
import cn.it.web.bookforum.comment.CommentsServiceJdbc;
import cn.it.web.bookforum.common.URLParser;
import cn.it.web.bookforum.user.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;


//回传的*应该是要删除的Comment的ID
@WebServlet("/DeleteComment/*")
public class DeleteComment extends HttpServlet {
    private CommentsServiceJdbc commentsService = new CommentsServiceJdbc();
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        URLParser urlParser = new URLParser();
        Integer CommentId = Integer.valueOf(urlParser.extractContentAfterString(request, "/DeleteComment"));
        try {
            Comment comment=commentsService.getComment(CommentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user.getUserId()==CommentId) {
            try {
                commentsService.deleteComment(CommentId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
