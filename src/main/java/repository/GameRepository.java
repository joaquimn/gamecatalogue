package repository;

import domain.*;
import repository.PlatformRepository;
import service.PlatformService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class GameRepository extends Dbtools {

    PlatformRepository platformRepository = new PlatformRepository();

    public List<Game> getGameByName(String gameNameP){
        List<Game> games = new ArrayList<>();

        makeJDBCConnection();

        try{
            String query = "select * from view_games where game_name like ?";
            prepareStat = varConn.prepareStatement(query);
            prepareStat.setString(1, "%"+gameNameP+"%");

            ResultSet rs = prepareStat.executeQuery();

            while (rs.next()) {
                String gameId = rs.getString("game_id" );
                String gameName = rs.getString("game_name" );
                String cover = rs.getString("game_cover" );

                String platformId = rs.getString("platform_id");
                String platformName = rs.getString("platform_name");
                String logo = rs.getString("logo");

                Platform platform = new Platform(platformId, platformName, logo);

                Game newGame = new Game(gameId,gameName, cover, platform);
                games.add(newGame);
            }
        } catch (SQLException e){
            e.getErrorCode();
        } finally {
            closeConnection(varConn);
        }

        return games;
    }

    public List<Game> listIndexGames(String type){
        makeJDBCConnection();

        List<Game> games = new ArrayList<Game>();

        try {
            String query = "select * from view_games_"+type+" limit 5";
            prepareStat = varConn.prepareStatement(query);
            ResultSet rs = prepareStat.executeQuery();

            while (rs.next()) {
                String gameId = rs.getString("game_id");
                String gameName = rs.getString("game_name");
                String cover = rs.getString("game_cover");

                String platformId = rs.getString("platform_id");
                String platformName = rs.getString("platform_name");
                String platformLogo = rs.getString("logo");

                Platform platform = new Platform( platformId, platformName, platformLogo);

                Game newGame = new Game(gameId, gameName, cover, platform);
                games.add(newGame);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(varConn);
        }

        return games;
    }

    public Game findGameById( String gameId, String platformId )
    {
        Game game = new Game();
        makeJDBCConnection();

        try{
            Genre genre = null;
            Platform platform = null;
            Producer producer = null;
            Region region = null;
            Status status = null;

            String query = "select a.game_name, a.game_description, a.released_at, b.genre_name, " +
                    "c.producer_name, " +
                    "d.game_cover, " +
                    "e.platform_name, e.platform_id, e.logo " +
                    "from games a " +
                    "left join genres b on (a.genre_id = b.genre_id) " +
                    "left join producers c on (a.producer_id = c.produce_id) " +
                    "left join games_platforms d on (a.game_id = d.game_id) " +
                    "left join platforms e on (d.platform_id = e.platform_id) " +
                    "where a.game_id = ? and e.platform_id = ? and a.flg_active = 1";

            prepareStat = varConn.prepareStatement(query);
            prepareStat.setString(1, gameId);
            prepareStat.setString(2, platformId);

            ResultSet rs = prepareStat.executeQuery();

            while (rs.next()) {
                String gameName = rs.getString("game_name" );
                String description = rs.getString("game_description" );
                String cover = rs.getString("game_cover");
                boolean flgActive = true;
                String released = rs.getString("released_at");
                String createdAt = null;

                String genreId = null;
                String genreName = rs.getString("genre_name");

                genre = new Genre(genreId, genreName);

                String platformName = rs.getString("platform_name");
                String platformLogo = rs.getString("logo");

                platform = new Platform(platformId, platformName, platformLogo);

                String producerId = null;
                String producerName = rs.getString("producer_name");

                producer = new Producer(producerId, producerName);

                game = new Game(gameId, gameName, description, cover, flgActive, released, createdAt, genre, platform, producer, region, status);
            }
        } catch (SQLException e){
            e.getErrorCode();
        } finally {
            closeConnection(varConn);
        }

        return game;
    }

    public Map findWishHaveUserGameList( String userId, String statusId )
    {
        Map platformGames = new LinkedHashMap();
        List<Platform> platforms = platformRepository.findPlatformsByUserAndStatus(userId, statusId);

        makeJDBCConnection();
        
        try {
            if( !platforms.isEmpty() ){

                for ( Platform platform : platforms ) {

                    String query = "SELECT c.game_id, c.game_name, b.game_cover, d.platform_id, d.logo, d.platform_name " +
                            "FROM games_users a " +
                            "LEFT JOIN games_platforms b ON( a.game_platform_id = b.game_platform_id) " +
                            "LEFT JOIN games c ON(c.game_id = b.game_id) " +
                            "LEFT JOIN platforms d ON(d.platform_id = b.platform_id) " +
                            "WHERE " +
                            "a.user_id = ? AND  " +
                            "a.status_id = ? AND " +
                            "d.platform_id = ? " +
                            "ORDER BY c.game_name";

                    prepareStat = varConn.prepareStatement(query);

                    prepareStat.setString(1, userId);
                    prepareStat.setString(2, statusId);

                    String platformId = platform.getPlatformId();
                    prepareStat.setString(3, platformId);

                    ResultSet rs = prepareStat.executeQuery();

                    List<Game> games = new ArrayList<>();

                    while (rs.next()){

                        String gameId = rs.getString("game_id");
                        String gameName = rs.getString("game_name");
                        String cover = rs.getString("game_cover");

                        Game game = new Game(gameId, gameName, cover, platform);

                        games.add(game);
                    }

                    platformGames.put(platform, games);
                }
            }

        } catch (SQLException e){
        e.getErrorCode();
        } finally {
            closeConnection(varConn);
        }

        return platformGames;
    }

    public void insertGameUser(String gameUserId, String gamePlatformId, String userId, String statusId){

        makeJDBCConnection();

        try {
            String query = "INSERT INTO games_users (game_user_id, game_platform_id, user_id, status_id) VALUES (?, ?, ?, ?);";

            prepareStat = varConn.prepareStatement(query);

            prepareStat.setString(1, gameUserId);
            prepareStat.setString(2, gamePlatformId);
            prepareStat.setString(3, userId);
            prepareStat.setString(4, statusId);

            prepareStat.executeUpdate();

        } catch (SQLException e){
            e.getErrorCode();
        } finally {
            closeConnection(varConn);
        }
    }

    public String returnGamePlatformId( String gameId, String platformId ){
        makeJDBCConnection();
        String gamePlatformId = null;
        try {
            String query = "select * from games_platforms where game_id= ? and platform_id = ?";

            prepareStat = varConn.prepareStatement(query);

            prepareStat.setString(1, gameId);
            prepareStat.setString(2, platformId);



            ResultSet rs = prepareStat.executeQuery();
            while (rs.next()){
                gamePlatformId = rs.getString("game_platform_id");
            }
        } catch (SQLException e){
            e.getErrorCode();
        } finally {
            closeConnection(varConn);
        }
        return gamePlatformId;
    }
}
