package service;

import domain.User;
import exception.EntityNotFoundException;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UserService extends UserTools {

    private UserRepository userRepository = new UserRepository();

    public boolean createUser (User user){

        if((userTest(user)) && (!userRepository.checkUserByEmail(user))){
            user.setUserId(UUID.randomUUID().toString());
            String pwd = encryptPassword(user.getPassword());

            if( pwd != null ){
                user.setPassword(pwd);
            } else {
                return false;
            }

            boolean resultCreateUser = userRepository.createUser(user);

            return resultCreateUser;
        }else{
            return false;
        }
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
