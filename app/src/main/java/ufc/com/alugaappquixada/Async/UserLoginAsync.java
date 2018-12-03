package ufc.com.alugaappquixada.Async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ufc.com.alugaappquixada.Config.ConfigRetrofit;
import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.repository.UserRepository;
import ufc.com.alugaappquixada.util.Util;
import ufc.com.alugaappquixada.view.LoginView;


public class UserLoginAsync extends AsyncTask<String,Void,User> {
    private UserRepository userRepository;
    private User user;
    private LoginView loginView;
    private Context ctx;

    public UserLoginAsync(Context ctx,LoginView loginView){
        this.loginView = loginView;
        this.ctx = ctx;
        this.userRepository = ConfigRetrofit.getRetrofitConfig().create(UserRepository.class);
    }
    @Override
    protected User doInBackground(String... credentials) {
        final String username = credentials[0];
        final String password = credentials[1];
        final User user = User.create();
        Call<List<User>> userCall = this.userRepository.getUserByEmail(username);
        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    User user  = response.body().get(0);
                    if(user != null) {
                        if (user.getPassword().equals(password)) {
                            Util.saveUserOnSesion(ctx,user);
                            Util.saveUserLoged(ctx,user);
                            loginView.onLoginSucess();
                        } else {
                            loginView.onLoginFailure("Senha inválida");
                        }
                    }
                    loginView.onLoginFailure("Usuário inválido");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("ERROR",t.getMessage());
            }
        });
        return user;
    }

}
