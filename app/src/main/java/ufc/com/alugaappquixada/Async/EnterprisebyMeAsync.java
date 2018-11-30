package ufc.com.alugaappquixada.Async;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ufc.com.alugaappquixada.Config.ConfigRetrofit;
import ufc.com.alugaappquixada.Model.Enterprise;
import ufc.com.alugaappquixada.Model.PointMaker;
import ufc.com.alugaappquixada.repository.EnterpriseRepository;
import ufc.com.alugaappquixada.view.MapView;

public class EnterprisebyMeAsync extends AsyncTask<Void,Void,Void> {

    private EnterpriseRepository enterpriseRepository;
    private MapView mapView;
    public EnterprisebyMeAsync(MapView mapView){
        this.mapView = mapView;
        this.enterpriseRepository = ConfigRetrofit.getRetrofitConfig().create(EnterpriseRepository.class);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Call<List<Enterprise>> listCall = this.enterpriseRepository.getAllEnterpriseNearByMe();
        listCall.enqueue(new Callback<List<Enterprise>>() {
            @Override
            public void onResponse(Call<List<Enterprise>> call, Response<List<Enterprise>> response) {
                if(response.isSuccessful()){
                    List<PointMaker> listMockPointMaker = new ArrayList<>();
                    List<Enterprise> avaliableEnterpriseNaerMe = response.body();
                    for(Enterprise enterprise : avaliableEnterpriseNaerMe){
                        listMockPointMaker.add(PointMaker.create(
                                enterprise.getLatitute(),enterprise.getLongitute()
                                ,enterprise.getDescription()
                                ,enterprise.getId()));
                    }
                    mapView.addAvailableApsOnMap(listMockPointMaker);

                }
            }
            @Override
            public void onFailure(Call<List<Enterprise>> call, Throwable t) {

            }
        });


        return null;
    }
}
