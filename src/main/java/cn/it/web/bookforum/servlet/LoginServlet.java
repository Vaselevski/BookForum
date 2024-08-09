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
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserServiceJdbc userServiceJdbc = new UserServiceJdbc();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


            Class.forName("org.postgresql.Driver");
            Users user = userServiceJdbc.seachUser(username);
            if (user == null) {
                response.sendRedirect("/Login");
            }
            if (password.equals(user.getPassword())){
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("isAdmin", user.GetIsAdmin());
                response.sendRedirect("http://localhost:8080/search.html");
            }else{
                response.sendRedirect("http://localhost:8080/Login");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
        }
    }
}
