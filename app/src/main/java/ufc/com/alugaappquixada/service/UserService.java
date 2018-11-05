package ufc.com.alugaappquixada.service;

import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.dao.UserDao;
import ufc.com.alugaappquixada.dao.UserDaoMemoryImpl;
import ufc.com.alugaappquixada.util.Util;

public class UserService {
    private static UserService instance;
    private UserDao userDao;
    private UserService(){
        this.userDao = UserDaoMemoryImpl.getInstance();
    }
    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
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
        return Util.getUserLogged();
    }
}
