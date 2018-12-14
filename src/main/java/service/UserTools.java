package service;

import domain.City;
import domain.User;
import repository.UserRepository;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTools {

    private UserRepository userRepository = new UserRepository();

    protected boolean emailCheck(String email){

        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        if (matcher.find() && matcher.group().equals(email)){
            return true;
        }

        return false;
    }

    protected boolean postalCodeCheck(String postalCode){

        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z]●?[0-9][A-Z][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(postalCode);

        if (matcher.find() && matcher.group().equals(postalCode)){
            return true;
        }

        return false;
    }

    protected boolean userTest(User user){

        if(user.getUserName() == ""){
            return false;
        }

        if((user.getUserEmail() == "") || !emailCheck(user.getUserEmail())){

            return false;
        }

        if(user.getPostalCode() == "" || !postalCodeCheck(user.getPostalCode())){
            return false;
        }

        City city = user.getUserCity();
        int cityId = city.getCityId();

        if(cityId == 0 || !userRepository.checkUserCity(user)){
            return false;
        }

        return true;
    }

    protected boolean checkUserByEmail(User user){

        if( user != null){
            boolean teste = userRepository.checkUserByEmail(user);

            if(teste == true){
                return true;
            }
            return false;
        }

        return false;
    }

    protected String encryptPassword(String pwd)
    {
        String newPwd = null;

        try {
            String password = pwd;
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(pwd.getBytes());
            byte[] bytes = digest.digest();
            BASE64Encoder encoder = new BASE64Encoder();
            newPwd = encoder.encode(bytes);


        } catch (Exception e){
            System.out.println("Password could not be encrypted");
        }

        return newPwd;
    }
}
