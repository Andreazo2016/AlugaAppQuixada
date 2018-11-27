package ufc.com.alugaappquixada.dao;

import android.content.Context;

import java.util.concurrent.ExecutionException;

import ufc.com.alugaappquixada.Async.UserAsync;
import ufc.com.alugaappquixada.Async.UserLoginAsync;
import ufc.com.alugaappquixada.Config.ConfigRetrofit;
import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.interfaceHelp.HalperUser;
import ufc.com.alugaappquixada.repository.UserRepository;
import ufc.com.alugaappquixada.util.Util;

public class UserDaoMemoryImpl implements UserDao,HalperUser {
    private static UserDaoMemoryImpl instance;
    private Context ctx;
    private User newFound;

    private UserDaoMemoryImpl(){}

    private UserDaoMemoryImpl(Context ctx){
        this.ctx = ctx;

    }
    public static UserDaoMemoryImpl createWithContext(Context ctx){
        if(instance == null){
            instance = new UserDaoMemoryImpl(ctx);
        }
        return instance;
    }
    @Override
    public void save(User user) {
        new UserAsync().execute(user);
    }
    @Override
    public User findOne(String email) {
        return Util.getUserLogged(ctx);
    }

    @Override
    public void salveUser(User user) {
        this.newFound = user;
    }

}
