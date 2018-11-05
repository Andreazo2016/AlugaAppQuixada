package ufc.com.alugaappquixada.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ufc.com.alugaappquixada.Model.Enterprise;
import ufc.com.alugaappquixada.Model.Owner;
import ufc.com.alugaappquixada.Model.PhoneNumber;

public class EnterpriseDaoMemoryImpl implements EnterpriseDAO {

    private static  EnterpriseDaoMemoryImpl enterpriseDaoMemory = null;
    private List<Enterprise> dataBaseOfEnterprises ;
    private EnterpriseDaoMemoryImpl(){
        this.dataBaseOfEnterprises = new ArrayList<>();
        this.dataBaseOfEnterprises.add(new Enterprise(1,-5.7323739,-39.0102391,
                new Owner("Andreazo silva","Andreazo2012@gmail.com"
                        ,new PhoneNumber("Comercial","88 999071542"))
                ,"Escola José Oster"));
        this.dataBaseOfEnterprises.add(new Enterprise(2,-5.7320133,-39.010271,
                new Owner("Anderson Souza","anderson2013@gmail.com"
                        ,new PhoneNumber("Zap","88 979071542"))
                ,"Mr Dogr"));
        this.dataBaseOfEnterprises.add(new Enterprise(3,-5.732563,-39.0107431,
                new Owner("Fernando Souza","ferDemais@gmail.com"
                        ,new PhoneNumber("Zap","88 979071533"))
                ,"Bar da Telma"));
        this.dataBaseOfEnterprises.add(new Enterprise(4,-4.969663,-39.014899,
                new Owner("Seu zé","ZéDemais@gmail.com"
                        ,new PhoneNumber("Zap","88 97907456"))
                ,"Lpc Decorações"));
        this.dataBaseOfEnterprises.add(new Enterprise(5,-4.9684459,-39.014324,
                new Owner("Ceverino","CVmais@gmail.com"
                        ,new PhoneNumber("Zap","88 929571533"))
                ,"Apartamentos do freitas"));
    }

    public static EnterpriseDaoMemoryImpl getInstance(){
        if(enterpriseDaoMemory == null ){
            enterpriseDaoMemory = new EnterpriseDaoMemoryImpl();
        }
        return enterpriseDaoMemory;
    }
    @Override
    public Enterprise save(Enterprise enterprise) {
        return null;
    }


    private Enterprise findEnterpriseById(Integer id) throws Exception {
        for(Enterprise enterprise : dataBaseOfEnterprises){
            if( enterprise.getId().equals( id ) ){
                return enterprise;
            }
        }
        throw new Exception("Enterprise was not found!!!");
    }

    @Override
    public Enterprise findOne(Integer id) {
        Enterprise enterprise = null;
        try {

            enterprise = findEnterpriseById(id);

        } catch (Exception e) {

            Log.d("Error Enterprise",e.getMessage());

        }
        return enterprise;
    }

    @Override
    public List<Enterprise> findAll() {
        return this.dataBaseOfEnterprises;
    }

    @Override
    public void update(Enterprise enterprise) {

    }

    @Override
    public Enterprise delete(Integer id) {
        return null;
    }
}
