package ufc.com.alugaappquixada.dao;

import java.util.List;

import ufc.com.alugaappquixada.Model.Enterprise;

public interface EnterpriseDAO {
    Enterprise save(Enterprise enterprise);
    Enterprise findOne(Integer id);
    List<Enterprise> findAll();
    void update(Enterprise enterprise);
    Enterprise delete(Integer id);
}
