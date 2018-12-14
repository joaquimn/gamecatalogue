package view;

import domain.Game;
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

public class GameServletController extends BaseController {

    GameService gameService = new GameService();
    UserService userService = new UserService();
    PlatformService platformService = new PlatformService();
    String wishStatus = "a849bfe3-0cce-47cf-a4fc-804e685eb198";
    String haveStatus = "81f74fdb-bf16-4452-95e6-7343d515118a";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String action = extractAction(request);

            switch (action) {
                case "/game.jsp":
                    showGame(request, response);
                    break;
                case "/gameAdd.jsp":
                    addGame(request, response);
                    break;
                case "/gameSearch.jsp":
                    showListGames(request, response);
                    break;
                default:
                    String path = request.getContextPath();
                    response.sendRedirect(path+"/index");
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String gameId = request.getParameter("gameId");
            String platformId = request.getParameter("platformId");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/game.jsp");
            Game game = gameService.findGameById(gameId, platformId);

            List<User> wUser = userService.findUserByGameAndStatus(gameId, wishStatus);
            List<User> hUser = userService.findUserByGameAndStatus(gameId, haveStatus);

            String wUserError = "";
            String hUserError = "";

            if (hUser == null || hUser.isEmpty()) {
                hUserError = "Nobody wants this game!";
            }

            if (wUser == null || wUser.isEmpty()) {
                wUserError = "Nobody wishes this game!";
            }

            request.setAttribute("wUserError", wUserError);
            request.setAttribute("hUserError", hUserError);
            request.setAttribute("wUser", wUser);
            request.setAttribute("hUser", hUser);
            request.setAttribute("game", game);
            request.setAttribute("wishStatus", wishStatus);
            request.setAttribute("haveStatus", haveStatus);

            dispatcher.forward(request, response);

        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            String path = request.getContextPath();
            response.sendRedirect(path+"/index");
        }
    }

    private void addGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            User user = (User) request.getSession().getAttribute("loggedUser");
            String gameId = request.getParameter("gameId");
            String statusId = request.getParameter("statusId");
            String platformId = request.getParameter("platformId");
            String userId = user.getUserId();

            gameService.addGame(user, gameId, statusId, platformId);

            String path = request.getContextPath();
            response.sendRedirect(path+"/user/user.jsp?id="+userId);

        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            String path = request.getContextPath();
            response.sendRedirect(path+"/index");
        }
    }

    private void showListGames(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String gameName = request.getParameter("gameName");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/gameSearch.jsp");

            String warning = "";

            List<Game> games = new ArrayList<>();

            games = gameService.getGameByName(gameName);

            if( games.isEmpty() ){
                warning = "Game not found";
            }

            request.setAttribute("games", games);
            request.setAttribute("warning", warning);
            request.setAttribute("gameName", gameName);

            dispatcher.forward(request, response);

        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            String path = request.getContextPath();
            response.sendRedirect(path+"/index");
        }
    }
}
