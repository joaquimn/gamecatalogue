package view;

import domain.*;
import exception.EntityNotFoundException;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccessServletController extends BaseController {

    GameService gameService = new GameService();
    UserService userService = new UserService();
    PlatformService platformService = new PlatformService();
    ProvinceService provinceService = new ProvinceService();
    CityService cityService = new CityService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            String action = extractAction(request);

            switch (action) {
                case "/signUp/":
                    signUp(request, response);
                    break;
                case "/signIn.jsp":
                    addUser(request, response);
                    break;
                case "/editUser.jsp":
                    editUser(request, response);
                    break;
                default:
                    signUp(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/signUp.jsp");

        User user = (User) request.getSession().getAttribute("loggedUser");
        String title = "Sign up";

        if(user != null ){
            title = "Edit user";
        }

        request.setAttribute("title", title);
        List<City> cities = cityService.listCity();

        int provinceControl = 0;
        request.setAttribute("cities", cities);
        request.setAttribute("provinceControl", provinceControl);

        dispatcher.forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("name");
        String userEmail = request.getParameter("email");
        String postalCode = request.getParameter("postalCode");
        int cityId = Integer.parseInt(request.getParameter("cityId"));
        String password = request.getParameter("password");

        User user = userService.createUser(userName, userEmail, postalCode, cityId, password);

        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            session.setAttribute("nameUser", user.getUserName());
            response.sendRedirect(request.getContextPath() + "/user/user.jsp?id="+user.getUserId());
        }
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User userSession = (User) request.getSession().getAttribute("loggedUser");

        String userId = userSession.getUserId();
        String userEmail = userSession.getUserEmail();
        String userName = request.getParameter("name");
        String postalCode = request.getParameter("postalCode");
        int cityId = Integer.parseInt(request.getParameter("cityId"));
        String password = request.getParameter("password");

        User user = userService.editUser(userId, userName, userEmail, postalCode, cityId, password);

        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            session.setAttribute("nameUser", user.getUserName());
            response.sendRedirect(request.getContextPath() + "/user/user.jsp?id="+user.getUserId());
        }
    }
}