package ufc.com.alugaappquixada.dao;

import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.util.Util;

public class UserDaoMemoryImpl implements UserDao{
    private static UserDaoMemoryImpl instance;

    private UserDaoMemoryImpl(){}

    public static UserDaoMemoryImpl getInstance(){
        if(instance == null){
            instance = new UserDaoMemoryImpl();
        }
        return instance;
    }

    @Override
    public void save(User user) {
        Util.saveUserLoged(user);
    }

    @Override
    public User findOne(String email) {
        return Util.getUserByUserName(email);
    }
}
