package ufc.com.alugaappquixada.service;

import java.util.List;

import ufc.com.alugaappquixada.Model.Enterprise;
import ufc.com.alugaappquixada.dao.EnterpriseDAO;
import ufc.com.alugaappquixada.dao.EnterpriseDaoMemoryImpl;

public class EnterpriseService {

    private EnterpriseDAO enterpriseDAO;

    public EnterpriseService(){
        this.enterpriseDAO = EnterpriseDaoMemoryImpl.getInstance();
    }

    public List<Enterprise> findAllEnterprise(){
        return enterpriseDAO.findAll();
    }
    public Enterprise findEnterpriseById(Integer id){
        return enterpriseDAO.findOne(id);
    }

}
