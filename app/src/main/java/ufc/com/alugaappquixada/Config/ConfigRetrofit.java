package ufc.com.alugaappquixada.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    private static  Retrofit retrofit = null;

     public static Retrofit getRetrofitConfig(){
         if(retrofit == null){
             return new Retrofit
                     .Builder()
                     .baseUrl("http://192.168.11.9:3000/")
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }
         return  retrofit;

     }
}
