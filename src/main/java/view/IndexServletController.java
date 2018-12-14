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
import java.util.*;

public class IndexServletController extends BaseController {

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
                case "/user.jsp":
                    showUser(request, response);
                    break;
                default:
                    listWishedGames(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listWishedGames(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Game> listWishedGames = gameService.listIndexGames("wished");
        List<Game> listOfferedGames = gameService.listIndexGames("offered");

        List<List<Game>> wishedAndOfferedGames = new ArrayList<List<Game>>(2);
        wishedAndOfferedGames.add(listWishedGames);
        wishedAndOfferedGames.add(listOfferedGames);

        request.setAttribute("wishedAndOfferedGames", wishedAndOfferedGames);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void showUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user.jsp");
            String id = request.getParameter("id");

            User user = userService.getUserById(id);
            List<Platform> platforms = platformService.findPlatformsByUser(id);

            request.setAttribute("user", user);
            request.setAttribute("platforms", platforms);
            request.setAttribute("wishStatus", wishStatus);

            String gamesW = "";
            String gamesH = "";
            String userPlatforms = "";

            if(platforms.isEmpty()){
                userPlatforms = user.getUserFirstName()+" does not have any platforms";
            }

            int showForm = 1;
            String userSeesionId = null;

            if( request.getSession().getAttribute("loggedUser") != null )
            {
                User userSession = (User) request.getSession().getAttribute("loggedUser");
                userSeesionId = userSession.getUserId();
            }

            if( userSeesionId == null || userSeesionId == id ){
                showForm = 0;
            }

            Map platformGamesW = gameService.findWishHaveUserGameList(id, wishStatus);
            if( platformGamesW.isEmpty() ){
                gamesW = user.getUserFirstName()+" does not wish any game";
            }

            Map platformGamesH = gameService.findWishHaveUserGameList(id, haveStatus);
            if( platformGamesH.isEmpty() ){
                gamesH = user.getUserFirstName()+" does not have any game";
            }

            request.setAttribute("platformGamesW", platformGamesW);
            request.setAttribute("platformGamesH", platformGamesH);

            request.setAttribute("gamesW", gamesW);
            request.setAttribute("gamesH", gamesH);
            request.setAttribute("userPlatforms", userPlatforms);

            request.setAttribute("showForm", showForm );

            dispatcher.forward(request, response);

        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            listWishedGames(request, response);
        }
    }
}