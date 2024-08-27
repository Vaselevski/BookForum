package cn.it.web.bookforum.servlet.post;

import cn.it.web.bookforum.common.MybatisUtil;
import cn.it.web.bookforum.entityclass.Comment;
import cn.it.web.bookforum.common.URLParser;
import cn.it.web.bookforum.entityclass.User;
import cn.it.web.bookforum.mapper.CommentService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

//*应该是想要创建的评论对应的书籍ID
@WebServlet("/CreateComment/*")
public class CreateComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("sucessful");
        String text = request.getParameter("text");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int parentId = Integer.parseInt(request.getParameter("parentId"));


        HttpSession session = request.getSession(false);
        User user=(User)session.getAttribute("user");
        Comment com=new Comment();
        int userId=user.getUserId();
        String username=user.getUsername();

        com.setParentCommentId(parentId);
        com.setComment(text);
        com.setUserId(userId);
        com.setUsername(username);
        com.setBookId(bookId);
        try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
            CommentService commentService = sqlSession.getMapper(CommentService.class);
            commentService.addComment(com);
        }


    }
}
