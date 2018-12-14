package view;

import domain.Game;
import domain.Platform;
import domain.User;
import exception.EntityNotFoundException;
import service.GameService;
import service.PlatformService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccessServletController extends BaseController {

    GameService gameService = new GameService();
    UserService userService = new UserService();
    PlatformService platformService = new PlatformService();

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

    }
}