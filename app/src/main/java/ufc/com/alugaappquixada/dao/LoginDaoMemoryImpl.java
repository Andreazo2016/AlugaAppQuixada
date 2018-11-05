package ufc.com.alugaappquixada.dao;

import android.content.Context;

import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.service.UserService;

public class LoginDaoMemoryImpl implements LoginDao {
    private static LoginDaoMemoryImpl instance;
    private UserService userService;
    private Context ctx;
    private LoginDaoMemoryImpl(Context ctx){
        this.ctx = ctx;
        this.userService = UserService.createWithContext(ctx);
    }

    public static LoginDaoMemoryImpl getInstance(Context ctx){
        if(instance == null){
            instance = new LoginDaoMemoryImpl(ctx);
        }
        return instance;
    }
    @Override
    public User findUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }
}
