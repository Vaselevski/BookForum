package cn.it.web.bookforum.servlet.redirect;
import cn.it.web.bookforum.common.HttpUtils;
import cn.it.web.bookforum.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//用于重定向至主要页面书籍详情，同时检测是否登录
@WebServlet("/BookDetail/*")
public class BookDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.disableCaching(resp);
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/Login");
            return;
        }
        req.getRequestDispatcher("/detail.html").forward(req, resp);
    }


}
