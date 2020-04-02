package service;

import domain.City;
import domain.Province;
import domain.User;
import exception.EntityNotFoundException;
import repository.UserRepository;
import view.LoginController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService extends UserTools {

    private UserRepository userRepository = new UserRepository();

    public User createUser(String userName, String userEmail, String postalCode, int cityId, String password){

        String picture = "generic_avatar.gif";
        Province province = new Province();
        City city = new City(province, cityId, "");
        String userId = UUID.randomUUID().toString();
        String pwd = encryptPassword(password);
        User user = new User(userId, userName, userEmail, postalCode, city, 1, picture, pwd, "");

        if((userTest(user)) && (!userRepository.checkUserByEmail(user))){
            userRepository.createUser(user);
            user = getUserByEmailPwd( userEmail,  password);
        }
        return user;
    }

    public User editUser(String userId, String userName, String userEmail, String postalCode, int cityId, String password){

        User user = new User();

        String pwd = encryptPassword(password);

        boolean rtn = userRepository.editUser(userId, userName, postalCode, cityId, pwd);

        if( rtn == true ){
            user = getUserByEmailPwd( userEmail,  password);
        }
        return user;
    }

    public User getUserById(String userId) {

        User user = new User();

        if( userId != null && userId != ""){
            user = userRepository.getUserById(userId);
        }

        return user;
    }

    public User getUserByEmailPwd(String email, String password){

        password = encryptPassword(password);

        User user = userRepository.getUserByEmailPwd(email, password);

        return user;
    }

    public boolean updateUser(User user){

        boolean testUser = userTest(user);

        if(testUser == true && (userRepository.checkUserByEmail(user) == true)){

            String pwd = encryptPassword(user.getPassword());

            if( pwd != null ){
                user.setPassword(pwd);
            } else {
                return false;
            }

            boolean resultUpdateUser = userRepository.updateUser(user);

            return resultUpdateUser;
        }else{
            return false;
        }
    }

    public List<User> findUserByGameAndStatus(String gameId, String statusId )
    {
        List<User> users = new ArrayList<>();

        users = userRepository.findUserByGameAndStatus(gameId, statusId);

        return users;
    }
}
