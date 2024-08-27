package cn.it.web.bookforum.servlet.delete;
import cn.it.web.bookforum.common.MybatisUtil;
import cn.it.web.bookforum.entityclass.Comment;
import cn.it.web.bookforum.common.URLParser;
import cn.it.web.bookforum.entityclass.User;
import cn.it.web.bookforum.mapper.CommentService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;
import java.sql.SQLException;
//回传的*应该是要删除的Comment的ID
@WebServlet("/DeleteComment/*")
public class DeleteComment extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        URLParser urlParser = new URLParser();
        Integer CommentId = Integer.valueOf(urlParser.extractContentAfterString(request, "/DeleteComment"));
        Comment comment;
        try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
            CommentService commentService = sqlSession.getMapper(CommentService.class);
            comment= commentService.searchCommentById(CommentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user.getUserId()==comment.getUserId()) {
            try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
                CommentService commentService = sqlSession.getMapper(CommentService.class);
                commentService.deleteCommentById(CommentId);
            }
        }
    }
}
