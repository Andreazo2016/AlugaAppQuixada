package ufc.com.alugaappquixada.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ufc.com.alugaappquixada.Model.User;

public interface UserRepository {
    @GET("users")
    Call<List<User>> getUserByEmail(@Query("email") String email);
    @POST("users")
    Call<User> save(@Body User user);
}
