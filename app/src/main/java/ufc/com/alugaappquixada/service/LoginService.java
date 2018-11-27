package ufc.com.alugaappquixada.service;

import android.content.Context;

import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.dao.LoginDao;
import ufc.com.alugaappquixada.dao.LoginDaoMemoryImpl;
import ufc.com.alugaappquixada.interfaceHelp.HalperUser;
import ufc.com.alugaappquixada.response.LoginResponse;
import ufc.com.alugaappquixada.util.Util;

public class LoginService  {
    private LoginDao loginDao;
    private User user;
    private static LoginService instance;
    private Context ctx;
    private LoginService(Context ctx){
        this.ctx= ctx;
        this.loginDao = LoginDaoMemoryImpl.getInstance(ctx);
    }

    public static LoginService getInstance(Context ctx){
        if(instance == null){
            instance =  new LoginService(ctx);
        }
        return instance;
    }
    private boolean userWithUsernameExist(String username){
        this.user = loginDao.findUserByUsername(username);
        if(user != null){
            return this.user.getEmail().equals(username);
        }
        return false;
    }
    private boolean checkPasswords(String passwordDigitedByUser){
     return this.user.getPassword().equals(passwordDigitedByUser);
    }
    private void addUserToSession(){
        Util.saveUserOnSesion(ctx,this.user);
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
                addUserToSession();
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
    public void login(String username,String password){

    }


}
