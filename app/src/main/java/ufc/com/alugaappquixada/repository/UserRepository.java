package ufc.com.alugaappquixada.repository;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ufc.com.alugaappquixada.Model.User;

public interface UserRepository {
    @GET("users/{email}")
    Call<User> getUserByEmail(@Path("email") String email);
    @POST("users")
    Call<User> save(@Body User user);
}
