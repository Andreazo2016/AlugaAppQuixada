package ufc.com.alugaappquixada.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

     public static Retrofit getRetrofitConfig(){
         return new Retrofit
                 .Builder()
                 .baseUrl("http://192.168.11.9:3000/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
     }
}
