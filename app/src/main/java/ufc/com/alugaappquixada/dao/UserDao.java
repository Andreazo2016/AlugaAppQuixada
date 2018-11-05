package ufc.com.alugaappquixada.dao;

import ufc.com.alugaappquixada.Model.User;

public interface UserDao {
    void save(User user);
    User findOne(String email);
}
