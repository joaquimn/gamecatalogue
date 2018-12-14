package repository;

import domain.City;
import domain.Province;
import domain.User;

import java.security.Provider;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends Dbtools  {

    public boolean createUser(User user) {

        makeJDBCConnection();

        try {
            String userEmail = user.getUserEmail();
            String userId = user.getUserId();
            String userName = user.getUserName();
            String postalCode = user.getPostalCode();
            String picture = user.getPicture();
            String password = user.getPassword();

            City userCity = user.getUserCity();

            int cityId = userCity.getCityId();
            int flgActive = 1;


            String insertQueryStatement = "INSERT INTO users (user_id, user_name, email, city_id, postal_code, picture, flg_active, password) " +
                                          "VALUES (?,?,?,?,?,?,?,?);";

            prepareStat = varConn.prepareStatement(insertQueryStatement);

            prepareStat.setString(1, userId);
            prepareStat.setString(2, userName);
            prepareStat.setString(3, userEmail);
            prepareStat.setInt(4, cityId);
            prepareStat.setString(5, postalCode);
            prepareStat.setString(6, picture);
            prepareStat.setInt(7, flgActive);
            prepareStat.setString(8, password);

            // execute insert SQL statement
            prepareStat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(varConn);
        }

        return false;
    }

    public boolean checkUserCity(User user){

        boolean result = false;

        makeJDBCConnection();
        try{
            City userCity = user.getUserCity();
            int cityId = userCity.getCityId();

            String getQueryStatement = "SELECT * FROM  cities WHERE city_id = ?";
            prepareStat = varConn.prepareStatement(getQueryStatement);
            prepareStat.setInt(1, cityId );
            ResultSet rs = prepareStat.executeQuery();

            if(rs.isBeforeFirst() ){
                result = true;

            } else {
                result = false;
            }

        } catch (SQLException e){
            log(e.getMessage());
        } finally {
            closeConnection(varConn);
        }

        return result;
    }

    public User getUserById(String userId){

        User user = null;

        makeJDBCConnection();

        try {
            // MySQL Select Query
            String getQueryStatement = "SELECT * FROM view_user WHERE user_id = ? AND flg_active = 1 LIMIT 1";

            prepareStat = varConn.prepareStatement(getQueryStatement);
            prepareStat.setString(1, userId);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = prepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            if (rs.isBeforeFirst()) {

                while (rs.next()) {

                    String userid = rs.getString("user_id");
                    String userName = rs.getString("user_name");
                    String userEmail = rs.getString("email");
                    String postalCode = rs.getString("postal_code");

                    int flgActive = rs.getInt("flg_active");
                    String picture = rs.getString("picture");
                    String password = rs.getString("password");
                    String createdAt = rs.getString("created_at");

                    int userProvinceInt = rs.getInt("province_id");
                    String userProvince = rs.getString("province_name");

                    Province cityProvinceObj = new Province(userProvinceInt, userProvince);

                    int userCityId = rs.getInt("city_id");
                    String userCityName = rs.getString("city_name");

                    City userCityObj = new City(cityProvinceObj, userCityId, userCityName);


                    user = new User(userId, userName, userEmail, postalCode, userCityObj, flgActive, picture, password, createdAt);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(varConn);

        }

        return user;
    }

    public boolean checkUserByEmail( User user ){

        boolean result = false;

        makeJDBCConnection();
        try {
            String userEmail = user.getUserEmail();

            String getQueryStatement = "SELECT * FROM users WHERE email = ?";
            prepareStat = varConn.prepareStatement(getQueryStatement);
            prepareStat.setString( 1, userEmail );
            ResultSet rs = prepareStat.executeQuery();

            if(rs.isBeforeFirst() ){
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException e) {
            log(e.getMessage());
        } finally {
            closeConnection(varConn);
        }

        return result;
    }


    public boolean checkUserById( User user ){

        boolean result = false;

        makeJDBCConnection();

        try {
            String userId = user.getUserId();

            String getQueryStatement = "SELECT * FROM users WHERE user_id = ?";
            prepareStat = varConn.prepareStatement(getQueryStatement);
            prepareStat.setString( 1, userId );
            ResultSet rs = prepareStat.executeQuery();

            if(rs.isBeforeFirst() ){
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException e) {
            log(e.getMessage());
        } finally {
            closeConnection(varConn );
        }

        return result;
    }

    public User getUserByEmailPwd(String email, String pwd){

        makeJDBCConnection();

        User user = new User();

        try{
            String query = "select * from  view_user where email =  ? and password = ?";

            prepareStat = varConn.prepareStatement(query);
            prepareStat.setString(1, email);
            prepareStat.setString(2, pwd);

            ResultSet rs = prepareStat.executeQuery();


            if (rs.next()) {
                String userid = rs.getString("user_id");
                String userName = rs.getString("user_name");
                String userEmail = rs.getString("email");
                String postalCode = rs.getString("postal_code");
                int flgActive = rs.getInt("flg_active");
                String picture = rs.getString("picture");
                String password = rs.getString("password");
                String createdAt = rs.getString("created_at");
                int userProvinceInt = rs.getInt("province_id");
                String userProvince = rs.getString("province_name");
                int userCityId = rs.getInt("city_id");
                String userCityName = rs.getString("city_name");

                Province cityProvinceObj = new Province(userProvinceInt, userProvince);
                City userCityObj = new City(cityProvinceObj, userCityId, userCityName);

                User userFilled = new User(userid, userName, userEmail, postalCode, userCityObj, flgActive, picture, password, createdAt);
                user = userFilled;
            }
        } catch (SQLException e){
           log(e.getMessage());
        } finally {
            closeConnection(varConn);
        }

        return user;
    }

    public boolean updateUser(User user)  {

        boolean updateUserTest = false;

        try {
            String userId = user.getUserId();

            if(checkUserById(user) == true){
                makeJDBCConnection();

                String query = "UPDATE users SET user_name = ?, city_id = ?, postal_code = ?, picture = ?, flg_active = ?, password = ? WHERE user_id = ?";

                prepareStat = varConn.prepareStatement(query);

                prepareStat.setString(1, user.getUserName());

                City cityUserObj = user.getUserCity();
                int cityUserId = cityUserObj.getCityId();
                prepareStat.setInt(2, cityUserId);
                prepareStat.setString(3, user.getPostalCode());
                prepareStat.setString(4, user.getPicture());
                prepareStat.setInt(5, user.getFlgActive());
                prepareStat.setString(6, user.getPassword());
                prepareStat.setString(7, user.getUserId());

                prepareStat.executeUpdate();

                updateUserTest = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(varConn);
        }

        return updateUserTest;
    }

    public List<User> findUserByGameAndStatus(String gameId, String statusId){
        List<User> users = new ArrayList<>();

        makeJDBCConnection();

        try{
            String query = "SELECT * FROM view_finduserbygameandstatus WHERE gameId = ? AND statusId = ? limit 5";

            prepareStat = varConn.prepareStatement(query);
            prepareStat.setString(1, gameId);
            prepareStat.setString(2, statusId);
            ResultSet rs = prepareStat.executeQuery();

            while (rs.next()){

                User user = new User();
                City city = new City();
                Province province = new Province();

                String userId = rs.getString("userId");
                String userName = rs.getString("userName");
                String picture = rs.getString("picture");
                String provinceName = rs.getString("provinceName");
                String cityName = rs.getString("cityName");

                user.setUserId(userId);
                user.setUserName(userName);
                user.setPicture(picture);
                province.setProvinceName(provinceName);
                city.setProvince(province);
                city.setCityName(cityName);
                user.setCity(city);

                users.add(user);
            }

            return users;

        } catch (SQLException e){
            e.getErrorCode();
        } finally {
            closeConnection(varConn);
        }

        return users;
    }
}