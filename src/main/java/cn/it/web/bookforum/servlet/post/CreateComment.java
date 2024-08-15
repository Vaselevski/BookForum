package cn.it.web.bookforum.servlet.post;

import cn.it.web.bookforum.comment.Comment;
import cn.it.web.bookforum.comment.CommentsServiceJdbc;
import cn.it.web.bookforum.common.Hash;
import cn.it.web.bookforum.common.URLParser;
import cn.it.web.bookforum.user.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
//*应该是想要创建的评论对应的书籍ID
@WebServlet("/CreateComment/*")
public class CreateComment extends HttpServlet {
    private CommentsServiceJdbc commentsService = new CommentsServiceJdbc();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comment = request.getParameter("comment");
        int parentCommentId=Integer.parseInt(request.getParameter("parentCommentId"));
        HttpSession session = request.getSession(false);
        User user=(User)session.getAttribute("user");
        Comment com=new Comment();
        int userId=user.getUserId();
        String username=user.getUsername();
        URLParser urlParser=new URLParser();
        Integer bookID= Integer.valueOf(urlParser.extractContentAfterString(request,"/CreateComment"));
        com.setParentCommentId(parentCommentId);
        com.setComment(comment);
        com.setUserId(userId);
        com.setUsername(username);
        com.setBookId(bookID);
        try {
            commentsService.addComment(com);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
