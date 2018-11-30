package ufc.com.alugaappquixada.Async;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ufc.com.alugaappquixada.Config.ConfigRetrofit;
import ufc.com.alugaappquixada.Model.Enterprise;
import ufc.com.alugaappquixada.Model.MarkerInformation;
import ufc.com.alugaappquixada.Model.Owner;
import ufc.com.alugaappquixada.repository.EnterpriseRepository;
import ufc.com.alugaappquixada.view.MapView;

public class EnterpriseAsync extends AsyncTask<Integer,Void,Void> {
    private MapView mapView;
    private EnterpriseRepository enterpriseRepository;

    public EnterpriseAsync(MapView mapView){
        this.mapView = mapView;
        this.enterpriseRepository = ConfigRetrofit.getRetrofitConfig().create(EnterpriseRepository.class);
    }

    @Override
    protected Void doInBackground(Integer... id) {
        Call<Enterprise> enterpriseCall =  this.enterpriseRepository.findById(id[0]);

        enterpriseCall.enqueue(new Callback<Enterprise>() {
            @Override
            public void onResponse(Call<Enterprise> call, Response<Enterprise> response) {
                if(response.isSuccessful()){
                    Enterprise enterprise = response.body();
                    if(enterprise != null){
                        Owner owner = enterprise.getOwner();
                        mapView.showInformationAboutMarkerClicked(MarkerInformation
                                .create(owner.getEmail(),owner.getName(),enterprise.getDescription(),owner.getPhoneNumber().getNumber()));
                    }
                }
            }

            @Override
            public void onFailure(Call<Enterprise> call, Throwable t) {

            }
        });

        return null;
    }
}
