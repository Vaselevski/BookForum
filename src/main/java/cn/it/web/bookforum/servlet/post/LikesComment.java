package cn.it.web.bookforum.servlet.post;


import cn.it.web.bookforum.entityclass.CommentLikes;
import cn.it.web.bookforum.commentlikes.CommentLikesJdbc;
import cn.it.web.bookforum.common.URLParser;
import cn.it.web.bookforum.entityclass.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LikesComment/*")
public class LikesComment extends HttpServlet {
    private CommentLikesJdbc commentLikesJdbc = new CommentLikesJdbc();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        URLParser urlParser=new URLParser();
        Integer commentId= Integer.valueOf(urlParser.extractContentAfterString(request,"/LikesComment"));
        CommentLikes commentLikes=new CommentLikes();
        commentLikes.setCommentid(commentId);
        HttpSession session=request.getSession(false);
        User user=(User)session.getAttribute("user");
        commentLikes.setUserid(user.getUserId());
        try {
            commentLikesJdbc.addCommentLike(commentLikes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
