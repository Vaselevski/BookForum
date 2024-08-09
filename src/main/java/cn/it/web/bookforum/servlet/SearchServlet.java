package cn.it.web.bookforum.servlet;

import cn.it.web.bookforum.user.UserServiceJdbc;
import cn.it.web.bookforum.user.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/searchbook")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        HttpSession session = req.getSession(false);
        session.setAttribute("bookId", bookId);
        req.getRequestDispatcher("/results.html").forward(req, resp);
    }

}
