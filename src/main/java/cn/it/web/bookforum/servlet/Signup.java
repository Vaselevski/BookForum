package cn.it.web.bookforum.servlet;
import cn.it.web.bookforum.common.Hash;
import cn.it.web.bookforum.user.User;
import cn.it.web.bookforum.user.UserService;
import cn.it.web.bookforum.user.UserServiceJdbc;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
    private UserService userService = new UserServiceJdbc();
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
            if(password.equals(confirmPassword)){
                if(userService.varifyUser(username)){
                    User user = new User();
                    User user2 = new User();
                    Hash hash = new Hash();
                    user.setUsername(username);
                    user.setPassword(hash.hashString(password));
                    userService.CreateUser(user);
                    user2= userService.seachUser(username);
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user2);
                    resp.sendRedirect("/Home");
                }else {
                    req.getRequestDispatcher("/signup.html").forward(req, resp);
                }
            }else{
                req.getRequestDispatcher("/signup.html").forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}