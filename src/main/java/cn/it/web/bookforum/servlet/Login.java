package cn.it.web.bookforum.servlet;
import cn.it.web.bookforum.common.Hash;
import cn.it.web.bookforum.common.HttpUtils;
import cn.it.web.bookforum.common.MybatisUtil;
import cn.it.web.bookforum.mapper.UserJdbc;
import cn.it.web.bookforum.entityclass.User;
import cn.it.web.bookforum.user.UserServiceJdbc;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserServiceJdbc userServiceJdbc = new UserServiceJdbc();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpUtils.disableCaching(response);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.getRequestDispatcher("/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user;
            try (SqlSession sqlSession = MybatisUtil.openSession(true);) {
                UserJdbc userJdbc = sqlSession.getMapper(UserJdbc.class);
                user = userJdbc.searchUserByUsername(username);
            }

            Hash hash = new Hash();
            if (user == null) {
                doGet(request, response);
                return;
            }
            if (hash.verifyString(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("/Home");
                System.out.println(user.getUsername());
            } else {
                doGet(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
        }
    }
}
