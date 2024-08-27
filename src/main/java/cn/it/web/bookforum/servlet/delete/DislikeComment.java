package cn.it.web.bookforum.servlet.delete;

import cn.it.web.bookforum.common.MybatisUtil;
import cn.it.web.bookforum.entityclass.CommentLikes;
import cn.it.web.bookforum.common.URLParser;
import cn.it.web.bookforum.entityclass.User;
import cn.it.web.bookforum.mapper.CommentLikeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.SQLException;
//回传的*应该是要取消点赞的Comment的ID
@WebServlet("/DislikeComment/*")
public class DislikeComment extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        URLParser urlParser = new URLParser();
        Integer CommentId = Integer.valueOf(urlParser.extractContentAfterString(req, "/DislikeComment"));
        HttpSession session = req.getSession(false);
        User user= (User) session.getAttribute("user");
        int userId=user.getUserId();
        CommentLikes commentLikes=new CommentLikes();
        commentLikes.setUserid(userId);
        commentLikes.setCommentid(CommentId);
        try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
            CommentLikeService commentLikeService =sqlSession.getMapper(CommentLikeService.class);
            commentLikeService.deleteCommentLike(commentLikes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
