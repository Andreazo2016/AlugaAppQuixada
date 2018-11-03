package ufc.com.alugaappquixada.dao;

import ufc.com.alugaappquixada.Model.User;

public class LoginDaoMemoryImpl implements LoginDao {
    private static LoginDaoMemoryImpl instance;
    private LoginDaoMemoryImpl(){}

    public static LoginDaoMemoryImpl getInstance(){
        if(instance == null){
            instance = new LoginDaoMemoryImpl();
        }
        return instance;
    }
    @Override
    public User findUserByUsername(String username) {
        return User.create("andreazo2012@gmail.com","Andreazo","88999071542","123321a");
    }
}
