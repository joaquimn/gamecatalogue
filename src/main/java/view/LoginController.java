package view;

import domain.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends BaseController {

    UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userService.getUserByEmailPwd(email, password);
            if(user != null){
                HttpSession session = request.getSession();
                session.setAttribute("loggedUser", user);
                session.setAttribute("nameUser", user.getUserName());
                response.sendRedirect(request.getContextPath() + "/user/user.jsp?id="+user.getUserId());
            }
        } catch (Exception e){
            String path = request.getContextPath();
            response.sendRedirect(path+"/index");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
