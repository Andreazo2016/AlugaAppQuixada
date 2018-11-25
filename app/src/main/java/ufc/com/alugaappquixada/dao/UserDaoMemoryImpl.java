package ufc.com.alugaappquixada.dao;

import android.content.Context;

import ufc.com.alugaappquixada.Async.UserAsync;
import ufc.com.alugaappquixada.Config.ConfigRetrofit;
import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.repository.UserRepository;
import ufc.com.alugaappquixada.util.Util;

public class UserDaoMemoryImpl implements UserDao{
    private static UserDaoMemoryImpl instance;
    private Context ctx;


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
        return null;
    }
}
