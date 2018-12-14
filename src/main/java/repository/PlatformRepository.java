package repository;

import domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PlatformRepository extends Dbtools {

    public List<Platform> findPlatformsByUser(String userId) {

        makeJDBCConnection();

        List<Platform> platforms = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT b.platform_id, c.logo, c.platform_name " +
                    "FROM games_users a " +
                    "LEFT JOIN games_platforms b ON (a.game_platform_id = b.game_platform_id) " +
                    "LEFT JOIN platforms c ON (b.platform_id = c.platform_id) " +
                    "WHERE a.user_id = ? ORDER BY c.platform_name";

            prepareStat = varConn.prepareStatement(query);
            prepareStat.setString(1, userId);

            ResultSet rs = prepareStat.executeQuery();

            while (rs.next()) {

                String platformId = rs.getString("platform_id");
                String platformName = rs.getString("platform_name");
                String platformLogo = rs.getString("logo");
                Platform platform = new Platform(platformId, platformName, platformLogo);

                platforms.add(platform);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(varConn);
        }

        return platforms;
    }

    public List<Platform> findPlatformsByUserAndStatus(String userId, String statusId) {

        makeJDBCConnection();

        List<Platform> platforms = new ArrayList<>();

        try {
            String query = "SELECT c.platform_id, c.platform_name, c.logo " +
                    "FROM games_users a " +
                    "LEFT JOIN games_platforms b ON (a.game_platform_id = b.game_platform_id) " +
                    "LEFT JOIN platforms c ON (b.platform_id = c.platform_id) " +
                    "WHERE a.user_id = ?  and " +
                    "a.status_id = ? " +
                    "GROUP BY c.platform_name ORDER BY c.platform_name ";

            prepareStat = varConn.prepareStatement(query);
            prepareStat.setString(1, userId);
            prepareStat.setString(2, statusId);

            ResultSet rs = prepareStat.executeQuery();

            while (rs.next()) {

                String platformId = rs.getString("platform_id");
                String platformName = rs.getString("platform_name");
                String platformLogo = rs.getString("logo");
                Platform platform = new Platform(platformId, platformName, platformLogo);

                platforms.add(platform);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(varConn);
        }

        return platforms;
    }




}
