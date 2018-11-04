package ufc.com.alugaappquixada.service;

import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.dao.LoginDao;
import ufc.com.alugaappquixada.dao.LoginDaoMemoryImpl;
import ufc.com.alugaappquixada.response.LoginResponse;

public class LoginService {
    private LoginDao loginDao;
    private User user;
    private static LoginService instance;
    private LoginService(){
        this.loginDao = LoginDaoMemoryImpl.getInstance();
    }

    public static LoginService getInstance(){
        if(instance == null){
            instance =  new LoginService();
        }
        return instance;
    }
    private boolean userWithUsernameExist(String username){
        this.user = loginDao.findUserByUsername(username);
       return this.user.getEmail().equals(username);
    }
    private boolean checkPasswords(String passwordDigitedByUser){
     return this.user.getPassword().equals(passwordDigitedByUser);
    }

    public LoginResponse checkCredentials(String username, String password){
        if(username == null || password == null){
            return LoginResponse
                    .create("Username or Password is null")
                    .setLoginAsStatusFailure();
        }
        if(username.isEmpty() || password.isEmpty()){
            return LoginResponse
                    .create("Username or Password is Empty")
                    .setLoginAsStatusFailure();
        }
        if(userWithUsernameExist(username)){
            if(checkPasswords(password)){
                return LoginResponse
                        .create("User logged with sucessful")
                        .setLoginAsStatusSucess();
            }
            return LoginResponse
                    .create("Password do not match!")
                    .setLoginAsStatusFailure();
        }
        return LoginResponse
                .create("User " + username + " do not existe!")
                .setLoginAsStatusFailure();
    }
}
