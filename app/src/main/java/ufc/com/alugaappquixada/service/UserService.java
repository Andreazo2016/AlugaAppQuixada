package ufc.com.alugaappquixada.service;

import android.content.Context;

import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.dao.UserDao;
import ufc.com.alugaappquixada.dao.UserDaoMemoryImpl;
import ufc.com.alugaappquixada.util.Util;

public class UserService {
    private static UserService instance;
    private UserDao userDao;
    private Context ctx;
    private UserService(){
    }
    private UserService(Context ctx){
        this.ctx = ctx;
        this.userDao = UserDaoMemoryImpl.createWithContext(ctx);
    }
    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }
    public static UserService createWithContext(Context ctx){
        if(instance == null){
            instance = new UserService(ctx);
        }
        return instance;
    }

    public void salvarUser(User user){
        if(user != null){
            userDao.save(user);
        }
    }
    public User getUserByUsername(String username){

        return userDao.findOne(username);
    }
    public User getUserLogged(){
        return Util.getUserLogged(ctx);
    }
}
