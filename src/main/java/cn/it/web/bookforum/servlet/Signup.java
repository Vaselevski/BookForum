package cn.it.web.bookforum.servlet;
import cn.it.web.bookforum.common.Hash;
import cn.it.web.bookforum.common.MybatisUtil;
import cn.it.web.bookforum.entityclass.User;
import cn.it.web.bookforum.mapper.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

@WebServlet("/Signup")
public class Signup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        req.getRequestDispatcher("/signup.html").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirm-password");
            try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
                UserService userJdbc = sqlSession.getMapper(UserService.class);
                if(password.equals(confirmPassword)){
                    if(userJdbc.verifyUser(username)){
                        User user = new User();
                        User user2 ;
                        Hash hash = new Hash();
                        user.setUsername(username);
                        user.setPassword(hash.hashString(password));
                        userJdbc.createUser(user);
                        user2= userJdbc.searchUserByUsername(username);
                        HttpSession session = req.getSession();
                        session.setAttribute("user", user2);
                        resp.sendRedirect("/Home");
                    }else {
                        req.getRequestDispatcher("/signup.html").forward(req, resp);
                    }
                }else{
                    req.getRequestDispatcher("/signup.html").forward(req, resp);
                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}