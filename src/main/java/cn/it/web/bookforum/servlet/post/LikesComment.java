package cn.it.web.bookforum.servlet.post;


import cn.it.web.bookforum.common.MybatisUtil;
import cn.it.web.bookforum.entityclass.CommentLikes;
import cn.it.web.bookforum.common.URLParser;
import cn.it.web.bookforum.entityclass.User;
import cn.it.web.bookforum.mapper.CommentLikeService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LikesComment/*")
public class LikesComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        URLParser urlParser=new URLParser();
        Integer commentId= Integer.valueOf(urlParser.extractContentAfterString(request,"/LikesComment"));
        CommentLikes commentLikes=new CommentLikes();
        commentLikes.setCommentid(commentId);
        HttpSession session=request.getSession(false);
        User user=(User)session.getAttribute("user");
        commentLikes.setUserid(user.getUserId());
        try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
            CommentLikeService commentLikeService =sqlSession.getMapper(CommentLikeService.class);
            commentLikeService.addCommentLike(commentLikes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
