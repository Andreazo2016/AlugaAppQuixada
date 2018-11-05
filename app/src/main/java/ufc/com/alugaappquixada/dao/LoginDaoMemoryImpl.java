package ufc.com.alugaappquixada.dao;

import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.service.UserService;

public class LoginDaoMemoryImpl implements LoginDao {
    private static LoginDaoMemoryImpl instance;
    private UserService userService;
    private LoginDaoMemoryImpl(){
        this.userService = UserService.getInstance();
    }

    public static LoginDaoMemoryImpl getInstance(){
        if(instance == null){
            instance = new LoginDaoMemoryImpl();
        }
        return instance;
    }
    @Override
    public User findUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }
}
