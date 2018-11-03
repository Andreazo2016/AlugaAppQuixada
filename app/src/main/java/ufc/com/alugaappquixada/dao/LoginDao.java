package ufc.com.alugaappquixada.dao;

import ufc.com.alugaappquixada.Model.User;

public interface LoginDao {
    User findUserByUsername(String username);
}
