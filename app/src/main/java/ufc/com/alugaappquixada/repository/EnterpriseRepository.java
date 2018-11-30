package ufc.com.alugaappquixada.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ufc.com.alugaappquixada.Model.Enterprise;

public interface EnterpriseRepository {

    @GET("enterprises")
    Call<List<Enterprise>> getAllEnterpriseNearByMe();

    @GET("enterprises/{id}")
    Call<Enterprise> findById(@Path("id") Integer id);
}
