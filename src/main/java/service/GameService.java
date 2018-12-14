package service;

import domain.Game;
import domain.User;

import repository.GameRepository;
import repository.UserRepository;

import java.util.*;

public class GameService {

    static UserRepository userRepository = new UserRepository();
    static GameRepository gameRepository = new GameRepository();

    public List<Game> getGameByName(String gameName){
        List<Game> games = new ArrayList<>();

        if( gameName != "")
        {
            games = gameRepository.getGameByName(gameName);
        }
        return games;
    }

    public void addGame( User user, String gameId, String statusId ,String platformId){

        String gameUserId = UUID.randomUUID().toString();
        String gamePlatformId = gameRepository.returnGamePlatformId(gameId, platformId);
        String userId = user.getUserId();

        gameRepository.insertGameUser(gameUserId, gamePlatformId, userId, statusId);
    }

    private String returnGamePlatformId(String gameId, String platformId){

        String gamePlatformId = gameRepository.returnGamePlatformId(gameId, platformId);

        return gamePlatformId;
    }

    public List<Game> listIndexGames(String type){
        List<Game> wishedGames = gameRepository.listIndexGames(type);

        return wishedGames;
    }

    public Game findGameById( String gameId, String platformId ) {
        Game game = gameRepository.findGameById(gameId, platformId);

        return game;
    }

    public Map findWishHaveUserGameList(String userId, String statusId ){
        Map platformGames = new HashMap();

        platformGames = gameRepository.findWishHaveUserGameList(userId, statusId);

        return platformGames;
    }
}