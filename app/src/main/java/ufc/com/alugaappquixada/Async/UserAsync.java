package ufc.com.alugaappquixada.Async;

import android.os.AsyncTask;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ufc.com.alugaappquixada.Config.ConfigRetrofit;
import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.repository.UserRepository;

public class UserAsync extends AsyncTask<User,Void,Boolean> {
    private UserRepository userRepository;
    public UserAsync(){
        this.userRepository = ConfigRetrofit.getRetrofitConfig().create(UserRepository.class);
    }
    @Override
    protected Boolean doInBackground(User... users) {

        Call<User> callback = userRepository.save(users[0]);
        int i = 0;
        callback.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Log.d("Sucess","Sucess save");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });

        return null;
    }
}
