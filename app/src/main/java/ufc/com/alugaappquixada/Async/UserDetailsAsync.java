package ufc.com.alugaappquixada.Async;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ufc.com.alugaappquixada.Config.ConfigRetrofit;
import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.repository.UserRepository;
import ufc.com.alugaappquixada.view.UserDetailsView;

public class UserDetailsAsync extends AsyncTask<User,Void,Void> {

    private UserDetailsView userDetailsView;
    public UserDetailsAsync(UserDetailsView userDetailsView){
        this.userDetailsView = userDetailsView;
    }
    @Override
    protected Void doInBackground(User... users) {
        UserRepository userRepository = ConfigRetrofit.getRetrofitConfig().create(UserRepository.class);
        Call<User> userCall = userRepository.update(users[0],users[0].getId());
        try {
            userCall.execute();
            int i = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
       /*
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    userDetailsView.onSucess();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userDetailsView.onFailure( "Error ao atualizar seus dados!" );
            }
        });
        */
        return null;
    }
}
